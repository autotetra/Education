import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";
import CreateTicket from "./CreateTicket";
import io from "socket.io-client";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AdminDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [showCreateTicket, setShowCreateTicket] = useState(false);
  const [socket, setSocket] = useState(null);

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

  const handleCreateTicket = () => {
    setShowCreateTicket(true);
  };

  const handleUpdateTicket = async (ticketId, status) => {};

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <button onClick={fetchTickets}>View All Tickets</button>
      <button onClick={handleCreateTicket}>Create Ticket</button>
      <button onClick={handleLogout}>Logout</button>

      {showCreateTicket && <CreateTicket />}

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
    </div>
  );
}

export default AdminDashboard;
