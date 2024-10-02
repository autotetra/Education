import express from "express";
import mongoose from "mongoose";
import dotenv from "dotenv";
import path from "path";
import { fileURLToPath } from "url";
import userRoutes from "./routes/users.js";
import { Server } from "socket.io";
import http from "http";

dotenv.config();

// Setup __dirname for ES Modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// MongoDB Connection
mongoose
  .connect(process.env.MONGODB_URI)
  .then(() => console.log("Connected to MongoDB"))
  .catch((err) => console.error("Could not connect to MongoDB", err));

// Create an express instance
const app = express();

// Create an HTTP server
const server = http.createServer(app);

// Setup Websocket server
const io = new Server(server);

io.on("connection", (socket) => {
  console.log("A user connection");

  // Listen for a specific event
  socket.on("message", (msg) => {
    console.log("Message received:", msg);
    io.emit("message", msg);
  });

  socket.on("disconnect", (msg) => {
    console.log("User disconnected");
  });
});

// Middlewares
// Serve the static files from "public" directory
app.use(express.static(path.join(__dirname, "public")));

// Global JSON parsing middleware
app.use(express.json());
// For form data
app.use(express.urlencoded({ extended: false }));

// Use the user routes
app.use("/users", userRoutes);

// Error handling middleware
app.use((err, req, res, next) => {
  res.status(500).json({ message: err.message });
});

// Run Server
const PORT = process.env.PORT || 8080;

server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
