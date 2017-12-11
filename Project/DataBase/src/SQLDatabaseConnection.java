// Use the JDBC driver  
import java.sql.*;



public class SQLDatabaseConnection {

    public static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String connectionString =
                "jdbc:sqlserver://cthuff.database.windows.net:1433;"
                        +"database=CS157a;"
                        +"user=craigery@cthuff;" +
                        "password=huff*SQLdatabase;" +
                        "encrypt=true;" +
                        "trustServerCertificate=false;" +
                        "hostNameInCertificate=*.database.windows.net;" +
                        "loginTimeout=30;";

        // Declare the JDBC objects.
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement prepsInsertProduct = null;

        try {
            connection = DriverManager.getConnection(connectionString);

            // Create and execute an INSERT SQL prepared statement.
            String insertSql = "INSERT INTO dbo.Product (name, ProductNumber, Color, StandardCost, ListPrice, SellStartDate) VALUES "
                    + "('NewBike', 'BikeNew', 'Blue', 50, 120, '2016-01-01');";

            prepsInsertProduct = connection.prepareStatement(
                    insertSql,
                    Statement.RETURN_GENERATED_KEYS);
            prepsInsertProduct.execute();

            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("this didn't work");
        }
        finally {
            // Close the connections after the data has been handled.
            if (prepsInsertProduct != null) try { prepsInsertProduct.close(); } catch(Exception e) {}
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
            if (statement != null) try { statement.close(); } catch(Exception e) {}
            if (connection != null) try { connection.close(); } catch(Exception e) {}
        }
    }
}