package com.ese.modbus.control;

import com.ese.modbus.bean.*;
import com.ese.modbus.dao.PLCAddressMapManagement;
import com.ese.modbus.dao.WorkorderFgManagement;
import com.ese.modbus.dao.WorkorderManagement;
import com.ese.modbus.dao.WorkorderMaterialManagement;
import com.ese.modbus.io.ModbusStringClient;
import com.ghgande.j2mod.modbus.Modbus;
import org.hibernate.jdbc.Work;

import java.util.ArrayList;
import java.util.List;

public class AssignWorkOrderToPLC {

    Workorder wo;
    List<Workorder> woList = new ArrayList<Workorder>();
    WorkorderManagement wom = new WorkorderManagement();

    PLCAddressMapManagement pam = new PLCAddressMapManagement();
    List<PLCAddressMap> plcMapList = new ArrayList<PLCAddressMap>();

    List<WorkorderFg> woFgList = new ArrayList<WorkorderFg>();
    WorkorderFgManagement wofgM = new WorkorderFgManagement();

    List<WorkorderMaterial> woMatList = new ArrayList<WorkorderMaterial>();
    WorkorderMaterialManagement womm = new WorkorderMaterialManagement();

    Machine machine;

    public AssignWorkOrderToPLC(Machine machine){
        System.out.println("AssignWorkOrderToPLC");
        this.machine = machine;
        getWorkorder();
        System.out.println("woList Size "+woList.size());

//        for(int i = 0; i < woList.size(); i++){   //for many workorder
        for(int i = 0; i < 1; i++){
            wo = woList.get(i);
            String job = pam.getFreeAddress(machine);
            System.out.println("Job "+job);
            plcMapList = pam.getPLCList(machine, job);
            woFgList = wofgM.getWorkorderFG(woList.get(i));
            woMatList = womm.getWorkorderFGList(wo);

            System.out.println("woFgList Size "+woFgList.size());
            System.out.println("plcMapList Size "+plcMapList.size());

            for(int j = 0; j <= woFgList.size(); j++){
                WorkorderFg wofg = new WorkorderFg();

                String dataSet = "";
                if(j == 0){
                    dataSet = setHeader();
//                }else if(j == 1){
//                    dataSet = setSpecoil();
//                }else if(j == 2){
//                    dataSet = setTotalItem();
//                }else if(j == 3){
//                    dataSet = setTotalLength();
//                }else if(j == 4){
//                    dataSet = setFlag();
                }else{
                    wofg = woFgList.get(j-1);
                    dataSet = setJobList(wofg);
                }
                System.out.println("dataSet "+dataSet);


                sendToModbusServer(machine.getIpAddress(), Integer.parseInt(plcMapList.get(j).getModbusAddress()), dataSet);
//                sendToModbusServer("127.0.0.1", Integer.parseInt(plcMapList.get(j).getModbusAddress()), dataSet);   //test

            }

            //send total lenght to modbus server Address 406020-6784
            sendToModbusServer(machine.getIpAddress(), 400236, wo.getTotalMeter().toString());
        }
    }

    public void sendToModbusServer(String ip, int modbusAddress, String data){
        System.out.println("sendToModbusServer");
        ModbusStringClient client = new ModbusStringClient(ip, Modbus.DEFAULT_PORT, modbusAddress, data); // Replace with your PLC IP

        try {
            client.connect();

            // Example: Write a string to starting register address
            // modbus address 401001-6784 = 394217
            //client.writeString(394217, "2506PR00013400IM-AL-3540-914-150-55006052411");
            int addr = modbusAddress;   //SEP212024
            System.out.println("Modbus Address "+addr);
            client.writeString(addr, data);   //SEP212024

            String readStr = client.readString(addr, data.length()); // Adjust the register count based on the length of the string
//            String readStr = client.readString(399217, 200); // for force data
            System.out.println("Address "+addr+" Read String: " + readStr);

            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void getWorkorder(){
        woList = wom.getWorkorderList(machine);
    }

    public String setHeader(){
        String header = "";
        String prqId = wo.getPrqId();
        if(prqId.length()<12){
        }

        String specCoil = woMatList.get(0).getSpecoilCode();
        if(specCoil.length() <24){
            switch (specCoil.length()) {
                case 23:
                    specCoil = " " + specCoil;
                    break;

                case 22:
                    specCoil = "  " + specCoil;
                    break;
                case 21:
                    specCoil = "   " + specCoil;
                    break;
                case 20:
                    specCoil = "    " + specCoil;
                    break;
                case 19:
                    specCoil = "     " + specCoil;
                    break;
                case 18:
                    specCoil = "      " + specCoil;
                    break;
                case 17:
                    specCoil = "       " + specCoil;
                    break;
                case 16:
                    specCoil = "        " + specCoil;
                    break;
                case 15:
                    specCoil = "         " + specCoil;
                    break;
            }
        }

        String totalItem =  woFgList.size()+"";

        if(totalItem.length() < 2){
            totalItem = "0"+totalItem;
        }
        System.out.println("Total Item "+totalItem);

        String totalLenght = wo.getTotalMeter().toString();
        if(totalLenght.length() <4) {
            switch (totalLenght.length()) {
                case 3:
                    totalLenght = " "+totalLenght;
                    break;

                case 2:
                    totalLenght = "  "+totalLenght;
                    break;

                case 1:
                    totalLenght = "   "+totalLenght;
                    break;
            }
        }else if(totalLenght.length() > 4) {
            totalLenght = totalLenght.substring(0, 4);
        }
        String tFlag = "1";
        String gFlag = "1";

        System.out.println("prqId "+prqId.length()+" specCoil "+specCoil.length()+" totalItem "+totalItem.length()+" tFlag "+tFlag+" gFlag "+gFlag);
        header = prqId+specCoil+totalItem+totalLenght+tFlag+gFlag;
//        header = "                                                                     "; //Clear Data

        System.out.println("header "+header.length()+" "+header);

        return header;
    }



    public String setJobList(WorkorderFg woFg){
        String jobList = "";
        String fgSpec = woFg.getFgSpec();
        if(fgSpec.length() < 20){
            switch (fgSpec.length()) {
                case 19:
                    fgSpec = fgSpec+" ";
                    break;

                case 18:
                    fgSpec = fgSpec+"  ";
                    break;

                case 17:
                    fgSpec = fgSpec+"   ";
                    break;
                case 16:
                    fgSpec = fgSpec+"    ";
                    break;
            }
        }

        double fgL = woFg.getFgLenght()*1000;
        String fgLength = fgL+"";
//        String replaceSpace = " ";
//        fgLength = fgLength.replace(".", replaceSpace);

        if(fgLength.length() == 5){
            fgLength = " "+fgLength;
        }else if (fgLength.length() == 4){
            fgLength = "  "+fgLength;
        }else if (fgLength.length() == 3){
            fgLength = "   "+fgLength;
        }else if (fgLength.length() == 2){
            fgLength = "    "+fgLength;
        }else if (fgLength.length() == 1){
            fgLength = "     "+fgLength;
        }

        String qty = woFg.getFgQty().toString();
        if(qty.length() == 3){
            qty = " "+qty;
        }else if(qty.length() == 2){
            qty = "  "+qty;
        }else if(qty.length() == 1){
            qty = "   "+qty;
        }

        System.out.println("fgSpec "+fgSpec.length()+" fgLength "+fgLength.length()+" qty "+qty.length());
        jobList = fgSpec+fgLength+qty;
//Clear Datas
//        jobList = "                                                                                                                                                                                                                                                                                                                                                                                    ";
        System.out.println("jobList "+jobList);
        return jobList;
    }

}
