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

    // Handle "status-updated" event and log it
    socket.on("status-updated", (updatedTicket) => {
      console.log(
        `Event "statusUpdated" emitted to all clients:`,
        updatedTicket
      );
      io.emit("status-updated", updatedTicket); // Send event to all clients
    });

    // Handle "ticket-created"
    socket.on("ticket-created", (newTicket) => {
      console.log("Event 'ticket-created' emitted to all clients:", newTicket);
      io.emit("ticket-created", newTicket);
    });

    socket.on("disconnect", () => {
      console.log("User disconnected:", socket.id);
    });
  });

  return io;
};
