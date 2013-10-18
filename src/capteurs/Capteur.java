package capteurs;

import lejos.nxt.ColorSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Capteur {

	private ICapteursFonctions capteur;

	public Capteur(ICapteursFonctions capteur) {
		this.capteur = capteur;

	}

	public int detecter() {
		return this.capteur.executerDetection();
	}
}