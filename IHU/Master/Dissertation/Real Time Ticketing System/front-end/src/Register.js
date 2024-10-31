import React, { useState } from "react";

function Register() {
  const [username, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleRegister = (e) => {
    e.preventDefault();
    // Logic for handling register submission (to be implemented later)
    console.log("Username: ", username);
    console.log("Email: ", email);
    console.log("Password: ", password);
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
