import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel implements KeyListener, MouseListener {
    Bird my_bird = new Bird(100);
    ArrayList<Walls> all_walls = new ArrayList<>();
    
    int state = 0; 
    int timer = 0;
    int score = 0;
    int max_length = 600;
    Rectangle startButton = new Rectangle(125, 350, 150, 50);

    Image birdImg, pipeImg, bgImg;

    public Game() {
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);

        try {
            birdImg = ImageIO.read(new File("bird.png"));
            pipeImg = ImageIO.read(new File("pipe.png"));
            bgImg = ImageIO.read(new File("background.jpg"));
        } catch (IOException e) {
            System.out.println("Resources not found.");
        }
    }

    public void start() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.add(this);
        frame.setSize(400, max_length);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        while (true) {
            if (state == 1) {
                my_bird.move();
                timer++;

                if (timer >= 80) {
                    all_walls.add(new Walls(400));
                    timer = 0;
                }

                for (int i = 0; i < all_walls.size(); i++) {
                    Walls w = all_walls.get(i);
                    int oldX = w.wallX;
                    w.move();

                    if (oldX >= 100 && w.wallX < 100) {
                        score++;
                    }

                    if (w.wallX < -60) {
                        all_walls.remove(i);
                        i--;
                    }
                }

                if (checkCollision() || my_bird.birdY >= 500 || my_bird.birdY <= 0) {
                    state = 2;
                }
            }
            repaint();
            try { Thread.sleep(20); } catch (Exception e) {}
        }
    }

    public boolean checkCollision() {
        Rectangle birdBox = new Rectangle(100, (int) my_bird.birdY, 30, 30);
        for (Walls w : all_walls) {
            Rectangle topPipe = new Rectangle(w.wallX, 0, w.width, w.upperHeight);
            Rectangle bottomPipe = new Rectangle(w.wallX, w.upperHeight + w.gap, w.width, 600);
            if (birdBox.intersects(topPipe) || birdBox.intersects(bottomPipe)) return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (bgImg != null) {
            g2.drawImage(bgImg, 0, 0, 400, 600, null);
        } else {
            g2.setColor(new Color(135, 206, 235));
            g2.fillRect(0, 0, 400, 600);
        }

        for (Walls w : all_walls) {
            GradientPaint pipeGradient = new GradientPaint(w.wallX, 0, new Color(34, 139, 34), w.wallX + w.width, 0, new Color(144, 238, 144));
            g2.setPaint(pipeGradient);
            g2.fillRect(w.wallX, 0, w.width, w.upperHeight);
            g2.fillRect(w.wallX, w.upperHeight + w.gap, w.width, 500);

            g2.setColor(new Color(0, 100, 0));
            g2.fillRect(w.wallX - 5, w.upperHeight - 25, w.width + 10, 25);
            g2.fillRect(w.wallX - 5, w.upperHeight + w.gap, w.width + 10, 25);
            
            g2.setColor(Color.BLACK);
            g2.drawRect(w.wallX, 0, w.width, w.upperHeight);
            g2.drawRect(w.wallX - 5, w.upperHeight - 25, w.width + 10, 25);
            g2.drawRect(w.wallX, w.upperHeight + w.gap, w.width, 500 - (w.upperHeight + w.gap));
            g2.drawRect(w.wallX - 5, w.upperHeight + w.gap, w.width + 10, 25);
        }

        g2.setColor(new Color(222, 184, 135));
        g2.fillRect(0, 500, 400, 100);
        g2.setColor(new Color(34, 139, 34));
        g2.fillRect(0, 500, 400, 15);

        if (state >= 1) {
            if (birdImg != null) {
                g2.drawImage(birdImg, 100, (int) my_bird.birdY, 35, 30, null);
            } else {
                g2.setColor(Color.YELLOW);
                g2.fillOval(100, (int) my_bird.birdY, 30, 30);
            }
            
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.drawString("" + score, 190, 50);
        }

        if (state == 0) {
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, 400, 600);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            g2.drawString("FLAPPY BIRD", 70, 150);
            g2.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
            g2.setColor(Color.BLACK);
            g2.drawRect(startButton.x, startButton.y, startButton.width, startButton.height);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("START GAME", 135, 382);
        }

        if (state == 2) {
            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRect(0, 0, 400, 600);
            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.drawString("GAME OVER", 55, 250);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.drawString("Score: " + score, 155, 300);
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Click to Restart", 125, 340);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (state == 0 && startButton.contains(e.getPoint())) {
            state = 1;
            score = 0;
            requestFocusInWindow();
        } else if (state == 2) {
            my_bird = new Bird(100);
            all_walls.clear();
            score = 0;
            state = 0;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && state == 1) {
            my_bird.flop();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new Game().start();
    }
}