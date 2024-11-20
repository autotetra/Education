import express, { response } from "express";
import Ticket from "../models/ticketModel.js";
import { sendMessage } from "../services/kafkaProducer.js";
import authenticateJWT from "../middleware/authMiddleware.js";

// Create an instance of Router interface
const router = express.Router();

// Create new ticket
router.post("/create", authenticateJWT, async (req, res) => {
  try {
    console.log("Received Payload:", req.body);
    const { title, description, category } = req.body;
    const userId = req.user.id; // Extract user ID from decoded token

    // Send the new ticket to Kafka
    const messageData = JSON.stringify({
      title,
      description,
      category,
      status: "Waiting",
      createdBy: userId, // Attach user ID
    });
    await sendMessage("new-ticket", messageData);

    res.status(201).json({ message: "Ticket created successfully" });
  } catch (error) {
    res.status(500).json({ error: "Failed to create ticket." });
  }
});

// Get all tickets
router.get("/", authenticateJWT, async (req, res) => {
  try {
    const role = req.headers.role;
    const userId = req.user.id;

    console.log("Headers received:", req.headers);

    let tickets;
    if (role === "user") {
      // Get tickets for the logged-in user
      tickets = await Ticket.find({ createdBy: userId });
    } else if (role === "agent") {
      // Placeholder logic for agents
      tickets = await Ticket.find({ category: req.user.department });
    } else if (role === "admin") {
      // Admin gets all tickets
      tickets = await Ticket.find();
    } else {
      return res.status(403).json({ message: "Unauthorized role" });
    }

    res.status(200).json(tickets);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Get ticket by id
router.get("/:id", async (req, res) => {
  try {
    const ticket = await Ticket.findById(req.params.id);
    if (!ticket) {
      res.status(404).json({ message: "Ticket not found" });
    }
    res.status(200).json(ticket);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Update ticket
router.put("/:id", async (req, res) => {
  try {
    const { title, description } = req.body;
    const updatedTicket = await Ticket.findByIdAndUpdate(
      req.params.id,
      { title, description },
      { new: true }
    );
    if (!updatedTicket) {
      res.status(404).json({ message: "Ticket not found" });
    }
    res.status(200).json({ updatedTicket });
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Delete ticket
router.delete("/:id", async (req, res) => {
  try {
    const ticketToDelete = await Ticket.findByIdAndDelete(req.params.id);
    if (!ticketToDelete)
      return res.status(404).json({ message: "Ticket not found" });
    res.status(200).json("Ticket deleted successfully");
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

export default router;
