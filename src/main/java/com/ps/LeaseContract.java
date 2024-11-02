package com.ps;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private static double leaseFee = 0.07;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        calculateEndingValue(vehicleSold);

    }

    public double calculateEndingValue(Vehicle vehicle){
    expectedEndingValue = vehicle.getPrice() - (vehicle.getPrice() / 2);
    return expectedEndingValue;
    }

    public double calculateLeaseFee(){
        return getVehicleSold().getPrice() * leaseFee;
    }

    @Override
    public double getMonthlyPayment(){
        int totalMonths = 36;
        // first part is off by 30 bucks not sure why, tried everything.
        double powValue = Math.pow((1 + 0.00333333), -totalMonths);
        System.out.println(1+ 0.00333333 );
        System.out.println(powValue);
        double first = (expectedEndingValue * 0.00333333) / (1 - powValue);
        System.out.println(first);
        double second = expectedEndingValue / totalMonths;
        System.out.println(second);
        double monthlyPayment  = first + second;
        System.out.println(monthlyPayment);
        return monthlyPayment;

    }

    @Override
    public double getTotalPrice(){
        return expectedEndingValue + calculateLeaseFee();
    }
    @Override
    public String toString(){
        return String.format("%s|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f", getClass().getSimpleName(), getDate(), getCustomerName(),
                getCustomerEmail(),getVehicleSold(), expectedEndingValue, calculateLeaseFee(), getTotalPrice(),getMonthlyPayment());
    }
}
