// server.js

// Import required modules
import express from "express";
import dotenv from "dotenv";
import mongoose from "mongoose";

// Load environment variables from .env file
dotenv.config();

const app = express();

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
