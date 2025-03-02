package floppybird;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class App extends JPanel{
    
    private final Menu menu;
    private GameController gameController;

    public JFrame frame;

    public App(int windowWidth, int windowHeight){

        // initialize window and panel
        this.frame = new JFrame("Floppy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(this.gameController.getFloppyBirdPanel(), JLayeredPane.DEFAULT_LAYER);
        layeredPane.setPreferredSize(new Dimension(windowWidth, windowHeight));

        // load Game controller
        this.gameController = new GameController(windowWidth, windowHeight);
        this.gameController.getFloppyBirdPanel().setBounds(0, 0, windowWidth, windowHeight);
        this.gameController.gameRunning = false;

        this.menu = new Menu(windowWidth, windowHeight, this, this.gameController, layeredPane);
        this.menu.ShowStartButton("Start Game");

        frame.add(layeredPane);
        frame.setVisible(true);
    }
    public static void main(String[] args) {

        int WINDOW_HEIGHT = 640;
        int WINDOW_WIDTH = 360;
        
        @SuppressWarnings("unused")
        App app = new App(WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}
