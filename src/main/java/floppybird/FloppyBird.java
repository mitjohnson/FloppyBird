package floppybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FloppyBird extends JPanel implements KeyListener {

    private final Image birdImage, backgroundImage;
    private final int BOARD_HEIGHT, BOARD_WIDTH;
    private final PipeManager pipeManager;
    private final GameController gameController;

    Bird bird;
    public int score = 0;
    
    FloppyBird(int WINDOW_WIDTH, int WINDOW_HEIGHT, PipeManager pipeManager, GameController gameController){

        this.BOARD_WIDTH = WINDOW_WIDTH;
        this.BOARD_HEIGHT = WINDOW_HEIGHT;

        setPreferredSize(new Dimension(this.BOARD_WIDTH, this.BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(FloppyBird.this);

        // load images
        this.backgroundImage = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png")).getImage();
        this.birdImage = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();

        // load bird
        int birdX = this.BOARD_WIDTH / 8;
        int birdY = this.BOARD_HEIGHT / 2;
        this.bird = new Bird(birdX, birdY, 34, 24, this.birdImage);

        // load pipe manager and game controller
        this.pipeManager = pipeManager;
        this.gameController = gameController;
    }

    public void draw(Graphics g){
        g.drawImage(this.backgroundImage, 0, 0, this.BOARD_WIDTH, this.BOARD_HEIGHT, null );

        bird.draw(g);

        for (Pipe pipe : this.pipeManager.getList()) {
            pipe.draw(g);
        }

        //Score
        if (this.score != 0){
            g.setFont(new Font("Arial Black", Font.PLAIN, 40));
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(this.score), this.BOARD_WIDTH / 2 - 20, 40);
        }
        
    }

    public void resetBirdPosition(){
        this.bird.x = this.BOARD_WIDTH / 8;
        this.bird.y = this.BOARD_HEIGHT / 2;
    }
    public void update(){
        this.bird.update();
        for(Pipe pipe : this.pipeManager.getList()){
            if(checkCollision(this.bird, pipe)){
                this.gameController.showGameOverMenu();
            }
        }
    }

    Bird getBird(){
        return this.bird;
    }

    boolean checkCollision(Bird bird, Pipe pipe){

        // get bird position and size.
        int birdX = bird.x;
        int birdY = bird.y;
        int birdHeight = bird.height;
        int birdWidth = bird.width;

        // get pipe postion and size.
        int pipeX = pipe.x;
        int pipeY = pipe.y;
        int pipeHeight = pipe.height;
        int pipeWidth = pipe.width;

        if (birdX + birdWidth > pipeX && birdX < pipeX + pipeWidth) {
            if (birdY < pipeY + pipeHeight && birdY + birdHeight > pipeY) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE -> bird.flap();
            case KeyEvent.VK_P -> this.gameController.togglePause();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
