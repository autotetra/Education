import React from "react";
import Login from "./Login";
import Register from "./Register";
import styles from "../Home/Homepage.module.css";

function Home() {
  return (
    <div>
      {/* Header */}
      <header className={styles.header}>
        <h3>Welcome to the Ticketing System</h3>
      </header>
      <hr className={styles.divider} />

      {/* Main Container */}
      <div className={styles.homeContainer}>
        {/* Login Section */}
        <div className={styles.loginSection}>
          <h3>Login</h3>
          <Login />
        </div>

        {/* Register Section */}
        <div className={styles.registerSection}>
          <h3>Register</h3>
          <Register />
        </div>
      </div>
    </div>
  );
}

export default Home;
