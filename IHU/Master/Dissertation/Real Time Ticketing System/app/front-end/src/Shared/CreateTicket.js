import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

function CreateTicket({ onTicketCreated }) {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [category, setCategory] = useState("");

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = { title, description, category };
    const token = localStorage.getItem("token");

    try {
      // Send the POST request to create a ticket
      await axios.post(endpoints.CREATE_TICKET, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      // Show success alert
      alert("Ticket created successfully!");

      // Clear form fields
      setTitle("");
      setDescription("");
      setCategory("");

      // Notify parent component if needed
      if (onTicketCreated) {
        onTicketCreated("Ticket created successfully!");
      }
    } catch (error) {
      console.error("Error creating ticket:", error.message);
      alert("Failed to create ticket. Please try again.");
    }
  };

  return (
    <div className="createTicketForm">
      <h3>Create a Ticket</h3>

      {/* Form */}
      <form onSubmit={handleSubmit}>
        <div className="formGroup">
          <label>Title:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div className="formGroup">
          <label>Description:</label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          ></textarea>
        </div>
        <div className="formGroup">
          <label>Category:</label>
          <select
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            required
          >
            <option value="" disabled>
              Select a category
            </option>
            <option value="Technical Support">Technical Support</option>
            <option value="General Inquiry">General Inquiry</option>
            <option value="Sales">Sales</option>
          </select>
        </div>
        <button type="submit">Create Ticket</button>
      </form>
    </div>
  );
}

export default CreateTicket;
