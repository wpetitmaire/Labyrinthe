package Labyrinthe;

import processing.core.PApplet;

import java.util.Random;

public class Monstre extends Ennemi{

	
	
	Monstre(Salle s, PApplet p) {
		super(s, p);
	}
	
	public void effetCollision(Labyrinthe l) {
		l.getPersonnage().nbVies = 0;
		l.getPersonnage().setSalleCourante(l.getEntree());
		/*l.listeMonstre.clear();
		
		Random r = new Random();
		int valeur = 1 + r.nextInt(l.al.size() - 1);
		Monstre monster = new Monstre(l.trouverSalle(valeur), dessin);
		l.listeMonstre.add(monster);*/
	}
	
	public void deplacer(Labyrinthe l) {
		Random r = new Random();
		int valeur = 1 + r.nextInt(Constantes.PROBA_DEPLACEMENT - 1);
		
		if (valeur <= Constantes.DEPLACEMENT)
			setSalleActuelle(salleAdjacente(l));
	}
	
	public Salle salleAdjacente(Labyrinthe l) {
		Salle nouvelleSalle = new Salle(getSalleActuelle());
		Random r = new Random();
		int direction = 1 + r.nextInt(Constantes.MAX_DIRECTION - 1);
		
		switch(direction) {
			case Constantes.BAS : 
				for (Salle s : l.getAl()) {
					if (s.x == getSalleActuelle().x && s.y == getSalleActuelle().y +1)
						nouvelleSalle = new Salle (s);
				}
				break;
				
			case Constantes.HAUT :
				for (Salle s : l.getAl()) {
					if (s.x == getSalleActuelle().x && s.y == getSalleActuelle().y -1)
						nouvelleSalle = new Salle (s);
				}
				break;
			
			case Constantes.DROITE :
				for (Salle s : l.getAl()) {
					if (s.x == getSalleActuelle().x+1 && s.y == getSalleActuelle().y)
						nouvelleSalle = new Salle (s);
				}
				break;
				
			default :
				for (Salle s : l.getAl()) {
					if (s.x == getSalleActuelle().x-1 && s.y == getSalleActuelle().y)
						nouvelleSalle = new Salle (s);
				}
				break;
		}
		
		return nouvelleSalle;
	}
	
}


