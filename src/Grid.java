import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class Grid extends JPanel {
    private Square[][] squares = new Square[16][16]; //16x16 grid of (50x50 squares)
    private final int side = 50;
    private boolean player = true;
    private int score1;
    private int score2;

    public Grid() {
        double rand = 0.0;
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[0].length; c++) {
                rand = (Math.random()*10) +1;
                if (rand >= 9) {
                squares[r][c] = new Square(r, c, true);
            }
                else{ squares[r][c] = new Square(r, c, false); }
            }


            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateGame();
                    repaint();
                }
            });
            timer.start();
            this.setFocusable(true);


            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int xClick = e.getX() / 50;
                    int yClick = e.getY() / 50;
                    System.out.println("clicked");
                    if (squares[xClick][yClick].getClicked()) { player = !player; }
                    if (squares[xClick][yClick].isBomb()) {
                        if (!player && !squares[xClick][yClick].getClicked()) {score2++;}
                        if (player && !squares[xClick][yClick].getClicked()) {score1++;}
                    }
                    squares[xClick][yClick].setClicked();
                    squares[xClick][yClick].reveal();
                    repaint();
                }
            });
        }

    }

    public void drawBackground(Graphics2D g) {
        g.setColor(new Color(253, 253, 160, 255));
        for (int r = 0; r<16;r+=1) {
            for (int c = 0; c<16; c+=1) {
                squares[r][c].drawSquare(g);
            }
        }
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        drawBackground(g2d);
        g.setColor(new Color(253, 253, 160, 255));
        String str = "PLAYER 1:  " + score1;
        showMessage(str, 50, g2d);
        String str2 = "PLAYER 2:  " + score2;
        showMessage(str2, 70, g2d);

        if (score1 == 3 || score2 == 3) { gameOver(g2d); }
    }

    public void showMessage(String s, int locateY, Graphics2D g2d) {
        Font font = new Font("SansSerif", Font.PLAIN, 20);
        Rectangle2D textBox = font.getStringBounds(s, g2d.getFontRenderContext());
        Rectangle2D textBox1 = font.getStringBounds(s, g2d.getFontRenderContext());
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString(s, 810, locateY);
    }

    public void gameOver(Graphics2D g2d) {
        Font font = new Font("SansSerif", Font.PLAIN, 50);
        if (score1 == 3) {
            Rectangle2D textBox1 = font.getStringBounds("Player 2 won!", g2d.getFontRenderContext());
            g2d.drawString("Player 2 won!", 400, 300); }
        if (score2 == 3) {
            Rectangle2D textBox1 = font.getStringBounds("Player 1 won!", g2d.getFontRenderContext());
            g2d.drawString("Player 1 won!", 400, 300);}

        font = new Font("SansSerif", Font.PLAIN, 80);
        Rectangle2D textBox = font.getStringBounds("GAME OVER", g2d.getFontRenderContext());
        g2d.setFont(font);
        g2d.drawString("GAME OVER", 300, 400);
    }

    // game logic -- collisions, bounces, etc.
    private void updateGame() {
       // System.out.println("Update game elements now...");

    }
}
