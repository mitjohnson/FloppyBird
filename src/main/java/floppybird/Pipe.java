package floppybird;

import java.awt.Image;

public class Pipe {
    int x;
    int y;
    int width;
    int height;
    Image img;

    private boolean passed = false;
    int velocity = 2;

    Pipe(int xCord, int yCord, int width, int height, Image img){
        this.x = xCord;
        this.y = yCord;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void update() {
        this.x -= this.velocity;
    }
}
