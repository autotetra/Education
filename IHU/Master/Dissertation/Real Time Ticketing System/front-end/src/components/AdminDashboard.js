import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "../api/endpoints";
import CreateTicket from "./CreateTicket";
import io from "socket.io-client";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AdminDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [showCreateTicket, setShowCreateTicket] = useState(false);
  const [socket, setSocket] = useState(null);

  // Initialize WebSocket connection
  useEffect(() => {
    const newSocket = io("http://localhost:8000"); // WebSocket server
    setSocket(newSocket);

    // Subscribe to statusUpdated events
    newSocket.on("statusUpdated", (updatedTicket) => {
      console.log("Event received in AdminDashboard:", updatedTicket);
      if (!updatedTicket) {
        console.error("No data received for statusUpdated event");
        return;
      }

      // Update the ticket list dynamically
      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket._id === updatedTicket._id ? updatedTicket : ticket
        )
      );

      // Debug updated state
      setTimeout(() => {
        console.log("Updated tickets state in AdminDashboard:", tickets);
      }, 1000);
    });

    return () => {
      newSocket.disconnect(); // Cleanup on unmount
      console.log("WebSocket disconnected in AdminDashboard");
    };
  }, []);

  // Get all tickets
  const fetchTickets = async () => {
    try {
      const token = localStorage.getItem("token");
      const role = localStorage.getItem("role");

      const response = await axios.get(endpoints.GET_TICKETS, {
        headers: {
          Authorization: `Bearer ${token}`,
          Role: role,
        },
      });
      setTickets(response.data);
    } catch (error) {
      console.error("Error fetching tickets:", error.message);
    }
  };

  // Get specific ticket
  const fetchTicketDetails = async (ticketId) => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(endpoints.GET_TICKET_BY_ID(ticketId), {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setSelectedTicket(response.data);
    } catch (error) {
      console.error("Error fetching ticket details:", error.message);
    }
  };

  // Delete a ticket
  const deleteTicket = async (ticketId) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this ticket?"
    );

    if (!confirmDelete) return;

    try {
      const token = localStorage.getItem("token");
      await axios.delete(endpoints.DELETE_TICKET(ticketId), {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setTickets(tickets.filter((ticket) => ticket._id !== ticketId));
      alert("Ticket deleted successfully.");
    } catch (error) {
      console.error("Error deleting ticket:", error.message);
    }
  };

  const handleCreateTicket = () => {
    setShowCreateTicket(true);
  };

  const handleUpdateTicket = async (ticketId, status) => {
    try {
      const token = localStorage.getItem("token");

      // Send the PUT request to update the ticket status
      const response = await axios.put(
        endpoints.UPDATE_TICKET(ticketId),
        { status },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      // Check if response is successful
      if (response.status === 200) {
        // Update the UI with the updated ticket
        const updatedTicket = response.data.updatedTicket;
        setTickets((prevTickets) =>
          prevTickets.map((ticket) =>
            ticket._id === updatedTicket._id ? updatedTicket : ticket
          )
        );
      } else {
        throw new Error("Failed to update Ticket.");
      }

      // Emit the WebSocket event
      console.log(
        "Emitting statusUpdated event for:",
        response.data.updatedTicket
      );
      socket.emit("statusUpdated", response.data.updatedTicket);
      console.log("statusUpdated event emitted.");
    } catch (error) {
      console.error("Error updating ticket:", error.message);
      alert("Failed to update Ticket");
    }
  };

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <button onClick={fetchTickets}>View All Tickets</button>
      <button onClick={handleCreateTicket}>Create Ticket</button>
      <button onClick={handleLogout}>Logout</button>

      {showCreateTicket && <CreateTicket />}

      <ul>
        {tickets.map((ticket) => (
          <li key={ticket._id}>
            <strong>Title:</strong> {ticket.title} <br />
            <strong>Status:</strong>{" "}
            <select
              value={ticket.status}
              onChange={(e) => handleUpdateTicket(ticket._id, e.target.value)}
            >
              <option value="Waiting">Waiting</option>
              <option value="In Progress">In Progress</option>
              <option value="Resolved">Resolved</option>
            </select>
            <br />
            <button onClick={() => fetchTicketDetails(ticket._id)}>
              View Details
            </button>
            <button onClick={() => deleteTicket(ticket._id)}>Delete</button>
          </li>
        ))}
      </ul>

      {selectedTicket && (
        <div>
          <h2>Ticket Details</h2>
          <p>
            <strong>Title:</strong> {selectedTicket.title}
          </p>
          <p>
            <strong>Description:</strong> {selectedTicket.description}
          </p>
          <p>
            <strong>Category:</strong> {selectedTicket.category}
          </p>
          <p>
            <strong>Status:</strong> {selectedTicket.status}
          </p>
          <p>
            <strong>Created By:</strong>{" "}
            {selectedTicket.createdBy?.username || "N/A"}
          </p>
        </div>
      )}
    </div>
  );
}

export default AdminDashboard;
