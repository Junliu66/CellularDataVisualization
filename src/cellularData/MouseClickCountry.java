package cellularData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * Construct a MouseClickCountry class which implements MouseListener 
 * Make countries in the Legend panel clickable
 * Add a particular country when single click, deselect the country when deselect
 * @author yuxuanli
 *
 */
public class MouseClickCountry implements MouseListener{
	String paintCountryName;
	GraphView myPlots;
	JFrame frame;
	LinkedList<Country> countryList;
	
	/**
	 * Constructor to save the frame from JFrame, the painted country list and the graph view panel
	 * @param frame		from JFrame
	 * @param countryList		the painted country list
	 * @param myPlots		the graphview panel
	 */
	public MouseClickCountry(JFrame frame, LinkedList<Country> countryList, GraphView myPlots){
		this.frame = frame;
		this.countryList = countryList;
		this.myPlots = myPlots;
	}
	
	/**
	 * Override mouseClicked method to decide which country has been chosed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		// Math function to calculate whether the mouse clicked the place drawed with country name
		if(mouseX >= 5 && mouseX <= 70 && (mouseY - 70) % 100 >= 0 && (mouseY - 70) % 100 <= 25){
			
			java.util.Iterator<Country> iterator = countryList.iterator();
			int i = 0;
			while(iterator.hasNext()){
				Country country = iterator.next();
				if (i == mouseY/100){ // math function calculate which country has been clicked
					this.paintCountryName = country.getName(); // get the name of the country that has been clicked
					break;
				}
				i++;
			}
			myPlots.addClickCountryName(paintCountryName);  // add country to the new painted list
			frame.repaint(); 
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
