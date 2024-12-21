import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "../../api/endpoints";
import { useNavigate } from "react-router-dom";
import "../../styles/global.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      console.log("Account is already logged in");
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
        console.log("Login successful:", response.data);
        const { token, account } = response.data;
        localStorage.setItem("token", token);
        localStorage.setItem("role", account.role);
        localStorage.setItem("username", account.username);
        if (account.role === "admin") {
          navigate("/admin-dashboard");
        } else if (account.role === "user") {
          navigate("/user-dashboard");
        } else if (account.role === "agent") {
          navigate("/agent-dashboard");
        }
      })
      .catch((error) => {
        if (
          error.response &&
          error.response.data &&
          error.response.data.message
        ) {
          alert(error.response.data.message);
        } else {
          alert("An unexpected error occurred.");
        }
      });
  };

  return (
    <div className="loginContainer">
      <h3 className="loginHeader">Login</h3>
      <form onSubmit={handleLogin} className="loginForm">
        <div className="formGroupLogin">
          <label className="loginLabel">Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="loginInput"
          />
        </div>
        <div className="formGroupLogin">
          <label className="loginLabel">Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="loginInput"
          />
        </div>
        <button type="submit" className="loginButton">
          Login
        </button>
        {errorMessage && (
          <div className="loginErrorMessage">{errorMessage}</div>
        )}
      </form>
    </div>
  );
}

export default Login;
