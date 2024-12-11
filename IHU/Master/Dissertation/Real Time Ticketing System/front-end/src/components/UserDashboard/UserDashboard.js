import React, { useState, useEffect } from "react";
import CreateTicket from "../../Shared/CreateTicket";
import endpoints from "../../api/endpoints";
import axios from "axios";
import io from "socket.io-client";
import styles from "./UserDashboard.module.css";

function UserDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);

  useEffect(() => {
    // Initialize WebSocket connection
    const socket = io("http://localhost:8000");
    console.log("WebSocket initialized:", socket);

    // On connect, log socket ID
    socket.on("connect", () => {
      console.log("WebSocket connected with ID:", socket.id);
    });

    // Listen for the "statusUpdated" event
    socket.on("statusUpdated", (updatedTicket) => {
      console.log("Event received in UserDashboard:", updatedTicket);
      if (!updatedTicket) {
        console.error("No data received for statusUpdated event");
        return;
      }

      // Debug previous state
      console.log("Previous tickets:", tickets);

      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket._id === updatedTicket._id ? updatedTicket : ticket
        )
      );

      // Debug updated state (state changes are asynchronous)
      setTimeout(() => {
        console.log("Updated tickets state:", tickets);
      }, 1000);
    });

    // Cleanup
    return () => {
      socket.disconnect();
      console.log("WebSocket disconnected");
    };
  }, []);

  useEffect(() => {
    // Fetch tickets on mount
    fetchTickets();
  }, []);

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/";
  };

  const fetchTickets = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(endpoints.GET_TICKETS, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setTickets(response.data);
    } catch (error) {
      console.error("Error fetching tickets:", error.message);
    }
  };

  const fetchTicketDetails = async (ticketId) => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(endpoints.GET_TICKET_BY_ID(ticketId), {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setSelectedTicket(response.data);
    } catch (error) {
      console.error("Error fetching ticket details:", error.message);
    }
  };

  return (
    <div>
      <h3 className={styles.header}>User Dashboard</h3>
      <button onClick={handleLogout} className={styles.logoutButton}>
        Logout
      </button>
      <hr className={styles.divider} />
      <div className={styles.dashboardContainer}>
        <div className={styles.ticketsList}>
          <h3>My Tickets</h3>
          {tickets.length > 0 ? (
            <ul className={styles.ticketList}>
              {tickets.map((ticket) => (
                <li key={ticket._id} className={styles.ticketItem}>
                  <strong>Title:</strong> {ticket.title} <br />
                  <strong>Status:</strong> {ticket.status} <br />
                  <button onClick={() => fetchTicketDetails(ticket._id)}>
                    View Details
                  </button>
                </li>
              ))}
            </ul>
          ) : (
            <p>No tickets found.</p>
          )}
        </div>

        <div className={styles.createTicketSection}>
          <h3>Create Ticket</h3>
          <CreateTicket />
        </div>
      </div>

      {selectedTicket && (
        <div className={styles.ticketDetails}>
          <h3>Ticket Details</h3>
          <p>
            <strong>Title:</strong> {selectedTicket.title}
          </p>
          <p>
            <strong>Description:</strong> {selectedTicket.description}
          </p>
          <p>
            <strong>Category:</strong> {selectedTicket.category}
          </p>
          <p>
            <strong>Status:</strong> {selectedTicket.status}
          </p>
          <p>
            <strong>Created By:</strong>{" "}
            {selectedTicket.createdBy?.username || "N/A"}
          </p>
        </div>
      )}
    </div>
  );
}

export default UserDashboard;
