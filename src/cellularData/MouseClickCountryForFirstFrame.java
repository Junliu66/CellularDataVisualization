package cellularData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * Construct a MouseClickCountryForFirstFrame class which implements MouseListener
 * Single click country to select, double click country to deselect to choose which country to print
 * @author yuxuanli
 *
 */
public class MouseClickCountryForFirstFrame implements MouseListener {
	Country paintCountry;
	JFrame frame;
	ShowCountryPanelForFirstFrame selectedCountryPanel;
	LinkedList<Country> countryList;
	
	/**
	 * Constructor to get the first frame, the panel to show the country and the country list for allcountries
	 * @param frame		the first frame
	 * @param showPanel		the panel to print the country name
	 * @param allCountries		all countries from csv file
	 */
	public MouseClickCountryForFirstFrame(JFrame frame, ShowCountryPanelForFirstFrame showPanel, LinkedList<Country> allCountries){
		this.selectedCountryPanel = showPanel;
		this.frame = frame;
		this.countryList = allCountries;
	}
	/**
	 * Override the mouseClicked method to decide which country has been selected
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		/**
		 * Math function to calculate which country has been clicked
		 */
		if(mouseX >= 10 && mouseX <= 190 && mouseY >= 0 && mouseY <= 5285){
			
			java.util.Iterator<Country> iterator = countryList.iterator();
			int count = 1;
			while(iterator.hasNext()){
				Country country = iterator.next();
				
				if (count == (mouseY - 5)/20 + 1){ // math function to calculate which country has been selected
					this.paintCountry = country;
					break;
				}
				count++;
			}
			selectedCountryPanel.addClickCountryName(paintCountry);  // add the country has been clicked to the linked list
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
