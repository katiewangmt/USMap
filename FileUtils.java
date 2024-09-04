import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
// no java.io.* <- tell you where file comes from 

/**
 *	File Utilities for reading and writing
 *
 *	@author Katie Wang 
 *	@since August 23rd, 2024 
 */ 
 public class FileUtils {
	 
	 /**
	  *  Opens a file to read using the Scanner class.
	  * @param fileName 	name of the file to open
	  * @return 			the Scanner object to the file 
	  */
	 public static java.util.Scanner openToRead(String fileName) { //only use static with Utilities <- otherwise will get points taken off 
		java.util.Scanner input = null; // need to initialize as null 
		
		try {
			input = new java.util.Scanner(new java.io.File(fileName)); 
		}
		catch (java.io.FileNotFoundException e){ // catch exceptions as e 
			System.err.println("ERROR: Cannot open " + fileName + 
							" for reading.");
			System.exit(72);								
		}
		return input;
	 }
	 
	 /**
	  * Opens a file to write using the PrintWriter class.
	  * @param fileName 	name of the file open
	  * @return 			the PrintWriter object to the file
	  */
	 public static PrintWriter openToWrite(String fileName) {
		 PrintWriter output = null;
		 
		 try {
			 output = new PrintWriter(new File(fileName));
		 }
		 catch (FileNotFoundException e) {
			 System.err.println("ERROR: Cannot open " + fileName + 
							 " for writing.");
			 System.exit(73);
		 }
		 return output;
	 }
}
