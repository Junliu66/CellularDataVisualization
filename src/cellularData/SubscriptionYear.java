package cellularData;

/**
 * Create a SubscriptionYear class which store the specific year and corresponding subscription
 * Get and set the year and subscriptions
 * @author yuxuanli Junliu Zhang
 *
 */
public class SubscriptionYear {
	private int year;
	private double subscriptions;
	
	/**
	 * Construct a SubscriptionYear class to pass the value year and subscriptions
	 * @param year			specific year
	 * @param subscriptions		corresponding subscription
	 */
	public SubscriptionYear(int year, double subscriptions){
		this.year = year;
		this.subscriptions = subscriptions;
	}
	
	/**
	 * Accessor method returns the value of year
	 * @return		the specific year
	 */
	public int getYear(){
		return this.year;
	}
	
	/**
	 * Mutator method to set a value of year
	 * @param year		the specific year
	 */
	public void setYear(int year){
		this.year = year;
	}
	
	/**
	 * Accessor method returns the value of subscription
	 * @return		the specific subscription
	 */
	public double getSubscriptions(){
		return this.subscriptions;
	}
	
	/**
	 * Mutator method to set a value of subscription
	 * @param subscriptions		the specific subscription
	 */
	public void setSubscriptions(double subscriptions){
		this.subscriptions = subscriptions;
	}
	
	/**
	 * A string representation to:
	 * present the subscription
	 */
	public String toString(){
		return String.valueOf(this.subscriptions);
	}
	
	/**
	 * Compare the name of the Object obj with the country name in the linked list
	 * @param obj		SubscriptionYear object
	 * @return			true if the data in the SubscriptionYear element are same
	 */
	public boolean equals(SubscriptionYear obj){
		return (this.year == obj.getYear() && this.subscriptions == obj.getSubscriptions()) ;
	}
}
