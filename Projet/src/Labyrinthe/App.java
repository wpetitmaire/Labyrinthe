package Labyrinthe;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

	private static final long serialVersionUID = 1L;
	int windowWidth, windowHeight;
	String filePath = "niveaux\\lab.txt";
	Scanner scanner;
	PImage back, caseLab, imgPerso;
	Labyrinthe lab = new Labyrinthe(this, Constantes.NB_PIEGE);

	public void setup() {

		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Récupération de la taille de la fenÃªtre
		windowWidth = scanner.nextInt() * Constantes.TAILLE_SALLE + Constantes.DECALAGE_FENETRE;
		windowHeight = scanner.nextInt() * Constantes.TAILLE_SALLE+ Constantes.DECALAGE_FENETRE;
		// Chargement du labyrinthe dans une collection
		lab.load();

		// Chargement des diffÃ©rentes images
		back = loadImage("images\\back.jpg");
		caseLab = loadImage("images\\case.jpg");
		imgPerso = loadImage("images\\perso.png");
		size(windowWidth, windowHeight);
	}

	public void draw() {
		// Dessin du backgound et du labyrinthe
		image(back, 0, 0, windowWidth, windowHeight);
		lab.draw(caseLab, imgPerso);

		if (keyPressed == true)
			lab.keyPressed();

		// Si le personnage a atteint l'arrivée, on affiche un message
		if (lab.perso.getSalleCourante().x == lab.sortie.x
				&& lab.perso.getSalleCourante().y == lab.sortie.y) {
			textSize(40);
			text("Bravo !", windowWidth / 2, windowHeight / 2);
			fill(255, 255, 255);
		}
	}

	public static void main(String[] args) {
		PApplet.main("Application");
	}
}