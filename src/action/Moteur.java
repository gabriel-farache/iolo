package action;

import java.util.HashMap;
import java.util.Map;

import lejos.nxt.BasicMotor;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class Moteur extends BasicMotor {
	public static boolean semaphore = true;
	private static int speedMax = 87;
	private NXTMotor motorLeft;
	private NXTMotor motorRight;
	private Map<Integer,Integer> mapVirage ;

	public Moteur() {
		motorLeft = new NXTMotor(MotorPort.B);
		motorRight = new NXTMotor(MotorPort.C);
		mapVirage = new HashMap<>();
		mapVirage.put(0, 10);
		mapVirage.put(1, 30);
		mapVirage.put(2,18);
		mapVirage.put(3,24);
		mapVirage.put(4,30);
	}

	public void avancer() {
		Button.waitForAnyPress();
		motorLeft.setPower(speedMax);
		motorRight.setPower(speedMax);
	}

	public void stop() {
		motorLeft.setPower(0);
		motorRight.setPower(0);
	}

	public void turnLeftRac(){
		motorLeft.setPower(speedMax);
		motorRight.setPower(speedMax);
		Motor.B.rotate(90);
	}
	
	public void turnRightRac(){
		motorLeft.setPower(speedMax);
		motorRight.setPower(speedMax);
		Motor.C.rotate(-90);
	}
	
	public void turnLeft(int virage) {
		motorLeft.setPower(speedMax - mapVirage.get(virage));
		motorRight.setPower(speedMax);
	}

	public void turnRight(int virage) {
		motorRight.setPower(speedMax - mapVirage.get(virage));
		motorLeft.setPower(speedMax);		
	}

	public void ralentir() {
		speedMax = 67;
		motorLeft.setPower(speedMax);
		motorRight.setPower(speedMax);

	}

	public void accelerer() {
		speedMax = 80;
		motorLeft.setPower(speedMax);
		motorRight.setPower(speedMax);
	}

}
