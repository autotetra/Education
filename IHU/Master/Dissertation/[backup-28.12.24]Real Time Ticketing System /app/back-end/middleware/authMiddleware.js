import jwt from "jsonwebtoken";
import User from "../models/accountModel.js";

const authenticateJWT = async (req, res, next) => {
  // Extract token from the authorization header
  const token = req.headers.authorization?.split(" ")[1];
  if (!token) {
    // If no token, deny access
    return res.status(401).json({ message: "Unauthorized: Token is missing" });
  }

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET); // Verify the token
    const user = await User.findById(decoded.id); // Fetch User details from database

    if (!user) {
      return res.status(404).json({ message: "User not found" });
    }

    //Attach all necessary user details to req.user
    req.user = {
      id: user._id,
      email: user.email,
      role: user.role,
      department: user.department,
    };
    next(); // Continue next middleware
  } catch (error) {
    // If token is invalid, deny access
    return res.status(403).json({ message: "Forbidden: Invalid token" });
  }
};

export default authenticateJWT;
