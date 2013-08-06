import java.util.*;
import java.io.*;

/**
*  readFromFile
*	 reads speed limits from file
*
**/
public class readFromFile {

	private Scanner reader;
	private Scanner abbrevs;

	/**
	*  Constructor
	*	 initializes the scanner object that reads from file
	*
	**/
	public readFromFile() throws FileNotFoundException{
		reader =  new Scanner(new File("C:/Users/Anirudh/Documents/GitHub/speedLimitProject/speedLimits.txt"));	//change these paths to wherever your speedLimits.txt file
		abbrevs = new Scanner(new File("C:/Users/Anirudh/Documents/GitHub/speedLimitProject/abbreviations.txt"));
	}

	/**
	* getSpeedLimit
	*   takes in street name and returns the speed limit for that street if its in the file; otherwise returns 0
	*
	* @param loc - the street name
	* @return String - returns the speed limit of the street in string form
	*
	**/
	public String getSpeedLimit(String loc){
		String[] vals = new String[2];
		String total;						// the array and string that hold the street and speed limit data
		String upperLoc = loc.toUpperCase();

		String abTotal;
		String[] abs = new String[2];  // the array and string that hold the abbreviation data

		while(abbrevs.hasNextLine()){
			abTotal = abbrevs.nextLine();
			abs = abTotal.split(",");
			if(upperLoc.contains(abs[1])){
				upperLoc = upperLoc.replaceAll(abs[1], abs[0]);
				break;
			}
		}

		while(reader.hasNextLine()){
			total = reader.nextLine();
			vals = total.split(",");
			if(vals[0].equalsIgnoreCase(upperLoc)){
				break;
			}
		}
		if(!(vals[0].equalsIgnoreCase(upperLoc))){
			return "0";
		}
		return vals[1];
	}
}