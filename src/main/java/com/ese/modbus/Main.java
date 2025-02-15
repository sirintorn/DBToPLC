package com.ese.modbus;

import com.ese.modbus.control.AllMachineWork;
import com.ghgande.j2mod.modbus.Modbus;

import java.io.InputStream;
import java.util.Date;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {

        try (
                InputStream configFile = Main.class.getClassLoader().getResourceAsStream("logging.properties")) {
            if (configFile != null) {
                LogManager.getLogManager().readConfiguration(configFile);
                System.out.println("Logging configuration loaded successfully.");
            } else {
                System.err.println("Could not find logging.properties in src/main/resources.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(new Date().getHours() >7 && new Date().getHours() < 21) {    //Remark By P.Sirintorn limited time

            AllMachineWork amw = new AllMachineWork();
            amw.getAllMachine();
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
}