import { Server } from "socket.io";

// Initialize connection
export const initializeWebSocket = (server) => {
  const io = new Server(server, {
    cors: {
      origin: "http://localhost:3000", // Allow frontend origin
      methods: ["GET", "POST", "PUT", "DELETE"], // Allowed HTTP methods
      allowedHeaders: ["Authorization", "Role"], // Headers allowed in requests
      credentials: true, // Allow cookies and credentials
    },
  });

  io.on("connection", (socket) => {
    console.log("User connected:", socket.id);

    socket.on("message", (msg) => {
      console.log("Message received:", msg);
      io.emit("message", msg); // Send message to all clients
    });

    socket.on("disconnect", () => {
      console.log("User disconnected:", socket.id);
    });
  });

  return io;
};
