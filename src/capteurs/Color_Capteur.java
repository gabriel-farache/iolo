package capteurs;

import lejos.nxt.SensorPort;

public class Color_Capteur implements ICapteursFonctions {

	@Override
	public int executerDetection() {
		return SensorPort.S1.readValue();
	}

}
