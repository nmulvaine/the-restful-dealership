package com.pluralsight.controller;

import com.pluralsight.dao.VehicleDao;
import com.pluralsight.dao.VehicleDaoInterface;
import com.pluralsight.dataTables.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController
{
    private VehicleDaoInterface vehicleDaoInterface;
    private VehicleDao vehicleDao;


    @Autowired
    VehicleController(VehicleDaoInterface vehicleDaoInterface, VehicleDao vehicleDao)
    {
        this.vehicleDaoInterface = vehicleDaoInterface;
        this.vehicleDao = vehicleDao;
    }

    @GetMapping(path = "/data")
    public List<Vehicle> getAllVehicles()
    {
        return vehicleDaoInterface.getAllVehicle();
    }

    @GetMapping(path = "/data/search")
    public List<Vehicle> filterVehicles(@RequestParam(required = false) String vin,
                                        @RequestParam(required = false) String make,
                                        @RequestParam(required = false) String model,
                                        @RequestParam(required = false) Integer year,
                                        @RequestParam(required = false) Double price,
                                        @RequestParam(required = false) String color,
                                        @RequestParam(required = false) Integer mileage,
                                        @RequestParam(required = false) String type,
                                        @RequestParam(required = false) String condition,
                                        @RequestParam(required = false) String leaseStatus,
                                        @RequestParam(required = false) boolean available)

    {

        List<Vehicle> vehicleList = vehicleDaoInterface.getAllVehicle();
        return vehicleList.stream()
                .filter(vehicle -> vin == null || vin.equals(vehicle.getVin()))
                .filter(vehicle -> make == null || make.equalsIgnoreCase(vehicle.getMake()))
                .filter(vehicle -> model == null || model.equalsIgnoreCase(vehicle.getModel()))
                .filter(vehicle -> type == null || type.equalsIgnoreCase(vehicle.getType()))
                .filter(vehicle -> year == null || year.equals(vehicle.getYear()))
                .filter(vehicle -> price == null || price.equals(vehicle.getPrice()))
                .filter(vehicle -> color == null || color.equalsIgnoreCase(vehicle.getColor()))
                .filter(vehicle -> mileage == null || mileage.equals(vehicle.getMileage()))
                .filter(vehicle -> condition == null || condition.equals(vehicle.getCondition()))
                .filter(vehicle -> leaseStatus == null || leaseStatus.equals(vehicle.getLeaseStatus()))
                .filter(vehicle -> available || !vehicle.isAvailable())
                .collect(Collectors.toList());

    }

    @GetMapping(path = "/data/search/filters")
    public List<Vehicle> filterVehicleByData(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMileage,
            @RequestParam(required = false) Integer maxMileage,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) String leaseStatus,
            @RequestParam(required = false) boolean available
    )
    {
        List<Vehicle> vehicles = vehicleDaoInterface.getAllVehicle();
        return vehicles.stream()
                .filter(vehicle -> minPrice == null || vehicle.getPrice() >= minPrice)
                .filter(vehicle -> maxPrice == null || vehicle.getPrice() <= maxPrice)
                .filter(vehicle -> make == null || make.equalsIgnoreCase(vehicle.getMake()))
                .filter(vehicle -> model == null || model.equalsIgnoreCase(vehicle.getModel()))
                .filter(vehicle -> minYear == null || vehicle.getYear() >= minYear)
                .filter(vehicle -> maxYear == null || vehicle.getYear() <= maxYear)
                .filter(vehicle -> color == null || color.equalsIgnoreCase(vehicle.getColor()))
                .filter(vehicle -> minMileage == null || vehicle.getMileage() >= minMileage)
                .filter(vehicle -> maxMileage == null || vehicle.getMileage() <= maxMileage)
                .filter(vehicle -> type == null || type.equalsIgnoreCase(vehicle.getType()))
                .collect(Collectors.toList());
    }

}
