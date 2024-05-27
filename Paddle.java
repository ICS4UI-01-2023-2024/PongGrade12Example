public class Paddle extends GameObject {
    
    private int direction;

    public Paddle(int x, int y, int width, int height, int speed){
        super(x, y, width, height, speed);
        this.direction = 0;
    }

    public void move(){
        int newY = super.getY() + this.direction*super.getSpeed();
        super.setY(newY);
    }

    public void moveUp(){
        this.direction = -1;
    }

    public void moveDown(){
        this.direction = +1;
    }

    public void stop(){
        this.direction = 0;
    }
}
