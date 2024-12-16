package com.pluralsight.dao;

import com.pluralsight.model.LeaseContract;
import org.springframework.lang.Contract;

import java.time.LocalDate;
import java.util.List;

public interface LeaseDao
{
    void addLeaseContract (String vin, LocalDate singingDate, String customerName,
    String contractId, double amount, double monthlyAmount, int termLength);

    double calculatePayment (double price, int term);
    List<LeaseContract> getLeaseContracts();
}
