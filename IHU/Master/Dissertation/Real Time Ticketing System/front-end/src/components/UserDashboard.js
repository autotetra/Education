import React, { useState } from "react";
import CreateTicket from "./CreateTicket";

function UserDashboard() {
  const [showCreateTicket, setShowCreateTicket] = useState(false);

  const handleCreateTicket = () => {
    setShowCreateTicket(true);
  };

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/"; // Redirect to homepage
  };

  return (
    <div>
      <h1>User Dashboard</h1>
      <button onClick={handleCreateTicket}>Create Ticket</button>
      <button onClick={handleLogout}>Logout</button>
      {showCreateTicket && <CreateTicket />}
    </div>
  );
}

export default UserDashboard;
