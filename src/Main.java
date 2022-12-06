import java.sql.*;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Main {
    public static void main(String[] args) {
        selectAll();
    }
        private static Connection connect() {
            // SQLite connection string
            String url = "jdbc:sqlite:C:\\Users\\arman\\stadiums.db";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }

    private static void selectAll(){
        String sql = "SELECT * FROM stadiums";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("stadiumId") +  "\t" +
                        rs.getString("stadiumName") + "\t" +
                        rs.getString("stadiumCity") + "\t" +
                        rs.getString("stadiumCapacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}
}