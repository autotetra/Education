import React from "react";

function Dashboard() {
  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/"; // Redirect to homepage
  };
  return (
    <div>
      <h1>Welcome to your Dashboard</h1>
      <button onClick={handleLogout}>Logout</button>
      <button>Create Ticket</button>
    </div>
  );
}

export default Dashboard;
