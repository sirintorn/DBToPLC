package com.ese.modbus.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPostgresql {
//    private static final String DB_URL = "jdbc:postgresql://18.207.187.82:5432/mbtproduction";  //test
    private static final String DB_URL = "jdbc:postgresql://192.168.10.10:5432/mbtproduction";
//    private static final String DB_URL = "jdbc:postgresql://metalbuilding.thaiddns.com:5432/mbtproduction";

    private static final String USER = "postgres";
    private static final String PASS = "esepostgres";

    private Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this.connection;
    }

    public void closeConnectionDB(){
        try{
            this.connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

