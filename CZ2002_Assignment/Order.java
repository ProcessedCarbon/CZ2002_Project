package CZ2002_Assignment;

/*

import CZ2002_Assignment.MenuItem; // TODO: java.awt.MenuItem ->  CZ2002_Assignment.MenuItem
import java.util.*;  

public class Order {
	private Staff staffName;
	private ArrayList<MenuItem> alacarte;
	private ArrayList<MenuItem> promo;
	private ArrayList<Integer> alacarteqty;
	private ArrayList<Integer> promoqty;
	private String timeStamp;
	private int tableNo;
	private float discount;
	private double totalPrice;

	public Order(Staff name, String ts, int table) {
		this.staffName = name;
		this.alacarte = new ArrayList<MenuItem>();
		this.promo = new ArrayList<MenuItem>();
		this.alacarteqty = new ArrayList<Integer>();
		this.promoqty = new ArrayList<Integer>();
		this.totalPrice = 0;

	}

	// TODO: CreateOrder() <- Add this function
	// TODO: Take menu as a parameter and use addAlacarte() and addPromo() to create order
	// TODO: Ask input from User for other attributes
	// TODO: Return Order (FULLY CREATED) to SystemManager 

	public void CreateOrder() 
	{
		int tempInt;
		String tempStr;

		Scanner sc = new Scanner(System.in);
		System.out.println("Order Time Stamp: ");
		tempStr = sc.nextLine();
		setTimeStamp(tempStr);

		System.out.println("Order Table: ");
		tempInt = sc.nextInt();
		setTable(tempInt);

		sc.nextLine();
	}

	public void setDiscount(float d) {
		this.discount = d;
	}

	public void addAlacarte(MenuItem dish, Integer qty) {
		int index = alacarte.indexOf(dish);
		if(index < 0) {
			alacarte.add(dish);
			alacarteqty.add(qty);
		}
		else {
			Integer cur = alacarteqty.get(index);
			alacarteqty.set(index, cur + qty);
		}
		totalPrice += (dish.getItemPrice() * qty);
	}

	public void removeAlacarte(MenuItem dish, Integer qty) {
		int index = alacarte.indexOf(dish);
		Integer cur = alacarteqty.get(index);
		alacarteqty.set(index, cur - qty);
		if(alacarteqty.get(index) == 0) {
			alacarte.remove(index);
			alacarteqty.remove(index);
		}
		totalPrice -= (dish.getItemPrice() * qty);
	}

	public void addPromo(MenuItem dish, Integer qty) {
		int index = promo.indexOf(dish);
		if(index < 0) {
			promo.add(dish);
			promoqty.add(qty);
		}
		else {
			Integer cur = promoqty.get(index);
			promoqty.set(index, cur + qty);
		}
		totalPrice += (dish.getItemPrice() * qty);
	}

	public void removePromo(MenuItem dish, Integer qty) {
		int index = promo.indexOf(dish);
		Integer cur = promoqty.get(index);
		promoqty.set(index, cur - qty);
		if(promoqty.get(index) == 0) {
			promo.remove(index);
			promoqty.remove(index);
		}
		totalPrice -= (dish.getItemPrice() * qty);
	}

	public void printOrderItems() {
		for(int i = 0; i<alacarte.size(); i++ ) {
			System.out.printf("%d. %s x%d/n", i+1, alacarte.get(i).getItemName(), alacarteqty.get(i));
		}
		for(int i = 0; i<promo.size(); i++ ) {
			System.out.println("PROMO ITEMS");
			System.out.printf("%d. %s x%d/n", i+1, promo.get(i).getItemName(), promoqty.get(i));
		}
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public int getTableNo() {
		return tableNo;
	}

	public float getDiscount() { // TODO: int -> float 
		return discount;
	}

	public void printInvoice() {
		System.out.println("-------------------");
		System.out.println("INVOICE");
		System.out.println("Table Number: " + getTableNo());
		System.out.println("");
		printOrderItems();
		System.out.println("");
		System.out.println("Discount Applied: " + getDiscount());
		System.out.println("Total Price: " + ((getTotalPrice()) * ((100 - getDiscount()) / 100 )));
		System.out.println("-------------------");
	}

	// TODO: setTimeStamp(String ts) <- Add this function 
	public void setTimeStamp(String ts)
	{
		this.timeStamp = ts;
	}
	// TODO: setTable(int table) <- Add this function 
	public void setTable(int t)
	{
		this.tableNo = t;
	}

}
 */

import CZ2002_Assignment.MenuItem;
import CZ2002_Assignment.Menu;

import java.util.*;  
import java.time.*;

// ==========================================
// ASSUMPTIONS
// NAME OF ENTERED STAFF IS PRESENT IN STAFFLIST
// ASK USER TO TYPE -1 TO STOP ANY INPUTS
// ==========================================

