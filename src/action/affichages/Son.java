package action.affichages;

import java.io.File;

import lejos.nxt.Sound;

public class Son {
	public Son() {
	}

	public void playLego(int nbToRead) {
		switch (nbToRead) {
		case 0:
			Sound.playSample(new File("00.wav"));
			break;
		case 1:
			Sound.playSample(new File("01.wav"));
			break;
		case 2:
			Sound.playSample(new File("02.wav"));
			break;
		case 3:
			Sound.playSample(new File("03.wav"));
			break;
		case 4:
			Sound.playSample(new File("04.wav"));
			break;
		case 5:
			Sound.playSample(new File("05.wav"));
			break;
		case 6:
			Sound.playSample(new File("06.wav"));
			break;
		case 7:
			Sound.playSample(new File("07.wav"));
			break;
		case 8:
			Sound.playSample(new File("08.wav"));
			break;
		case 9:
			Sound.playSample(new File("09.wav"));
			break;
		case 10:
			Sound.playSample(new File("10.wav"));
			break;
		default:
			break;
		}

	}
}
