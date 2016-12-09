package cellularData;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MouseListenerForDataPoint implements MouseListener {
	JLabel label;
	
	public void mouseClicked(MouseEvent evt) {
			
	}
	
	public void mouseEntered(MouseEvent evt) {
		if (evt.getSource() == label) {
			
		}
			
	}
	
	public void mouseExited(MouseEvent evt) {
		
	}
	
	public void mousePressed(MouseEvent evt) {
		// do nothing
	}
	
	public void mouseReleased(MouseEvent evt) {
		// do nothing		
	}

}
