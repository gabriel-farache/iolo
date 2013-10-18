package controller;

import java.io.File;

import lejos.nxt.LCD;
import lejos.nxt.Sound;
import action.affichages.Son;

public class Main {

	public static void main(String[] args) {
		Son son = new Son();
		for (int i=0;i<11;i++){
			son.playLego(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
