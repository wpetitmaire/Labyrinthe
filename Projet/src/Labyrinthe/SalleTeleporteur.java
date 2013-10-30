package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * @author Petit-Maire & Bailly
 * 
 *         Création et gestion des salles téléporteuses.
 */

public class SalleTeleporteur extends SallePiege {

	/**
	 * création de la salle.
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
	SalleTeleporteur(int abs, int ord, int col, PApplet ap) {
		super(abs, ord, col, ap);
		actif = true;
	}

	/**
	 * Constructeur par default de la salle
	 * 
	 * @param s
	 *            Salle à copier
	 */
	SalleTeleporteur(Salle s) {
		super(s);
		actif = true;
	}

	/**
	 * définie l'effet de la salle dans le labyrinthe.
	 * 
	 * @param l
	 *            Labyrinthe en question
	 */
	@Override
	public void effet(Labyrinthe l) {
		if (actif) {
			// On tire un nombre aléatoire
			Random r = new Random();
			int valeur = 1 + r.nextInt(l.al.size() - 1);

			// On "sauvegarde" les propriétés de la salle piégée actuelle
			SalleTeleporteur sT = new SalleTeleporteur(this);

			// On téléporte le joueur dans une salle aléatoire
			l.perso.setSalleCourante(l.trouverSalle(valeur));
			actif = false;

			// On remplace la salle Telepoteur par une salle normale
			l.supprimerPiege(sT);
		}
	}

	/**
	 * Dessine l'image de la salle
	 * 
	 * @param i
	 *            Image ç dessiner
	 * @param s
	 *            Salle qui va contenir l'image.
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

		// On dessine les salles téléporteurs en violet
		dessin.tint(125, 0, 125, opacite);
		dessin.image(i, x * taille, y * taille, taille, taille);
	}
}
