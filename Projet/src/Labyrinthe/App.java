package Labyrinthe;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author wpeti_000
 * 
 *         Cette classe définie notre application.
 */
@SuppressWarnings("serial")
public class App extends PApplet {

	etatDuJeu etat;
	int windowWidth, windowHeight;
	String filePath;
	Scanner scanner;
	PImage back, caseLab, imgPerso, vie, vie2, vie3, imgMonstre;
	Labyrinthe lab;
	int numeroNiveau;

	/**
	 * La méthode setup() initialise la fenêtre du jeu : la dimension de la
	 * fenêtre, les images, les niveaux. On initialise le labyrinthe ainsi que
	 * les vies du joueur.
	 * 
	 * @return Aucun.
	 */

	public void setup() {
		frameRate(25); // Définition des FPS (Frames Per Second), ici 25FPS
		etat = etatDuJeu.JEU;
		numeroNiveau = 1;
		filePath = "ressources/level" + numeroNiveau + ".txt"; // Chemin relatif
																// pour les
																// niveaux
		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Récupération de la taille de la fenêtre
		windowWidth = scanner.nextInt() * Constantes.TAILLE_SALLE + Constantes.DECALAGE_FENETRE;
		windowHeight = scanner.nextInt() * Constantes.TAILLE_SALLE + Constantes.DECALAGE_FENETRE;

		// Création et chargement du labyrinthe
		lab = new Labyrinthe(this, filePath);
		lab.load();

		// Chargement des différentes images
		back = loadImage("ressources/back.jpg");
		caseLab = loadImage("ressources/case.jpg");
		imgPerso = loadImage("ressources/perso.png");
		vie = loadImage("ressources/vie.png");
		vie2 = vie;
		vie3 = vie;
		imgMonstre = loadImage("ressources/flammes.png");
		size(windowWidth, windowHeight);
	}

	/**
	 * Efface le contenu du labyrinthe (Salles, pièges...) pour un niveau, et le
	 * remplace par celui du niveau suivant.
	 */

	void redemarrage() {
		lab.al.clear();
		lab.load();
		lab.perso.nbVies = Constantes.VIES_DEPART;
		lab.perso.setSalleCourante(lab.entree);
	}

	/**
	 * La méthode controleur() regroupe toutes les actions qui modifient les
	 * états du jeu : Gestion du GameOver (Le joueur n'a plus de vie ou a touché
	 * un monstre), Gestion du changement de niveau et Gestion de la fin du jeu
	 */

	void controleur() {
		switch (etat) {
		case JEU:
			// Appel de la fonction gérant les déplacements du personnage
			if (keyPressed == true)
				lab.keyPressed();
			for (Monstre m : lab.listeMonstre)
				m.deplacer(lab);

			// Si le personnage a atteint l'arrivée
			if (lab.perso.getSalleCourante().x == lab.sortie.x && lab.perso.getSalleCourante().y == lab.sortie.y
					&& numeroNiveau <= Constantes.NIVEAU_MAX)
				etat = etatDuJeu.ARRIVEE;

			// Si la salle est piégée
			if (lab.perso.salleCourante instanceof SallePiege) {
				((SallePiege) lab.perso.salleCourante).effet(lab);
			}

			// Si le joueur rencontre un monstre
			for (Monstre m : lab.listeMonstre) {
				if (lab.perso.salleCourante.x == m.salleActuelle.x && lab.perso.salleCourante.y == m.salleActuelle.y)
					m.effetCollision(lab);
			}

			// Si le joueur n'a plus de vie
			if (lab.perso.nbVies <= 0) {
				redemarrage();
				etat = etatDuJeu.GAME_OVER;
			}
			break;

		case ARRIVEE:
			if (touchePressee()) {
				// Passage au niveau suivant
				lab.al.clear();

				if (numeroNiveau >= Constantes.NIVEAU_MAX)
					etat = etatDuJeu.TERMINE;
				else {
					etat = etatDuJeu.JEU;
					numeroNiveau++;
				}

				filePath = "ressources/level" + numeroNiveau + ".txt";
				lab = new Labyrinthe(this, filePath);
				lab.load();
				lab.perso.nbVies = Constantes.VIES_DEPART;
				lab.perso.setSalleCourante(lab.entree);

			}
			break;

		case GAME_OVER:
			if (touchePressee())
				etat = etatDuJeu.JEU;
			break;

		case TERMINE:
			if (touchePressee())
				etat = etatDuJeu.JEU;
			numeroNiveau = 1;
			break;
		}

	}

	/**
	 * Gestion de l'affichage du jeu : (images, texte,..)
	 */

	public void draw() {

		switch (etat) {
		case JEU:
			tint(255, 255, 255, 255);
			// Dessin du backgound et du labyrinthe
			image(back, 0, 0, windowWidth, windowHeight);
			lab.draw(caseLab, imgPerso, imgMonstre);

			// Dessin des vies
			tint(255, 0, 0, 255);
			textSize(15);
			text("Vies : ", 10, 15);
			if (lab.perso.nbVies > 0)
				image(vie, 15, 18, 20, 20);
			if (lab.perso.nbVies > 1)
				image(vie2, 15 + Constantes.DECALAGE_IMG_VIES, 18, 20, 20);
			if (lab.perso.nbVies > 2)
				image(vie3, 15 + 2 * Constantes.DECALAGE_IMG_VIES, 18, 20, 20);
			break;

		case ARRIVEE:
			background(0);
			textSize(40);
			text("Niveau " + numeroNiveau + " fini !\n", windowWidth / 2, windowHeight / 3);
			text("Appuyer sur Entree pour continuer", windowWidth / 6, windowHeight / 2);
			fill(255, 255, 255);
			break;

		case GAME_OVER:
			// Dessin du game over si le joueur a perdu
			background(0);
			textSize(45);
			text("Game over ... \n", windowWidth / 3, windowHeight / 3);
			textSize(35);
			text("Appuyer sur Entree pour continuer", windowWidth / 6, windowHeight / 2);
			break;

		case TERMINE:
			tint(255, 255, 255);
			textSize(40);
			text("Vous avez reussi a sortir du labyrinthe !", windowWidth / 2, windowHeight / 3);
			text("Recommencer ?", windowWidth / 6, windowHeight / 2);
			fill(255, 255, 255);
			break;
		}
		controleur();
	}

	/**
	 * Cette méthode récupère la touche pressé par le joueur et vérifie si
	 * celle-ci correspond à la touche entrée
	 * 
	 * @return Vrai si c'est la bonne touche, Faux sinon
	 */

	boolean touchePressee() {
		if (keyPressed && key == ENTER)
			return true;

		return false;
	}

	/**
	 * Getter de la classe Labyrinthe
	 * 
	 * @return le labyrinthe
	 */

	public Labyrinthe getLab() {
		return lab;
	}

	/**
	 * fonction principale
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		PApplet.main("Projet AP3");
	}
}