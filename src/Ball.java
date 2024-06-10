import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.sound.sampled.Line;

public class Ball {
		//instance variables
	    private int x, y;
	    private int d;
	    private double xSpeed, ySpeed;
	    Music bounce = new Music("ballSound.wav", false);
//constructor
	    public Ball(int x, int y, int d, double speed) {
	        this.x = x;
	        this.y = y;
	        this.d = d;
	        this.xSpeed = speed;
	        this.ySpeed = -speed;
	    }
//updates position with there being movement
	    public void move() {
	        x += xSpeed;
	        y += ySpeed;
	        if (x <= 0 || x >= 800 - d) {
	        	bounce.play();
	            xSpeed *= -1;
	        }
	        if (y <= 0) {
	            ySpeed *= -1;
	            bounce.play();
	        }
	     }
	    public void stopMove() {
	    	xSpeed = 0;
	    	ySpeed = 0;
	    }
	    //bounces
	    public void reflectY() {
	    	ySpeed*=-1;
	    }
	    public void reflectX() {
	    	xSpeed*=-1;
	    } 
//sets up for collision
	    public Rectangle getLoc() {
	        return new Rectangle(x, y, d, d);
	    }
	    //getters and setters
	    public int getX() {
	    	return x;
	    }
	    public double getXSpeed() {
	    	return xSpeed;
	    }
	   
	    public double getYSpeed() {
	    	return ySpeed;
	    }
	    public void setYSpeed(double yb) {
	    	ySpeed = yb;
	    }
	    public void setXSpeed(double xb) {
	    	xSpeed = xb;
	    }
	    
	    public int getY() {
	    	return y;
	    }
	    public void setX(int X) {
	    	x =  X;
	    }
	    public void setY(int Y) {
	    	y = Y;
	    }
	    

	    public void draw(Graphics g) {
	        g.setColor(Color.WHITE);
	        g.fillOval(x, y, d, d);
	    }

	   }





