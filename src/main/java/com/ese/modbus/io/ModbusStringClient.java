package com.ese.modbus.io;


import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.facade.ModbusTCPMaster;
import com.ghgande.j2mod.modbus.procimg.Register;
import com.ghgande.j2mod.modbus.procimg.SimpleRegister;

public class ModbusStringClient {

    private ModbusTCPMaster modbusMaster;
    private int plcAddress;
    private String data;

    public ModbusStringClient(String ip, int port,int plcAddress, String data) {
        modbusMaster = new ModbusTCPMaster(ip, port);
        this.plcAddress = plcAddress;
        this.data = data;
    }

    public void connect() throws Exception {
        modbusMaster.connect();
    }

    public void disconnect() {
        modbusMaster.disconnect();
    }

    public void writeString(int ref, String str) throws ModbusException {
        byte[] strBytes = str.getBytes();
        int registerCount = (strBytes.length + 1) / 2;
        Register[] registers = new Register[registerCount];

        for (int i = 0; i < registerCount; i++) {
//            int byte1 = (i * 2 < strBytes.length) ? (strBytes[i * 2] & 0xFF) : 0;   //default
            int byte1 = (i * 2 + 1 < strBytes.length) ? (strBytes[i * 2 + 1] & 0xFF) : 0;

//            int byte2 = (i * 2 + 1 < strBytes.length) ? (strBytes[i * 2 + 1] & 0xFF) : 0;      //default
            int byte2 = (i * 2 < strBytes.length) ? (strBytes[i * 2] & 0xFF) : 0;

            int value = (byte1 << 8) | byte2;
            registers[i] = new SimpleRegister(value);
        }

        modbusMaster.writeMultipleRegisters(ref, registers);
    }

    public String readString(int ref, int registerCount) throws ModbusException {
        Register[] registers = modbusMaster.readMultipleRegisters(ref, registerCount);
        byte[] resultBytes = new byte[registerCount * 2];

        for (int i = 0; i < registerCount; i++) {
            int value = registers[i].getValue();
            resultBytes[i * 2] = (byte) (value & 0xFF);
            resultBytes[i * 2 + 1] = (byte) (value >> 8);
        }

        return new String(resultBytes).trim();
    }


}