package floppybird;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Menu {

    private final App app;
    private final GameController gameController;
    private final JPanel overlayPanel;

    public JButton startButton;


    int WINDOW_HEIGHT, WINDOW_WIDTH;
    Menu(int WINDOW_WIDTH, int WINDOW_HEIGHT, App app, GameController gameController, JLayeredPane layeredPane){

        this.WINDOW_WIDTH = WINDOW_WIDTH;
        this.WINDOW_HEIGHT = WINDOW_HEIGHT;
        this.gameController = gameController;
        this.app = app;

        this.overlayPanel = new JPanel();
        this.overlayPanel.setOpaque(false);
        this.overlayPanel.setLayout(null);
        this.overlayPanel.setBounds(0, 0, this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
        layeredPane.add(this.overlayPanel, JLayeredPane.PALETTE_LAYER);

    }

    public void ShowStartButton(String ButtonText){
        this.startButton = new JButton(ButtonText);
        this.startButton.addActionListener((ActionEvent e) -> {
            this.overlayPanel.remove(this.startButton);
            this.gameController.startGame();
        });
        this.startButton.setBounds(this.WINDOW_WIDTH / 2 - 50, this.WINDOW_HEIGHT / 2 - 25, 100, 50);        
        this.overlayPanel.add(this.startButton);
        this.app.frame.revalidate();
        this.app.frame.repaint();
    }
}
