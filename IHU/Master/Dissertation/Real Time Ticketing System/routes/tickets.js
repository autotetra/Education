import express from "express";
import Ticket from "../models/ticket.js";
import { sendMessage } from "../services/kafkaProducer.js";

// Create an instance of Router interface
const router = express.Router();

// Get all tickets
router.get("/", async (req, res) => {
  try {
    const tickets = await Ticket.find();
    res.status(200).json(tickets);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Create new ticket
router.post("/create", async (req, res) => {
  try {
    const { title, description } = req.body;

    // Send the new ticket to Kafka
    const messageData = JSON.stringify({
      title,
      description,
      status: "Waiting",
    });
    await sendMessage("new-ticket", messageData);

    res.status(201).json({ message: "Ticket created successfully" });
  } catch (error) {
    res.status(500).json({ error: "Failed to create ticket." });
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
    const { name, age } = req.body;
    const updatedTicket = await Ticket.findByIdAndUpdate(
      req.params.id,
      { name, age },
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
