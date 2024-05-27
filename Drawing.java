import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Drawing extends JPanel{
  // Timer for the game loop
  Timer gameTimer;
  
  Keyboard keys = new Keyboard();
  Mouse mouse = new Mouse();

  
  // How fast do you want your game to run? Frames Per Second  
  final int FPS = 60;
  // What suze of screen do you want?
  final int WIDTH = 800;
  final int HEIGHT = 600;

  // Other variables for your project can go on the next lines
  private Paddle player1;
  private Paddle player2;
  private Ball ball;
  private int player1Score;
  private int player2Score;
  

  // Initialize things BEFORE the game starts
  public void setup(){
    // are there any variables that need initialized BEFORE the game starts?
    // Do that here!
    int paddleHeight = 60;
    int paddleWidth = 20;
    int paddleSpeed = 2;
    this.player1 = new Paddle(10, HEIGHT/2 - paddleHeight/2 , paddleWidth, paddleHeight, paddleSpeed);
    this.player2 = new Paddle(WIDTH - 10 - paddleWidth, HEIGHT/2 - paddleHeight/2, paddleWidth, paddleHeight, paddleSpeed);
    
    this.ball = new Ball(WIDTH/2 - 10, HEIGHT/2 - 10, 20, 20, 2);

    this.player1Score = 0;
    this.player2Score = 0;
  }

    // Paint the game components here
    @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // clear the screen
    g.clearRect(0, 0, WIDTH, HEIGHT);

    // You can add more drawing here
    // black background
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    g.setColor(Color.WHITE);
    this.player1.draw(g);
    this.player2.draw(g);
    this.ball.draw(g);

    // draw score
    g.drawString("" + player1Score, WIDTH/2 - 200, 20);
    g.drawString("" + player2Score, WIDTH/2 + 200, 20);
    
  }

  // Update game logic here
  public void loop() {
    // This method is called by the game loop
    // This is where your game logic goes
    this.ball.move();
    this.player1.move();
    this.player2.move();
    
    // ball collision
    if(ball.getBottom() > HEIGHT){
        this.ball.bounceUp();
    }else if(this.ball.getTop() < 0){
        this.ball.bounceDown();
    }else if(this.ball.getLeft() < 0){
        this.player2Score++;
        resetBall();
    }else if(this.ball.getRight() > WIDTH){
        this.player1Score++;
        resetBall();
    }

    // paddle1 movement
    if(keys.isPressed(KeyEvent.VK_W)){
        this.player1.moveUp();
    }else if(keys.isPressed(KeyEvent.VK_S)){
        this.player1.moveDown();
    }else{
        this.player1.stop();
    }

    // paddle2 movement
    if(keys.isPressed(KeyEvent.VK_UP)){
        this.player2.moveUp();
    }else if(keys.isPressed(KeyEvent.VK_DOWN)){
        this.player2.moveDown();
    }else{
        this.player2.stop();
    }

    // ball bounce off the paddles
    if(this.ball.collision(this.player1)){
        this.ball.bounceRight();
    }else if(this.ball.collision(this.player2)){
        this.ball.bounceLeft();
    }

    if(player1Score == 11 || player2Score == 11){
        gameTimer.stop();
    }
    
  }

  private void resetBall(){
    this.ball.reset(WIDTH/2 - 10, HEIGHT/2 - 10);
  }


  // YOU SHOULDN'T NEED TO MODIFY ANYTHING AFTER THIS POINT
  // Feel free to have a look to see what is happening but don't touch the code down here!
  // This is what makes the window and all of the keyboard and mouse stuff work

  // creates the game window and sets everything up to run properly
  public Drawing() {
    // Initialize the game window
    JFrame frame = new JFrame("Simple 2D Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    frame.setVisible(true);
    frame.add(this);
    frame.pack();
   
    frame.addKeyListener(keys);
    this.addMouseListener(mouse);
    this.addMouseMotionListener(mouse);
    this.addMouseWheelListener(mouse);
    // call the setup method for parts that need initialized before the game starts
    setup();

    // Initialize game timer to run at a constant FPS
    gameTimer = new Timer(1000/FPS, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            loop();
            repaint();
        }
    });
    gameTimer.start();
  }

  


  public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              new Drawing();
          }
      });
  }
}