package Test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import Labyrinthe.Constantes;
import Labyrinthe.Monstre;
import Labyrinthe.Salle;

public class testMonstre {

	Salle s1 = new Salle(1,1,1,null);
	Monstre m = new Monstre(s1,null);
	//Labyrinthe l = new Labyrinthe();
	
	/*
	@Test
	public void testConstructeur(){
		Monstre m = new Monstre(s1,null);
		assertEquals(m.getSalleActuelle(),s1);
	}
	
	@Test
	public void testEffetCollision(){
		
	}
	
	*/
	
	/*@Test
	public void testDeplacer(){
		int valeur  = 5;
		if(valeur <= Constantes.DEPLACEMENT)
			m.setSalleActuelle(m.salleAdjacente(l));
	}*/
}
