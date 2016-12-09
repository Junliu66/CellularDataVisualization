package cellularData;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Iterator;

/**
 * Construct the LegendPanel class to print the LegendPanel panel
 * Which shows the cooresponding color and country name graphed in the GraphView Panel
 * @author Junliu Zhang, yuxuanli
 *
 */
public class LegendPanel extends JPanel {
	LinkedList<Color> countryColor;
	LinkedList<Country> countries;
	private int count;
	
	/**
	 * Construct LegendPanel constructor to store the linked list for both the countries and color list
	 * @param colors          the color list assign color to each country
	 * @param countries       the country list containing data of each country
	 */
	public LegendPanel(LinkedList<Color> colors, LinkedList<Country> countries){
		this.countryColor = colors;
		this.countries = countries;
		repaint();
	}

	
	/**
	 * A method form JComponent to draw the LegendPanel panel and add components to it 
	 */
	public void paintComponent(Graphics g2d){
		super.paintComponent(g2d);
		count = 0;
		
		//Use iterator to traverse the countries and colorlist
		java.util.Iterator<Country> iterator = countries.iterator();
		java.util.Iterator<Color> iterator2 = countryColor.iterator();
		while(iterator.hasNext()){
			Color currentColor = iterator2.next(); // Get the current color for the country list
			Country currentCountry = iterator.next(); // Get the current Country obejct in the linked list
			
			g2d.setColor(currentColor); // Set the color corresponding to the linked list
			g2d.drawRoundRect(5, 70 + 100 * count, 25, 25, 10, 10); // Draw a rounded rectangle
			g2d.fillRoundRect(5, 70 + 100 * count, 25, 25, 10, 10);	// Fill the rounded rectangle with the current color
			
			// Add the label with the name of the current countries next to the rounded rectangle
			Font font = new Font("Serif", Font.PLAIN, 10);
			g2d.setFont(font);
			g2d.drawString(currentCountry.getName(), 34, 85 + 100 * count);
			count++;
		}
	}
}