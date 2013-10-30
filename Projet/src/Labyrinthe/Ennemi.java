package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Petit-Maire & Bailly
 * 
 *         Cette classe gère la cration et le placement dans le labyrinthe des
 *         ennemis. C'est une classe abstraite.
 */

public abstract class Ennemi {
	Salle salleActuelle;
	PApplet dessin;

	/**
	 * Méthode abstraite sur l'effet d'une collision entre le joueur et
	 * l'ennemi. Elle est définie dans les classes filles.
	 * 
	 * @param l
	 */
	public abstract void effetCollision(Labyrinthe l);

	/**
	 * Constructeur de la classe Ennemi.
	 * 
	 * @param s
	 *            Correspond à la salle dans laquelle on place l'énnemi.
	 * @param p
	 *            Correspond à notre application.
	 */
	Ennemi(Salle s, PApplet p) {
		salleActuelle = new Salle(s);
		dessin = p;
	}

	/**
	 * Cette méthode dessine l'image du piège dans la bonne salle du labyrinthe.
	 * 
	 * @param img
	 *            Correspond à l'image à dessiner.
	 * @param s
	 *            Correspond à la salle piégé.
	 */
	public void draw(PImage img, Salle s) {
		float d = salleActuelle.distance(s);
		int opacite = 255;

		// En fonction de cette distance, on régule l'opacité de la salle à
		// dessiner
		if (d >= Constantes.ADJACENTE && d < Constantes.PROCHE)
			opacite = 150;
		else if (d >= Constantes.PROCHE && d < Constantes.ELOIGNEE)
			opacite = 100;
		else if (d >= Constantes.ELOIGNEE)
			opacite = 0;

		dessin.tint(255, 255, 255, opacite);
		dessin.image(img, salleActuelle.x * salleActuelle.taille, salleActuelle.y * salleActuelle.taille, salleActuelle.taille, salleActuelle.taille);
	}

	/**
	 * Getter de salleActuel
	 * 
	 * @return salleActuel
	 */
	public Salle getSalleActuelle() {
		return salleActuelle;
	}

	/**
	 * Getter de salleActuel
	 * 
	 * @return salleActuel
	 */
	public void setSalleActuelle(Salle s) {
		salleActuelle = s;
	}

}
