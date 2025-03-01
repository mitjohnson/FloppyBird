package floppybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    int velocityY = 0;

    Timer gameLoop;
    
    FloppyBird(){
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        // load images
        backgroundImage = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();

        // load bird
        int birdX = BOARD_WIDTH / 8;
        int birdY = BOARD_HEIGHT / 2;
        bird = new Bird(birdX, birdY, 34, 24, birdImage);

        // init timer
        gameLoop = new Timer(1000/60, this);
        gameLoop.start(); 
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
        g.drawImage(backgroundImage, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null );
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
    }

    public void update(){
        bird.update();
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
