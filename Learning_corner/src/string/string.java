package string;

import java.util.Scanner;
public class string {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = "Delete from Book where id in ( ";
		System.out.print("Enter amount: ");
		int amount = scan.nextInt();
		for (int i = 0; i < amount; i++) {
			System.out.print("Enter id: ");
			int id = scan.nextInt();
			if (i == 0) {
				s = s + id;
			} else {
				s = s + ", " + id;
			}
		}
		s += ")";
		System.out.println(s);
	}

}
