package CZ2002_Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;

public class SystemManager
{
	
	static ArrayList<Table> tableList = new ArrayList<Table>();
 	static ArrayList<Order> orderList = new ArrayList<Order>();
	static ArrayList<Order> paidOrderList = new ArrayList<Order>();
	static ArrayList<Customer> customerList = new ArrayList<Customer>();
	static ArrayList<Staff> staffList = new ArrayList<Staff>();
	static ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

	public static void main(String[] args)
	{
		SystemManager sys = new SystemManager();

		LocalTime currentTime = LocalTime.parse("09:00");
		
		String tempStr;

		Menu menu = new Menu(); // MENU

		int choice, numOfPax, tableIndex;
		Scanner sc = new Scanner(System.in);

		//////////////////////// DRIVER MENU \\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		// PRE MADE MENU; 		A menu that has pre-initialised menuItems 
		// NON PRE MADE MENU: 	An empty menu

		System.out.println("Do you want to use a pre-made menu (0/1)?");
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

				// --- MENU --- //

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
				
				// --- CUSTOMER --- //
				
			case 7: // CREATE CUSTOMER //
				Customer cst = new Customer("","",1,true,-1);
				cst.CreateCustomer();
				sys.AppendCustomerToList(cst); 
				break;
				
				// --- ORDER --- //

			case 8: // CREATE ORDER // 
				System.out.println("=========================");
				sc.nextLine(); // Buffer
				System.out.println("Creating New Order...");
				System.out.println("Enter Staff Name: ");
				tempStr = sc.nextLine();
				
				for(int i = 0; i < staffList.size(); i++)
				{
					if(tempStr.equals(staffList.get(i).getName())) 
					{
						Order order = new Order(staffList.get(i),currentTime,-1,1);
						order.createOrder(menu);
						sys.AppendOrderToList(order);
						System.out.println("Order Created!");
						break;
					}
					System.out.println(tempStr + " not found in staffList.");
				}
				break;

			case 9: // UPDATE ORDER //
				System.out.println("=========================");
				System.out.println("Updating Order...");
				System.out.println("Enter Table Number: ");
				tableIndex = sc.nextInt();
				sys.getOrderToUpdate(tableIndex, menu);
				break;

			case 10: // VIEW ORDERS //
				System.out.println("=========================");
				System.out.println("Viewing Order...");
				System.out.println("Enter Table Number: ");
				tableIndex = sc.nextInt();
				sys.ViewOrder(tableIndex);
				break;
				
			case 11: // VIEW ALL ORDERS //
				System.out.println("=========================");
				System.out.println("Viewing All Orders...");
				sys.ViewAllOrders();
				break;

			case 12: // PRINT ORDER INVOICE //
				System.out.println("=========================");
				System.out.println("Viewing Order Invoice...");
				System.out.println("Enter Table Number: ");
				tableIndex = sc.nextInt();
				sys.ViewOrderInvoice(tableIndex);
				break;

			case 13:
				System.out.println("=========================");
				System.out.println("Making payment...");
				System.out.println("Enter Table Number: ");
				tableIndex = sc.nextInt();
				sys.MakePayment(tableIndex);
				break;


				// --- TABLE --- //

			case 14: // VIEW TABLE //
				sys.ViewTable();
				break;

				// --- RESERVATION  --- //

			case 15: // CREATE RESERVATION //
				sc.nextLine(); 
				System.out.println("=========================");
				System.out.println("Creating New Reservation...");
				System.out.println("Enter Customer Name: ");
				tempStr = sc.nextLine();
				
				for(int i = 0; i < customerList.size(); i++)
				{
					if(tempStr.equals(customerList.get(i).getName())) 
					{
						System.out.println("Input Reservation Time (XX:XX):");
						String temp = sc.nextLine();
						
						Reservation rsv = new Reservation("",LocalTime.parse(temp),-1,customerList.get(i),-1);
						rsv.createReservation();
						
						int index = sys.FindFreeTable(rsv.getReservationPax());
						
						rsv.setReservationTable(index);
						sys.ReserveTable(index);
						
						sys.AppendReservationToList(rsv);
						System.out.println("Reservation for " + rsv.getReservationCustomer().getName() + " created!");
						break;
					}
					System.out.println(tempStr + " not found in customerList.");
				}
				break;

			case 16: // REMOVE RESERVATION //
				sc.nextLine();
				System.out.println("=========================");
				System.out.println("Removing Reservation...");
				System.out.println("Enter Customer Name: ");
				tempStr = sc.nextLine();
				for (int i=0; i < reservationList.size(); i++)
				{
					if(tempStr.equals(reservationList.get(i).getReservationCustomer().getName()))
					{
						tableList.get(reservationList.get(i).getReservationTable()).setReserved(false);;
						reservationList.remove(i);
						System.out.println("Reservation for " + tempStr + " Removed");
						break;
					}
					System.out.println(tempStr + " not found in customerList.");
				}
				break;

			case 17: // CHECK FOR TIME OUT RESERVATION //
				System.out.println("=========================");
				System.out.println("Checking for reservation time outs...");
				sys.CheckForTimeOut(currentTime);
				break;
				
				// --- OCCUPY TABLE --- //

			case 18: // OCCUPY TABLE - FOR WALK IN CUSTOMERS //
				sc.nextLine();
				System.out.println("=========================");
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
						break;
					}
					System.out.println("All tables occupied.");
				}
				break;
				
			case 19: // OCCUPY TABLE - FOR RESERVED CUSTOMERS //
				sc.nextLine();
				System.out.println("=========================");
				System.out.println("Enter Customer Name: ");
				tempStr = sc.nextLine();
				for (int i=0; i < reservationList.size(); i++)
				{
					if(tempStr.equals(reservationList.get(i).getReservationCustomer().getName()))
					{
						tableList.get(reservationList.get(i).getReservationTable()).setOccupied(true);
						reservationList.remove(i);
						System.out.println("Fufilled Reservation for " + tempStr);
						break;
					}
				}
				break;
				
			case 20:
				currentTime = sys.TimeOptions(currentTime);
				break;
				
			case 21: // View Total Revenue
				sys.ViewTotalRevenue();

			case 22: // EXIT PROGRAM //
				System.out.println("Exiting Program...");
				break;

				/////////////////////////////////////////////

			}
		} while(choice < 22);
	}
	
	public void AppendCustomerToList(Customer cst) 
	{
		customerList.add(cst);
		System.out.println(cst.getName() + " appended to customerList!");
	}
	
	public void AppendOrderToList(Order order)
	{
		orderList.add(order);
		System.out.println("Order appended to customerList!");
	}

	public void getOrderToUpdate(int tableIndex, Menu menu)
	{
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

	public void ViewOrder(int tableIndex)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				orderList.get(i).printOrder();
				return;
			}
		}
		System.out.println("Order not found.");
		return;
	}	

	public void ViewAllOrders()
	{	
		for (int i = 0; i < orderList.size(); i++)
		{
			orderList.get(i).printOrder();
		}
		return;
	}
	
	public void ViewOrderInvoice(int tableIndex)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				orderList.get(i).printInvoice();
				return;
			}	
		}
		System.out.println("Order not found.");
		return;
	}
	
	public void MakePayment (int tableIndex)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if(orderList.get(i).getTableNo() == tableIndex)
			{
				orderList.get(i).printInvoice();
				paidOrderList.add(orderList.get(i));
				orderList.remove(i);
				tableList.get(tableIndex-1).setOccupied(false);
				tableList.get(tableIndex-1).setReserved(false);
				System.out.println("Payment for " + tableIndex + " Made!");
				return;
			}	
		}
		System.out.println("Order not found.");
		return;
	}

	public int FindFreeTable(int numOfPax)
	{
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
		tableList.get(tableIndex).setReserved(true);
		System.out.println("Table " + (tableIndex+1) + " reserved");
		return;
	}
	
	public void OccupyTable(int tableIndex)
	{
		tableList.get(tableIndex).setOccupied(true);
		System.out.println("Table " + (tableIndex+1) + " occupied!");
	}
	
	public int OccupyFreeTable(int numOfPax)
	{
		int tableIndex = FindFreeTable(numOfPax);
		
		if(tableIndex != -1)
		{
			 OccupyTable(tableIndex);
			 return tableIndex;
		}
		else
		{
			System.out.println("All tables occupied.");
			return -1;
		}	
	}
	
	public void ViewTable() 
	{
		System.out.println("===================================");
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
		for (int i = 0; i < customerList.size(); i++)
		{
			if(name.equals(customerList.get(i).getName()))
			{
				OccupyTable(customerList.get(i).getTableNo());
				System.out.println("Reservation for" + name + " Fulfilled.");
				return;
			}
		}
		System.out.println(name + " not found.");
		return;
	}
	public void ViewReservation(Reservation rsv)
	{
		System.out.println(rsv.getReservationTable());
		System.out.println(rsv.getReservationTime());
		System.out.println(rsv.getReservationCustomer().getName());
		System.out.println(rsv.getReservationPax());
	}
	
	public void AppendReservationToList(Reservation rsv)
	{
		reservationList.add(rsv);
		System.out.println("Reservation for " + rsv.getReservationCustomer().getName() + " appended to List");
	}

	public void RemoveReservation(String name)
	{
		for (int i = 0; i < customerList.size(); i++)
		{
		 	if(name.equals(customerList.get(i).getName()))
			{
		 		tableList.get(customerList.get(i).getTableNo()).setReserved(false); 
				System.out.println("Reservation Removed.");
				return;
			}
		}
		System.out.println("Reservation not found.");
		
		//////
		
		
		
	}

	public void CheckForTimeOut(LocalTime currentTime)
	{		
		System.out.println("Checking for time out...");
		
		int maxTimeDuration = 30;
		String tempStr;
		
		for(int i = 0; i < reservationList.size(); i++)
		{
			LocalTime maxTime = reservationList.get(i).getReservationTime().plus(Duration.ofMinutes(maxTimeDuration));
			//System.out.println("Max Time: " + maxTime);
			if(currentTime.isAfter(maxTime))
			{
				tempStr = reservationList.get(i).getReservationCustomer().getName();
				
				for (int j=0; j < reservationList.size(); i++)
				{
					if(tempStr.equals(reservationList.get(i).getReservationCustomer().getName()))
					{
						tableList.get(reservationList.get(i).getReservationTable()).setReserved(false);;
						reservationList.remove(i);
						System.out.println("Reservation for " + tempStr + " Removed.");
						break;
					}
				}
			}
		}
		
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
		
		System.out.println("Total Price = $" + totalRevenue ); 
	}

	public LocalTime TimeOptions(LocalTime currentTime)
	{
		Scanner sc = new Scanner(System.in);
		String tempStr;
		int tempInt, choice;
		System.out.println("=========================");
		System.out.println("(1) View");
		System.out.println("(2) Set Time Manually");
		System.out.println("(3) Advance time by Hours");
		System.out.println("(4) Advance time by Hours");
		System.out.println("(5) Exit");
		System.out.println("=========================");
		
		do
		{
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Time: " + currentTime);
				break;
			
			case 2:
				sc.nextLine(); // Buffer
				System.out.println("Input Time (XX:XX): ");
				tempStr = sc.nextLine();
				currentTime = LocalTime.parse(tempStr);
				System.out.println("Time: " + currentTime);
				break;
				
			case 3:
				System.out.println("Advance time by (Minutes): ");
				tempInt = sc.nextInt();
				currentTime = currentTime.plus(Duration.ofMinutes(tempInt));
				System.out.println("Time: " + currentTime);
				break;
				
			case 4:
				System.out.println("Advance time by (Hours): ");
				tempInt = sc.nextInt();
				currentTime = currentTime.plus(Duration.ofHours(tempInt));
				System.out.println("Time: " + currentTime);
				break;
				
			case 5:
				System.out.println("Exiting Time Options");
				break;
				
			}
		} while(choice < 5);
		
		return currentTime;
	}
	
	public void DisplaySystemOptions()
	{
		System.out.println("=========================");

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

		System.out.println("=========================");
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
