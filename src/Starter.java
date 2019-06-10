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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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

	private boolean mainMenu = true;

	private boolean options = false;
	
	private int difficulty = 2;
	private String difficultyString = "Intermediate";
	
	private boolean createAsteroids = false;
	
	private Rectangle easyButton;
	private Rectangle intermediateButton;
	private Rectangle hardButton;
	
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
		
		Canvas mainMenuView = new Canvas(WIDTH, HEIGHT);
		mainMenuView.setFocusTraversable(true);
		
		root.getChildren().add(mainMenuView);
		
		Canvas optionsView = new Canvas(WIDTH, HEIGHT);
		optionsView.setFocusTraversable(true);
		
		root.getChildren().add(optionsView);

		root.getChildren().add(statisch);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
		for (int i = 0; i < 25 * difficulty; i++) {
			Asteroid a = new Asteroid(0, 0, 0);
			a.reposition(WIDTH, HEIGHT);
			asteroid.add(a);
		}

		
		
		// GameLoop
		new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {
				if (mainMenu) {
					if(options == true) {
						
						optionsView.toFront();
						
						optionsView.getGraphicsContext2D().setFill(Color.BLACK);

						optionsView.getGraphicsContext2D().fillRect(0, 0, WIDTH, HEIGHT);

						optionsView.getGraphicsContext2D().setFill(Color.WHITE);

						optionsView.getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);

						// Easy - Mode Button
						String easy = "Easy";

						float easyWidth = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(easy,
								optionsView.getGraphicsContext2D().getFont());
						float easyHeight = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader()
								.getFontMetrics(optionsView.getGraphicsContext2D().getFont()).getLineHeight();

						optionsView.getGraphicsContext2D().setFont(new Font("Arial", 20));

						optionsView.getGraphicsContext2D().fillText(easy, WIDTH / 2, HEIGHT / 2 - 70);
						
						
						//Intermediate - Mode Button
						String intermediate = "Intermediate";

						float intermediateWidth = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(intermediate,
								optionsView.getGraphicsContext2D().getFont());
						float intermediateHeight = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader()
								.getFontMetrics(optionsView.getGraphicsContext2D().getFont()).getLineHeight();

						optionsView.getGraphicsContext2D().setFont(new Font("Arial", 20));

						optionsView.getGraphicsContext2D().fillText(intermediate, WIDTH / 2, HEIGHT / 2);

						
						//Hard - Mode Button
						
						String hard = "Hard";

						float hardWidth = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(hard,
								optionsView.getGraphicsContext2D().getFont());
						
						float hardHeight = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader()
								.getFontMetrics(optionsView.getGraphicsContext2D().getFont()).getLineHeight();

						optionsView.getGraphicsContext2D().setFont(new Font("Arial", 20));

						optionsView.getGraphicsContext2D().fillText(hard, WIDTH / 2, HEIGHT / 2 + 70);
						
						// Create buttons
						easyButton = new Rectangle(WIDTH / 2 - easyWidth / 2, HEIGHT / 2 - easyHeight / 2 -75 ,
								easyWidth, easyHeight);
						
						intermediateButton = new Rectangle(WIDTH / 2 - easyWidth / 2, HEIGHT / 2 - easyHeight / 2,
								intermediateWidth, intermediateHeight);

						hardButton = new Rectangle(WIDTH / 2 - hardWidth / 2, HEIGHT / 2 - hardHeight / 2 + 70,
								hardWidth, hardHeight);

						//Mouse Handler
						
					} else {
						mainMenuView.toFront();
						mainMenuView.getGraphicsContext2D().setFill(Color.BLACK);

						mainMenuView.getGraphicsContext2D().fillRect(0, 0, WIDTH, HEIGHT);

						mainMenuView.getGraphicsContext2D().setFill(Color.WHITE);

						mainMenuView.getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);

						// Start game button
						String start = "Start the game";

						float startWidth = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(start,
								mainMenuView.getGraphicsContext2D().getFont());
						float startHeight = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader()
								.getFontMetrics(mainMenuView.getGraphicsContext2D().getFont()).getLineHeight();

						mainMenuView.getGraphicsContext2D().setFont(new Font("Arial", 30));

						mainMenuView.getGraphicsContext2D().fillText(start, WIDTH / 2, HEIGHT / 2);
						
						
						//Options Button
						String optionsDialog = "Options";

						float optionsWidth = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(optionsDialog,
								mainMenuView.getGraphicsContext2D().getFont());
						float optionsHeight = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader()
								.getFontMetrics(mainMenuView.getGraphicsContext2D().getFont()).getLineHeight();

						mainMenuView.getGraphicsContext2D().setFont(new Font("Arial", 20));

						mainMenuView.getGraphicsContext2D().fillText(optionsDialog, WIDTH / 2, HEIGHT / 2 + 60);

						// Create buttons
						Rectangle button = new Rectangle(WIDTH / 2 - startWidth / 2, HEIGHT / 2 - startHeight / 2 - 10,
								startWidth, startHeight);
						
						Rectangle optionsButton = new Rectangle(WIDTH / 2 - optionsWidth / 2, HEIGHT / 2 + 60 - optionsHeight / 2,
								optionsWidth, optionsHeight);

						mainMenuView.getGraphicsContext2D().setFont(new Font("Arial", 15));

						mainMenuView.getGraphicsContext2D().fillText("Difficulty: " + difficultyString, WIDTH / 2, HEIGHT / 2 + 170);
						
						
						//Mouse Handler
						mainMenuView.addEventHandler(MouseEvent.MOUSE_CLICKED, 
							       new EventHandler<MouseEvent>() {
							public void handle(MouseEvent e) {
								Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
								
								if (optionsButton.getBoundsInLocal().intersects(r.getBoundsInLocal())) {
									options = true;
								}

								if (button.getBoundsInLocal().intersects(r.getBoundsInLocal())) {
									mainMenu = false;
								}
							}
						});
					}	
				} else {
					mainMenuView.toBack();
					optionsView.toBack();
					
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
						
						String backToMain = "Press Esc for Main Menu";

						canvas.getGraphicsContext2D().fillText(backToMain, WIDTH / 2, HEIGHT / 2 + 100);

						canvas.getGraphicsContext2D().fillText("Current Score: " + ship.getScore(), WIDTH / 2,
								HEIGHT / 2 + 150);
					}
				}
			}

		}.start();

		optionsView.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			       new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				
				Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
				if (easyButton.getBoundsInLocal().intersects(r.getBoundsInLocal())) {
					difficulty = 1;
					options = false;
					createAsteroids = true;
					difficultyString = "Easy";
				}

				if (intermediateButton.getBoundsInLocal().intersects(r.getBoundsInLocal())) {
					difficulty = 2;
					options = false;
					createAsteroids = true;
					difficultyString = "Intermediate";
				}
				
				if (hardButton.getBoundsInLocal().intersects(r.getBoundsInLocal())) {
					difficulty = 3;
					options = false;
					createAsteroids = true;
					difficultyString = "Hard";
				}	
				
				if(createAsteroids) {
					asteroid.clear();
					for (int i = 0; i < 15 * difficulty; i++) {
						Asteroid a = new Asteroid(0, 0, 0);
						a.reposition(WIDTH, HEIGHT);
						asteroid.add(a);
					}
					
				}
				
			}
		});
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

		if(mainMenu == false) {
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
			case "Esc": if(ship.isGameOver()) {
					mainMenu = true;
					ship.restart();
					for (Asteroid ast : asteroid) {
						ast.reposition(WIDTH, HEIGHT);
					}
				}
			break;
			}
		}
		
	}

}
