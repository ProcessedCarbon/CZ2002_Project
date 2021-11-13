package CZ2002_Assignment;

import java.util.Scanner; 

/**
* Entity Class for Customer Object
* Customer contains information on customer and the table they are assigned to
*/
public class Customer {
	private String Name, Gender;
	private int Contact; 
	private boolean Membership;
	private int tableNo; 
	
	/**
	* Constructor of Customer Class
	* @param name customer's name
	* @param gender customer's gender
	* @param contact customer's contact number
	* @param membership customer's membership status
	* @param tableNo table number that customer is assigned to
	*/
	public Customer(String name, String gender, int contact, boolean membership, int tableNo) { // TODO: int gender -> String Gender
		super();
		this.Name = name;
		this.Gender = gender;
		this.Contact = contact;
		this.Membership = membership;
		this.tableNo = tableNo;
	}

	/**
	* Allows user to input information to create a Customer object
	*/
	public void CreateCustomer() 
	{
		int tempInt;
		String tempStr;
		Boolean tempBool;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Name of Customer: ");
		tempStr = sc.nextLine();
		setName(tempStr);
		
		System.out.println("Customer Gender: ");
		tempStr = sc.nextLine();
		setGender(tempStr);
		
		System.out.println("Customer Contact: ");
		tempInt = sc.nextInt();
		setContact(tempInt);
		
		System.out.println("Is Customer a Member? ");
		tempBool = sc.nextBoolean();
		setMembership(tempBool);
		
		sc.nextLine();
	}
	/**
	* Returns the name of the Customer
	* @return name of the customer
	*/
	public String getName() {
		return Name;
	}
	/**
	* Sets the name of the customer to the input
	* @param name name of Customer 
	*/
	public void setName(String name) {
		Name = name;
	}
	/**
	* Returns the gender of the Customer
	* @return returns customer gender
	*/
	public String getGender() {	
		return Gender;
	}
	/**
	* Sets the gender of the customer to the input
	* @param gender gender of Customer 
	*/
	public void setGender(String gender) {  
		Gender = gender;
	}
	/**
	* Returns the contact number of the Customer
	* @return Contact returns customer contact
	*/
	public int getContact() {
		return Contact;
	}
	/**
	* Sets the contact number of the customer to the input
	* @param contact contact number of Customer 
	*/
	public void setContact(int contact) {
		Contact = contact;
	}
	/**
	* Returns the membership status of the Customer
	* @return returns if the customer is a member
	*/
	public boolean isMembership() {
		return Membership;
	}
	/**
	* Sets the membership status of the customer to the input
	* @param membership membership status of Customer 
	*/
	public void setMembership(boolean membership) {
		Membership = membership;
	}
	/**
	* Sets the table number the customer is assigned
	* @param tableIndex table number Customer is assigned to
	*/
	public void setTableNo(int tableIndex) {
		tableNo = tableIndex;
	}
	/**
	* Returns the table number the Customer is assigned to
	* @return returns the table number that the customer is seated at
	*/
	public int getTableNo() {
		return tableNo;
	}
	
}

