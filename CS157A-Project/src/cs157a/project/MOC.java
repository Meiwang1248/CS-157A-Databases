package cs157a.project;

import java.sql.*;
import java.io.*;

public class MOC {

    public static Connection getconnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            System.out.println("Creating Database..");
            conn = DriverManager.getConnection(url, "pulkit", "p");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
}
