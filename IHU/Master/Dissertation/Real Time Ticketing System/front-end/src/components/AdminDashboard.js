import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

function AdminDashboard() {
  const [role, setRole] = useState("user");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [department, setDepartment] = useState("N/A");

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/"; // Redirect to homepage
  };

  const handleRegister = (e) => {
    e.preventDefault();
    const data = {
      username,
      email,
      password,
      role,
      department: role === "agent" ? department : "N/A",
    };
    console.log(data);
    axios
      .post(endpoints.REGISTER, data)
      .then((response) => {
        console.log("Registration successful:", response.data);
      })
      .catch((error) => {
        console.error(
          "Registration error:",
          error.response?.data?.message || error.message
        );
      });
  };

  return (
    <div>
      <h2>Admin Dashboard - Register User/Agent</h2>
      <form onSubmit={handleRegister}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Role:</label>
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="user">User</option>
            <option value="agent">Agent</option>
          </select>
        </div>
        {role === "agent" && (
          <div>
            <label>Department:</label>
            <select
              value={department}
              onChange={(e) => setDepartment(e.target.value)}
            >
              <option value="Technical Support">Technical Support</option>
              <option value="General Inquiry">General Inquiry</option>
              <option value="Sales">Sales</option>
            </select>
          </div>
        )}
        <button type="submit">Register</button>
      </form>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default AdminDashboard;
