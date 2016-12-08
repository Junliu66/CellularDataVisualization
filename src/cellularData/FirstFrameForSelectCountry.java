package cellularData;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;


public class FirstFrameForSelectCountry extends JPanel{
	LinkedList<Country> countries;
	int count;

	public FirstFrameForSelectCountry(LinkedList<Country> countries) {
		this.countries = countries;
		repaint();
	}
	
	public boolean isEmptyClick(){
		java.util.Iterator<Country> iterator = countries.iterator();
		return !iterator.hasNext();
	}
	
	public boolean isExistCountry(Country country){
		java.util.Iterator<Country> iterator = countries.iterator();
		while(iterator.hasNext()){
			Country temp = iterator.next();
			if (temp.getName().equals(country.getName())){
				return true;
			}
		}
		return false;
	}
	
	public void addClickCountryName(Country country){
		if (isExistCountry(country)){
			countries.remove(country);
			count--;
		}
		else{
			countries.add(country);
			count++;
		}
	}
	
	public void paintComponent(Graphics g2d){
		super.paintComponent(g2d);

		java.util.Iterator<Country> iterator = countries.iterator();
		while(iterator.hasNext()){
			Country currentCountry = iterator.next();
			Font font = new Font("Serif", Font.PLAIN, 10);
			g2d.setFont(font);
			g2d.drawString(currentCountry.getName(), 34, 50 + 10 * 250);

			
		}
	}
	
	public int getCount(){
		return count;
	}
	
	public LinkedList<Country> getSelectedCountryList(){
		return this.countries;
	}

	

}
