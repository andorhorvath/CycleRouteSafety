-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2018. Máj 03. 17:50
-- Kiszolgáló verziója: 10.1.30-MariaDB
-- PHP verzió: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `cycleroutes`
--
CREATE DATABASE IF NOT EXISTS `cycleroutes` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;
USE `cycleroutes`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `markers`
--

CREATE TABLE `markers` (
  `markerID` int(11) NOT NULL,
  `description` text COLLATE utf8_hungarian_ci NOT NULL,
  `markerType` text COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `markers`
--

INSERT INTO `markers` (`markerID`, `description`, `markerType`) VALUES
(1, '1', 'res/from.png'),
(2, '2', 'res/to.png'),
(3, '3', 'res/Paris.png'),
(36, 'gzsgfj', 'res/dots.png'),
(37, 'dasfasf', 'res/dots.png');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `pois`
--

CREATE TABLE `pois` (
  `poiID` int(11) NOT NULL,
  `lat` double DEFAULT NULL,
  `lng` double NOT NULL,
  `markerID` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `pois`
--

INSERT INTO `pois` (`poiID`, `lat`, `lng`, `markerID`) VALUES
(1, 47.47684, 19.079819, 3),
(2, 47.522997, 18.999825, 3),
(3, 47.491225, 19.201012, 3),
(4, 47.506881, 19.13063, 3),
(5, 47.523461, 19.152946, 3),
(6, 47.467905, 19.014416, 3),
(7, 47.502359, 19.072094, 3),
(8, 47.476028, 19.120331, 3),
(9, 47.463379, 19.161873, 3),
(10, 47.458737, 19.183159, 3),
(11, 47.534009, 19.032955, 3);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `routes`
--

CREATE TABLE `routes` (
  `routeID` int(11) NOT NULL,
  `routeName` text COLLATE utf8_hungarian_ci NOT NULL,
  `author` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `startPoint` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `finishPoint` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `routeLength` int(11) NOT NULL,
  `lastUpdateTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `routes`
--

INSERT INTO `routes` (`routeID`, `routeName`, `author`, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`) VALUES
(22, '<From: Budapest, Pázmány Péter stny. 1a, 1117 Magyarország> <To: Budapest, Egressy út 178, 1141 Magyarország>', 'ahorvath', 'Budapest, Pázmány Péter stny. 1a, 1117 Magyarország', 'Budapest, Egressy út 178, 1141 Magyarország', 1, '2018-04-29 08:15:08');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `markers`
--
ALTER TABLE `markers`
  ADD UNIQUE KEY `markerID_2` (`markerID`),
  ADD KEY `markerID` (`markerID`);

--
-- A tábla indexei `pois`
--
ALTER TABLE `pois`
  ADD PRIMARY KEY (`poiID`);

--
-- A tábla indexei `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`routeID`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `markers`
--
ALTER TABLE `markers`
  MODIFY `markerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT a táblához `pois`
--
ALTER TABLE `pois`
  MODIFY `poiID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT a táblához `routes`
--
ALTER TABLE `routes`
  MODIFY `routeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
