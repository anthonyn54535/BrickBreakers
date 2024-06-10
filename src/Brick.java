

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.geom.AffineTransform;

public class Brick {
//instance variables
	protected String type;
    protected Image img;
    protected AffineTransform tx;
    protected int x;
    protected int y;
	//constructor that take takes in string to identify type of brick and also position
	public Brick(String t, int x1, int y1) {
		type =t;
		setType();
		setImage();
		x = x1;
		y = y1;
		tx = new AffineTransform();
	}
	//setter
	public void setImage() {
	    setType();
	    img = getImage("/imgs/"+type);
	}
	//getter
	public String getType() {
		return type;
	}
	//variability of different bricks
	public void setType() {
		int r = (int)((Math.random()*50) + 1);
		if(r<30) {
			type = "iron.png";
		}
		if(r>=30&&r<45) {
			type = "gold.png";
		}
		if(r>=45) {
			type = "diamond.png";
		}
		
	}
	public void setX(int x2) {
		x = x2;
	}
	//normal brick is default to false;
	public boolean isPowerUp() {
		return false;
	}
	public int getX() {
		return x;
	}
//help set of collision
	public Rectangle getLoc() {
		return new Rectangle(x,y,64,64);
	}

	public Rectangle getLeftRect() {
		return new Rectangle(x,y,1,64);
	}
	public Rectangle getRightRect() {
		return new Rectangle(x+63,y,1,64);
	}
	public Rectangle getTopRect() {
		return new Rectangle(x,y,65,1);
	}
	public Rectangle getBottomRect() {
		return new Rectangle(x-5,y+64,69,1);
	}
	
	
	

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		init(x,y);
		g2.drawImage(img, tx, null);

	}
	//initialize
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
	}

	//get image file
	public Image getImage(String path){
		Image tempImage = null;
		try {
			URL imageURL = Brick.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
}
