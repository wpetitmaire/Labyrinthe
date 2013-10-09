package Labyrinthe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class Labyrinthe extends PApplet{

	private static final long serialVersionUID = 1L;
	ArrayList<Salle> al = new ArrayList<Salle>();
	PApplet dessin;
	Salle entree, sortie, piege, piege2, piege3, piege4, piege5; 
	int nbpiege;
	Personnage perso;
	
	Labyrinthe(PApplet ap, int piege) {
		dessin = ap;
		nbpiege = piege;
	}
	
	public void load() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("niveaux\\lab.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Lecture et stockage de l'entrée et de la sortie
		scanner.nextLine();
		entree = new Salle(1, 1, Constantes.BLEUE, dessin);
		scanner.nextLine();
		sortie = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.ROUGE, dessin);
		scanner.nextLine();
		piege = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.PIEGE, dessin);
		scanner.nextLine();
		piege2 = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.PIEGE, dessin);
		scanner.nextLine();
		piege3 = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.PIEGE, dessin);
		scanner.nextLine();
		piege4 = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.PIEGE, dessin);
		scanner.nextLine();
		piege5 = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.PIEGE, dessin);
		
		al.add(entree);
		al.add(sortie);
		al.add(piege);
		al.add(piege2);
		al.add(piege3);
		al.add(piege4);
		al.add(piege5);
		
		/*for(int i=0; i<=nbpiege; i++){
			scanner.nextLine();
			Salle tmp = new Salle(scanner.nextInt(), scanner.nextInt(), Constantes.PIEGE, dessin);
			al.add(tmp);
		}*/
		
		// On initialise le personnage à l'entrée
		perso = new Personnage(dessin, this.entree, Constantes.PTS_VIE);

		// Lecture du reste du fichier et stockage des valeurs dans une collection
		while (scanner.hasNextInt())
		{
			Salle c = new Salle(scanner.nextInt(), scanner.nextInt(),  2, dessin);
			al.add(c);
			scanner.nextLine();
		}
	}
	
	public void draw(PImage img, PImage imgPerso) {
		for (Salle c : al) {
			//c.draw(img);
			c.drawEclairage(img, perso.getSalleCourante());
		}
		perso.draw(imgPerso);
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
		for (Salle c : al) {
			if (futur.x == c.x && futur.y == c.y) {
				perso.setSalleCourante(futur);
			}
		}
	}	
	
	public static void main(String[] args) {
		
	}

}
