package Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
import Labyrinthe.Monstre;
import Labyrinthe.Salle;

public class TestLabyrinthe {

	Salle s1 = new Salle(1,1,1,null);
	Salle s2 = new Salle(1,1,2,null);
	Salle s3 = new Salle(1,1,3,null);
	Salle s4 = new Salle(1,1,4,null);
	ArrayList<Monstre> listeMonstre = new ArrayList<Monstre>();
	ArrayList<Salle> a1 = new ArrayList<Salle>();
	
	@Test
	public void testLabyrintheListeSalle(){
		a1.add(s1);
		assertEquals(a1.isEmpty(),false);
	}
	
	@Test
	public void testLabyrintheListeMonstre(){
		Monstre m = null;
		listeMonstre.add(m);
		assertEquals(listeMonstre.isEmpty(), false);
	}
	
	@Test
	public void testCreationMonstre(){
		a1.add(s2);
		a1.add(s3);
		a1.add(s4);
		listeMonstre.clear();
		Random r = new Random();
		int valeur = 1 + r.nextInt(a1.size() - 1);
		assertEquals(valeur, 1 + r.nextInt(a1.size() - 1));

	}
	@Test
	public void testTrouverSalle(){
		a1.add(s1);
		a1.add(s2);
		a1.add(s3);
		a1.add(s4);
		Salle retour = a1.get(2);
		assertEquals(retour,s3 );
	}
	
	// Problème assertion
	/*@Test
	public void testSupprimerPiege(){
		a1.add(s1);
		a1.add(s2);
		a1.add(s3);
		a1.add(s4);
		
		int cpt = 0, pos = 0;
		// Recherche de la position de la salle Ã  supprimer
		for (Salle salle : a1) {
			if (salle.x == s3.x && salle.y == s3.y) {
				pos = cpt;
			}
			else
				cpt++;
		}
		assertEquals(pos, 0);
	}*/
	
}
