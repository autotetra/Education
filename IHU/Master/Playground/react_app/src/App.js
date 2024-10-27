import React from "react";
import Greeting from "./Greeting"; // Import the Greeting component

function App() {
  return (
    <div>
      <h1>Welcome to Your Simple React App!</h1>
      <p>Let's learn React step-by-step.</p>

      {/* Use the Greeting component with a prop */}
      <Greeting name="John" />
      <Greeting name="Jane" />
    </div>
  );
}

export default App;
