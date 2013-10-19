package action;

import lejos.*;
import lejos.nxt.Button;
import lejos.nxt.Motor;


public class Moteur {
	
	private float speedB ;
	private float speedC ;
	
	public void avancer(float nbTour)
	{
		Button.waitForAnyPress();
		speedB = nbTour * 360 ;
		speedC = speedB ;
		Motor.B.setSpeed(speedB);
		Motor.C.setSpeed(speedC);
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
	
	public void turnLeft(int angle){
		Motor.B.rotate(angle,true);
		Motor.B.setSpeed(speedB);
		Motor.B.forward();
	}
	
	public void turnRight(int angle){
		Motor.C.rotate(angle,true);
		Motor.C.setSpeed(speedC);
		Motor.C.forward();
	}
	
	public void ralentir(float nbTour)
	{
		speedB = nbTour * 360;
		speedC = speedB ;
		Motor.B.setSpeed(speedB);
		Motor.C.setSpeed(speedC);
		//Motor.B.forward();
		//Motor.C.forward();
		
	}
	
	public void accelerer(float nbTour)
	{
		speedB = nbTour * 360;
		speedC = speedB ;
		Motor.B.setSpeed(speedB);
		Motor.C.setSpeed(speedC);
	}
	
}
