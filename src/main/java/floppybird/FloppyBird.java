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

    Image birdImage;
    Image backgroundImage;
    Image topPipeImage;
    Image bottomPipeImage;

    Bird bird;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer placePipesTimer;
    
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
        this.bird = new Bird(birdX, birdY, 34, 24, birdImage);
        this.pipes = new ArrayList<>();

        // pipes timer
        this.placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                placePipes();
            }
        });
        placePipesTimer.start();

        // init timer
        this.gameLoop = new Timer(1000/60, this);
        this.gameLoop.start(); 
    }

    public void placePipes() {
        Pipe pipe = new Pipe(this.BOARD_WIDTH, 0, 64, 512, this.topPipeImage);
        pipe.y = (int)(0 - pipe.height / 4 - Math.random()*(pipe.height/2));
        pipes.add(pipe);
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
        bird.update();

        for (Pipe pipe : this.pipes) {
            pipe.update();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.flap();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }
}
