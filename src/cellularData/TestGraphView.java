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
 *  *  Tests the GraphView class by creating an object of type GraphView and adding components to it.
 *  Creates one container of type JFrame and adds an object of type GraphView.
 * @author Foothill College, Yuxuan Li, Junliu Zhang
 *
 */
public class TestGraphView 
{
	protected final int FRAME_WIDTH = 800;
	protected final int FRAME_HEIGHT = 600;
	int requestedSize;

	public TestGraphView(){
		
	}

	/**
	 * Creates a generic linked list. Then based on the user's input,
	 * adds a random number of countries to the list.
	 * @param allCountries      An array of Country objects
	 */
	private LinkedList<Country> buildCountryList(Country [] allCountries)
	{	
		
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < allCountries.length; i++)
		{
				selectedCountries.add(allCountries[i]);
		}
		System.out.println(allCountries.length);
		return selectedCountries;
	}


	/**
	 * Initializes the GUI with two JPanels and populates them.
	 * One panel draws the data points, the second draws the legend.
	 * @param selectedCountries      A randomly selected list of countries.
	 *
	 * Note: You may add as many panels as you see fit.
	 */
	private void initializeGui(LinkedList<Country> selectedCountries)
	{

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

	/**
	 * Uses a CSV to parse a CSV file.
	 * Adds the data for each country to an array of Country objects.
	 * Adds a random selection of countries to a generic LinkedList object.
	 * Draws the list of countries to a JFrame.
	 */
	public static void main(String[] args) 
	{		
		//final String FILENAME = "resources/cellular_short_oneDecade.csv";	// test file with shorter number of countries and subscription years
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
		TestGraphView application = new TestGraphView();

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
