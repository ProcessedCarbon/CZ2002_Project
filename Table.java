package Package;

public class Table {
	private int SeatCapacity;
	private boolean isOccupied, isReserved;
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
