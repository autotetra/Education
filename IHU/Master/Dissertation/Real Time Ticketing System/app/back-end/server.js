import express from "express";
import mongoose from "mongoose";
import dotenv from "dotenv";
import path from "path";
import { fileURLToPath } from "url";
import ticketRoutes from "./routes/ticketRoute.js";
import http from "http";
import { initializeWebSocket } from "./services/socket.js";
import { connectProducer, sendMessage } from "./services/kafkaProducer.js";
import { connectConsumer, consumeMessages } from "./services/kafkaConsumer.js";
import authRoute from "./routes/authRoute.js";
import cors from "cors";

dotenv.config();

// Setup __dirname for ES Modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// MongoDB connection
mongoose
  .connect(process.env.MONGODB_URI)
  .then(() => console.log("Connected to MongoDB"))
  .catch((err) => console.error("Could not connect to MongoDB", err));

// Create an express instance
const app = express();

// Create an HTTP server
const server = http.createServer(app);

// Initialize WebSocket connection
const io = initializeWebSocket(server);
app.set("io", io);

// Middlewares --->

// Global JSON parsing middleware
app.use(express.json());

// For form data
app.use(express.urlencoded({ extended: false }));

// Middleware for Cross-Origin Resource Sharing
app.use(
  cors({
    origin: "http://localhost:3000",
    methods: ["GET", "POST", "DELETE", "PUT"],
    allowedHeaders: ["Content-Type", "Authorization", "Role"],
    credentials: true,
  })
);
console.log("CORS middleware applied");

// Serve the static files from "public" directory
app.use(express.static(path.join(__dirname, "public")));

// Use the tickets routes
app.use("/api/tickets", ticketRoutes);

// Use the auth routes for authentication
app.use("/api/auth", authRoute);

// Error handling middleware
app.use((err, req, res, next) => {
  res.status(500).json({ message: err.message });
  console.error(err);
});

// Run Server
const PORT = process.env.PORT || 8080;

server.listen(PORT, async () => {
  console.log(`Server is running on port ${PORT}`);
  //Connect the producer
  await connectProducer();

  // Connect the consumer
  await connectConsumer();

  //Start consuming messages from a topic
  await consumeMessages("new-ticket");
});
