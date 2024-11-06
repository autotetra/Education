import bcrypt from "bcryptjs";
import jwt from "jsonwebtoken";
import User from "../models/userModel.js";

// Helper function to generate JWT token
const generateToken = (user) => {
  return jwt.sign({ id: user._id, email: user.email }, process.env.JWT_SECRET, {
    expiresIn: "1h",
  });
};

// Helper function to compare passwords
const comparePasswords = async (candidatePassword, userPassword) => {
  return await bcrypt.compare(candidatePassword, userPassword);
};

// Registration controller function
const registerUser = async (req, res) => {
  const { username, email, password } = req.body;

  try {
    // Check if the user exists
    const existingUser = await User.findOne({ email });
    if (existingUser) {
      return res.status(400).json({ message: "User already exists" });
    }

    // Create a new user instance
    const newUser = new User({
      username,
      email,
      password, // Password is hashed in user model
    });

    // Save user to the database
    const savedUser = await newUser.save();
    console.log("User saved to database");

    // Send the response with the user data only
    res.status(201).json({ user: savedUser });
  } catch (error) {
    console.error(error);
    res
      .status(500)
      .json({ message: "Something went wrong during registration" });
  }
};

// Login controller function
const loginUser = async (req, res) => {
  const { email, password } = req.body;
  try {
    // Check if the user exists
    const existingUser = await User.findOne({ email });
    if (!existingUser) {
      return res.status(400).json({ message: "Invalid credentials" });
    }

    // Compare passwords using the helper function
    const isMatch = await comparePasswords(password, existingUser.password);
    if (!isMatch) {
      return res.status(400).json({ message: "Incorrect Password" });
    }

    // Generate a JWT token using the helper function
    const token = generateToken(existingUser);

    // Send the response with the user and the token
    res.status(200).json({ user: existingUser, token });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Something went wrong during login" });
  }
};

// Export the functions
export { registerUser, loginUser };
