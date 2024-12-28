import React, { useState, useEffect } from "react";
import axios from "axios";
import endpoints from "../../api/endpoints";
import io from "socket.io-client";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/";
};

function AgentDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [socket, setSocket] = useState(null);
  const [username, setUsername] = useState("");

  useEffect(() => {
    console.log("Agent Dashboard useEffect triggered");
    // Get username
    const username = localStorage.getItem("username");
    setUsername(username);

    // Initialize WebSocket connection
    const socket = io("http://localhost:8000");
    setSocket(socket);

    // Log connection ID when WebSocket connects
    socket.on("connect", () => {
      console.log("WebSocket connected with ID:", socket.id);
    });

    // Handle ticket-created event
    socket.on("ticket-created", () => {
      console.log("New ticket created. Refetching tickets...");
      fetchTickets();
    });

    // Listen for the "statusUpdated" event
    socket.on("status-updated", (updatedTicket) => {
      if (!updatedTicket) {
        console.error("No data received for statusUpdated event");
        return;
      }

      // Update tickets in list
      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket._id === updatedTicket._id ? updatedTicket : ticket
        )
      );

      // Update the selected ticket if it matches the updated ticket
      setSelectedTicket((prevSelected) =>
        prevSelected && prevSelected._id === updatedTicket._id
          ? updatedTicket
          : prevSelected
      );
    });

    // Fetch tickets on mount
    fetchTickets();

    // Cleanup function
    return () => {
      socket.disconnect();
      console.log("WebSocket cleanup for User Dashboard");
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
          "Emitting status-updated event for:",
          response.data.updatedTicket
        );
        socket.emit("status-updated", updatedTicket);

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
      <header className="dashboardHeader">
        <div className="headerLeft">Welcome, {username || "[Username]"}</div>
        <h3 className="headerCenter">Agent Dashboard</h3>
        <button className="logoutButton" onClick={handleLogout}>
          Logout
        </button>
      </header>
      <hr className="divider" />

      {/* Dashboard Content */}
      <div className="dashboardContainer">
        {/* Assigned Tickets Section */}
        <div className="ticketsList">
          <h3 className="sectionHeader">Assigned Tickets</h3>
          <ul className="ticketList">
            {tickets.map((ticket, index) => (
              <React.Fragment key={ticket._id}>
                <li className="ticketItem">
                  <strong>Title:</strong> {ticket.title} <br />
                  <strong>Status:</strong>{" "}
                  <select
                    value={ticket.status}
                    onChange={(e) =>
                      handleUpdateTicket(ticket._id, e.target.value)
                    }
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
                {index !== tickets.length - 1 && (
                  <hr className="ticketItemDivider" />
                )}
              </React.Fragment>
            ))}
          </ul>
        </div>

        {/* Ticket Details Section */}
        <div className="ticketDetails">
          <h3 className="sectionHeader">Ticket Details</h3>
          {selectedTicket ? (
            <div>
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
          ) : (
            <p>Select a ticket to view details.</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default AgentDashboard;
