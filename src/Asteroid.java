import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Asteroid {

	private String AsteroidString = "*";
	private Integer x, y = 0;
	private int speed;
	private boolean hitted = false;
	
	/**
	 * ctor
	 * @param x 
	 * @param y
	 */
	public Asteroid(Integer x, Integer y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void move() {
		this.x -= this.speed;
	}
	
	public String toString() {
		return "Asteroid(" + this.x + "|" + this.y + "|" + this.speed + ")";
	}
	
	
	/**
	 * Zeichnet 
	 * @param gc
	 */
	public void paint(GraphicsContext gc) {
		move();
		gc.fillText(AsteroidString, x, y);
	}

	public boolean isVisible() {
		return (this.x > 0);
	}
	
	public void reposition(int width, int height) {
		Random rnd = new Random();
		
		this.x = width + rnd.nextInt(width);
		this.y = rnd.nextInt(height);
		this.speed = 1 + rnd.nextInt(4);
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	/**
	 * Liefert die Grenzen des Asteroiden zurück.
	 * @return 
	 */
	public Bounds getBounds() {
		Circle c = new Circle(this.x, this.y, 5);
		return c.getBoundsInLocal();
	}
	
	public void explodes() {
		
	}
	
	public void hit(boolean status) {
		this.hitted = status;
	}
	
	public boolean hitted() {
		return hitted;
	}
}


