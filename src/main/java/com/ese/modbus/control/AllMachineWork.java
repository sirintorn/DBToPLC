package com.ese.modbus.control;

import com.ese.modbus.Main;
import com.ese.modbus.bean.Machine;
import com.ese.modbus.dao.MachineManagement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

public class AllMachineWork {


    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    List<Machine> machineList = new ArrayList<Machine>();
    MachineManagement mm = new MachineManagement();

    public void getAllMachine(){
        machineList = mm.getMachineList();
        assignAllMachineWork();
        logger.info("");

    }

    public void assignAllMachineWork(){
        for(int loop = 0; loop<machineList.size() ; loop++){
            Machine machine = new Machine();
            machine = machineList.get(loop);
            AssignWorkOrderToPLC assignWorkOrderToPLC = new AssignWorkOrderToPLC(machine);
            logger.info("");
        }
    }
}
