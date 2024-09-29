import express from "express";
import mongoose from "mongoose";
import dotenv from "dotenv";
import path from "path";
import { fileURLToPath } from "url";
import fs from "fs";
import { error } from "console";
import User from "./models/user.js";

dotenv.config();

// Setup __dirname for ES Modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// MongoDB Connection
mongoose
  .connect(process.env.MONGODB_URI)
  .then(() => console.log("Connected to MongoDB"))
  .catch((err) => console.error("Could not connect to MongoDB", err));

const app = express();

// Middlewares
// Serve the static files from "public" directory
app.use(express.static(path.join(__dirname, "public")));

// Global JSON parsing middleware
app.use(express.json());
// For form data
app.use(express.urlencoded({ extended: false }));

// Error handling middleware
app.use((err, req, res, next) => {
  res.status(500).json({ message: err.message });
});

// Routes

// Get All Users
app.get("/users", async (req, res) => {
  try {
    const users = await User.find();
    res.json(users);
  } catch (err) {
    res.status(500).json({ message: "Error fetching users" });
  }
});

// Get User
app.get("/users/:id", async (req, res) => {
  try {
    const user = await User.findById(req.params.id);
    if (!user) {
      res.status(404).json({ message: "User not found" });
    }
    res.json(user);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Create User
app.post("/users", async (req, res) => {
  try {
    const { name, age } = req.body;
    const newUser = new User({
      name,
      age,
    });
    const savedUser = await newUser.save();
    res.status(201).json(savedUser); // Send back saved user
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Update User
app.put("/users/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const { name, age } = req.body;

    const updatedUser = await User.findByIdAndUpdate(
      id,
      { name, age },
      { new: true }
    );

    if (!updatedUser) {
      return res.status(404).json({ message: "User not found" });
    }
    res.status(200).json(updatedUser);
  } catch (err) {}
});

// Delete User
app.delete("/users/:id", async (req, res) => {
  try {
    const { id } = req.params;

    const userToDelete = await User.findByIdAndDelete(id);
    if (!userToDelete) {
      return res.status(404).json({ message: "User not found" });
    }
    res.status(200).json("User deleted successfully");
  } catch (err) {
    return res.status(400).json({ message: err.message });
  }
});

// Run Server
const PORT = process.env.PORT || 8080;

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
