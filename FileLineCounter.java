import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Arash
 *
 */
public class FileLineCounter {

	// attribute
	private int counter;

	// constructor
	public FileLineCounter() {
		counter = 0;
	}

	/**
	 * @param name
	 * @return counter
	 * @throws FileNotFoundException
	 */
	public int getLinesCounted(String name) throws FileNotFoundException {
		Scanner lineCounter = new Scanner(new File(name));
		while (lineCounter.hasNextLine()) {
			lineCounter.nextLine();
			counter++;
		}
		lineCounter.close();
		return counter;
	}
}
