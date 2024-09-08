import java.util.Scanner;
import java.util.HashMap;

/**
 * USMap Assignment
 *
 * @author Katie Wang
 * @since September 4th, 2024
 * 
 *        Displays a screen with all the cities in the United States as points.
 *        The red points represent the top 10 biggest cities. The blue points
 *        represent the rest of the biggest cities. Gray points are cities
 *        without population data.
 */

public class USMap {
	public static void main(String[] args) {

		// Setting up the display of the US map
		USMap newMap = new USMap();
		newMap.setupCanvas();

		// Creating Hashmap to store contents of bigCities.txt
		HashMap<String, CityData> bigCityRecords = newMap.getBigCitiesHashmap();

		// Read in cities.txt
		Scanner c = FileUtils.openToRead("cities.txt");

		// Loop through cities data line by line
		while (c.hasNextLine()) {
			String line = c.nextLine();

			int firstSpace = line.indexOf(' ');
			int secondSpace = line.indexOf(' ', firstSpace + 1);

			// Save the latitude, longitude as well as the city and state
			String latitudeString = line.substring(0, firstSpace);
			String longitudeString = line.substring(firstSpace + 1, secondSpace);
			String cityKey = line.substring(secondSpace + 1);

			double latitude = Double.parseDouble(latitudeString);
			double longitude = Double.parseDouble(longitudeString);

			/* Seeing if the city and state match up with a corresponding key in the
			    bigCityRecords hashmap */
			CityData matchedCityData = bigCityRecords.get(cityKey);

			/* If there is a corresponding bigCities key, then get the population size and
			    the city size ranking */
			if (matchedCityData != null) {
				int rank = matchedCityData.rank;
				int population = matchedCityData.population;

				// Calculating size of the point based on population
				double citySize = 0.6 * (Math.sqrt(population) / 18500);
				StdDraw.setPenRadius(citySize);

				if (rank <= 10) {
					/* If the city size ranking is less than or equal to 10, then the point color
					   will be red */
					StdDraw.setPenColor(StdDraw.RED);
				} else {
					/* If the city size ranking is greater than 10, than the point color will be
					   blue */
					StdDraw.setPenColor(StdDraw.BLUE);
				}

				// Plotting the point
				StdDraw.point(longitude, latitude);

			} else { /* If there is no corresponding key, set the point color to grey with a size of
						0.006 */
				StdDraw.setPenRadius(0.006);
				StdDraw.setPenColor(StdDraw.GRAY);
				// Plotting the point
				StdDraw.point(longitude, latitude);
			}

		}

	}

	/**
	 * Creates a Hashmap to store values within bigCities.text
	 * 
	 * @return the bigCityRecords hashmap
	 */
	public HashMap<String, CityData> getBigCitiesHashmap() {
		
		HashMap<String, CityData> bigCityRecords = new HashMap<>();

		// Read in bigcities.txt
		Scanner bc = FileUtils.openToRead("bigCities.txt");
		
		// Loop through big cities data line by line
		while (bc.hasNextLine()) {
			String line = bc.nextLine();

			int firstComma = line.indexOf(',');

			String first = line.substring(0, firstComma).strip();
			String second = line.substring(firstComma + 1).strip();

			// Save the rank (city size ranking) and city
			int firstSpace = first.indexOf(' ');
			int rank = Integer.parseInt(first.substring(0, firstSpace));
			String city = first.substring(firstSpace + 1);

			// Save the state and population size
			int secondSpace = second.indexOf(' ');
			String state = second.substring(0, secondSpace);
			int pop = Integer.parseInt(second.substring(secondSpace + 1));

			// Create a key corresponding to the city and state
			String key = city + " , " + state;

			/* Put the key and value into the hashmap (value is the city rank and
			   population) */
			bigCityRecords.put(key, new CityData(rank, pop));

		}
		bc.close();

		return bigCityRecords;
	}

	// Set up the canvas size and scale
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}

}

class CityData {
	int rank;
	int population;

	/**
	 * Constructor of CityData
	 * 
	 * @param rank       The city size rank
	 * @param population The population size
	 */
	public CityData(int rank, int population) {
		this.rank = rank;
		this.population = population;
	}
}
