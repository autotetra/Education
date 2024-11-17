import jwt from "jsonwebtoken";

const authenticateJWT = (req, res, next) => {
  // Extract token from the authorization header
  const token = req.headers.authorization?.split(" ")[1];
  if (!token) {
    // If no token, deny access
    return res.status(401).json({ message: "Unauthorized: Token is missing" });
  }

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET); // Verify the token
    req.user = decoded; // Attach decoded payload (e.g., user ID) to req.user
    next(); // Continue next middleware
  } catch (error) {
    // If token is invalid, deny access
    return res.status(403).json({ message: "Forbidden: Invalid token" });
  }
};

export default authenticateJWT;
