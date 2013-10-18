package tests;

import action.affichages.Displays;

public class DisplayTest {
	public static void main(String[] args) {
		Displays displays = new Displays();
		displays.displaysNumber(10);
		try {
			Thread.sleep(1000);

			displays.displaysNumber(100);
			Thread.sleep(1000);
			displays.displaysNumber(1000);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
