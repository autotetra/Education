import React from "react";
import Login from "./Login";
import Register from "./Register";
import styles from "./Home.module.css";

function Home() {
  return (
    <div className={styles.homeContainer}>
      <header className={styles.header}>Welcome to the Ticketing System</header>
      <div className={styles.authContainer}>
        <Login />
        <Register />
      </div>
    </div>
  );
}

export default Home;
