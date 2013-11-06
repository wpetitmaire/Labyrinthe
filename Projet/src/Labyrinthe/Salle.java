package Labyrinthe;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Petit-Maire & Bailly
 * 
 *         Création et gestion des salles du labyrinthe.
 */

public class Salle {

	public int x;
	public int y;
	int color;
	int taille = Constantes.TAILLE_SALLE;
	PApplet dessin;

	/**
	 * Création d'une salle.
	 * 
	 * @param abs
	 *            Abscisse d'une salle
	 * @param ord
	 *            Ordonée d'une salle
	 * @param col
	 *            Couleur de la salle
	 * @param ap
	 *            Application (la classe App)
	 */
	public Salle(int abs, int ord, int col, PApplet ap) {
		this.x = abs;
		this.y = ord;
		this.color = col;
		dessin = ap;

	}

	/**
	 * Constructeur par défault de la classe Salle
	 */
	Salle() {
		this.x = 0;
		this.y = 0;
		this.color = Constantes.BLANCHE;
	}

	/**
	 * Constructeur par copie de la classe Salle
	 * 
	 * @param s
	 *            Salle à copier
	 */
	Salle(Salle s) {
		this.x = s.x;
		this.y = s.y;
		this.color = s.color;
		this.dessin = s.dessin;
	}

	/**
	 * Dessin des salles. On les dessine selon la distance entre la salle en
	 * question et le joueur.
	 * 
	 * @param i
	 *            Image de la salle.
	 * @param s
	 *            Salle à dessiner.
	 */
	public void draw(PImage i, Salle s) {
		// On calcule la distance euclidienne entre la case à dessiner et la
		// case du personnage
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

		dessin.tint(255, 255, 255, opacite);

		// On colorie de façon différente l'entrée et la sortie
		if (this.color == Constantes.BLEUE)
			dessin.tint(0, 0, 255, opacite);
		if (this.color == Constantes.VERTE)
			dessin.tint(0, 255, 0, opacite);

		dessin.image(i, x * taille, y * taille, taille, taille);
	}

	public static void main(String[] args) {

	}

	/**
	 * Calcul la distance.
	 * 
	 * @param s
	 *            Salle utilisée pour calculer la distance.
	 * @return la distance sous forme d'entier si elle est positive sinon elle
	 *         retourne -1
	 */
	public float distance(Salle s) {
		float d = (this.x - s.x) * (this.x - s.x) + (this.y - s.y) * (this.y - s.y);
		if (d > 0)
			return (int) (Math.sqrt(d));
		else
			return -1;
	}

	/**
	 * Mise en place d'une salle piège (Explosion) selon la probabilité.
	 * 
	 * @return vraie si la probabilité est bonne, faux sinon.
	 */
	public boolean mettrePiegeExplosion() {
		Random r = new Random();
		int valeur = 1 + r.nextInt(Constantes.PROBA_PIEGE - 1);
		if (valeur == Constantes.PIEGE_EXPLOSION)
			return true;

		return false;
	}

	/**
	 * Mise en place d'une salle piège (Téléporteur) selon la probabilité.
	 * 
	 * @return vraie si la probabilité est bonne, faux sinon.
	 */
	public boolean mettrePiegeTeleporteur() {
		Random r = new Random();
		int valeur = 1 + r.nextInt(Constantes.PROBA_PIEGE - 1);
		if (valeur == Constantes.PIEGE_TELEPORTEUR)
			return true;

		return false;
	}

}
