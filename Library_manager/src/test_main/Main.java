package test_main;




import feature.FeatureImp;

public class Main {

	public static void main(String[] args) {

		System.out.println("Login");
		FeatureImp featureImp = new FeatureImp();
		while (true) {
			featureImp.LoginForm();
			featureImp.HomePage();
		}

	}
}
