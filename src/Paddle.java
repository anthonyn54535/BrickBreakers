
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
	
	    private int x, y;
	    private int width, height;
	    private int speed;

	    public Paddle(int x, int y, int width, int height) {
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
 
	        this.speed = 30;

	    }

	    public void moveLeft() {
	        x -= speed;
	        if (x < -30) {
	            x = -30;
	            System.out.println("penis");
	        }
	    }
	    public int getWidth() {
	    	return width;
	    }
	    public void setWidth(int w) {
	    	width= w;
	    }
	    public void setSpeed(int s) {
	    	speed = s;
	    }
	    public int getSpeed() {
	    	return speed;
	    }
	    public void moveRight() {
	        x += speed;
	        if (x > 800 - width) {
	            x = 800 - width;
	        }
	    }

	    public Rectangle getLoc() {
	        return new Rectangle(x, y, width, height);
	    }

	    public void draw(Graphics g) {
	        g.setColor(Color.WHITE);
	        g.fillRect(x, y, width, height);
	    }
	    
	    
	    
	    public int getX() {
	    	return x;
	    }
	    public void setX(int x3) {
	    	x=x3;
	    }
	    public void setY(int y3) {
	    	y=y3;
	    }
	    
	    public int getY() {
	    	return y;
	    }




}
