package capteurs;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LeftLightCapteur implements ICapteursFonctions{

	LightSensor light = new LightSensor(SensorPort.S3);
	@Override
	public int executerDetection() {
		SensorPort.S3.activate();
		Capteur.controller.setLc(SensorPort.S3.readValue());
		
		return Capteur.controller.getLc();
	}

}
