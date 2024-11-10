import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import Dashboard from "./components/Dashboard";
import UserRoute from "./components/UserRoute";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/dashboard"
          element={
            <DashboardProtectedRoute>
              <Dashboard />
            </DashboardProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
