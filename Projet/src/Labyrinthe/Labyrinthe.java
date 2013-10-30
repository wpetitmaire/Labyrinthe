package Labyrinthe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Petit-Maire & Bailly
 * 
 *         Cette classe gère la création de notre labyrinthe, il initialise
 *         notre jeu.
 */

@SuppressWarnings("serial")
public class Labyrinthe extends PApplet {

	ArrayList<Salle> al = new ArrayList<Salle>();
	ArrayList<Monstre> listeMonstre = new ArrayList<Monstre>();
	PApplet dessin;
	Salle entree, sortie;
	Personnage perso;
	String fichier;

	/**
	 * Création de notre labyrinthe.
	 * 
	 * @param ap
	 *            L'application elle même (la classe App)
	 * @param f
	 *            Le chemin relatif vers le fichier qui contient les
	 *            informations nécéssaires à la création de notre niveau.
	 */

	Labyrinthe(PApplet ap, String f) {
		dessin = ap;
		fichier = f;
	}

	/**
	 * Chargement de notre labyrinthe à partir du fichier.
	 */

	public void load() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fichier));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Lecture et stockage de l'entrée et de la sortie
		scanner.nextLine();
		entree = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.BLEUE, dessin);
		scanner.nextLine();
		sortie = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.VERTE, dessin);
		al.add(entree);
		al.add(sortie);

		// On initialise le personnage à l'entrée
		perso = new Personnage(dessin, this.entree);

		// Lecture du reste du fichier et stockage des valeurs dans une
		// collection
		while (scanner.hasNextInt()) {
			Salle c = new Salle();
			if (c.mettrePiegeExplosion())
				c = new SalleExplosive(scanner.nextInt(), scanner.nextInt(), Constantes.ROUGE, dessin);
			else if (c.mettrePiegeTeleporteur())
				c = new SalleTeleporteur(scanner.nextInt(), scanner.nextInt(), Constantes.VIOLET, dessin);
			else
				c = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.BLANCHE, dessin);
			al.add(c);
			scanner.nextLine();
		}

		// Initialisation du monstre s'il n'existait pas
		if (listeMonstre.isEmpty()) {
			Random r = new Random();
			int valeur = 1 + r.nextInt(al.size() - 1);
			Monstre monster = new Monstre(trouverSalle(valeur), dessin);
			listeMonstre.add(monster);
		}
	}

	/**
	 * Dessine les images du labyrinthe : Les salles, le personnage et l'image
	 * des monstres.
	 * 
	 * @param img
	 *            Image d'une salle
	 * @param imgPerso
	 *            Image du joueur
	 * @param imgMonstre
	 *            Image d'un monstre
	 */
	public void draw(PImage img, PImage imgPerso, PImage imgMonstre) {
		for (Salle c : al) {
			c.draw(img, perso.getSalleCourante());
		}
		perso.draw(imgPerso);

		for (Monstre m : listeMonstre)
			m.draw(imgMonstre, perso.salleCourante);
	}

	/**
	 * Gère les déplacements du personnage selon les touches du clavier.
	 * 
	 */

	public void keyPressed() {
		Salle futur = new Salle(-1, -1, -1, dessin);

		// On crée la salle où le personnage souhaite aller
		switch (dessin.keyCode) {
		case UP:
			futur = new Salle(perso.salleCourante.x, perso.salleCourante.y - 1, 0, dessin);
			break;
		case DOWN:
			futur = new Salle(perso.salleCourante.x, perso.salleCourante.y + 1, 0, dessin);
			break;
		case RIGHT:
			futur = new Salle(perso.salleCourante.x + 1, perso.salleCourante.y, 0, dessin);
			break;
		case LEFT:
			futur = new Salle(perso.salleCourante.x - 1, perso.salleCourante.y, 0, dessin);
			break;
		}

		// Si la salle existe vraiment, alors on place le personnage dans
		// celle-ci
		for (Salle s : al) {
			if (futur.x == s.x && futur.y == s.y) {

				if (s instanceof SalleExplosive)
					perso.salleCourante = new SalleExplosive((SalleExplosive) s);

				if (s instanceof SalleTeleporteur)
					perso.salleCourante = new SalleTeleporteur((SalleTeleporteur) s);

				else
					perso.setSalleCourante(s);
			}
		}
	}

	/**
	 * Renvoie la la n-ième salle du labyrinthe, où n est un nombre aléatoire.
	 * 
	 * @param alea
	 *            Nombre aléatoire
	 * @return Salle en question.
	 */

	public Salle trouverSalle(int alea) {
		return al.get(alea);
	}

	/**
	 * Remplace un piège par une nouvelle salle. Pour cela il recherche la
	 * position de la salle à supprimer. Puis la remplace par une nouvelle salle
	 * 
	 * @param s
	 *            Salle à supprimer
	 */

	public void supprimerPiege(Salle s) {
		int cpt = 0, pos = 0;
		// Recherche de la position de la salle à supprimer
		for (Salle salle : al) {
			if (salle.x == s.x && salle.y == s.y) {
				pos = cpt;
			} else
				cpt++;
		}
		// Suppression puis remplacement par une salle normale
		al.remove(pos);
		Salle nouvelleSalle = new Salle(s.x, s.y, Constantes.BLANCHE, dessin);
		al.add(nouvelleSalle);

	}

}