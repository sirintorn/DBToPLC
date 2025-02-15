package com.ese.modbus.dao;

import com.ese.modbus.Main;
import com.ese.modbus.bean.Machine;
import com.ese.modbus.bean.Workorder;
import com.ese.modbus.bean.WorkorderFg;
import com.ese.modbus.connection.ConnectPostgresql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkorderFgManagement {
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    public List<WorkorderFg> getWorkorderFG(Workorder wo) {
        List<WorkorderFg> workorderList = new ArrayList<WorkorderFg>();

        Statement stm = null;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";
//            sql += "select * from workorder_fg where workorder_id ="+wo.getId() + " and is_send = false order by fg_lenght desc limit 5";
            sql += "select * from workorder_fg where workorder_id ="+wo.getId() + " and is_send = false order by fg_lenght desc limit 10"; // Remark Send data without limited

            logger.info(sql);

            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()) {
                WorkorderFg fg = new WorkorderFg();
                fg.setId(rs.getInt("id"));
                fg.setFgSpec(rs.getString("fg_spec"));
                fg.setFgLenght(rs.getDouble("fg_lenght"));
                fg.setFgQty(rs.getInt("fg_qty"));
                workorderList.add(fg);
            }
            rs.close();
            conn.close();
            condb.closeConnectionDB();
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return workorderList;
    }
}
