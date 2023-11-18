-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2023 at 12:17 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `geersdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` varchar(15) NOT NULL,
  `size` varchar(15) NOT NULL,
  `price` float(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `size`, `price`) VALUES
('light', 'large', 90000.00),
('light', 'medium', 70000.00),
('light', 'small', 50000.00),
('power', 'large', 100000.00),
('power', 'medium', 80000.00),
('power', 'small', 60000.00),
('sound', 'large', 80000.00),
('sound', 'medium', 60000.00),
('sound', 'small', 40000.00),
('stage', 'large', 70000.00),
('stage', 'medium', 50000.00),
('stage', 'small', 30000.00);

-- --------------------------------------------------------

--
-- Table structure for table `company_inbox`
--

CREATE TABLE `company_inbox` (
  `message_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `sender` varchar(15) NOT NULL,
  `subject` varchar(15) NOT NULL,
  `message` varchar(100) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_inbox`
--

INSERT INTO `company_inbox` (`message_id`, `date`, `sender`, `subject`, `message`, `status`) VALUES
(1, '2023-11-15', '1000', 'Thank you', 'Ok thanks. I will come to the store tomorrow to finalise.', 'unread');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` varchar(15) NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `balance` float(10,2) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `first_name`, `last_name`, `telephone`, `balance`, `password`) VALUES
('1000', 'kimarley', 'julal', '8765432109', 50000.00, 'kj123'),
('1002', 'Jaiden', 'Julal', '8761234567', 100000.00, 'jj123');

-- --------------------------------------------------------

--
-- Table structure for table `customer_inbox`
--

CREATE TABLE `customer_inbox` (
  `message_id` int(11) NOT NULL,
  `sender` varchar(15) NOT NULL,
  `customer` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `subject` varchar(50) NOT NULL,
  `message` varchar(100) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_inbox`
--

INSERT INTO `customer_inbox` (`message_id`, `sender`, `customer`, `date`, `subject`, `message`, `status`) VALUES
(1, 'emp01', '1000', '2023-11-17', 'Hey', 'Hello', 'seen');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` varchar(15) NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `email` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `first_name`, `last_name`, `email`, `password`) VALUES
('EMP01', 'Mary', 'Jane', 'mj@geer.com', 'mj123');

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `equipment_id` varchar(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `category` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`equipment_id`, `name`, `category`, `status`) VALUES
('LGT01', 'Lighting Package', 'light', 'available'),
('POW01', 'Power Package', 'power', 'available'),
('SOU01', 'Sound Package', 'sound', 'available'),
('STG01', 'Staging Package', 'stage', 'available');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `customer` varchar(15) NOT NULL,
  `amount` float(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `date`, `customer`, `amount`) VALUES
(1, '2023-11-15', '1000', 50000.00);

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `customer` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `start_date` date NOT NULL,
  `duration` varchar(15) NOT NULL,
  `cost` float(10,2) NOT NULL,
  `employee` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`customer`, `date`, `start_date`, `duration`, `cost`, `employee`) VALUES
('1000', '2023-11-15', '2023-11-15', '2', 50000.00, 'EMP01'),
('1000', '2023-11-17', '2023-11-17', '2', 100000.00, 'EMP01');

-- --------------------------------------------------------

--
-- Table structure for table `rented_equipments`
--

CREATE TABLE `rented_equipments` (
  `customer` varchar(15) NOT NULL,
  `date` date NOT NULL,
  `equipment` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rented_equipments`
--

INSERT INTO `rented_equipments` (`customer`, `date`, `equipment`) VALUES
('1000', '2023-11-15', 'LGT01'),
('1000', '2023-11-15', 'POW01'),
('1000', '2023-11-17', 'STG01');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`,`size`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `company_inbox`
--
ALTER TABLE `company_inbox`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `sender` (`sender`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `customer_inbox`
--
ALTER TABLE `customer_inbox`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `sender` (`sender`),
  ADD KEY `customer` (`customer`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`equipment_id`),
  ADD KEY `category` (`category`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `customer` (`customer`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`customer`,`date`),
  ADD KEY `customer` (`customer`),
  ADD KEY `employee` (`employee`);

--
-- Indexes for table `rented_equipments`
--
ALTER TABLE `rented_equipments`
  ADD KEY `customer` (`customer`,`equipment`),
  ADD KEY `fk_equipment_rented` (`equipment`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company_inbox`
--
ALTER TABLE `company_inbox`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customer_inbox`
--
ALTER TABLE `customer_inbox`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `company_inbox`
--
ALTER TABLE `company_inbox`
  ADD CONSTRAINT `fk_emp_inbox_sender` FOREIGN KEY (`sender`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `customer_inbox`
--
ALTER TABLE `customer_inbox`
  ADD CONSTRAINT `fk_msg_customer` FOREIGN KEY (`customer`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `fk_msg_sender` FOREIGN KEY (`sender`) REFERENCES `employee` (`employee_id`);

--
-- Constraints for table `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`category`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_customer_payment` FOREIGN KEY (`customer`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `fk_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `fk_rental_costomer` FOREIGN KEY (`customer`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `rented_equipments`
--
ALTER TABLE `rented_equipments`
  ADD CONSTRAINT `fk_customer` FOREIGN KEY (`customer`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `fk_equipment_rented` FOREIGN KEY (`equipment`) REFERENCES `equipment` (`equipment_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
