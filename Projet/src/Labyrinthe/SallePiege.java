package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class SallePiege extends Salle {
	
	boolean actif;
	
	SallePiege(int abs, int ord, int col, PApplet ap) {
		super(abs, ord, col, ap);
		actif = true;
	}
	
	SallePiege(Salle s) {
		this.x = s.x;
		this.y = s.y;
		this.color = s.color;
		this.dessin = s.dessin;
	}
	
	public abstract void effet(Labyrinthe l);
	
	public abstract void draw(PImage i, Salle s);

}
