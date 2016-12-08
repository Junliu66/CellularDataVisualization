package cellularData;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class GraphFrame {
	protected final int FRAME_WIDTH = 800;
	protected final int FRAME_HEIGHT = 600;
	int requestedSize;

	private GraphFrame (LinkedList<Country> selectedCountries)
	{
		TestGraphView obj = new TestGraphView();
		
		
		JFrame frame = new JFrame("Cellular Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set Flowlayout for the frame
		FlowLayout myLayout = new FlowLayout();
		frame.setLayout(myLayout);

		//  Specify the size of your graph view based on your other panels
		int graph_panel_size = FRAME_WIDTH;

		//  Define the class GraphView such that it will map the cellular data of a country to the 
		//       width and height of the panel.
		GraphView myPlots = new GraphView(graph_panel_size, FRAME_HEIGHT, selectedCountries);
		
		myPlots.setBorder(new BevelBorder(BevelBorder.RAISED));		// Set bevel border for the GraphView panel
		myPlots.add(new JLabel("Graph View"));	// Add label to the panel
		myPlots.setPreferredSize(new Dimension(680,580)); // Set a preferred size for the panel

		myPlots.addMouseMotionListener(new MouseHoverOverData(frame, myPlots));
		
		
		// Add a "Help" button to give user instructions how to compare countries
		JButton bt = new JButton("Help");
		bt.setBounds(200, 200, 25, 40);
		
		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog (null, "Click countries you would like to see in the legend section\nCountries arrange in alphabetical order\n (Single click to select country, double click to deselect)", "How to get total subscription years and compare", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton bt2 = new JButton("Calculating Total Subscriptions");
		bt.setBounds(200, 200, 25, 40);
		
		bt2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog (null, "Click different countries to see the total subscriptions \n Single click to select country \n Double click to deselect", "How to get total subscription years and compare", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		myPlots.add(bt);
		myPlots.add(bt2);
		frame.add(myPlots);


		// Draw the legend panel
		LegendPanel legendPanel = new LegendPanel(myPlots.getColor(),selectedCountries); 
		legendPanel.setBorder(new BevelBorder(BevelBorder.RAISED)); // 	Set bevel border for the legend pane
		legendPanel.add(new JLabel("Legend")); // Add label to the legend panel
		//legendPanel.addMouseListener(new MouseHoverOverCountry());
		
		// Construct a scroll panel and add legendPanel to the scroll panel as a component
		JScrollPane scrollLegend = new JScrollPane(legendPanel);
		scrollLegend.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Set vertical scroll bar policy
		scrollLegend.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// Set horizontal scroll bar policy
		scrollLegend.setPreferredSize(new Dimension(100,580)); // Set preferred scroll panel dimension
		legendPanel.setPreferredSize(new Dimension(120,myPlots.getCount() * 100 + 100)); // Set preferred original panel dimension
		scrollLegend.setViewportView(legendPanel);
		
		legendPanel.addMouseMotionListener(new MouseHoverOverCountry(frame, selectedCountries, myPlots));
		legendPanel.addMouseListener(new MouseClickCountry(frame, selectedCountries, myPlots));
		// add the legend panel to your frame
		frame.add(scrollLegend);

		// Setup the frame to view.
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);	
	}
	public JComponent getComponent() {
	      return frame;
}
