import java.sql.*;

public class testDriver {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String hostName = "xxxxx";
        String dbName = "petersqldb";
        String user = "peter@xxxx";
        String password = "xxxxxxxx";
        String url = String.format("jdbc:sqlserver://%s.database.windows.net:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select 1+1 as sum");
        while(rs.next()) {
            System.out.println(rs.getInt("sum"));
        }
        rs.close();
        stat.close();
        conn.close();
    }
}
