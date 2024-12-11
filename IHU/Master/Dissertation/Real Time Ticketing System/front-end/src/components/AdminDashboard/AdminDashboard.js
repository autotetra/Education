import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "../../api/endpoints";
import CreateTicket from "../../Shared/CreateTicket";
import io from "socket.io-client";
import styles from "./AdminDashboard.module.css";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AdminDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [socket, setSocket] = useState(null);

  // Initialize WebSocket connection
  useEffect(() => {
    const newSocket = io("http://localhost:8000"); // WebSocket server
    setSocket(newSocket);

    // Subscribe to statusUpdated events
    newSocket.on("statusUpdated", (updatedTicket) => {
      console.log("Event received in AdminDashboard:", updatedTicket);
      if (!updatedTicket) {
        console.error("No data received for statusUpdated event");
        return;
      }

      // Update the ticket list dynamically
      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket._id === updatedTicket._id ? updatedTicket : ticket
        )
      );

      // Update the selected ticket if it matches the updated ticket
      setSelectedTicket((prevSelected) =>
        prevSelected && prevSelected._id === updatedTicket._id
          ? updatedTicket
          : prevSelected
      );

      // Debug updated state
      setTimeout(() => {
        console.log("Updated tickets state in AdminDashboard:", tickets);
      }, 1000);
    });

    return () => {
      newSocket.disconnect(); // Cleanup on unmount
      console.log("WebSocket disconnected in AdminDashboard");
    };
  }, []);

  // Fetch tickets on component mount
  useEffect(() => {
    fetchTickets(); // Call fetchTickets when the component mounts
  }, []);

  // Get all tickets
  const fetchTickets = async () => {
    try {
      const token = localStorage.getItem("token");
      const role = localStorage.getItem("role");

      const response = await axios.get(endpoints.GET_TICKETS, {
        headers: {
          Authorization: `Bearer ${token}`,
          Role: role,
        },
      });
      setTickets(response.data);
    } catch (error) {
      console.error("Error fetching tickets:", error.message);
    }
  };

  // Get specific ticket
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

  // Delete a ticket
  const deleteTicket = async (ticketId) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this ticket?"
    );

    if (!confirmDelete) return;

    try {
      const token = localStorage.getItem("token");
      await axios.delete(endpoints.DELETE_TICKET(ticketId), {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setTickets(tickets.filter((ticket) => ticket._id !== ticketId));
      alert("Ticket deleted successfully.");
    } catch (error) {
      console.error("Error deleting ticket:", error.message);
    }
  };

  const handleUpdateTicket = async (ticketId, status) => {
    try {
      const token = localStorage.getItem("token");

      const response = await axios.put(
        endpoints.UPDATE_TICKET(ticketId),
        { status },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 200) {
        const updatedTicket = response.data.updatedTicket;
        setTickets((prevTickets) =>
          prevTickets.map((ticket) =>
            ticket._id === updatedTicket._id ? updatedTicket : ticket
          )
        );
      } else {
        throw new Error("Failed to update Ticket.");
      }

      socket.emit("statusUpdated", response.data.updatedTicket);
    } catch (error) {
      console.error("Error updating ticket:", error.message);
      alert("Failed to update Ticket");
    }
  };

  return (
    <div>
      {/* Logout Button */}
      <button onClick={handleLogout} className={styles.logoutButton}>
        Logout
      </button>

      {/* Header */}
      <header className={styles.header}>
        <h3>Admin Dashboard</h3>
      </header>
      <hr className={styles.divider} />

      {/* Create Ticket Section */}
      <div className={styles.createTicketSection}>
        <CreateTicket />
      </div>

      <hr className={styles.divider} />

      {/* Tickets and Details */}
      <div className={styles.ticketsContainer}>
        {/* Ticket List Section */}
        <div className={styles.ticketList}>
          <h3 className={styles.sectionHeader}>Ticket List</h3>
          {tickets.length > 0 ? (
            tickets.map((ticket) => (
              <div key={ticket._id} className={styles.ticketCard}>
                <strong>Title:</strong> {ticket.title}
                <br />
                <strong>Status:</strong>{" "}
                <select
                  value={ticket.status}
                  onChange={(e) =>
                    handleUpdateTicket(ticket._id, e.target.value)
                  }
                >
                  <option value="Waiting">Waiting</option>
                  <option value="In Progress">In Progress</option>
                  <option value="Resolved">Resolved</option>
                </select>
                <br />
                <button onClick={() => fetchTicketDetails(ticket._id)}>
                  View Details
                </button>
                <button onClick={() => deleteTicket(ticket._id)}>Delete</button>
              </div>
            ))
          ) : (
            <p>No tickets available</p>
          )}
        </div>

        {/* Ticket Details Section */}
        <div className={styles.ticketDetails}>
          <h3 className={styles.sectionHeader}>Ticket Details</h3>
          {selectedTicket ? (
            <>
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
            </>
          ) : (
            <p>Select a ticket to view details</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
