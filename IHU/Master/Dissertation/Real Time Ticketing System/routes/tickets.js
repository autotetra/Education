import express from "express";
import Ticket from "../models/ticket.js";

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

// Create new ticket
router.post("/", async (req, res) => {
  try {
    const { name, age } = req.body;
    const newTicket = new Ticket({ name, age });
    const savedTicket = await newTicket.save();
    res.status(200).json(savedTicket);
  } catch (err) {
    res.status(400).json({ message: err.message });
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
