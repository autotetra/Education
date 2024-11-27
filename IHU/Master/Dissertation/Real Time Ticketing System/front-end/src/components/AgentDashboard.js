import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AgentDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);

  // Fetch all tickets for the agent
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
      setSelectedTicket(response.data); // Store selected ticket details
    } catch (error) {
      console.error("Error fetching ticket details:", error.message);
    }
  };

  // Delete a specific ticket with confirmation
  const deleteTicket = async (ticketId) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this ticket?"
    );

    if (!confirmDelete) {
      return; // Exit if user cancels the action
    }

    try {
      const token = localStorage.getItem("token");

      await axios.delete(endpoints.DELETE_TICKET(ticketId), {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      // Refresh ticket list after successful deletion
      setTickets(tickets.filter((ticket) => ticket._id !== ticketId));
      alert("Ticket deleted successfully.");
    } catch (error) {
      console.error("Error deleting ticket:", error.message);
      alert("Failed to delete ticket.");
    }
  };

  return (
    <div>
      <h1>Agent Dashboard</h1>
      <button onClick={fetchTickets}>View Tickets</button>

      <ul>
        {tickets.map((ticket) => (
          <li key={ticket._id}>
            <strong>Title:</strong> {ticket.title} <br />
            <strong>Status:</strong> {ticket.status} <br />
            <button onClick={() => fetchTicketDetails(ticket._id)}>
              View Details
            </button>
            <button onClick={() => deleteTicket(ticket._id)}>Delete</button>
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

      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default AgentDashboard;
