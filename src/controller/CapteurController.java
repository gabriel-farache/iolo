package controller;

import lejos.nxt.LCD;
import lejos.util.Delay;
import action.Moteur;
import action.affichages.Displays;
import action.affichages.Son;
import capteurs.Capteur;
import capteurs.ColorCapteur;
import capteurs.ICapteursFonctions;
import capteurs.LeftLightCapteur;
import capteurs.MiddleLightCapteur;
import capteurs.UltrasonCapteur;

public class CapteurController {
	boolean debug = true;
	public static final int CORRECTION_ANGLE = 2;
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
	boolean turnLeftLast = false;
	boolean turnRightLast = false;
	Displays display = new Displays();
	private int couleur = 0;
	private static int tour = 0;
	Son son;
	public static boolean continu = true;
	public boolean premCode = false;
	public boolean secCode = false;

	public void init() {
		tlc = new Thread(new Capteur(new LeftLightCapteur(), this));
		tmc = new Thread(new Capteur(new MiddleLightCapteur(), this));
		tuc = new Thread(new Capteur(new UltrasonCapteur(), this));
		tcc = new Thread(new Capteur(new ColorCapteur(), this));
		son = new Son();
		moteur = new Moteur();
	}

	public void start() throws InterruptedException {
		Capteur.calibrateLeft();
		Capteur.calibrateMiddle();
		tlc.start();
		tmc.start();
		tuc.start();
		tcc.start();
		moteur.avancer();
		while (true) {
			handle();
			handleTour();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void handleTour() {
		if ((uc < 26) && tour <= 3) {
			tour++;
			son.playLego(tour);
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (tour == 3) {
			moteur.stop();
			continu = false;
		}
	}

	public void handle() {
		/*
		 * if ((lc <= Capteur.LEFT_LIGHT_GRIS + ICapteursFonctions.OFFSET) &&
		 * (lc >= Capteur.LEFT_LIGHT_GRIS - ICapteursFonctions.OFFSET)) { // LC
		 * = 0.5 -> Entrée virage
		 * 
		 * if (debug) { LCD.drawString("Entree virage", 0, 0); } estEnVirage =
		 * true; moteur.ralentir();
		 * 
		 * } else if (Math.abs(lc - Capteur.LEFT_LIGHT_BLANC) <=
		 * ICapteursFonctions.OFFSET && (Math.abs(mc -
		 * Capteur.MIDDLE_LIGHT_NOIR) <= ICapteursFonctions.OFFSET)) { // Cas
		 * normal : lc = 0 && mc = 1 if (debug) { LCD.drawString("Cas bon", 0,
		 * 0); }
		 * 
		 * } else if ((Math.abs(lc - Capteur.LEFT_LIGHT_NOIR) <=
		 * ICapteursFonctions.OFFSET) && (!(Math.abs(mc -
		 * Capteur.MIDDLE_LIGHT_NOIR) <= ICapteursFonctions.OFFSET))) { // lc =
		 * 1 && mc != 0 if (debug) { LCD.drawString("Tourner gauche", 0, 0); }
		 * moteur.turnLeft(estEnVirage ? 1 : 0); } else if ((Math.abs(lc -
		 * Capteur.LEFT_LIGHT_NOIR) <= ICapteursFonctions.OFFSET) &&
		 * (Math.abs(mc - Capteur.MIDDLE_LIGHT_NOIR) <=
		 * ICapteursFonctions.OFFSET)) { // je sors du virage, je vois noir sur
		 * middle et ggauche // je reaccelere estEnVirage = false; if (debug) {
		 * LCD.drawString("Sortie virage", 0, 0); } moteur.accelerer(); } else
		 * if ((!(Math.abs(mc - Capteur.MIDDLE_LIGHT_NOIR) <=
		 * ICapteursFonctions.OFFSET)) && (Math.abs(lc -
		 * Capteur.LEFT_LIGHT_BLANC) <= ICapteursFonctions.OFFSET)) { if (debug)
		 * { LCD.drawString("Tourner a droite", 0, 0); }
		 * moteur.turnRight(estEnVirage ? 1 : 0); } else if ((Math.abs(lc -
		 * Capteur.LEFT_LIGHT_GRIS) <= ICapteursFonctions.OFFSET) &&
		 * (Math.abs(mc - Capteur.MIDDLE_LIGHT_BLANC) <=
		 * ICapteursFonctions.OFFSET)) { if (debug) {
		 * LCD.drawString("Tourner a gauche", 0, 0); }
		 * moteur.turnLeft(estEnVirage ? 1 : 0); } else { moteur.accelerer(); }
		 */
		int moyLc = (Capteur.LEFT_LIGHT_NOIR + Capteur.LEFT_LIGHT_BLANC) / 2;
		int moyMc = (Capteur.MIDDLE_LIGHT_BLANC + Capteur.MIDDLE_LIGHT_NOIR) / 2;
		
		if (lc <= moyLc - ICapteursFonctions.OFFSET) {
			moteur.ralentir();
			moteur.turnLeft(estEnVirage ? 1 : 0);
			LCD.clear();
			LCD.drawString("Tourner a gauche", 0, 0); 
			LCD.drawString("lc :"+lc+" - moy :"+moyLc, 0, 4);
			estEnVirage = true;
		}
		if (mc >= moyMc + ICapteursFonctions.OFFSET) {
			moteur.turnRight(estEnVirage ? 1 : 0);
			LCD.clear();
			LCD.drawString("Tourner a droite", 0, 0); 
			LCD.drawString("mc :"+mc+" - moy :"+moyMc, 0, 4);
		} else if (mc <= moyMc - ICapteursFonctions.OFFSET) {
			moteur.turnLeft(estEnVirage ? 1 : 0);
			LCD.clear();
			LCD.drawString("Tourner a gauche", 0, 0); 
			LCD.drawString("mc :"+mc+" - moy :"+moyMc, 0, 4);
		} else if (lc <= moyLc - ICapteursFonctions.OFFSET) {
			moteur.accelerer();
			LCD.clear();
			LCD.drawString("Accelerer", 0, 0); 
			LCD.drawString("lc :"+lc+" - moy :"+moyLc, 0, 4);
			estEnVirage = false;
		} else if (lc >= moyLc + ICapteursFonctions.OFFSET){
			moteur.turnRight(estEnVirage ? 1 : 0);
			LCD.clear();
			LCD.drawString("Tourner a droite", 0, 0); 
			LCD.drawString("lc :"+lc+" - moy :"+moyLc, 0, 4);
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
			if (tour == 2) {
				premCode = true;
			}
			break;
		case ICapteursFonctions.COLOR_JAUNE:

			break;
		case ICapteursFonctions.COLOR_NOIR:
			couleur = 4;
			break;
		case ICapteursFonctions.COLOR_ROUGE:
			if (premCode) {
				secCode = true;
			}
			couleur = 3;
			break;
		case ICapteursFonctions.COLOR_VERT:
			couleur = 2;
			break;
		case ICapteursFonctions.COLOR_WHITE:
			couleur = estEnVirage ? couleur : 0;
			if (premCode && secCode) {
				moteur.stop();
			}
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
