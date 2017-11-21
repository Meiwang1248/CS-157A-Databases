package cs157a.project;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Insert {
    private Statement stmt;
    private Connection connection;

    public Insert(Statement stmt, Connection connection) {
        this.stmt = stmt;
        this.connection = connection;
    }
    public Insert(){
        
    }
    public void fillTable() {
       try {
            // Storing random generated words in an array to populate in the tables
            String[] firstName = generateRandomWords(19);
            String[] lastName = generateRandomWords(19);
            String[] titles = generateRandomWords(19);
            String[] publisherName = generateRandomWords(19);
            String seq = "authorId";
            String sequence = "Create sequence " + seq + " start with 1 increment by 1 minvalue 1 maxvalue 1000;";
            String seq2 = "publisherId";
            String sequence2 = "Create sequence " + seq2 + " start with 1 increment by 1 mivalue 1 nomaxvalue;";
            try {
                for (int i = 1; i < 19; i++) {
                    // Inserting values in relation Authors=
                    String authors = "INSERT INTO AUTHORS(authorID, firstname, lastname) values("+i+", '" + firstName[i] + "', '" + lastName[i] + "' )";
                    // Inserting values in relation Titles
                    String title = "INSERT INTO TITLES(ISBN, TITLE, EDITIONNUMBER, YEAR, PUBLISHERID, PRICE) VALUES ('ISBN" + i + " ' ,' " + titles[i] + " ' , " + (100 + i) + " , " + (1997 + i) + " , " + i + " , " + (10 + i) + ") ";
                    String publisher = "INSERT INTO PUBLISHERS(publisherId, publisherName) Values("+i+", '" + publisherName[i] + "') ";
                    String authorIsbn = "INSERT INTO authorIsbn(authorId, Isbn) values(" + i + " , ' ISBN" + i + " ' )";
                    stmt.execute(authors);
                    stmt.execute(authorIsbn);
                    stmt.execute(publisher);
                    stmt.execute(title);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }

    // Generate Random words to populate the tables
    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }
}
