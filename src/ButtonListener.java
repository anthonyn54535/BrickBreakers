import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListener implements ActionListener{

	
	JButton theButton;
	
	//construcotr
	public ButtonListener(JButton button) {
		this.theButton = button;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		theButton.setText("X");
		theButton.setEnabled(false);
		theButton.setBackground(new Color(0,255,0));
		
		
	}

}
