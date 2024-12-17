import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

function CreateTicket({ onTicketCreated }) {
  // State for form fields
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [category, setCategory] = useState("");

  // State for success message
  const [successMessage, setSuccessMessage] = useState("");

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = { title, description, category };
    const token = localStorage.getItem("token");

    try {
      // Send the POST request to create a ticket
      const response = await axios.post(endpoints.CREATE_TICKET, data, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      // Show success message
      setSuccessMessage("Ticket created successfully!");

      // Clear form fields
      setTitle(""); // Clear Title
      setDescription(""); // Clear Description
      setCategory(""); // Clear Category

      // Remove success message after 3 seconds
      setTimeout(() => setSuccessMessage(""), 3000);

      // Notify parent component if needed
      if (onTicketCreated) {
        onTicketCreated(response.data.message);
      }
    } catch (error) {
      console.error("Error creating ticket:", error.message);
    }
  };

  return (
    <div className="createTicketForm" style={{ position: "relative" }}>
      <h3>Create a Ticket</h3>

      {/* Success Message */}
      {successMessage && (
        <div
          style={{
            position: "absolute",
            top: "0", // Align to the top of the form
            right: "-220px", // Adjust this value for spacing to the right
            whiteSpace: "nowrap", // Prevent wrapping
          }}
        >
          {successMessage}
        </div>
      )}

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
