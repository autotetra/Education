const http = require("http");

const server = http.createServer((req, res) => {
  res.writeHead(200, { "content-Type": "text/plain" });
  res.end(`Hello, World!\nMethod: ${req.method}\nURL: ${req.url}`);
});

const PORT = 1993;
server.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}/`);
});
