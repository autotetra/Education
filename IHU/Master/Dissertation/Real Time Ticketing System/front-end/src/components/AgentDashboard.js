import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AgentDashboard() {
  const [tickets, setTickets] = useState([]);
  const [showTickets, setShowTickets] = useState(false);

  // Function to fetch tickets assigned to the agent
  const fetchTickets = async () => {
    try {
      const token = localStorage.getItem("token");
      const role = localStorage.getItem("role"); // Fetch role from localStorage

      const response = await axios.get(endpoints.GET_TICKETS, {
        headers: {
          Authorization: `Bearer ${token}`,
          Role: role,
        },
      });

      setTickets(response.data); // Update tickets state
      setShowTickets(true); // Show tickets after fetching
    } catch (error) {
      console.error(
        "Error fetching tickets:",
        error.response?.data?.message || error.message
      );
    }
  };

  return (
    <div>
      <h1>Welcome to the Agent Dashboard</h1>
      <p>Here, agents can view and manage their assigned tickets.</p>

      {/* Button to fetch tickets */}
      <button onClick={fetchTickets}>View Tickets</button>

      {/* Render tickets if showTickets is true */}
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
                  <strong>Status:</strong> {ticket.status} <br />
                  <strong>Created By:</strong>{" "}
                  {ticket.createdBy?.username || "N/A"}
                </li>
              ))}
            </ul>
          ) : (
            <p>No tickets found.</p>
          )}
        </div>
      )}

      {/* Logout button */}
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default AgentDashboard;
