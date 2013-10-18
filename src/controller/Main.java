package controller;

import java.io.File;

import lejos.nxt.LCD;
import lejos.nxt.Sound;
import action.affichages.Son;

public class Main {

	public static void main(String[] args) {
		CapteurController controller = new CapteurController();
		controller.init();
	}

}
