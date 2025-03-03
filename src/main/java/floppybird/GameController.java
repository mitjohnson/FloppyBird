package floppybird;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

public class GameController {
    private final Image topPipeImage, bottomPipeImage;

    public boolean gameRunning, gameOver;
    private final Menu menu;

    public final FloppyBird floppyBird;
    private final GameTimer gameTimer;
    private final PipeManager pipeManager;

    public GameController(int windowWidth, int windowHeight, JLayeredPane layeredPane, App app) {

        // load pipe manager
        this.topPipeImage = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
        this.bottomPipeImage = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();
        this.pipeManager = new PipeManager(windowWidth, windowHeight, this.topPipeImage, this.bottomPipeImage, this);

        // load game
        this.floppyBird = new FloppyBird(windowWidth, windowHeight, this.pipeManager, this);
       
        // initalize game Timer
        this.gameTimer = new GameTimer(this.pipeManager, this.floppyBird);

        // load menu
        this.menu = new Menu(windowWidth, windowHeight,app,this, layeredPane);
    }

    public void showStartMenu(){
        this.menu.ShowStartButton("Start Game");
    }

    public void startGame(){
        this.gameRunning = true;
        this.gameTimer.startAll();
    }

    public void restartGame(){
        this.gameOver = false;
        this.gameRunning = true;
        this.floppyBird.score = 0;
        this.gameTimer.gameSpeed = 1000/60;
        this.floppyBird.resetBirdPosition();
        this.gameTimer.startAll();
    }

    public void showGameOverMenu(){
        this.gameRunning = false;
        this.gameOver = true;
        this.gameTimer.stopAll();
        this.menu.currentState = MenuState.GAME_OVER;
        this.menu.ShowStartButton("Play Again?");
    }

    public void pauseGame(){
        this.gameRunning = false;
        this.gameTimer.stopAll();
    }

    public FloppyBird getFloppyBirdPanel() {
        return this.floppyBird;
    }

    public void setFloppyBirdScore(int score){
        this.floppyBird.score = score;
    }

    public int getFloppyBirdScore(){
        return this.floppyBird.score;
    }

    Bird getBird(){
        return this.floppyBird.getBird();
    }
}
