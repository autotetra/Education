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
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_subtitle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_category_id` int(11) NOT NULL,
  `product_customer_id` int(11) NOT NULL,
  `product_price` decimal(10,2) NOT NULL,
  `product_order` int(11) NOT NULL,
  `product_active` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `product_subtitle`, `product_category_id`, `product_customer_id`, `product_price`, `product_order`, `product_active`) VALUES
(1, 'Laptop', 'Powerful laptop for work and gaming', 1, 123, 800.00, 1, 1),
(2, 'Smartphone', 'Latest smartphone model', 1, 123, 400.00, 2, 1),
(3, 'T-shirt', 'Casual cotton T-shirt', 2, 456, 20.00, 3, 1),
(4, 'Jeans', 'Classic denim jeans', 2, 456, 50.00, 4, 1),
(5, 'Python Programming', 'Python programming book', 3, 789, 30.00, 5, 1),
(6, 'The Great Gatsby', 'Classic novel by F. Scott Fitzgerald', 3, 789, 15.00, 6, 1),
(7, 'Blender', 'High-performance kitchen blender', 4, 101, 60.00, 7, 1),
(8, 'Bed Sheets', 'Soft and comfortable bed sheets', 4, 101, 25.00, 8, 1),
(9, 'Soccer Ball', 'Durable soccer ball', 5, 112, 15.00, 9, 1),
(10, 'Running Shoes', 'Running shoes for active lifestyle', 5, 112, 80.00, 10, 1),
(11, 'Sofa', 'Comfortable living room sofa', 6, 789, 500.00, 11, 1),
(12, 'Action Figure', 'Collectible action figure', 7, 101, 15.00, 12, 1),
(13, 'Lipstick', 'Long-lasting matte lipstick', 8, 112, 20.00, 13, 1),
(14, 'Diamond Ring', 'Elegant diamond ring', 9, 123, 1000.00, 14, 1),
(15, 'Camping Tent', 'Spacious camping tent for outdoor adventures', 10, 456, 120.00, 15, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
