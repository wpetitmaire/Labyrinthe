package Labyrinthe;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

// lire fichier texte : String[] lignes = load String("lab.txt");
// Pour découper chaîne des espaces : split

public class App extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int windowWidth, windowHeight;
	String filePath = "niveaux\\lab.txt";
	Scanner scanner;
	PImage back, caseLab, imgPerso;

	Labyrinthe lab = new Labyrinthe(this);

	public void setup() {

		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		windowWidth = scanner.nextInt() * 22 + 50;
		windowHeight = scanner.nextInt() * 22 + 50;
		lab.load();

		back = loadImage("images\\back.jpg");
		caseLab = loadImage("images\\case.jpg");
		imgPerso = loadImage("images\\perso.png");
		size(windowWidth, windowHeight);

	}

	public void draw() {
		image(back, 0, 0, windowWidth, windowHeight);
		lab.draw(caseLab, imgPerso);
		if (keyPressed)
			lab.keyPressed();
	}

	public static void main(String[] args) {
		PApplet.main("Application");
	}
}
