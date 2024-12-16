package com.pluralsight.controller;

import com.pluralsight.dao.VehicleDao;
import com.pluralsight.dao.VehicleDaoInterface;
import com.pluralsight.dataTables.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController
{
    private VehicleDaoInterface vehicleDaoInterface;


    @Autowired
    VehicleController(VehicleDaoInterface vehicleDaoInterface)
    {
        this.vehicleDaoInterface = vehicleDaoInterface;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleDaoInterface.getAllVehicle();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDaoInterface.addVehicle(vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getType(), vehicle.getColor(), vehicle.getMileage(), vehicle.getPrice(), vehicle.isAvailable());
        return vehicle;
    }

    @DeleteMapping("/{vin}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable String vin) {
        vehicleDaoInterface.removeVehicle(vin);
    }

    @GetMapping("/search")
    public List<Vehicle> filterVehicles(
            @RequestParam(required = false) String vin,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer mileage,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) boolean available) {

        List<Vehicle> vehicleList = vehicleDaoInterface.getAllVehicle();
        return vehicleList.stream()
                .filter(vehicle -> vin == null || vin.equals(vehicle.getVin()))
                .filter(vehicle -> make == null || make.equalsIgnoreCase(vehicle.getMake()))
                .filter(vehicle -> model == null || model.equalsIgnoreCase(vehicle.getModel()))
                .filter(vehicle -> year == null || year.equals(vehicle.getYear()))
                .filter(vehicle -> price == null || price.equals(vehicle.getPrice()))
                .filter(vehicle -> color == null || color.equalsIgnoreCase(vehicle.getColor()))
                .filter(vehicle -> mileage == null || mileage.equals(vehicle.getMileage()))
                .filter(vehicle -> type == null || type.equalsIgnoreCase(vehicle.getType()))
                .filter(vehicle -> condition == null || condition.equals(vehicle.getCondition()))
                .filter(vehicle -> available == vehicle.isAvailable())
                .collect(Collectors.toList());
    }
}