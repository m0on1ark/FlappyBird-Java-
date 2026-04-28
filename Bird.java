
public class Bird {
    int birdY = 0;
    final int birdX = 100;

    double gravitation = 0.5; 
    double velocityY = 1;
    public Bird(int birdY){
        this.birdY = birdY;
       
    }
    public void move(){
        velocityY += gravitation;
        this.birdY += velocityY;

    }
    public void flop(){
        velocityY = -10;
    }

}