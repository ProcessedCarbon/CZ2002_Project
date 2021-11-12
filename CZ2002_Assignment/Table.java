package CZ2002_Assignment;

/**
* Entity Class for Tables in the Restaurant
* Tables store data for Seat Capacity, Occupation and Reservation Status
*/
public class Table {
	private int SeatCapacity;
	private boolean isOccupied, isReserved;
	/**
	* Constructor for Table Class
	* @param seatCapacity number of maximum seats the Table can contain
	* @param isOccupied boolean to indicate whether Table is occupied
	* @param isReserved boolean to indicate whether Table is reserved
	*/
	public Table(int seatCapacity, boolean isOccupied, boolean isReserved) {
		super();
		this.SeatCapacity = seatCapacity;
		this.isOccupied = isOccupied;
		this.isReserved = isReserved;
	}
	public int getSeatCapacity() {
		return SeatCapacity;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public boolean isReserved() {
		return isReserved;
	}
	public void setSeatCapacity(int seatCapacity) {
		this.SeatCapacity = seatCapacity;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	
}

