package cellularData;

import java.util.*;
import java.io.*;

/**
 * Create a java class CSVReader which split the file read from TestCSVReader
 * Use Scanner and File method to read the file by each row at one time
 * Split the data in each row and store the year numbers, country name and subscriptions seperately
 * 
 * @author yuxuanli, Junliu Zhang
 */

// Create CSVReader class
public class CSVReader { 

	private String[] countryNames; 	// Declare 1D array to save corresponding country names and years
	private int[] yearLabels;
	private double[][] cellularDataTable; // Declare 2D array to store subscription numbers
	private int numberOfCountries;

	/**
	 * Construct a CSVReader object to read the file
	 * @param filePath		Read the File
	 */
	public CSVReader(String filePath){
		
		/**
		 * throw an exception
		 */
		try { 
			
			Scanner scanner = new Scanner(new File(filePath));// Read files
		
			int count = -1;
			
			while (scanner.hasNext()) { 	// Test whether the next line can be read
				count ++;
				String s1 = scanner.nextLine();
				String[] temp = s1.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Skip splitting token with comma inside the quotes 
				
				if (count == 1) { // Read the second line of the file
					this.numberOfCountries = Integer.parseInt(temp[1]);
				} 
				else if (count == 2) { // Read the third line of the file
					this.yearLabels = new int[temp.length - 1];
					for (int i = 1; i < temp.length; i++) { //assign year number of the array 
						int year = Integer.parseInt(temp[i]);
						this.yearLabels[i - 1] = year;
					}
					this.countryNames = new String[this.numberOfCountries];
					this.cellularDataTable = new double[this.numberOfCountries][this.yearLabels.length];
				}
				else if (count >= 3) { // Read the fourth line and lines after that
					for (int i = 1; i < temp.length; i++) {
						double subscription = Double.parseDouble(temp[i]);
						this.cellularDataTable[count - 3][i-1] = subscription;
						this.countryNames[count - 3] = "";
						this.countryNames[count - 3] = temp[0];
					}
				}
			}
			scanner.close();
		} 
		
		/**
		 * Catch the FileNotFoundException
		 */
		catch (FileNotFoundException ex) { 
			ex.printStackTrace();
		}
		/**
		 * Catch the ArrayIndexOutOfOoundsException
		 */
		catch (ArrayIndexOutOfBoundsException ex) { 
			System.out.print("ERROR! The array out of bounds with " + ex.getMessage());
		}
	}
	
	/**
	 * Accessor method returns the country names
	 * @return	    the country name
	 */
	public String[] getCountryNames(){
		return this.countryNames;
	}
	
	/**
	 * Accessor method returns the year labels
	 * @return		the year labels
	 */
	public int[] getYearLabels(){
		return this.yearLabels;
	}
	
	/**
	 * Accessor method returns the subscriptions data
	 * @return		the data table from cellular.csv
	 */
	public double[][] getParsedTable(){
		return this.cellularDataTable;
	}
	
	/**
	 * Accessor method returns the total number of years
	 * @return		the length of the years
	 */
	public int getNumberOfYears(){
		return this.yearLabels.length;
	}
}

