
import java.sql.*;
import java.util.Random;

public class Tables
{

    // Statement and connection from Java.sql to execute the tables
    private Statement stmt;
    private Connection connection;

    // Initalizing the statement and connection with our localhost
    public Tables(Statement stmt, Connection conn)
    {
        this.stmt = stmt;
        this.connection = conn;
    }

    // Function to create the four table provided
    public void createtable()
    {
        // Creating table Author
        String Table1 = "CREATE TABLE authors( "
                + " authorID integer not null, " + " firstName varchar(200), "
                + " lastName varchar(200), "
                + " CONSTRAINT constraint_pk_authorID PRIMARY KEY(authorID))";
        String Table2 = "CREATE TABLE authorISBN( " + "authorID INTEGER, "
                + "isbn CHAR(10), "
                + "CONSTRAINT constraint_fk_authorId FOREIGN KEY(authorID) REFERENCES authors(authorID)) ";
        // Creating table Publishers
        String Table3 = "CREATE TABLE publishers( "
                + " publisherID integer not NULL, "
                + " publisherName char(100), "
                + " CONSTRAINT constraint_pk_publisherId PRIMARY KEY ( publisherID))";
        // Creating table Titles
        // Creating table publishers before titles so that foreign key
        // constraint can be applied to titles
        String Table4 = "CREATE TABLE titles( " + " isbn CHAR(20), "
                + " title VARCHAR(100), " + " editionNumber integer, "
                + " Year CHAR(4), " + " publisherID integer, "
                + " price INTEGER, "
                + " CONSTRAINT constraint_pk_isbn PRIMARY KEY (isbn), "
                + " CONSTRAINT constraint_fk_publisherId FOREIGN KEY ( publisherID ) REFERENCES publishers( publisherID ))";
        // Creating table AuthorISBN
        // Creating table titles before authorISBN so that foreign key constaint
        // can be applied to authorISBN
        // or else error will be produced

        try
        {
            // Executes the given tables to add tables to Database
            stmt.executeUpdate(Table1);
            stmt.executeUpdate(Table3);
            stmt.executeUpdate(Table4);
            stmt.executeUpdate(Table2);
        } catch (SQLException se)
        {
            // Catches if there are exceptions raised by SQL
            se.printStackTrace();
        } catch (Exception e)
        {
            // Catches any Java Exceptions
            e.printStackTrace();
        }
    }

}
