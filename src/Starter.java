import java.util.ArrayList;
import java.util.List;

import GameElements.Asteroid;
import GameElements.Rocket;
import GameElements.SpaceShip;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Starter extends Application implements EventHandler<KeyEvent> {

	SpaceShip ship = new SpaceShip(100, 100);
	List<Asteroid> asteroid = new ArrayList<>();
	List<Rocket> rockets = new ArrayList<>();
	List<Asteroid> exploding = new ArrayList<>();
	List<Asteroid> exploded = new ArrayList<>();

	final int WIDTH = 600;
	final int HEIGHT = 400;
	
	Circle c;

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("SpaceShooter - Hopf");

		Group root = new Group();

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(this);
		root.getChildren().add(canvas);

		Image img = new Image("imgs/background.png");

		Canvas statisch = new Canvas(WIDTH, HEIGHT);

		root.getChildren().add(statisch);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		for (int i = 0; i < 35; i++) {
			Asteroid a = new Asteroid(0, 0, 0);
			a.reposition(WIDTH, HEIGHT);
			asteroid.add(a);
		}

		
		// GameLoop
		new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {

					if (ship.isGameOver() == false) {

						statisch.toFront();

						statisch.getGraphicsContext2D().clearRect(0, 0, WIDTH, HEIGHT);
						ship.drawStaticAssets(statisch.getGraphicsContext2D());

						canvas.getGraphicsContext2D().clearRect(0, 0, WIDTH, HEIGHT);
						canvas.getGraphicsContext2D().drawImage(img, 0, 0, WIDTH, HEIGHT);
						ship.paint(canvas.getGraphicsContext2D());
						for (Asteroid ast : asteroid) {
							ast.paint(canvas.getGraphicsContext2D());

							if (ast.isVisible() == false) {
								ast.reposition(WIDTH, HEIGHT);
							}
						}

						for (Asteroid ast : asteroid) {
							if (ast.getBounds().intersects(ship.getBounds()) && (ast.hitted() == false)) {
								ship.damage(canvas.getGraphicsContext2D());
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
								if (ast.getBounds().intersects(rocket.getBounds()) && ast.exploding() == false) {
									ship.scored(20);
									ast.explodes();
									exploding.add(new Asteroid(ast.getX() - 125, ast.getY(), ast.getSpeed()));
								}
							}
						}

						rockets.removeAll(zuLoeschen);
						for (Asteroid ast : asteroid) {
							for (Asteroid explode : exploding) {
								if (ast.exploding()) {
									if (explode.getBounds().intersects(ast.getBounds())
											&& explode.getY().equals(ast.getY())) {
										ast.reposition(WIDTH, HEIGHT);
										exploded.add(explode);
									}
								}
							}
						}
						exploding.removeAll(exploded);

					} else {
						int c = rockets.size();

						for (int i = c - 1; i >= 0; i--) {
							rockets.remove(i);
						}

						canvas.toFront();

						canvas.getGraphicsContext2D().setFill(Color.BLACK);
						canvas.getGraphicsContext2D().fillRect(0, 0, WIDTH, HEIGHT);

						canvas.getGraphicsContext2D().setFill(Color.WHITE);

						canvas.getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);

						String end = "GAME OVER";

						canvas.getGraphicsContext2D().setFont(new Font("Arial", 50));

						canvas.getGraphicsContext2D().fillText(end, WIDTH / 2, HEIGHT / 2);

						String restart = "Press Enter to Restart";

						canvas.getGraphicsContext2D().setFont(new Font("Arial", 20));

						canvas.getGraphicsContext2D().fillText(restart, WIDTH / 2, HEIGHT / 2 + 50);

						canvas.getGraphicsContext2D().fillText("Current Score: " + ship.getScore(), WIDTH / 2,
								HEIGHT / 2 + 150);
					}
				}
		}.start();

	}

	/**
	 * kÃ¼mmert sich um die Tastatureingaben
	 * 
	 * @param evnt
	 */
	public void handle(KeyEvent evnt) {

		int accellerator = 1;

		if (evnt.isShiftDown()) {
			accellerator = 2;
		}

		switch (evnt.getCode().getName()) {
		case "W":
			ship.moveUp(10 * accellerator);
			break;
		case "S":
			ship.moveDown(10 * accellerator);
			break;
		case "A":
			ship.moveLeft(10 * accellerator);
			break;
		case "D":
			ship.moveRight(10 * accellerator);
			break;
		case "Space":
			Rocket r = ship.fire();
			rockets.add(r);
			break;
		case "Enter":
			if (ship.isGameOver()) {
				ship.restart();
				for (Asteroid ast : asteroid) {
					ast.reposition(WIDTH, HEIGHT);
				}
			}
		}
	}

}
