import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class Square extends Rectangle2D.Double {
    private Color color;
    private final int side = 50;
    private Image img;
    private boolean revealed = false;
    private boolean bomb = false;
    private Graphics2D g;
    private boolean clicked;

    public Square(int xPos,int yPos, boolean b) {
        super(xPos*50, yPos*50, 50, 50);
        color = new Color(0,255,0,128);
        bomb = b;

        try {
            img = ImageIO.read(new File("res/bomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSquare(Graphics2D g2) {
        g = g2;

        if (revealed == false) {
            g2.setColor(color);
            g2.fill(this);
            g2.setPaint(Color.BLACK);
            g2.draw(this);
        } else {
            if (bomb == true) {
                if (img != null) {
                    g2.drawImage(img, (int) this.getX(), (int) this.getY(), side, side, null);
                }
            } else {
                g2.setPaint(Color.LIGHT_GRAY);
                g2.fill(this);
                g2.setPaint(Color.BLACK);
                g2.draw(this);
            }
        }
    }

    public void reveal() {
        revealed = true;
        System.out.println("revealed");
    }

    public void setColor(Color c) {
        color = c;
    }
    public double getXPos() { return super.x; }
    public double getYPos() { return super.y; }
    public boolean isRevealed() { return revealed; }
    public boolean isBomb() { return bomb; }
    public boolean getClicked() { return clicked; }
    public void setClicked() { clicked = true; }
}
