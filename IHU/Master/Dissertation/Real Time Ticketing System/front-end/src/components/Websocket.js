import React, { useEffect } from "react";
import io from "socket.io-client";

const WebSocketTest = () => {
  useEffect(() => {
    // Connect to the WebSocket server
    const socket = io("http://localhost:8000"); // Update the URL if different

    // Listen for WebSocket events
    socket.on("connect", () => {
      console.log("Connected to WebSocket server:", socket.id);
    });

    socket.on("message", (msg) => {
      console.log("Message from server:", msg);
    });

    // Emit a test message
    socket.emit("message", "Hello Server!");

    // Clean up the socket connection on unmount
    return () => socket.disconnect();
  }, []);

  return (
    <div>
      <h2>WebSocket Test</h2>
      <p>Check the console for WebSocket connection logs.</p>
    </div>
  );
};

export default WebSocketTest;
