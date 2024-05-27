public class Ball extends GameObject {
    private int xDirection;
    private int yDirection;

    public Ball(int x, int y, int width, int height, int speed){
        super(x, y, width, height, speed);
        // pick random starting x direction
        int randDirection = (int)(Math.random()*2);
        if(randDirection == 0){
            this.xDirection = 1;
        }else{
            this.xDirection = -1;
        }

        // pick random starting y direction
        randDirection = (int)(Math.random()*2);
        if(randDirection == 0){
            this.yDirection = 1;
        }else{
            this.yDirection = -1;
        }
        
    }

    public void move(){
        // calculating new position
        int newX = super.getX() + this.xDirection*super.getSpeed();
        int newY = super.getY() + this.yDirection*super.getSpeed();
        // updating position
        super.setX(newX);
        super.setY(newY);
    }

    public void bounceUp(){
        this.yDirection = -1;
    }

    public void bounceDown(){
        this.yDirection = 1;
    }

    public void bounceLeft(){
        this.xDirection = -1;
    }

    public void bounceRight(){
        this.xDirection = 1;
    }

    public void reset(int x, int y){
        super.setX(x);
        super.setY(y);
    }
}
