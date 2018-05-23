-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2018 at 01:32 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cycleroutes`
--

-- --------------------------------------------------------

--
-- Table structure for table `markers`
--

CREATE TABLE `markers` (
  `markerID` int(11) NOT NULL,
  `description` text COLLATE utf8_hungarian_ci NOT NULL,
  `markerType` text COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `markers`
--

INSERT INTO `markers` (`markerID`, `description`, `markerType`) VALUES
(1, 'üvegszilánkok', 'res/brokenglass_bw.png'),
(2, 'útszűkület, keskeny kerékpáros infra', 'res/narrow_road.png'),
(3, 'gyalogosok a kerékpárúton', 'res/pedestrians.png'),
(4, 'kerékpáros út lezárva, kerülő szükséges', 'res/closed_road.png'),
(5, 'elsőbbségadás kötelező a többi jármű részére', 'res/look_around.png'),
(86, 'bringaszerviz', 'res/repair.png');

-- --------------------------------------------------------

--
-- Table structure for table `pois`
--

CREATE TABLE `pois` (
  `poiID` int(11) NOT NULL,
  `lat` double DEFAULT NULL,
  `lng` double NOT NULL,
  `markerID` int(50) NOT NULL,
  `placeDescription` varchar(256) CHARACTER SET utf16 COLLATE utf16_hungarian_ci NOT NULL DEFAULT 'testDefault'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `pois`
--

INSERT INTO `pois` (`poiID`, `lat`, `lng`, `markerID`, `placeDescription`) VALUES
(1, 47.47684, 19.079819, 3, 'testDefault'),
(2, 47.522997, 18.999825, 3, 'testDefault'),
(3, 47.491225, 19.201012, 3, 'testDefault'),
(4, 47.506881, 19.13063, 3, 'testDefault'),
(5, 47.523461, 19.152946, 3, 'testDefault'),
(6, 47.467905, 19.014416, 3, 'testDefault'),
(7, 47.476028, 19.120331, 3, 'testDefault'),
(8, 47.463379, 19.161873, 3, 'testDefault'),
(9, 47.458737, 19.183159, 3, 'testDefault'),
(10, 47.534009, 19.032955, 3, 'testDefault'),
(11, 47.497952, 19.112434, 2, 'testDefault'),
(12, 47.482525, 19.08514, 2, 'testDefault'),
(13, 47.490297, 19.104881, 2, 'testDefault'),
(14, 47.543627, 18.997765, 1, 'testDefault'),
(15, 47.471619, 19.09853, 1, 'testDefault'),
(16, 47.533197, 18.881721, 3, 'testDefault'),
(17, 47.524852, 19.028664, 3, 'testDefault'),
(18, 47.585326, 19.041367, 3, 'testDefault'),
(19, 47.602459, 18.950043, 3, 'testDefault'),
(20, 47.566797, 18.996048, 2, 'testDefault'),
(21, 47.561006, 19.052696, 2, 'testDefault'),
(22, 47.407412, 19.094925, 3, 'testDefault'),
(23, 47.445968, 19.069519, 3, 'testDefault'),
(24, 47.50549, 19.101791, 38, 'testDefault'),
(25, 47.489485, 19.05304, 38, 'testDefault'),
(26, 47.488325, 19.009953, 38, 'testDefault'),
(27, 47.556372, 18.984203, 38, 'testDefault'),
(28, 47.517954, 19.086514, 3, 'testDefault'),
(29, 47.518483, 19.085773, 37, 'testDefault'),
(30, 47.519483, 19.083198, 36, 'testDefault'),
(31, 47.519114, 19.081289, 36, 'testDefault'),
(32, 47.518889, 19.080774, 36, 'testDefault'),
(33, 47.515454, 19.077265, 36, 'testDefault'),
(34, 47.519889, 19.089389, 36, 'testDefault'),
(35, 47.52007, 19.089786, 36, 'testDefault'),
(36, 47.514679, 19.076664, 4, 'testDefault'),
(37, 47.515172, 19.076149, 4, 'testDefault'),
(38, 47.519534, 19.08381, 4, 'testDefault'),
(39, 47.519345, 19.082737, 4, 'testDefault'),
(40, 47.51865, 19.078939, 4, 'testDefault'),
(41, 47.518447, 19.085677, 5, 'testDefault'),
(42, 47.512273, 19.073682, 5, 'testDefault'),
(43, 47.508374, 19.067845, 5, 'testDefault'),
(44, 47.507853, 19.067008, 5, 'testDefault'),
(45, 47.506563, 19.065142, 5, 'testDefault'),
(46, 47.51123, 19.097586, 86, 'testDefault'),
(47, 47.508911, 19.090805, 86, 'testDefault'),
(48, 47.490935, 19.049177, 86, 'testDefault'),
(49, 47.493835, 19.052267, 86, 'testDefault'),
(50, 47.412291, 18.915024, 86, 'testDefault'),
(51, 47.461406, 19.176979, 86, 'testDefault'),
(52, 47.500271, 19.035015, 86, 'testDefault'),
(53, 47.502475, 19.062824, 86, 'testDefault'),
(54, 47.527634, 19.095268, 86, 'testDefault'),
(55, 47.437718, 19.111292, 4, 'SzentImreHerceg lezarva');

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE `routes` (
  `routeID` int(11) NOT NULL,
  `routeName` text COLLATE utf8_hungarian_ci NOT NULL,
  `author` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `startPoint` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `finishPoint` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `routeLength` int(11) NOT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  `rank` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`routeID`, `routeName`, `author`, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`, `rank`) VALUES
(22, 'Nagyszülőktől Kerepesi útig', 'ahorvath', 'Batthyany utca 23', 'Budapest, Kerepesi út 54, 1148 Hungary', 1, '2018-05-08 07:37:53', 0),
(23, 'Állatkerti séta', 'ahorvath', 'Budapesti Allatkert', 'Budapest, Pálma u. 6, 1146 Hungary', 1, '2018-05-09 15:12:05', 0),
(24, 'Gyurihoz', 'ahorvath', 'Budapest, Állatkerti krt. 6-12, 1146 Hungary', 'Budapest, Ilka u. 3a, 1143 Hungary', 1, '2018-05-12 07:45:24', 0),
(25, 'Dobos utca', 'ahorvath', 'Budapest, Dobos u. 2, 1204 Hungary', 'Budapest, Dobos u. 15, 1204 Hungary', 1, '2018-05-15 04:15:33', 18);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `markers`
--
ALTER TABLE `markers`
  ADD UNIQUE KEY `markerID_2` (`markerID`),
  ADD KEY `markerID` (`markerID`);

--
-- Indexes for table `pois`
--
ALTER TABLE `pois`
  ADD PRIMARY KEY (`poiID`);

--
-- Indexes for table `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`routeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `markers`
--
ALTER TABLE `markers`
  MODIFY `markerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT for table `pois`
--
ALTER TABLE `pois`
  MODIFY `poiID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `routes`
--
ALTER TABLE `routes`
  MODIFY `routeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
