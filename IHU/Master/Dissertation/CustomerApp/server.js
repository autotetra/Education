import express from "express";
import path from "path";
import { fileURLToPath } from "url";
import posts from "./routes/posts.js";

const port = process.env.PORT || 8000;
const app = express();

//Body parses middleware
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

//setup static folder
//app.use(express.static(path.join(__dirname, "public")));

app.use("/api/posts", posts);

app.listen(port, () => console.log(`Server is running on port ${port}`));
