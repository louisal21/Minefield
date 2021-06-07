import javax.swing.*;
import java.awt.*;

public class Minefield extends JFrame{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 850;

    public Minefield() {
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Grid());

        //center the window on the viewing screen
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) screenDim.getWidth()/2 - WIDTH/2, (int) screenDim.getHeight()/2 - HEIGHT/2);

        //create a panel component and add it to the window
        this.add(new Grid());

        //user input
        String player1 = JOptionPane.showInputDialog("Enter to play! ");

    }

    public static void main(String[] args) {
        Minefield game = new Minefield();
        game.setVisible(true);
        System.out.println("Game has begun. Close window to exit.");
    }
}
