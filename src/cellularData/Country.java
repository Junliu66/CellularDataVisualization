package cellularData;

import java.util.Iterator;
/**
 * One object of this class represents a list of type SubscriptionYear
 * containing year and subscriptions for a country. 
 * There are five methods in this class.
 * The constructor take name as a parameter and create a temporary country object.
 * The second method create a new SubscriptionYear object and add it to the list. 
 * The third method return the name of the country.
 * The fourth method return the number of subscriptions of a period.
 * The fifth method return the subscriptions of a country as a string.
 * 
 * @author zhangJunliu Yuxuan Li
 *
 */
public class Country{
	private String name;
	LinkedList<SubscriptionYear> subscriptions;
	private int minYear;
	private int maxYear;
	
	/**
	 * The constructor only takes name as a parameter and create a temporary
	 * country object.
	 * @param name       the name of the country
	 */
	public Country(String name) {
		this.name = name; 
		this.subscriptions = new LinkedList<SubscriptionYear>();
		minYear = 9999;
		maxYear = 0;
	}
	
	/**
	 * This method creates a new SubscriptionYear object and add to list of subscriptions 
	 * @param year            represents one specific year of subscriptions 
	 * @param subscription    subscriptions of that year   
	 */
	public void addSubscriptionYear(int year, double subscription) {
		SubscriptionYear data = new SubscriptionYear(year, subscription);
		subscriptions.add(data);
		minYear = subscriptions.getIndex(0).getYear();
		maxYear = year;
	}
	
	/**
	 * This method returns the name of the country.
	 * @return      the name of the country
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method returns the number of subscriptions of a period. 
	 * throw the exception and print out error message
     * if requested starting year less than starting year 
     *                       or ending requested year greater than ending year 
	 * @param startYear      the requested starting year of subscription
	 * @param endYear        the requested ending year of subscription
	 * @return               the sum of cellular data subscription of a country  
     *                       
	 */
	public double getNumSubscriptionsForPeriod(int startYear, int endYear) throws IllegalArgumentException{
		Iterator<SubscriptionYear> iterator = subscriptions.iterator();
		double numSubscriptions = 0;	
		int startingIndex = startYear - minYear;
		int period = endYear - startYear + 1;
		int endingIndex = startingIndex + period;
		
		if (startYear < minYear) {			
			throw new IllegalArgumentException("Illegal Argument Request of startYear " + startYear + 
					", Valid period for "+ name + " is " + minYear + " and " + maxYear + "\n");
		}
		else if (endYear > maxYear) {
			throw new IllegalArgumentException("Illegal Argument Request of endYear " + endYear + 
					", Valid period for "+ name + " is " + minYear + " and " + maxYear + "\n");
		}
		else {
			int i = 0;
		    while (iterator.hasNext()) {
		    	if (i < startingIndex) {
		    		iterator.next().getSubscriptions();
		    		i++;
		    		continue;
		    	}
		    	else if (i < endingIndex) {
			    numSubscriptions += iterator.next().getSubscriptions();
			    i++;			    
		    	}
		    	else {
		    		iterator.next().getSubscriptions();
		    	}
		    }
		    return numSubscriptions;
		}
	}
	
	/**
	 * Checks if the other node has the same name as this one.
	 * @param other         the other node of country object
	 */
	public boolean equals(Object other) {
		if (other instanceof Country) {                            
	            Country current = (Country)other;
	            if (current.name.equals(this.name))
	                return true;        
	        }    
	        return false;
	}

	
	/**
	 * return a string containing subscriptions of one country
	 */
	public String toString() {
		String representation = name + "\t";
		for (SubscriptionYear object: subscriptions) {
		    representation += String.format(" %.2f", object.getSubscriptions()) + "\t";
		}
		return representation;	
	}		
}
