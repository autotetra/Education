import mongoose from "mongoose";

const ticketSchema = mongoose.Schema({
  name: {
    type: String,
    required: true,
  },
  status: {
    type: String,
    required: true,
  },
});

const Ticket = mongoose.model("Ticket", ticketSchema, "allTickets");
export default Ticket;
