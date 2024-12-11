import React from "react";
import Login from "./Login";
import Register from "./Register";
import styles from "./Home.module.css";

function Home() {
  return (
    <div>
      <h3 className={styles.header}>Welcome to the Ticketing System</h3>
      <hr className={styles.divider} />
      <div className={styles.homeContainer}>
        <div className={styles.loginSection}>
          <Login />
        </div>
        <div className={styles.registerSection}>
          <Register />
        </div>
      </div>
    </div>
  );
}

export default Home;
