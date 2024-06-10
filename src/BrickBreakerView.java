

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
//start class
public class BrickBreakerView extends JPanel implements KeyListener, MouseListener {
	//init instance variables
	    private Paddle paddle;
	    private Ball ball;
	    private int ballX = 250;
	    private int ballY = 500;
	    private Background b;
	    private Start s;
	    private End e;
	    private Brick[][] brickArray = new Brick[11][4];
	    private int screen=0;
	    private int width = 800;
	    private int hieght = 600;
	    private Coin c;
	    private int currency = 0;
	    private Shop shop;
	    private PowerUp1 one;
	    private PowerUp1 two;
	    private PowerUp1 three;
	   private  double xS;
    	private double yS;
    	private Rectangle p1;
    	private Rectangle p2;
    	private Rectangle p3;
    	private int wins;
    	private int count;
    	private int matchCount = 0;
    	private HighScoreManager highScoreManager;
    	//int sounds
    	Music bounce = new Music("ballSound.wav", false);
    	Music backgroundMusic = new Music("og-minecraft-music-made-with-Voicemod.wav", true);
    	// constructor 
	    public BrickBreakerView() {
	        paddle = new Paddle(300, 530, 150, 20);
	        ball = new Ball(ballX, ballY, 20, 2);
	        ball.setYSpeed(-1);	 
	        //fill in 2darray of all bricks
	        for(int i=0; i<brickArray.length; i++) {
	            for(int j=0; j<brickArray[i].length; j++) {
	            if(Math.random()<.25) {
	            	brickArray[i][j] = new PowerUp("",i*70+10,j*70+10);
		        	
	            }else {
	            	
	        	brickArray[i][j] = new Brick("",i*70+10,j*70+10);
	        	matchCount++;
	            }
	             }
	        }
	        
	        wins =0;
	        
	        b =  new Background("caveBackground.png");
	        s = new Start("startScreen.gif");
	        e = new End("endScreen.gif");
	        c = new Coin(10,460);
	        shop = new Shop("shopGUIFinal.png");
	        one = new PowerUp1("sizePowerUp.png", 250, 250);
	        two = new PowerUp1("speedPowerUp.png", 350, 250);
	        three = new PowerUp1("timePowerUp.png", 450, 250);
	        p1 = new Rectangle(250, 250, 64, 64); 
	        p2 = new Rectangle(350, 250, 64, 64);
	        p3 = new Rectangle(450, 250, 64, 64);
	        
	        highScoreManager = new HighScoreManager();
	        addMouseListener(this);
	        addKeyListener(this);
	        setFocusable(true);
	    }
	    
