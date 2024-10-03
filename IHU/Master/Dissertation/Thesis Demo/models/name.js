// models/name.js
import mongoose from "mongoose";

const NameSchema = new mongoose.Schema(
  { name: String },
  { collection: "messages" } // Explicitly set the collection name
);

export default mongoose.model("Name", NameSchema);
