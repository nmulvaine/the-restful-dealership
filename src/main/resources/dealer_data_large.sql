# Expanding the dataset by adding more entries to all tables in the SQL script.

# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          large_dealership                                #
# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS dealership_database;

CREATE DATABASE IF NOT EXISTS dealership_database;

USE dealership_database;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #
# ---------------------------------------------------------------------- #
# Add table "Dealerships"                                                #
# ---------------------------------------------------------------------- #

-- Create Tables
CREATE TABLE Dealerships (
    DealershipID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Location VARCHAR(255) NOT NULL
);

# ---------------------------------------------------------------------- #
# Add table "Vehicles"                                                   #
# ---------------------------------------------------------------------- #


CREATE TABLE Vehicles (
    VehicleID INT PRIMARY KEY AUTO_INCREMENT,
    Make VARCHAR(255) NOT NULL,
    Model VARCHAR(255) NOT NULL,
    VehicleYear INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Mileage INT NOT NULL,
    Availability ENUM('Available', 'Sold') NOT NULL,
    VehicleCondition ENUM('New', 'Used') NOT NULL,
    LeaseStatus ENUM('Leased', 'Not Leased') NULL,
    DealershipID INT,
    FOREIGN KEY (DealershipID) REFERENCES Dealerships(DealershipID)
);

# ---------------------------------------------------------------------- #
# Add table "Contracts"                                              #
# ---------------------------------------------------------------------- #

CREATE TABLE Contracts (
    ContractID INT PRIMARY KEY AUTO_INCREMENT,
    VehicleID INT NOT NULL,
    CustomerName VARCHAR(255) NOT NULL,
    ContractType ENUM('Sale', 'Lease') NOT NULL,
    ContractDate DATE NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (VehicleID) REFERENCES Vehicles(VehicleID)
);

-- Insert Dealership Data
INSERT INTO Dealerships (Name, Location) VALUES
('Northside Autos', 'New York, NY'),
('West Coast Cars', 'Los Angeles, CA'),
('Southern Rides', 'Houston, TX'),
('Midwest Motors', 'Chicago, IL');

-- Insert Vehicle Data
INSERT INTO Vehicles (Make, Model, VehicleYear, Price, Mileage, Availability, VehicleCondition, LeaseStatus, DealershipID) VALUES
-- Vehicles for Northside Autos
('Toyota', 'Camry', 2020, 24000, 15000, 'Available', 'Used', NULL, 1),
('Honda', 'Civic', 2019, 22000, 18000, 'Sold', 'Used', NULL, 1),
('Ford', 'F-150', 2021, 35000, 5000, 'Available', 'New', 'Not Leased', 1),
('Chevrolet', 'Silverado', 2021, 36000, 8000, 'Sold', 'Used', NULL, 1),
('Nissan', 'Altima', 2018, 20000, 25000, 'Available', 'Used', NULL, 1),
-- Vehicles for West Coast Cars
('Tesla', 'Model 3', 2022, 39999, 2000, 'Available', 'New', 'Not Leased', 2),
('Toyota', 'Corolla', 2020, 21000, 12000, 'Sold', 'Used', NULL, 2),
('BMW', 'X5', 2021, 60000, 5000, 'Available', 'New', 'Not Leased', 2),
('Audi', 'A4', 2019, 35000, 22000, 'Sold', 'Used', NULL, 2),
('Mercedes-Benz', 'C-Class', 2020, 45000, 8000, 'Available', 'Used', NULL, 2),
-- Vehicles for Southern Rides
('Jeep', 'Wrangler', 2021, 32000, 10000, 'Available', 'New', 'Not Leased', 3),
('Ram', '1500', 2022, 45000, 3000, 'Available', 'New', 'Not Leased', 3),
('Kia', 'Sorento', 2020, 27000, 15000, 'Sold', 'Used', NULL, 3),
('Hyundai', 'Elantra', 2019, 18000, 20000, 'Available', 'Used', NULL, 3),
('Chevrolet', 'Tahoe', 2021, 55000, 7000, 'Sold', 'Used', NULL, 3),
-- Vehicles for Midwest Motors
('Subaru', 'Outback', 2020, 29000, 14000, 'Available', 'Used', NULL, 4),
('Ford', 'Escape', 2019, 25000, 19000, 'Sold', 'Used', NULL, 4),
('Honda', 'CR-V', 2021, 31000, 5000, 'Available', 'New', 'Not Leased', 4),
('Mazda', 'CX-5', 2020, 28000, 12000, 'Available', 'Used', NULL, 4),
('Toyota', 'RAV4', 2021, 32000, 8000, 'Sold', 'Used', NULL, 4);


-- Insert Contract Data
INSERT INTO Contracts (VehicleID, CustomerName, ContractType, ContractDate, Amount) VALUES
(1, 'John Doe', 'Sale', '2023-01-15', 24000),
(2, 'Jane Smith', 'Lease', '2023-02-10', 15000),
(3, 'Emily Johnson', 'Sale', '2023-03-05', 35000),
(4, 'Michael Brown', 'Lease', '2023-04-20', 18000),
(5, 'Sophia Davis', 'Sale', '2023-05-11', 20000),
(6, 'James Wilson', 'Lease', '2023-06-07', 39999),
(7, 'Olivia Martinez', 'Sale', '2023-07-22', 21000),
(8, 'Daniel Garcia', 'Lease', '2023-08-14', 30000),
(9, 'Isabella Hernandez', 'Sale', '2023-09-03', 45000),
(10, 'Matthew Moore', 'Lease', '2023-10-19', 25000);


-- Generate More Vehicles
DELIMITER $$
CREATE PROCEDURE PopulateMoreVehicles()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE dealership INT;
    DECLARE brand_list TEXT DEFAULT 'Toyota,Honda,Ford,Chevrolet,Nissan';
    DECLARE model_list TEXT DEFAULT 'Camry,Civic,F-150,Silverado,Altima';
    
    WHILE i <= 200 DO
        SET dealership = (i MOD 4) + 1;
        INSERT INTO Vehicles (Make, Model, VehicleYear, Price, Mileage, Availability, VehicleCondition, LeaseStatus, DealershipID) 
        VALUES (
            ELT((i MOD 5) + 1, 'Toyota', 'Honda', 'Ford', 'Chevrolet', 'Nissan'),
            ELT((i MOD 5) + 1, 'Camry', 'Civic', 'F-150', 'Silverado', 'Altima'),
            2018 + (i MOD 5), 
            20000 + (i * 100 MOD 20000), 
            10000 + (i * 10 MOD 30000),
            IF(i MOD 3 = 0, 'Sold', 'Available'),
            IF(i MOD 2 = 0, 'New', 'Used'),
            IF(i MOD 2 = 0, 'Not Leased', NULL),
            dealership
        );
        INSERT INTO Contracts (VehicleID, CustomerName, ContractType, ContractDate, Amount)
        VALUES (
            i + 10, 
            CONCAT('Customer', i), 
            IF(i MOD 2 = 0, 'Sale', 'Lease'), 
            DATE_ADD('2023-01-01', INTERVAL i DAY), 
            20000 + (i * 100 MOD 20000)
        );
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL PopulateMoreVehicles();