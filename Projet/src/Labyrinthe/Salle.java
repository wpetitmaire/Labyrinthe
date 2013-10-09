package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

public class Salle {

	int x;
	int y;
	int color;
	int taille = Constantes.TAILLE_SALLE;
	PApplet dessin;

	Salle(int abs, int ord, int col, PApplet ap) {
		this.x = abs;
		this.y = ord;
		this.color = col;
		dessin = ap;
	}
	
	Salle(Salle s) {
		this.x = s.x;
		this.y = s.y;
		this.color = s.color;
		this.dessin = s.dessin;
	}

	public void draw(PImage i) {
		dessin.tint(255, 255, 255, 255);
		
		// On colorie de fa�on diff�rente l'entr�e et la sortie
		if (this.color == Constantes.BLEUE)
			dessin.tint(0, 0, 255, 255);
		if (this.color == Constantes.ROUGE)
			dessin.tint(255, 0, 0, 255);
		if (this.color == Constantes.PIEGE)
			dessin.tint(0, 0, 255, 255);
		
		dessin.image(i, x*taille, y*taille, taille, taille);
	}
	
	
	public void drawEclairage(PImage i, Salle s) {
		// On calcule la distance euclidienne entre la case à dessiner et la case du personnage
		float d = this.distance(s);
		int opacite=255;
		
		// En fonction de cette distance, on régule l'opacité de la salle à dessiner
		if (d>=Constantes.ADJACENTE && d<Constantes.PROCHE)
			opacite=150;
		else if (d>=Constantes.PROCHE && d<Constantes.ELOIGNEE)
			opacite=100;
		else if (d>=Constantes.ELOIGNEE)
			opacite = 0;
		
		dessin.tint(255, 255, 255, opacite);
		
		// On colorie de façon différente l'entrée et la sortie
		if (this.color == 0)
			dessin.tint(0, 0, 255, opacite);
		if (this.color == 1)
			dessin.tint(255, 0, 0, opacite);
		if(this.color == 3)
			dessin.tint(0,255,0,opacite);
		
		dessin.image(i, x*taille, y*taille, taille, taille);
	}

	
	public static void main(String[] args) {
		
	}
	
	public float distance (Salle s) {
		float d = (this.x-s.x)*(this.x-s.x) + (this.y-s.y)*(this.y-s.y);
		if (d>0)
			return (int) ( Math.sqrt(d) );
		else
			return -1;
	}

}
