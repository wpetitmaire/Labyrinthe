package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

public class Personnage {
	Salle salleCourante;
	PApplet dessin;

	Personnage(PApplet d, Salle s) {
		this.dessin = d;
		this.salleCourante = new Salle(s);
	}

	public void draw(PImage i) {
		dessin.tint(255, 255, 255, 255);
		dessin.image(i, salleCourante.x * salleCourante.taille, salleCourante.y
				* salleCourante.taille, salleCourante.taille,
				salleCourante.taille);
	}

	public Salle getSalleCourante() {
		return salleCourante;
	}

	public void setSalleCourante(Salle s) {
		this.salleCourante = s;
	}

}
