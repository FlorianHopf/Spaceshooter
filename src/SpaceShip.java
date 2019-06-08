import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpaceShip {

	private Color farbe;
	private String ShipString;
	private Integer x, y = 0;
	
	private Integer lifes = 14;
	private Integer currentLifes = lifes;
	
	private String path = "imgs/Ship.png";
	
	private Image shipImage;
	
	List<Image> healthBar = new ArrayList<>();
	
	private Image heart = new Image("imgs/heart.png");
	private Image emptyHeart = new Image("imgs/emptyHeart.png");
	
	private Image damageTaken = new Image("imgs/damageTaken.gif");
	
	private Integer score = 0;
	
	private double width;
	private double height;
	
	/**
	 * ctor
	 * @param x
	 * @param y
	 */
	public SpaceShip(String a, Integer x, Integer y) {
		this.shipImage = new Image(path);
		this.ShipString = a;
		this.x = x;
		this.y = y;
		
		for(int i = 0; i < lifes; i++) {
			healthBar.add(heart);
		}
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
		gc.drawImage(shipImage, this.x, this.y);
		
		width = shipImage.getWidth();
        height = shipImage.getHeight();
        
        for(int i = 0; i < lifes; i++) {
        	gc.drawImage(healthBar.get(i), 550 - 30*i, 350);
        }
        
        gc.setFill(Color.WHITE);
        
        gc.fillText("Score: " + score, 300, 50);
	}

	/**
	 * Shoot rocket
	 * @return rocket object
	 */
	public Rocket fire() {
		Rocket r = new Rocket((int) (this.x + width/2), (int) (this.y+height/2), 4);
		return r;
	}
	
	/**
	 * 
	 * @return Bounds of the SpaceShip
	 */
	public Bounds getBounds() {
		Rectangle r = new Rectangle(this.x, this.y, width, height);
		return r.getBoundsInLocal();
	}
	
	/**
	 * Spaceship takes damage
	 * with 0 lifes the game ends
	 */
	public void damage(GraphicsContext gc) {
		
		currentLifes--;
		
		gc.drawImage(damageTaken, this.x, this.y);
		
		if(currentLifes < lifes) {
			healthBar.set(currentLifes, emptyHeart);
		}
		
		if(currentLifes == 0) {
			System.exit(0);
		}
	}
	
	public void scored(Integer amount) {
		this.score = this.score + amount;
	}
	
}
