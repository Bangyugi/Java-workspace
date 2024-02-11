package test_main;

import java.util.Scanner;

import database.JDBCUtil;
import feature.FeatureImp;

public class Main {
	static Scanner scan = new Scanner(System.in);

	
	
	public static void Lmao() {
		while (true) {
			
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
