project folder:
team03-project09/


Brief description of submitted files:

src/CSVReader.java
    - Read data from the "resources/cellular.csv" file.
    - get name of country, number of years, year labels etc and return values.
    
src/SubscriptionYear.java
    - One object of class stores single year and subscriptions of a country.
    - Get and set year and subscriptions.
        
src/Country.java
    - One object of class stores representations of a country.
    - Get name of country and calculate subscriptions in a period for a country.
    
src/Node.java
    - One object of class stores the node of a object.
    - Get and set node of object.
    
src/LinkedList.java
    - One object of class stores the list of objects.
    - Add, search and insert node in the list. 

src/GraphView.java
    - One object of class stores the graph view of Country list. 
    - generate graph panel and map the cellular data of country to the panel.
      
src/ColoredPoint.java
    - One object of class stores the color of one point in the graph.
    - get color and label of country.
    
src/LegendPanel.java
    - One object of class represents the panel to show the visual guide 
      to the graphed countries.
    - generate legend panel and color and label matching the plots on GraphView panel.
    
src/MouseHoverOverData.java
    - Constructe a MouseHoverOverData class that implements mouseMotionListener
    - mouseMoved method to get the coordinates that mouse hover over

src/MouseHoverOverCountry.java
    - Construct a MouseHoverOverCountry class which implements MouseMotionListener 
    - mouseMoved method to activate the hover over action
    
src/MouseClickCountry.java
    - Construct a MouseClickCountry class which implements MouseListener  
    - mouseClicked method get mouse data and decide which country has been chosen

src/MouseClickCountryForFirstFrame.java
    - Construct a MouseClickCountryForFirstFrame class which implements MouseListener
    - mouseClicked method get the coordinates and decide which country has been selected

src/ShowCountryPanelForFirstFrame.java
    - One object of class represents the panel to show the list of all countries
    - Draw a panel with all countries name which have been got from cvs file
        
src/SelectedCountryPanelForFirstFrame.java
    - One object of class represents the panel to show the selected countries
    - Add a panel at the first frame to show the country that has been selected
    
src/TestSelectedCountryandGraphView.java
    - Test the GraphView class.
    - Includes main(), buildCountryList(), initializeGui() and SecondFrame class with 
      secondFrame() method for running the application.

resources/cellular.csv
    - A CSV (Comma Separated Value) file.
    - First row contains the year of cellular data for each country.
    - First column contains the name of each country.
    - Lines 2 to EOF (end of file) contain cellular data for each country.

resources/cellular_short_oneDecade.csv
    - A file containing cellular data of three countries for one decade.

resources/RUN1(a).jpg
resources/RUN1(b).jpg
resources/RUN2.jpg          
    - pictures showing first Frame for selecting countries and second Frame for showing graph 
      from CSVReader, SubscriptionYear, Country, CountryNode, CountryList, 
      GraphView, ColoredPoint and LegendPanel, MouseHoverOverData,
      MouseHoverOverCountry, MouseClickCountry, MouseClickCountryForFirstFrame, 
      ShowCountryPanelForFirstFrame, SelectedCountryPanelForFirstFrame and 
      TestSelectedCountryandGraphView.java.

README.txt
    - Description of submitted files
