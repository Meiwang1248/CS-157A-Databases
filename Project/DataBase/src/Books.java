import java.sql.*;
import java.sql.SQLException;

public class Books
{
    public static void main(String[] args) throws SQLException
    {
        Connection conn;
        conn = MOC.getconnection();
        try
        {
            Statement stmt = conn.createStatement();
            Tables createTable = new Tables(stmt, conn);
            createTable.createtable();

            Insert insert = new Insert(stmt, conn);
            insert.fillTable();

            System.out.println("Table populated with random values");
            // ================================================================================
            // Select all authors from the authorstable. Order the information
            // alphabetically by the author’s last name and first name.

            String query1 = "select * from authors ORDER BY firstName ASC, lastName ASC";
            ResultSet resultset = stmt.executeQuery(query1);
            while (resultset.next())
            {
                int authorId = resultset.getInt(1);
                String firstname = resultset.getString(2);
                System.out.println("AuthorId:" + authorId + " " + "FirstName:"
                        + firstname);
            }
            System.out.println(
                    "==========================================================================================");
            // ==================================================================================
            // Select all publishers from the publisherstable.

            String query2 = "select * from publishers";
            ResultSet resultset2 = stmt.executeQuery(query2);
            while (resultset2.next())
            {
                int publisherId = resultset2.getInt(1);
                String publisherName = resultset2.getString(2);
                System.out.println("Publisher Id:" + " " + publisherId
                        + " PublisherName:" + publisherName);
            }
            System.out.println(
                    "==========================================================================================");
            // =================================================================================
            // Select a specific publisher and list all books published by that
            // publisher. Include the title, yearand ISBN number. Order the
            // information alphabetically by title
            for (int i = 1; i < 19; i++)
            {
                String query3 = "Select title, isbn, year from titles where publisherId= "
                        + i + "ORDER BY title ASC";
                ResultSet resultset3 = stmt.executeQuery(query3);
                while (resultset3.next())
                {
                    String title = resultset3.getString(1);
                    String isbn = resultset3.getString(2);
                    String year = resultset3.getString(3);
                    System.out.println("Title:" + title + "ISBN: " + isbn + "Year:" + year);
                }
            }
            System.out.println(
                    "==========================================================================================");

            // ==========================================================================================
            // Add new author
            String query4 = "insert into authors(authorId, firstname, lastname) values (25, 'Pulkit', 'Agarwal') ";
            stmt.execute(query4);
            String sub = "Select firstname, lastname from authors where authorid= 25";
            ResultSet rs = stmt.executeQuery(sub);
            while (rs.next())
            {
                String lastName = rs.getString(2);
                String name = rs.getString(1);

                System.out
                        .println("Name:" + name + " " + "LastName:" + lastName);
            }
            System.out.println(
                    "==========================================================================================");
            // ===========================================================================================
            // Edit/Update the existing information about an author

            String query5 = "Update authors set firstname='Ahmed' where authorid=1";
            stmt.execute(query5);
            String subquery = "Select * from authors";
            ResultSet rs2 = stmt.executeQuery(subquery);
            while (rs2.next())
            {
                int authorId = rs2.getInt(1);
                String firstname = rs2.getString(2);
                String lastname = rs2.getString(3);
                System.out.println("AuthorId:" + authorId + " " + "FirstName:"
                        + firstname + " " + "LastName: " + lastname);
            }
            System.out.println(
                    "==========================================================================================");
            // ==================================================================================================
            // Add a new title for an author
            String query6 = "Insert into titles(isbn, title, editionNumber, price) values ('1313123', 'Murder on the orient Express', 1234, 190)";
            stmt.execute(query6);
            String sub3 = "Select * from titles";
            ResultSet rs3 = stmt.executeQuery(sub3);
            while (rs3.next())
            {
                String isbn, titles, year;
                int edition, publisher, price;
                isbn = rs3.getString(1);
                titles = rs3.getString(2);
                edition = rs3.getInt(3);
                year = rs3.getString(4);
                publisher = rs3.getInt(5);
                price = rs3.getInt(6);
                System.out.println("ISBN " + isbn + " " + "TITLES: " + titles
                        + " " + "Edition: " + edition + " " + "Year: " + year
                        + " " + "PublisherId: " + publisher + " " + "Price: "
                        + price);
            }
            System.out.println(
                    "==========================================================================================");
            // ================================================================================================
            // Add new publisher
            String query7 = "Insert into publishers(publisherId, publishername) values( 24,'Pulkit')";
            stmt.execute(query7);
            String sub4 = "Select * from publishers";
            ResultSet rs4 = stmt.executeQuery(sub4);
            while (rs4.next())
            {
                int id = rs4.getInt(1);
                String name = rs4.getString(2);
                System.out.println("ID: " + id + " " + "Name: " + name);
            }
            System.out.println(
                    "==========================================================================================");
            // ===========================================================================================
            // Edit/Update the existing information about a publisher
            String query8 = "Update publishers set publishername ='Database' where publisherid=2";
            stmt.execute(query8);
            String sub5 = "Select * from publishers";
            ResultSet rs5 = stmt.executeQuery(sub5);
            while (rs5.next())
            {
                int id = rs5.getInt(1);
                String name = rs5.getString(2);
                System.out.println("ID: " + id + " " + "Name: " + name);
            }

            String cleaningQuery = " DELETE\n" +
                    "            FROM publishers\n" +
                    "            WHERE publisherID = 24;\n" +
                    "\n" +
                    "            DELETE\n" +
                    "            FROM authors\n" +
                    "            WHERE authorID = 25;\n" +
                    "\n" +
                    "            DELETE\n" +
                    "            FROM titles\n" +
                    "            WHERE isbn = '1313123';";
            stmt.execute(cleaningQuery);
            System.out.println("\nready to go again");


            stmt.close();

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } catch (Exception e)
        {
        }
        conn.close();
    }
}
