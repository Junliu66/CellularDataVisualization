package cellularData;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.util.Iterator;

public class CountryList extends JPanel{
	LinkedList<Country> countries;
	int count;
	
	public CountryList(LinkedList<Country> countries){
		this.countries = countries;
	}
	
	public void paintComponent(Graphics g2d){
		super.paintComponent(g2d);
		count = 0;
		
		java.util.Iterator<Country> iterator = countries.iterator();
		while(iterator.hasNext()){
			Country currentCountry = iterator.next();
			
			Font font = new Font("Serif", Font.PLAIN, 10);
			g2d.setFont(font);
			g2d.drawString(currentCountry.getName(), 50, 10 + 10 * count);
			count++;
		}
	}
}