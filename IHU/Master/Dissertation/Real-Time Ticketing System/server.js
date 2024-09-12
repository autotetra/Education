// server.js

// Import required modules
import express from "express";
import dotenv from "dotenv";
import mongoose from "mongoose";
import { Server } from "socket.io";
import http from "http";
// Serve files from the public directory
import path from "path";
import { fileURLToPath } from "url";

// Import Kafka functions
import {
  connectProducer,
  sendMessage,
  connectConsumer,
  subscribeAndRunConsumer,
} from "./kafkaClient.js"; //Adjust path if necessary

// Setting up __dirname for ES Modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Load environment variables from .env file
dotenv.config();

const app = express();

// Serve files from the public directory
app.use(express.static(path.join(__dirname, "public")));

// Create an HTTP server
const server = http.createServer(app);

// Initialize Socket.io
const io = new Server(server);

// Middleware to parse incoming requests with JSON payloads
app.use(express.json());

// Basic route to check if the server is running
app.get("/posts", (req, res) => {
  res.send("Server is running");
});

// Connect to MongoDB
mongoose
  .connect(process.env.MONGO_URI)
  .then(() => console.log("Connected to MongoDB"))
  .catch((error) => console.error("Error connecting to MongoDB", error));

// WebSocket connection event
io.on("connection", (socket) => {
  console.log("A user connected");

  // Listen for messages from the client
  socket.on("message", (msg) => {
    console.log("Message received: ", msg);

    // Send a message back to the client
    socket.emit("message", "Hello from the server!");
  });

  // Handle disconnection
  socket.on("disconnect", () => {
    console.log("A user disconnected");
  });
});

// Start the server
const PORT = process.env.PORT || 8000;
server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

// Connect the Kafka producer and send a test message
connectProducer().then(() => {
  sendMessage("test-topic", "Hello, this is a test message from the producer!")
    .then(() => console.log("Test message sent successfully"))
    .catch((error) => console.error("Error sending test message:", error));
});
