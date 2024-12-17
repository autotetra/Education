import bcrypt from "bcryptjs";
import jwt from "jsonwebtoken";
import Account from "../models/accountModel.js";

// Helper function to generate JWT token
const generateToken = (account) => {
  return jwt.sign(
    { id: account._id, email: account.email },
    process.env.JWT_SECRET,
    {
      expiresIn: "1h",
    }
  );
};

// Helper function to compare passwords
const comparePasswords = async (candidatePassword, accountPassword) => {
  return await bcrypt.compare(candidatePassword, accountPassword);
};

// Registration controller function
const registerAccount = async (req, res) => {
  const { username, email, password, role, department } = req.body;

  try {
    // Check if the account exists
    const existingAccount = await Account.findOne({ email });
    if (existingAccount) {
      return res.status(400).json({ message: "Account already exists" });
    }

    // Create a new account instance
    const newAccount = new Account({
      username,
      email,
      password, // Password is hashed in account model
      role,
      department,
    });

    // Save account to the database
    const savedAccount = await newAccount.save();
    console.log("Account saved to database");

    // Send the response with the account data only
    res.status(201).json({ account: savedAccount });
  } catch (error) {
    console.error(error);
    res
      .status(500)
      .json({ message: "Something went wrong during registration" });
  }
};

// Login controller function
const loginAccount = async (req, res) => {
  const { email, password } = req.body;
  try {
    // Check if the account exists
    const existingAccount = await Account.findOne({ email });
    if (!existingAccount) {
      return res.status(400).json({ message: "Invalid credentials" });
    }

    // Compare passwords using the helper function
    const isMatch = await comparePasswords(password, existingAccount.password);
    if (!isMatch) {
      return res.status(400).json({ message: "Incorrect Password" });
    }

    // Generate a JWT token using the helper function
    const token = generateToken(existingAccount);

    // Send the response with the account and the token
    res.status(200).json({ account: existingAccount, token });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Something went wrong during login" });
  }
};

// Export the functions
export { registerAccount, loginAccount };
