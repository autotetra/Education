import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "./api/endpoints";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

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
      })
      .catch((error) => {
        if (error.message) {
          console.log(error("Login error:", error.response.data.message)); // Display the error message from backend
        } else {
          console.error("An error occurred:", error.message);
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
