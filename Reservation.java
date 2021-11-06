package cz2002;
import java.util.Scanner;

public class Reservation {

	private String date;
	private float time;
	private int pax;
	private Customer customer;
	private int tableNo;
	
	public Reservation(String date, float time, int pax, Customer customer, int tableNo)
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
	
	public float getReservationTime()
	{
		return time;
	}
	public void setReservationTime(float time)
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
	
	public getReservationCustomer()
	{
		return customer;
	}
	public setReservationCustomer(Customer customer)
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
	
	public void createReservation(ArrayList<Customer> customerList)
	{
		int tempIntPax;
		String tempStringCustomerName;
		String tempStringDate;
		float tempFloatTime;
		
		System.out.println("Creating new reservation: ");
		System.out.println("Name of customer: ");
		String tempStringCustomerName = sc.nextLine(); //customer will have error as reservation attribute
		//for customer is not changed to string yet
		rsv.setReservationCustomer(tempStringCustomerName);
		
		rsv.tableNo = -1;
		
		System.out.println("Date of reservation: ");
		tempStringDate = sc.nextLine();
		rsv.setReservationDate(tempStringDate);
		
		System.out.println("Time of reservation: ");
		tempFloatTime = sc.nextFloat();
		rsv.setReservationTime(tempFloatTime);
		
		System.out.println("Number of people: ");
		tempIntPax = sc.nextInt();
		rsv.setReservationTime(tempIntPax);
		
	}
}
