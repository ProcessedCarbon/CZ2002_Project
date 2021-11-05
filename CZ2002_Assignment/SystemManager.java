package CZ2002_Assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class SystemManager
{
	
	static ArrayList<Table> tableList = new ArrayList<Table>();
 	static ArrayList<Order> orderList = new ArrayList<Order>();
	static ArrayList<Order> paidOrderList = new ArrayList<Order>();
	static ArrayList<Customer> customerList = new ArrayList<Customer>();
	static ArrayList<Staff> staffList = new ArrayList<Staff>();
	static ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	 
	// NOTES:
	// Comment the things that are uncommented
	// Uncomment the things that are commented 
	// 1st Create a customer -> Create a Reservation/OccpuyFreeTable. 
	// DO NOT Create multiple customers than create reservations/occupyFreeTables

	public static void main(String[] args)
	{
		SystemManager sys = new SystemManager();

		int time = 1000;
		int tempInt;
		String tempStr;

		Menu menu = new Menu(); // MENU

		int choice, numOfPax, tableIndex;
		Scanner sc = new Scanner(System.in);

		//////////////////////// DRIVER MENU \\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		// PRE MADE MENU; 		A menu that has pre-initialised menuItems 
		// NON PRE MADE MENU: 	An empty menu

		System.out.println("Do you want to use a pre-made menu?");
		choice = sc.nextInt();

			if(choice == 1)
				menu.LoadBaseMenu();
			
		// Load Pre Made Staff	
		sys.LoadStaff(); 

		sys.DisplaySystemOptions();
		choice = -1;

		// MENU LOOP //////////////////////////////////////////

		do
		{
			choice = sc.nextInt();
			switch(choice)
			{
			case 0: // DISPLAY SYSTEM OPTIONS //
				sys.DisplaySystemOptions();
				break;

				////////////////////////////////
				// MENU - PROMO ////////////////
				////////////////////////////////

			case 1: // MENU ITEM CREATION //
				menu.Create();
				break;

			case 2: // MENU ITEM REMOVAL //
				menu.Remove();
				break;

			case 3: // UPDATE MENU ITEM //
				menu.Update();
				break;

			case 4: // PROMOTION CREATION //
				menu.Create();
				break;

			case 5: // PROMOTION REMOVAL //
				menu.Remove();
				break;

			case 6: // UPDATE PROMOTION OPTIONS //
				menu.Update();
				break;

			//TODO: View Menu Item (SYSTEMMANAGER)
				
				////////////////////////////////
				// CUSTOMER //// ///////////////
				////////////////////////////////

			case 7: // CREATE CUSTOMER //
				Customer cst = new Customer("","",1,true,-1);
				cst.CreateCustomer();
				sys.AppendCustomerToList(cst); 
				// for (int i = 0; i < customerList.size(); i++) System.out.println(customerList.get(i).getName());
				
				break;

				////////////////////////////////
				// ORDER ///////////////////////
				////////////////////////////////

			case 8: // CREATE ORDER // 
				
				// order = Order.createOrder(staffList, menu);  // (Menu menu)
				sc.nextLine();
				System.out.println("Creating New Order");
				System.out.println("Enter Staff Name: ");
				tempStr = sc.nextLine();
				
				for(int i = 0; i < staffList.size(); i++)
				{
					// System.out.println("TempStr: " + tempStr);
					// System.out.println("CurStr: " + staffList.get(i).getName());
					if(tempStr.equals(staffList.get(i).getName())) 
					{
						Order order = new Order(staffList.get(i),"",-1,1);
						order.createOrder(menu);
						sys.AppendOrderToList(order);
						System.out.println("Order Created!");
						break;
					}
				}
				break;

			case 9: // UPDATE ORDER //
				// System.out.println("Enter Table Number (Update Order): ");
				// tableIndex = sc.nextInt();
				// sys.getOrderToUpdate(tableIndex);
				break;

			case 10: // VIEW ORDERS //
				// System.out.println("Enter Table Number (View Order): ");
				// tableIndex = sc.nextInt();
				// sys.ViewOrder(tableIndex);
				break;
				
			case 11: // VIEW ALL ORDERS //
				// sys.ViewAllOrder();
				break;

			case 12: // PRINT ORDER INVOICE //
				// System.out.println("Enter Table Number (View Order Invoice): ");
				// tableIndex = sc.nextInt();
				// sys.ViewOrderInvoice(tableIndex);
				break;

			case 13: // MAKE PAYMEMT //
				
				// System.out.println("Enter Table Number (Making Payment): ");
				// tableIndex = sc.nextInt();
				// sys.MakePayment(tableIndex);
				break;

				////////////////////////////////
				// TABLES //////////////////////
				////////////////////////////////

			case 14: // VIEW TABLE //
				sys.ViewTable();
				break;

				////////////////////////////////
				// RESERVATION /////////////////
				////////////////////////////////

			case 15: // CREATE RESERVATION //
				// rsv = sys.MakeReservation(); 
				// sys.AppendReservationToList(rsv); 	
				break;

			case 16: // REMOVE RESERVATION //
				// System.out.println("Enter Customer Name");
				// String name;
				// name = sc.nextLine();
				//for (int i=0; i < reservationList.size(); i++)
				//{
				//	if(name.equals(reservationList.get(i).getReservationCustomer().getName()))
				//	{
				//		reservationList.remove(i);
				//	}
				//}
				break;

			case 17: // CHECK FOR TIME OUT RESERVATION //
				// sys.CheckForTimeOut(time);
				break;

			case 18: // OCCUPY TABLE - FOR WALK IN CUSTOMERS //
				// IMPORTANT: CREATE A CUSTOMER FIRST THEN CALL THIS FUNCTION
				System.out.println("Enter Customer Name: ");
				tempStr = sc.nextLine();
				for(int i = 0; i < customerList.size(); i++)
				{
					if(tempStr.equals(customerList.get(i).getName()))
					{
						System.out.println("Enter Pax: ");
						numOfPax = sc.nextInt();
						tableIndex = sys.OccupyFreeTable(numOfPax);
						customerList.get(i).setTableNo(tableIndex); 
					}
				}
				break;
				
			case 19: // OCCUPY TABLE - FOR RESERVED CUSTOMERS //
				System.out.println("Enter Customer Name");
				String name;
				name = sc.nextLine();
				sc.nextLine();
				sys.fufilReservation(name);
				break;
				
			case 20: // SET TIME
				System.out.println("Input time: ");
				time = sc.nextInt();
				break;

			case 21: // EXIT PROGRAM //
				System.out.println("Exiting Program...");
				break;

				////////////////////////////////

			}
		} while(choice < 21);
	}
	
	// public void AppendCustomerToList()
	public void AppendCustomerToList(Customer cst) 
	{
		// ADD LOGIC HERE

		customerList.add(cst);

		System.out.println("Customer Appeneded to customerList!");
	}
	
	/*
	public void CreateOrder()
	// public Order CreateOrder()
	{
		// ADD LOGIC HERE
		Staff staff = new Staff("","","",0);
		staff.CreateStaff();
		Order order = new Order(staff,"",0);
		order.CreateOrder();
		sys.AppendOrderToList(order);
							
		//TODO: CreateOrder() -> Order Class

		System.out.println("Order Created!");
	}
	*/
	
	//public void AppendOrderToList()
	public void AppendOrderToList(Order order)
	{
		// ADD LOGIC HERE
		orderList.add(order);

		System.out.println("Order Appeneded to customerList!");
	}

	//public void getOrderToUpdate()
	public void getOrderToUpdate(int tableIndex)
	{
		// ADD LOGIC HERE
		
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				// orderList.get(i).UpdateOrder();
				System.out.println("Order updated.");
				return;
			}
		}
		System.out.println("Order not found.");
		return;
	}
	
	// public void ViewOrder() 
	public void ViewOrder(int tableIndex)
	{
		// ADD LOGIC HERE
		
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				System.out.println("Printing Order...");
				// orderList.get(i).PrintOrder();
				return;
			}
		}
		System.out.println("Order not found.");
		return;
		// Order order = new Order();
		// order = orderList.get(tableIndex-1);
		// order.ViewOrder();					//TODO: ViewOrder() -> Order Class
	}	

	public void ViewAllOrders()
	{		
		System.out.println("View All Orders...");

		// ADD LOGIC HERE

		for (int i = 0; i < orderList.size(); i++)
		{
			System.out.println("Printing Order...");
			// orderList.get(i).PrintOrder();
		}
		return;
	}
	
	// public void ViewOrderInvoice()
	public void ViewOrderInvoice(int tableIndex)
	{
		

		// ADD LOGIC HERE

		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				System.out.println("Viewing Order Invoice...");
				orderList.get(i).printInvoice();
				return;
			}	
		}
	}
	
	//public void MakePayment ()
	public void MakePayment (int tableIndex) // TableIndex
	{
		// ADD LOGIC HERE
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				System.out.println("Paying for Order...");
				paidOrderList.add(orderList.get(i));
				orderList.remove(tableIndex);
				return;
			}	
		}
		System.out.println("Payment Made!");
	}

	// FINDING TABLE //

	// public void FindFreeTable()
	public int FindFreeTable(int numOfPax)
	{
		// ADD LOGIC HERE

		for(int i = 0; i < tableList.size() ; i++)
		{
			if(tableList.get(i).getSeatCapacity() >= numOfPax && !tableList.get(i).isReserved() && !tableList.get(i).isOccupied())
			{
				System.out.println("Table " + i+1 + " is free");
				return i;
		   }
		 }
		
		System.out.println("No tables are free.");
		return -1;

		// System.out.println("Free Table Found!");
	}

	// public void ReserveTable()
	public void ReserveTable(int tableIndex)
	{
		// ADD LOGIC HERE

		tableList.get(tableIndex).setReserved(true); // TODO: setReserved() -> Reservation Class

		System.out.println("Free Table Reserved!");

	}
	
	//public void OccupyTable() 
	public void OccupyTable(int tableIndex)
	{
		// ADD LOGIC HERE
		
		tableList.get(tableIndex).setOccupied(true);
		System.out.println("Table Occupied!");

	}
	
	// public int OccupyFreeTable()	
	public int OccupyFreeTable(int numOfPax)
	{
		// ADD LOGIC HERE
		
		int tableIndex = FindFreeTable(numOfPax);
		
		if(tableIndex != -1)
		{
			 OccupyTable(tableIndex);
			 System.out.println("Free table occupied!");
			 //return tableIndex;
		}
		else
			System.out.println("No Free Tables Found.");
		
		return -1;
	}
	
	public void ViewTable() 
	{
		// ADD LOGIC HERE

		// Table tempTable = new Table();

		// for(int i = 0; i < tablelist.size(); i++)
		// {
		//	tempTable = tableList.get(i);
		//	System.out.println("Table Number " + (i+1) + " Reserved: " + tempTable.isReserved() + " Occupied: " + tempTable.isOccupied);
		// }

		System.out.println("Viewing Tables...");
	}

	// RESERVATION // HARD CODED WARNING ONLY CREATE CUSTOMER -> MAKE RESERVATION -> DO NOT MAKE MORE THAN ONE CUSTOMER

	public void MakeReservation() // TODO
	// public Reservation MakeReservation()
	{
		// ADD LOGIC HERE

		// Reservation rsv = new Reservation();
		// rsv.CreateReservation();
		// numofPax = rsv.getPax();
		// int tableIndex = FindFreeTable(int numOfPax);

		// if (tableIndex != -1)
		// {
		//	  ReserveTable(int tableIndex);
		//	  rsv.setReservationTable(tableIndex);		
		// }
	
		System.out.println("Table Reserved!");
	}

	//public void FulfilReservation()
	public void fufilReservation(String name)
	{
		// ADD LOGIC HERE
		
		for (int i = 0; i < customerList.size(); i++)
		{
			if(name.equals(customerList.get(i).getName()))
			{
				OccupyTable(customerList.get(i).getTableNo());
				System.out.println("Reservation Fulfilled.");
				return;
			}
		}
		System.out.println("Customer not found.");
		
		System.out.println("Reservation Fulfilled.");
	}
	
	// public void AppendReservationToList()
	public void AppendReservationToList(Reservation rsv)
	{
		// ADD LOGIC HERE

		reservationList.add(rsv);

		System.out.println("Reservation Appended to List");
	}

	//public void RemoveReservation()
	public void RemoveReservation(String name)
	{
		// ADD LOGIC HERE
		
		for (int i = 0; i < customerList.size(); i++)
		{
		 	if(name.equals(customerList.get(i).getName()))
			{
		 		tableList.get(customerList.get(i).getTableNo()).setReserved(false); 
				System.out.println("Reservation Removed.");
				return;
			}
		}
		System.out.println("Table Unreserved");
	}

	public void CheckForTimeOut() // TODO
	// public void CheckForTimeOut(int time)
	{
		// ADD LOGIC HERE
		
		System.out.println("Checking for time out...");
		
		// int timeOutTime = 30;

		// Reservation rsv = new Reservation();

		// for (int i = 0; i < reservationList.size(); i++)
		// {
		// 	rsv = reservationList.get(i);
		//	if(rsv.Reserved() && rsv.getOccupied && time <= (rsv.getReservationTime() + timeOutTime))
		//	{	
		//		for(int j = 0; j < customerList.size(); j++)
		//		{
		//			if(customerList.get(j).getTableNo() == rsv.getTableNo())		// TODO: tableNo in both RSV CLASS AND CUST CLASS
		//			{
		//				customerList.get(j).setTableNo(-1);							// TODO: setTableNo() CUSTOMER CLASS
		//			}
		//		}
		//		reservationList.get(i).setReserved(false);
		//  }
		// }
	}
	
	public void ViewTotalRevenue() // TODO: 
	{
		System.out.println("Calculating Total Revenue...");

		// ADD LOGIC HERE

		// float totalRevenue = 0;

		// for(int i = 0; i < paidOrderList.size(); i++)
		//  	totalRevenue += paidorderList.get(i).getTotalPrice();



	}
	
	
	public void DisplaySystemOptions()
	{
		System.out.println("================================");

		// MENU //////////////////////////////////

		System.out.println("1:  Create Menu Item");
		System.out.println("2:  Remove Menu Item");
		System.out.println("3:  Update Menu Item");

		// PROMOTION /////////////////////////////

		System.out.println("4:  Create Promotion");
		System.out.println("5:  Remove Promotion");
		System.out.println("6:  Update Promotion");

		// CUSTOMER //////////////////////////////

		System.out.println("7:  Create Customer");

		// ORDER /////////////////////////////////

		System.out.println("8:  Create Order");
		System.out.println("9:  Update Order");
		System.out.println("10: View Order");
		System.out.println("11: View All Orders");
		
		System.out.println("12: Print Order Invoice");
		System.out.println("13: Make Payment");

		// TODO: Move Order to paidOrderList

		// TABLES /////////////////////////////////

		System.out.println("14: View Tables");

		// RESERVATION ///////////////////////////	

		System.out.println("15: Create Reservation");
		System.out.println("16: Remove Reservation");
		System.out.println("17: Time Out Reservations");
		System.out.println("18: Occupy Table (Walk-ins)");
		System.out.println("19: Occupy Table (Reservations)");
		
		System.out.println("20: Set Time");

		System.out.println("21: Quit");

		System.out.println("================================");
		///////////////////////////////////////////////////////		
	}
	
	public void LoadStaff()
	{
		Staff staff1 = new Staff("Joan", "Female", "Waiter", 0);
		staffList.add(staff1);
		Staff staff2 = new Staff("Mary", "Female", "Waiter", 1);
		staffList.add(staff2);
		Staff staff3 = new Staff("Bill", "Male", "Cook", 2);
		staffList.add(staff3);
		Staff staff4 = new Staff("Johnson", "Male", "Cook", 3);
		staffList.add(staff4);
		Staff staff5 = new Staff("Emma", "Female", "Manager", 4);
		staffList.add(staff5);
	}

}
