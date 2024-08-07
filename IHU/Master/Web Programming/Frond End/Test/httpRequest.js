const http = require("http");

const options = {
  hostname: "jsonplaceholder.typicode.com",
  port: 80,
  path: "/todos/1",
  method: "GET",
};

const req = http.request(options, (res) => {
  let data = "";

  res.on("data", (chunk) => {
    data += chunk;
  });

  res.on("end", () => {
    console.log(JSON.parse(data));
  });
});

req.on("error", (error) => {
  console.error("Request error:", error.message);
});

req.end();
