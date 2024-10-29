import React from "react";

function ControlButtons({ onIncrement, onDecrement }) {
  return (
    <div>
      <button onClick={onIncrement}>Increase</button>
      <button onClick={onDecrement}>Decrease</button>
    </div>
  );
}

export default ControlButtons;
