package Labyrinthe;

import processing.core.PApplet;

import java.util.Random;

/**
 * @author Petit-Maire & Bailly
 * 
 *         Cette classe gère la création et le contrôl des monstre dans un
 *         niveau du labyrinthe. Elle hérite également de la classe mère Ennemi.
 */

public class Monstre extends Ennemi {

	/**
	 * On créer un monstre dans une salle de notre labyrinthe.
	 * 
	 * @param s
	 *            La salle en question
	 * @param p
	 *            Notre application
	 */
	public Monstre(Salle s, PApplet p) {
		super(s, p);
	}

	/**
	 * Détermine l'effet d'une collision entre le joueur et le monstre.
	 * 
	 * @param l
	 *            Le labyrinthe dans lequel se trouve le monstre et notre
	 *            joueur.
	 */

	public void effetCollision(Labyrinthe l) {
		l.perso.nbVies = 0;
		l.perso.setSalleCourante(l.entree);
		/*
		 * l.listeMonstre.clear();
		 * 
		 * Random r = new Random(); int valeur = 1 + r.nextInt(l.al.size() - 1);
		 * Monstre monster = new Monstre(l.trouverSalle(valeur), dessin);
		 * l.listeMonstre.add(monster);
		 */
	}

	/**
	 * gère le déplacement du monstre dans le labbyrinthe.
	 * 
	 * @param l
	 *            Le labyrinthe.
	 */

	public void deplacer(Labyrinthe l) {
		Random r = new Random();
		int valeur = 1 + r.nextInt(Constantes.PROBA_DEPLACEMENT - 1);

		if (valeur <= Constantes.DEPLACEMENT)
			salleActuelle = salleAdjacente(l);
	}

	/**
	 * Cette méthode vérifie s'il existe une salle à coté du monstre selon la
	 * direction dans laquelle il se dirige.
	 * 
	 * @param l
	 *            Le labyrinthe.
	 * @return nouvelleSalle Salle dans laquel est placé le monstre.
	 */

	public Salle salleAdjacente(Labyrinthe l) {
		Salle nouvelleSalle = new Salle(this.salleActuelle);
		Random r = new Random();
		int direction = 1 + r.nextInt(Constantes.MAX_DIRECTION - 1);

		switch (direction) {
		case Constantes.BAS:
			for (Salle s : l.al) {
				if (s.x == this.salleActuelle.x && s.y == this.salleActuelle.y + 1)
					nouvelleSalle = new Salle(s);
			}
			break;

		case Constantes.HAUT:
			for (Salle s : l.al) {
				if (s.x == this.salleActuelle.x && s.y == this.salleActuelle.y - 1)
					nouvelleSalle = new Salle(s);
			}
			break;

		case Constantes.DROITE:
			for (Salle s : l.al) {
				if (s.x == this.salleActuelle.x + 1 && s.y == this.salleActuelle.y)
					nouvelleSalle = new Salle(s);
			}
			break;

		default:
			for (Salle s : l.al) {
				if (s.x == this.salleActuelle.x - 1 && s.y == this.salleActuelle.y)
					nouvelleSalle = new Salle(s);
			}
			break;
		}

		return nouvelleSalle;
	}

}
