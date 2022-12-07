import java.sql.*;
import java.util.Scanner;


public class Main {
    private static Connection connect() {
        String url = "jdbc:sqlite:C:\\Users\\arman\\stadiums.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean quit = false;
        printMenu();

        while (!quit) {
            System.out.println("\n" +
                    "Please chose a number from the menu (Press 7 to se the Menu again)");
            int userchoise = sc.nextInt();
            sc.nextLine();

            switch (userchoise) {

                case 0:
                    System.out.println("\n you have exit the Menu");
                    quit = true;
                    break;

                case 1:
                    selectAllStadiums();

                    break;

                case 2:
                    createStadium();

                    break;

                case 3:
                    System.out.println("Enter Stadium name: " );
                    String inputName = sc.nextLine();

                    System.out.println("Enter Stadium city");
                    String inputCity = sc.nextLine();

                    System.out.println("Enter Stadium capacity");
                    int inputCapacity = sc.nextInt();

                    System.out.println("Enter Id number you want to update");
                    int inputId = sc.nextInt();

                    update(inputName, inputCity, inputCapacity, inputId);
                    break;

                case 4:
                    deleteStadium();
                    break;

                case 5:
                    searchStadiums();
                    break;

                case 6:
                    showingStadiums();
                    break;
                case 7:
                    printMenu();
                    break;
            }

      }

    }

    public static void printMenu() {
        System.out.println("\nMENU:\n");
        System.out.println("0  - Exit\n" +
                "1  - show all the Stadiums\n" +
                "2  - Add new Stadium\n" +
                "3  - Update \n" +
                "4  - Delete\n" +
                "5  - Search Stadium\n" +
                "6  - Show how many Stadiums\n ");
    }

    private static void selectAllStadiums() {
        String sql = "SELECT * FROM stadiums";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("stadiumId") + "\t" +
                        rs.getString("stadiumName") + "\t" +
                        rs.getString("stadiumCity") + "\t" +
                        rs.getString("stadiumCapacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createStadium() {
        System.out.println("Enter Stadium Id: ");
        int inputId = sc.nextInt();
        String xbug = sc.nextLine();

        System.out.println("Enter the name Stadium: ");
        String inputName = sc.nextLine();

        System.out.println("Enter the city Stadium: ");
        String inputCity = sc.nextLine();

        System.out.println("Enter stadium capacity: ");
        int inputCapacity = sc.nextInt();
        String xybug = sc.nextLine();

        insertStadium(inputId, inputName, inputCity, inputCapacity);
        sc.nextLine();
    }


    public static void insertStadium( int stadiumId, String stadiumName, String stadiumCity, int stadiumCapacity) {
        String sql = "INSERT INTO stadiums(stadiumId, stadiumName, stadiumCity, stadiumCapacity) VALUES(?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stadiumId);
            pstmt.setString(2, stadiumName);
            pstmt.setString(3, stadiumCity);
            pstmt.setInt(4, stadiumCapacity);
            System.out.println("You have added a new stadium");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStadium() {
        System.out.println("chose the stadium Id-number to delete: ");
        int inputId = sc.nextInt();
        delete(inputId);
        sc.nextLine();
    }

    private static void delete(int id) {
        String sql = "DELETE FROM stadiums WHERE stadiumId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("You have deleted one Stadium");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(String stadiumName, String stadiumCity, int stadiumCapacity, int stadiumId) {

        String sql = "UPDATE stadiums SET stadiumName = ? , "
                + "stadiumCity = ? , "
                + "stadiumCapacity = ? "
                + "WHERE stadiumId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, stadiumName);
            pstmt.setString(2, stadiumCity);
            pstmt.setInt(3, stadiumCapacity);
            pstmt.setInt(4, stadiumId);
            // update
            pstmt.executeUpdate();
            System.out.println("You have updated the Stadium");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void showingStadiums() {
        String sql = "SELECT COUNT(*) AS stadiumCount FROM stadiums";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println( "There are a total of " + rs.getInt("stadiumCount") + " stadiums!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchStadiums(){
        System.out.println("Enter the Stadium name: ");
        String sql = "SELECT * FROM stadiums WHERE stadiumName = ? ";
        try (
                Connection conn = connect();
                PreparedStatement pstmt  = conn.prepareStatement(sql)){
            String inputStadium = sc.nextLine();
            pstmt.setString(1,inputStadium);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("stadiumId") +  "\t" +
                        rs.getString("stadiumName") + "\t" +
                        rs.getString("stadiumCity") + "\t" +
                        rs.getInt("stadiumCapacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
