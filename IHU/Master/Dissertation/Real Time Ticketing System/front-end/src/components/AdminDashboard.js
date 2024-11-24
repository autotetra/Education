import React, { useEffect, useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

function AdminDashboard() {
  const [tickets, setTickets] = useState([]);
  const [showTickets, setShowTickets] = useState(false);
  const [error, setError] = useState("");
  const [role, setRole] = useState("user");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [department, setDepartment] = useState("N/A");

  // Fetch Tickets
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
      setError(err.response?.data?.message || "Failed to fetch tickets.");
    }
  };

  useEffect(() => {
    fetchTickets();
  }, []);

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
      {showTickets && (
        <div>
          <h3>All Tickets</h3>
          {error && <p style={{ color: "red" }}>{error}</p>}
          {tickets.length > 0 ? (
            <ul>
              {tickets.map((ticket) => (
                <li key={ticket._id}>
                  <strong>Title:</strong> {ticket.title} <br />
                  <strong>Description:</strong> {ticket.description} <br />
                  <strong>Category:</strong> {ticket.category} <br />
                  <strong>Status:</strong> {ticket.status} <br />
                  <strong>Created By:</strong>{" "}
                  {ticket.createdBy?.username || "N/A"}
                  <br />
                </li>
              ))}
            </ul>
          ) : (
            <p>No tickets found.</p>
          )}
        </div>
      )}
    </div>
  );
}

export default AdminDashboard;
