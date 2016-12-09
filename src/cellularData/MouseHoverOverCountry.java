package cellularData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

/**
 * Construct a MouseHoverOverCountry class which implements MouseMotionListener 
 * Decide whether the mouse hover over the country name in the legend panel
 * If yes, repaint to show the data label for the country
 * @author junliuzhang
 *
 */
public class MouseHoverOverCountry implements MouseMotionListener {
	String paintCountryName;
	GraphView myPlots;
	JFrame frame;
	LinkedList<Country> countryList;
	
	/**
	 * Constructor to get the second frame, the painted country list and the graph view panel
	 * @param frame		the second frame
	 * @param countryList		the country list that is going to paint	
	 * @param myPlots		the graph view panel
	 */
	public MouseHoverOverCountry(JFrame frame, LinkedList<Country> countryList, GraphView myPlots){
		this.frame = frame;
		this.countryList = countryList;
		this.myPlots = myPlots;
	}
	
	/**
	 * 	Override the mouseMoved method to activate the hover over action
	 */
	public void mouseMoved(MouseEvent e){
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		// math function to decide whether mouse is hovering over the country name or the filled round rectangles
		if(mouseX >= 5 && mouseX <= 70 && (mouseY - 70) % 100 >= 0 && (mouseY - 70) % 100 <= 25){
			
			java.util.Iterator<Country> iterator = countryList.iterator();
			int i = 0;
			while(iterator.hasNext()){
				Country country = iterator.next();
				if (i == mouseY/100){ // calculate which country has been hovered over
					this.paintCountryName = country.getName();
					break;
				}
				i++;
			}
			myPlots.setPaintCountryName(paintCountryName); // get the name of the country whose labels are going to paint
		}
		else{
			myPlots.setPaintCountryName(""); // if hover over other places, set the paint country name to ""
		}
		frame.repaint(); // repaint the graphview
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
}
