package capteurs;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;

public class ColorCapteur implements ICapteursFonctions {

	ColorSensor color = new ColorSensor(SensorPort.S1);

	@Override
	public int executerDetection() {
		SensorPort.S1.activate();
		Capteur.controller.setCc(SensorPort.S1.readValue());
		
		return Capteur.controller.getCc();
	}

}
