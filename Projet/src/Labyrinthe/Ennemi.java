package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Ennemi {
	private Salle salleActuelle;
	private PApplet dessin;
	
	public Salle getSalleActuelle() {
		return salleActuelle;
	}


	public void setSalleActuelle(Salle salleActuelle) {
		this.salleActuelle = salleActuelle;
	}


	public PApplet getDessin() {
		return dessin;
	}


	public void setDessin(PApplet dessin) {
		this.dessin = dessin;
	}


	public abstract void effetCollision(Labyrinthe l);
	 
	
	Ennemi(Salle s, PApplet p) {
		salleActuelle = new Salle(s);
		dessin = p;
	}
	
	public void draw(PImage img, Salle s) {
		float d = salleActuelle.distance(s);
		int opacite=255;
		
		// En fonction de cette distance, on régule l'opacité de la salle à dessiner
		if (d>=Constantes.ADJACENTE && d<Constantes.PROCHE)
			opacite=150;
		else if (d>=Constantes.PROCHE && d<Constantes.ELOIGNEE)
			opacite=100;
		else if (d>=Constantes.ELOIGNEE)
			opacite = 0;
		
		dessin.tint(255,255,255,opacite);
		dessin.image(img, salleActuelle.x*salleActuelle.taille, salleActuelle.y*salleActuelle.taille, salleActuelle.taille, salleActuelle.taille);
	}
	
}
