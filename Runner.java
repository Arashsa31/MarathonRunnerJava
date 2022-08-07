import java.util.Arrays;

/**
 * @author Arash
 *
 */
public class Runner {

	// attribute
	private Person[] person;
	private int numberOfPerson;

	// constructor
	public Runner() {
		numberOfPerson = 0;
	}

	// overloaded constructor
	public Runner(int index) {
		person = new Person[index];
		numberOfPerson = 0;
	}

	/**
	 * @param runner
	 * @param checkpoint
	 * @param time
	 * @throws RaceException
	 */
	public void setRunner(String runner, String checkpoint, String time) throws RaceException {
		person[numberOfPerson] = new Person(runner, checkpoint, time);
		numberOfPerson++;
	}

	/**
	 * @return runner ID numbers
	 */
	public String getRunnerNumber() {
		String tempRunner = "";
		for (int i = 0; i < person.length; i++) {
			if (person[i].getCheckpoint() == 0) {
				if (i != 0)
					tempRunner += ", ";
				tempRunner += person[i].getPerson();
			}
		}
		return tempRunner;
	}

	/**
	 * @param racer
	 * @return racer checkpoints and split times
	 */
	public String getPlayerInfo(int racer) {
		String tempRacer = "";
		for (int i = 0; i < this.person.length; i++) {
			if (this.person[i].getPerson() == racer) {
				tempRacer += this.person[i].toString() + "\n";
			}
		}
		if (tempRacer.length() == 0)
			return "Unable to find racer.";
		else
			return tempRacer;
	}

	/**
	 * @param racerNumber
	 * @return racer finish place
	 * @throws RaceException
	 */
	public String getPlaceNumber(int racerNumber) throws RaceException {
		int tempFinishPlace = 1;
		String[] numberOfRacers = getRunnerNumber().split(", ");

		int[] tempRacer = new int[numberOfRacers.length];
		for (int i = 0; i < tempRacer.length; i++)
			tempRacer[i] = paceTime(Integer.parseInt(numberOfRacers[i]));

		// sorts my runners
		int tempPaceTime;
		String tempRunner;
		// loops through every element
		for (int j = 0; j < tempRacer.length - 1; j++) {
			// sorts the lowest number to the end of the array
			for (int k = 0; k < tempRacer.length - 1; k++) {
				if (tempRacer[k] > tempRacer[k + 1]) {
					tempPaceTime = tempRacer[k + 1];
					tempRunner = numberOfRacers[k + 1];

					tempRacer[k + 1] = tempRacer[k];
					numberOfRacers[k + 1] = numberOfRacers[k];

					tempRacer[k] = tempPaceTime;
					numberOfRacers[k] = tempRunner;
				}
			}
		}

		for (int z = 0; z < numberOfRacers.length; z++) {
			if (Integer.parseInt(numberOfRacers[z]) == racerNumber)
				return "They finished in place number: " + tempFinishPlace;
			else
				tempFinishPlace++;
		}

		return "No person found."; // if no runners found
	}

	/**
	 * @param racerNumber
	 * @return total pace time for the racer
	 */
	private int paceTime(int racerNumber) {
		int tempCheckpointStart = 0;
		int tempCheckpointEnd = 0;
		int tempPaceTime = 0;
		for (int i = 0; i < person.length; i++) {
			if (person[i].getPerson() == racerNumber)
				if (person[i].getCheckpoint() == 0) {
					tempCheckpointStart = i;
					tempCheckpointEnd = i;
				} else if (tempCheckpointEnd < person.length)
					tempCheckpointEnd = i;
		}
		tempPaceTime = person[tempCheckpointEnd].getTime() - person[tempCheckpointStart].getTime();
		return tempPaceTime;
	}

	public void getCheater(int racerNumber) throws RaceException {

		int tempCheckpointStart = 0;
		int tempCheckpointEnd = 0;
		int tempSplitTime = 0;
		for (int i = 0; i < person.length; i++) {
			if (person[i].getPerson() == racerNumber) {
				if (person[i].getCheckpoint() == 0) {
					tempCheckpointStart = i;
					tempCheckpointEnd = i;
				} else if (tempCheckpointEnd < person.length)
					tempCheckpointEnd = i;
				if (tempCheckpointStart != tempCheckpointEnd) {
					// racer missed a checkpoint
					if ((person[tempCheckpointStart].getCheckpoint() + 1) != person[tempCheckpointEnd]
							.getCheckpoint()) {
						throw new RaceException(
								"Racer " + racerNumber + " missed checkpoint " + (person[tempCheckpointStart].getCheckpoint() + 1));
					}
					// racer is running faster than 4:30 per mile
					else if (((person[tempCheckpointEnd].getTime() - person[tempCheckpointStart].getTime()) / 7) < 270)
						throw new RaceException("Racer " + racerNumber + " is too fast");
				}
				tempCheckpointStart = tempCheckpointEnd;
			}
		}
	}

//	int tempLastCheckpoint = person[person.length - 1].getCheckpoint() + 1;
//	Person[] tempArray = new Person[tempLastCheckpoint];
//	int i = 0;
//
//	for(
//	int x = 0;x<person.length;x++)
//	{
//		if (person[x].getPerson() == racerNumber) {
//			tempArray[i] = person[x];
//			i++;
//		}
//	}
//
//	for(
//	int y = 0;y<tempArray.length;y++)
//	{
//		if (tempArray[y].getCheckpoint() == y) {
//			if (tempArray.length - 1 == y)
//				break; // ends check when reached end of array
//			int avgTime = tempArray[y + 1].getTime() - tempArray[y].getTime();
//			avgTime /= 7; // each checkpoint is 7 miles
//			if (avgTime < 430) // racer is running faster than 4:30 per mile
//				throw new RaceException("Racer " + racerNumber + " is too fast");
//		} else
//	throw new RaceException("Racer "+racerNumber+" missed checkpoint "+y);

	/**
	 * sorts by time
	 */
	public void sortTime() {
		Arrays.sort(person);
	}

	/**
	 * @return toString person[]
	 */
	public String toString() {
		String racerInfo = "";
		for (int i = 0; i < person.length; i++) {
			racerInfo += person[i] + "\n";
		}
		return racerInfo;
	}
}
