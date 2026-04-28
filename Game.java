import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements KeyListener {
    Bird my_bird = new Bird(100);
    boolean running = true;
    int max_length = 600;
    public Game() {
        setFocusable(true);
        addKeyListener(this);
    }

    public void start() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.add(this);
        frame.setSize(400, max_length);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        while (my_bird.birdY < max_length) {
            my_bird.move();
            repaint();
            
            try {
                Thread.sleep(16); 
            } catch (Exception e) {}
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
       
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());

        
        g.setColor(Color.YELLOW);
        g.fillRect(100, (int)my_bird.birdY, 30, 30);

        
        g.setColor(Color.ORANGE);
        g.fillRect(0, 500, 400, 100);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            my_bird.flop();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}