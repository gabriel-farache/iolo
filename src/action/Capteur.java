package action;

import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class Capteur {
	
	public static void main(String[] args) throws Exception {
	    LightSensor lightR = new LightSensor(SensorPort.S3);
	    LightSensor lightM = new LightSensor(SensorPort.S2);
		ColorSensor color = new ColorSensor(SensorPort.S1);

	    while (true) {
	      LCD.drawInt(lightR.getLightValue(), 4, 0, 0);
	      LCD.drawInt(lightR.getNormalizedLightValue(), 4, 0, 1);   
	      
	     // LCD.drawInt(lightM.getLightValue(), 4, 0, 4);
	      //LCD.drawInt(lightM.getNormalizedLightValue(), 4, 0, 5);
	      
	    //  LCD.drawInt(color.getLightValue(), 4, 0, 8);
	    //  LCD.drawInt(color.getNormalizedLightValue(), 4, 0, 9);
	      
	      LCD.drawInt(SensorPort.S3.readRawValue(), 4, 0, 2);
	      LCD.drawInt(SensorPort.S3.readValue(), 4, 0, 3);
	      
	     // LCD.drawInt(SensorPort.S2.readRawValue(), 4, 0, 6);
	     // LCD.drawInt(SensorPort.S2.readValue(), 4, 0, 7);
	      
	     // LCD.drawInt(SensorPort.S1.readRawValue(), 4, 0, 10);
	     // LCD.drawInt(SensorPort.S1.readValue(), 4, 0, 11);
	    }
	  }
}