public class Order {

	// ATTRIBUTES
	private Staff staffName;
	private ArrayList<MenuItem> alacarte;
	private ArrayList<Promotion> promo;
	private ArrayList<Integer> alacarteqty;
	private ArrayList<Integer> promoqty;
	//private String timeStamp; // TODO
	LocalTime timeStamp;	// Changed String time to LocalTime together with its functions
	private int tableNo;
	private float discount;
	private double totalPrice;
	private float tax;
	private Scanner sc = new Scanner(System.in);

	// CONSTRUCTOR
	public Order(Staff s, LocalTime ts, int tn, float d) {
		this.tax = 7;
		this.staffName = s;
		this.timeStamp = ts;
		this.tableNo = tn;
		this.discount = d;
		this.totalPrice = 0;
		this.alacarte = new ArrayList<MenuItem>();
		this.promo = new ArrayList<Promotion>();
		this.alacarteqty = new ArrayList<Integer>();
		this.promoqty = new ArrayList<Integer>();
	}

	/*
	// CREATES A NEW ORDER WITH INPUTS. CALLS Order() CONSTRUCTOR AND addItems()
	public Order createOrder(ArrayList<Staff> staffList, Menu menu) {
		Staff staffToAdd;
		String timestampToAdd;
		int tablenoToAdd;
		float discountToAdd;

		System.out.println("Creating New Order");
		System.out.println("Enter name of staff");
		String staffname = sc.next();
		Iterator iter = staffList.iterator();
		while (iter.hasNext()) {
			Staff cur = (Staff) iter.next(); 				// cast to staff
			if (staffname == cur.getName()) {
				staffToAdd = cur;
				break;
			}
		}
		System.out.println("Enter time stamp");
		timestampToAdd = sc.next();
		System.out.println("Enter table number");
		tablenoToAdd = sc.nextInt();
		System.out.println("Enter discount");
		discountToAdd = sc.nextFloat();

		Order neworder = new Order(staffToAdd, timestampToAdd, tablenoToAdd, discountToAdd);

		neworder.addItems(menu);

		return neworder;
	}
	*/
	
	// ------------------------------------------------------------ //
	public void createOrder(Menu menu) {
		//String timestampToAdd;
		int tablenoToAdd;
		float discountToAdd;

		//System.out.println("Enter time stamp");
		//timestampToAdd = sc.next();
		//this.setTimeStamp(timestampToAdd);
		System.out.println("Enter table number");
		tablenoToAdd = sc.nextInt();
		this.setTableNo(tablenoToAdd);
		System.out.println("Enter discount");
		discountToAdd = sc.nextFloat();
		this.setDiscount(discountToAdd);

		this.addItems(menu);
	}
	// ------------------------------------------------------------ //
	
	public void updateOrder(Menu menu) {
		printOrderItems();
		int updatechoice;
		do{
			System.out.println("1: Add items");
			System.out.println("2: Remove items");
			System.out.println("3: Finish Updating");
			updatechoice = sc.nextInt();
			switch(updatechoice) {
			case 1:
				addItems(menu);
				break;
			case 2:
				removeItems();
				break;
			case 3: 
				System.out.println("Order Updated");
				printOrderItems();
				break;
			}
		} while(updatechoice < 3);
	}
	
	// ------------------------------------------------------------ //

	// UPDATES THE CURRENT ORDER. IN CASE LOOP. 
	// CALLS addItems() AND removeItems()
	/*
	public void updateOrder(ArrayList<Staff> staffList, Menu menu) {
		printOrderItems();
		int updatechoice;
		do{
			System.out.println("1: Add items");
			System.out.println("2: Remove items");
			System.out.println("3: Finish Updating");
			updatechoice = sc.nextInt();
			switch(updatechoice) {
			case 1:
				addItems(menu);
				break;
			case 2:
				removeItems();
				break;
			case 3: 
				System.out.println("Order Updated");
				printOrderItems();
				break;
			}
		} while(updatechoice < 3);
	}
	*/

	// PRINTS WHOLE MENU AND ASK USER INPUT TO ADD ITEMS. IN WHILE LOOP. 
	// CALLS addAlacarteItems() AND addPromoItems()
	public void addItems(Menu m) {
		int addchoice;
		m.PrintMenu();
		System.out.println("Select which menu to Order");
		do{
			System.out.println("1: Menu Items");
			System.out.println("2: Promotion Items");
			System.out.println("3: Finish Ordering");

			addchoice = sc.nextInt();
			switch(addchoice) {
			case 1: 
				addAlacarteItems(m);
				break;

			case 2: 
				addPromoItems(m);
				break;

			case 3:
				System.out.println("Order Updated");
				printOrderItems();
				break;
			}
		} while(addchoice < 3);
	}

