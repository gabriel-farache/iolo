package action;

import lejos.*;
import lejos.nxt.Motor;


public class Moteur {
	
	public void avancer(float nbTour)
	{
		float speed = nbTour * 360;
		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);
		Motor.B.forward();
		Motor.C.forward();
		
	}
	
	public void stop()
	{
		Motor.B.stop();
		Motor.C.stop();
	}
	
	public void turn(int angle)
	{
		Motor.B.rotate(angle);
		Motor.C.rotate(angle);
	}
	
	public void ralentir(float nbTour)
	{
		float speed = nbTour * 360;
		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);
		Motor.B.forward();
		Motor.C.forward();
		
	}
	
	public void accelerer(int acceleration)
	{
		Motor.B.setAcceleration(acceleration);
		Motor.C.setAcceleration(acceleration);
	}
	
}
