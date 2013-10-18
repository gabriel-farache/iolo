package capteurs;

public interface ICapteursFonctions {
	public final static int OFFSET = 4;
	
	public final static int RIGHT_LIGHT_NOIR = 30;
	public final static int RIGHT_LIGHT_BLANC = 60;
	public final static int RIGHT_LIGHT_GRIS = 40;
	
	public final static int MIDDLE_LIGHT_NOIR = 37;
	public final static int MIDDLE_LIGHT_BLANC = 66;
	public final static int MIDDLE_LIGHT_GRIS = 48;
	
	public final static int COLOR_JAUNE = 4;
	public final static int COLOR_ROUGE = 5;
	public final static int COLOR_VERT = 3;
	public final static int COLOR_BLEU = 2;
	public final static int COLOR_NOIR = 1;
	public final static int COLOR_WHITE = 6;
	
	public int executerDetection();

}
