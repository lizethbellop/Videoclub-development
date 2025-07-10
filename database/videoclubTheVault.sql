CREATE DATABASE thevaultVideoclub;
USE thevaultVideoclub;

CREATE TABLE Film (
    idFilm INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(55) NOT NULL,
    genre VARCHAR(45),
    subgenre VARCHAR(45),
    director VARCHAR(45) NOT NULL,
    premierYear INT NOT NULL,
    existingUnits INT,
    productionCompany VARCHAR(60) NOT NULL,
    type ENUM('VHS', 'DVD') NOT NULL
);

CREATE TABLE Purchase (
    idPurchase INT AUTO_INCREMENT PRIMARY KEY,
    purchaseDate DATE NOT NULL,
    quantity INT NOT NULL,
    supplier VARCHAR(50),
    idFilm INT NOT NULL,
    FOREIGN KEY (idFilm) REFERENCES Film(idFilm)
);

CREATE TABLE Promotion (
    idPromotion INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(70) NOT NULL,
    description VARCHAR(255),
    startDate DATE NOT NULL,
    finishDate DATE NOT NULL
);

CREATE TABLE Promotion_includes_film (
    idFilm INT NOT NULL,
    idPromotion INT NOT NULL,
    PRIMARY KEY (idFilm, idPromotion),
    FOREIGN KEY (idFilm) REFERENCES Film(idFilm),
    FOREIGN KEY (idPromotion) REFERENCES Promotion(idPromotion)
);

CREATE TABLE Client (
    idClient INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    phone VARCHAR(12),
    email VARCHAR(100) NOT NULL,
    birthDate DATE NOT NULL,
    strike INT,
    isAccountActive TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE Employee (
    idEmployee INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    email VARCHAR(100) NOT NULL,
    birthDate DATE NOT NULL,
    role VARCHAR(45) NOT NULL
);

CREATE TABLE Reservation (
    idReservation INT AUTO_INCREMENT PRIMARY KEY,
    applicationDate DATE NOT NULL,
    estimatedRentalDate DATE NOT NULL,
    idFilm INT NOT NULL,
    idClient INT NOT NULL,
    FOREIGN KEY (idFilm) REFERENCES Film(idFilm),
    FOREIGN KEY (idClient) REFERENCES Client(idClient)    
);

CREATE TABLE Event (
    idEvent INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(255),
    type VARCHAR(45)
);

CREATE TABLE Event_includes_film (
    idFilm INT NOT NULL,
    idEvent INT NOT NULL,
    PRIMARY KEY (idFilm, idEvent),
    FOREIGN KEY (idFilm) REFERENCES Film(idFilm),
    FOREIGN KEY (idEvent) REFERENCES Event(idEvent)
);

CREATE TABLE Employee_on_charge_Event (
    idEvent INT NOT NULL,
    idEmployee INT NOT NULL,
    PRIMARY KEY (idEvent, idEmployee),
    FOREIGN KEY (idEvent) REFERENCES Event(idEvent),
    FOREIGN KEY (idEmployee) REFERENCES Employee(idEmployee)
);

CREATE TABLE Rental (
    idRental INT AUTO_INCREMENT PRIMARY KEY,
    rentalDate DATE NOT NULL,
    estimatedReturnDate DATE NOT NULL,
    isReturned TINYINT DEFAULT 0,
    isLost TINYINT,
    fine DECIMAL(5,2),
    idFilm INT NOT NULL,
    idClient INT NOT NULL,
    FOREIGN KEY (idFilm) REFERENCES Film(idFilm),
    FOREIGN KEY (idClient) REFERENCES Client(idClient)
);

-- Inserts

-- Employee
    
INSERT INTO Employee (name, lastName, phone, email, birthDate, role) 
VALUES ('Ashley', 'Bennett', '5551234567', 'ashley.bennett@thevault.com', '1990-05-22', 'Manager');

INSERT INTO Employee (name, lastName, phone, email, birthDate, role) 
VALUES ('Lucas', 'Reed', '5559876543', 'lucas.reed@thevault.com', '1985-11-10', 'Clerk');

-- Clients
    
INSERT INTO Client (name, lastName, phone, email, birthDate, strike, isAccountActive) 
VALUES ('Emma', 'Collins', '5551112233', 'emma.collins@mail.com', '2002-07-15', 0, 1);

INSERT INTO Client (name, lastName, phone, email, birthDate, strike, isAccountActive) 
VALUES ('Noah', 'Anderson', '5554445566', 'noah.anderson@mail.com', '1999-02-27', 0, 1);

-- Films
    
-- Slasher
    
INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, type) 
VALUES ('Friday the 13th', 'Horror', 'Slasher', 'Sean S. Cunningham', 1980, 4, 'Paramount Pictures', 'VHS');

-- Slasher

INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, type) 
VALUES ('A Nightmare on Elm Street', 'Horror', 'Slasher', 'Wes Craven', 1984, 3, 'New Line Cinema', 'VHS');

-- RomCom

INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, type) 
VALUES ('When Harry Met Sally', 'Comedy', 'RomCom', 'Rob Reiner', 1989, 5, 'Columbia Pictures', 'DVD');

-- Classic Monsters

INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, type) 
VALUES ('Frankenstein', 'Horror', 'Classic Monsters', 'James Whale', 1931, 2, 'Universal Pictures', 'VHS');

-- Star Wars

INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, type) 
VALUES ('Star Wars: Episode III – Revenge of the Sith', 'Sci-Fi', 'Space Opera', 'George Lucas', 2005, 6, 'Lucasfilm Ltd.', 'DVD');

-- Triggers
    
DELIMITER //
CREATE TRIGGER trg_update_units_after_rental
AFTER INSERT ON Rental
FOR EACH ROW
BEGIN
UPDATE Film
SET existingUnits = existingUnits - 1
WHERE idFilm = NEW.idFilm;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER trg_update_units_after_return
AFTER UPDATE ON Rental
FOR EACH ROW
BEGIN
IF OLD.isReturned = 0 AND NEW.isReturned = 1 THEN
UPDATE Film
SET existingUnits = existingUnits + 1
WHERE idFilm = NEW.idFilm;
END IF;
END;
//
DELIMITER ;