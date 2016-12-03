package cellularData;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
/**
 * Construct a ColoredPoint class to store the color of the the country list
 * Outprint the label according to the requested format
 * @author Junliu Zhang, yuxuanli
 *
 */
public class ColoredPoint extends Point{
	private Color color;
	private double originalX;
	private double originalY;
	
	/**
	 * Construct a ColoredPoint constructor to receive the data and store the data
	 * @param myColor	the color of the current country list
	 * @param mappedX	the X coordinate in the map
	 * @param mappedY	the Y coordinate in the map
	 * @param originalX		the year of the data
	 * @param originalY		the number of the subscription in a particular year
	 */
	public ColoredPoint(Color myColor, double mappedX, double mappedY, double originalX, double originalY) {
		this.color = myColor;
		super.x = (int) mappedX;
		super.y = (int) mappedY;
		this.originalX = originalX;
		this.originalY = originalY;
	}
	
	/**
	 * Accessor method to get the color of the current country list
	 * @return	the color of the current coutry list
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * A String presentation to print the requested format of the coordinate
	 * @return	the String of the coordinate
	 */
	public String getLabel() {
		
		String res = "(" + (int)originalX + ", " + String.format("%.1f",originalY) + ")"; // Coordinate format
		return res;
	}
}
