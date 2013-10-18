package controller;

import action.Moteur;
import action.affichages.Son;
import capteurs.* ;

public class CapteurController {
	public static final int CORRECTION_ANGLE = 7 ;
	int rc ;
	int mc ;
	int uc ;
	int cc ;
	Thread trc ;
	Thread tmc ;
	Thread tuc ;
	Thread tcc ;
	Moteur moteur ;
	
	public void init(){
		trc = new Thread(new Capteur(new RightLightCapteur(),this));		
		tmc = new Thread(new Capteur(new MiddleLightCapteur(),this));
		tuc = new Thread(new Capteur(new UltrasonCapteur(),this));
		tcc = new Thread(new Capteur(new ColorCapteur(),this));
		moteur = new Moteur();
	}

	public void start(){
		Capteur.calibrateRight();
		Capteur.calibrateMiddle();
		trc.start();
		tmc.start();
		tuc.start();
		tcc.start();
	}
	
	public void setRc(int rc) {
		this.rc = rc;
		if (Math.abs(rc - ICapteursFonctions.RIGHT_LIGHT_NOIR)>ICapteursFonctions.OFFSET){
			moteur.turn(-CORRECTION_ANGLE);
		}
	}

	public void setMc(int mc) {
		this.mc = mc;
		if (Math.abs(mc - ICapteursFonctions.MIDDLE_LIGHT_NOIR)>ICapteursFonctions.OFFSET){
			moteur.turn(CORRECTION_ANGLE);
		}
	}

	public void setUc(int uc) {
		this.uc = uc;
	}

	public void setCc(int cc) {
		this.cc = cc;
		switch(cc){
		case ICapteursFonctions.COLOR_BLEU:
			break ;
		case ICapteursFonctions.COLOR_JAUNE:
			break;
		case ICapteursFonctions.COLOR_NOIR:
			break;
		case ICapteursFonctions.COLOR_ROUGE:
			break;
		case ICapteursFonctions.COLOR_VERT:
			break ;
		case ICapteursFonctions.COLOR_WHITE:
			break;
		default:
			break;
		}
		if(cc >= 0 && cc <= 10) {
			new Son().playLego(cc);
		}
	}
}
