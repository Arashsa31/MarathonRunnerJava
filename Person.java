/**
 * @author Arash
 *
 */
public class Person implements Comparable {

	// attribute
	private int personID, checkpoint, time;

	// constructor
	public Person() {
		personID = 0;
		checkpoint = 0;
		time = 0;
	}

	// overloaded constructor
	public Person(int personID, int checkpoint, int time) {
		setPerson(personID);
		setCheckpoint(checkpoint);
		setTime(time);
	}

	public Person(String personID, String checkpoint, String time) throws RaceException {
		setPerson(Integer.parseInt(personID));
		setCheckpoint(Integer.parseInt(checkpoint));

		Time tempTime = new Time(time);
		setTime(tempTime.getHour() * 60 * 60 + tempTime.getMinute() * 60 + tempTime.getSecond());
	}

	// mutators
	/**
	 * @param personID
	 */
	public void setPerson(int personID) {
		this.personID = personID;
	}

	/**
	 * @param checkpoint
	 */
	public void setCheckpoint(int checkpoint) {
		this.checkpoint = checkpoint;
	}

	/**
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}

	// accessor
	/**
	 * @return personID
	 */
	public int getPerson() {
		return personID;
	}

	/**
	 * @return checkpoint
	 */
	public int getCheckpoint() {
		return checkpoint;
	}

	/**
	 * @return time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @return toString person, checkpoint, time
	 */
	public String toString() {
		String tempTime = "";
		try {
			Time tempTimeObj = new Time(getTime());
			tempTime = tempTimeObj.toString();
		} catch (RaceException e) {
		}

		return "Racer: " + getPerson() + "\tCheckpoint: " + getCheckpoint() + "\tTime: " + tempTime;
	}

	@Override
	public int compareTo(Object o) {
		return checkpoint - ((Person) o).checkpoint;
	}
}
