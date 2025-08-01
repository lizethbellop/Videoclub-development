CREATE DATABASE thevaultVideoclub;
USE thevaultVideoclub;

CREATE TABLE User (
    idUser INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    profilePicture VARCHAR(255) NULL
);

CREATE TABLE Film (
    idFilm INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(55) NOT NULL,
    genre VARCHAR(45),
    subgenre VARCHAR(45),
    director VARCHAR(45) NOT NULL,
    premierYear INT NOT NULL,
    existingUnits INT,
    productionCompany VARCHAR(60) NOT NULL,
    format VARCHAR(255) NOT NULL,
    imageurl VARCHAR(255) NULL
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
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    phone VARCHAR(12),
    birthDate DATE NOT NULL,
    strike INT,
    isAccountActive TINYINT NOT NULL DEFAULT 1,
    idUser INT NOT NULL,
    FOREIGN KEY (idUser) REFERENCES User(idUser)
);

CREATE TABLE Employee (
    idEmployee INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    birthDate DATE NOT NULL,
    role VARCHAR(45) NOT NULL,
    idUser INT NOT NULL,
    FOREIGN KEY (idUser) REFERENCES User(idUser)
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

-- User

-- Employees

INSERT INTO User (username, password, role, email)
VALUES ('abennett', '$2a$12$mVV52qOqXRBXk48Oomu2d.w5NpU4/qPzBb7Oy4ZW0agzHrdNavCYu', 'employee', 'ashley.bennett@thevault.com');

INSERT INTO User (username, password, role, email)
VALUES ('lreed', '$2a$12$HD1.8jmXC8AGD0/M7Y3RfOtNZH.fg8oBfx.vp.2uyLpK6.ti7rPyS', 'employee', 'lucas.reed@thevault.com');

-- Clients

INSERT INTO User (username, password, role, email)
VALUES ('ecollins', '$2a$12$Tjzd4fl8zCcRKQ0ikHhnc.gJh9eftNGiAaJrCe88BiZw5.xdANv5C', 'client', 'emma.collins@mail.com');

INSERT INTO User (username, password, role, email)
VALUES ('nanderson', '$2a$12$P0Gj5O/3ZVRE5te4dHinOOntKAUZj3CdLXiXRZ7ZJGDcd9CWC/Adq', 'client', 'noah.anderson@mail.com');


-- Employee
    
INSERT INTO Employee (firstName, lastName, phone, birthDate, role, idUser) 
VALUES ('Ashley', 'Bennett', '5551234567', '1990-05-22', 'Manager', 1);

INSERT INTO Employee (firstName, lastName, phone, birthDate, role, idUser) 
VALUES ('Lucas', 'Reed', '5559876543', '1985-11-10', 'Clerk', 2);

-- Clients
    
INSERT INTO Client (firstName, lastName, phone, birthDate, strike, isAccountActive, idUser) 
VALUES ('Emma', 'Collins', '5551112233', '2002-07-15', 0, 1, 3);

INSERT INTO Client (firstName, lastName, phone, birthDate, strike, isAccountActive, idUser) 
VALUES ('Noah', 'Anderson', '5554445566', '1999-02-27', 0, 1, 4);

-- Films
    
-- Slasher
    
INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, format) 
VALUES ('Friday the 13th', 'Horror', 'Slasher', 'Sean S. Cunningham', 1980, 4, 'Paramount Pictures', 'VHS');

-- Slasher

INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, format) 
VALUES ('A Nightmare on Elm Street', 'Horror', 'Slasher', 'Wes Craven', 1984, 3, 'New Line Cinema', 'VHS');

-- RomCom

INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, format) 
VALUES ('When Harry Met Sally', 'Comedy', 'RomCom', 'Rob Reiner', 1989, 5, 'Columbia Pictures', 'DVD');

-- Classic Monsters

INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, format) 
VALUES ('Frankenstein', 'Horror', 'Classic Monsters', 'James Whale', 1931, 2, 'Universal Pictures', 'VHS');

-- Star Wars

INSERT INTO Film (name, genre, subgenre, director, premierYear, existingUnits, productionCompany, format) 
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