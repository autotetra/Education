import React from "react";
import Login from "./Login";
import Register from "./Register";

function Home() {
  return (
    <div>
      {/* Header */}
      <header className="dashboardHeader">
        <h3>Welcome to the Ticketing System</h3>
      </header>
      <hr className="divider" />

      {/* Main Container */}
      <div className="verticalContainer">
        {/* Login Section */}
        <div className="loginContainer">
          <Login />
        </div>

        {/* Register Section */}
        <div className="registerContainer">
          <Register />
        </div>
      </div>
    </div>
  );
}

export default Home;
