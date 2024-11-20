import React, { useState } from "react";
import CreateTicket from "./CreateTicket";
import endpoints from "../api/endpoints";
import axios from "axios";

function UserDashboard() {
  const [showCreateTicket, setShowCreateTicket] = useState(false);
  const [tickets, setTickets] = useState([]);
  const [showTickets, setShowTickets] = useState(false); // Toggle for ticket list visibility

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
      const response = await axios.get(endpoints.GET_TICKETS, {
        headers: {
          Authorization: `Bearer ${token}`, // Pass token in header
        },
      });
      setTickets(response.data);
      setShowTickets(true); // Show the tickets section
    } catch (error) {
      console.error("Error fetching tickets: ", error.message);
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
                  <strong>Description:</strong> {ticket.description} <br />
                  <strong>Category:</strong> {ticket.category} <br />
                  <strong>Status:</strong> {ticket.status}
                </li>
              ))}
            </ul>
          ) : (
            <p>No tickets found.</p>
          )}
        </div>
      )}
    </div>
  );
}

export default UserDashboard;
