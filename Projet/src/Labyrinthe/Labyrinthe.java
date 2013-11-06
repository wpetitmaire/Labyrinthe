package Labyrinthe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

import processing.core.PApplet;
import processing.core.PImage;


@SuppressWarnings("serial")
public class Labyrinthe extends PApplet{

	private ArrayList<Salle> al = new ArrayList<Salle>();
	private ArrayList<Monstre> listeMonstre = new ArrayList<Monstre>();
	private PApplet dessin;
	private Salle entree, sortie;
	private Personnage perso;
	private String fichier;
	
	Labyrinthe(PApplet ap, String f) {
		dessin = ap;
		fichier = f;
	}
	
	
	public Personnage getPersonnage() {
		return perso;
	}
	
	public Salle getEntree() {
		return entree;
	}
	
	public Salle getSortie() {
		return sortie;
	}
	
	public ArrayList<Salle> getAl() {
		return al;
	}

	public ArrayList<Monstre> getListeMonstre() {
		return listeMonstre;
	}
	
	public void load() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fichier));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		
		
		// Lecture du reste du fichier et stockage des valeurs dans une collection
		while (scanner.hasNextInt())
		{
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
	
	
	
	public void draw(PImage img, PImage imgPerso, PImage imgMonstre) {
		for (Salle c : al) {
			c.draw(img, perso.getSalleCourante());
		}
		perso.draw(imgPerso);
		
		for(Monstre m : listeMonstre)
			m.draw(imgMonstre, perso.salleCourante);
	}
	
	public void keyPressed() {
		Salle futur = new Salle(-1, -1, -1, dessin);
		
		// On crée la salle où le personnage souhaite aller
		switch(dessin.keyCode)
		{
			case UP:
				futur = new Salle(perso.salleCourante.x, perso.salleCourante.y-1, 0, dessin);
				break;
			case DOWN:
				futur = new Salle(perso.salleCourante.x, perso.salleCourante.y+1, 0, dessin);
				break;
			case RIGHT:
				futur = new Salle(perso.salleCourante.x+1, perso.salleCourante.y, 0, dessin);
				break;
			case LEFT:
				futur = new Salle(perso.salleCourante.x-1, perso.salleCourante.y, 0, dessin);
				break;
		}
		
		// Si la salle existe vraiment, alors on place le personnage dans celle-ci
		for (Salle s : al) {
			if (futur.x == s.x && futur.y == s.y) {
				
				if (s instanceof SalleExplosive)
					perso.salleCourante = new SalleExplosive( (SalleExplosive) s);
				
				if (s instanceof SalleTeleporteur)
					perso.salleCourante = new SalleTeleporteur( (SalleTeleporteur) s);
				
				else
					perso.setSalleCourante(s);
			}
		}
	}	

	
	public Salle trouverSalle(int alea) {
		// Renvoie la la n-ième salle du labyrinthe, où n est un nombre aléatoire passé en paramètre
		return al.get(alea);
	}
	
	public void supprimerPiege(Salle s) {
		int cpt = 0, pos = 0;
		// Recherche de la position de la salle à supprimer
		for (Salle salle : al) {
			if (salle.x == s.x && salle.y == s.y) {
				pos = cpt;
			}
			else
				cpt++;
		}
		// Suppression puis remplacement par une salle normale
		al.remove(pos);
		Salle nouvelleSalle = new Salle(s.x, s.y, Constantes.BLANCHE, dessin);
		al.add(nouvelleSalle);
		
	}

	
	public static void main(String[] args) {

	}

}