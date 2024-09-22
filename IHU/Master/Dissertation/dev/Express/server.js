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
// Serve the static files from "public" directory
app.use(express.static(path.join(__dirname, "public")));

// Handle JSON Body Data
app.use(express.json());

// Functions
const getUsers = () => {
  const data = fs.readFileSync(path.join(__dirname, "users.json"), "utf-8");
  return JSON.parse(data);
};

const getUserById = (id) => {
  const users = getUsers();
  return users.find((user) => user.id === parseInt(id));
};

const deleteUserById = (id) => {
  let users = getUsers();
  const userIndex = users.findIndex((user) => user.id === parseInt(id));

  if (userIndex != -1) {
    users.splice(userIndex, 1);
    fs.writeFileSync(
      path.join(__dirname, "users.json"),
      JSON.stringify(users, null, 2)
    );
    return true;
  } else {
    res.status(404).json({
      message: "User not found",
    });
    return false;
  }
};

// Routes

// Create User
app.post("/user", (req, res) => {});

// Get All Users
app.get("/users", (req, res) => {
  const users = getUsers();
  res.json(users);
});

// Get a single User
app.get("/user/:id", (req, res) => {
  const { id } = req.params;
  const user = getUserById(id);

  if (user) {
    res.json(user);
  } else {
    res.status(404).json({
      message: "User not found",
    });
  }
});

// Delete User
app.delete("/users/:id", (req, res) => {
  const { id } = req.params;
  const userDeleted = deleteUserById(id);
  if (userDeleted) {
    res.status(200).json({
      message: "User deleted successfully",
    });
  } else {
    res.status(404).json({
      message: "User not found.",
    });
  }
});

// Run Server
const PORT = process.env.PORT || 8080;

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
