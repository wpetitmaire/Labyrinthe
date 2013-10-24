package Labyrinthe;


public interface Constantes {
	
	// Constantes pour la visibilité des salles
	final static int ADJACENTE = 3;
	final static int PROCHE = 5;
	final static int ELOIGNEE = 8;
	
	// Couleur des salles
	final static int BLEUE = 0;
	final static int VERTE = 1;
	final static int BLANCHE = 2;
	final static int ROUGE = 3;
	final static int VIOLET = 4;
	
	// Taille des salles
	final static int TAILLE_SALLE = 20;
	
	// Décalage pour la taille de la fenêtre
	final static int DECALAGE_FENETRE = 30;
	
	
	// Constantes pour les salles piégées
	final static int PROBA_PIEGE = 130;
	final static int PIEGE_EXPLOSION = 1;
	final static int PIEGE_TELEPORTEUR = 2;
	
	// Constantes pour le joueur
	final static int VIES_DEPART = 3;
	
	// Constantes pour les vies
	final static int DECALAGE_IMG_VIES = 30;
	
	// Constantes pour les niveaux
	final static int NIVEAU_MAX = 5;
	
	// Constantes pour les monstres
	final static int PROBA_DEPLACEMENT = 10;
	final static int DEPLACEMENT = 2;
	final static int MAX_DIRECTION = 5;
	final static int BAS = 1;
	final static int HAUT = 2;
	final static int DROITE = 3;



}