import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

function AgentDashboard() {
  const [tickets, setTickets] = useState([]);
  const [showTickets, setShowTickets] = useState(false);
}

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
  } catch (err) {
    console.error("Error fetching tickets: ", err.message);
  }
};

function AgentDashboard() {
  return (
    <div>
      <h1>Welcome to the Agent Dashboard</h1>
      <p>Here, agents can view and manage their assigned tickets.</p>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default AgentDashboard;
