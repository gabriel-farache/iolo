package capteurs;

import lejos.nxt.SensorPort;

public class RightLightCapteur implements ICapteursFonctions{

	@Override
	public int executerDetection() {
		return SensorPort.S3.readValue();
	}

}
