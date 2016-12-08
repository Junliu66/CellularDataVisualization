package cellularData;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FirstFrameForPrintOutCountry extends JPanel{
	LinkedList<Country> countries;
	int count;
	
	public FirstFrameForPrintOutCountry(LinkedList<Country> countries){
		this.countries = countries;
	}
	
	public void paintComponent(Graphics g2d){
		super.paintComponent(g2d);
		count = 0;
		
		java.util.Iterator<Country> iterator = countries.iterator();
		while(iterator.hasNext()){
			Country currentCountry = iterator.next();
			
			Font font = new Font("Serif", Font.PLAIN, 15);
			g2d.setFont(font);
			g2d.drawString(currentCountry.getName(), 20, 20 + 20 * count);
			count++;
		}
	}
}