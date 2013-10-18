package capteurs;

import lejos.nxt.SensorPort;

public class Right_Light_Capteur implements ICapteursFonctions{

	@Override
	public int executerDetection() {
		return SensorPort.S3.readValue();
	}

}
