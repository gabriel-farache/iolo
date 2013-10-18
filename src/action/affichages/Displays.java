package action.affichages;

import lejos.nxt.LCD;

public class Displays {
	
	public void displaysNumber(int nbDisplay){
		LCD.drawInt(nbDisplay, 0, 0);
	}
}
