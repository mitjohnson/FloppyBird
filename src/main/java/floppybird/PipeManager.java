package floppybird;

import java.awt.Image;
import java.util.ArrayList;

public class PipeManager {
    ArrayList<Pipe> pipes;
    public int pipeSpeed = 2000;
    private final int BOARD_WIDTH, BOARD_HEIGHT;
    private final Image topPipeImage, bottomPipeImage;

    public PipeManager(int BOARD_WIDTH, int BOARD_HEIGHT, Image topPipeImage, Image bottomPipeImage) {
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.BOARD_HEIGHT = BOARD_HEIGHT;
        this.topPipeImage = topPipeImage;
        this.bottomPipeImage = bottomPipeImage;
        this.pipes = new ArrayList<>();
    }

    public void placePipes() {

        int pipeGap = this.BOARD_HEIGHT / 4;

        Pipe topPipe = new Pipe(this.BOARD_WIDTH,getRandomPipePosition(512), 64, 512, this.topPipeImage);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(this.BOARD_WIDTH, 0, 64, 512, this.bottomPipeImage);
        bottomPipe.y = topPipe.y + 512 + pipeGap;
        pipes.add(bottomPipe);

    }

    private int getRandomPipePosition(int pipeHeight){
        return (int)(0 - pipeHeight / 4 - Math.random()*(pipeHeight / 2));
    }

    ArrayList<Pipe> getList(){
        return this.pipes;
    }
}
