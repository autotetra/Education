import express from "express";
import dotenv from "dotenv";
import path from "path";
import { fileURLToPath } from "url";
import fs from "fs";

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

// Create User
app.post("/user", (req, res) => {});

// Get All Users
app.get("/users", (req, res) => {});

// Get a single User
app.get("/user/:id", (req, res) => {});

// Run Server
const PORT = process.env.PORT || 8080;

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
