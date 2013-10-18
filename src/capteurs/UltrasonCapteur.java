package capteurs;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class UltrasonCapteur implements ICapteursFonctions  {

	UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S4);
	
	@Override
	public int executerDetection() {
		return sonic.getDistance();
	}

}
