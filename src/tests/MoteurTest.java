package tests;

import lejos.nxt.Button;
import action.Moteur;

public class MoteurTest {

	public static void main(String[] args) {
		
		Moteur mot = new Moteur();
		mot.avancer(2);
		Button.waitForAnyPress();
		mot.stop();
		
		
		
	}
}
