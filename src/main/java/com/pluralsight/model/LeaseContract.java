package com.pluralsight.model;

import java.time.LocalDate;

public class LeaseContract
{
private final String vin;
private final LocalDate signingDate;
private final String customerName;
private final String contractId;
private final double amount;
private final double monthlyAmount;
private final int termLength;

public LeaseContract(String vin, LocalDate signingDate, String customerName, String contractId,
                     double totalAmount, double monthlyAmount, int termLength) {
    this.vin = vin;
    this.signingDate = signingDate;
    this.customerName = customerName;
    this.contractId = contractId;
    this.amount = totalAmount;
    this.monthlyAmount = monthlyAmount;
    this.termLength = termLength;
}

    public double getAmount()
    {
        return amount;
    }

    public String getContractId()
    {
        return contractId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public double getMonthlyAmount()
    {
        return monthlyAmount;
    }

    public LocalDate getSigningDate()
    {
        return signingDate;
    }

    public int getTermLength()
    {
        return termLength;
    }

    public String getVin()
    {
        return vin;
    }

    @Override
    public String toString(){
    return String.format("%s | %s | %s | %.2f | %.2f | %d | %s",
            contractId, customerName, signingDate, amount, monthlyAmount, termLength, vin);
    }
}
