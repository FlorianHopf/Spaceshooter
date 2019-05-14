import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Starter extends Application implements EventHandler<KeyEvent>{

	SpaceShip ship = new SpaceShip("🚀", 100, 100);
	List<Asteroid> asteroid = new ArrayList<>();
	List<Rocket> rockets = new ArrayList<>();
	
	final int WIDTH = 600;
	final int HEIGHT = 400;
	final int hitRate = 30 /2;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpaceShooter - Hopf");
		
		Group root = new Group();
		
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(this);
		root.getChildren().add(canvas);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
		for(int i = 0; i < 50; i++) {
			Asteroid a = new Asteroid(0, 0, 0);
			a.reposition(WIDTH, HEIGHT);
			asteroid.add(a);
		}
		
		// GameLoop
		new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {
				canvas.getGraphicsContext2D().clearRect(0, 0,WIDTH, HEIGHT);
				ship.paint(canvas.getGraphicsContext2D());
				for(Asteroid ast : asteroid) {
					ast.paint(canvas.getGraphicsContext2D());
					
					if(ast.isVisible() == false) {
						ast.reposition(WIDTH, HEIGHT);
					}
				} 
				
			for (Asteroid ast : asteroid) {
				if(ast.getBounds().intersects(ship.getBounds()) && (ast.hitted() == false)) {
						ship.damage();
						ast.hit(true);
				}
			}
			
				List<Rocket> zuLoeschen = new ArrayList<>();	
				
			for (Rocket rocket : rockets) {

				rocket.paint(canvas.getGraphicsContext2D());
				rocket.move();

				if (rocket.isVisible() == false) {
					zuLoeschen.add(rocket);
				}
				
				for (Asteroid ast : asteroid) {
					if(ast.getBounds().intersects(rocket.getBounds())) {
						ast.reposition(WIDTH, HEIGHT);
						ast.hit(false);
					}
				}
			}
			rockets.removeAll(zuLoeschen);
			}
		}.start();
	
	}

	/**
	 * kümmert sich um die Tastatureingaben
	 * @param evnt
	 */
	@Override
	public void handle(KeyEvent evnt) {
		
		int accellerator = 1;
		
		if(evnt.isShiftDown()) {
			accellerator = 2;
		}
		
		
		
		switch(evnt.getCode().getName()) {
		case "W":ship.moveUp(10 * accellerator);break;
		case "S":ship.moveDown(10 * accellerator);break;
		case "A":ship.moveLeft(10 * accellerator);break;
		case "D":ship.moveRight(10 * accellerator);break;
		case "Space":Rocket r = ship.fire();
					rockets.add(r);
					break;
		}	
	}   

}
