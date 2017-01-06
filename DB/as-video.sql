-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 02. Jan 2017 um 18:59
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `as-video`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `fsk`
--

CREATE TABLE `fsk` (
  `FSK_ID` int(11) NOT NULL,
  `Altersklasse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kategorien`
--

CREATE TABLE `kategorien` (
  `KAT_ID` int(11) NOT NULL,
  `Kategoriename` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `leihen`
--

CREATE TABLE `leihen` (
  `Film_ID` int(11) NOT NULL,
  `Kunden_NR` int(11) NOT NULL,
  `Anfangsdatum` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Enddatum` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `medien`
--

CREATE TABLE `medien` (
  `FILM_ID` int(11) NOT NULL,
  `Medium` int(11) NOT NULL,
  `Erscheinungsjahr` year(4) NOT NULL,
  `Titel` text NOT NULL,
  `Kategorie` int(11) NOT NULL,
  `FSK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `medientypen`
--

CREATE TABLE `medientypen` (
  `Medien_ID` int(11) NOT NULL,
  `Bezeichnung` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_kunden`
--

CREATE TABLE `t_kunden` (
  `Kunden_Nr` int(11) NOT NULL,
  `Anrede` text NOT NULL,
  `Vorname` text NOT NULL,
  `Nachname` text NOT NULL,
  `Strasse` text NOT NULL,
  `PLZ` int(5) NOT NULL,
  `Ort` text NOT NULL,
  `Geburtsdatum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabelle mit Kundendaten';

--
-- Daten für Tabelle `t_kunden`
--

INSERT INTO `t_kunden` (`Kunden_Nr`, `Anrede`, `Vorname`, `Nachname`, `Strasse`, `PLZ`, `Ort`, `Geburtsdatum`) VALUES
(1000, 'Frau', 'Test', 'Test', 'Test 122', 12577, 'Berlin', '1966-09-08'),
(1006, 'Herr', 'Neu', 'Neu', 'Neu2', 12455, 'Neu', '1988-08-08'),
(1007, 'Herr', 'Testtt', 'Test', 'Test2', 45887, 'Test', '1987-08-07');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `fsk`
--
ALTER TABLE `fsk`
  ADD PRIMARY KEY (`FSK_ID`);

--
-- Indizes für die Tabelle `kategorien`
--
ALTER TABLE `kategorien`
  ADD PRIMARY KEY (`KAT_ID`);

--
-- Indizes für die Tabelle `leihen`
--
ALTER TABLE `leihen`
  ADD PRIMARY KEY (`Film_ID`,`Kunden_NR`),
  ADD KEY `Kunden` (`Kunden_NR`);

--
-- Indizes für die Tabelle `medien`
--
ALTER TABLE `medien`
  ADD PRIMARY KEY (`FILM_ID`),
  ADD UNIQUE KEY `FSK` (`FSK`),
  ADD UNIQUE KEY `Kategorie` (`Kategorie`),
  ADD UNIQUE KEY `Medium` (`Medium`);

--
-- Indizes für die Tabelle `medientypen`
--
ALTER TABLE `medientypen`
  ADD PRIMARY KEY (`Medien_ID`);

--
-- Indizes für die Tabelle `t_kunden`
--
ALTER TABLE `t_kunden`
  ADD PRIMARY KEY (`Kunden_Nr`),
  ADD UNIQUE KEY `Kunden_Nr` (`Kunden_Nr`),
  ADD UNIQUE KEY `Kunden_Nr_2` (`Kunden_Nr`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `fsk`
--
ALTER TABLE `fsk`
  MODIFY `FSK_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `kategorien`
--
ALTER TABLE `kategorien`
  MODIFY `KAT_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `medien`
--
ALTER TABLE `medien`
  MODIFY `FILM_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `medientypen`
--
ALTER TABLE `medientypen`
  MODIFY `Medien_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `t_kunden`
--
ALTER TABLE `t_kunden`
  MODIFY `Kunden_Nr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1009;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `leihen`
--
ALTER TABLE `leihen`
  ADD CONSTRAINT `Kunden` FOREIGN KEY (`Kunden_NR`) REFERENCES `t_kunden` (`Kunden_Nr`),
  ADD CONSTRAINT `Medien` FOREIGN KEY (`Film_ID`) REFERENCES `medien` (`FILM_ID`);

--
-- Constraints der Tabelle `medien`
--
ALTER TABLE `medien`
  ADD CONSTRAINT `FSK` FOREIGN KEY (`FSK`) REFERENCES `fsk` (`FSK_ID`),
  ADD CONSTRAINT `Kategorie` FOREIGN KEY (`Kategorie`) REFERENCES `kategorien` (`KAT_ID`),
  ADD CONSTRAINT `Medientyp` FOREIGN KEY (`Medium`) REFERENCES `medientypen` (`Medien_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
