package floppybird;

import java.awt.Image;

public class Pipe {
    int x;
    int y;
    int width;
    int height;
    Image img;

    boolean passed = false;
    boolean scored = false;
    int velocity = 2;

    Pipe(int xCord, int yCord, int width, int height, Image img){
        this.x = xCord;
        this.y = yCord;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void update(Bird bird) {
        this.x -= this.velocity;

        if (!this.passed && this.x + this.width < bird.x) {
            this.passed = true;
        }
    }

    public boolean hasPassed(){
        return this.passed;
    }
}
