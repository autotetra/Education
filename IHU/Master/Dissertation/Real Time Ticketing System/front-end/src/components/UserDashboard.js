import React, { useState, useEffect } from "react";
import CreateTicket from "./CreateTicket";
import endpoints from "../api/endpoints";
import axios from "axios";
import io from "socket.io-client";

function UserDashboard() {
  const [showCreateTicket, setShowCreateTicket] = useState(false);
  const [tickets, setTickets] = useState([]);
  const [showTickets, setShowTickets] = useState(false);
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

  const handleCreateTicket = () => {
    setShowCreateTicket(true);
  };

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
      setShowTickets(true);
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

  const deleteTicket = async (ticketId) => {
    try {
      const confirmDelete = window.confirm(
        "Are you sure you want to delete this ticket?"
      );
      if (!confirmDelete) return;

      const token = localStorage.getItem("token");
      await axios.delete(endpoints.DELETE_TICKET(ticketId), {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      alert("Ticket deleted successfully.");
      fetchTickets(); // Refresh tickets
    } catch (error) {
      console.error("Error deleting ticket:", error.message);
    }
  };

  return (
    <div>
      <h1>User Dashboard</h1>
      <button onClick={handleCreateTicket}>Create Ticket</button>
      <button onClick={handleLogout}>Logout</button>
      <button onClick={fetchTickets}>See Tickets</button>

      {showCreateTicket && <CreateTicket />}

      {showTickets && (
        <div>
          <h2>Your Tickets</h2>
          {tickets.length > 0 ? (
            <ul>
              {tickets.map((ticket) => (
                <li key={ticket._id}>
                  <strong>Title:</strong> {ticket.title} <br />
                  <strong>Status:</strong> {ticket.status} <br />
                  <button onClick={() => fetchTicketDetails(ticket._id)}>
                    View Details
                  </button>
                  <button onClick={() => deleteTicket(ticket._id)}>
                    Delete
                  </button>
                </li>
              ))}
            </ul>
          ) : (
            <p>No tickets found.</p>
          )}
        </div>
      )}

      {selectedTicket && (
        <div>
          <h2>Ticket Details</h2>
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
