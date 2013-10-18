package capteurs;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class MiddleLightCapteur implements ICapteursFonctions {

	LightSensor light_middle = new LightSensor(SensorPort.S2);

	@Override
	public int executerDetection() {
		SensorPort.S2.activate();
		return SensorPort.S2.readValue();
	}

}
