package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Petit-Maire & Bailly
 * 
 *         création et gestion d'une salle explosive. Elle hérite de la classe
 *         SallePiege.
 */

public class SalleExplosive extends SallePiege {

	/**
	 * Création de la salle.
	 * 
	 * @param abs
	 *            Abscisse de la salle.
	 * @param ord
	 *            Ordonnée de la salle.
	 * @param col
	 *            Couleur de la salle.
	 * @param ap
	 *            Application.
	 */
	SalleExplosive(int abs, int ord, int col, PApplet ap) {
		super(abs, ord, col, ap);
		actif = true;
	}

	/**
	 * Constructeur par copie de la classe SalleExplosive
	 * 
	 * @param s
	 *            Salle à copier.
	 */
	SalleExplosive(Salle s) {
		super(s);
		actif = true;
	}

	/**
	 * Définit l'effet d'une collision entre le joueur et la salle. Ici le
	 * joueur perd une vie à chaque collision. On supprime la salle après la
	 * collision.
	 */
	@Override
	public void effet(Labyrinthe l) {
		if (actif) {
			l.perso.nbVies--;
			actif = false;
			l.supprimerPiege(this);
		}
	}

	/**
	 * Dessine l'image de la salle.
	 */
	@Override
	public void draw(PImage i, Salle s) {
		float d = this.distance(s);
		int opacite = 255;

		// En fonction de cette distance, on régule l'opacité de la salle à
		// dessiner
		if (d >= Constantes.ADJACENTE && d < Constantes.PROCHE)
			opacite = 150;
		else if (d >= Constantes.PROCHE && d < Constantes.ELOIGNEE)
			opacite = 100;
		else if (d >= Constantes.ELOIGNEE)
			opacite = 0;

		// On dessine les salles explosives en rouge
		dessin.tint(255, 0, 0, opacite);
		dessin.image(i, x * taille, y * taille, taille, taille);
	}

}
