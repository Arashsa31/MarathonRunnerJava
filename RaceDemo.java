import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Arash
 *
 */
public class RaceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String filePath = "src/race.txt";
		FileLineCounter fileCounterObject = new FileLineCounter();
		Runner runnerArray = null;

		try {
			Scanner inputStream = new Scanner(new File(filePath));
			inputStream.nextLine();
			runnerArray = new Runner(fileCounterObject.getLinesCounted(filePath) - 1);

			while (inputStream.hasNextLine()) {
				String[] tempArray = inputStream.nextLine().split(",");
				runnerArray.setRunner(tempArray[1], tempArray[0], tempArray[2]);
			}
			runnerArray.sortTime();
			System.out.println(runnerArray);
			System.out.println("Racer's are loaded into the system.\n");

		} catch (FileNotFoundException e) {
			System.out.println("Error opening file " + filePath);
			System.exit(0);
		} catch (RaceException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		Scanner keyboard = new Scanner(System.in);
		int raceNumber = 0;
		while (true) {
			System.out.println("Enter a racer number to get their:" + "\n" + "a. Overall Finish Place" + "\n"
					+ "b. Split times" + "\n" + "c. Overall race time and race pace" + "\n"
					+ "or press any non-integer key to exit." + "\n" + "The racer numbers available are: ");

			System.out.println(runnerArray.getRunnerNumber() + "\n");
			System.out.print("Waiting for a racer number... ");

			try {
				raceNumber = keyboard.nextInt();

				runnerArray.getCheater(raceNumber);
				
				System.out.print(runnerArray.getPlayerInfo(raceNumber));
				System.out.println(runnerArray.getPlaceNumber(raceNumber));
				System.out.println();
				
			} catch (RaceException e) {
				System.out.println(e.getMessage());
				System.out.println();
				continue;
			} catch (Exception e) {

				System.out.println(e.getMessage());
				System.out.println("Goodbye.");
				System.exit(0);
			}

		}
}

}
