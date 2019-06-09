import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SpaceShip {

	private Integer x, y = 0;
	private Integer startX, startY = 0;
	
	private Integer lifes = 5;
	private Integer currentLifes = lifes;
	private boolean gameOver = false;
	
	private String path = "imgs/ship.png";
	
	private Image shipImage;
	
	List<Image> healthBar = new ArrayList<>();
	
	private Image heart = new Image("imgs/heart.png");
	private Image emptyHeart = new Image("imgs/emptyHeart.png");
	
	private Integer score = 0;
	
	private double width;
	private double height;
	
	/**
	 * ctor
	 * @param x
	 * @param y
	 */
	public SpaceShip( Integer x, Integer y) {
		this.shipImage = new Image(path);
		this.x = x;
		this.y = y;
		
		this.startX = x;
		this.startY = y;
		
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
	}
	
	public void drawStaticAssets(GraphicsContext gc) {
		 for(int i = 0; i < lifes; i++) {
	        	gc.drawImage(healthBar.get(i), 550 - 30*i, 350);
	        }
	        
	        gc.setFill(Color.WHITE);
	        gc.setFont(new Font("Arial", 20));
	        
	        gc.fillText("Score: " + score, 450, 50);
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
		
		if(currentLifes < lifes) {
			healthBar.set(currentLifes, emptyHeart);
		}
		
		if(currentLifes == 0) {
			gameOver = true;
		}
	}
	
	public void scored(Integer amount) {
		this.score = this.score + amount;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void restart() {
		this.gameOver = false;
		this.currentLifes = lifes;
		
		this.x = this.startX;
		this.y = this.startY;
		
		this.score = 0;
		
		for(int i = 0; i < lifes; i++) {
			healthBar.set(i, heart);
		}
	}
	
	
}
