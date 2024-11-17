import React from "react";

function Dashboard() {
  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/"; // Redirect to homepage
  };

  const handleCreateTicket = () => {
    console.log("Create Ticket clicked!");
  };
  return (
    <div>
      <h1>Welcome to your Dashboard</h1>
      <button onClick={handleCreateTicket}> Create Ticket</button>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default Dashboard;
