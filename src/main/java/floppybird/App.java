package floppybird;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {

        // initialize widow
        int WINDOW_HEIGHT = 360;
        int WINDOW_WIDTH = 640;

        JFrame frame = new JFrame("Floppy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // paint canvas
        FloppyBird floppyBird = new FloppyBird();
        frame.add(floppyBird);
        frame.pack();
        floppyBird.requestFocus();
        frame.setVisible(true);
    }
}
