package capteurs;

public class Capteur {

	private ICapteursFonctions capteur;

	public Capteur(ICapteursFonctions capteur) {
		this.capteur = capteur;
	}
	
	public int detecterColeur() {
		return this.capteur.executerDetection();
	}
}