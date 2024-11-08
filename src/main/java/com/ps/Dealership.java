package com.ps;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;


    public Dealership(String name, String address, String phone) {

        this.name = name;
        this.address = address;
        this.phone = phone;

        this.inventory = new ArrayList<>();



    }
    public List<Vehicle> getVehiclesByPrice(double min, double max){
        List<Vehicle> priceMatchedVehicles = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            boolean match = true;
            if(vehicle.getPrice() < min){
                match = false;
            }
            if(vehicle.getPrice() > max){
                match = false;
            }
            if(match){
                priceMatchedVehicles.add(vehicle);
            }
        }
        return priceMatchedVehicles;
    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        List<Vehicle> makeAndModelMatch = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                makeAndModelMatch.add(vehicle);
            }
        }
        return makeAndModelMatch;
    } // List<Vehicle>
    public List<Vehicle> getVehiclesByYear(int min, int max){
        List<Vehicle> yearMatch = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            boolean match = true;
            if(vehicle.getYear() < min){
                match = false;
            }
            if(vehicle.getYear() > max){
                match = false;
            }
            if(match){
                yearMatch.add(vehicle);
            }
        }
        return yearMatch;
    } // List<Vehicle>
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> colorMatch = new ArrayList<>();
        for(Vehicle vehicle: inventory){
            if(vehicle.getColor().equalsIgnoreCase(color)){
                colorMatch.add(vehicle);
            }
        }
        return colorMatch;
    }
    public List<Vehicle> getVehiclesByMileage(int min, int max){
        List<Vehicle>  mileMatch = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            boolean match = true;
            if(vehicle.getOdometer() < min){
                match = false;
            }
            if(vehicle.getOdometer() > max){
                match = false;
            }
            if(match){
                mileMatch.add(vehicle);
            }

        }
        return mileMatch;

    } // List <Vehicle>
    public List<Vehicle> getVehiclesByType(String vehicleType){
        List<Vehicle> typeMatch = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                typeMatch.add(vehicle);
            }
        }
        return typeMatch;
    }
    public List<Vehicle> getAllVehicles(){
        return this.inventory;
    } // List<Vehicle>


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addVehicle(Vehicle vehicle){
        this.inventory.add(vehicle);
    } //expects a vehicle object


    public  void removeVehicle(Vehicle vehicle){
        this.inventory.remove(vehicle);
    } // removes vehicle



    @Override
    public String toString() {
        return "Dealership{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}