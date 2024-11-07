import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "../api/endpoints.js";
import { useNavigate } from "react-router-dom";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate(); // Initialize navigate

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      console.log("User is already logged in");
      // Navigate to a protected page or perform some other action
    }
  }, []);

  const handleLogin = (e) => {
    e.preventDefault();
    axios
      .post(endpoints.LOGIN, {
        email: email,
        password: password,
      })
      .then((response) => {
        console.log("Login successful:", response.data); // Loge the response data
        const token = response.data.token; // Get the token from response
        localStorage.setItem("token", response.data.token);
        navigate("/dashboard"); // Redirect user to the dashboard page
      })
      .catch((error) => {
        if (
          error.response &&
          error.response.data &&
          error.response.data.message
        ) {
          console.error("Login error:", error.response.data.message); // Display error message from backend
        }
      });
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label>Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
