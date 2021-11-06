package CZ2002_Assignment;

import java.util.Scanner;

public class Reservation {

	private String date;
	private String time;
	private int pax;
	private Customer customer;
	private int tableNo;
	
	public Reservation(String date, String time, int pax, Customer customer, int tableNo)
	{
		this.date = date;
		this.time = time;
		this.pax = pax;
		this.customer = customer;
		this.tableNo = tableNo;
	}
	
	//public Time_Out()
	
	public String getReservationDate()
	{
		return date;
	}
	public void setReservationDate(String date)
	{
		this.date = date;
	}
	
	public String getReservationTime()
	{
		return time;
	}
	public void setReservationTime(String time)
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
	public void createReservation()
	{
		Scanner sc = new Scanner(System.in);
		
		int tempIntPax;
		String tempStringCustomerName;
		String tempStringDate;
		String tempStringTime;
		
		System.out.println("Number of Pax (including customer): ");
		tempIntPax = sc.nextInt();
		this.setReservationPax(tempIntPax);
		sc.nextLine(); // Buffer
		System.out.println("Date of reservation: ");
		tempStringDate = sc.nextLine();
		this.setReservationDate(tempStringDate);
		System.out.println("Time of reservation: ");
		tempStringTime = sc.nextLine();
		this.setReservationTime(tempStringTime);
	}
}
