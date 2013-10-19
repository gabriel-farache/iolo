package controller;

import javax.microedition.lcdui.Displayable;

import action.Moteur;
import capteurs.*;
import action.affichages.Displays;
import action.affichages.Son;

public class CapteurController {
	public static final int CORRECTION_ANGLE = 7;
	int rc;
	int mc;
	int uc;
	int cc;
	Thread trc;
	Thread tmc;
	Thread tuc;
	Thread tcc;
	Moteur moteur;
	boolean estEnVirage = false ;
	Displays display = new Displays();

	public void init() {
		trc = new Thread(new Capteur(new RightLightCapteur(), this));
		tmc = new Thread(new Capteur(new MiddleLightCapteur(), this));
		tuc = new Thread(new Capteur(new UltrasonCapteur(), this));
		tcc = new Thread(new Capteur(new ColorCapteur(), this));
		moteur = new Moteur();
		moteur.avancer(1f);
	}

	public void start(){
		//Capteur.calibrateRight();
		//Capteur.calibrateMiddle();
		trc.start();
		tmc.start();
		tuc.start();
		tcc.start();
	}

	public void setRc(int rc) {
		this.rc = rc;
		if (Math.abs(rc - Capteur.RIGHT_LIGHT_GRIS) < ICapteursFonctions.OFFSET) {
			//estEnVirage = true ;
			moteur.ralentir(0.5f);
			display.displaysNumber(1000);
			//virageImminent();
			
		} else if (Math.abs(rc - Capteur.RIGHT_LIGHT_BLANC) < ICapteursFonctions.OFFSET) {
			display.displaysNumber(2000);
		} else if ((Math.abs(rc - Capteur.RIGHT_LIGHT_NOIR) < ICapteursFonctions.OFFSET) && estEnVirage 
				&& !(Math.abs(mc - Capteur.RIGHT_LIGHT_NOIR) < ICapteursFonctions.OFFSET) ) {
			display.displaysNumber(3000);
			moteur.turn(CORRECTION_ANGLE);
		} else if ((Math.abs(rc - Capteur.RIGHT_LIGHT_NOIR) < ICapteursFonctions.OFFSET) 
				&& (Math.abs(mc - Capteur.RIGHT_LIGHT_NOIR) < ICapteursFonctions.OFFSET)){
			estEnVirage = false ;
			display.displaysNumber(4000);
			moteur.accelerer(1f);
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
