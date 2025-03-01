package floppybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FloppyBird extends JPanel implements ActionListener, KeyListener {

    int BOARD_HEIGHT = 640;
    int BOARD_WIDTH = 360;
    int SCORE = 0;
    boolean GAME_OVER = false;

    Image birdImage;
    Image backgroundImage;
    Image topPipeImage;
    Image bottomPipeImage;

    Bird bird;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer placePipesTimer;
    int pipeSpeed;
    
    FloppyBird(){
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        // load images
        this.backgroundImage = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png")).getImage();
        this.birdImage = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();
        this.topPipeImage = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
        this.bottomPipeImage = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();

        // load bird
        int birdX = this.BOARD_WIDTH / 8;
        int birdY = this.BOARD_HEIGHT / 2;
        this.bird = new Bird(birdX, birdY, 34, 24, this.birdImage);
        this.pipes = new ArrayList<>();

        // pipes timer
        this.pipeSpeed = 3000;
        this.placePipesTimer = new Timer(this.pipeSpeed, (ActionEvent e) -> {
            placePipes();
        });
        this.placePipesTimer.start();

        // init timer
        this.gameLoop = new Timer(1000/60, this);
        this.gameLoop.start(); 
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

    public void draw(Graphics g){
        g.drawImage(this.backgroundImage, 0, 0, this.BOARD_WIDTH, this.BOARD_HEIGHT, null );
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
    }

    public void update(){
        this.bird.update();

        for (Pipe pipe : this.pipes) {
            pipe.update(this.bird);

            if(checkCollision(this.bird, pipe)){
                this.GAME_OVER = true;
            }

            if(pipe.hasPassed()){
                this.SCORE++;
            }
        }
    }

    private boolean checkCollision(Bird bird, Pipe pipe){

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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.flap();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
        if (this.GAME_OVER){
            this.placePipesTimer.stop();
            this.gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
