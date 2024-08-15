import express from "express";
import bodyParser from "body-parser";
import path from "path";

let app = express();

let logger = (req, res, next) => {
  console.log("Logging...");
  next();
};

app.use(logger);

app.get("/", (req, res) => {
  res.send("Hello World");
});

app.listen(3000, () => {
  console.log("Server Started on 3000...");
});