	// ASK USER INPUT TO REMOVE ITEMS FROM CURRENT ORDER. IN WHILE LOOP.
	// CALLS removeAlacarteItems() AND removePromoItems()
	public void removeItems() {
		int removechoice;
		System.out.println("Select Item to remove");
		do{
			System.out.println("1: Menu Items");
			System.out.println("2: Promotion Items");
			System.out.println("3: Finish Removing");

			removechoice = sc.nextInt();
			switch(removechoice) {
			case 1:
				removeAlacarteItems();
				break;
			case 2:
				removePromoItems();
				break;
			case 3:
				System.out.println("Order Updated");
				printOrderItems();
				break;
			}
		} while(removechoice < 3);
	}

	// PRINTS ALACARTE MENU AND ASK USER INPUT TO ADD MENU ITEMS. IN WHILE LOOP.
	// CALLS addAlacarte()
	public void addAlacarteItems(Menu m) {
		int additemnumber;
		Integer additemqty;
		m.printItemsMenu();
		System.out.println("Enter menu item to add or type -1 to stop ordering");
		additemnumber = sc.nextInt();
		if(additemnumber == -1) {return;}
		do{
			MenuItem itemToAdd = m.getMenuItem(additemnumber-1);
			System.out.println("Enter quantity of Menu Item");
			additemqty = sc.nextInt();
			System.out.println(additemqty);
			addAlacarte(itemToAdd,additemqty);
			System.out.println("Added "+additemqty+" "+itemToAdd.getItemName()+" to your alacarte order");
			System.out.println("Enter menu item to add or type -1 to stop ordering");
			additemnumber = sc.nextInt();
		} while(additemnumber != -1);
	}

	// ASK USER INPUT TO REMOVE ALACARTE ITEMS FROM CURRENT ORDER. IN WHILE LOOP. 
	// CALLS removeAlacarte()
	public void removeAlacarteItems() {
		int removeitemnumber;
		Integer removeitemqty;
		System.out.println("Enter menu item to remove or type -1 to stop removing");
		removeitemnumber = sc.nextInt();
		if(removeitemnumber == -1) {return;}
		do{
			MenuItem itemToRemove = alacarte.get(removeitemnumber - 1);
			System.out.println("Enter quanitity of menu item to remove");
			removeitemqty = sc.nextInt();
			removeAlacarte(itemToRemove, removeitemqty);
			System.out.println("Removed "+removeitemqty+" "+itemToRemove.getItemName()+" from your alacarte order");
			System.out.println("Enter menu item to remove or type -1 to stop removing");
			removeitemnumber = sc.nextInt();
		} while(removeitemnumber != -1);
	}

	// PRINTS PROMO MENU AND ASK UISER INPUT TO ADD PROMO ITEMS. IN WHILE LOOP.
	// CALLS addPromo()
	public void addPromoItems(Menu m) {
		int addpromonumber;
		Integer addpromoqty;
		m.printPromotionMenu();
		System.out.println("Enter promotion set to add or type -1 to stop adding");
		addpromonumber = sc.nextInt();
		do {
			Promotion promoToAdd = m.getPromotion(addpromonumber - 1);
			System.out.println("Enter quantity of promotions to add");
			addpromoqty = sc.nextInt();
			addPromo(promoToAdd, addpromoqty);
			System.out.println("Added "+addpromoqty+" "+promoToAdd.getPromoName()+" to your promotions order");;
			System.out.println("Enter promotion set to add or type -1 to srop adding");
			addpromonumber = sc.nextInt();
		} while (addpromonumber != -1);
	}

	// ASK USER INPUT TO REMOVE PROMO ITEMS FROM CURRENT ORDER. IN WEHILE LOOP. 
	// CALLS removePromo()
	public void removePromoItems() {
		int removepromonumber;
		Integer removepromoqty;
		System.out.println("Enter promo item to remove or type -1 to stop removing");
		removepromonumber = sc.nextInt();
		if(removepromonumber == -1) {return;}
		do{
			Promotion promoToRemove = promo.get(removepromonumber - 1);
			System.out.println("Enter quanitity of promotions to remove");
			removepromoqty = sc.nextInt();
			removePromo(promoToRemove, removepromoqty);
			System.out.println("Removed "+removepromoqty+" "+promoToRemove.getPromoName()+" from your promotions order");
			System.out.println("Enter promotion set to remove or type -1 to stop removing");
			removepromonumber = sc.nextInt();
		} while(removepromonumber != -1);
	}

