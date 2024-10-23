import express from "express";
import mongoose from "mongoose";
import dotenv from "dotenv";
import path from "path";
import { fileURLToPath } from "url";
import ticketRoutes from "./routes/tickets.js";
import http from "http";
import { initializeWebSocket } from "./services/socket.js";
import { connectProducer, sendMessage } from "./services/kafkaProducer.js";
import { connectConsumer, consumeMessages } from "./services/kafkaConsumer.js";
import authRoute from "./routes/authRoute.js";

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

const io = initializeWebSocket(server);

// Middlewares
// Serve the static files from "public" directory
app.use(express.static(path.join(__dirname, "public")));

// Global JSON parsing middleware
app.use(express.json());
// For form data
app.use(express.urlencoded({ extended: false }));

// Use the tickets routes
app.use("/tickets", ticketRoutes);

// Use the auth routes for authentication
app.use("/api/auth", authRoute);

// Error handling middleware
app.use((err, req, res, next) => {
  res.status(500).json({ message: err.message });
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
