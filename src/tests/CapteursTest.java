package tests;

import controller.CapteurController;
import capteurs.Capteur;
import capteurs.ColorCapteur;
import capteurs.MiddleLightCapteur;
import capteurs.LeftLightCapteur;
import capteurs.UltrasonCapteur;
import lejos.nxt.LCD;

public class CapteursTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CapteurController controller = new CapteurController();
		LCD.drawString(
				"RL : " + (new Capteur(new LeftLightCapteur(),controller)).detecter(), 0,
				0);
		try {
			Thread.sleep(1000);

			LCD.drawString("ML : "+ (new Capteur(new MiddleLightCapteur(),controller))
									.detecter(), 0, 0);

			Thread.sleep(1000);

			LCD.drawString("Color : " + (new Capteur(new ColorCapteur(),controller)).detecter(),
					0, 0);

			Thread.sleep(1000);

			LCD.drawString("Ultrason : "+ (new Capteur(new UltrasonCapteur(),controller)).detecter()+ " cm",
					0, 0);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
