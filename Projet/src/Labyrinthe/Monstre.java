package Labyrinthe;

import processing.core.PApplet;

import java.util.Random;

public class Monstre extends Ennemi{

	
	
	Monstre(Salle s, PApplet p) {
		super(s, p);
	}
	
	public void effetCollision(Labyrinthe l) {
		l.perso.nbVies = 0;
		l.perso.setSalleCourante(l.entree);
	}
	
	public void deplacer(Labyrinthe l) {
		Random r = new Random();
		int valeur = 1 + r.nextInt(Constantes.PROBA_DEPLACEMENT - 1);
		
		if (valeur <= Constantes.DEPLACEMENT)
			salleActuelle = salleAdjacente(l);
	}
	
	public Salle salleAdjacente(Labyrinthe l) {
		Salle nouvelleSalle = new Salle(this.salleActuelle);
		Random r = new Random();
		int direction = 1 + r.nextInt(Constantes.MAX_DIRECTION - 1);
		
		switch(direction) {
			case Constantes.BAS : 
				for (Salle s : l.al) {
					if (s.x == this.salleActuelle.x && s.y == this.salleActuelle.y +1)
						nouvelleSalle = new Salle (s);
				}
				break;
				
			case Constantes.HAUT :
				for (Salle s : l.al) {
					if (s.x == this.salleActuelle.x && s.y == this.salleActuelle.y -1)
						nouvelleSalle = new Salle (s);
				}
				break;
			
			case Constantes.DROITE :
				for (Salle s : l.al) {
					if (s.x == this.salleActuelle.x+1 && s.y == this.salleActuelle.y)
						nouvelleSalle = new Salle (s);
				}
				break;
				
			default :
				for (Salle s : l.al) {
					if (s.x == this.salleActuelle.x-1 && s.y == this.salleActuelle.y)
						nouvelleSalle = new Salle (s);
				}
				break;
		}
		
		return nouvelleSalle;
	}
	
}


