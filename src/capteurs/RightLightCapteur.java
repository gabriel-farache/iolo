package capteurs;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class RightLightCapteur implements ICapteursFonctions{

	LightSensor light = new LightSensor(SensorPort.S3);
	@Override
	public int executerDetection() {
		SensorPort.S3.activate();
		return SensorPort.S3.readValue();
	}

}
