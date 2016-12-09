package cellularData;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import javax.swing.*;

import java.util.Random;

/**
 * Construct a ShowCountryPanelForFirstFrame class which extends Jpanel
 * Draw a panel with all countries name which have been got from cvs file
 * @author yuxuanli
 *
 */
public class ShowCountryPanelForFirstFrame extends JPanel{
	LinkedList<Country> clickCountryList;
	int count;
	int length = 0;
	
	/**
	 * Default Counstructor 
	 */
	public ShowCountryPanelForFirstFrame(){
		this.clickCountryList = new LinkedList<Country>(); // initiate the linked list
	}
	
	/**
	 * Accessor method to decide whether the painted linked list is empty or not
	 * @return	true if the list is empty
	 */
	public boolean isEmptyClick(){
		java.util.Iterator<Country> iterator = clickCountryList.iterator();
		return !iterator.hasNext();
	}
	
	/**
	 * Accessor method to decide whether the country selected is already in the linked list
	 * @param clickedCountry	the country that the user has clicked
	 * @return		true if the country already existed in the list 
	 */
	public boolean isExistCountry(Country clickedCountry){
		java.util.Iterator<Country> iterator = clickCountryList.iterator(); 
		while(iterator.hasNext()){
			Country temp = iterator.next();
			if (temp.getName().equals(clickedCountry.getName())){ // compare whether the name of the country are equal
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Mutator method to add the country to the list if it does not exist in the list
	 * Or remove the country to the list if it already exists
	 * @param clickedCountry
	 */
	public void addClickCountryName(Country clickedCountry){
		if (isExistCountry(clickedCountry)){
			clickCountryList.remove(clickedCountry); // remove the country in the linked list
			length--;
		}
		else{
			clickCountryList.add(clickedCountry); // add the country int he linked list
			length++;
		}
	}
	
	/**
	 * Accessor method to get the size of the painted liked list
	 * @return		the size of the pained list
	 */
	public int getLength(){
		return this.length;
	}
	
	/**
	 * Accessor method to get the country list that the user selected
	 * @return		the country list that user select
	 */
	public LinkedList<Country> getSelectedCountryList(){
		return this.clickCountryList;
	}
	
	/**
	 * Override the method in the JComponent to paint all the list of the country
	 */
	public void paintComponent(Graphics g2d){
		super.paintComponent(g2d);
		count = 1;
		
		java.util.Iterator<Country> iterator = clickCountryList.iterator();
		while(iterator.hasNext()){
			Country currentCountry = iterator.next();
			
			Font font = new Font("Serif", Font.PLAIN, 15);
			g2d.setFont(font);
			
			
			if(!iterator.hasNext() && count == 1){ // if it is first country of the list, paint in initial position
				g2d.drawString(currentCountry.getName(), 20, 40);
			}
			else{// if not, paint according to the math function
				g2d.drawString(currentCountry.getName(), 20, 20 + 20 * count);
			}
			count++;
		}
	}
	
}

