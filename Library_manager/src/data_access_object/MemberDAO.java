package data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.JDBCUtil;
import model.Member;

public class MemberDAO {

    Scanner scan = new Scanner(System.in);

    public static MemberDAO getInstance() {
        return new MemberDAO();
    }

    public void addMember() {
        try {
            Connection connection = JDBCUtil.getConnection();
            Member member = new Member();
            member.input();

            // Them vào bảng Member
            String memberQuery = "INSERT INTO Member (fname, lname, joined_date, active_status_id, phone) VALUES\n" + //
                    "(?, ?, CURRENT_DATE(), (Select id from Member_Status where state_value = 'Active'), ?);";
            PreparedStatement psMember = connection.prepareStatement(memberQuery, Statement.RETURN_GENERATED_KEYS);
            psMember.setString(1, member.getFname());
            psMember.setString(2, member.getLname());
            psMember.setString(3, member.getPhone());
            psMember.executeUpdate();
            ResultSet rsMember = psMember.getGeneratedKeys();
            if (rsMember.next()) {
                System.out.println("Member added successfully!");
            } else {
                System.out.println("Failed to retrieve generated member ID");
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
        boolean flag = true;
        try {
            while (flag) {
                Connection connection = JDBCUtil.getConnection();
                String query = "select M.fname, M.lname, M.joined_date, MS.state_value, M.phone from Member as M \n" + //
                        "left join Member_Status as MS on M.active_status_id = MS.id order by M.lname; ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%94s+\n", "-".repeat(94));
                System.out.printf("|%s%54s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%94s+\n", "-".repeat(94));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%39s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%94s+\n", "-".repeat(94));
                System.out.printf("|%94s|\n", " ".repeat(94));
                System.out.printf("|%3s+%-28s+%-17s+%-22s+%-16s+%3s|\n", " ", "-".repeat(28), "-".repeat(17),
                        "-".repeat(22), "-".repeat(16), "");
                System.out.printf("|%3s| %-26s | %-15s | %-20s | %-14s |%3s|\n", " ", "Name", "Joined date", "Category",
                        "Phone", "");
                System.out.printf("|%3s+%-28s+%-17s+%-22s+%-16s+%3s|\n", " ", "-".repeat(28), "-".repeat(17),
                        "-".repeat(22), "-".repeat(16), "");
                while (rs.next()) {
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    Date joinedDate = rs.getDate("joined_date");
                    String phone = rs.getString("phone");
                    String stateValue = rs.getString("state_value");
                    System.out.printf("|%3s| %-26s | %-15s | %-20s | %-14s |%3s|\n", " ", fname + " " + lname,
                            joinedDate,
                            stateValue, phone, "");
                }
                System.out.printf("|%3s+%-28s+%-17s+%-22s+%-16s+%3s|\n", " ", "-".repeat(28), "-".repeat(17),
                        "-".repeat(22), "-".repeat(16), "");
                System.out.printf("|%94s|\n", " ".repeat(94));
                System.out.printf("|%94s|\n", " ".repeat(94));
                System.out.printf("|%74s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%94s+\n", "-".repeat(94));
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

    public void displayMember() {
        boolean flag = true;
        try {
            while (flag) {
                Connection connection = JDBCUtil.getConnection();
                String query = "select M.id, M.fname, M.lname, M.joined_date, MS.state_value, M.phone from Member as M \n"
                        + //
                        "left join Member_Status as MS on M.active_status_id = MS.id where M.active_status_id = 400005  order by M.lname; ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%s%69s%45s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%59s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                System.out.printf("|%3s| %-10s | %-27s | %-15s | %-20s | %-20s |%3s|\n", " ", "ID", "Name",
                        "Joined date",
                        "Category", "Phone", "");
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    Date joinedDate = rs.getDate("joined_date");
                    String phone = rs.getString("phone");
                    String stateValue = rs.getString("state_value");
                    System.out.printf("|%3s| %-10s | %-27s | %-15s | %-20s | %-20s |%3s|\n", " ",
                            id, fname + " " + lname,
                            joinedDate, stateValue, phone, "");
                }
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%94s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%114s+\n", "-".repeat(114));
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

    public void SpecificUpdate() {
        try {
            Connection connection = JDBCUtil.getConnection();
            String memberQuery = "UPDATE Member \n" + //
                    "SET active_status_id = 400005 \n" + //
                    "WHERE active_status_id = 400002 AND id in (";
            System.out.print("Enter the number of member you want to accept: ");
            int amount = scan.nextInt();
            for (int i = 0; i < amount; i++) {
                System.out.print("Enter book's id: ");
                int id = scan.nextInt();
                if (i == 0) {
                    memberQuery = memberQuery + id;
                } else {
                    memberQuery = memberQuery + ", " + id;
                }
            }
            scan.nextLine();
            memberQuery += ")";
            Statement statement = connection.createStatement();
            int row = 0;
            row += statement.executeUpdate(memberQuery);
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

    public void MultipleUpdate() {
        try {
            Connection connection = JDBCUtil.getConnection();
            String memberQuery = "UPDATE Member \n" + //
                    "SET active_status_id = 400005 \n" + //
                    "WHERE active_status_id = 400002;";
            Statement statement = connection.createStatement();
            int row = 0;
            row += statement.executeUpdate(memberQuery);
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

    public void DisplayPendingApproval() {
        boolean flag = true;
        try {
            while (flag) {
                Connection connection = JDBCUtil.getConnection();
                String query = "select M.id, M.fname, M.lname, M.joined_date, MS.state_value, M.phone from Member as M \n"
                        + //
                        "left join Member_Status as MS on M.active_status_id = MS.id where MS.state_value = 'Pending Approval' order by M.lname; ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%s%69s%45s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%59s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                System.out.printf("|%3s| %-10s | %-27s | %-15s | %-20s | %-20s |%3s|\n", " ", "ID", "Name",
                        "Joined date",
                        "Category", "Phone", "");
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    Date joinedDate = rs.getDate("joined_date");
                    String phone = rs.getString("phone");
                    String stateValue = rs.getString("state_value");
                    System.out.printf("|%3s| %-10s | %-27s | %-15s | %-20s | %-20s |%3s|\n", " ",
                            id, fname + " " + lname,
                            joinedDate, stateValue, phone, "");
                }
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%3s%-20s%91s|\n", " ", "(1) Accept all", "");
                System.out.printf("|%3s%-20s%91s|\n", " ", "(2) Accept by id", "");
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%94s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%114s+\n", "-".repeat(114));
                JDBCUtil.closeConnection(connection);
                System.out.print("Enter your choice: ");
                char choice = scan.nextLine().charAt(0);
                switch (choice) {
                    case '1': {
                        MultipleUpdate();
                        break;
                    }
                    case '2': {
                        SpecificUpdate();
                        break;
                    }
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

    public void MultipleRemove() {
        try {
            Connection connection = JDBCUtil.getConnection();
            String memberQuery = "Delete from Member where id in ( ";
            System.out.print("Enter the number of members you want to delete: ");
            int amount = scan.nextInt();
            for (int i = 0; i < amount; i++) {
                System.out.print("Enter members's id: ");
                int id = scan.nextInt();
                if (i == 0) {
                    memberQuery = memberQuery + id;
                } else {
                    memberQuery = memberQuery + ", " + id;
                }
            }
            scan.nextLine();
            memberQuery += ")";
            Statement statement = connection.createStatement();
            int row = 0;
            row += statement.executeUpdate(memberQuery);
            if (row > 0) {
                System.out.println("Remove successfull!");
                System.out.println(row + " rows affected");
            } else {
                System.out.println("Remove fail!");
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

    public void displayWithId() {
        boolean flag = true;
        try {
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select M.id, M.fname, M.lname, M.joined_date, MS.state_value, M.phone from Member as M \n"
                        + //
                        "left join Member_Status as MS on M.active_status_id = MS.id where M.active_status_id = 400005  order by M.lname; ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%s%69s%45s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%59s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%114s+\n", "-".repeat(114));
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                System.out.printf("|%3s| %-10s | %-27s | %-15s | %-20s | %-20s |%3s|\n", " ", "ID", "Name",
                        "Joined date",
                        "Category", "Phone", "");
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");
                    Date joinedDate = rs.getDate("joined_date");
                    String phone = rs.getString("phone");
                    String stateValue = rs.getString("state_value");
                    System.out.printf("|%3s| %-10s | %-27s | %-15s | %-20s | %-20s |%3s|\n", " ",
                            id, fname + " " + lname,
                            joinedDate, stateValue, phone, "");
                }
                System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
                        "-".repeat(17), "-".repeat(22), "-".repeat(22), "");
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%3s%-20s%91s|\n", " ", "(E) Continue remove", "");

                System.out.printf("|%114s|\n", " ".repeat(114));
                System.out.printf("|%94s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%114s+\n", "-".repeat(114));
                JDBCUtil.closeConnection(connection);
                System.out.print("Enter your choice: ");
                char choice = scan.nextLine().charAt(0);
                switch (choice) {
                    case 'E': {
                        MultipleRemove();
                        break;
                    }

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
