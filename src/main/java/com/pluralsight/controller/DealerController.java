package com.pluralsight.controller;

import com.pluralsight.dao.DealershipDao;
import com.pluralsight.dao.SqlDealershipDao;
import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dealership")
public class DealershipController
{
    private final DealershipDao dealershipDao;
    private final DealershipDao sqlDealershipDao;

    @Autowired
    public DealershipController(DealershipDao dealershipDao, SqlDealershipDao sqlDealershipDao)
    {
        this.dealershipDao = dealershipDao;
        this.sqlDealershipDao = sqlDealershipDao;
    }


    @PostMapping("/dealership/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Dealership createDealership(@RequestBody Dealership dealership)
    {
        return sqlDealershipDao.addDealership(dealership);
    }


    @GetMapping("/dealership")
    public List<Dealership> findAllDealers()
    {
        return sqlDealershipDao.getAllDealers();
    }

    @GetMapping("/dealership/{id}")
    public Dealership findDealerById(@PathVariable String id)
    {
        return sqlDealershipDao.findDealerById(id);
    }


    @GetMapping("/dealership/inventory")
    public ResponseEntity<?> findVehiclesByDealerId(@RequestParam String dealershipId)
    {
        if (!sqlDealershipDao.dealerActive(dealershipId)) {
            Map<String, String> response = Map.of(
                    "error", "Not Found",
                    "message", "No dealership found with the provided ID: " + dealershipId
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Map<Dealership, List<Vehicle>> inventory = sqlDealershipDao.getDealerInventoryById(dealershipId);
        if (inventory == null || inventory.isEmpty()) {
            Map<String, String> response = Map.of(
                    "error", "No Inventory",
                    "message", "The dealership with ID " + dealershipId + " exists, but it has no inventory."
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(inventory);
    }


    @PutMapping("/dealership/{id}")
    public void updateDealership(@PathVariable String id,
                                 @RequestBody Dealership dealership)
    {
        sqlDealershipDao.updateDealer(id, dealership);
    }


    @DeleteMapping("/dealership/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDealer(@PathVariable String id)
    {
        sqlDealershipDao.deleteDealer(id);
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public DealerEntity createDealer(@RequestBody DealerEntity dealership)
    {
        return dealershipDao.saveDealer(dealership);
    }


    @GetMapping
    public List<Dealership> getAllDealers()
    {
        return DealershipDao.getAllDealers();
    }

    @GetMapping("/{id}")
    public DealerEntity getDealerById(@PathVariable String id)
    {
        return dealershipDao.getDealerById(id);
    }

}