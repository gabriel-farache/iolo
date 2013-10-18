package capteurs;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;

public class ColorCapteur implements ICapteursFonctions {

	ColorSensor light = new ColorSensor(SensorPort.S1);
	@Override
	public int executerDetection() {
		return SensorPort.S1.readValue();
	}

}
