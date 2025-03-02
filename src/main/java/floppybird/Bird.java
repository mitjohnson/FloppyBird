package floppybird;
import java.awt.Graphics;
import java.awt.Image;

 class Bird {

    private final Image img;
    private final int gravity = 1;
    private int velocity = 0;

    public int x, y, width, height;

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

    public void draw(Graphics g){
        g.drawImage(this.img, this.x, this.y, this.width, this.height, null);
    }

    public void flap() {
        this.velocity = -10;
    }
}
