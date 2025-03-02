package floppybird;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class GameTimer {

    public Timer gameLoop, placePipesTimer;
    private final PipeManager pipeManager;
    private final FloppyBird floppyBird;
    private final GameController gameController;

    GameTimer(PipeManager pipeManager, FloppyBird floppyBird, GameController gameController){

        this.floppyBird = floppyBird;
        this.gameController = gameController;

        this.gameLoop = new Timer(1000/60, (ActionEvent e) -> {
            if (!this.gameController.gameRunning) {
                return;
            }

            this.floppyBird.update();
            this.floppyBird.repaint();
        });

        this.pipeManager = pipeManager; 
        this.placePipesTimer = new Timer(this.pipeManager.pipeSpeed, (ActionEvent e) -> {
            if (!this.gameController.gameRunning) {
                return;
            }
            this.pipeManager.placePipes();
        });

    }
    
    public void startAll(){
        this.gameLoop.start();
        this.placePipesTimer.start();
    }

    public void stopAll(){
        this.gameLoop.stop();
        this.placePipesTimer.stop();
    }   
}
