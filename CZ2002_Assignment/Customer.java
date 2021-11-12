package CZ2002_Assignment;

import java.util.Scanner; // TODO: Add this

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
	* @param membership customer's memebership status
	* @param tableNo table number that customer is assigned to
	*/
	public Customer(String name, String gender, int contact, boolean membership, int tableNo) {
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
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {  
		Gender = gender;
	}
	public int getContact() {
		return Contact;
	}
	public void setContact(int contact) {
		Contact = contact;
	}
	public boolean isMembership() {
		return Membership;
	}
	public void setMembership(boolean membership) {
		Membership = membership;
	}
	public void setTableNo(int tableIndex) {
		tableNo = tableIndex;
	}
	public int getTableNo() {
		return tableNo;
	}
	
}

