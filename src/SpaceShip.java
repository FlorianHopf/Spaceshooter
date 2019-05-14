import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpaceShip {

	private Color farbe;
	private String ShipString;
	private Integer x, y = 0;
	private Integer lifes = 100;
	private Integer invincibleTimer = 0;
	/**
	 * ctor
	 * @param x 
	 * @param y
	 */
	public SpaceShip(String a, Integer x, Integer y) {
		this.ShipString = a;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * moves the SpaceShip up
	 * @param speed the value of the movement
	 */
	public void moveUp(int speed) {
		this.y -= speed;
	}

	
	/**
	 * moves the SpaceShip down
	 * @param speed the value of the movement
	 */
	public void moveDown(int speed) {
		this.y += speed;
	}

	
	/**
	 * moves the SpaceShip to the left
	 * @param speed the value of the movement
	 */
	public void moveLeft(int speed) {
		this.x -= speed;
	}

	
	/**
	 * moves the SpaceShip to the right
	 * @param speed the value of the movement
	 */
	public void moveRight(int speed) {
		this.x += speed;
	}
	
	public String toString() {
		return "SpaceShip(" + this.x + "|" + this.y + ")";
	}
	
	
	/**
	 * Zeichnet 
	 * @param gc
	 */
	public void paint(GraphicsContext gc) {
		gc.fillText(ShipString, x, y);
	}

	public Rocket fire() {
		Rocket r = new Rocket(this.x, this.y, 2);
		return r;
	}
	
	public Bounds getBounds() {
		Rectangle r = new Rectangle(this.x, this.y, 5, 5);
		return r.getBoundsInLocal();
	}
	
	public void damage() {
		this.lifes--;
		System.out.println("Leben: " + lifes);
	}
	
}
