import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints.js";
import { useNavigate } from "react-router-dom";

function Register() {
  const [username, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate(); // Initialize navigate

  const handleRegister = (e) => {
    e.preventDefault();
    axios
      .post(endpoints.REGISTER, {
        username: username,
        email: email,
        password: password,
      })
      .then((response) => {
        console.log("Registration successful:", response.data);
        navigate("/"); // Redirect to the dashboard
      })
      .catch((error) => {
        if (error.response && error.response.data.message) {
          console.error("Registration error:", error.response.data.message);
        } else {
          console.error("An error occurred:", error.message);
        }
      });
  };

  return (
    <div>
      <h2>Register</h2>
      <form onSubmit={handleRegister}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => {
              setUserName(e.target.value);
            }}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="text"
            value={email}
            onChange={(e) => {
              setEmail(e.target.value);
            }}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="text"
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
            }}
            required
          />
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;
