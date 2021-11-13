package CZ2002_Assignment;

import java.util.Scanner;
import java.time.*;

/**
 * Reservation class for reservations made in the restaurant
 * Contains attributes and methods relevant to a reservation made
 */
public class Reservation {

	/**
	* The date of reservation
	*/
	private String date;
	
	LocalTime time;
	
	/**
	* Number of customers who reserved seats
	*/
	private int pax;
	
	/**
	* The customer who made the reservation
	*/
	private Customer customer;
	
	/**
	* The table number allocated to the reservation
	*/
	private int tableNo;
	
	/**
	* Class constructor for Reservation 
	*/
	public Reservation(String date, LocalTime time, int pax, Customer customer, int tableNo)
	{
		this.date = date;
		this.time = time;
		this.pax = pax;
		this.customer = customer;
		this.tableNo = tableNo;
	}
	
	public String getReservationDate()
	{
		return date;
	}
	public void setReservationDate(String date)
	{
		this.date = date;
	}
	
	public LocalTime getReservationTime()
	{
		return time;
	}
	public void setReservationTime(LocalTime time)
	{
		this.time = time;
	}
	
	public int getReservationPax()
	{
		return pax;
	}
	public void setReservationPax(int pax)
	{
		this.pax = pax;
	}
	
	public Customer getReservationCustomer()
	{
		return customer;
	}
	public void setReservationCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	public int getReservationTable()
	{
		return tableNo;
	}
	public void setReservationTable(int tableNo)
	{
		this.tableNo = tableNo;
	}
	
	/**
	* Method which allows the user to create a reservation
	* User inputs number of pax and date of reservation
	*/
	public void createReservation()
	{
		Scanner sc = new Scanner(System.in);
		
		int tempIntPax;
		String tempStringDate;
		
		System.out.println("Number of Pax (including customer): ");
		tempIntPax = sc.nextInt();
		this.setReservationPax(tempIntPax);
		sc.nextLine(); // Buffer
		System.out.println("Date of reservation: ");
		tempStringDate = sc.nextLine();
		this.setReservationDate(tempStringDate);
	}
}
