import java.sql.*;
import java.util.Scanner;


public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        selectAllStadiums();
        Scanner sc = new Scanner(System.in);

        boolean quit = false;
        printMenu();

        while (!quit) {
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
                    updateStadium();
                    break;

                case 4:
                    deleteStadium();
                    break;

                case 5:
                    System.out.println("you have chose searching stadiums");
                    break;

                case 6:
                    showingHowManyStadiums();
                    break;

            }
        }
    }

    public static void printMenu() {
        System.out.println("\nMENU:\n");
        System.out.println("0  - exit\n" +
                "1  - show all Stadiums\n" +
                "2  - create new Stadium\n" +
                "3  - Upgrade \n" +
                "4  - delete\n" +
                "5  - search Stadium\n" +
                "6  - show how many Stadiums\n " +
                "Please, chose a number  ");

    }

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

    private static void selectAllStadiums() {
        String sql = "SELECT * FROM stadiums";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
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
        System.out.println("Enter the name of the Stadium: ");
        String inputTitel = sc.nextLine();
        System.out.println("Enter the Stadium city: ");
        String inputForfattare = sc.nextLine();
        System.out.println("Stadium capacity: ");
        int inputPris = sc.nextInt();
        insertStadium(inputTitel, inputForfattare, inputPris);
        sc.nextLine();
    }


    public static void insertStadium(String stadiumName, String stadiumCity, int stadiumCapacity) {
        String sql = "INSERT INTO stadiums(stadiumName, stadiumCity, stadiumCapacity) VALUES(?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, stadiumName);
            pstmt.setString(2, stadiumCity);
            pstmt.setInt(3, stadiumCapacity);
            pstmt.executeUpdate();
            System.out.println("You have added a new stadium");
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

    public static void updateStadium() {
        System.out.println("chose the stadium Id-number to upgrade: ");
        int inputId = sc.nextInt();
        upgrade(inputId);
        sc.nextLine();

    }

    public static void upgrade(int id) {

        System.out.println("what do you want to upgrade?");
        System.out.println("1 - Name?");
        System.out.println("2 - City ");
        System.out.println("3 - Capacity");
        int userupgrade = sc.nextInt();
        sc.nextLine();

        if (userupgrade == 1) {
            System.out.println("Enter the new name of the Stadium: ");
            String updatestadiumName = sc.nextLine();


        } else if (userupgrade == 2) {
            System.out.println("You will upgrade the City");

        } else if (userupgrade == 3) {
            System.out.println("You will upgrade the Capacity");
        }


      /*  try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, stadiumName);
            pstmt.setString(2, stadiumCity);
            pstmt.setInt(3, stadiumCapacity);
            pstmt.setInt(4, stadiumId);
            // update
            pstmt.executeUpdate();
            System.out.println("Du har uppdaterat vald bok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

    }

    private static void showingHowManyStadiums() {

        String sql = "SELECT COUNT(*) FROM stadiums";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
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
}