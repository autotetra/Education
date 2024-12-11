import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "../../api/endpoints";
import io from "socket.io-client";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/"; // Redirect to homepage
};

function AgentDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [socket, setSocket] = useState(null);

  useEffect(() => {
    // Initialize WebSocket connection
    const newSocket = io("http://localhost:8000");
    setSocket(newSocket);

    // Listen for the "statusUpdated" event
    newSocket.on("statusUpdated", (updatedTicket) => {
      console.log("Event received in AgentDashboard:", updatedTicket);
      if (!updatedTicket) {
        console.error("No data received for statusUpdated event");
        return;
      }

      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket._id === updatedTicket._id ? updatedTicket : ticket
        )
      );

      // Debug updated state
      setTimeout(() => {
        console.log("Updated tickets state in AgentDashboard:", tickets);
      }, 1000);
    });

    return () => {
      newSocket.disconnect();
      console.log("WebSocket disconnected in AgentDashboard");
    };
  }, []);

  // Fetch all tickets assigned to the agent
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

  // Update ticket status
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

      if (response.status === 200) {
        // Emit the WebSocket event
        const updatedTicket = response.data.updatedTicket;
        console.log(
          "Emitting statusUpdated event for:",
          response.data.updatedTicket
        );
        socket.emit("statusUpdated", updatedTicket);

        // Update the UI with the updated ticket
        setTickets((prevTickets) =>
          prevTickets.map((ticket) =>
            ticket._id === updatedTicket._id ? updatedTicket : ticket
          )
        );
        console.log("Ticket status updated successfully!");
      } else {
        throw new Error("Failed to update ticket.");
      }
    } catch (error) {
      console.error("Error updating ticket:", error.message);
    }
  };

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

  return (
    <div>
      <h3>Agent Dashboard</h3>
      <button onClick={fetchTickets}>View Tickets</button>
      <button onClick={handleLogout}>Logout</button>

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

export default AgentDashboard;
