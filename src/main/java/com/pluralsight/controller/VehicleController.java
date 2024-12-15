package com.pluralsight.controller;

import com.pluralsight.dataTables.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.pluralsight.dao.VehicleDao;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController
{
    private VehicleDao vehicleDao;

    @Autowired
    VehicleController(VehicleDao vehicleDao)
    {
        this.vehicleDao = vehicleDao;
    }

    public List<Vehicle> filterVehicles(@RequestParam(required = false) String id,
                                        @RequestParam(required = false) String make,
                                        @RequestParam(required = false) String model,
                                        @RequestParam(required = false) Integer year,
                                        @RequestParam(required = false) Double price,
                                        @RequestParam(required = false) String color,
                                        @RequestParam(required = false) Integer mileage,
                                        @RequestParam(required = false) String type,
                                        @RequestParam(required = false) String condition,
                                        @RequestParam(required = false) String leaseStatus)
    {


}
