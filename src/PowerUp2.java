import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class PowerUp2 {
	

	private AffineTransform tx;
	private int x;
	private int y;
	private Image img;
	double scaleWidth = .9;		 //change to scale image
	double scaleHeight = .9;
	
	public PowerUp2(int x1, int y1) {
	
		setImage();
		x = x1;
		y = y1;
		tx = new AffineTransform();
	}
	public void setImage() {
	    
	    img = getImage("/imgs/"+"diamondCoin.png");
	}
	
	
	
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		init(x,y);
		g2.drawImage(img, tx, null);

	}
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);

	}

	
	private Image getImage(String path){
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
