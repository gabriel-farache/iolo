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
	boolean debug = true;
	public static final int CORRECTION_ANGLE = 5;
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
		Thread.sleep(5);
		tmc.start();
		Thread.sleep(5);
		tuc.start();
		Thread.sleep(5);
		tcc.start();
		moteur.avancer(1f);
		while(true){
			handle();
		}
	}

	public void handle(){
		if (Math.abs(lc - Capteur.LEFT_LIGHT_GRIS) < ICapteursFonctions.OFFSET) {
			// LC = 0.5 -> Entrée virage
			moteur.ralentir(1f);
			if (debug) {
				LCD.clear();
				LCD.drawString("Entree virage", 0, 0);
			}
		} else if (Math.abs(lc - Capteur.LEFT_LIGHT_BLANC) < ICapteursFonctions.OFFSET
				&& (Math.abs(mc - Capteur.MIDDLE_LIGHT_NOIR) < ICapteursFonctions.OFFSET)) {
			// Cas normal : lc = 0 && mc = 1
			if (debug) {
				LCD.clear();
				LCD.drawString("Cas bon", 0, 0);
			}
		} else if ((Math.abs(lc - Capteur.LEFT_LIGHT_NOIR) < ICapteursFonctions.OFFSET)
				&& ((Math.abs(mc - Capteur.MIDDLE_LIGHT_BLANC) < ICapteursFonctions.OFFSET))) {
			// lc = 1 && mc != 0
			if (debug) {
				LCD.clear();
				LCD.drawString("Tourner gauche", 0, 0);
			}
			moteur.turnRight(CORRECTION_ANGLE);
		} else if ((Math.abs(lc - Capteur.LEFT_LIGHT_NOIR) < ICapteursFonctions.OFFSET)
				&& (Math.abs(mc - Capteur.MIDDLE_LIGHT_NOIR) < ICapteursFonctions.OFFSET)) {
			// lc = 1 && mc = 1
			estEnVirage = false;
			if (debug) {
				LCD.clear();
				LCD.drawString("Sortie virage", 0, 0);
			}
			moteur.accelerer(1.5f);

		} else if ((Math.abs(lc - Capteur.LEFT_LIGHT_BLANC) < ICapteursFonctions.OFFSET)
				&& (Math.abs(mc - Capteur.MIDDLE_LIGHT_BLANC) < ICapteursFonctions.OFFSET)) {
			moteur.turnLeft(CORRECTION_ANGLE);
			if (debug) {
				LCD.clear();
				LCD.drawString("Tourner droite", 0, 0);
			}
		}
	}
	
	public synchronized void setLc(int lc) {
		this.lc = lc;		
	}

	public synchronized void setMc(int mc) {
		this.mc = mc;
	}

	public synchronized void setUc(int uc) {
		this.uc = uc;
	}

	public synchronized void setCc(int cc) {
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
