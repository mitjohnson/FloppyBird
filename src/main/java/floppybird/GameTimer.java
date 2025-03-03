package floppybird;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class GameTimer {

    public Timer gameLoop;
    private final PipeManager pipeManager;
    private final FloppyBird floppyBird;


    public int gameSpeed = 1000/60;

    GameTimer(PipeManager pipeManager, FloppyBird floppyBird){

        this.floppyBird = floppyBird;

        this.pipeManager = pipeManager; 
        this.pipeManager.placePipes();

        this.gameLoop = new Timer(this.gameSpeed, (ActionEvent e) -> {
            this.floppyBird.update();
            this.floppyBird.repaint();
            this.pipeManager.update();
        });
    }
    
    public void startAll(){
        this.gameLoop.start();
    }

    public void stopAll(){
        this.gameLoop.stop();
    }   
}
