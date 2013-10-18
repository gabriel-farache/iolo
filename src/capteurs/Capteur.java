package capteurs;

import controller.CapteurController;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
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
	
	public static void calibrateRight() {
		LCD.drawString("Droite blanc", 0, 0);
		while(!Button.ENTER.isDown()) {
			
		}
		ICapteursFonctions.RIGHT_LIGHT_BLANC = SensorPort.S3.readValue();
		LCD.drawString("Droite gris", 0, 0);
		while(!Button.ENTER.isDown()) {
			
		}
		ICapteursFonctions.RIGHT_LIGHT_GRIS = SensorPort.S3.readValue();
		LCD.drawString("Droite noir", 0, 0);
		while(!Button.ENTER.isDown()) {
			
		}
		ICapteursFonctions.RIGHT_LIGHT_NOIR = SensorPort.S3.readValue();
	}
	
	public static void calibrateMiddle() {
		LCD.drawString("Milieu blanc", 0, 0);
		while(!Button.ENTER.isDown()) {
			
		}
		ICapteursFonctions.MIDDLE_LIGHT_BLANC = SensorPort.S3.readValue();
		LCD.drawString("Milieu gris", 0, 0);
		while(!Button.ENTER.isDown()) {
			
		}
		ICapteursFonctions.MIDDLE_LIGHT_GRIS = SensorPort.S3.readValue();
		LCD.drawString("Milieu noir", 0, 0);
		while(!Button.ENTER.isDown()) {
			
		}
		ICapteursFonctions.MIDDLE_LIGHT_NOIR = SensorPort.S3.readValue();
	}
}