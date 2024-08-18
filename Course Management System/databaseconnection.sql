-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 12, 2024 at 01:48 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `databaseconnection`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

CREATE TABLE `assignment` (
  `Name` varchar(35) NOT NULL,
  `Course` varchar(30) NOT NULL,
  `Module` varchar(35) NOT NULL,
  `Level` int(10) NOT NULL,
  `Marks` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`Name`, `Course`, `Module`, `Level`, `Marks`) VALUES
('Ram', 'BIT', 'OOPs', 5, 70),
('Niroj', 'BBM', 'Python', 4, 100),
('tarik', 'BBA', 'accounting', 5, 100),
('Hemanta', 'BHM', '', 0, 100);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `CourseID` varchar(35) NOT NULL,
  `CourseName` varchar(30) NOT NULL,
  `Batch` int(35) NOT NULL,
  `Year` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`CourseID`, `CourseName`, `Batch`, `Year`) VALUES
('1', ' BBA', 2022, 3),
('2', 'BBM', 2021, 4),
('3', 'BIT', 2022, 4);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `Topic` varchar(35) NOT NULL,
  `Course` varchar(30) NOT NULL,
  `Module` varchar(35) NOT NULL,
  `Level` int(10) NOT NULL,
  `Marks` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`Topic`, `Course`, `Module`, `Level`, `Marks`) VALUES
('classification', 'BBA', 'AI', 5, 100),
('regression', 'BBM', 'OOPs', 4, 75),
('nought and cross', 'BIT', 'Python', 4, 80);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `StudentID` varchar(35) NOT NULL,
  `StudentName` varchar(30) NOT NULL,
  `Email` varchar(35) NOT NULL,
  `Phone` int(10) NOT NULL,
  `Course` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`StudentID`, `StudentName`, `Email`, `Phone`, `Course`) VALUES
('NP03CS4A220247', 'Silash Chaudhary', 'NP03CS4A220247@heraldcollege.edu.np', 2147483647, 'BIT'),
('2330947', 'Karan Rai', 'Karan@gmail.com', 2147483647, 'BBM'),
('2334556', 'Abhi Shah', 'Abhi@outlook.com', 985455562, 'BBA');

-- --------------------------------------------------------

--
-- Table structure for table `tutors`
--

CREATE TABLE `tutors` (
  `ID` int(35) DEFAULT NULL,
  `Name` varchar(30) NOT NULL,
  `Email` varchar(35) NOT NULL,
  `Phone_no` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tutors`
--

INSERT INTO `tutors` (`ID`, `Name`, `Email`, `Phone_no`) VALUES
(1, 'Krishna Rai', 'Krishna@gmail.com', 2147483647),
(3, 'Anil Khadha', 'anil@gamil.com', 2147483647),
(2, 'RAm Bhadaur', 'ram@gmail', 98544552);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `email`, `password`, `role`) VALUES
('as', 'as', 'as', 'Student'),
('as', 'as', 'as', 'Teacher'),
('ad', 'ad', 'ad', 'Admin'),
('a', 'a', 'a', 'Student');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
