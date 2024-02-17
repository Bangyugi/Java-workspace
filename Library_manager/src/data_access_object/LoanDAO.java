package data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.JDBCUtil;
import model.Loan;

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
                String query = "select L.id, B.title, M.fname, M.lname, L.loan_date, L.return_date from Loan as L\n" + //
                                        "inner join Book as B on L.book_id = B.id\n" + //
                                        "inner join Member as M on L.member_id = M.id\n" + //
                                        "order by L.id desc;";
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

    public void haventReturn() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select L.id, B.title, M.fname, M.lname, L.loan_date, L.return_date from Loan as L\n" + //
                                        "inner join Book as B on L.book_id = B.id\n" + //
                                        "inner join Member as M on L.member_id = M.id\n" + //
                                        "where L.return_date is null\n" + //
                                        "order by L.id desc;\n" + //
                                        "";
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
                System.out.printf("|%3s| %-25s | %-50s | %-13s | %-13s |%3s|\n", " ", "Member", "Book's title",
                        "Loan date",
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
    
    public void OutOfTime() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select L.id, B.title, M.fname, M.lname, L.loan_date, L.return_date from Loan as L\n" + //
                                        "inner join Book as B on L.book_id = B.id\n" + //
                                        "inner join Member as M on L.member_id = M.id\n" + //
                                        "where ( L.return_date is null and current_date() - L.loan_date > 7 )\n" + //
                                        "or L.return_date - L.loan_date >7\n" + //
                                        "order by L.id desc;";
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
                System.out.printf("|%3s| %-25s | %-50s | %-13s | %-13s |%3s|\n", " ", "Member", "Book's title",
                        "Loan date",
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
    
    public void MultipleUpdate() {
        try {
            Connection connection = JDBCUtil.getConnection();
            String returnDate = "";
            String query1 = "' where id in (";
            System.out.print("Enter the number of loan you want to update: ");
            int amount = scan.nextInt();
            for (int i = 0; i < amount; i++) {
                System.out.print("Enter loan's id: ");
                int id = scan.nextInt();
                if (i == 0) {
                    query1 = query1 + id;
                } else {
                    query1 = query1 + ", " + id;
                }
            }
            scan.nextLine();
            System.out.print("Enter return date: ");
            returnDate = scan.nextLine();
            query1 = "Update Loan set return_date = '" + returnDate + query1;
            query1 += ")";
            Statement statement = connection.createStatement();
            int row = 0;
            row += statement.executeUpdate(query1);
            if (row > 0) {
                System.out.println("Update successfull!");
                System.out.println(row + " rows affected");
            } else {
                System.out.println("Update fail!");
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

    public void addLoan() {
        try {
            Connection connection = JDBCUtil.getConnection();
            Loan loan = new Loan();
            loan.input();

            // Them vào bảng Loan
            String loanQuery = "insert into Loan (book_id, member_id, loan_date, return_date) values \n" + //
                                "(?,?,?,null);";
            PreparedStatement psLoan = connection.prepareStatement(loanQuery, Statement.RETURN_GENERATED_KEYS);
            psLoan.setInt(1, loan.getBook_id());
            psLoan.setInt(2, loan.getMember_id());
            psLoan.setString(3, loan.getLoan_date());
            psLoan.executeUpdate();
            ResultSet rsLoan = psLoan.getGeneratedKeys();
            if (rsLoan.next()) {
                System.out.println("Loan added successfully!");
            } else {
                System.out.println("Failed to retrieve generated loan ID");
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

    public void UpdateReturnDate() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select L.id, B.title, M.fname, M.lname, L.loan_date, L.return_date from Loan as L\n" + //
                                        "inner join Book as B on L.book_id = B.id\n" + //
                                        "inner join Member as M on L.member_id = M.id\n" + //
                                        "where L.return_date is null\n" + //
                                        "order by L.id desc;";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%131s+\n", "-".repeat(131));
                System.out.printf("|%s%75s%56s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%131s+\n", "-".repeat(131));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%76s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%131s+\n", "-".repeat(131));
                System.out.printf("|%131s|\n", " ".repeat(131));
                System.out.printf("|%3s+%-10s+%-27s+%-52s+%-15s+%-15s+%3s|\n", " ", "-"
                        .repeat(10), "-".repeat(27), "-".repeat(52),
                        "-".repeat(15),
                        "-".repeat(15), "");
                System.out.printf("|%3s| %-8s | %-25s | %-50s | %-13s | %-13s |%3s|\n", " ","ID", "Member", "Book's title", "Loan date",
                        "Return date", "");
                System.out.printf("|%3s+%-10s+%-27s+%-52s+%-15s+%-15s+%3s|\n", " ", "-"
                        .repeat(10), "-".repeat(27), "-".repeat(52),
                        "-".repeat(15),
                        "-".repeat(15), "");

                while (rs.next()) {

                    String title = rs.getString("title");
                    Date loanDate = rs.getDate("loan_date");
                    Date returnDate = rs.getDate("return_date");
                    String fName = rs.getString("fname");
                    String lName = rs.getString("lname");
                    int id = rs.getInt("id");
                    System.out.printf("|%3s| %-8s | %-25s | %-50s | %-13s | %-13s |%3s|\n", " ",id, fName + " " + lName, title,
                            loanDate, returnDate, "");

                }
                System.out.printf("|%3s+%-10s+%-27s+%-52s+%-15s+%-15s+%3s|\n", " ", "-"
                        .repeat(10), "-".repeat(27), "-".repeat(52),
                        "-".repeat(15),
                        "-".repeat(15), "");
                System.out.printf("|%131s|\n", " ".repeat(131));
                System.out.printf("|%131s|\n", " ".repeat(131));
                System.out.printf("|%3s%-128s|\n", " ", "(E) Enter return update");
                System.out.printf("|%111s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%131s+\n", "-".repeat(131));
                JDBCUtil.closeConnection(connection);
                System.out.print("Enter your choice: ");
                char choice = scan.nextLine().charAt(0);
                switch (choice) {
                    case 'E': {
                        MultipleUpdate();
                        break;
                    }
                    case 'R': {
                        flag = false;
                        break;
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
