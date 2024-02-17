package data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.JDBCUtil;

public class FinePaymentDAO {
    Scanner scan = new Scanner(System.in);

    public static FinePaymentDAO getInstance() {
        return new FinePaymentDAO();
    }

    public void displayAll() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select * from Fine_Payment order by payment_date desc;";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%122s+\n", "-".repeat(122));
                System.out.printf("|%s%50s%-72s|\n", "", "", "LIBRARY MANAGEMENT SYSTEM");
                System.out.printf("+%122s+\n", "-".repeat(122));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%67s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%122s+\n", "-".repeat(122));
                System.out.printf("|%122s|\n", " ".repeat(122));
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-22s+%-37s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(22),
                        "-".repeat(37), "");
                System.out.printf("|%3s| %-15s | %-15s | %-15s | %-20s | %-35s |%3s|\n", " ", "Id",
                        "Member's id",
                        "Payment date", "Payment amount",
                        "Note", "");
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-22s+%-37s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(22),
                        "-".repeat(37), "");

                while (rs.next()) {

                    int memberId = rs.getInt("member_id");
                    int id = rs.getInt("id");
                    Date paymentDate = rs.getDate("payment_date");
                    int paymentAmount = rs.getInt("payment_amount");
                    String note = rs.getString("note");
                    System.out.printf("|%3s| %-15s | %-15s | %-15s | %-20s | %-35s |%3s|\n", " ", id, memberId,
                            paymentDate, paymentAmount, note, " ");

                }
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-22s+%-37s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(22),
                        "-".repeat(37), "");
                System.out.printf("|%122s|\n", " ".repeat(122));
                System.out.printf("|%122s|\n", " ".repeat(122));
                System.out.printf("|%102s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%122s+\n", "-".repeat(122));
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
