// server.js
// server.js or the main file where your application starts
import { EventEmitter } from "events";

// Set the default maximum number of listeners
EventEmitter.defaultMaxListeners = 20; // Adjust this number as needed

import express from "express";
import dotenv from "dotenv";
import mongoose from "mongoose";
import { Server } from "socket.io";
import http from "http";
import path from "path";
import { fileURLToPath } from "url";
import Name from "./models/name.js"; // Importing the Name model
import {
  connectProducer,
  sendMessage,
  connectConsumer,
  subscribeAndRunConsumer,
} from "./kafkaClient.js"; // Ensure correct path

// Setting up __dirname for ES Modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Load environment variables from .env file
dotenv.config();

const app = express();
const server = http.createServer(app);
const io = new Server(server);

// Serve files from the public directory
app.use(express.static(path.join(__dirname, "public")));
app.use(express.json());

// Connect to MongoDB
mongoose
  .connect(process.env.MONGO_URI)
  .then(() => {
    console.log(`Connected to MongoDB: ${mongoose.connection.db.databaseName}`);
  })
  .catch((error) => {
    console.error("Error connecting to MongoDB", error);
  });

// WebSocket connection event
io.on("connection", (socket) => {
  console.log("A user connected");

  // Handle name submission
  socket.on("submitName", async (name) => {
    try {
      await sendMessage("names-topic", name); // Produce message to Kafka
      console.log(`Name ${name} sent to Kafka`);
      io.emit("nameAdded", name); // Notify all clients of new submission
    } catch (error) {
      console.error("Error sending name to Kafka:", error);
    }
  });

  // Handle review action
  socket.on("reviewName", (name) => {
    socket.emit("statusUpdate", "Reviewed!");
    console.log(`Name ${name} reviewed`);
  });

  socket.on("disconnect", () => {
    console.log("A user disconnected");
  });
});

// Start the server
const PORT = process.env.PORT || 8000;
server.listen(PORT, async () => {
  console.log(`Server is running on port ${PORT}`);
  await connectProducer();
  await connectConsumer();
  await subscribeAndRunConsumer("names-topic");
});
