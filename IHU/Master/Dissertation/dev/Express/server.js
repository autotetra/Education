import express from "express";
import path from "path";
import { fileURLToPath } from "url";

// Setup __dirname for ES Modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();

// Serve the static files from "public" directory
app.use(express.static(path.join(__dirname, "public")));

// Basic route to test if the server is running
app.get("/test", (req, res) => {
  res.send("Express Server is running!");
});

const PORT = process.env.PORT || 8000;

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
