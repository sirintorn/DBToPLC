package com.ese.modbus.dao;


import com.ese.modbus.bean.Machine;
import com.ese.modbus.connection.ConnectPostgresql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MachineManagement {
//    Logger logger = LogManager.getLogger(String.valueOf(this.getClass()));


    public List<Machine> getMachineList(){
        List<Machine> machineList = new ArrayList<Machine>();

        Statement stm = null;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";
            sql += "select * from machine where is_valid = true";

            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()) {
                Machine mc = new Machine();
                mc.setId(rs.getInt("id"));
                mc.setName(rs.getString("name"));
                mc.setIpAddress(rs.getString("ip_address"));
                mc.setMountPath(rs.getString("mount_path"));
                machineList.add(mc);
            }
            rs.close();
            conn.close();
            condb.closeConnectionDB();
        } catch (Exception e) {
            System.out.println(e);
        }

        return machineList;

    }
}
