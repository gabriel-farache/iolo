package capteurs;

import controller.CapteurController;
import lejos.nxt.ColorSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Capteur implements Runnable {
	public static final int SLEEP_TIME = 500 ;
	private ICapteursFonctions capteur;
	protected static CapteurController controller ;

	public Capteur(ICapteursFonctions capteur,CapteurController controller) {
		this.capteur = capteur;
		this.controller = controller ;
	}

	public int detecter() {
		return this.capteur.executerDetection();
	}

	@Override
	public void run() {
		while (true){
			this.capteur.executerDetection();
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException ignore) {
			}
		}
	}
}