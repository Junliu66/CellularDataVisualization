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

/**
 * Test two frames, selected country frame and graph view frame, connect two frame with a JButton
 * Add selected country panel and show country panel to the first frame
 * Add graphview panel and legend panel to the second frame
 * @author yuxuanli junliuzhang
 *
 */
public class TestSelectedCountryandGraphView {
	
	/**
	 * default constructor
	 */
	public TestSelectedCountryandGraphView(){
	}
	
	/**
	 * Accessor method to build the country list with all countries in the cvs file
	 * @param allCountries		the array with the whole country list
	 * @return		the country linked list with all countries
	 */
	private LinkedList<Country> buildCountryList(Country [] allCountries)
	{	
		
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < allCountries.length; i++)
		{
				selectedCountries.add(allCountries[i]);
		}
		return selectedCountries;
	}
	
	/**
	 * Mutator method to initialize first frame
	 * Add two panels to first frame
	 * @param selectedCountries		the whole list of the country
	 */
	private void initializeGui(LinkedList<Country> selectedCountries)
	{
		JFrame firstFrame = new JFrame("Selected Countries You Want to Show in the Graph"); // initialize the first frame
		firstFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		BorderLayout myLayout = new BorderLayout(); // set to border layout
		firstFrame.setLayout(myLayout);

		//  Define the class GraphView such that it will map the cellular data of a country to the 
		//       width and height of the panel.

		//Add label and set label alignment to the frame
		JLabel lb = new JLabel("Single Click to Select, Double Click to Deselect");
		lb.setHorizontalAlignment(JLabel.CENTER);
		firstFrame.add(lb, BorderLayout.NORTH);
		
		// Initialize the showCountryPanel which show all the country in the list
		ShowCountryPanelForFirstFrame showCountryPanel = new ShowCountryPanelForFirstFrame(); 
		showCountryPanel.setBorder(new BevelBorder(BevelBorder.RAISED)); // 	Set bevel border for the showCountry pane
		showCountryPanel.add(new JLabel("Country Selected")); // Add label to the legend panel
		
		// Construct a scroll panel and add showCountryPanel to the scroll panel as a component
		JScrollPane scrollShow = new JScrollPane(showCountryPanel);
		scrollShow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Set vertical scroll bar policy
		scrollShow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// Set horizontal scroll bar policy
		scrollShow.setPreferredSize(new Dimension(195,600)); // Set preferred scroll panel dimension
		showCountryPanel.setPreferredSize(new Dimension(200,showCountryPanel.getLength() * 20)); // Set preferred original panel dimension
		scrollShow.setViewportView(showCountryPanel);
		
		// add the showCountryPanel to your frame
		firstFrame.add(scrollShow, BorderLayout.WEST);
		
		// Initialize the selectedCountryPanel which show the country that user selected
		SelectedCountryPanelForFirstFrame selectedCountryPanel = new SelectedCountryPanelForFirstFrame(selectedCountries);
		selectedCountryPanel.setBorder(new BevelBorder(BevelBorder.RAISED));		// Set bevel border for the selectedCountryPanel
		
		JScrollPane scrollmyPlots = new JScrollPane(selectedCountryPanel);
		scrollmyPlots.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Set vertical scroll bar policy
		scrollmyPlots.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// Set horizontal scroll bar policy
		scrollmyPlots.setPreferredSize(new Dimension(195,500)); // Set preferred scroll panel dimension
		selectedCountryPanel.setPreferredSize(new Dimension(200,5450)); // Set preferred original panel dimension
		scrollmyPlots.setViewportView(selectedCountryPanel);
		
		// add mouse click action to the showCountryPanel that user can select the country and paint to the selectedCountryPanel
		selectedCountryPanel.addMouseListener(new MouseClickCountryForFirstFrame(firstFrame, showCountryPanel, selectedCountries));
		
		// add the selectedCountryPanel to the first frame
		firstFrame.add(scrollmyPlots, BorderLayout.EAST);
		
		// add a JButton for at the first frame 
		JButton bt = new JButton("Done");
		firstFrame.add(bt,BorderLayout.SOUTH);
		
		// add actionlistener on the button to go to second frame
		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				firstFrame.dispose(); // dispose the first frame when open the second frame
				new SecondFrame(showCountryPanel.getSelectedCountryList()); // construct the second frame and save the country list that is going paint to the second frame
			}
		});
		
		// set frame size and visibility
		firstFrame.setSize(400, 600);
		firstFrame.setResizable(false);
		firstFrame.setVisible(true);	
	}
	
	/**
	 * Initialize the second frame
	 * Add two panels to the second frame
	 * @author yuxuanli
	 *
	 */
	public class SecondFrame{
		LinkedList<Country> selectedCountries;
		JFrame secondFrame = new JFrame("Cellular Graph"); // initialize the second JFrame
		
		/**
		 * Constructor to set the country linked list that is going to paint
		 * @param selectedList
		 */
		public SecondFrame(LinkedList<Country> selectedList){	
			final int FRAME_WIDTH = 800; // the second frame height
			final int FRAME_HEIGHT = 600;// the second frame width
			
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

			// add MouseMotionListener to allow user hover over the dots in the graph view panel
			myPlots.addMouseMotionListener(new MouseHoverOverData(secondFrame, myPlots));
			
			
			// Add a "Help" button to give user instructions how to compare countries
			JButton bt = new JButton("Help");
			bt.setBounds(200, 200, 25, 40);
			
			// add actionlistener to the button to show the instruction dialog
			bt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog (null, "Click countries you would like to see in the legend section\nCountries arrange in alphabetical order\n (Single click to select country, double click to deselect)", "How to get total subscription years and compare", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
			myPlots.add(bt); // add button to the graph panel
			secondFrame.add(myPlots); // add the graph panel to the second frame
			
			// Draw the legend panel
			LegendPanel legendPanel = new LegendPanel(myPlots.getColor(),selectedCountries); 
			legendPanel.setBorder(new BevelBorder(BevelBorder.RAISED)); // 	Set bevel border for the legend pane
			legendPanel.add(new JLabel("Legend")); // Add label to the legend panel
			
			// Construct a scroll panel and add legendPanel to the scroll panel as a component
			JScrollPane scrollLegend = new JScrollPane(legendPanel);
			scrollLegend.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Set vertical scroll bar policy
			scrollLegend.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// Set horizontal scroll bar policy
			scrollLegend.setPreferredSize(new Dimension(100,580)); // Set preferred scroll panel dimension
			legendPanel.setPreferredSize(new Dimension(120,myPlots.getCount() * 100 + 100)); // Set preferred original panel dimension
			scrollLegend.setViewportView(legendPanel);
			
			// add MouseMotionListener to allow user both click the data and hover over the data
			legendPanel.addMouseMotionListener(new MouseHoverOverCountry(secondFrame, selectedCountries, myPlots));
			legendPanel.addMouseListener(new MouseClickCountry(secondFrame, selectedCountries, myPlots));
			// add the legend panel to your frame
			secondFrame.add(scrollLegend);
			
			
			secondFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			secondFrame.setResizable(false);
			secondFrame.setVisible(true);	
		}
	}
	
	/**
	 * Uses a CSV to parse a CSV file.
	 * Adds the data for each country to an array of Country objects.
	 * Adds a random selection of countries to a generic LinkedList object.
	 * Draws the list of countries to a JFrame.
	 * @param args
	 */
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
