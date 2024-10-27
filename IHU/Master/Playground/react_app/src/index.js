import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";

// Create the root element where the React app wil lbe rendered
const root = ReactDOM.createRoot(document.getElementById("root"));

// Render the App component inside the root element
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
