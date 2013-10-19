package capteurs;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import controller.CapteurController;

public class Capteur implements Runnable {
	public static int LEFT_LIGHT_NOIR = 30;
	public static int LEFT_LIGHT_BLANC = 60;
	public static int LEFT_LIGHT_GRIS = 40;

	public static int MIDDLE_LIGHT_NOIR = 37;
	public static int MIDDLE_LIGHT_BLANC = 66;
	public static int MIDDLE_LIGHT_GRIS = 48;
	public static final int SLEEP_TIME = 20;
	private ICapteursFonctions capteur;
	protected static CapteurController controller;

	public Capteur(ICapteursFonctions capteur, CapteurController controller) {
		this.capteur = capteur;
		Capteur.controller = controller;
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

	public static void calibrateLeft() {
		LCD.drawString("Gauche blanc", 0, 0);
		Button.ENTER.waitForPress();
		LCD.clear();
		Capteur.LEFT_LIGHT_BLANC = SensorPort.S3.readValue();
		LCD.drawString(Capteur.LEFT_LIGHT_BLANC+"  Gauche gris", 0, 0);
		Button.ENTER.waitForPress();
		LCD.clear();
		Capteur.LEFT_LIGHT_GRIS = SensorPort.S3.readValue();
		LCD.drawString(Capteur.LEFT_LIGHT_GRIS+"  Gauche noir", 0, 0);
		Button.ENTER.waitForPress();
		Capteur.LEFT_LIGHT_NOIR = SensorPort.S3.readValue();
	}

	public static void calibrateMiddle() {
		LCD.clear();
		LCD.drawString(LEFT_LIGHT_NOIR+"  Milieu blanc", 0, 0);
		Button.ENTER.waitForPress();
		Capteur.MIDDLE_LIGHT_BLANC = SensorPort.S2.readValue();
		LCD.clear();
		LCD.drawString(MIDDLE_LIGHT_BLANC+"  Milieu gris", 0, 0);
		Button.ENTER.waitForPress();
		Capteur.MIDDLE_LIGHT_GRIS = SensorPort.S2.readValue();
		LCD.clear();
		LCD.drawString(MIDDLE_LIGHT_GRIS+"  Milieu noir", 0, 0);
		Button.ENTER.waitForPress();
		Capteur.MIDDLE_LIGHT_NOIR = SensorPort.S2.readValue();
		LCD.clear();
		LCD.drawInt(Capteur.MIDDLE_LIGHT_NOIR, 0, 0);

	}
}