package capteurs;

import lejos.nxt.SensorPort;

public class Right_Middle_Capteur implements ICapteursFonctions {

	@Override
	public int executerDetection() {
		return SensorPort.S2.readValue();
	}

}