	// ADDS ALACARTE MENU ITEM INTO ORDER'S ALACARTE ARRAYLIST, UPDATES ALACARTE QTY AND TOTALPRICE
	public void addAlacarte(MenuItem dish, Integer qty) {
		int index = alacarte.indexOf(dish);
		if(index < 0) {
			alacarte.add(dish);
 			alacarteqty.add(qty);
		}
		else {
			Integer cur = alacarteqty.get(index);
			alacarteqty.set(index, cur + qty);
		}
 		totalPrice += (dish.getItemPrice() * qty);
	}

	// REMOVES ALACARTE MENU ITEM FROM ORDER'S ALACARTE ARRAYLIST, UPDATES ALACARTE QTY AND TOTALPRICE
	public void removeAlacarte(MenuItem dish, Integer qty) {
		System.out.println("Enter");
		int index = alacarte.indexOf(dish);
		Integer cur = alacarteqty.get(index);
		alacarteqty.set(index, cur - qty);
		if(alacarteqty.get(index) == 0) {
			alacarte.remove(index);
			alacarteqty.remove(index);
		}
		totalPrice -= (dish.getItemPrice() * qty);
	}

	// ADDS PROMO ITEM INTO ORDER'S PROMO ARRAYLIST, UPDATES ALACARTE QTY AND TOTALPRICE
	public void addPromo(Promotion promotion, Integer qty) {
		int index = promo.indexOf(promotion);
		if(index < 0) {
			promo.add(promotion);
			promoqty.add(qty);
		}
		else {
			Integer cur = promoqty.get(index);
			promoqty.set(index, cur + qty);
		}
		totalPrice += (promotion.getOriginalPrice() * qty);
	}

	// REMOVES PROMO ITEM FROM ORDER'S PROMO ARRAYLIST, UPDATES ALACARTE QTY AND TOTALPRICE
	public void removePromo(Promotion promotion, Integer qty) {
		int index = promo.indexOf(promotion);
		Integer cur = promoqty.get(index);
		promoqty.set(index, cur - qty);
		if(promoqty.get(index) == 0) {
			promo.remove(index);
			promoqty.remove(index);
		}
		totalPrice -= (promotion.getOriginalPrice() * qty);
	}

	public void setStaffName(Staff s) {
		this.staffName = s;
	}
	
	public void setTax(float tax) {
		this.tax = tax;	
	}	

	public Staff getStaffName() {
		return staffName;
	}
	public void setTimeStamp(LocalTime ts) {
		this.timeStamp = ts;
	}

	public LocalTime getTimeStamp() {
		return timeStamp;
	}

	public void setTableNo(int i) {
		this.tableNo = i;
	}
	
	public float getTax() {
		return tax;	
	}	

	public int getTableNo() {
		return tableNo;
	}

	public void setDiscount(float d) {
		this.discount = d;
	}

	public float getDiscount() {
		return discount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	// Get Total Price After Calculation
	public double getTotalPriceAfterCalculation() {
		return ((getTotalPrice()) * ((100 - getDiscount()) / 100 ));
	}
	
	public double getTotalPriceAfterTax() {
		return ((getTotalPriceAfterCalculation()) * ( (100 + getTax()) / 100 ));
	}
	
	// PRINTS OUT LIST OF ALACARTE AND PROMO ITEMS IN ORDER
	public void printOrderItems() {
	    System.out.println("======== ORDERED ALACARTE ITEMS ==============");
	    for(int i = 0; i<alacarte.size(); i++ ) {
	      System.out.println(i+1+". "+alacarte.get(i).getItemName()+" x"+alacarteqty.get(i));
	    }
	    System.out.println("======== ORDERED PROMO ITEMS ==============");
	    for(int i = 0; i<promo.size(); i++ ) {
	      System.out.println(i+1+". "+promo.get(i).getPromoName()+" x"+promoqty.get(i));
	    }
	  }

	// PRINTS ALL ATTRIBUTES OF ORDER
	public void printOrder() {
	    System.out.println("============ ORDER DETAILS ============");
	    System.out.println("StaffName: "+getStaffName().getName()+"\nTimeStamp: "+getTimeStamp()+"\nTableNo: "+getTableNo()+"\nDiscount: "+getDiscount());
	    printOrderItems();
	    System.out.println("TotalPrice: "+getTotalPrice());
	  }

	// PRINTS INVOICE
	public void printInvoice() {
		System.out.println("==================");
		System.out.println("INVOICE");
		System.out.println("Table Number: " + getTableNo());
		System.out.println("");
		printOrderItems();
		System.out.println("");
		System.out.println("Discount Applied: " + getDiscount());
		System.out.println("Total Price: " + (getTotalPriceAfterCalculation()));
		System.out.println("Total Price w GST: " + (getTotalPriceAfterTax()));
		System.out.println("===================");
	}
	

}
