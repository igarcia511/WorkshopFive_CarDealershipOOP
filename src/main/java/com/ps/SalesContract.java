package com.ps;

public class SalesContract extends Contract {
    private static final double salesTax = 0.05;
    private static final double recordingFee = 100.00;
    private double processingfee;
    private boolean doesWantToFinance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean doesWantToFinance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.doesWantToFinance = doesWantToFinance;
        calculateProcessingFee(vehicleSold);


    }


    public double calculateProcessingFee(Vehicle vehicle) {
        if (vehicle.getPrice() < 10000) {
            this.processingfee = 295.00;
        } else {
            this.processingfee = 495.00;
        }
        return this.processingfee;
    }

    public double getSalesTax() {
        return getVehicleSold().getPrice() * salesTax;
    }


    @Override
    public double getMonthlyPayment() {
        double monthlyInterestRate = 0.00354167;
        double principal = getVehicleSold().getPrice();
        double monthlyPayment = 0;

        if (doesWantToFinance) {
            if (getVehicleSold().getPrice() >= 10000) {
                monthlyPayment = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, 48)) / (Math.pow(1 + monthlyInterestRate, 48) - 1);
            } else if(getVehicleSold().getPrice() < 10000) {
                monthlyPayment = (principal * 0.04375 * Math.pow(1 + 0.04375, 24)) / (Math.pow(1 + 0.04375, 24) - 1);
            } else {
                System.out.println("Error");
            }
        } else {
            monthlyPayment = 0.00;
        }
        return monthlyPayment;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getSalesTax() + recordingFee + processingfee;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f|%b|%.2f", getClass().getSimpleName(), getDate(), getCustomerName(), getCustomerEmail(), getVehicleSold(),
                getSalesTax(), recordingFee, processingfee, getTotalPrice(), doesWantToFinance, getMonthlyPayment());
    }
}
