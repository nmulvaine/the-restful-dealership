package com.pluralsight.model;

public class Dealership
{
    private final String id;
    private final String name;
    private final String address;
    private final String phone;

    public Dealership(String address, String id, String name, String phone)
    {
        this.address = address;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Dealership(){
        this.id = "";
        this.name = "";
        this.address = "";
        this.phone = "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Dealership information:" +
               id + name + address + phone;
    }
}
