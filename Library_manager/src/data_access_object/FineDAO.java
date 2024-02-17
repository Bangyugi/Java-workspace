package data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.JDBCUtil;
import model.Fine;

public class FineDAO {

    Scanner scan = new Scanner(System.in);

    public static FineDAO getInstance() {
        return new FineDAO();
    }

    public void AddOverdueReturnFine() {
        try {
            Connection connection = JDBCUtil.getConnection();
            Fine fine = new Fine();
            fine.input();
            String fineQuery = "insert into Fine (loan_id, member_id,fine_date,fint_amount, type) values \n" + //
                    "(?,?,?,?,'Overdue return');";
            PreparedStatement psFine = connection.prepareStatement(fineQuery, Statement.RETURN_GENERATED_KEYS);
            psFine.setInt(1, fine.getLoan_id());
            psFine.setInt(2, fine.getMember_id());
            psFine.setString(3, fine.getFine_date());
            psFine.setInt(4, fine.getFine_amount());
            psFine.executeUpdate();
            ResultSet rsFine = psFine.getGeneratedKeys();
            if (rsFine.next()) {
                System.out.println("Fine added successfully!");
            } else {
                System.out.println("Failed to retrieve generated fine ID");
            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void AddLostFine() {
        try {
            Connection connection = JDBCUtil.getConnection();
            Fine fine = new Fine();
            fine.input();

            String fineQuery = "insert into Fine (loan_id, member_id,fine_date,fint_amount, type) values \n" + //
                    "(?,?,?,?,'Lost book');";
            PreparedStatement psFine = connection.prepareStatement(fineQuery, Statement.RETURN_GENERATED_KEYS);
            psFine.setInt(1, fine.getLoan_id());
            psFine.setInt(2, fine.getMember_id());
            psFine.setString(3, fine.getFine_date());
            psFine.setInt(4, fine.getFine_amount());
            psFine.executeUpdate();
            ResultSet rsFine = psFine.getGeneratedKeys();
            if (rsFine.next()) {
                System.out.println("Fine added successfully!");
            } else {
                System.out.println("Failed to retrieve generated fine ID");
            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void AddDamageFine() {
        try {
            Connection connection = JDBCUtil.getConnection();
            Fine fine = new Fine();
            fine.input();

            String fineQuery = "insert into Fine (loan_id, member_id,fine_date,fint_amount, type) values \n" + //
                    "(?,?,?,?,'Damaged book');";
            PreparedStatement psFine = connection.prepareStatement(fineQuery, Statement.RETURN_GENERATED_KEYS);
            psFine.setInt(1, fine.getLoan_id());
            psFine.setInt(2, fine.getMember_id());
            psFine.setString(3, fine.getFine_date());
            psFine.setInt(4, fine.getFine_amount());
            psFine.executeUpdate();
            ResultSet rsFine = psFine.getGeneratedKeys();
            if (rsFine.next()) {
                System.out.println("Fine added successfully!");
            } else {
                System.out.println("Failed to retrieve generated fine ID");
            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void displayAll() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select * from Fine order by id desc; ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%s%45s%-67s|\n", "", "", "LIBRARY MANAGEMENT SYSTEM");
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%57s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");
                System.out.printf("|%3s| %-15s | %-15s | %-15s | %-15s | %-30s |%3s|\n", " ", "Member's id",
                        "Loan's id",
                        "Fine date", "Fine amount",
                        "Type of fine", "");
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");

                while (rs.next()) {

                    int memberId = rs.getInt("member_id");
                    int loanId = rs.getInt("loan_id");
                    Date fineDate = rs.getDate("fine_date");
                    int fineAmount = rs.getInt("fint_amount");
                    String description = rs.getString("type");
                    System.out.printf("|%3s| %-15s | %-15s | %-15s | %-15s | %-30s |%3s|\n", " ", memberId, loanId,
                            fineDate, fineAmount, description, " ");

                }
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%92s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%112s+\n", "-".repeat(112));
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

    public void displayOverdueReturn() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select * from Fine where type = 'Overdue return' order by id desc; ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%s%45s%-67s|\n", "", "", "LIBRARY MANAGEMENT SYSTEM");
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%57s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");
                System.out.printf("|%3s| %-15s | %-15s | %-15s | %-15s | %-30s |%3s|\n", " ", "Member's id",
                        "Loan's id",
                        "Fine date", "Fine amount",
                        "Type of fine", "");
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");

                while (rs.next()) {

                    int memberId = rs.getInt("member_id");
                    int loanId = rs.getInt("loan_id");
                    Date fineDate = rs.getDate("fine_date");
                    int fineAmount = rs.getInt("fint_amount");
                    String description = rs.getString("type");
                    System.out.printf("|%3s| %-15s | %-15s | %-15s | %-15s | %-30s |%3s|\n", " ", memberId, loanId,
                            fineDate, fineAmount, description, " ");

                }
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%92s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%112s+\n", "-".repeat(112));
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

    public void displayLost() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select * from Fine where type = 'Lost book' order by id desc; ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%s%45s%-67s|\n", "", "", "LIBRARY MANAGEMENT SYSTEM");
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%57s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");
                System.out.printf("|%3s| %-15s | %-15s | %-15s | %-15s | %-30s |%3s|\n", " ", "Member's id",
                        "Loan's id",
                        "Fine date", "Fine amount",
                        "Type of fine", "");
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");

                while (rs.next()) {

                    int memberId = rs.getInt("member_id");
                    int loanId = rs.getInt("loan_id");
                    Date fineDate = rs.getDate("fine_date");
                    int fineAmount = rs.getInt("fint_amount");
                    String description = rs.getString("type");
                    System.out.printf("|%3s| %-15s | %-15s | %-15s | %-15s | %-30s |%3s|\n", " ", memberId, loanId,
                            fineDate, fineAmount, description, " ");

                }
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%92s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%112s+\n", "-".repeat(112));
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

    public void displayDamaged() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select * from Fine where type = 'Damaged book' order by id desc; ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%s%45s%-67s|\n", "", "", "LIBRARY MANAGEMENT SYSTEM");
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%57s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%112s+\n", "-".repeat(112));
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");
                System.out.printf("|%3s| %-15s | %-15s | %-15s | %-15s | %-30s |%3s|\n", " ", "Member's id",
                        "Loan's id",
                        "Fine date", "Fine amount",
                        "Type of fine", "");
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");

                while (rs.next()) {

                    int memberId = rs.getInt("member_id");
                    int loanId = rs.getInt("loan_id");
                    Date fineDate = rs.getDate("fine_date");
                    int fineAmount = rs.getInt("fint_amount");
                    String description = rs.getString("type");
                    System.out.printf("|%3s| %-15s | %-15s | %-15s | %-15s | %-30s |%3s|\n", " ", memberId, loanId,
                            fineDate, fineAmount, description, " ");

                }
                System.out.printf("|%3s+%-17s+%-17s+%-17s+%-17s+%-32s+%3s|\n", " ", "-".repeat(17), "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(17),
                        "-".repeat(32), "");
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%112s|\n", " ".repeat(112));
                System.out.printf("|%92s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%112s+\n", "-".repeat(112));
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
