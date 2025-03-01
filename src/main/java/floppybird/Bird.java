package floppybird;
import java.awt.Image;

 class Bird {

    int x;
    int y;
    int width;
    int height;
    Image img;

    private int velocity = 0;
    private int gravity = 1;

    Bird(int xCord, int yCord, int width, int height, Image img) {
        this.x = xCord;
        this.y = yCord;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public void update() {

        this.velocity += this.gravity;
        this.y = Math.max(this.y + this.velocity, 0);

        if (this.y > 640 - this.height) {
            this.y = 640 - this.height;
            this.velocity = 0;
        }
    }

    public void flap() {
        this.velocity = -10;
    }
}
