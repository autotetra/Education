// Event example

// import { Logger } from "./logger.js";

// const logger = new Logger();

// //Register a listener
// logger.on("messageLogged", (arg) => {
//   console.log("Listener called", arg);
// });

// logger.log("message");

//End of event example

import http from "http";
const port = 3000;

const server = http.createServer((req, res) => {
  if (req.method === "GET") {
    if (req.url === "/") {
      res.writeHead(200, { "Content-Type": "text/plain" });
      res.write("Hello World");
      res.end();
    } else if (req.url === "/api/courses") {
      res.writeHead(200, { "Content-Type": "application/json" });
      res.write(JSON.stringify([1, 2, 3]));
      res.end();
    } else {
      res.writeHead(404, { "Content-Type": "text/plain" });
      res.write("Not Found");
      res.end();
    }
  } else {
    res.writeHead(405, { "Content-Type": "text/plain" });
    res.write("Method Not Allowed");
    res.end();
  }
});

server.listen(port, (error) => {
  if (error) {
    console.log("Something went wrong", error);
  } else {
    console.log("Server is listening on port " + port);
  }
});
