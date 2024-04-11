// Import the 'http' module to create an HTTP server
const http = require("http");

// Create an HTTP server
// The callback function handles incoming requests
const server = http.createServer((req, res) => {
  // Handle requests based on the URL
  if (req.url == "/") {
    // If the URL is the root '/', send a welcome message
    res.writeHead(200, { "Content-Type": "text/plain" });
    res.end("Welcome to the Home Page!");
  } else if (req.url == "/about") {
    // If the URL is '/about', send information about the page
    res.writeHead(200, { "Content-Type": "text/plain" });
    res.end("Learn more about us on the About Page!");
  } else {
    // For all other URLs, send a 404 Not Found response
    res.writeHead(404, { "Content-Type": "text/plain" });
    res.end("Not Found");
  }
});

// Define the port number on which the server will listen
const PORT = 3000;

// Start the server and listen on the specified port
server.listen(PORT, () => {
  // This callback function is executed once the server starts successfully
  console.log(`Server started successfully on port ${PORT}`);
});
