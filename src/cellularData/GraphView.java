package cellularData;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.util.Random;

/**
 * Construct a GraphView class to create a GraphView panel 
 * Measure the relative coordinate and print the dots on the panel according to the coordinates
 * Print the X and Y axis and add label to it
 * @author yuxuanli, Junliu Zhang
 *
 */
public class GraphView extends JPanel{
	private int width;
	private int height;
	private int plottedXmin; 
	private int plottedXmax;
	private int plottedYmin;
	private int plottedYmax;
	private Font font;
	public static int POINT_SIZE = 6;
	LinkedList<Country> countries; 
	private double dataMinX;
	private double dataMaxX;
	private double dataMinY;
	private double dataMaxY;
	public LinkedList<Color> colorList; // randomly generate color and store in the LinkedList
	int count;
	String currentCountryName;
	LinkedList<String> clickCountryList;
	String hoverOverSingleName;
	double hoverOverMappedX = 0;
	double hoverOverMappedY = 0;
	
	/**
	 * Construct a GraphView constructor to set the width and height of the GraphView panel
	 * initiate text font, max value and min value of the plotted X and Y value in the map
	 * @param w		the width of the panel
	 * @param h		the height of the panel
	 * @param countries		the country list which is going to be printed
	 */
	public GraphView(int w, int h, LinkedList<Country> countries){
		this.width = w;
		this.height = h;
		font = new Font("Serif", Font.PLAIN, 11);
		this.countries = countries;
		plottedXmin = 30;
		plottedXmax = 630;
		plottedYmin = h - 80;
		plottedYmax = 30;
		colorList = new LinkedList<Color>(); // initiate the colorList linked list to store the random color
		this.clickCountryList = new LinkedList<String>();
		
		setDataAndColor(); // call the method to find the max value and min value of the data year and subscription in the map
	}
	
	/**
	 * Mutator method to find the max and min value of the year(x-axis) and subscription(y-axis) in all country list
	 */
	public void setDataAndColor(){
		this.dataMinY = 0;
		this.dataMaxY = -1;
		this.dataMinX = 9999;
		this.dataMaxX = 0;
		this.count = 0;
		
		//Use iterator to traverse the countries list
		java.util.Iterator<Country> iterator = countries.iterator(); 
		while(iterator.hasNext()){
			Country country = iterator.next();
			
			//Randomly generate color and store it in the colorList linkedlist 
			Random rand = new Random();
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color randomColor = new Color(r,g,b);
			randomColor = randomColor.darker(); // To make data color readable, darker the color
			
			colorList.add(randomColor);
			count++;	// Count how many items in the colorList 
			
			//Use iterator to traverse the subscription list
			java.util.Iterator<SubscriptionYear> iterator1 = country.subscriptions.iterator();
			while (iterator1.hasNext()){
				SubscriptionYear temp = iterator1.next();
				double sub = temp.getSubscriptions(); 
				if (sub > this.dataMaxY){ // Compare two subscriptions and always store bigger one in dataMaxY
					this.dataMaxY = sub;
				}
				if (sub < this.dataMinY){// Compare two subscriptions and always store smaller one in dataMinY
					this.dataMinY = sub;
				}
				
				int year = temp.getYear();// Compare two year and always store bigger one in dataMaxX
				if (year > this.dataMaxX){
					this.dataMaxX = year;
				}
				if (year < this.dataMinX){// Compare two year and always store smaller one in dataMinX
					this.dataMinX = year;
				}
			}
		}
	}
	
	/**
	 * Mutator method to set the data name when hover over the country in legend panel
	 * @param currentCountryName	the country name that mouse hovered over
	 */
	public void setPaintCountryName(String currentCountryName){
		this.currentCountryName = currentCountryName;
	}
	
	/**
	 * Mutator method to set the data name when hover over the specific dots
	 * @param singlePaintCountryName	the country name which mouse hover over the dots
	 */
	public void setSinglePaintCountryName(String singlePaintCountryName){
		this.hoverOverSingleName = singlePaintCountryName;
	}
	
