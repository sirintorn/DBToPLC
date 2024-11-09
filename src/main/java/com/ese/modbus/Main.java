package com.ese.modbus;

import com.ese.modbus.control.AllMachineWork;
import com.ghgande.j2mod.modbus.Modbus;

public class Main {
    public static void main(String[] args) {

        AllMachineWork amw = new AllMachineWork();
        amw.getAllMachine();
    }
}