package capteurs;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class MiddleLightCapteur implements ICapteursFonctions {

	LightSensor light = new LightSensor(SensorPort.S2);
	@Override
	public int executerDetection() {
		return SensorPort.S2.readValue();
	}

}
