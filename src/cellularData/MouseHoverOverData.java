package cellularData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

/**
 * Constructe a MouseHoverOverData class that implements mouseMotionListener
 * Decide whether the mouse hover over the specific dot in the graphview panel
 * If yes, repaint to show the data label for the dot
 * @author junliuzhang
 *
 */
public class MouseHoverOverData implements MouseMotionListener {
	GraphView myPlots;
	JFrame frame;
	
	/**
	 * Constructor that set the second frame and the graph view panel
	 * @param frame
	 * @param myPlots
	 */
	public MouseHoverOverData(JFrame frame, GraphView myPlots){
		this.frame = frame;
		this.myPlots = myPlots;
	}

	/**
	 * Override the mouseMoved method to get the coordinates that mouse hover over
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (myPlots.mouseXYequalsmappedXY(mouseX,mouseY)){ //  get the pixels mouse hovered over and compare with dots in graphview class
			myPlots.setSinglePaintCountryName(myPlots.hoverOverSingleName); //if the mouse does hover over dots, add the country to the linkedlist
		}
		else{
			myPlots.setSinglePaintCountryName(""); // if not, set the name that is going to paint as ""
		}
		frame.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
