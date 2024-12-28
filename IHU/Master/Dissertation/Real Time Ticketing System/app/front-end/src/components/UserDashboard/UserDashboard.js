import React, { useState, useEffect } from "react";
import CreateTicket from "../../Shared/CreateTicket";
import endpoints from "../../api/endpoints";
import axios from "axios";
import io from "socket.io-client";

const handleLogout = () => {
  localStorage.clear();
  window.location.href = "/";
};

function UserDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [username, setUsername] = useState("");
  const [socket, setSocket] = useState(null);

  useEffect(() => {
    console.log("User Dashboard useEffect triggered");
    // Get username once
    const username = localStorage.getItem("username");
    setUsername(username);

    // Fetch tickets once on mount
    fetchTickets();

    // Initialize WebSocket connection
    const socket = io("http://localhost:8000", { autoConnect: false });
    socket.connect();
    setSocket(socket);
    // Log WebSocket connection
    socket.on("connect", () => {
      console.log("WebSocket connected with ID:", socket.id);
    });

    // Handle WebSocket error
    socket.on("error", (err) => {
      console.error("WebSocket error:", err);
    });

    // Handle WebSocket disconnect
    socket.on("disconnect", (reason) => {
      console.warn("WebSocket disconnected:", reason);
    });

    // Handle ticket-created event
    socket.on("ticket-created", () => {
      console.log("New ticket created. Refetching tickets...");
      fetchTickets();
    });

    // Handle ticket-updated event
    socket.on("status-updated", (updatedTicket) => {
      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket._id === updatedTicket._id ? updatedTicket : ticket
        )
      );
      setSelectedTicket((prevSelected) =>
        prevSelected && prevSelected._id === updatedTicket._id
          ? updatedTicket
          : prevSelected
      );
    });

    // Cleanup
    return () => {
      socket.disconnect();
      console.log("WebSocket cleanup for User Dashboard");
    };
  }, []);

  const fetchTickets = async () => {
    try {
      console.log("Fetching tickets...");
      const token = localStorage.getItem("token");
      const response = await axios.get(endpoints.GET_TICKETS, {
        headers: { Authorization: `Bearer ${token}` },
      });
      const uniqueTickets = response.data.filter(
        (ticket, index, self) =>
          index === self.findIndex((t) => t._id === ticket._id)
      );
      setTickets(uniqueTickets);
    } catch (error) {
      console.error("Error fetching tickets:", error.message);
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
        <h3 className="headerCenter">User Dashboard</h3>
        <button className="logoutButton" onClick={handleLogout}>
          Logout
        </button>
      </header>
      <hr className="divider" />

      {/* Create Ticket Section */}
      <div className="createTicketSection">
        <CreateTicket socket={socket} />
      </div>
      <hr className="divider" />

      {/* Tickets and Details */}
      <div className="dashboardContainer">
        {/* My Tickets Section */}
        <div className="ticketsList">
          <h3 className="sectionHeader">My Tickets</h3>
          {tickets.length > 0 ? (
            <ul className="ticketList">
              {tickets.map((ticket, index) => (
                <React.Fragment key={ticket._id}>
                  <li className="ticketItem">
                    <strong>Title:</strong> {ticket.title} <br />
                    <strong>Status:</strong> {ticket.status} <br />
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
          ) : (
            <p>No tickets found.</p>
          )}
        </div>

        {/* Ticket Details Section */}
        <div className="ticketDetails">
          <h3 className="sectionHeader">Ticket Details</h3>
          {selectedTicket ? (
            <>
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
            </>
          ) : (
            <p>Select a ticket to view details</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default UserDashboard;
