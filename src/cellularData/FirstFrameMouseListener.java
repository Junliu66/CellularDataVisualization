package cellularData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class FirstFrameMouseListener implements MouseListener {
	Country paintCountry;
	JFrame frame;
	FirstFrameForSelectCountry selectedCountryPanel;
	LinkedList<Country> countryList;
	
	public FirstFrameMouseListener(JFrame frame, FirstFrameForSelectCountry showPanel, LinkedList<Country> allCountries){
		this.selectedCountryPanel = showPanel;
		this.frame = frame;
		this.countryList = allCountries;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if(mouseX >= 10 && mouseX <= 190 && mouseY >= 0 && mouseY <= 5285){
			
			java.util.Iterator<Country> iterator = countryList.iterator();
			int count = 1;
			while(iterator.hasNext()){
				Country country = iterator.next();
				
				if (count == (mouseY - 5)/20 + 1){
					this.paintCountry = country;
					break;
				}
				count++;
			}
			selectedCountryPanel.addClickCountryName(paintCountry);
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