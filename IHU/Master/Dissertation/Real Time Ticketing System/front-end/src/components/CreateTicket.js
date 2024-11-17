import React, { useState } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";

function CreateTicket() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [category, setCategory] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = [title, description, category];

    axios
      .post(endpoints.CREATE_TICKET, data)
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        if (error.response) {
          console.error(error.response.data.message); // Backend error
        } else {
          console.error("Network or server error:", error.message); // Frontend error
        }
      });
  };

  return <h2>Create a Ticket</h2>;
  <form onSubmit={handleSubmit}>
    <div>
      <label>Title:</label>
      <input
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        required
      />
    </div>
    <div>
      <label>Description:</label>
      <textarea
        value={description}
        onChange={(e) => {
          e.target.value;
        }}
        required
      ></textarea>
    </div>
    <div>
      <label>Category</label>
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
  </form>;
}
