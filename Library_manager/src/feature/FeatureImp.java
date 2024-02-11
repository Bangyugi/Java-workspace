package feature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access_object.BookDAO;
import database.JDBCUtil;
import model.Account;

public class FeatureImp implements Feature {

	static Scanner scan = new Scanner(System.in);

	@Override
	public void LoginForm() {
		String error = "";
		while (true) {
			try {
				System.out.print("\033[H\033[2J"); // Đây là một cách xóa console sử dụng các lệnh escape sequence.
				System.out.flush();

				System.out.printf("+%50s+\n", "-".repeat(50));
				System.out.printf("|%s%25s%25s|\n", "", "LOGIN", "");
				System.out.printf("+%50s+\n", "-".repeat(50));
				System.out.printf("|%50s|\n", " ");
				System.out.printf("|%5s%-45s|\n", "", "Username: ");
				System.out.printf("|%5s%-45s|\n", "", "Password: ");
				if (!error.equals(""))
					System.out.printf("|%5s%-45s|\n", "", error);
				System.out.printf("|%50s|\n", " ");
				System.out.printf("+%50s+\n", "-".repeat(50));
				System.out.println();
				Connection connection = JDBCUtil.getConnection();
				Account account = new Account();
				account.input();
				String queryString = "SELECT * FROM Account WHERE userName = ? AND passWord = ?;";
				PreparedStatement preparedStatement = connection.prepareStatement(queryString);
				preparedStatement.setString(1, account.getUserName());
				preparedStatement.setString(2, account.getPassWord());
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					System.out.print("\033[H\033[2J"); // Đây là một cách xóa console sử dụng các lệnh escape sequence.
					System.out.flush();
					System.out.printf("+%50s+\n", "-".repeat(50));
					System.out.printf("|%s%25s%25s|\n", "", "LOGIN", "");
					System.out.printf("+%50s+\n", "-".repeat(50));
					System.out.printf("|%50s|\n", " ");
					System.out.printf("|%5s%-45s|\n", "", "Access accepted!");
					System.out.printf("|%50s|\n", " ");
					System.out.printf("+%50s+\n", "-".repeat(50));
					System.out.println();
					JDBCUtil.closeConnection(connection);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				} else {
					JDBCUtil.closeConnection(connection);
					throw new SQLException("Wrong username or password!");
				}
			} catch (SQLException e) {
				error = e.getMessage();
			}
		}
	}

	public void FindByNamePage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%16s+-----------------------------------------------------------+------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|    (E) Enter keywords                                     |      |%16s|\n", " ",
					" ");
			System.out.printf("|%16s+-----------------------------------------------------------+------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'E': {
					BookDAO.getInstance().displayByName();
					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void FindByCategoryNamePage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%16s+-----------------------------------------------------------+------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|    (E) Enter keywords                                     |      |%16s|\n", " ",
					" ");
			System.out.printf("|%16s+-----------------------------------------------------------+------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'E': {
					BookDAO.getInstance().displayByCategory();
					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void FindByYearPage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%16s+-----------------------------------------------------------+------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-55s|%6s|%16s|\n", " ", " ", "(E) Enter Year", " ",
					" ");
			System.out.printf("|%16s+-----------------------------------------------------------+------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'E': {
					BookDAO.getInstance().displayByYear();
					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void FindByAuthorNamePage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%16s+-----------------------------------------------------------+------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-55s|%6s|%16s|\n", " ", " ", "(E) Enter information", " ",
					" ");
			System.out.printf("|%16s+-----------------------------------------------------------+------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'E': {
					System.out.print("\033[H\033[2J");
					System.out.flush();
					System.out.println();
					System.out.printf("+%100s+\n", "-".repeat(100));
					System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
					System.out.printf("+%100s+\n", "-".repeat(100));
					System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
							"(A)ABOUT", "(E)HELP", "");
					System.out.printf("+%100s+\n", "-".repeat(100));
					System.out.printf("|%100s|\n", " ".repeat(100));
					System.out.printf("|%100s|\n", " ".repeat(100));
					System.out.printf(
							"|%16s+------------------------------------------------------------------+%16s|\n", " ",
							" ");
					System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", "Enter author's first name", " ",
							" ");
					System.out.printf(
							"|%16s+------------------------------------------------------------------+%16s|\n", " ",
							" ");
					System.out.printf(
							"|%16s+------------------------------------------------------------------+%16s|\n", " ",
							" ");
					System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", "Enter author's second name", " ",
							" ");
					System.out.printf(
							"|%16s+------------------------------------------------------------------+%16s|\n", " ",
							" ");
					System.out.printf("|%100s|\n", " ".repeat(100));
					System.out.printf("|%100s|\n", " ".repeat(100));
					System.out.printf("|%100s|\n", " ".repeat(100));
					System.out.printf("+%100s+\n", "-".repeat(100));
					BookDAO.getInstance().displayByAuthor();
					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void AddBookPage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%3s%-97s|\n", " ", "(A) Add a new book's information:");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|    Enter title                                                   |%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|    Enter category                                                |%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|    Enter publication date                                        |%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|    Enter copies owned                                            |%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|    Enter author's first name                                     |%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|    Enter author's second name                                    |%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'A': {
					BookDAO.getInstance().add();
					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void FindBookPage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(1) Find by name", "|", " ",
					"|", "(2) Find by year", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(3) Find by Category", "|", " ",
					"|", "(4) Find by id", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(5) Find by author", "|", " ",
					" ", " ", " ", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case '1': {
					FindByNamePage();
					break;
				}
				case '2': {
					FindByYearPage();
					break;
				}
				case '3': {
					FindByCategoryNamePage();

					break;
				}
				case '4': {

					break;
				}
				case '5': {
					FindByAuthorNamePage();
					break;
				}
				case '6': {

					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void BookPage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(1) Add a new book", "|", " ",
					"|", "(2) Delete book", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(3) Show all infor", "|", " ",
					"|", "(4) Look for book", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(5) Book issue", "|", " ",
					" ", " ", " ", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");

			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			// scan.nextLine();
			switch (choice) {
				case '1': {
					AddBookPage();
					break;
				}
				case '2': {
					break;
				}
				case '3': {
					BookDAO.getInstance().showAll();

					break;
				}
				case '4': {
					FindBookPage();

					break;
				}
				case '5': {
					break;
				}
				case '6': {

					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void MemberPage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(1) Add a new member", "|", " ",
					"|", "(4) Remove Member", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(2) Pending approval", "|", " ",
					"|", "(5) Show all infor", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");

			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(3) Update member", "|", " ",
					"|", "(6) Find by name", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");

			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			// scan.nextLine();
			switch (choice) {
				case '1': {

					break;
				}
				case '2': {

					break;
				}
				case '3': {

					break;
				}
				case '4': {

					break;
				}
				case '5': {

					break;
				}
				case '6': {

					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void LoanPage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(1) Show all infor", "|", " ",
					"|", "(2) Find by date", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(3) Haven't return", "|", " ",
					"|", "(4) Add a new loan", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(5) Update loan", "|", " ",
					" ", " ", " ", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");

			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			// scan.nextLine();
			switch (choice) {
				case '1': {

					break;
				}
				case '2': {

					break;
				}
				case '3': {

					break;
				}
				case '4': {

					break;
				}
				case '5': {

					break;
				}
				case '6': {

					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void FinePage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(1) Show all infor", "|", " ",
					"|", "(2) Find by date", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(3) Update fine", "|", " ",
					"|", "(4) Add a new loan", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", ". . . ", "|", " ",
					" ", " ", " ", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");

			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			// scan.nextLine();
			switch (choice) {
				case '1': {

					break;
				}
				case '2': {

					break;
				}
				case '3': {

					break;
				}
				case '4': {

					break;
				}
				case '5': {

					break;
				}
				case '6': {

					break;
				}
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	public void HomePage() {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%5s%-75s%-20s|\n", "", "Hello admin!", "(M) My account->");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-11s%-20s%s%16s%-11s%-20s%s%10s|\n", " ", "|", "(1) Book", "|", " ",
					"|", "(2) Member", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-11s%-20s%s%16s%-11s%-20s%s%10s|\n", " ", "|", "(3) Loan", "|", " ",
					"|", "(4) Fine", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-11s%-20s%s%16s%-11s%-20s%s%10s|\n", " ", "|", "(5) Payment ", "|", " ",
					"|", ". . .", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");

			System.out.printf("|%80s%-20s|\n", " ", "(L) Log out->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			// scan.nextLine();
			switch (choice) {
				case '1': {
					BookPage();
					break;
				}
				case '2': {
					MemberPage();
					break;
				}
				case '3': {
					LoanPage();
					break;
				}
				case '4': {
					FinePage();
					break;
				}
				case 'L': {
					return;
				}
				default:
					break;
			}
		}
	}

}
