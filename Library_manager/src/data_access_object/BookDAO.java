package data_access_object;

import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.JDBCUtil;
import model.Author;
import model.Book;

public class BookDAO {
    public static BookDAO getInstance() {
        return new BookDAO();
    }

    Scanner scan = new Scanner(System.in);

    public void add() {

        try {
            Connection connection = JDBCUtil.getConnection();
            Book book = new Book();
            Author author = new Author();
            book.intput();
            author.input();
            // Kiểm tra và thêm tác giả nếu không tồn tại
            String authorQuery = "SELECT id FROM Author WHERE fname = ? AND lname = ?;";
            PreparedStatement authorPS = connection.prepareStatement(authorQuery);
            authorPS.setString(1, author.getFname());
            authorPS.setString(2, author.getLname());
            ResultSet rsAuthor = authorPS.executeQuery();
            int authorId;
            if (rsAuthor.next()) {
                authorId = rsAuthor.getInt("id");
                System.out.println("Author already available!");
            } else {
                String insertAuthorQuery = "INSERT INTO Author (fname, lname) VALUES (?,?);";
                PreparedStatement insertAuthorPS = connection.prepareStatement(insertAuthorQuery,
                        Statement.RETURN_GENERATED_KEYS);
                insertAuthorPS.setString(1, author.getFname());
                insertAuthorPS.setString(2, author.getLname());
                insertAuthorPS.executeUpdate();
                ResultSet generatedKeys = insertAuthorPS.getGeneratedKeys();
                if (generatedKeys.next()) {
                    authorId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated author ID.");
                }
                System.out.println("Author added successfully!");
            }

            // Kiểm tra và thêm thể loại nếu không tồn tại
            String categoryQuery = "SELECT id FROM Category WHERE category_name = ?;";
            PreparedStatement categoryPS = connection.prepareStatement(categoryQuery);
            categoryPS.setString(1, book.getCategory());
            ResultSet rsCategory = categoryPS.executeQuery();
            int categoryId;
            if (rsCategory.next()) {
                categoryId = rsCategory.getInt("id");
                System.out.println("Category already available");
            } else {
                String insertCategoryQuery = "INSERT INTO Category (category_name) VALUES (?);";
                PreparedStatement insertCategoryPS = connection.prepareStatement(insertCategoryQuery,
                        Statement.RETURN_GENERATED_KEYS);
                insertCategoryPS.setString(1, book.getCategory());
                insertCategoryPS.executeUpdate();
                ResultSet generatedKeys = insertCategoryPS.getGeneratedKeys();
                if (generatedKeys.next()) {
                    categoryId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated category ID.");
                }
                System.out.println("Category added successfully!");
            }

            // Thêm sách
            String insertBookQuery = "INSERT INTO Book (title, category_id, publication_date, copies_owned) VALUES (?, ?, ?, ?);";
            PreparedStatement insertBookPS = connection.prepareStatement(insertBookQuery,
                    Statement.RETURN_GENERATED_KEYS);
            insertBookPS.setString(1, book.getTitle());
            insertBookPS.setInt(2, categoryId);
            insertBookPS.setString(3, book.getPublicationDate());
            insertBookPS.setInt(4, book.getCopiesOwned());
            insertBookPS.executeUpdate();
            ResultSet generatedKeys = insertBookPS.getGeneratedKeys();
            int bookId;
            if (generatedKeys.next()) {
                bookId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve generated book ID.");
            }
            System.out.println("Book added successfully!");

            // Liên kết sách và tác giả
            String insertBookAuthorQuery = "INSERT INTO Book_Author (book_id, author_id) VALUES (?,?);";
            PreparedStatement insertBookAuthorPS = connection.prepareStatement(insertBookAuthorQuery);
            insertBookAuthorPS.setInt(1, bookId);
            insertBookAuthorPS.setInt(2, authorId);
            insertBookAuthorPS.executeUpdate();
            System.out.println("Book-Author relationship added successfully!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void displayAll() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                String query = "select B.id as Book_id, B.title, C.category_name, B.publication_date, B.copies_owned,A.id as Author_id, A.fname, A.lname from Book as B \n"
                        + //
                        "inner join Category as C on B.Category_id = C.id\n" + //
                        "inner join Book_Author as BA on B.id = BA.Book_id\n" + //
                        "inner join Author as A on A.id = BA.Author_id\n" + //
                        "order by B.title;";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%s%70s%56s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%71s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");
                System.out.printf("|%3s| %-51s | %-26s | %-20s | %-10s |%3s|\n", " ", "title", "Author", "Category",
                        "Copies", "");
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");

                while (rs.next()) {

                    String title = rs.getString("title");
                    String categoryName = rs.getString("category_name");
                    int copies = rs.getInt("copies_owned");
                    String fName = rs.getString("fname");
                    String lName = rs.getString("lname");
                    System.out.printf("|%3s| %-51s | %-26s | %-20s | %-10s |%3s|\n", " ", title, fName + " " + lName,
                            categoryName, copies, "");

                }
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%106s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%126s+\n", "-".repeat(126));
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

    public void displayWithId() {
        try {

            Connection connection = JDBCUtil.getConnection();
            String query = "select B.id as Book_id, B.title, C.category_name, B.publication_date, B.copies_owned,A.id as Author_id, A.fname, A.lname from Book as B \n"
                    + //
                    "inner join Category as C on B.Category_id = C.id\n" + //
                    "inner join Book_Author as BA on B.id = BA.Book_id\n" + //
                    "inner join Author as A on A.id = BA.Author_id\n" + //
                    "order by B.title;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println();
            System.out.printf("+%126s+\n", "-".repeat(126));
            System.out.printf("|%s%70s%56s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
            System.out.printf("+%126s+\n", "-".repeat(126));
            System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%71s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                    "(A)ABOUT", "(E)HELP", "");
            System.out.printf("+%126s+\n", "-".repeat(126));
            System.out.printf("|%126s|\n", " ".repeat(126));
            System.out.printf("|%3s+%-12s+%-53s+%-28s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(53),
                    "-".repeat(28),
                    "-".repeat(22), "");
            System.out.printf("|%3s| %-10s | %-51s | %-26s | %-20s |%3s|\n", " ", "ID", "Title", "Author",
                    "Category", "");
            System.out.printf("|%3s+%-12s+%-53s+%-28s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(53),
                    "-".repeat(28),
                    "-".repeat(22), "");

            while (rs.next()) {

                String title = rs.getString("title");
                String categoryName = rs.getString("category_name");
                int id = rs.getInt("Book_id");
                String fName = rs.getString("fname");
                String lName = rs.getString("lname");
                System.out.printf("|%3s| %-10s | %-51s | %-26s | %-20s |%3s|\n", " ", id, title,
                        fName + " " + lName,
                        categoryName, "");

            }
            System.out.printf("|%3s+%-12s+%-53s+%-28s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(53),
                    "-".repeat(28),
                    "-".repeat(22), "");

            System.out.printf("|%126s|\n", " ".repeat(126));
            System.out.printf("|%126s|\n", " ".repeat(126));
            System.out.printf("|%106s%-20s|\n", " ", "(R) Return ->");
            System.out.printf("+%126s+\n", "-".repeat(126));
            JDBCUtil.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayByName() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();

                System.out.print("Enter keyword: ");
                String name = scan.nextLine();
                String query = "select B.id as Book_id, B.title, C.category_name, B.publication_date, B.copies_owned,A.id as Author_id, A.fname, A.lname from Book as B\n"
                        + //
                        "inner join Category as C on B.Category_id = C.id\n" + //
                        "inner join Book_Author as BA on B.id = BA.Book_id\n" + //
                        "inner join Author as A on A.id = BA.Author_id\n" + //
                        "where B.title like '%" + name + "%'\n" + //
                        "order by B.title;";
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%s%70s%56s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%71s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");
                System.out.printf("|%3s| %-51s | %-26s | %-20s | %-10s |%3s|\n", " ", "title", "Author", "Category",
                        "Copies", "");
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");

                while (rs.next()) {

                    String title = rs.getString("title");
                    String categoryName = rs.getString("category_name");
                    int copies = rs.getInt("copies_owned");
                    String fName = rs.getString("fname");
                    String lName = rs.getString("lname");
                    System.out.printf("|%3s| %-51s | %-26s | %-20s | %-10s |%3s|\n", " ", title, fName + " " + lName,
                            categoryName, copies, "");

                }
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%106s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%126s+\n", "-".repeat(126));
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

    public void displayByCategory() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();

                System.out.print("Enter keyword: ");
                String category_name = scan.nextLine();
                String query = "select B.id as Book_id, B.title, C.category_name, B.publication_date, B.copies_owned,A.id as Author_id, A.fname, A.lname from Book as B\n"
                        + //
                        "inner join Category as C on B.Category_id = C.id\n" + //
                        "inner join Book_Author as BA on B.id = BA.Book_id\n" + //
                        "inner join Author as A on A.id = BA.Author_id\n" + //
                        "where C.category_name = '" + category_name + "'\n" + //
                        "order by B.title;";
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%s%70s%56s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%71s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");
                System.out.printf("|%3s| %-51s | %-26s | %-20s | %-10s |%3s|\n", " ", "title", "Author", "Category",
                        "Copies", "");
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");

                while (rs.next()) {

                    String title = rs.getString("title");
                    String categoryName = rs.getString("category_name");
                    int copies = rs.getInt("copies_owned");
                    String fName = rs.getString("fname");
                    String lName = rs.getString("lname");
                    System.out.printf("|%3s| %-51s | %-26s | %-20s | %-10s |%3s|\n", " ", title, fName + " " + lName,
                            categoryName, copies, "");

                }
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%106s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%126s+\n", "-".repeat(126));
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

    public void displayByAuthor() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                Author author = new Author();
                author.input();
                String query = "select B.id as Book_id, B.title, C.category_name, B.publication_date, B.copies_owned,A.id as Author_id, A.fname, A.lname from Book as B\n"
                        + //
                        "inner join Category as C on B.Category_id = C.id\n" + //
                        "inner join Book_Author as BA on B.id = BA.Book_id\n" + //
                        "inner join Author as A on A.id = BA.Author_id\n" + //
                        "where A.fname = '" + author.getFname() + "'\n" + //
                        "and A.lname = '" + author.getLname() + "'\n" + //
                        "order by B.title;";
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%s%70s%56s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%71s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%126s+\n", "-".repeat(126));
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");
                System.out.printf("|%3s| %-51s | %-26s | %-20s | %-10s |%3s|\n", " ", "title", "Author", "Category",
                        "Copies", "");
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");

                while (rs.next()) {

                    String title = rs.getString("title");
                    String categoryName = rs.getString("category_name");
                    int copies = rs.getInt("copies_owned");
                    String fName = rs.getString("fname");
                    String lName = rs.getString("lname");
                    System.out.printf("|%3s| %-51s | %-26s | %-20s | %-10s |%3s|\n", " ", title, fName + " " + lName,
                            categoryName, copies, "");

                }
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-12s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(12), "");
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%126s|\n", " ".repeat(126));
                System.out.printf("|%106s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%126s+\n", "-".repeat(126));
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

    public void displayByYear() {
        try {
            boolean flag = true;
            while (flag) {

                Connection connection = JDBCUtil.getConnection();
                System.out.print("Enter year: ");
                String year = scan.nextLine();
                String query = "select B.id as Book_id, B.title, C.category_name, B.publication_date, B.copies_owned,A.id as Author_id, A.fname, A.lname from Book as B\n"
                        + //
                        "inner join Category as C on B.Category_id = C.id\n" + //
                        "inner join Book_Author as BA on B.id = BA.Book_id\n" + //
                        "inner join Author as A on A.id = BA.Author_id\n" + //
                        "Where year(publication_date) like '" + year + "%'" + //
                        "order by B.title;";
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(query);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println();
                System.out.printf("+%132s+\n", "-".repeat(132));
                System.out.printf("|%s%73s%59s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
                System.out.printf("+%132s+\n", "-".repeat(132));
                System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%77s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
                        "(A)ABOUT", "(E)HELP", "");
                System.out.printf("+%132s+\n", "-".repeat(132));
                System.out.printf("|%132s|\n", " ".repeat(132));
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-18s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(18), "");
                System.out.printf("|%3s| %-51s | %-26s | %-20s | %-16s |%3s|\n", " ", "title", "Author", "Category",
                        "Publication date", "");
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-18s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(18), "");

                while (rs.next()) {

                    String title = rs.getString("title");
                    String categoryName = rs.getString("category_name");
                    Date publication_date = rs.getDate("publication_date");
                    String fName = rs.getString("fname");
                    String lName = rs.getString("lname");
                    System.out.printf("|%3s| %-51s | %-26s | %-20s | %-16s |%3s|\n", " ", title, fName + " " + lName,
                            categoryName, publication_date, "");

                }
                System.out.printf("|%3s+%-53s+%-28s+%-22s+%-18s+%3s|\n", " ", "-".repeat(53), "-".repeat(28),
                        "-".repeat(22),
                        "-".repeat(18), "");
                System.out.printf("|%132s|\n", " ".repeat(132));
                System.out.printf("|%132s|\n", " ".repeat(132));
                System.out.printf("|%112s%-20s|\n", " ", "(R) Return ->");
                System.out.printf("+%132s+\n", "-".repeat(132));
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

    public void MultipleRemove() {
        try {
            displayWithId();
            Connection connection = JDBCUtil.getConnection();
            String bookQuery = "Delete from Book where id in ( ";
            String bookAuthorQuery = "Delete from Book_Author where book_id in ( ";
            System.out.print("Enter the number of books you want to delete: ");
            int amount = scan.nextInt();
            for (int i = 0; i < amount; i++) {
                System.out.print("Enter book's id: ");
                int id = scan.nextInt();
                if (i == 0) {
                    bookQuery = bookQuery + id;
                    bookAuthorQuery = bookAuthorQuery + id;
                } else {
                    bookQuery = bookQuery + ", " + id;
                    bookAuthorQuery = bookAuthorQuery + ", " + id;
                }
            }
            scan.nextLine();
            bookQuery += ")";
            bookAuthorQuery += ")";
            Statement statement = connection.createStatement();
            int row = 0;
            statement.executeUpdate(bookAuthorQuery);
            row += statement.executeUpdate(bookQuery);
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

    public void SpecificRemove() {
        try {
            Connection connection = JDBCUtil.getConnection();
            System.out.print("Enter book's title: ");
            String title = scan.nextLine();
            String bookQuery = "Delete from Book where title = ?";
            String bookAuthorQuery = "Delete from Book_Author where book_id =  (select id from Book where title = ?);";

            PreparedStatement psBook = connection.prepareStatement(bookQuery);
            PreparedStatement psAuthorBook = connection.prepareStatement(bookAuthorQuery);
            psBook.setString(1, title);
            psAuthorBook.setString(1, title);
            System.out.println(bookQuery);
            System.out.println(bookAuthorQuery);
            int row = 0;
            psAuthorBook.executeUpdate();
            row += psBook.executeUpdate();
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

}
