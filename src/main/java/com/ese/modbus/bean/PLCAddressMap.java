package com.ese.modbus.bean;

public class PLCAddressMap {

    private int id;
    private String info;
    private String modbusAddress;
    private int machineId;
    private String machineName;
    private String ipAddress;
    private String insideData;

    public PLCAddressMap() {
    }

    public PLCAddressMap(int id, String info, String modbusAddress, int machineId, String machineName, String ipAddress, String insideData) {
        this.id = id;
        this.info = info;
        this.modbusAddress = modbusAddress;
        this.machineId = machineId;
        this.machineName = machineName;
        this.ipAddress = ipAddress;
        this.insideData = insideData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getModbusAddress() {
        return modbusAddress;
    }

    public void setModbusAddress(String modbusAddress) {
        this.modbusAddress = modbusAddress;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getInsideData() {
        return insideData;
    }

    public void setInsideData(String insideData) {
        this.insideData = insideData;
    }
}
