import React, { useState } from "react";
import Counter from "./Counter";
import ControlButtons from "./ControlButtons";

function App() {
  const [count, setCount] = useState(0);

  const increment = () => setCount(count + 1);
  const decrement = () => setCount(count - 1);

  return (
    <div>
      <h2>Simple Counter App</h2>
      <Counter count={count} />
      <ControlButtons onIncrement={increment} onDecrement={decrement} />
    </div>
  );
}

export default App;
