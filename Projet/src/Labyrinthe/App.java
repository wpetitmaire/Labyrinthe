package Labyrinthe;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;


@SuppressWarnings("serial")
public class App extends PApplet {
	
	private etatDuJeu etat;
	private int windowWidth, windowHeight;
	private String filePath;
	private Scanner scanner;
	private PImage back, caseLab, imgPerso, vie, vie2, vie3, imgMonstre;
	private Labyrinthe lab;
	private int numeroNiveau;
	
	public void setup() {
		etat = etatDuJeu.JEU;
		numeroNiveau = 1;
		filePath = "ressources/level"+numeroNiveau+".txt";
		try {
			scanner = new Scanner(new File(filePath));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Récupération de la taille de la fenêtre
		windowWidth = scanner.nextInt()*Constantes.TAILLE_SALLE + Constantes.DECALAGE_FENETRE;
		windowHeight = scanner.nextInt()*Constantes.TAILLE_SALLE + Constantes.DECALAGE_FENETRE;
		
		// Création et chargement du labyrinthe
		lab = new Labyrinthe(this, filePath);
		lab.load();
		
		// Chargement des différentes images
		back = loadImage("ressources/back.jpg");
		caseLab = loadImage("ressources/case.jpg");
		imgPerso = loadImage("ressources/perso.png");
		vie = loadImage("ressources/vie.png");
		vie2 = vie;
		vie3 = vie;
		imgMonstre = loadImage("ressources/flammes.png");
		size(windowWidth, windowHeight);
	}
	
	
	void redemarrage()
	{
		lab.getAl().clear();
		lab.load();
		lab.getPersonnage().nbVies = Constantes.VIES_DEPART;
		lab.getPersonnage().setSalleCourante(lab.getEntree());
	}
	
	void controleur() {
		switch (etat) {
		case JEU :
			// Appel de la fonction gérant les déplacements du personnage
			if (keyPressed == true)
				lab.keyPressed();
			for (Monstre m : lab.getListeMonstre())
				m.deplacer(lab);
			
			// Si le personnage a atteint l'arrivée
			if (lab.getPersonnage().getSalleCourante().x == lab.getSortie().x && lab.getPersonnage().getSalleCourante().y == lab.getSortie().y && numeroNiveau<= Constantes.NIVEAU_MAX)
				etat = etatDuJeu.ARRIVEE;
			
			// Si la salle est piégée
			if (lab.getPersonnage().salleCourante instanceof SallePiege) {
				( (SallePiege) lab.getPersonnage().salleCourante).effet(lab);
			}
			
			// Si le joueur rencontre un monstre
			for (Monstre m : lab.getListeMonstre()) {
				if (lab.getPersonnage().salleCourante.x == m.getSalleActuelle().x && lab.getPersonnage().salleCourante.y == m.getSalleActuelle().y)
					m.effetCollision(lab);
			}
			
			// Si le joueur n'a plus de vie
			if (lab.getPersonnage().nbVies <=0) {
				redemarrage();
				etat = etatDuJeu.GAME_OVER;
			}
			break ;
			
		case ARRIVEE:
			if (toucheEntreePressee()) {
				// Passage au niveau suivant
				lab.getAl().clear();
				
				if (numeroNiveau >= Constantes.NIVEAU_MAX)
					etat = etatDuJeu.TERMINE;
				else {
					etat = etatDuJeu.JEU;
					numeroNiveau++;
				}
				
				filePath = "ressources/level"+numeroNiveau+".txt";
				lab = new Labyrinthe(this, filePath);
				lab.load();
				lab.getPersonnage().nbVies = Constantes.VIES_DEPART;
				lab.getPersonnage().setSalleCourante(lab.getEntree());
				
				
			}
			break;
			
		case GAME_OVER :
			if (toucheEntreePressee())
				etat = etatDuJeu.JEU;
			break;
			
		case TERMINE :
			if (choixOui())
				etat = etatDuJeu.JEU;
			numeroNiveau = 1;
			break;
		}
		
			
		
	}
	
	public void draw() {
		
		switch(etat) {
		case JEU :
			tint(255,255,255,255);
			// Dessin du backgound et du labyrinthe
			image(back, 0, 0, windowWidth, windowHeight);
			lab.draw(caseLab, imgPerso, imgMonstre);
			
			// Dessin des vies
			tint(255, 0, 0, 255);
			textSize(15);
			text("Vies : ", 10, 15);
			if (lab.getPersonnage().nbVies>0)
				image(vie, 15, 18, 20, 20);
			if (lab.getPersonnage().nbVies>1)
				image(vie2, 15+Constantes.DECALAGE_IMG_VIES, 18, 20, 20);
			if (lab.getPersonnage().nbVies>2)
				image(vie3, 15 + 2*Constantes.DECALAGE_IMG_VIES, 18, 20, 20);
			break;
			
		case ARRIVEE :
			background(0);
			textSize(40);
			text("Niveau "+numeroNiveau+" fini !\n", windowWidth/2, windowHeight/3);
			text("Appuyer sur Entree pour continuer", windowWidth/6, windowHeight/2);
			fill(255,255,255);
			break;
			
		case GAME_OVER :
			// Dessin du game over si le joueur a perdu
				background(0);
				textSize(45);
				text("Game over ... \n", windowWidth/3, windowHeight/3);
				textSize(35);
				text("Appuyer sur Entree pour continuer", windowWidth/6, windowHeight/2);
			break;
			
		case TERMINE :
			background(0);
			tint(255, 255, 255);
			textSize(40);
			text("Vous avez reussi a sortir !", windowWidth/6, windowHeight/3);
			text("Recommencer ?", windowWidth/6, windowHeight/2);
			fill(255,255,255);
			break;
		}
		
		controleur();
	}

	boolean toucheEntreePressee() {
		if (keyPressed && key == ENTER)
			return true;
		
		return false;	
	}
	
	boolean choixOui() {
		if (keyPressed && key == 'o')
			return true;
		
		return false;	
	}
	
	
	public Labyrinthe getLab() {
		return lab;
	}
	
	public static void main(String[] args) {
		PApplet.main("Application");
	}
}