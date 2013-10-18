package controller;

import action.Moteur;
import capteurs.*;

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

	public void init() {
		trc = new Thread(new Capteur(new RightLightCapteur(), this));
		tmc = new Thread(new Capteur(new MiddleLightCapteur(), this));
		tuc = new Thread(new Capteur(new UltrasonCapteur(), this));
		tcc = new Thread(new Capteur(new ColorCapteur(), this));
		moteur = new Moteur();
	}

	public void start() {
		trc.start();
		tmc.start();
		tuc.start();
		tcc.start();
	}

	public void setRc(int rc) {
		this.rc = rc;
		if (Math.abs(rc - ICapteursFonctions.RIGHT_LIGHT_GRIS) < ICapteursFonctions.OFFSET) {
			estEnVirage = true ;
			moteur.ralentir(1f);
			virageImminent();
		} else if (Math.abs(rc - ICapteursFonctions.RIGHT_LIGHT_BLANC) < ICapteursFonctions.OFFSET) {
			moteur.turn(CORRECTION_ANGLE);
		} else if ((Math.abs(rc - ICapteursFonctions.RIGHT_LIGHT_NOIR) > ICapteursFonctions.OFFSET) && estEnVirage ) {
			estEnVirage = false ;
			moteur.accelerer(2);
		} else if (Math.abs(rc - ICapteursFonctions.RIGHT_LIGHT_NOIR) < ICapteursFonctions.OFFSET){
			moteur.turn(-CORRECTION_ANGLE);
		}
	}

	public void setMc(int mc) {
		this.mc = mc;
		if (Math.abs(mc - ICapteursFonctions.MIDDLE_LIGHT_NOIR) < ICapteursFonctions.OFFSET) {
			moteur.turn(0);
		} else {
			moteur.turn(-CORRECTION_ANGLE);
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
