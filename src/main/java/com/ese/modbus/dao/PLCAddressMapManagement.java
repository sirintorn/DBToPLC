package com.ese.modbus.dao;

import com.ese.modbus.bean.Machine;
import com.ese.modbus.bean.PLCAddressMap;
import com.ese.modbus.bean.Workorder;
import com.ese.modbus.connection.ConnectPostgresql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PLCAddressMapManagement {


    public List<PLCAddressMap> getPLCMapList(Machine mc){
        List<PLCAddressMap> plcMapList = new ArrayList<PLCAddressMap>();

        Statement stm = null;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";
            sql += "select * from vw_machine_plc_mapping where machine_id ="+mc.getId();


            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()) {
                PLCAddressMap plcm = new PLCAddressMap();
                plcm.setId(rs.getInt("id"));
                plcm.setInfo(rs.getString("info"));
                plcm.setModbusAddress(rs.getString("modbus_address"));
                plcm.setMachineId(rs.getInt("machine_id"));
                plcm.setMachineName(rs.getString("machine_name"));
                plcm.setIpAddress(rs.getString("ip"));
                plcm.setInsideData(rs.getString("inside_data"));
                plcMapList.add(plcm);
            }
            rs.close();
            conn.close();
            condb.closeConnectionDB();
        } catch (Exception e) {
            System.out.println(e);
        }

        return plcMapList;

    }

    public List<PLCAddressMap> getPLCList(Machine mc, String job){
        List<PLCAddressMap> plcMapList = new ArrayList<PLCAddressMap>();


        System.out.println("getPLCList "+mc.getId()+" "+job);
        Statement stm = null;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";
            sql += "select * from vw_machine_plc_mapping ";
            sql += " where machine_id ="+mc.getId();
            sql += " and info like '"+job+"%'";
            sql += " and inside_data = ''";

            System.out.println("sql "+sql);

            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()) {
                PLCAddressMap plcm = new PLCAddressMap();

                plcm.setId(rs.getInt("id"));
                plcm.setInfo(rs.getString("info"));
                plcm.setModbusAddress(rs.getString("modbus_address"));
                plcm.setMachineId(rs.getInt("machine_id"));
                plcm.setMachineName(rs.getString("machine_name"));
                plcm.setIpAddress(rs.getString("ip"));
                plcm.setInsideData(rs.getString("inside_data"));

                plcMapList.add(plcm);
            }
            rs.close();
            conn.close();
            condb.closeConnectionDB();
        } catch (Exception e) {
            System.out.println(e);
        }

        return plcMapList;

    }

    public String getFreeAddress(Machine mc){
        String job ="" ;

        Statement stm = null;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";
            sql += "select distinct substring(info,0,5) as job from vw_machine_plc_mapping ";
            sql += " where machine_id = "+mc.getId();
            sql += " and info like '%header' and inside_data = ''";
            sql += " limit 1";


            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()) {
                job = rs.getString("job");
            }
            rs.close();
            conn.close();
            condb.closeConnectionDB();
        } catch (Exception e) {
            System.out.println(e);
        }

        return job;

    }
}
