package floppybird;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GameController {
    private final Image topPipeImage, bottomPipeImage;

    public boolean gameRunning, gameOver;

    private final FloppyBird floppyBird;
    private final GameTimer gameTimer;
    private final PipeManager pipeManager;

    public GameController(int windowWidth, int windowHeight) {

        this.topPipeImage = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
        this.bottomPipeImage = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();
        this.pipeManager = new PipeManager(windowWidth, windowHeight, this.topPipeImage, this.bottomPipeImage);

        this.floppyBird = new FloppyBird(windowWidth, windowHeight, this.pipeManager, this);
       
        // initalize game Timer
        this.gameTimer = new GameTimer(this.pipeManager, this.floppyBird, this);
        
    }

    public void startGame(){
        this.gameRunning = true;
        this.gameTimer.startAll();
    }

    public void pauseGame(){
        this.gameRunning = false;
    }

    public FloppyBird getFloppyBirdPanel() {
        return this.floppyBird;
    }
}