	    //start method to set JFrame
	    public void start() {
	        JFrame frame = new JFrame("Brick Breaker");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(width, hieght);
	        frame.getContentPane().add(this);
	        frame.setResizable(false);
	        frame.setVisible(true);
	       
	          
	        // Game loop
	        while (true) {
	            update();
	            repaint();
	            try {
	                Thread.sleep(10);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    //collisions method to manage all ball paddle brick collisions
	    public void collisions() {
	    	Rectangle[] r = new Rectangle[paddle.getWidth()];
	    	
	    	//creates a diff xSpeed depending on where ball hits paddle
	    	if(paddle.getLoc().intersects(ball.getLoc())) {
	    		ball.reflectY();
	    		bounce.play();
	    		//if ball intersects on left side, ball goes back to the left and if ball hits on the right side
	    		//ball goes to the rights
	    		for(int i=0;i<r.length;i++) {
		    		r[i] = new Rectangle(paddle.getX()+i,paddle.getY(),2,2);
		    		if(ball.getLoc().intersects(r[i])) {
		    			if(i<=paddle.getWidth()/2) {
		    				ball.setXSpeed((i-paddle.getWidth()/2)/5);
		    				if(ball.getXSpeed() == 0) {
		    					ball.setXSpeed(1);
		    				}
		    			}
		    			if(i>paddle.getWidth()/2) {
		    				ball.setXSpeed((i-paddle.getWidth()/2)/10);
		    				if(ball.getXSpeed() == 0) {
		    					ball.setXSpeed(1);
		    				}
		    			}
		    		}
		    			
		    		
		            
		    	}
	    		
	    	}
	    	//this for loop handles if ball collides with the bricks 
	    	for(int i = 0 ; i<brickArray.length; i++) {
	    		for(int j=0; j<brickArray[i].length; j++) {
	    			if(brickArray[i][j].isPowerUp() && (((ball.getLoc().intersects(brickArray[i][j].getBottomRect()))|| ball.getLoc().intersects(brickArray[i][j].getRightRect()))||ball.getLoc().intersects(brickArray[i][j].getLeftRect())||ball.getLoc().intersects(brickArray[i][j].getTopRect()))) {
	    				
	    				bounce.play();
	    				System.out.println("detected of power");
	    				handlePowerUpCollision((PowerUp)brickArray[i][j]);
	    				count--;
	    			
	    				
	    			}
	    			//handles if it hits 2 bricks at once
	    			if(i>0 && ball.getLoc().intersects(brickArray[i][j].getBottomRect())&& ball.getLoc().intersects(brickArray[i-1][j].getBottomRect())){
	    				if(brickArray[i][j].getType().equals("diamond.png")) {
	    					currency++;
	    				}
	    				ball.reflectY();
	    				bounce.play();
	    				brickArray[i][j] = new Brick("",-100,-100);
	    				if(brickArray[i][j].isPowerUp() == false) {
	    					count++;
	    				}
	    				
	    				
	    				
	    			}//handles if ball hits sides of bricks
	    			if(ball.getLoc().intersects(brickArray[i][j].getRightRect())|| ball.getLoc().intersects(brickArray[i][j].getLeftRect())) {
	    				if(brickArray[i][j].getType().equals("diamond.png")) {
	    					currency++;
	    				}
	    				ball.reflectX();
	    				bounce.play();
	    				brickArray[i][j] = new Brick("",-100,-100);
	    				if(brickArray[i][j].isPowerUp() == false) {
	    					count++;
	    				}
	    				
	    			}//handles if ball hits the top or bottom of the brick
	    			if(ball.getLoc().intersects(brickArray[i][j].getTopRect())|| ball.getLoc().intersects(brickArray[i][j].getBottomRect())){
	    				if(brickArray[i][j].getType().equals("diamond.png")) {
	    					currency++;
	    				}
	    				ball.reflectY();
	    				bounce.play();
	    				brickArray[i][j] = new Brick("",-100,-100);
	    				if(brickArray[i][j].isPowerUp() == false) {
	    					count++;
	    				}
	    			}
	    		}
	    	}
	    	 // Update high score if necessary
            if (wins > highScoreManager.getHighScore()) {
                highScoreManager.updateHighScore(wins);
            }
	    	System.out.println("Count: " + count);
	        System.out.println("Match Count: " + matchCount);
	    	if (count == matchCount) {
	    		
	    	    count = 0;
	    	    matchCount = 0; // Reset matchCount
	    	    wins++;
	    	    // Reset other game elements
	    	    ball.setY(ballY);
	    	    ball.setX(ballX);
	    	    ball.setXSpeed(2);
	    	    ball.setYSpeed(-2);
	    	    ball.move();
	    	    currency = 0;
	    	    paddle.setWidth(150);
	    	    paddle.setX(300);
	    	    paddle.setY(530);

	    	    // Reset brickArray
	    	    for (int i = 0; i < brickArray.length; i++) {
	    	        for (int j = 0; j < brickArray[i].length; j++) {
	    	            if (Math.random() < 0.25) {
	    	                brickArray[i][j] = new PowerUp("", i * 70 + 10, j * 70 + 10);
	    	            } else {
	    	                brickArray[i][j] = new Brick("", i * 70 + 10, j * 70 + 10);
	    	                matchCount++; // Increment matchCount for each non-power-up brick
	    	            }
	    	        }
	    	    }
	    	}
	    	
	    	
 
	    	 
	    }
	    private void handlePowerUpCollision(PowerUp powerUp) {
	        String type = powerUp.getType();
	     
	        // Apply different effects based on power-up type
	       if(type.equals("sizePowerUp.png")) {
	            
	                paddle.setWidth(paddle.getWidth() + 50); 
	                System.out.println(type);// Increase paddle width
	                
	              
	        }
	       if(type.equals("sizeDebuff.png")) {
	            if(paddle.getWidth()-50 >=0) {
               paddle.setWidth(paddle.getWidth() - 50); 
               System.out.println(type);// Increase paddle width
               System.out.println("Decreased paddle width: " + paddle.getWidth());
	            } else {
	                System.out.println("Paddle width cannot be reduced further.");
	            }
             
       }
	       if(type.equals("speedPowerUp.png")) {
	            
               paddle.setSpeed(paddle.getSpeed() + 20); 
               System.out.println(type);// Increase paddle width//f
               
             
       }
	       if(type.equals("speedDebuff.png")) {
	            
               paddle.setSpeed(paddle.getSpeed() - 20); 
               if(paddle.getSpeed()<0) {
            	   paddle.setSpeed(paddle.getSpeed()*-1);
               }
               System.out.println(type);// Increase paddle width
               
             
       }
//	       if(type.equals("timePowerUp.png")) {
//	    	   if (ball.getXSpeed() > 1) {
//	    		   ball.setXSpeed(ball.getXSpeed() - 1); // Decrease ball speed
//	    	   }
//	            if (ball.getYSpeed() > 1) {
//	            	ball.setYSpeed(ball.getYSpeed() - 1);
//	            }
//	            
//	            if(ball.getXSpeed() ==0) {
//	            	ball.setXSpeed(1);
//	            }
//	            if(ball.getYSpeed()==0) {
//	            	   ball.setYSpeed(-1);
//	               }
//               System.out.println(type);// Increase paddle width
//               
//             
//       }
//	       if(type.equals("timeDebuff.png")) {
//	            
//	    	   ball.setXSpeed(ball.getXSpeed() +2);
//               ball.setYSpeed(ball.getYSpeed() +2);
//               if(ball.getXSpeed()==0) {
//            	   ball.setXSpeed(1);
//               }
//               if(ball.getYSpeed()==0) {
//            	   ball.setYSpeed(1);
//               }
//               System.out.println(type);// Increase paddle width
//              
//             
//       }
	       if (type.equals("timePowerUp.png")) {
	    	    // Decrease ball speed
	    	    ball.setXSpeed(ball.getXSpeed() * 0.8); 
	    	    ball.setYSpeed(ball.getYSpeed() * 0.8); 
	    	    System.out.println(type + ": Decreased ball speed");
	    	}

	    	if (type.equals("timeDebuff.png")) {
	    	    // Increase ball speed
	    	    ball.setXSpeed(ball.getXSpeed() * 1.2); 
	    	    ball.setYSpeed(ball.getYSpeed() * 1.2); 
	    	    System.out.println(type + ": Increased ball speed");
	    	}
	
	    }
	    //next three functions is for the shop
	    public void handleP1() { 
	    	System.out.println("p1");
	    	paddle.setWidth(paddle.getWidth() + 50);
	    }
	    public void handleP2() {
	    	System.out.println("p2");
	    	paddle.setSpeed(paddle.getSpeed() + 50);
	    }
	    public void handleP3() {
	    	System.out.println("p3");
	    	ball.setXSpeed(ball.getXSpeed() * 0.8);
	    	ball.setYSpeed(ball.getYSpeed() *0.8);
	    }
	    
	    //update function updates the game so that game works
	    private void update() {
	        paddle.moveLeft();
	        paddle.moveRight();
	        
	     
	        
	        if(screen == 1) {
	        	ball.move();
	        	
	        }
	        collisions();
	        if(ball.getY()>=hieght) {
	        	screen =2;
	        }
	        
	     }

	    

	    @Override
	    //paints all components
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	
	       
	    	//paints start screen
	        if(screen == 0) {
	        	s.paint(g);
	        	        
	        }
	       //paints playing screen
	        if(screen == 1) {
	        	b.paint(g);
	        	paddle.draw(g);
	        	ball.draw(g);
	        	for(int i=0; i<brickArray.length; i++) {
	        		for(int j=0; j<brickArray[i].length; j++) {
	        			brickArray[i][j].paint(g);
	        		}
	        	}
	        	 c.paint(g);   
	        	 Font f = new Font("atari", Font.PLAIN, 50);
	     		g.setFont(f);
	     		g.setColor(Color.WHITE);

	 	        g.drawString(""+currency, 70, 510);
	 	       Font a = new Font("atari", Font.PLAIN, 20);
	     		g.setFont(a);
	 	        g.drawString("Wins: "+wins,  650, 510);
	
	 	       g.drawString("High Score: " + highScoreManager.getHighScore(), 650, 530);
	 	        
	        }
	        //paints lose screen
	        if (screen == 2) {
	        	e.paint(g);
	        	wins =0;
	        	count = 0;
	        	matchCount =0;
	        	paddle.setSpeed(30);
	        }
	        //paints shop screen
	        if (screen ==3) {
	        	shop.paint(g);
	        	one.paint(g);
	        	two.paint(g);
	        	three.paint(g);
	        	c.paint(g);
	        	 Font f = new Font("atari", Font.PLAIN, 50);
		     		g.setFont(f);
		     		g.setColor(Color.WHITE);
	        	 g.drawString(""+currency, 70, 510);
	        }
	        
	        
	    }
	    //uses keyboard to interact with objects
	    public void keyPressed(KeyEvent e) {
	    	
	        int keyCode = e.getKeyCode();
	        //paddle movement
	        if (keyCode == KeyEvent.VK_LEFT) {
	            paddle.moveLeft();
	        } else if (keyCode == KeyEvent.VK_RIGHT) {
	            paddle.moveRight();
	        }
	        if(keyCode == KeyEvent.VK_SPACE && screen == 0) {
	            backgroundMusic.play();
	        	screen = 1;
	        	ball.setXSpeed(2);
	            ball.setYSpeed(-2);
	        }
	        //moves all the balls
	        if(keyCode == KeyEvent.VK_SPACE && screen == 2) {
	        	screen = 0;
	        	ball.setY(ballY);
	        	ball.setX(ballX);
	        	ball.stopMove();
	        	currency = 0;
	        	paddle.setWidth(150);
	        	paddle.setX(300);
	        	paddle.setY(530);
	        	count =0;
	        	matchCount=0;
	        	
	        	 for(int i=0; i<brickArray.length; i++) {
	 	            for(int j=0; j<brickArray[i].length; j++) {
	 	            if(Math.random()<.25) {
	 	            	brickArray[i][j] = new PowerUp("",i*70+10,j*70+10);
	 		        	
	 	            }else {
	 	        	brickArray[i][j] = new Brick("",i*70+10,j*70+10);
	 	        	matchCount++;
	 	            }
	 	             }
	 	        }
	        	 wins=0;
	        }
	        if(keyCode == KeyEvent.VK_S && screen ==1) {
	        	 xS = ball.getXSpeed();
	        	 yS = ball.getYSpeed();
	        	ball.stopMove();
	        	screen =3;
	        }
	        if(keyCode == KeyEvent.VK_SPACE && screen ==3) {
	        	
	        	screen =1;
	        	ball.setXSpeed(xS);
	        	ball.setYSpeed(yS);
	        }
	    }
	    //gg
	    public void keyReleased(KeyEvent e) {
	        // Unused
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {
	        // Unused
	    }
	    public void mouseClicked(MouseEvent e) {
	        int x = e.getX();
	        int y = e.getY();

	        if (screen == 3 && currency>0) { // If in shop screen
	            if (p1.contains(x, y)) {
	                handleP1();
	                currency--;
	            } else if (p2.contains(x, y)) {
	               handleP2();
	               currency--;
	            } else if (p3.contains(x, y)) {
	                handleP3();
	                currency--;
	            }
	            
	        }
	    }

	  
	    @Override
	    public void mousePressed(MouseEvent e) {}
	    @Override
	    public void mouseReleased(MouseEvent e) {}
	    @Override
	    public void mouseEntered(MouseEvent e) {}
	    @Override
	    public void mouseExited(MouseEvent e) {}


	    public static void main(String[] args) {
	        BrickBreakerView game = new BrickBreakerView();
	        game.start();
	    }
	    
	  
	}





