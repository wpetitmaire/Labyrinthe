import processing.core.PApplet;
import processing.core.PImage;

public class Salle {

	int x;
	int y;
	int color;
	int taille = 22;
	PApplet dessin;

	Salle(int abs, int ord, int col, PApplet ap) {
		this.x = abs;
		this.y = ord;
		this.color = col;
		dessin = ap;
	}

	Salle(Salle s) {
		this.x = s.x;
		this.y = s.y;
		this.color = s.color;
		this.dessin = s.dessin;
	}

	public void draw(PImage i) {
		dessin.tint(255, 255, 255, 255);

		if (this.color == 0)
			dessin.tint(0, 0, 255, 255);
		if (this.color == 1)
			dessin.tint(255, 0, 0, 255);

		dessin.image(i, x * taille, y * taille, taille, taille);
	}

	public void drawEclairage(PImage i, Salle s) {
		float d = this.distance(s);
		int opacite = 255;

		if (d >= 2 && d < 5)
			opacite = 150;
		else if (d >= 5 && d < 10)
			opacite = 100;
		else if (d >= 10)
			opacite = 0;

		dessin.tint(255, 255, 255, opacite);

		if (this.color == 0)
			dessin.tint(0, 0, 255, opacite);
		if (this.color == 1)
			dessin.tint(255, 0, 0, opacite);

		dessin.image(i, x * taille, y * taille, taille, taille);
	}

	public static void main(String[] args) {

	}

	public float distance(Salle s) {
		float d = (this.x - s.x) * (this.x - s.x) + (this.y - s.y)
				* (this.y - s.y);
		if (d > 0)
			return (int) (Math.sqrt(d));
		else
			return 255;
	}

}
