-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 30, 2023 at 10:22 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `OrderAppDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(200) NOT NULL,
  `category_customer_id` int(11) NOT NULL,
  `category_order` int(11) NOT NULL,
  `category_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_active` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`, `category_customer_id`, `category_order`, `category_image`, `category_active`) VALUES
(1, 'Electronics', 123, 1, 'electronics_image.jpg', 1),
(2, 'Clothing', 456, 2, 'clothing_image.jpg', 1),
(3, 'Books', 789, 3, 'books_image.jpg', 1),
(4, 'Home & Kitchen', 101, 4, 'home_kitchen_image.jpg', 1),
(5, 'Sports', 112, 5, 'sports_image.jpg', 1),
(6, 'Furniture', 789, 6, 'furniture_image.jpg', 1),
(7, 'Toys', 101, 7, 'toys_image.jpg', 1),
(8, 'Beauty', 112, 8, 'beauty_image.jpg', 1),
(9, 'Jewelry', 123, 9, 'jewelry_image.jpg', 1),
(10, 'Outdoor', 456, 10, 'outdoor_image.jpg', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
