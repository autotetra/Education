import { Server } from "socket.io";

export const initializeWebSocket = (server) => {
  const io = new Server(server);

  io.on("connection", (socket) => {
    console.log("User connected");

    socket.on("message", (msg) => {
      console.log("Message received:", msg);
      io.emit("message", msg); // Send message to all clients
    });

    socket.on("user disconnected", () => {
      console.log("User disconnected");
    });
  });

  return io;
};
