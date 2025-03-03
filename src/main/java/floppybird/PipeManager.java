package floppybird;

import java.awt.Image;
import java.util.ArrayList;

public class PipeManager {
    ArrayList<Pipe> pipes;
    public int pipeSpeed = 3000;
    private final int BOARD_WIDTH, BOARD_HEIGHT;
    private final Image topPipeImage, bottomPipeImage;
    private final GameController gameController;

    public PipeManager(int BOARD_WIDTH, int BOARD_HEIGHT, Image topPipeImage, Image bottomPipeImage, GameController gameController) {
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.BOARD_HEIGHT = BOARD_HEIGHT;
        this.topPipeImage = topPipeImage;
        this.bottomPipeImage = bottomPipeImage;
        this.gameController = gameController;
        this.pipes = new ArrayList<>();
    }

    public void placePipes() {
        int pipeGap = this.BOARD_HEIGHT / 4;
        int pipeSpacing = 200; // spacing between pipes
        int numPipes = 2; // number of pipes
    
        for (int i = 0; i < numPipes; i++) {
            Pipe topPipe = new Pipe(this.BOARD_WIDTH + i * pipeSpacing, getRandomPipePosition(512), 64, 512, this.topPipeImage);
            topPipe.scored = true;
            pipes.add(topPipe);
    
            Pipe bottomPipe = new Pipe(this.BOARD_WIDTH + i * pipeSpacing, 0, 64, 512, this.bottomPipeImage);
            bottomPipe.y = topPipe.y + 512 + pipeGap;
            bottomPipe.scored = true;
            pipes.add(bottomPipe);
        }
    }
    

    public void update() {
        for (int i = 0; i < this.pipes.size(); i += 2) {
            Pipe topPipe = this.pipes.get(i);
            Pipe bottomPipe = this.pipes.get(i + 1);
            Bird bird = this.gameController.getBird();
            topPipe.update(bird);
            bottomPipe.update(bird);
            if (topPipe.x < -topPipe.width) {
                topPipe.x = this.BOARD_WIDTH;
                topPipe.y = getRandomPipePosition(512);
                bottomPipe.x = this.BOARD_WIDTH;
                bottomPipe.y = topPipe.y + 512 + this.BOARD_HEIGHT / 4;
                topPipe.scored = false;
                bottomPipe.scored = false;
            }
            if (topPipe.hasPassed() && !topPipe.scored) {
                this.gameController.setFloppyBirdScore(this.gameController.getFloppyBirdScore() + 1);
                topPipe.scored = true;
                if (this.gameController.getFloppyBirdScore() % 10 == 0) {
                    this.increasePipesVelocity();
                }
            }
        }
    }
    
    public void increasePipesVelocity(){
        for(Pipe pipe : this.pipes){
            pipe.increaseVelocity(pipe.velocity + 1);
        }
    }

    private int getRandomPipePosition(int pipeHeight){
        return (int)(0 - pipeHeight / 4 - Math.random()*(pipeHeight / 2));
    }

    public void setPipeSpeed(int speed){
        this.pipeSpeed = speed;
    }

    public void clearPipes(){
        this.pipes.clear();
    }

    ArrayList<Pipe> getList(){
        return this.pipes;
    }
}
