/**
 * @author Arash
 *
 */
public class Time {

	//attribute
	private int hour, minute, second;

	//constructor
	public Time() {
		hour = 0;
		minute = 0;
		second = 0;
	}

	//overloaded constructor
	public Time(int hour, int minute, int second) throws RaceException {
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	
	public Time(int seconds) throws RaceException {
		secondConverter(seconds);
	}

	public Time(String time) throws RaceException {
		timeConverter(time);
	}

	/**
	 * @param time
	 * @throws RaceException
	 */
	public void timeConverter(String time) throws RaceException {
		// 00:00:00 format
		String tempTime = "";
		String tempHour = "";
		String tempMinute = "";
		String tempSecond = "";
		for (int i = 0; i < time.length(); i++) {
			if (time.charAt(i) != ':' && time.charAt(i) != ' ')
				tempTime += time.charAt(i);
		}
		for (int counter = 0; counter < 3; counter++) {
			switch (counter) {
			case 0:
				tempHour = tempTime.charAt(0) + "" + tempTime.charAt(1);
				break;
			case 1:
				tempMinute = tempTime.charAt(2) + "" + tempTime.charAt(3);
				break;
			case 2:
				tempSecond = tempTime.charAt(4) + "" + tempTime.charAt(5);
				break;
			default:
				throw new RaceException("Unable to convert time.");
			}
		}
		setHour(Integer.parseInt(tempHour));
		setMinute(Integer.parseInt(tempMinute));
		setSecond(Integer.parseInt(tempSecond));
	}
	
	public void secondConverter(int second) throws RaceException{
		int tempSecond = second % 60;
		int tempMinute = (second / 60) %60;
		int tempHours = (second/60) / 60;
		
		setSecond(tempSecond);
		setMinute(tempMinute);
		setHour(tempHours);
		
	}

	//mutator
	/**
	 * @param hour
	 * @throws RaceException
	 */
	public void setHour(int hour) throws RaceException {
		if (hour > 23)
			throw new RaceException("Invalid hour.");
		this.hour = hour;
	}

	/**
	 * @param minute
	 * @throws RaceException
	 */
	public void setMinute(int minute) throws RaceException {
		if (minute > 59)
			throw new RaceException("Invalid minute.");
		this.minute = minute;
	}

	/**
	 * @param second
	 * @throws RaceException
	 */
	public void setSecond(int second) throws RaceException {
		if (second > 59)
			throw new RaceException("Invalid seconds.");
		this.second = second;
	}

	//accessor 
	/**
	 * @return hour
	 */ 
	public int getHour() {
		return hour;
	}

	/**
	 * @return minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @return second
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * @return toString hour:minute:second
	 */
	public String toString() {
		return "Time: " + getHour() + ":" + getMinute() + ":" + getSecond();
	}
}
