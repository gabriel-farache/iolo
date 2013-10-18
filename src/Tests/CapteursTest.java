package Tests;

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
		LCD.drawInt((new Capteur(new RightLightCapteur())).detecter(), 0, 0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LCD.drawInt((new Capteur(new MiddleLightCapteur())).detecter(), 0, 0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LCD.drawInt((new Capteur(new ColorCapteur())).detecter(), 0, 0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LCD.drawString((new Capteur(new UltrasonCapteur())).detecter()+" cm", 0, 0);
		
	}

}
