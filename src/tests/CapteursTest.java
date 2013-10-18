package tests;

import capteurs.Capteur;
import capteurs.ColorCapteur;
import capteurs.MiddleLightCapteur;
import capteurs.RightLightCapteur;
import capteurs.UltrasonCapteur;
import lejos.nxt.LCD;

public class CapteursTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LCD.drawString(
				"RL : " + (new Capteur(new RightLightCapteur())).detecter(), 0,
				0);
		try {
			Thread.sleep(1000);

			LCD.drawString("ML : "+ (new Capteur(new MiddleLightCapteur()))
									.detecter(), 0, 0);

			Thread.sleep(1000);

			LCD.drawString("Color : " + (new Capteur(new ColorCapteur())).detecter(),
					0, 0);

			Thread.sleep(1000);

			LCD.drawString("Ultrason : "+ (new Capteur(new UltrasonCapteur())).detecter()+ " cm",
					0, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
