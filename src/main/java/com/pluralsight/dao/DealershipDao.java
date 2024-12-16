package com.pluralsight.dao;

import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;

import java.util.List;
import java.util.Map;

public interface DealershipDao
{
    Dealership addDealership (Dealership dealership);

    Dealership findDealerById (String id);
    List<Dealership> getAllDealers();

    Map<Dealership, List<Vehicle>> getDealerInventory(String id);

    void updateDealer (String id, Dealership dealership);

    void deleteDealer (String id);
    boolean dealerActive(String id);
}
