package com.ese.modbus.control;

import com.ese.modbus.Main;
import com.ese.modbus.bean.*;
import com.ese.modbus.dao.PLCAddressMapManagement;
import com.ese.modbus.dao.WorkorderFgManagement;
import com.ese.modbus.dao.WorkorderManagement;
import com.ese.modbus.dao.WorkorderMaterialManagement;
import com.ese.modbus.io.ModbusStringClient;
import com.ghgande.j2mod.modbus.Modbus;
import org.hibernate.jdbc.Work;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssignWorkOrderToPLC {
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    Workorder wo;
    List<Workorder> woList = new ArrayList<Workorder>();
    WorkorderManagement wom = new WorkorderManagement();

    PLCAddressMapManagement pam = new PLCAddressMapManagement();
    List<PLCAddressMap> plcMapList = new ArrayList<PLCAddressMap>();

    List<WorkorderFg> woFgList = new ArrayList<WorkorderFg>();
    WorkorderFgManagement wofgM = new WorkorderFgManagement();

    List<WorkorderMaterial> woMatList = new ArrayList<WorkorderMaterial>();
    WorkorderMaterialManagement womm = new WorkorderMaterialManagement();

    String totalLenght = "";
    Machine machine;

    public AssignWorkOrderToPLC(Machine machine) {

        this.machine = machine;
//        while (true){
            getWorkorder();

            logger.info("woList Size " + woList.size());
//        for(int i = 0; i < woList.size(); i++){   //for many workorder

            if(woList.size() > 0){
            for (int i = 0; i < 1; i++) {
                wo = woList.get(i);
                    String job = pam.getFreeAddress(machine);
                    logger.info("Job " + job);

                    plcMapList = pam.getPLCList(machine, job);
                    woFgList = wofgM.getWorkorderFG(woList.get(i));
                    woMatList = womm.getWorkorderFGList(wo);

                    logger.info("woFgList Size " + woFgList.size());
                    logger.info("plcMapList Size " + plcMapList.size());

                    for (int j = 0; j <= woFgList.size(); j++) {
                        WorkorderFg wofg = new WorkorderFg();

                        String dataSet = "";
                        if (j == 0) {
                            dataSet = setHeader();
//                }else if(j == 1){
//                    dataSet = setSpecoil();
//                }else if(j == 2){
//                    dataSet = setTotalItem();
//                }else if(j == 3){
//                    dataSet = setTotalLength();
//                }else if(j == 4){
//                    dataSet = setFlag();
                        } else {
                            wofg = woFgList.get(j - 1);
                            dataSet = setJobList(wofg);
                        }
                        logger.info("dataSet " + dataSet +" PLCMapList "+plcMapList.get(j).getInfo()+"  "+Integer.parseInt(plcMapList.get(j).getModbusAddress()));

//                System.out.println("address "+Integer.parseInt(plcMapList.get(j).getModbusAddress()));

//                sendToModbusServer(machine.getIpAddress(), Integer.parseInt(plcMapList.get(j).getModbusAddress()), dataSet);  //Remark NOV272024

                        sendToModbusServer(machine.getIpAddress(), Integer.parseInt(plcMapList.get(j).getModbusAddress()), dataSet);



//                sendToModbusServer("127.0.0.1", Integer.parseInt(plcMapList.get(j).getModbusAddress()), dataSet);   //test

                    }

                    //send total lenght to modbus server Address 406020-6784
//            sendToModbusServer(machine.getIpAddress(), 400236, wo.getTotalMeter().toString());    //Remark DEC012024
//            patchWorkorderStatus();   //update workorder status with api
//                    wo.setStatus(4);  //DEC132024
//                    wom.updateWorkorderStatus(wo);  //update workorder status without api
                }

            }


    }

    public void sendToModbusServer(String ip, int modbusAddress, String data) {
        ModbusStringClient client = new ModbusStringClient(ip, Modbus.DEFAULT_PORT, modbusAddress, data); // Replace with your PLC IP


        try {
            client.connect();

            // Example: Write a string to starting register address
            // modbus address 401001-6784 = 394217
            //client.writeString(394217, "2506PR00013400IM-AL-3540-914-150-55006052411");
            int addr = modbusAddress;   //SEP212024
            logger.info("Modbus Address " + addr);
            client.writeString(addr, data);   //Remark Send Data
 /*         // Clear All Data (Job Lise, Item List)
            client.writeString(394217,  "                                                                                            ");
            client.writeString(394239,  "                                                                                            ");
            client.writeString(394254,  "                                                                                            ");
            client.writeString(394269,  "                                                                                            ");
            client.writeString(394284,  "                                                                                            ");
            client.writeString(394299,  "                                                                                            ");
            client.writeString(394314,  "                                                                                            ");
            client.writeString(394329,  "                                                                                            ");
            client.writeString(394344,  "                                                                                            ");
            client.writeString(394359,  "                                                                                            ");
            client.writeString(394374,  "                                                                                            ");
            client.writeString(394387,  "                                                                                            ");
            client.writeString(394401,  "                                                                                            ");
            client.writeString(394415,  "                                                                                            ");
            client.writeString(394429,  "                                                                                            ");
            client.writeString(394443,  "                                                                                            ");
            client.writeString(394457,  "                                                                                            ");
            client.writeString(394471,  "                                                                                            ");
            client.writeString(394485,  "                                                                                            ");
            client.writeString(394499,  "                                                                                            ");
            client.writeString(394513,  "                                                                                            ");
            client.writeString(394526,  "                                                                                            ");
            client.writeString(394540,  "                                                                                            ");
            client.writeString(394554,  "                                                                                            ");
            client.writeString(394568,  "                                                                                            ");
            client.writeString(394582,  "                                                                                            ");
            client.writeString(394596,  "                                                                                            ");
            client.writeString(394610,  "                                                                                            ");
            client.writeString(394624,  "                                                                                            ");
            client.writeString(394638,  "                                                                                            ");
            client.writeString(394652,  "                                                                                            ");
            client.writeString(400236,  "                                                                                            ");
*/

            String readStr = client.readString(addr, data.length()); // Adjust the register count based on the length of the string
//            String readStr = client.readString(399217, 200); // for force data
            logger.info("data.length() "+data.length()+" Address " + addr + " Read String: " + readStr);

            client.disconnect();
//            if(readStr.equals(data)){
                wo.setStatus(4);
                wom.updateWorkorderStatus(wo);  //update workorder status without api
//            }

        } catch (Exception e) {
            logger.info(e.toString());
        }


    }

    public boolean patchWorkorderStatus() {
        boolean result = false;
//        String url = "https://api.example.com/resource/1";
        String url = "http://metalbuilding.thaiddns.com:8080/MBTService/api/patchWorkorderStatus/";
        try {
            // Open connection
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            // Set request method to PATCH
            connection.setRequestMethod("PATCH");

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer your-token-here");

            // Enable output to send request body
            connection.setDoOutput(true);

            // JSON body for partial update
//            String jsonInputString = "{\"age\": 36}";ß
            String jsonInputString = "{\"workOrderId\": " + wo.getId() + ",\"stakeholderId\": 3,\"status\":4}";

            // Write request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            connection.disconnect();

        } catch (Exception e) {
            logger.info(e.toString());
        }
        return result;
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
        logger.info("Total Item "+totalItem);

        totalLenght = wo.getTotalMeter().toString();
        sendToModbusServer(machine.getIpAddress(), 399116, totalLenght);    //Remark JAN192024 Fix Modbus Address for Total Lenght

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

        logger.info("prqId "+prqId.length()+" specCoil "+specCoil.length()+" totalItem "+totalItem.length()+" totalLenght"+totalLenght+ "Flag "+tFlag+" gFlag "+gFlag);
        header = prqId+specCoil+totalItem+totalLenght+tFlag+gFlag;
//        header = "                                                                     "; //Clear Data

        logger.info("header "+header.length()+" "+header);


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
        fgLength = fgLength.substring(0, fgLength.length() - 2);

//        System.out.println("lenght "+fgLength.length());
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

//        System.out.println("fgSpec "+fgSpec.length()+" fgLength "+fgLength.length()+" qty "+qty.length());
        jobList = fgSpec+fgLength+qty;
//Clear Datas
//        jobList = "                                                                                                                                                                                                                                                                                                                                                                                    ";
//        System.out.println("jobList "+jobList);
        return jobList;
    }

}
