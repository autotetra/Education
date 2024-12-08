import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components//Home/Homepage";
import UserRoute from "./components/UserDashboard/UserRoute";
import Dashboard from "./components/UserDashboard/UserDashboard";
import AdminRoute from "./components//AdminDashboard/AdminRoute";
import AdminDashboard from "./components/AdminDashboard/AdminDashboard";
import AgentDashboard from "./components/AgentDashboard/AgentDashboard";
import AgentRoute from "./components/AgentDashboard/AgentRoute";
// Test component
import WebSocketTest from "./components/clipboard/[TEST]websocket";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/user-dashboard"
          element={
            <UserRoute>
              <Dashboard />
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
        <Route path="/test-websocket" element={<WebSocketTest />} />
      </Routes>
    </Router>
  );
}

export default App;
