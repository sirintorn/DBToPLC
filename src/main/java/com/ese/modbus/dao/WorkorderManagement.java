package com.ese.modbus.dao;

import com.ese.modbus.bean.*;
import com.ese.modbus.connection.ConnectPostgresql;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.jdbc.Work;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkorderManagement {




    public List<Workorder> getWorkorderList(Machine mc) {
        List<Workorder> workorderList = new ArrayList<Workorder>();

        Statement stm = null;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";
            sql += "select * from workorder where machine_id ="+mc.getId()+" and status = 3 order by id limit 1";

//            System.out.println("sql "+sql);

            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()) {
                Workorder workorder = new Workorder();
                workorder.setId(rs.getInt("id"));
                workorder.setPrqId(rs.getString("prq_id"));
                workorder.setTotalMeter(rs.getDouble("total_meter"));
                workorderList.add(workorder);
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
