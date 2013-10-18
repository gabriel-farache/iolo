package controller;

import action.Moteur;
import capteurs.Capteur;
import capteurs.ColorCapteur;
import capteurs.ICapteursFonctions;
import capteurs.LeftLightCapteur;
import capteurs.MiddleLightCapteur;
import capteurs.UltrasonCapteur;

public class CapteurController {
	public static final int CORRECTION_ANGLE = 7;
	int lc;
	int mc;
	int uc;
	int cc;
	Thread tlc;
	Thread tmc;
	Thread tuc;
	Thread tcc;
	Moteur moteur;
	boolean estEnVirage = false;

	public void init() {
		tlc = new Thread(new Capteur(new LeftLightCapteur(), this));
		tmc = new Thread(new Capteur(new MiddleLightCapteur(), this));
		tuc = new Thread(new Capteur(new UltrasonCapteur(), this));
		tcc = new Thread(new Capteur(new ColorCapteur(), this));
		moteur = new Moteur();
	}

	public void start() {
		// Capteur.calibrateLeft();
		// Capteur.calibrateMiddle();
		tlc.start();
		tmc.start();
		tuc.start();
		tcc.start();
	}

	public void setRc(int lc) {
		this.lc = lc;
		if (Math.abs(lc - Capteur.LEFT_LIGHT_GRIS) < ICapteursFonctions.OFFSET) {
			estEnVirage = true;
			moteur.ralentir(1f);
			virageImminent();
		} else if (Math.abs(lc - Capteur.LEFT_LIGHT_BLANC) < ICapteursFonctions.OFFSET) {
			moteur.turn(CORRECTION_ANGLE);
		} else if ((Math.abs(lc - Capteur.LEFT_LIGHT_NOIR) > ICapteursFonctions.OFFSET)
				&& estEnVirage) {
			estEnVirage = false;
			moteur.accelerer(2);
		} else if (Math.abs(lc - Capteur.LEFT_LIGHT_NOIR) < ICapteursFonctions.OFFSET) {
			moteur.turn(-CORRECTION_ANGLE);
		}
	}

	public void setMc(int mc) {
		this.mc = mc;
		if (Math.abs(mc - Capteur.MIDDLE_LIGHT_NOIR) < ICapteursFonctions.OFFSET) {
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
