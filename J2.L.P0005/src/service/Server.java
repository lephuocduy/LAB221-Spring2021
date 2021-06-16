/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Le Phuoc Duy
 */
public class Server {

    public static void main(String[] args) {
        String serviceName = "rmi://localhost:1098/FamilyHealthcareService";
        RegistrationServer service = null;
        try {
            service = new RegistrationServer();
            Registry r = LocateRegistry.createRegistry(1098);
            Naming.rebind(serviceName, service);
            System.out.println("Service: " + serviceName + " is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
