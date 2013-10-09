package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;


public class Personnage {
	Salle salleCourante;
	PApplet dessin;
	int vie;
	
	Personnage(PApplet d, Salle s, int ptsVie) {
		this.dessin = d;
		this.salleCourante = new Salle(s);
		vie = ptsVie;
	}
	
	public void draw(PImage i) {
		dessin.tint(255,255,255,255);
		dessin.image(i, salleCourante.x*salleCourante.taille, salleCourante.y*salleCourante.taille, salleCourante.taille, salleCourante.taille);
	}
	
	public Salle getSalleCourante() {
		return salleCourante;
	}
	
	public void setSalleCourante(Salle s) {
		this.salleCourante = s;
	}


}
