import mongoose from "mongoose";

// Define Ticket Schema
const ticketSchema = mongoose.Schema({
  title: {
    type: String,
    required: true,
  },
  description: {
    type: String,
    required: true,
  },
  status: {
    type: String,
    required: true,
    enum: ["Waiting", "In-Progress", "Closed"], // Available options
    default: "Waiting", // Default status
  },
  category: {
    type: String,
    enum: ["Technical Support", "General Inquiry", "Sales"],
    required: true,
  },
  createdBy: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "Account", // Reference the user / agent schema
    required: true,
  },
});

const Ticket = mongoose.model("Ticket", ticketSchema, "Tickets");
export default Ticket;
