package cellularData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MouseClickCountry implements MouseListener{
	String paintCountryName;
	GraphView myPlots;
	JFrame frame;
	LinkedList<Country> countryList;
	
	public MouseClickCountry(JFrame frame, LinkedList<Country> countryList, GraphView myPlots){
		this.frame = frame;
		this.countryList = countryList;
		this.myPlots = myPlots;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if(mouseX >= 5 && mouseX <= 70 && (mouseY - 70) % 100 >= 0 && (mouseY - 70) % 100 <= 25){
			
			java.util.Iterator<Country> iterator = countryList.iterator();
			int i = 0;
			while(iterator.hasNext()){
				Country country = iterator.next();
				if (i == mouseY/100){
					this.paintCountryName = country.getName();
					break;
				}
				i++;
			}
			myPlots.addClickCountryName(paintCountryName);
			frame.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}