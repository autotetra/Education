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
    default: "Waiting", // Default status
    enum: ["Waiting", "In-Progress", "Closed"], // Available options
  },
});

const Ticket = mongoose.model("Ticket", ticketSchema, "Tickets");
export default Ticket;
