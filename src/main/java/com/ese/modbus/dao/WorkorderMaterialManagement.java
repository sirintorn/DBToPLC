package com.ese.modbus.dao;

import com.ese.modbus.bean.Workorder;
import com.ese.modbus.bean.WorkorderMaterial;
import com.ese.modbus.connection.ConnectPostgresql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkorderMaterialManagement {

    public List<WorkorderMaterial> getWorkorderFGList(Workorder wo) {
        List<WorkorderMaterial> workorderList = new ArrayList<WorkorderMaterial>();

        Statement stm = null;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";
            sql += "select * from workorder_material where workorder_id ="+wo.getId();

            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()) {
                WorkorderMaterial fg = new WorkorderMaterial();
                fg.setId(rs.getInt("id"));
                fg.setSpecoilCode(rs.getString("specoil_code"));
                workorderList.add(fg);
            }
            rs.close();
            conn.close();
            condb.closeConnectionDB();
        } catch (Exception e) {
            System.out.println(e);
        }
        return workorderList;
    }
}
