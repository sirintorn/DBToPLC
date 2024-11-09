package com.ese.modbus.control;

import com.ese.modbus.bean.Machine;
import com.ese.modbus.dao.MachineManagement;

import java.util.ArrayList;
import java.util.List;

public class AllMachineWork {

    List<Machine> machineList = new ArrayList<Machine>();
    MachineManagement mm = new MachineManagement();

    public void getAllMachine(){
        machineList = mm.getMachineList();
        assignAllMachineWork();
        System.out.println("getAllMachine()");
    }

    public void assignAllMachineWork(){
        for(int loop = 0; loop<machineList.size() ; loop++){
            Machine machine = new Machine();
            machine = machineList.get(loop);
            AssignWorkOrderToPLC assignWorkOrderToPLC = new AssignWorkOrderToPLC(machine);
            System.out.println("assignAllMachineWork()");

        }
    }
}
