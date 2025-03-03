package floppybird;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

enum GameTimerState {
    RUNNING,
    NOT_RUNNING
}

public class GameTimer {

    public Timer gameLoop;
    private final PipeManager pipeManager;
    private final FloppyBird floppyBird;

    GameTimerState currentState;
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

    private GameTimerState getState(){
        return this.currentState;
    }

    public void update(){
        switch (getState()) {
            case RUNNING -> startAll();
            case NOT_RUNNING -> stopAll();

        }
    }
    
    private void startAll(){
        this.gameLoop.start();
    }

    private void stopAll(){
        this.gameLoop.stop();
    }   
}
