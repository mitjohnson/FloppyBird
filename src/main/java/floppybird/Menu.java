package floppybird;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

enum MenuState {
    START,
    GAME_RUNNING,
    GAME_PAUSED,
    GAME_OVER
}

public class Menu {

    private final App app;
    private final GameController gameController;
    private final JPanel overlayPanel;

    MenuState currentState;
    public JButton startButton;
    public JButton pauseButton;
    public JButton resumeButton;
    public JButton settingsButton;


    int WINDOW_HEIGHT, WINDOW_WIDTH;
    Menu(int WINDOW_WIDTH, int WINDOW_HEIGHT, App app, GameController gameController, JLayeredPane layeredPane){

        this.currentState = MenuState.START;

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


    private MenuState getCurrentState(){
        return this.currentState;
    }

    public void update(){
        switch (getCurrentState()){
            case START -> startMenu("Play");
            case GAME_RUNNING -> playingMenu();
            case GAME_PAUSED -> pauseMenu();
            case GAME_OVER -> startMenu("Play Again?");
        }
    }

    private void pauseMenu(){
        this.overlayPanel.removeAll();
        showResumeButton();
        this.resumeButton.requestFocusInWindow();
        showSettingsButton("Settings");
    }

    private void startMenu(String startText){
        this.overlayPanel.removeAll();
        ShowStartButton(startText);
        this.startButton.requestFocusInWindow();
        showSettingsButton("Settings");
    }

    private void playingMenu(){
        this.overlayPanel.removeAll();
        showPauseButton();
    }


    private void ShowStartButton(String ButtonText){
        this.startButton = new JButton(ButtonText);
        this.startButton.addActionListener((ActionEvent e) -> {
            this.overlayPanel.remove(this.startButton);
            if(this.currentState == MenuState.GAME_OVER){
                this.gameController.restartGame();
            } else {
                this.gameController.startGame();
            }
            showPauseButton();
            this.currentState = MenuState.GAME_RUNNING;
        });
        this.startButton.setBounds(this.WINDOW_WIDTH / 2 - 50, this.WINDOW_HEIGHT / 2 - 25, 100, 50);        
        this.overlayPanel.add(this.startButton);
        this.app.frame.revalidate();
        this.app.frame.repaint();
    }

    private void showPauseButton(){
        this.pauseButton = new JButton("Pause");
        this.pauseButton.setBounds(this.WINDOW_WIDTH - 120, 2, 100, 40);
        this.pauseButton.addActionListener((ActionEvent e) -> {
            this.overlayPanel.remove(this.pauseButton);
            this.gameController.togglePause();
        });
        this.overlayPanel.add(this.pauseButton);
    }

    private void showResumeButton(){
        this.resumeButton = new JButton("Resume");
        this.resumeButton.setBounds(this.WINDOW_WIDTH / 2 - 50, this.WINDOW_HEIGHT / 2 - 25, 100, 50);        
        this.resumeButton.addActionListener((ActionEvent e) -> {
            this.overlayPanel.remove(this.resumeButton);
            this.gameController.togglePause();
        });
        this.overlayPanel.add(this.resumeButton);
    }
    

    private void showSettingsButton(String ButtonText){
        this.settingsButton = new JButton(ButtonText);
        this.settingsButton.addActionListener((ActionEvent e) -> {});
        this.overlayPanel.add(this.settingsButton);
    }
}
