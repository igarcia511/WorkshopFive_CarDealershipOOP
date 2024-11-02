package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ContractFileManager {
    public static void saveContract(Contract contract){

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv", true));
            if(contract instanceof SalesContract){
                SalesContract sale = (SalesContract) contract;
                String salesContractToAdd = sale.toString();
                bufferedWriter.write(salesContractToAdd + "\n");
            } else {
                LeaseContract lease = (LeaseContract) contract;
                String leaseContractToAdd = lease.toString();
                bufferedWriter.write(leaseContractToAdd + "\n");
            }
          bufferedWriter.close();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }
}
