
package com.ps;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
public class UserInterface {
    private static Dealership dealership;
    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);

    public UserInterface() {
    }


    private static void init() {
        System.out.println("init method");
        dealership = DealershipFileManager.getDealership();

        // Loading Dealership  and inventory from a file
    }

    public static void display() {
        init();

         try {
             System.out.println("Welcome to " + dealership.getName());
             int mainMenuCommand;
             do {
                 System.out.println("1) Find vehicles within a price range");
                 System.out.println("2) Find vehicles by make / model");
                 System.out.println("3) Find vehicles by year range");
                 System.out.println("4) Find vehicles by color");
                 System.out.println("5) Find vehicles by mileage range");
                 System.out.println("6) Find vehicles by type (car, truck, SUV, van)");
                 System.out.println("7) List ALL vehicles");
                 System.out.println("8) Add a vehicle");
                 System.out.println("9) Remove a vehicle");
                 System.out.println("10) Sell/Lease a vehicle");
                 System.out.println("99) to quit. . .");

                 System.out.print("Command: ");
                 mainMenuCommand = commandScanner.nextInt();

                 switch (mainMenuCommand) {
                     case 1:
                         processGetByPriceRequest();
                         break;
                     case 2:
                         processGetByMakeModelRequest();
                         break;
                     case 3:
                         processGetByYearRequest();
                         break;
                     case 4:
                         processGetByColorRequest();
                         break;
                     case 5:
                         processGetByMileageRequest();
                         break;
                     case 6:
                         processGetByVehicleTypeRequest();
                         break;
                     case 7:
                         processGetAllVehiclesRequest();
                         break;
                     case 8:
                         processAddVehicleRequest();
                         break;
                     case 9:
                         processRemoveVehicleRequest();
                         break;
                     case 10:
                         processSaleOrLeaseVehicle();
                         break;
                     case 99:
                         System.out.println("Exiting the program. . . ");
                         break;
                     default:
                         System.out.println("Invalid command, try again. . .");
                         break;

                 }


             } while (mainMenuCommand != 99);
         } catch (Exception exception){
             exception.printStackTrace();
         }
    }


    public static void processGetByPriceRequest() {
        System.out.println("Searching by price:");
        System.out.print("Enter the minimum price: ");
        double minPrice = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the maximum price: ");
        double maxPrice = inputScanner.nextInt();
        inputScanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        for (Vehicle vehicle : vehicles) {
            displayVehicles(vehicle);
        }
    }

    public static void processGetByMakeModelRequest() {
        System.out.println("Searching by make and model");
        System.out.print("Enter the make: ");
        String make = inputScanner.nextLine();
        System.out.print("Enter the model: ");
        String model = inputScanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        for (Vehicle vehicle : vehicles) {
            displayVehicles(vehicle);
        }
    }

    public static void processGetByYearRequest() {
        System.out.print("Enter the start year: ");
        int startYear = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the end year: ");
        int endYear = inputScanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByYear(startYear, endYear);
        for (Vehicle vehicle : vehicles) {
            displayVehicles(vehicle);
        }

    }

    public static void processGetByColorRequest() {
        System.out.print("Enter the color you are looking for:");
        String userColorChoice = inputScanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(userColorChoice);
        for (Vehicle vehicle : vehicles) {
            displayVehicles(vehicle);
        }


    }

    public static void processGetByMileageRequest() {
        System.out.print("Enter the minimum miles:");
        int minMiles = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the maximum miles:");
        int maxMiles = inputScanner.nextInt();
        inputScanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMiles, maxMiles);
        for (Vehicle vehicle : vehicles) {
            displayVehicles(vehicle);
        }


    }

    public static void processGetByVehicleTypeRequest() {
        System.out.print("Enter the type of vehicle you are searching for(suv, truck, sedan): ");
        String vehicleType = inputScanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        for (Vehicle vehicle : vehicles) {
            displayVehicles(vehicle);
        }

    }

    public static void processGetAllVehiclesRequest() {
        System.out.println("Displaying all vehicles");
        List<Vehicle> vehicles = dealership.getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            displayVehicles(vehicle);
        }

    }

    public static void processAddVehicleRequest() {
        System.out.println("Enter details of vehicle to add:");
        //int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price
        System.out.print("Enter the vin number: ");
        int vinNumber = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the year:");
        int yearNumber = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the make:");
        String make = inputScanner.nextLine();
        System.out.print("Enter the model:");
        String model = inputScanner.nextLine();
        System.out.print("Enter vehicle type(suv, truck, sedan):");
        String type = inputScanner.nextLine();
        System.out.print("Enter the color: ");
        String color = inputScanner.nextLine();
        System.out.print("Enter the mileage: ");
        int mileage = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter the price: ");
        double price = inputScanner.nextDouble();

        Vehicle vehicle = new Vehicle(vinNumber, yearNumber, make, model, type, color, mileage, price);
        dealership.addVehicle(vehicle);
        DealershipFileManager.saveDealership(dealership);
    }

    public static void processRemoveVehicleRequest() {

        int index = 1;
        List<Vehicle> vehicles = dealership.getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            System.out.printf(index + " " + "%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
            index++;
        }

        System.out.println("Select the vehicle you want to remove by its number");
        int vehicleNumber = inputScanner.nextInt();
        Vehicle vehicle = vehicles.get(vehicleNumber - 1);

        if (vehicleNumber < 1) {
            System.out.println("Invalid vehicle");
        } else {
            dealership.removeVehicle(vehicle);
        }
        DealershipFileManager.saveDealership(dealership);


    }

    private static void processSaleOrLeaseVehicle() {
        //as user if it is a sale or a lease
        // collect basic sales informations
        // add vehicle to contract
        // calculate pricing
        int buyOption;
        List<Vehicle> vehiclesToRemove = new ArrayList<>();
        System.out.println("Enter 1) to buy a vehicle, 2) to lease a vehicle: ");
        buyOption = inputScanner.nextInt();
        inputScanner.nextLine();

        if (buyOption == 1) {

            System.out.print("Enter date: ");
            String date = inputScanner.nextLine();
            System.out.print("Enter name: ");
            String name = inputScanner.nextLine();
            System.out.print("Enter email: ");
            String email = inputScanner.nextLine();
            System.out.print("Do you want to finance? 1) for yes 2) for no: ");
            int financeOrNo = inputScanner.nextInt();
            inputScanner.nextLine();
            boolean financeDecision;
            if (financeOrNo == 1) {
                financeDecision = true;
            } else if (financeOrNo == 2) {
                financeDecision = false;
            } else {
                financeDecision = false;
            }
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                displayVehicles(vehicle);

            }
            System.out.print("Enter the vin of vehicle you want to buy:");
            int vehicleVinNumber = inputScanner.nextInt();


            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vehicleVinNumber) {
                    SalesContract sale = new SalesContract(date, name, email, vehicle, financeDecision);
                    ContractFileManager.saveContract(sale);
                    vehiclesToRemove.add(vehicle);
                }

            }


        } else if (buyOption == 2) {
            System.out.print("Enter date: ");
            String date = inputScanner.nextLine();
            System.out.print("Enter name: ");
            String name = inputScanner.nextLine();
            System.out.print("Enter email: ");
            String email = inputScanner.nextLine();
           for (Vehicle vehicle : dealership.getAllVehicles()) {
               LocalDate currentDate = LocalDate.now();
               LocalDate dateMinusThreeYears = currentDate.minusYears(4);
               int yearMinusThree = dateMinusThreeYears.getYear();
               if(vehicle.getYear() > yearMinusThree){
                   displayVehicles(vehicle);
               }
            }
            System.out.println("Enter the vin of the vehicle you want to lease:");
           int vehicleNumber = inputScanner.nextInt();

           for(Vehicle vehicle : dealership.getAllVehicles()){
               if(vehicle.getVin() == vehicleNumber){
                   LeaseContract lease = new LeaseContract(date, name, email, vehicle);
                   ContractFileManager.saveContract(lease);
                   vehiclesToRemove.add(vehicle);

               }

           }
        }
              for(Vehicle vehicle: vehiclesToRemove){
                  dealership.removeVehicle(vehicle);
              }
    }

    private static void displayVehicles(Vehicle vehicle) {
        //Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price
        System.out.printf("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                vehicle.getVin(),
                vehicle.getYear(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getVehicleType(),
                vehicle.getColor(),
                vehicle.getOdometer(),
                vehicle.getPrice());


    }

}