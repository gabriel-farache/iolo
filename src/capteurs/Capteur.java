package capteurs;

import controller.CapteurController;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Capteur implements Runnable {
	public static int RIGHT_LIGHT_NOIR = 30;
	public static int RIGHT_LIGHT_BLANC = 60;
	public static int RIGHT_LIGHT_GRIS = 40;

	public static int MIDDLE_LIGHT_NOIR = 37;
	public static int MIDDLE_LIGHT_BLANC = 66;
	public static int MIDDLE_LIGHT_GRIS = 48;
	public static final int SLEEP_TIME = 200;
	private ICapteursFonctions capteur;
	protected static CapteurController controller;

	public Capteur(ICapteursFonctions capteur, CapteurController controller) {
		this.capteur = capteur;
		this.controller = controller;
	}

	public int detecter() {
		return this.capteur.executerDetection();
	}

	@Override
	public void run() {
		while (true) {
			this.capteur.executerDetection();
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException ignore) {
			}
		}
	}

	public static void calibrateRight() {
		LCD.drawString("Droite blanc", 0, 0);
		while (!Button.ENTER.isDown()) {

		}
		LCD.clear();
		Capteur.RIGHT_LIGHT_BLANC = SensorPort.S3.readValue();
		LCD.drawString("Droite gris", 0, 0);
		while (!Button.ENTER.isDown()) {

		}
		LCD.clear();
		Capteur.RIGHT_LIGHT_GRIS = SensorPort.S3.readValue();
		LCD.drawString("Droite noir", 0, 0);
		while (!Button.ENTER.isDown()) {

		}
		Capteur.RIGHT_LIGHT_NOIR = SensorPort.S3.readValue();
	}

	public static void calibrateMiddle() {
		LCD.clear();
		LCD.drawString("Milieu blanc", 0, 0);
		while (!Button.ENTER.isDown()) {

		}
		Capteur.MIDDLE_LIGHT_BLANC = SensorPort.S3.readValue();
		LCD.clear();
		LCD.drawString("Milieu gris", 0, 0);
		while (!Button.ENTER.isDown()) {

		}
		Capteur.MIDDLE_LIGHT_GRIS = SensorPort.S3.readValue();
		LCD.clear();
		LCD.drawString("Milieu noir", 0, 0);
		while (!Button.ENTER.isDown()) {

		}
		Capteur.MIDDLE_LIGHT_NOIR = SensorPort.S3.readValue();
	}
}