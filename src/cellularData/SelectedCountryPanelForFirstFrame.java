package cellularData;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Construct a SelectedCountryPanelForFirstFrame class extends Jpanel
 * Add a panel at the first frame to show the country that has been selected
 * @author yuxuanli
 *
 */
public class SelectedCountryPanelForFirstFrame extends JPanel{
	LinkedList<Country> countries;
	int count;
	
	/**
	 * Constructor to set the country list that is going be painted
	 * @param countries
	 */
	public SelectedCountryPanelForFirstFrame(LinkedList<Country> countries){
		this.countries = countries;
	}
	
	/**
	 * Override method in JComponent to paint the country that has been selected
	 */
	public void paintComponent(Graphics g2d){
		super.paintComponent(g2d);
		count = 0;
		
		java.util.Iterator<Country> iterator = countries.iterator(); // traverse the pained linked list
		while(iterator.hasNext()){ 
			Country currentCountry = iterator.next();
			
			Font font = new Font("Serif", Font.PLAIN, 15); 
			g2d.setFont(font);
			g2d.drawString(currentCountry.getName(), 20, 20 + 20 * count);
			count++;
		}
	}
}