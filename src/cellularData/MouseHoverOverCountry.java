package cellularData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MouseHoverOverCountry implements MouseMotionListener {
	boolean hover;
	String paintCountryName;
	GraphView myPlots;
	JFrame frame;
	LinkedList<Country> countryList;
	
	public MouseHoverOverCountry(JFrame frame, LinkedList<Country> countryList, GraphView myPlots){
		this.frame = frame;
		this.countryList = countryList;
		this.myPlots = myPlots;
	}
	
	public void mouseMoved(MouseEvent e){
		int mouseX = e.getX();
		int mouseY = e.getY();
		

		if(mouseX >= 5 && mouseX <= 70 && (mouseY - 70) % 100 >= 0 && (mouseY - 70) % 100 <= 25){
			
			LinkedList<Country> temp = new LinkedList<Country>();
			java.util.Iterator<Country> iterator = countryList.iterator();
			int i = 0;
			while(iterator.hasNext()){
				Country country = iterator.next();
				if (i == mouseY/100){
					temp.add(country);
					this.paintCountryName = country.getName();
					break;
				}
				i++;
			}
			myPlots.setPaintCountryName(paintCountryName);
		}
		else{
			myPlots.setPaintCountryName("");
		}
		frame.repaint();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
}