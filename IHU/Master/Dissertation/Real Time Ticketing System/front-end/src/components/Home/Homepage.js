import React from "react";
import "../../styles/global.css"; // Import the existing global CSS file

function Home() {
  return (
    <div>
      {/* Header */}
      <header className="dashboardHeader">
        <h3>Welcome to the Ticketing System</h3>
      </header>
      <hr className="divider" />

      {/* Main Container */}
      <div className="homeContainer">
        {/* Login Section */}
        <div className="formSection">
          <h3>Login</h3>
          <div className="formGroupUnique">
            <label>Email:</label>
            <input type="text" placeholder="Enter Email" />
          </div>
          <div className="formGroupUnique">
            <label>Password:</label>
            <input type="password" placeholder="Enter Password" />
          </div>
          <button className="buttonUnique">Login</button>
        </div>

        {/* Register Section */}
        <div className="formSection">
          <h3>Register</h3>
          <div className="formGroupUnique">
            <label>Username:</label>
            <input type="text" placeholder="Enter Username" />
          </div>
          <div className="formGroupUnique">
            <label>Email:</label>
            <input type="text" placeholder="Enter Email" />
          </div>
          <div className="formGroupUnique">
            <label>Password:</label>
            <input type="password" placeholder="Enter Password" />
          </div>
          <button className="buttonUnique">Register</button>
        </div>
      </div>
    </div>
  );
}

export default Home;
