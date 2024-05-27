import java.awt.Graphics;

public abstract class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;


    public GameObject(int x, int y, int width, int height, int speed){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLeft(){
        return this.x;
    }

    public int getRight(){
        return this.x + this.width;
    }

    public int getTop(){
        return this.y;
    }

    public int getBottom(){
        return this.y + this.height;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void move();

    public void draw(Graphics g){
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    public boolean collision(GameObject object){
        // are the x values between each other
        if(this.x  < object.x + object.width && this.x + this.width > object.x){
            // are the y values between each other
            if(this.y < object.y + object.height && this.y + this.height > object.y){
                return true;
            }
        }
        // no collision
        return false;
    }
}
