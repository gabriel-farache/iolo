package capteurs;

import lejos.nxt.SensorPort;

public class MiddleLightCapteur implements ICapteursFonctions {

	@Override
	public int executerDetection() {
		return SensorPort.S2.readValue();
	}

}
