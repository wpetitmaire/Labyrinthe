package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Petit-Maire & Bailly Création et gestion des salle piège. Hérite de
 *         la classe Salle.
 */

public abstract class SallePiege extends Salle {

	boolean actif;

	/**
	 * Création d'une salle piège
	 * 
	 * @param abs
	 *            Abscisse de la salle
	 * @param ord
	 *            Ordonnée de la salle
	 * @param col
	 *            Couleur de la salle
	 * @param ap
	 *            Application dans laquelle se trouve la salle.
	 */
	SallePiege(int abs, int ord, int col, PApplet ap) {
		super(abs, ord, col, ap);
		actif = true;
	}

	/**
	 * Constructeur par copie de la classe SallePiege.
	 * 
	 * @param s
	 *            Salle à copier.
	 */
	SallePiege(Salle s) {
		this.x = s.x;
		this.y = s.y;
		this.color = s.color;
		this.dessin = s.dessin;
	}

	/**
	 * Méthode abstraite définissant l'effet du piège dans le labyrinthe.
	 * 
	 * @param l
	 *            Labyrinthe en question.
	 */
	public abstract void effet(Labyrinthe l);

	/**
	 * Méthode abstraite définissant l'image à afficher dans la salle piégée.
	 * 
	 * @param i
	 *            Images à afficher.
	 * @param s
	 *            Salle qui va contenir l'image.
	 */
	public abstract void draw(PImage i, Salle s);

}
