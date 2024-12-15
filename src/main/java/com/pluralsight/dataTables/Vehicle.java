package com.pluralsight.dataTables;

public class Vehicle
{
    private String vin;
    private String make;
    private String model;
    private String type;
    private int year;
    private double price;
    private String color;
    private int mileage;
    private String condition;
    private String leaseStatus;
    private boolean available;

    public Vehicle(boolean available, String color, String condition, String leaseStatus, String make, int mileage, String model, double price, String type, String vin, int year)
    {
        this.available = available;
        this.color = color;
        this.condition = condition;
        this.leaseStatus = leaseStatus;
        this.make = make;
        this.mileage = mileage;
        this.model = model;
        this.price = price;
        this.type = type;
        this.vin = vin;
        this.year = year;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public String getColor()
    {
        return color;
    }

    public String getCondition()
    {
        return condition;
    }

    public String getLeaseStatus()
    {
        return leaseStatus;
    }

    public String getMake()
    {
        return make;
    }

    public int getMileage()
    {
        return mileage;
    }

    public String getModel()
    {
        return model;
    }

    public double getPrice()
    {
        return price;
    }

    public String getType()
    {
        return type;
    }

    public String getVin()
    {
        return vin;
    }

    public int getYear()
    {
        return year;
    }

    @Override
    public String toString()
    {
        return String.format("%s | %s | %s | %s | %d | %.2f | %s | %d | %s, | $s | %b",
                vin, make, model, type, year, price, color, mileage, condition, leaseStatus, available);
    }

    public String toCSV()
    {
        return String.format("%s | %s | %s | %s | %d | %.2f | %s | %d | %s, | $s",
                vin, make, model, type, year, price, color, mileage, condition, leaseStatus);
    }
}
