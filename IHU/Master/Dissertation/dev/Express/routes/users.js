import express from "express";
import User from "../models/user.js";

// Create an instance of Router interface
const router = express.Router();

// Get all users
router.get("/", async (req, res) => {
  try {
    const users = await User.find();
    res.status(200).json(users);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Get user by id
router.get("/:id", async (req, res) => {
  try {
    const user = await User.findById(req.params.id);
    if (!user) {
      res.status(404).json({ message: "User not found" });
    }
    res.status(200).json(user);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Create new user
router.post("/", async (req, res) => {
  try {
    const { name, age } = req.body;
    const newUser = new User({ name, age });
    const savedUser = await newUser.save();
    res.status(200).json(savedUser);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Update user
router.put("/:id", async (req, res) => {
  try {
    const { name, age } = req.body;
    const updatedUser = await User.findByIdAndUpdate(
      req.params.id,
      { name, age },
      { new: true }
    );
    if (!updatedUser) {
      res.status(404).json({ message: "User not found" });
    }
    res.status(200).json({ updatedUser });
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Delete user
router.delete("/:id", async (req, res) => {
  try {
    const userToDelete = await User.findByIdAndDelete(req.params.id);
    if (!userToDelete)
      return res.status(404).json({ message: "User not found" });
    res.status(200).json("User deleted successfully");
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

export default router;
