package GameElements;
import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Asteroid {

	private Integer x, y = 0;
	private int speed;
	private boolean hitted = false;
	private boolean exploding = false;
	
	private double width;
	private double height;
	
	private Image asteroidImg = new Image("imgs/asteroid.png");
	
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
	
	public int getSpeed() {
		return speed;
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
		gc.drawImage(asteroidImg, this.x, this.y);
		
		width = asteroidImg.getWidth();
        height = asteroidImg.getHeight();
	}

	public boolean isVisible() {
		return (this.x > 0);
	}
	
	public void reposition(int width, int height) {
		Random rnd = new Random();
		
		this.x = width + rnd.nextInt(width);
		this.y = rnd.nextInt(height);
		this.speed = 1 + rnd.nextInt(2);
		
		hitted = false;
		exploding = false;

		asteroidImg = new Image("imgs/asteroid.png");
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
		Circle c = new Circle(this.x + width/2, this.y + height/2, 10);
		return c.getBoundsInLocal();
	}
	
	public void explodes() {
		
		asteroidImg = new Image("imgs/explosion.png");
		hitted = true;
		exploding = true;
		
	}
	
	public void hit(boolean status) {
		this.hitted = status;
	}
	
	public boolean hitted() {
		return hitted;
	}
	
	public boolean exploding() {
		return exploding;
	}
}


