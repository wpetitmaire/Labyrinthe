package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;

public class SalleExplosive extends SallePiege{

	SalleExplosive(int abs, int ord, int col, PApplet ap, boolean p) {
		super(abs, ord, col, ap, p);
	}

	@Override
	public void effet(Personnage p) {
		p.nbVies--;
	}
	
	@Override
	public void draw(PImage i, Salle s) {
		float d = this.distance(s);
		int opacite=255;
		
		// En fonction de cette distance, on régule l'opacité de la salle à dessiner
		if (d>=Constantes.ADJACENTE && d<Constantes.PROCHE)
			opacite=150;
		else if (d>=Constantes.PROCHE && d<Constantes.ELOIGNEE)
			opacite=100;
		else if (d>=Constantes.ELOIGNEE)
			opacite = 0;
		
		// On dessine les salles explosives en rouge
		dessin.tint(255, 0, 0, opacite);
		
		dessin.image(i, x*taille, y*taille, taille, taille);
	}
		
}
