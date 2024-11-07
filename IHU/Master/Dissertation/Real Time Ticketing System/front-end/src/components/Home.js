import React from "react";
import Login from "./Login";
import Register from "./Register";

function Home() {
  return (
    <div>
      <h1>Welcome to the Ticketing System</h1>
      <div>
        <Login />
        <Register />
      </div>
    </div>
  );
}

export default Home;