	/**
	 * Accessor method to judge whether the clicked country list is empty
	 * @return		true if the clicked country list is empty
	 */
	public boolean isEmptyClick(){
		java.util.Iterator<String> iterator = clickCountryList.iterator();
		return !iterator.hasNext();
	}
	
	/**
	 * Accessor method to judge whether the clicked country is already in the list
	 * @param name		The name of the country clicked
	 * @return		true if the clicked country is already existed
	 */
	public boolean isExistCountry(String name){
		java.util.Iterator<String> iterator = clickCountryList.iterator();
		while(iterator.hasNext()){
			String temp = iterator.next();
			if (temp.equals(name)){ // judge whether the name clicked is same with the name in the list
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Mutator method to add the country to the list if clicked country is not in the list
	 * And remove the country if it is not in the list
	 * @param currentCountryName	the name of the clicked country
	 */
	public void addClickCountryName(String currentCountryName){
		if (isExistCountry(currentCountryName)){
			clickCountryList.remove(currentCountryName);
		}
		else{
			clickCountryList.add(currentCountryName);
		}
	}
	
	/**
	 * Accessor method decide which country and specific data the mouse hovered over
	 * @param mouseX		the x pixels that the mouse hovered over
	 * @param mouseY		the y pixels that the mouse hovered over
	 * @return		true if the mouse hovered over the specific dots in the graph panel
	 */
	public boolean mouseXYequalsmappedXY(int mouseX, int mouseY){
		double x = 0;
		double y = 0;
		java.util.Iterator<Country> iterator = countries.iterator();
		while(iterator.hasNext()){
			Country currentCountry = iterator.next();
			
			java.util.Iterator<SubscriptionYear> subscriptions = currentCountry.subscriptions.iterator();
			while(subscriptions.hasNext()){
				SubscriptionYear currentSubscriptionYear = subscriptions.next();
				int yearValue = currentSubscriptionYear.getYear();
				double subscriptionValue = currentSubscriptionYear.getSubscriptions();
				
				x = map((double)yearValue, this.dataMinX, this.dataMaxX, this.plottedXmin, this.plottedXmax);
				y = map(subscriptionValue, this.dataMinY, this.dataMaxY, this.plottedYmin, this.plottedYmax);
				
				if(mouseX >= (int)x - 7 && mouseX <= (int)x + 7 && mouseY  >= (int)y - 7 && mouseY <= (int)y + 7){// Give more range sensitivity when hover over the specific data 
					this.hoverOverSingleName = currentCountry.getName();
					this.hoverOverMappedX = x;
					this.hoverOverMappedY = y;
					return true;
				}
				else{
				}
			}
		}
		return false;
	}
	
	/**
	 * Accessor method to return the size of the colorList
	 * @return       the size of colorList
	 */
	public int getCount(){
		return count;
	}
	
	/**
	 * Accessor method to calculate the relative coordinates in the map
	 * @param value		the value of year or the number of subscriptions
	 * @param dataMin	the min data in the year or subscriptions
	 * @param dataMax	the max data in the year of subscriptions
	 * @param plottedMin	the min plotted data of the map
	 * @param plottedMax	the max plotted data of the map
	 * @return        the relative coordinates in the map
	 */
	static public final double map(double value, double dataMin, double dataMax, double plottedMin, double plottedMax){
	    return plottedMin + (plottedMax - plottedMin) * ((value - dataMin) / (dataMax - dataMin));
	}
	
	/**
	 * Accessor method to return the colorList which store a list of random color
	 * @return		colorList which store the random color
	 */
	public LinkedList<Color> getColor(){
		return this.colorList;
	}
	
	/**
	 * A method form JComponent to draw the GraphView panel and add components to it 
	 */
	public void paintComponent(Graphics g2d){
		super.paintComponent(g2d);
		int count = 0;
		double tempX = 0;
		double tempY = 0;
		
		// Draw two lines represent x and y axis
		g2d.drawLine(30, 520, 660, 520);
		g2d.drawLine(30, 520, 30, 30);
		
		//Use iterator to traverse the countries and colorList linked list
		java.util.Iterator<Country> iterator = countries.iterator();
		java.util.Iterator<Color> iterator2 = colorList.iterator();
		
		while(iterator.hasNext()){
			Country currentCountry = iterator.next(); // Get the current country list in the linked list
			
			java.util.Iterator<SubscriptionYear> iterator1 = currentCountry.subscriptions.iterator();
			Color currentColor = iterator2.next(); // Get the current color in the linked list
			int count1 = 0;
			while(iterator1.hasNext()){
				SubscriptionYear currentSubscriptionYear = iterator1.next();
				int yearValue = currentSubscriptionYear.getYear();
				double subscriptionValue = currentSubscriptionYear.getSubscriptions();
				
				// Calculate the relative coordiantes in the map by calling the map() method
				double x = map((double)yearValue, dataMinX, dataMaxX, plottedXmin, plottedXmax);
				double y = map(subscriptionValue, dataMinY, dataMaxY, plottedYmin, plottedYmax);
				
				g2d.setFont(font); // Set the font for the text
				
				// Draw the scale line and scale label on the X axis when traverse the first item in the country list
				if(count == 0){
					g2d.setColor(Color.black);
					g2d.drawLine((int)x, 520, (int)x, 516);
					if(count1 % 5 == 0){
					g2d.drawString(String.valueOf(yearValue), (int)(x - 10), 530);
					}
					count1++;
				}
				
				// Construct a current object with the type ColoredPoinkt
				ColoredPoint current = new ColoredPoint(currentColor,x,y,yearValue,subscriptionValue);
				
				// Judge if the new clicked or hover list is empty or in the list
				if(isEmptyClick() || isExistCountry(currentCountry.getName())){ //print the country list if it is empty or hovered over or clicked
					g2d.setColor(current.getColor());// Set the current color in the linked list
					g2d.fillOval((int)x,(int)y, POINT_SIZE, POINT_SIZE); // Use a filled color circle to represent the data
					
					// whether the mouse is hovered over the specific dots in GraphView panel
					if(currentCountry.getName() == this.hoverOverSingleName && this.hoverOverMappedX == x && this.hoverOverMappedY == y){
						g2d.drawString(current.getLabel(), (int)(x - 20), (int)y);// Add label to the data
					}
					// whether the place mouse hovered over is the country needed to draw
					if(currentCountry.getName() == this.currentCountryName){ 
						g2d.drawString(current.getLabel(), (int)(x - 20), (int)y); // Add label to the data
					}
					// draw lines in between dots and generate the line graph
					if (tempX != 0 && tempY != 0 ){
						if (x > tempX) {
							g2d.setColor(current.getColor());
					        g2d.drawLine((int)tempX + 3, (int)tempY + 3, (int)x + 3, (int)y + 3);
						}				
					}				
					tempX = x;
					tempY = y;
				}
				
			}
			count++; 
		}
		
		//Draw the x-axis and y-axis label for the coordinate system
		g2d.setColor(Color.black); 
		Font font2 = new Font("Arial", Font.PLAIN, 15); 
		g2d.setFont(font2);
		g2d.drawString("Year", (plottedXmax - plottedXmin)/2 + 40, 545); 
		
		// 90 degree rotate the y axis label
		AffineTransform affineTransform = new AffineTransform(); 
		affineTransform.rotate(Math.PI/2);
		Font rotateFont = font2.deriveFont(affineTransform);
		g2d.setFont(rotateFont);
		g2d.drawString("Number of Subscriptions (per 100 people)", 15, (plottedYmin - plottedYmax)/3 );
	}
}
