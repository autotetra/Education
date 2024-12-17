import React from "react";
import { Navigate } from "react-router-dom";

function AgentRoute({ children }) {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  // If the user is not logged in or the role is not agent, redirect to home
  if (!token || !role === "agent") {
    return <Navigate to="/" replace />;
  }

  // if the user is an AgentRoute, render the children (AgentDashboard)
  return children;
}

export default AgentRoute;
