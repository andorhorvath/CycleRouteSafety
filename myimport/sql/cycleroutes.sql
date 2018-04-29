-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2018. Ápr 29. 13:57
-- Kiszolgáló verziója: 10.1.31-MariaDB
-- PHP verzió: 7.2.3

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
(1, 'alma', 'res/Paris.png ');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `pois`
--

CREATE TABLE `pois` (
  `poiID` int(11) NOT NULL,
  `routeID` int(11) NOT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `poiType` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `isAlreadyPlanned` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `pois`
--

INSERT INTO `pois` (`poiID`, `routeID`, `x`, `y`, `poiType`, `isAlreadyPlanned`) VALUES
(1, 3, NULL, NULL, 'finish', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `routes`
--

CREATE TABLE `routes` (
  `routeID` int(11) NOT NULL,
  `routeName` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `author` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `startPoint` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `finishPoint` varchar(100) COLLATE utf8_hungarian_ci NOT NULL,
  `routeLength` int(11) NOT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  `plannedRoute` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `routes`
--

INSERT INTO `routes` (`routeID`, `routeName`, `author`, `startPoint`, `finishPoint`, `routeLength`, `lastUpdateTime`, `plannedRoute`) VALUES
(1, 'Pálma utca - Haller Gardens', 'Andor', 'Budapest, Pálma utca 6, 1146', 'Haller Gardens - Budapest, Soroksári út 34, 1095', 7, '2018-02-25 15:13:17', 0),
(2, '[From: [47.474047,19.062583]]', 'ahorvath', '47.474047', '19.062583', 1, '2018-04-19 09:01:15', 1),
(3, 'Budapest, Pázmány Péter stny. 1a, 1117 Magyarország', 'ahorvath', '47.474047', '19.062583', 1, '2018-04-19 09:12:15', 1),
(5, 'Kiindulás: Cinkotai út 68, Budapest, 1141 Cél: Bazsarózsa u. 94o, Budapest, 1141 – Google Térkép', 'author', 'startPoint', 'finishPoint', 1, '2018-04-08 12:03:19', 1),
(6, '[From: [47.519436,19.12635]]', 'ahorvath', '47.519436', '19.12635', 1, '2018-04-19 08:44:53', 1),
(8, 'Kiindulás: 48.1091584, 19.8074368 Cél: Pap Károly u. 22c, Budapest, 1139 – Google Térkép', 'author', 'startPoint', 'finishPoint', 1, '2018-04-12 11:13:21', 1),
(9, 'Kiindulás: 48.1091584, 19.8074368 Cél: Országbíró u. 2, Budapest, 1139 – Google Térkép', 'author', 'startPoint', 'finishPoint', 1, '2018-04-12 11:14:31', 1),
(10, 'Kiindulás: Petneházy u. 27, Budapest, 1139 Cél: Szegedi út 11, Budapest, 1139 – Google Térkép', 'author', 'startPoint', 'finishPoint', 1, '2018-04-12 11:55:38', 1),
(13, 'Kiindulás: Petneházy u. 12, Budapest, 1139 Cél: Szegedi út 2, Budapest, 1139 – Google Térkép', 'author', 'startPoint', 'finishPoint', 1, '2018-04-12 11:56:41', 1);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `markers`
--
ALTER TABLE `markers`
  ADD PRIMARY KEY (`markerID`);

--
-- A tábla indexei `pois`
--
ALTER TABLE `pois`
  ADD PRIMARY KEY (`poiID`);

--
-- A tábla indexei `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`routeID`),
  ADD UNIQUE KEY `routeName` (`routeName`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `pois`
--
ALTER TABLE `pois`
  MODIFY `poiID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT a táblához `routes`
--
ALTER TABLE `routes`
  MODIFY `routeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
