package controller;

import lejos.nxt.LCD;
import action.Moteur;
import action.affichages.Displays;
import capteurs.Capteur;
import capteurs.ColorCapteur;
import capteurs.ICapteursFonctions;
import capteurs.LeftLightCapteur;
import capteurs.MiddleLightCapteur;
import capteurs.UltrasonCapteur;

public class CapteurController {
	public static final int CORRECTION_ANGLE = 30;
	int lc;
	int mc;
	int uc;
	int cc;
	Thread tlc;
	Thread tmc;
	Thread tuc;
	Thread tcc;
	Moteur moteur;
	boolean estEnVirage = false ;
	Displays display = new Displays();


	public void init() {
		tlc = new Thread(new Capteur(new LeftLightCapteur(), this));
		tmc = new Thread(new Capteur(new MiddleLightCapteur(), this));
		tuc = new Thread(new Capteur(new UltrasonCapteur(), this));
		tcc = new Thread(new Capteur(new ColorCapteur(), this));
		moteur = new Moteur();
	}

	public void start() throws InterruptedException {
		Capteur.calibrateLeft();
		Capteur.calibrateMiddle();
		tlc.start();
		Thread.sleep(100);
		tmc.start();
		Thread.sleep(100);
		tuc.start();
		Thread.sleep(100);
		tcc.start();
		moteur.avancer(1f);
	}

	public void setLc(int lc) {
		this.lc = lc;
		if (Math.abs(lc - Capteur.LEFT_LIGHT_GRIS) < ICapteursFonctions.OFFSET) {
			moteur.ralentir(0.5f);
			display.displaysNumber(1000);
			LCD.clear();
			LCD.drawString("t1 : GRIS - NOIR", 0, 0);
			
		} else if (Math.abs(lc - Capteur.LEFT_LIGHT_BLANC) < ICapteursFonctions.OFFSET) {
			display.displaysNumber(2000);
			LCD.clear();
			LCD.drawString("t2 : BLANC - NOIR", 0, 0);
		} else if ((Math.abs(lc - Capteur.LEFT_LIGHT_NOIR) < ICapteursFonctions.OFFSET) 
				&& (!(Math.abs(mc - Capteur.MIDDLE_LIGHT_NOIR) < ICapteursFonctions.OFFSET))) {
			LCD.clear();
			LCD.drawString("t3 : NOIR - AUTRE", 0, 0);
			display.displaysNumber(3000);
			moteur.turnRight(-CORRECTION_ANGLE);
		} else if ((Math.abs(lc - Capteur.LEFT_LIGHT_NOIR) < ICapteursFonctions.OFFSET) 
				&& (Math.abs(mc - Capteur.MIDDLE_LIGHT_NOIR) < ICapteursFonctions.OFFSET)){
			estEnVirage = false ;
			display.displaysNumber(4000);
			moteur.accelerer(1f);
			LCD.clear();
			LCD.drawString("t4 : NOIR - NOIR", 0, 0);
		} else {
			LCD.clear();
			LCD.drawString("ERREUR LC: "+lc, 0, 0);
		}
	}

	public void setMc(int mc) {
		this.mc = mc;
		if (Math.abs(mc - Capteur.MIDDLE_LIGHT_NOIR) < ICapteursFonctions.OFFSET) {
			//display.displaysNumber(1000);
			
		} else {
			//display.displaysNumber(2000);
			//moteur.turn(-CORRECTION_ANGLE);
		}
	}

	public void setUc(int uc) {
		this.uc = uc;
	}

	public void setCc(int cc) {
		this.cc = cc;
		switch (cc) {
		case ICapteursFonctions.COLOR_BLEU:
			break;
		case ICapteursFonctions.COLOR_JAUNE:
			break;
		case ICapteursFonctions.COLOR_NOIR:
			break;
		case ICapteursFonctions.COLOR_ROUGE:
			break;
		case ICapteursFonctions.COLOR_VERT:
			break;
		case ICapteursFonctions.COLOR_WHITE:
			break;
		default:
			break;
		}

	}

	public int getLc() {
		return lc;
	}

	public int getMc() {
		return mc;
	}

	public int getUc() {
		return uc;
	}

	public int getCc() {
		return cc;
	}

	public void virageImminent() {
		switch (cc) {
		case ICapteursFonctions.COLOR_NOIR:
			break;
		case ICapteursFonctions.COLOR_ROUGE:
			break;
		case ICapteursFonctions.COLOR_VERT:
			break;
		default:
			break;
		}
	}
}
