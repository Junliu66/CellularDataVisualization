package cellularData;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import javax.swing.*;

import java.util.Random;

public class ShowCountryPanelForFirstFrame extends JPanel{
	LinkedList<Country> clickCountryList;
	int count;
	int length = 0;
	
	public ShowCountryPanelForFirstFrame(){
		this.clickCountryList = new LinkedList<Country>();
	}
	
	public boolean isEmptyClick(){
		java.util.Iterator<Country> iterator = clickCountryList.iterator();
		return !iterator.hasNext();
	}
	
	public boolean isExistCountry(Country clickedCountry){
		java.util.Iterator<Country> iterator = clickCountryList.iterator();
		while(iterator.hasNext()){
			Country temp = iterator.next();
			if (temp.getName().equals(clickedCountry.getName())){
				return true;
			}
		}
		return false;
	}
	
	public void addClickCountryName(Country clickedCountry){
		if (isExistCountry(clickedCountry)){
			clickCountryList.remove(clickedCountry);
			length--;
		}
		else{
			clickCountryList.add(clickedCountry);
			length++;
		}
	}
	
	public int getLength(){
		return this.length;
	}
	
	public LinkedList<Country> getSelectedCountryList(){
		return this.clickCountryList;
	}
	
	public void paintComponent(Graphics g2d){
		super.paintComponent(g2d);
		count = 1;
		
		java.util.Iterator<Country> iterator = clickCountryList.iterator();
		while(iterator.hasNext()){
			Country currentCountry = iterator.next();
			
			Font font = new Font("Serif", Font.PLAIN, 15);
			g2d.setFont(font);
			
			if(!iterator.hasNext() && count == 1){
				g2d.drawString(currentCountry.getName(), 20, 40);
			}
			else{
				g2d.drawString(currentCountry.getName(), 20, 20 + 20 * count);
			}
			count++;
		}
	}
	
}

