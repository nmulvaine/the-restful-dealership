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
    VIN VARCHAR(17) PRIMARY KEY,
    Make VARCHAR(255) NOT NULL,
    Model VARCHAR(255) NOT NULL,
    VehicleYear INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Mileage INT NOT NULL,
    Availability ENUM('Available', 'Sold') NOT NULL,
    VehicleCondition ENUM('New', 'Used') NOT NULL,
    LeaseStatus ENUM('Leased', 'Not Leased', 'No') DEFAULT 'No',
    DealershipID INT,
    FOREIGN KEY (DealershipID) REFERENCES Dealerships(DealershipID)
);

# ---------------------------------------------------------------------- #
# Add table "Contracts"                                              #
# ---------------------------------------------------------------------- #

CREATE TABLE Contracts (
    ContractID INT PRIMARY KEY AUTO_INCREMENT,
    VIN VARCHAR(17) NOT NULL,
    CustomerName VARCHAR(255) DEFAULT 'No Customer',
    ContractType ENUM('Sale', 'Lease') NOT NULL,
    ContractDate DATE NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

-- Insert Dealership Data
INSERT INTO Dealerships (Name, Location) VALUES
('Northside Autos', 'New York, NY'),
('West Coast Cars', 'Los Angeles, CA'),
('Southern Rides', 'Houston, TX'),
('Midwest Motors', 'Chicago, IL');

-- Insert Vehicle Data
INSERT INTO Vehicles (VIN, Make, Model, VehicleYear, Price, Mileage, Availability, VehicleCondition, LeaseStatus, DealershipID) VALUES
-- Vehicles for Northside Autos
('0000001', 'Toyota', 'Camry', 2020, 24000, 15000, 'Available', 'Used', 'No', 1),
('0000002', 'Honda', 'Civic', 2019, 22000, 18000, 'Sold', 'Used', 'No', 1),
('0000003', 'Ford', 'F-150', 2021, 35000, 5000, 'Available', 'New', 'Not Leased', 1),
('0000004', 'Chevrolet', 'Silverado', 2021, 36000, 8000, 'Sold', 'Used', 'No', 1),
('0000005', 'Nissan', 'Altima', 2018, 20000, 25000, 'Available', 'Used', 'No', 1),
-- Vehicles for West Coast Cars
('0000006', 'Tesla', 'Model 3', 2022, 39999, 2000, 'Available', 'New', 'Not Leased', 2),
('0000007', 'Toyota', 'Corolla', 2020, 21000, 12000, 'Sold', 'Used', 'No', 2),
('0000008', 'BMW', 'X5', 2021, 60000, 5000, 'Available', 'New', 'Not Leased', 2),
('0000009', 'Audi', 'A4', 2019, 35000, 22000, 'Sold', 'Used', 'No', 2),
('0000010', 'Mercedes-Benz', 'C-Class', 2020, 45000, 8000, 'Available', 'Used', 'No', 2);

-- Insert Contract Data
-- Insert Contract Data with proper CustomerName values
INSERT INTO Contracts (VIN, CustomerName, ContractType, ContractDate, Amount) VALUES
('0000001', 'Alice Johnson', 'Sale', '2023-01-15', 24000),
('0000002', 'Bob Smith', 'Lease', '2023-02-10', 15000),
('0000003', 'Charlie Brown', 'Sale', '2023-03-05', 35000),
('0000004', 'Diana Prince', 'Lease', '2023-04-20', 18000),
('0000005', 'Eve Adams', 'Sale', '2023-05-11', 20000),
('0000006', 'Frank Castle', 'Lease', '2023-06-07', 39999),
('0000007', 'Grace Hopper', 'Sale', '2023-07-22', 21000),
('0000008', 'Hank Pym', 'Lease', '2023-08-14', 30000),
('0000009', 'Ivy Carter', 'Sale', '2023-09-03', 45000),
('0000010', 'John Doe', 'Lease', '2023-10-19', 25000);


-- Generate More Vehicles
DELIMITER $$
CREATE PROCEDURE PopulateMoreVehicles()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE dealership INT;
    WHILE i <= 200 DO
        SET dealership = (i MOD 4) + 1;
        -- Insert into Vehicles table
        INSERT INTO Vehicles (VIN, Make, Model, VehicleYear, Price, Mileage, Availability, VehicleCondition, LeaseStatus, DealershipID) 
        VALUES (
            CONCAT(LPAD(i + 10, 7, '0')),
            ELT((i MOD 5) + 1, 'Toyota', 'Honda', 'Ford', 'Chevrolet', 'Nissan'),
            ELT((i MOD 5) + 1, 'Camry', 'Civic', 'F-150', 'Silverado', 'Altima'),
            2018 + (i MOD 5), 
            20000 + (i * 100 MOD 20000), 
            10000 + (i * 10 MOD 30000),
            IF(i MOD 3 = 0, 'Sold', 'Available'),
            IF(i MOD 2 = 0, 'New', 'Used'),
            IF(i MOD 2 = 0, 'Not Leased', 'No'),
            dealership
        );
        -- Insert into Contracts table
        INSERT INTO Contracts (VIN, CustomerName, ContractType, ContractDate, Amount)
        VALUES (
            CONCAT(LPAD(i + 10, 7, '0')),
            CONCAT(
                ELT((i MOD 5) + 1, 'John', 'Frank', 'Eve', 'Hank', 'Grace'),
                ' ',
                ELT((i MOD 5) + 1, 'Carter', 'Hopper', 'Adams', 'Prince', 'Brown')
            ),
            IF(i MOD 2 = 0, 'Sale', 'Lease'), 
            DATE_ADD('2023-01-01', INTERVAL i DAY), 
            20000 + (i * 100 MOD 20000)
        );
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL PopulateMoreVehicles();