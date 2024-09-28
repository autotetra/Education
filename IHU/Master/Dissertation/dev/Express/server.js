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

const router = express.Router();
const app = express();

app.use("/users", router);

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
router.get("/users", async (req, res) => {
  try {
    const users = await User.find();
    res.json(users);
  } catch (err) {
    res.status(500).json({ message: "Error fetching users" });
  }
});

// Get User
router.get("/users/:id", async (req, res) => {
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
router.put("/users/:id", (req, res) => {
  const users = getUsers();
  const { id } = req.params;
  const { name } = req.body;
  const indexId = req.body.id;
  const userIndex = users.findIndex((user) => user.id === parseInt(id));

  if (userIndex === -1) {
    return res.status(404).json({ message: "User not found" });
  }
  users[userIndex].name = name || users[userIndex].name;
  fs.writeFileSync(
    path.join(__dirname, "users.json"),
    JSON.stringify(users, null, 2)
  );
  res.status(200).json({ message: "User updated", user: users[userIndex] });
});

// Delete User
router.delete("/users/:id", (req, res) => {
  const { id } = req.params;
  const userDeleted = deleteUserById(id);
  if (userDeleted) {
    res.status(200).json({
      message: "User deleted successfully",
    });
  } else {
    res.status(404).json({
      message: "User not found.",
    });
  }
});

// Run Server
const PORT = process.env.PORT || 8080;

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
