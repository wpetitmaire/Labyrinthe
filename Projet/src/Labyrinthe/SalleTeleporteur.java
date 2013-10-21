package Labyrinthe;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

public class SalleTeleporteur extends SallePiege{

	
	SalleTeleporteur(int abs, int ord, int col, PApplet ap) {
		super(abs, ord, col, ap);
		actif = true;
	}
	
	SalleTeleporteur(Salle s) {
		super(s);
		actif = true;
	}

	@Override
	public void effet(Labyrinthe l) {
		if (actif) {
			// On tire un nombre aléatoire
			Random r = new Random();
			int valeur = 1 + r.nextInt(Constantes.PROBA_PIEGE - 1);
			
			// On "sauvegarde" les propriétés de la salle piégée actuelle 
			SalleTeleporteur sT = new SalleTeleporteur(this);
			
			// On téléporte le joueur dans une salle aléatoire
			l.perso.setSalleCourante(l.trouverSalle(valeur));
			actif = false;
			
			// On remplace la salle Telepoteur par une salle normale
			l.supprimerPiege(sT);
		}
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
		
		// On dessine les salles téléporteurs en violet
		dessin.tint(125, 0, 125, opacite);
		dessin.image(i, x*taille, y*taille, taille, taille);
	}
}
