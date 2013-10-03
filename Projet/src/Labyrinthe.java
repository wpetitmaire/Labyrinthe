import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class Labyrinthe extends PApplet {

	ArrayList<Salle> al = new ArrayList<Salle>();
	PApplet dessin;
	Salle entree, sortie;
	Personnage perso;

	Labyrinthe(PApplet ap) {
		dessin = ap;
	}

	public void load() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("lab2.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scanner.nextLine();
		entree = new Salle(scanner.nextInt(), scanner.nextInt(), 0, dessin);
		scanner.nextLine();
		sortie = new Salle(scanner.nextInt(), scanner.nextInt(), 1, dessin);
		al.add(entree);
		al.add(sortie);

		perso = new Personnage(dessin, this.entree);

		while (scanner.hasNextInt()) {
			Salle c = new Salle(scanner.nextInt(), scanner.nextInt(), 2, dessin);
			al.add(c);
			scanner.nextLine();
		}
	}

	public void draw(PImage img, PImage imgPerso) {
		for (Salle c : al) {
			// c.draw(img);
			c.drawEclairage(img, perso.getSalleCourante());
		}
		perso.draw(imgPerso);
	}

	public void keyPressed() {
		Salle futur = new Salle(-1, -1, -1, dessin);

		switch (dessin.keyCode) {
		case UP:
			futur = new Salle(perso.salleCourante.x, perso.salleCourante.y - 1,
					0, dessin);
			break;
		case DOWN:
			futur = new Salle(perso.salleCourante.x, perso.salleCourante.y + 1,
					0, dessin);
			break;
		case RIGHT:
			futur = new Salle(perso.salleCourante.x + 1, perso.salleCourante.y,
					0, dessin);
			break;
		case LEFT:
			futur = new Salle(perso.salleCourante.x - 1, perso.salleCourante.y,
					0, dessin);
			break;

		}
		for (Salle c : al) {
			if (futur.x == c.x && futur.y == c.y)
				perso.setSalleCourante(futur);
		}

		if (perso.getSalleCourante().x == this.sortie.x
				&& perso.getSalleCourante().y == this.sortie.y) {
			dessin.textSize(40);
			dessin.text("Vous avez gagne !", sortie.x * sortie.taille, sortie.y
					* sortie.taille);
			dessin.fill(255, 0, 0);
		}
	}

}
