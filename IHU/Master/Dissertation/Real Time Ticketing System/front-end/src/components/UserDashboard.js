import React, { useState } from "react";
import CreateTicket from "./CreateTicket";
import endpoints from "../api/endpoints";
import axios from "axios";

function UserDashboard() {
  const [showCreateTicket, setShowCreateTicket] = useState(false);
  const [tickets, setTickets] = useState([]);
  const [showTickets, setShowTickets] = useState(false); // Toggle for ticket list visibility
  const [selectedTicket, setSelectedTicket] = useState(null); // Store details of selected ticket

  const handleCreateTicket = () => {
    setShowCreateTicket(true);
  };

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/"; // Redirect to homepage
  };

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

  return (
    <div>
      <h1>User Dashboard</h1>
      <button onClick={handleCreateTicket}>Create Ticket</button>
      <button onClick={handleLogout}>Logout</button>
      <button onClick={fetchTickets}>See Tickets</button>

      {/* Render Create Ticket Component */}
      {showCreateTicket && <CreateTicket />}

      {/* Render Tickets */}
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
                </li>
              ))}
            </ul>
          ) : (
            <p>No tickets found.</p>
          )}
        </div>
      )}

      {/* Render Selected Ticket Details */}
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
