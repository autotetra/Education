import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AdminDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);

  // Fetch all tickets for the admin
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

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <button onClick={fetchTickets}>View All Tickets</button>

      <ul>
        {tickets.map((ticket) => (
          <li key={ticket._id}>
            <strong>Title:</strong> {ticket.title} <br />
            <strong>Status:</strong> {ticket.status} <br />
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

      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default AdminDashboard;
