package cellularData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MouseHoverOverData implements MouseMotionListener {
	GraphView myPlots;
	JFrame frame;
	
	public MouseHoverOverData(JFrame frame, GraphView myPlots){
		this.frame = frame;
		this.myPlots = myPlots;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (myPlots.mouseXYequalsmappedXY(mouseX,mouseY)){
			myPlots.setSinglePaintCountryName(myPlots.hoverOverSingleName);
		}
		else{
			myPlots.setSinglePaintCountryName("");
		}
		frame.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
