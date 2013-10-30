package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Petit-Maire & Bailly
 * 
 *         Création et gestion du joueur.
 */
public class Personnage {
	Salle salleCourante;
	int nbVies;
	PApplet dessin;

	/**
	 * Création du joueur
	 * 
	 * @param d
	 *            Application (classe App)
	 * @param s
	 *            Salle dans laquel on place le joueur.
	 */
	Personnage(PApplet d, Salle s) {
		this.dessin = d;
		this.salleCourante = new Salle(s);
		this.nbVies = Constantes.VIES_DEPART;
	}

	/**
	 * Dessin du joueur
	 * 
	 * @param i
	 */
	public void draw(PImage i) {
		dessin.tint(255, 255, 255, 255);
		dessin.image(i, salleCourante.x * salleCourante.taille, salleCourante.y * salleCourante.taille, salleCourante.taille, salleCourante.taille);
	}

	/**
	 * Retourne la salle dans laquelle se trouve le joueur
	 * 
	 * @return
	 */
	public Salle getSalleCourante() {
		return salleCourante;
	}

	/**
	 * Définie la salle dans laquelle se trouve le joueur
	 * 
	 * @param s
	 *            Salle en question.
	 */
	public void setSalleCourante(Salle s) {
		this.salleCourante.x = s.x;
		this.salleCourante.y = s.y;
		this.salleCourante.color = s.color;
	}

}