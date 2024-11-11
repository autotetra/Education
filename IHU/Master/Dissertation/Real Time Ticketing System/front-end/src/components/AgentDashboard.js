import React from "react";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AgentDashboard() {
  return (
    <div>
      <h1>Welcome to the Agent Dashboard</h1>
      <p>Here, agents can view and manage their assigned tickets.</p>
      {/*Rest code */}
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default AgentDashboard;
