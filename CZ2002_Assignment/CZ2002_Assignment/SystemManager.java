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

		int time = 1000; //TODO: SET TO STRING
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
		sys.LoadTable();

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
				break;

				////////////////////////////////
				// ORDER ///////////////////////
				////////////////////////////////

			case 8: // CREATE ORDER // 
				sc.nextLine(); // Buffer
				System.out.println("Creating New Order...");
				System.out.println("Enter Staff Name: ");
				tempStr = sc.nextLine();
				
				for(int i = 0; i < staffList.size(); i++)
				{
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
				System.out.println("Enter Table Number (Update Order): ");
				tableIndex = sc.nextInt();
				sys.getOrderToUpdate(tableIndex, menu);
				break;

			case 10: // VIEW ORDERS //
				System.out.println("Enter Table Number (View Order): ");
				tableIndex = sc.nextInt();
				sys.ViewOrder(tableIndex);
				break;
				
			case 11: // VIEW ALL ORDERS //
				sys.ViewAllOrders();
				break;

			case 12: // PRINT ORDER INVOICE //
				System.out.println("Enter Table Number (View Order Invoice): ");
				tableIndex = sc.nextInt();
				sys.ViewOrderInvoice(tableIndex);
				break;

			case 13: // MAKE PAYMEMT // //TODO: HANDLE PAYMENT
				System.out.println("Enter Table Number (Making Payment): ");
				tableIndex = sc.nextInt();
				sys.MakePayment(tableIndex);
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
				sc.nextLine(); // Buffer
				System.out.println("Creating New Reservation...");
				System.out.println("Enter Customer Name: ");
				tempStr = sc.nextLine();
				
				for(int i = 0; i < customerList.size(); i++)
				{
					if(tempStr.equals(customerList.get(i).getName())) 
					{
						Reservation rsv = new Reservation("","",-1,customerList.get(i),-1);
						rsv.createReservation();
						
						int index = sys.FindFreeTable(rsv.getReservationPax());
						
						rsv.setReservationTable(index);
						sys.ReserveTable(index);
						
						sys.AppendReservationToList(rsv);
						System.out.println("Reservation Created!");
						break;
					}
				}	
				break;

			case 16: // REMOVE RESERVATION //
				sc.nextLine(); //Buffer
				System.out.println("Enter Customer Name");
				tempStr = sc.nextLine();
				for (int i=0; i < reservationList.size(); i++)
				{
					if(tempStr.equals(reservationList.get(i).getReservationCustomer().getName()))
					{
						tableList.get(reservationList.get(i).getReservationTable()).setReserved(false);;
						reservationList.remove(i);
						System.out.println("Reservation " + (i+1) + " Removed");
						break;
					}
				}
				break;

			case 17: // CHECK FOR TIME OUT RESERVATION //
				// sys.CheckForTimeOut(time);
				break;

			case 18: // OCCUPY TABLE - FOR WALK IN CUSTOMERS //
				sc.nextLine();
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
				sc.nextLine(); //Buffer
				System.out.println("Enter Customer Name");
				tempStr = sc.nextLine();
				for (int i=0; i < reservationList.size(); i++)
				{
					if(tempStr.equals(reservationList.get(i).getReservationCustomer().getName()))
					{
						tableList.get(reservationList.get(i).getReservationTable()).setOccupied(true);
						reservationList.remove(i);
						System.out.println("Fufilled Reservation " + (i+1));
						break;
					}
				}
				break;
				
			case 20: // TODO: SET TIME
				// System.out.println("Input time: ");
				// time = sc.nextInt();
				// break;
				
			case 21:
				sys.ViewTotalRevenue();

			case 22: // EXIT PROGRAM //
				System.out.println("Exiting Program...");
				break;

				////////////////////////////////

			}
		} while(choice < 22);
	}
	
	public void AppendCustomerToList(Customer cst) 
	{
		// ADD LOGIC HERE

		customerList.add(cst);

		System.out.println("Customer Appeneded to customerList!");
	}
	
	public void AppendOrderToList(Order order)
	{
		// ADD LOGIC HERE
		orderList.add(order);

		System.out.println("Order Appeneded to customerList!");
	}

	public void getOrderToUpdate(int tableIndex, Menu menu)
	{
		// ADD LOGIC HERE
		
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				orderList.get(i).updateOrder(menu);
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
				orderList.get(i).printOrder(); //printOrderItems()
				return;
			}
		}
		System.out.println("Order not found.");
		return;
	}	

	public void ViewAllOrders()
	{		
		System.out.println("View All Orders...");

		// ADD LOGIC HERE

		for (int i = 0; i < orderList.size(); i++)
		{
			System.out.println("Printing Order...");
			orderList.get(i).printOrder();
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
	
	// Must have an order to table
	public void MakePayment (int tableIndex) // TODO: 
	{
		// ADD LOGIC HERE
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				System.out.println("Paying for Order...");
				orderList.get(i).printInvoice();
				paidOrderList.add(orderList.get(i));
				orderList.remove(i);
				tableList.get(tableIndex-1).setOccupied(false);
				tableList.get(tableIndex-1).setReserved(false);
				break;
			}	
		}
		System.out.println("Payment Made!");
	}

	// FINDING TABLE //

	public int FindFreeTable(int numOfPax)
	{
		// ADD LOGIC HERE

		for(int i = 0; i < tableList.size() ; i++)
		{
			if(tableList.get(i).getSeatCapacity() >= numOfPax && !tableList.get(i).isReserved() && !tableList.get(i).isOccupied())
			{
				System.out.println("Table " + (i+1) + " is free");
				return i;
		   }
		 }
		
		System.out.println("No tables are free.");
		return -1;
	}

	public void ReserveTable(int tableIndex)
	{
		// ADD LOGIC HERE

		tableList.get(tableIndex).setReserved(true); // TODO: setReserved() -> Reservation Class

		System.out.println("Free Table Reserved!");

	}
	
	public void OccupyTable(int tableIndex)
	{
		// ADD LOGIC HERE
		
		tableList.get(tableIndex).setOccupied(true);
		System.out.println("Table Occupied!");

	}
	
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
		System.out.println("Viewing Tables...");
		for(int i = 0; i < tableList.size(); i++)
		{
			System.out.println("Table Number " + (i+1));
			System.out.println("Table Size: " + tableList.get(i).getSeatCapacity()) ;
			System.out.println("Reserved: " + tableList.get(i).isReserved() + " Occupied: " + tableList.get(i).isOccupied());
			System.out.println("===================================");
		}
	}

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
	
	public void AppendReservationToList(Reservation rsv)
	{
		// ADD LOGIC HERE

		reservationList.add(rsv);

		System.out.println("Reservation Appended to List");
	}

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

		float totalRevenue = 0;

		for(int i = 0; i < paidOrderList.size(); i++)
		{
			
			totalRevenue += paidOrderList.get(i).getTotalPriceAfterTax();
		}
		
		System.out.println("Total Price = $" + totalRevenue ); //TODO: ADD TAXES
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

		// TABLES /////////////////////////////////

		System.out.println("14: View Tables");

		// RESERVATION ///////////////////////////	

		System.out.println("15: Create Reservation");
		System.out.println("16: Remove Reservation");
		System.out.println("17: Time Out Reservations");
		System.out.println("18: Occupy Table (Walk-ins)");
		System.out.println("19: Occupy Table (Reservations)");
		
		System.out.println("20: Set Time");
		
		System.out.println("21: View Total Revenue");

		System.out.println("22: Quit");

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
	
	public void LoadTable()
	{
		Table table1 = new Table (2,false,false);
		tableList.add(table1);
		Table table2 = new Table (2,false,false);
		tableList.add(table2);
		Table table3 = new Table (2,false,false);
		tableList.add(table3);
		Table table4 = new Table (2,false,false);
		tableList.add(table4);
		Table table5 = new Table (4,false,false);
		tableList.add(table5);
		Table table6 = new Table (4,false,false);
		tableList.add(table6);
		Table table7 = new Table (4,false,false);
		tableList.add(table7);
		Table table8 = new Table (4,false,false);
		tableList.add(table8);
		Table table9 = new Table (4,false,false);
		tableList.add(table9);
		Table table10 = new Table (4,false,false);
		tableList.add(table10);
		Table table11 = new Table (6,false,false);
		tableList.add(table11);
		Table table12 = new Table (6,false,false);
		tableList.add(table12);
		Table table13 = new Table (8,false,false);
		tableList.add(table13);
		Table table14 = new Table (8,false,false);
		tableList.add(table14);
		Table table15 = new Table (10,false,false);
		tableList.add(table15);
	}

}
