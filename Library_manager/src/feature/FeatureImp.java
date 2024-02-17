package feature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access_object.BookDAO;
import data_access_object.FineDAO;
import data_access_object.LoanDAO;
import data_access_object.MemberDAO;
import database.JDBCUtil;
import model.Account;
import model.Reservation;
import test_main.ClearScreen;

public class FeatureImp implements Feature {

	static Scanner scan = new Scanner(System.in);

	@Override
	public void LoginForm() {
		String error = "";
		while (true) {
			try {
				ClearScreen.clrscr();
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

	public void ComingSoonPage() {
		while (true) {
			ClearScreen.clrscr();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%16s%-68s%16s|\n", " ", "Comming soon!", " ",
					" ");

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

	public void FindByNamePage() {
		while (true) {
			ClearScreen.clrscr();
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
			ClearScreen.clrscr();
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
			ClearScreen.clrscr();
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
			ClearScreen.clrscr();
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

	public void AddMemberPage() {
		while (true) {
			ClearScreen.clrscr();
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
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter member's first name", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter member's last name", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter member's phone", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'A': {
					MemberDAO.getInstance().addMember();
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

	public void AddLoanPage() {
		while (true) {
			ClearScreen.clrscr();
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
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter book's id", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter member's id", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter loan date", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'A': {
					LoanDAO.getInstance().addLoan();
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

	public void AddFinePage(int number) {
		while (true) {
			ClearScreen.clrscr();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			if (number == 1)
				System.out.printf("|%3s%-97s|\n", " ", "(A) Add overdue return fine's information:");
			if (number == 2)
				System.out.printf("|%3s%-97s|\n", " ", "(A) Add lost book fine's information:");
			if (number == 3)
				System.out.printf("|%3s%-97s|\n", " ", "(A) Add damaged book fine's information:");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter member's id", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter loan's id", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter fine date", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", " Enter fine amount", " ",
					" ");
			System.out.printf("|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'A': {
					if (number == 1)
						FineDAO.getInstance().AddOverdueReturnFine();
					if (number == 2)
						FineDAO.getInstance().AddLostFine();
					if (number == 3)
						FineDAO.getInstance().AddDamageFine();
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
			ClearScreen.clrscr();
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
			ClearScreen.clrscr();
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
					"|", "(4) Find by author", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%100s|\n", " ".repeat(100));
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

					FindByAuthorNamePage();
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

	public void SpecificRemovePage() {
		while (true) {
			ClearScreen.clrscr();
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
			System.out.printf("|%16s|%4s%-62s|%16s|\n", " ", " ", "(E) Enter book's name", " ",
					" ");
			System.out.printf(
					"|%16s+------------------------------------------------------------------+%16s|\n", " ",
					" ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'E': {
					BookDAO.getInstance().SpecificRemove();
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

	public void RemovePage() {
		while (true) {
			ClearScreen.clrscr();
			System.out.println();
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%s%60s%40s|\n", "", "LIBRARY MANAGEMENT SYSTEM", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%3s%-10s%-10s%-12s%-10s%-10s%45s|\n", " ", "(H)HOME", "(B)BOOKS", "(P)PAPERS",
					"(A)ABOUT", "(E)HELP", "");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(1) Multiple remove", "|", " ",
					"|", "(2) Specific remove", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case '1': {
					BookDAO.getInstance().MultipleRemove();
					break;
				}
				case '2': {
					SpecificRemovePage();
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
			ClearScreen.clrscr();
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
					"|", "(2) Remove book", "|", " ");
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
					RemovePage();
					break;
				}
				case '3': {
					BookDAO.getInstance().displayAll();

					break;
				}
				case '4': {
					FindBookPage();

					break;
				}
				case '5': {
					ComingSoonPage();

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
			ClearScreen.clrscr();
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
					"|", "(2) Remove Member", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(3) Pending approval", "|", " ",
					"|", "(4) Show all infor", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(5) Display member", "|", " ",
					"", " ", " ", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					" " + " ".repeat(30) + " ", " ");

			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			// scan.nextLine();
			switch (choice) {
				case '1': {
					AddMemberPage();
					break;
				}
				case '2': {
					MemberDAO.getInstance().displayWithId();
					break;
				}
				case '3': {

					MemberDAO.getInstance().DisplayPendingApproval();
					break;
				}
				case '4': {
					MemberDAO.getInstance().displayAll();
					break;
				}
				case '5': {
					MemberDAO.getInstance().displayMember();
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
			ClearScreen.clrscr();
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
					"|", "(2) Out of time", "|", " ");
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
					LoanDAO.getInstance().displayAll();
					break;
				}
				case '2': {
					LoanDAO.getInstance().OutOfTime();
					break;
				}
				case '3': {
					LoanDAO.getInstance().haventReturn();
					break;
				}
				case '4': {
					AddLoanPage();
					break;
				}
				case '5': {
					LoanDAO.getInstance().UpdateReturnDate();
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

	public void TypeFinePage() {
		while (true) {
			ClearScreen.clrscr();
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
			System.out.printf("|%10s+%78s+%10s|\n", " ", "" + "-".repeat(78) + "", " ");
			System.out.printf("|%10s|%26s%-52s|%10s|\n", " ", "", "(1) Add Overdue return fine", "", " ");
			System.out.printf("|%10s+%78s+%10s|\n", " ", "" + "-".repeat(78) + "", " ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s+%78s+%10s|\n", " ", "" + "-".repeat(78) + "", " ");
			
			System.out.printf("|%10s|%28s%-50s|%10s|\n", " ", "", "(2) Add lost book fine", "", " ");
			
			System.out.printf("|%10s+%78s+%10s|\n", " ", "" + "-".repeat(78) + "", " ");
			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%10s+%78s+%10s|\n", " ", "" + "-".repeat(78) + "", " ");
			
			System.out.printf("|%10s|%29s%-49s|%10s|\n", " ", "", "(3) Add damage fine", "", " ");
			
			System.out.printf("|%10s+%78s+%10s|\n", " ", "" + "-".repeat(78) + "", " ");
			System.out.printf("|%100s|\n", " ".repeat(100));

			System.out.printf("|%100s|\n", " ".repeat(100));
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			// scan.nextLine();
			switch (choice) {
				case '1': {
					AddFinePage((int)choice - (int)'0');
					break;
				}
				case '2': {
					AddFinePage((int)choice - (int)'0');

					break;
				}
				case '3': {
					AddFinePage((int)choice - (int)'0');

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
			ClearScreen.clrscr();
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
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(1) Show all fine", "|",
					" ",
					"|", "(2) Lost fine", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(3) Overdue fine", "|", " ",
					"|", "(4) Damage fine", "|", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"+" + "-".repeat(30) + "+", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"" + "".repeat(30) + "", " ");
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(5) Add new fine", "|", " ",
					" ", " ", " ", " ");
			System.out.printf("|%10s%32s%16s%32s%10s|\n", " ", "+" + "-".repeat(30) + "+", " ",
					"" + "".repeat(30) + "", " ");
			System.out.printf("|%80s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%100s+\n", "-".repeat(100));
			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			// scan.nextLine();
			switch (choice) {
				case '1': {
					FineDAO.getInstance().displayAll();
					break;
				}
				case '2': {
					FineDAO.getInstance().displayLost();
					break;
				}
				case '3': {
					FineDAO.getInstance().displayOverdueReturn();
					break;
				}
				case '4': {
					FineDAO.getInstance().displayDamaged();
					break;
				}
				case '5': {

					TypeFinePage();
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

	public void ReservationPage() {
		while (true) {
			ClearScreen.clrscr();
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
			System.out.printf("|%10s%-7s%-24s%s%16s%-7s%-24s%s%10s|\n", " ", "|", "(1) All reservation", "|", " ",
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
			ClearScreen.clrscr();
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
			System.out.printf("|%10s%-11s%-20s%s%16s%-8s%-23s%s%10s|\n", " ", "|", "(5) Payment ", "|", " ",
					"|", "(6) Reservation", "|", " ");
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
				case '5': {
					ComingSoonPage();
					break;
				}
				case '6': {
					ReservationPage();
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
