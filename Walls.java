public class Walls {
    int wallX;
    int width = 60;
    int gap = 100; 
    int upperHeight;
    public Walls(int wallX){
        this.wallX = wallX;
        this.upperHeight = (int) (Math.random() * 250) + 50;
    }
    
    public void move(){
        wallX -= 5; 
    }
}
