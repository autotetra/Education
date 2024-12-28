import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components//Home/Homepage";
import UserRoute from "./components/UserDashboard/UserRoute";
import UserDashboard from "./components/UserDashboard/UserDashboard";
import AdminRoute from "./components//AdminDashboard/AdminRoute";
import AdminDashboard from "./components/AdminDashboard/AdminDashboard";
import AgentDashboard from "./components/AgentDashboard/AgentDashboard";
import AgentRoute from "./components/AgentDashboard/AgentRoute";
import "./styles/global.css";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/user-dashboard"
          element={
            <UserRoute>
              <UserDashboard />
            </UserRoute>
          }
        />
        <Route
          path="/agent-dashboard"
          element={
            <AgentRoute>
              <AgentDashboard />
            </AgentRoute>
          }
        />
        <Route
          path="/admin-dashboard"
          element={
            <AdminRoute>
              <AdminDashboard />
            </AdminRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
