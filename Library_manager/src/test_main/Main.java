package test_main;

import java.util.Scanner;

import feature.FeatureImp;

public class Main {
	static Scanner scan = new Scanner(System.in);

	
	
	public static void Lmao() {
		while (true) {
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
			System.out.printf("|%3s| %-10s | %-27s | %-15s | %-20s | %-20s |%3s|\n", " ", "ID", "Name", "Joined date",
					"Category", "Phone", "");
			System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
					"-".repeat(17), "-".repeat(22), "-".repeat(22), "");
			System.out.printf("|%3s| %-10s | %-27s | %-15s | %-20s | %-20s |%3s|\n", " ", "20052", "Do Van Dat",
					"2024-02-13", "Pending Approval", "0987654356", "");
			System.out.printf("|%3s+%-12s+%-29s+%-17s+%-22s+%-22s+%3s|\n", " ", "-".repeat(12), "-".repeat(29),
					"-".repeat(17), "-".repeat(22), "-".repeat(22), "");
			System.out.printf("|%114s|\n", " ".repeat(114));
			System.out.printf("|%3s%-20s%91s|\n", " ", "(1) Accept all", "");
			System.out.printf("|%3s%-20s%91s|\n", " ", "(2) Accept by id", "");
			System.out.printf("|%114s|\n", " ".repeat(114));
			System.out.printf("|%94s%-20s|\n", " ", "(R) Return ->");
			System.out.printf("+%114s+\n", "-".repeat(114));

			System.out.print("Enter your choice: ");
			char choice = scan.nextLine().charAt(0);
			switch (choice) {
				case 'R': {
					return;
				}
				default:
					break;
			}
		}
	}

	

	public static void main(String[] args) {

		System.out.println("Login");
		FeatureImp featureImp = new FeatureImp();
		while (true) {
			featureImp.LoginForm();
			featureImp.HomePage();
			// Lmao();
		}

	}
}
