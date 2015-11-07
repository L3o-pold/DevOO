package modele;

public class Intersection {

    /*--- Attributes ---*/

	private int id;
	private int x;
	private int y;

    /*--- Constructor ---*/

	public Intersection(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

    /*--- Accessors ---*/

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
