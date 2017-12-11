
import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class MOC
{
    private static String password;
    public static Connection getconnection()
    {
        System.out.print("Enter the password: ");
        Scanner reader = new Scanner(System.in);
        password = reader.nextLine();

        String connectionString =
                "jdbc:sqlserver://cthuff.database.windows.net:1433;"
                        +"database=CS157a;"
                        +"user=craigery@cthuff;" +
                        "password=" + password + 
                        ";encrypt=true;" +
                        "trustServerCertificate=false;" +
                        "hostNameInCertificate=*.database.windows.net;" +
                        "loginTimeout=30;";
        // Declare the JDBC objects.
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;

    }
}
