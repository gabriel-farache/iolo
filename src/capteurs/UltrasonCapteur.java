package capteurs;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class UltrasonCapteur implements ICapteursFonctions  {

	UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S4);

	@Override
	public int executerDetection() {
		SensorPort.S4.activate();
		Capteur.controller.setUc(sonic.getDistance());
		return sonic.getDistance();
	}

}
