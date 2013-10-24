package Labyrinthe;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;


@SuppressWarnings("serial")
public class App extends PApplet {

	int windowWidth, windowHeight;
	String filePath;
	Scanner scanner;
	PImage back, caseLab, imgPerso, vie, vie2, vie3, imgMonstre;
	Labyrinthe lab;
	boolean finDePartie;
	int numeroNiveau=1;
	
	public void setup() {
		
		filePath = "ressources/level1.txt";
		finDePartie = false;
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
		lab.al.clear();
		lab.load();
		lab.perso.nbVies = Constantes.VIES_DEPART;
		lab.perso.setSalleCourante(lab.entree);
	}
	
	void controleur() {
		// Appel de la fonction gérant les déplacements du personnage
		if (keyPressed == true)
			lab.keyPressed();
		for (Monstre m : lab.listeMonstre)
			m.deplacer(lab);
		
		// Si le personnage a atteint l'arrivéé, on affiche un message
		if (lab.perso.getSalleCourante().x == lab.sortie.x && lab.perso.getSalleCourante().y == lab.sortie.y) {
			textSize(40);
			text("Bravo !", windowWidth/2, windowHeight/3);
			text("Appuyer sur Entree pour continuer", windowWidth/6, windowHeight/2);
			fill(255,255,255);
			
			if (touchePressee()) {
				// Passage au niveau suivant
				lab.al.clear();
				numeroNiveau++;
				if (numeroNiveau > Constantes.NIVEAU_MAX)
					numeroNiveau = 1;
				
				filePath = "ressources/level"+numeroNiveau+".txt";
				
				lab = new Labyrinthe(this, filePath);
				lab.load();
				lab.perso.nbVies = Constantes.VIES_DEPART;
				lab.perso.setSalleCourante(lab.entree);
			}
		}
		
		// Si la salle est piégée
		if (lab.perso.salleCourante instanceof SallePiege) {
			( (SallePiege) lab.perso.salleCourante).effet(lab);
		}
		
		// Si le joueur rencontre un monstre
		for (Monstre m : lab.listeMonstre) {
			if (lab.perso.salleCourante.x == m.salleActuelle.x && lab.perso.salleCourante.y == m.salleActuelle.y)
				m.effetCollision(lab);
		}
		
		// Si le joueur n'a plus de vie
		if (lab.perso.nbVies <=0) {
			redemarrage();
			finDePartie = true;
		}
	}
	
	public void draw() {
		

		
		tint(255,255,255,255);
		// Dessin du backgound et du labyrinthe
		image(back, 0, 0, windowWidth, windowHeight);
		lab.draw(caseLab, imgPerso, imgMonstre);
		
		controleur();
		
		
		// Dessin des vies
		tint(255, 0, 0, 255);
		textSize(15);
		text("Vies : ", 10, 15);
		if (lab.perso.nbVies>0)
			image(vie, 15, 18, 20, 20);
		if (lab.perso.nbVies>1)
			image(vie2, 15+Constantes.DECALAGE_IMG_VIES, 18, 20, 20);
		if (lab.perso.nbVies>2)
			image(vie3, 15 + 2*Constantes.DECALAGE_IMG_VIES, 18, 20, 20);

		// Dessin du game over si le joueur a perdu
		if (finDePartie) {
			background(0);
			textSize(45);
			text("Game over ... \n", windowWidth/3, windowHeight/3);
			textSize(35);
			text("Appuyer sur Entree pour continuer", windowWidth/6, windowHeight/2);
			// On vérifie si l'utilisateur presse Entrée
			if (touchePressee())
				finDePartie = false;
		}
	}

	boolean touchePressee() {
		if (keyPressed && key == ENTER)
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