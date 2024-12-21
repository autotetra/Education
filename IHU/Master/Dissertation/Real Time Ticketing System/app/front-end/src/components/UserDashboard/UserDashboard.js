import React, { useState, useEffect } from "react";
import CreateTicket from "../../Shared/CreateTicket";
import endpoints from "../../api/endpoints";
import axios from "axios";
import io from "socket.io-client";

function UserDashboard() {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [username, setUsername] = useState("");

  useEffect(() => {
    // Get username name
    const username = localStorage.getItem("username");
    setUsername(username);

    // Initialize WebSocket connection
    const socket = io("http://localhost:8000");

    // Log connection ID when WebSocket connects
    socket.on("connect", () => {
      console.log("WebSocket connected with ID:", socket.id);
    });

    // Listen for the "statusUpdated" event
    socket.on("statusUpdated", (updatedTicket) => {
      if (!updatedTicket) {
        console.error("No data received for statusUpdated event");
        return;
      }

      // Update tickets in state
      setTickets((prevTickets) =>
        prevTickets.map((ticket) =>
          ticket._id === updatedTicket._id ? updatedTicket : ticket
        )
      );
    });

    // Fetch tickets on component mount
    fetchTickets();

    // Cleanup function to disconnect the socket
    return () => {
      socket.disconnect();
      console.log("WebSocket disconnected");
    };
  }, []); // Runs only once on component mount

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/";
  };

  const fetchTickets = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(endpoints.GET_TICKETS, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setTickets(response.data);
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
        <CreateTicket />
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
