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
