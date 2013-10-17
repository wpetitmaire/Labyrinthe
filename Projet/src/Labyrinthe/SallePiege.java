package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class SallePiege extends Salle {

	SallePiege(int abs, int ord, int col, PApplet ap, boolean p) {
		super(abs, ord, col, ap, p);
	}
	
	public abstract void effet(Personnage p);
	
	public abstract void draw(PImage i, Salle s);

}
