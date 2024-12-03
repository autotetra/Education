import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";
import io from "socket.io-client";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AgentDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);

  useEffect(() => {
    // Initialize WebSocket connection
    const socket = io("http://localhost:8000");
    console.log("WebSocket initialized:", socket);

    socket.on("connect", () => {
      console.log("WebSocket connected with ID:", socket.id);
    });

    // Listen for the "statusUpdated" event
    socket.on("statusUpdated", (updatedTicket) => {
      console.log("Event received in AgentDashboard:", updatedTicket);
      if (!updatedTicket) {
        console.error("No data received for statusUpdated event");
        return;
      }

      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket._id === updatedTicket._id ? updatedTicket : ticket
        )
      );

      // Debug updated state
      setTimeout(() => {
        console.log("Updated tickets state in AgentDashboard:", tickets);
      }, 1000);
    });

    // Cleanup
    return () => {
      socket.disconnect();
      console.log("WebSocket disconnected in AgentDashboard");
    };
  }, []);

  // Fetch all tickets assigned to the agent
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

  // Fetch details for a specific ticket
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

  // Update ticket status
  const handleUpdateTicket = async (ticketId, status) => {};

  return (
    <div>
      <h1>Agent Dashboard</h1>
      <button onClick={fetchTickets}>View Tickets</button>
      <button onClick={handleLogout}>Logout</button>

      <ul>
        {tickets.map((ticket) => (
          <li key={ticket._id}>
            <strong>Title:</strong> {ticket.title} <br />
            <strong>Status:</strong>{" "}
            <select
              value={ticket.status}
              onChange={(e) => handleUpdateTicket(ticket._id, e.target.value)}
            >
              <option value="Waiting">Waiting</option>
              <option value="In Progress">In Progress</option>
              <option value="Resolved">Resolved</option>
            </select>
            <br />
            <button onClick={() => fetchTicketDetails(ticket._id)}>
              View Details
            </button>
          </li>
        ))}
      </ul>

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

export default AgentDashboard;
