package cellularData;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;


public class TestSelectedCountryandGraphView {
	
	public TestSelectedCountryandGraphView(){
		
	}
	
	private LinkedList<Country> buildCountryList(Country [] allCountries)
	{	
		
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < allCountries.length; i++)
		{
				selectedCountries.add(allCountries[i]);
		}
		return selectedCountries;
	}
	
	private void initializeGui(LinkedList<Country> selectedCountries)
	{
		JFrame firstFrame = new JFrame("Selected Countries You Want to Show in the Graph");
		firstFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		BorderLayout myLayout = new BorderLayout();
		firstFrame.setLayout(myLayout);

		//  Define the class GraphView such that it will map the cellular data of a country to the 
		//       width and height of the panel.

		//Draw the legend panel
		
		ShowCountryPanelForFirstFrame showCountryPanel = new ShowCountryPanelForFirstFrame(); 
		showCountryPanel.setBorder(new BevelBorder(BevelBorder.RAISED)); // 	Set bevel border for the legend pane
		showCountryPanel.add(new JLabel("Country Selected")); // Add label to the legend panel
		//legendPanel.addMouseListener(new MouseHoverOverCountry());
		
		// Construct a scroll panel and add legendPanel to the scroll panel as a component
		JScrollPane scrollShow = new JScrollPane(showCountryPanel);
		scrollShow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Set vertical scroll bar policy
		scrollShow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// Set horizontal scroll bar policy
		scrollShow.setPreferredSize(new Dimension(195,600)); // Set preferred scroll panel dimension
		showCountryPanel.setPreferredSize(new Dimension(200,showCountryPanel.getLength() * 20)); // Set preferred original panel dimension
		scrollShow.setViewportView(showCountryPanel);
		
		
		//legendPanel.addMouseMotionListener(new MouseHoverOverCountry(frame, selectedCountries, myPlots));
		//legendPanel.addMouseListener(new MouseClickCountry(frame, selectedCountries, myPlots));
		// add the legend panel to your frame
		firstFrame.add(scrollShow, BorderLayout.WEST);
		
		SelectedCountryPanelForFirstFrame myPlots = new SelectedCountryPanelForFirstFrame(selectedCountries);
		
		myPlots.setBorder(new BevelBorder(BevelBorder.RAISED));		// Set bevel border for the GraphView panel
		//myPlots.add(new JLabel("Country List"));	// Add label to the panel
		
		JScrollPane scrollmyPlots = new JScrollPane(myPlots);
		scrollmyPlots.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Set vertical scroll bar policy
		scrollmyPlots.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// Set horizontal scroll bar policy
		scrollmyPlots.setPreferredSize(new Dimension(195,500)); // Set preferred scroll panel dimension
		myPlots.setPreferredSize(new Dimension(200,5450)); // Set preferred original panel dimension
		scrollmyPlots.setViewportView(myPlots);

		myPlots.addMouseListener(new MouseClickCountryForFirstFrame(firstFrame, showCountryPanel, selectedCountries));

		firstFrame.add(scrollmyPlots, BorderLayout.EAST);

		JButton bt = new JButton("Done");
		firstFrame.add(bt,BorderLayout.SOUTH);
		
		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				firstFrame.dispose();
				new SecondFrame(showCountryPanel.getSelectedCountryList());
			}
		});
		
		firstFrame.setSize(400, 600);
		firstFrame.setResizable(false);
		firstFrame.setVisible(true);	
	}
	
	public class SecondFrame{
		LinkedList<Country> selectedCountries;
		JFrame secondFrame = new JFrame("Cellular Graph");
		
		public SecondFrame(LinkedList<Country> selectedList){	
			final int FRAME_WIDTH = 800;
			final int FRAME_HEIGHT = 600;
			
			this.selectedCountries = selectedList;
			//JFrame frame = new JFrame("Cellular Graph");
			secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//Set Flowlayout for the frame
			FlowLayout myLayout = new FlowLayout();
			secondFrame.setLayout(myLayout);

			//  Specify the size of your graph view based on your other panels
			int graph_panel_size = FRAME_WIDTH;

			//  Define the class GraphView such that it will map the cellular data of a country to the 
			//       width and height of the panel.
			GraphView myPlots = new GraphView(graph_panel_size, FRAME_HEIGHT, selectedCountries);
			
			myPlots.setBorder(new BevelBorder(BevelBorder.RAISED));		// Set bevel border for the GraphView panel
			myPlots.add(new JLabel("Graph View"));	// Add label to the panel
			myPlots.setPreferredSize(new Dimension(680,580)); // Set a preferred size for the panel

			myPlots.addMouseMotionListener(new MouseHoverOverData(secondFrame, myPlots));
			
			
			// Add a "Help" button to give user instructions how to compare countries
			JButton bt = new JButton("Help");
			bt.setBounds(200, 200, 25, 40);
			
			bt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog (null, "Click countries you would like to see in the legend section\nCountries arrange in alphabetical order\n (Single click to select country, double click to deselect)", "How to get total subscription years and compare", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
			myPlots.add(bt);
			secondFrame.add(myPlots);
			
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
			
			legendPanel.addMouseMotionListener(new MouseHoverOverCountry(secondFrame, selectedCountries, myPlots));
			legendPanel.addMouseListener(new MouseClickCountry(secondFrame, selectedCountries, myPlots));
			// add the legend panel to your frame
			secondFrame.add(scrollLegend);
			
			
			secondFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			secondFrame.setResizable(false);
			secondFrame.setVisible(true);	
		}
	}
	
	public static void main(String[] args) 
	{		
		final String FILENAME = "resources/cellular.csv";	// test file with latest set of countries and subscription years

		// Parses the CSV data file
		// NOTE: Handle all exceptions in the constructor.
		//       For full credit, do *not* throw exceptions to main. 
		CSVReader parser = new CSVReader(FILENAME);

		// In class CSVReader the accessor methods only return values of instance variables.
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		


		// Holds the data for all Country object read from the input data file.
		Country [] countries;

		// Initializes the to the number of entries read by CSVReader.
		countries = new Country[countryNames.length];

		// Reference to a Country object
		Country current;

		// Go through each country name parsed from the CSV file.
		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   

			current = new Country(countryNames[countryIndex]);	// version 2 and final version of Country constructor

			// Go through each year of cellular data read from the CSV file.
			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}

			// Add the newly created country to the 1D array.
			countries[countryIndex] = current;
		}

		// Creates an object of our current application.		
		TestSelectedCountryandGraphView application = new TestSelectedCountryandGraphView();

		// Tests the generic LinkedList class by adding a all entries
		// from the array of Country objects.
		LinkedList<Country> listOfCountries = application.buildCountryList(countries);

		// Create graphical representation of our data.
		application.initializeGui(listOfCountries);

		// flush the error stream
		System.err.flush();

		System.out.println("\nDone with TestGraphView.");
	}
}
