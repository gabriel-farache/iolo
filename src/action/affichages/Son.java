package action.affichages;

import java.io.File;

import lejos.nxt.Sound;

public class Son {
	private File fichierSon1;
	private File fichierSon2;
	private File fichierSon3;
	private static final String urlFichierSonTour1 = "/home/etiik/Bureau/aircraft001.wav";
	private static final String urlFichierSonTour2 = "";
	private static final String urlFichierSonTour3 = "";

	public Son() {
		this.fichierSon1 = new File(urlFichierSonTour1);
		this.fichierSon2 = new File(urlFichierSonTour2);
		this.fichierSon3 = new File(urlFichierSonTour3);
	}

	public void playLego(int nbTour) {
		switch (nbTour) {
		case 1:
			Sound.playSample(fichierSon1);
			break;
		case 2:
			Sound.playSample(fichierSon2);
			break;
		case 3:
			Sound.playSample(fichierSon3);
			break;
		default:
			break;
		}

	}
}
