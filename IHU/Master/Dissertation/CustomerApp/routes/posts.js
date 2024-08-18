import express from "express";
import {
  createPost,
  deletePost,
  getPost,
  getPosts,
  updatePost,
} from "../controllers/postController.js";

const router = express.Router();
export default router;

//Get all post
router.get("/", getPosts);

//Get single post
router.get("/:id", getPost);

//Create new post
router.post("/", createPost);

//Update post
router.put("/:id", updatePost);

//Delete post
router.delete("/:id", deletePost);
