import express from "express";
import dotenv from "dotenv";
import path from "path";
import { fileURLToPath } from "url";
import users from "./data.js";

dotenv.config();

// Setup __dirname for ES Modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();

// Middlewares
//--------------------
// Serve the static files from "public" directory
app.use(express.static(path.join(__dirname, "public")));

// Handle JSON Body Data
app.use(express.json());

// Routes

// POST
//--------------------
// Create User
app.get("/user", (req, res) => {
  res.send(req.query);
});

// GET
//--------------------
// Get All Users
app.get("/users", (req, res) => {
  res.json(users);
});

// Run Server
const PORT = process.env.PORT || 8080;

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
