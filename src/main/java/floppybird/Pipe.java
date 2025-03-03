package floppybird;

import java.awt.Graphics;
import java.awt.Image;

class Pipe {

    private final Image img;

    public int x, y, width, height, velocity;
    public boolean passed, scored;

    Pipe(int xCord, int yCord, int width, int height, Image img){
        this.x = xCord;
        this.y = yCord;
        this.width = width;
        this.height = height;
        this.img = img;
        this.passed = false;
        this.scored = false;
        this.velocity = 2;
    }

    public void update(Bird bird) {
        this.x -= this.velocity;

        if (!this.passed && this.x + this.width < bird.x) {
            this.passed = true;
        }
    }

    public void draw(Graphics g){
        g.drawImage(this.img, this.x, this.y, this.width, this.height, null);
    }

    public void increaseVelocity(int velocity){
        this.velocity = velocity;
    }

    public boolean hasPassed(){
        return this.passed;
    }
}
