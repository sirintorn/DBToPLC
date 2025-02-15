package com.ese.modbus.dao;

import com.ese.modbus.Main;
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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkorderManagement {


    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());


    public List<Workorder> getWorkorderList(Machine mc) {
        List<Workorder> workorderList = new ArrayList<Workorder>();

        Statement stm = null;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";
            sql += "select * from workorder where machine_id =" + mc.getId() + " and status = 3 order by id limit 1";

            logger.info(sql);

            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
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
            logger.info(e.toString());
        }
        return workorderList;
    }

    public int updateWorkorderStatus(Workorder workorder) {
        Statement stm = null;
        int rowsUpdated = 0;
        try {
            ConnectPostgresql condb = new ConnectPostgresql();
            Connection conn = condb.getConnection();

            Statement stat = conn.createStatement();
            String sql = "";

            logger.info(sql);
            sql += "UPDATE workorder SET status = ?, modify_datetime = now() WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workorder.getStatus());
            pstmt.setInt(2, workorder.getId());

            rowsUpdated = pstmt.executeUpdate();
            conn.close();
            condb.closeConnectionDB();
        } catch (SQLException e) {
            e.printStackTrace();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsUpdated;

    }
}