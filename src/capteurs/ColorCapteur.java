package capteurs;

import lejos.nxt.SensorPort;

public class ColorCapteur implements ICapteursFonctions {

	@Override
	public int executerDetection() {
		return SensorPort.S1.readValue();
	}

}
