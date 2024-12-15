package com.pluralsight.dataTables;


// TODO look up record class
public class Vehicle
{
    private final String id;
    private final String make;
    private final String model;
    private final int year;
    private final double price;
    private final int mileage;
    private final boolean available;
    private final String condition;
    private final String leaseStatus;

    public Vehicle(boolean available, String id, String make, String model, int year, double price, int mileage, String condition, String leaseStatus)
    {
        this.available = available;
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.condition = condition;
        this.leaseStatus = leaseStatus;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public String getCondition()
    {
        return condition;
    }

    public String getId()
    {
        return id;
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

    public int getYear()
    {
        return year;
    }
}
