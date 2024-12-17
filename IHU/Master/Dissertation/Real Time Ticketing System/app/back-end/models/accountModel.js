import mongoose from "mongoose";
import bcrypt from "bcryptjs";

// Define Account Schema
const accountSchema = new mongoose.Schema({
  username: {
    type: String,
    required: true,
    trim: true,
  },
  email: {
    type: String,
    required: true,
    unique: true,
    lowercase: true,
    trim: true,
  },
  password: {
    type: String,
    required: true,
  },
  role: {
    type: String,
    enum: ["user", "agent", "admin"],
    default: "user",
  },
  department: {
    type: String,
    enum: ["Technical Support", "General Inquiry", "Sales", "N/A"],
    required: function () {
      return this.role === "agent";
    },
    default: "N/A",
  },
});

// Hash password before saving the account
accountSchema.pre("save", async function (next) {
  const account = this;
  if (account.isModified("password")) {
    const salt = await bcrypt.genSalt(10);
    account.password = await bcrypt.hash(account.password, salt);
  }
  next();
});

const Account = mongoose.model("Account", accountSchema, "Accounts");
export default Account;
