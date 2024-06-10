
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Toolkit;

public class PowerUp extends Brick {
    private String type;
//constructor
    public PowerUp(String t, int x1, int y1) {
        super(t, x1, y1);
        setType();
        setImage();
    }

    
    public void setImage() {
        img = getImage("/imgs/" + type);
    }

   public void setX(int x2) {
	  super.setX(x2);
   }
   //random powerup img
    public void setType() {
    	 int r = (int)((Math.random() * 6) + 1);
    	 if (r == 1) {
    	        type = "sizePowerUp.png";
    	 } else if (r == 2) {
    	        type = "sizeDebuff.png";
    	 } else if (r == 3) {
    	        type = "speedDebuff.png";
    	 } else if (r == 4) {
    	        type = "speedPowerUp.png";
    	 } else if (r == 5) {
    	        type = "timeDebuff.png";
    	 } else if (r == 6) {
    	        type = "timePowerUp.png";
    	 }
	
    }
    public String getType() {
    	return type;
    }
    //type of brick
    public boolean isPowerUp() {
    	return true;
    }
    
    public void paint(Graphics g) {
        super.paint(g);  
    }

    //getting image file
    public Image getImage(String path) {
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
