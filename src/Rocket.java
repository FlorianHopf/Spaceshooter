import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Rocket {

    private String rocket = "--->";
    
    private Image rocketImg;
    
    private Integer x, y;
    private int speed;
    
    private String path = "imgs/rocket.png";

    @Override
    public String toString() {
        return "Rakete=" + rocket + ", x=" + x + ", y=" + y + ", speed=" + speed;
    }

    public Rocket(Integer x, Integer y, int speed) {
    	this.rocketImg = new Image(path);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move() {
        this.x += this.speed;
    }

    public boolean isVisible() {
        return this.x < 600;
                }

    public void reposition(int width, int height) {
        Random rnd = new Random();

        this.x = width + rnd.nextInt(width);
        this.y = rnd.nextInt(height);
        this.speed = 1 + rnd.nextInt(3);
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

    public void paint(GraphicsContext gc) {
        gc.drawImage(rocketImg, x, y);
    }

    public Bounds getBounds() {
		Rectangle r = new Rectangle(this.x, this.y, rocketImg.getWidth(), rocketImg.getHeight());
		return r.getBoundsInLocal();
	}
}