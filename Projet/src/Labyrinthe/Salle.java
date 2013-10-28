package Labyrinthe;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Salle {

	public int x;
	public int y;
	int color;
	int taille = Constantes.TAILLE_SALLE;
	PApplet dessin;

	public Salle(int abs, int ord, int col, PApplet ap) {
		this.x = abs;
		this.y = ord;
		this.color = col;
		dessin = ap;

	}
	
	Salle() {
		this.x = 0;
		this.y = 0;
		this.color = Constantes.BLANCHE; 
	}
	
	Salle(Salle s) {
		this.x = s.x;
		this.y = s.y;
		this.color = s.color;
		this.dessin = s.dessin;
	}
	
	
	
	public void draw(PImage i, Salle s) {
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
		if (this.color == Constantes.BLEUE)
			dessin.tint(0, 0, 255, opacite);
		if (this.color == Constantes.VERTE)
			dessin.tint(0, 255, 0, opacite);
		
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
	
	public boolean mettrePiegeExplosion() {
		Random r = new Random();
		int valeur = 1 + r.nextInt(Constantes.PROBA_PIEGE - 1);
		if (valeur == Constantes.PIEGE_EXPLOSION)
			return true;
		
		return false;
	}
	
	public boolean mettrePiegeTeleporteur() {
		Random r = new Random();
		int valeur = 1 + r.nextInt(Constantes.PROBA_PIEGE - 1);
		if (valeur == Constantes.PIEGE_TELEPORTEUR)
			return true;
		
		return false;
	}

}

