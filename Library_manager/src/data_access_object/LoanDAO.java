package data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.JDBCUtil;

public class LoanDAO {

    Scanner scan = new Scanner(System.in);

    public static LoanDAO getInstance() {
        return new LoanDAO();
    }

    public void displayAll() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select B.title, M.fname, M.lname, L.loan_date, L.return_date from Loan as L\n" + //
                        "inner join Book as B on L.book_id = B.id\n" + //
                        "inner join Member as M on L.member_id = M.id;";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%120s+\n", "-".repeat(120));
                System.out.printf("|%s%70s%50s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%120s+\n", "-".repeat(120));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%65s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%120s+\n", "-".repeat(120));
                System.out.printf("|%120s|\n", " ".repeat(120));
                System.out.printf("|%3s+%-27s+%-52s+%-15s+%-15s+%3s|\n", " ", "-".repeat(27), "-".repeat(52),
                        "-".repeat(15),
                        "-".repeat(15), "");
                System.out.printf("|%3s| %-25s | %-50s | %-13s | %-13s |%3s|\n", " ", "Member", "Book's title", "Loan date",
                        "Return date", "");
                System.out.printf("|%3s+%-27s+%-52s+%-15s+%-15s+%3s|\n", " ", "-".repeat(27), "-".repeat(52),
                        "-".repeat(15),
                        "-".repeat(15), "");

                while (rs.next()) {

                    String title = rs.getString("title");
                    Date loanDate = rs.getDate("loan_date");
                    Date returnDate = rs.getDate("return_date");
                    String fName = rs.getString("fname");
                    String lName = rs.getString("lname");
                    System.out.printf("|%3s| %-25s | %-50s | %-13s | %-13s |%3s|\n", " ", fName + " " + lName, title,
                            loanDate, returnDate, "");

                }
                System.out.printf("|%3s+%-27s+%-52s+%-15s+%-15s+%3s|\n", " ", "-".repeat(27), "-".repeat(52),
                        "-".repeat(15),
                        "-".repeat(15), "");
                System.out.printf("|%120s|\n", " ".repeat(120));
                System.out.printf("|%120s|\n", " ".repeat(120));
                System.out.printf("|%100s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%120s+\n", "-".repeat(120));
                JDBCUtil.closeConnection(connection);
                System.out.print("Enter your choice: ");
                char choice = scan.nextLine().charAt(0);
                switch (choice) {

                    case 'R': {
                        flag = false;
                    }
                    default:
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
