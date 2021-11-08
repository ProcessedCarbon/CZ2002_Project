package CZ2002_Assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	private int menuItemSize = 0;
	private int menuPromoSize = 0;
	private static ArrayList<MenuItem> itemList = new ArrayList<MenuItem> ();
	
	//array list of array list
	private static ArrayList<Promotion> menuPromoList = new ArrayList<Promotion> ();

	// testing purpose
	/*
	public static void main (String[] args) {
		Menu menu = new Menu();
		// Base list of predefined menuItems
		menu.LoadBaseMenu();
		//menu.CreateNewMenuItem();
		//menu.PrintMenu();
		menu.CreateNewPromotion();
		while(true) {
			//just to test all condition 
			menu.Update();
			Scanner scan = new Scanner(System.in);
			int x = scan.nextInt();
			if(x == 0) {
				System.out.println("quit loop");
				break;
			}
		}
		
		//menu.Update();
		//menu.PrintMenu();
		
		//menu.CreateNewMenuItem();
		System.out.println("testing remove");
		menu.Remove();
		menu.PrintMenu();
	}
	*/
	
	// Loads the base menu and concatenates the list of base menu items to current item list.
	public void LoadBaseMenu() {
		BaseMenu bm = new BaseMenu();
		itemList.addAll(bm.CreateBase());

		PrintMenu();

		//int size = getMenuSize();
		//setMenuSize(size + bm.baseMenuSize());
	}
	
	public void Create() {
		Scanner sc = new Scanner(System.in);
		System.out.println("(1) Create New Item");
		System.out.println("(2) Create New Promotion");
		
		int choice = sc.nextInt();
		
		if(choice == 1) {
			CreateNewMenuItem();
		}
		else {
			CreateNewPromotion();
		}
	}

	// Key in number of menu items you want to add to the menu and adds them, values keyed must be case specific
	public void CreateNewMenuItem() {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many items you wish to add?");
		int numItems = sc.nextInt();

		if(numItems <= 0) {
			System.out.println("Invalid size declaration. Exiting.......");
			return;
		}

		// Clears Buffer
		sc.nextLine();

		// Ask for input for each item to be added.
		for(int i =0; i < numItems; i++) {
			System.out.printf("Item %d\n",i + 1);

			// Read Name
			System.out.println("Name:");
			String itemName = sc.nextLine();

			// If a duplicate name was keyed to be added, ask for another name again
			if(CheckList(itemName)) {
				
				
				System.out.println("(1) Try Again");
				System.out.println("(2) Exit");
				int choice = sc.nextInt();
				sc.nextLine();
				if(choice == 1) {
					i -= 1;
					continue;
				}
				else {
					System.out.println("Exiting, sucessful previous items added will reamain in the list");
					break;
				}
			}

			// Read Description
			System.out.println("Description:");
			String desp = sc.nextLine();

			// Read Price
			System.out.println("Price:");
			double price = sc.nextDouble();

			// Clears buffer 
			sc.nextLine();

			// Read category 
			System.out.println("Category:");
			MenuCategory category = MenuCategory.valueOf(sc.nextLine().toUpperCase());

			// Creates menuItem to be added
			MenuItem m = new MenuItem(itemName,desp,price,category);
			AddToItemList(m, true);
		}
	}

	public void CreateNewPromotion() {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many promotionalSets you wish to add?");
		int numOfSets = sc.nextInt();

		if(numOfSets <= 0) {
			System.out.println("Invalid size declaration. Exiting.......");
			return;
		}

		// Clears Buffer
		sc.nextLine();
		
		// Ask for input for each item to be added.
		for(int i =0; i < numOfSets; i++) {
			System.out.printf("New Promotion Set %d\n",i + 1);
		
		System.out.println("Promotion Name:");
		String promoName = sc.nextLine();
		// If a duplicate name was keyed to be added, ask for another name again
		if(checkPromoList(promoName)) {
			
			System.out.println("(1) Try Again");
			System.out.println("(2) Exit");
			
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1) {
				i-=1;
				continue;
			}
			else {
				System.out.println("Exiting, sucessful previous Promotions Set added will reamain in the list");
				return;
			}
		}
		System.out.println("Size of new Promotional set");
		int size = sc.nextInt();
		
		Promotion curPromoSet = new Promotion();
		
		
		addItemsToPromoSet(curPromoSet, size);
		
		//After finishing current promotional set add to promotion set menu
		curPromoSet.setPromoName(promoName);
		AddToMenuPromoList(curPromoSet, true);
		sc = new Scanner(System.in);//reset buffer
		
		}
	}

	private void addItemsToPromoSet(Promotion curPromoSet,int size) {
		Scanner sc = new Scanner(System.in);
		for(int j = 0;j<size;j++) {
			System.out.printf("Cur Promotion Set item no. %d\n",j + 1);
			System.out.println("Add mene Item(s) to Promotion Set or unique Promotional Item");
			System.out.println("(1) Add Selected Item(s) from menu to Promotion Set");
			System.out.println("(2) Unique Promotional Item(s)");
			int option = sc.nextInt();
			if(option == 1) {
				//display menu and add item to promotional Set
				printItemsMenu();
				System.out.println("Select menu item number to add to current Promotion Set");
				int itemNo = sc.nextInt();
				//0 base index recall
				MenuItem itemTobeAdded = itemList.get(itemNo -1);
				curPromoSet.addMenuItem(itemTobeAdded);
			}
			else if (option == 2) {
				System.out.println("Enter unique item");
				System.out.println("name:");
				sc.nextLine();
				String itemName = sc.nextLine();

				// If item enter already in menu
				if(CheckList(itemName)) {
					 
					System.out.println("Item already in menu not unique please use option 1 for this item!!!");
					j = j - 1; //no count so can retry
					continue;
				}
				//unique item only for promotion not in menu
				// Read Description
				System.out.println("Description:");
				String desp = sc.nextLine();

				// Read Price
				System.out.println("Price:");
				double price = sc.nextDouble();

				// Clears buffer 
				sc.nextLine();

				// Read category 
				System.out.println("Category:");
				MenuCategory category = MenuCategory.valueOf(sc.nextLine().toUpperCase());

				// Creates menuItem to be added
				MenuItem itemTobeAdded = new MenuItem(itemName,desp,price,category);
				curPromoSet.addMenuItem(itemTobeAdded);
			}
		}
		//Set the current promotional set price 
		System.out.println("current promotion set price base on original item price: $" + curPromoSet.getOriginalPrice());
		System.out.println("Set current promotion Set price: ");
		double curPromoPrice = sc.nextDouble();
		curPromoSet.setPromoPrice(curPromoPrice);
		
	}
	
	// Checks through both list to see if there are any duplicates of name == parameter in both Lists
	private boolean CheckList(String name) {
		for(int i = 0; i < itemList.size();i++) {
			String itemName = itemList.get(i).getItemName();
			if(itemName.compareToIgnoreCase(name) == 0) {
				System.out.println("Duplicate detected in List of Items. Please try again.");

				return true;
			}
		}
		/*for(int i = 0; i < menuPromoList.size();i++) {
			String itemName = itemList.get(i).getItemName();
			if(itemName.compareToIgnoreCase(name) == 0) {
				System.out.println("Duplicate detected in list of Promotions. Please try again.");
				return true;
			}
		}*/
		return false;
	}
	
	//Check promoList if have any duplicate promotional names
	private boolean checkPromoList(String name) {
		for(Promotion pSet : menuPromoList) {
			if(pSet.getPromoName().equals(name)) {
				System.out.println("Duplicate Promotion-Set in List of Promotinal Items. Please try again.");
				return true;
			}
		}
		
		return false;
	}
	

	// Adds menu item to the list data structure, boolean asks if you want the item added to be printed
	public void AddToItemList(MenuItem m, boolean print) {
		itemList.add(m);

		if(print) {
			int latestIndex = itemList.lastIndexOf(m);
			System.out.println("");
			System.out.println("=====================ADDED ITEM===========================");
			PrintFromMenuListItem(latestIndex);
			System.out.println("=========================================================");
			System.out.println("");
			PrintMenu();
		}
		//IncItemListSize();
	}
	
	public void AddToMenuPromoList(Promotion p, boolean print) {
		menuPromoList.add(p);
		if(print) {
			int latestIndex = menuPromoList.lastIndexOf(p);
			System.out.println("");
			System.out.println("=====================ADDED PROMOTIONAL SET===========================");
			PrintFromPromoListItem(latestIndex);
			System.out.println("=========================================================");
			System.out.println("");
			//PrintMenu();
		}
		//IncMenuPromoListSize();
	}

	public void Update() {
		Scanner sc = new Scanner(System.in);
		System.out.println("(1) Update a Menu Item");
		System.out.println("(2) Update a Promotion");
		
		int choice = sc.nextInt();
		
		if(choice == 1) {
			//PrintMenu();
			//display all menu excludind promotion
			printItemsMenu();
			System.out.println("Select Item Number to update:");
			int index = sc.nextInt();
			UpdateMenuItem(index - 1);
		}
		else {
			//display all promotion and their items
			printPromotionMenu();
			System.out.println("Select Promotion Number to update:");
			int index = sc.nextInt();
			UpdatePromotion(index -1);		
		}
	}
	
	// Updates Menu item of specified index, also ask the user for input as to what to update
	public void UpdateMenuItem(int index) {	
		
		Scanner sc = new Scanner(System.in);

		MenuItem m = itemList.get(index);
		
		System.out.println("");
		System.out.println("================ SELECTED ITEM ===================");
		PrintFromMenuListItem(index);
		System.out.println("==================================================");

		System.out.println("(1)Change Name");
		System.out.println("(2)Change Price");
		System.out.println("(3)Change Description");
		System.out.println("(4)Change Category");

		int option = sc.nextInt();
		
		switch(option) {
			case 1: 
				sc.nextLine();
				System.out.println("New Name");
				String newName = sc.nextLine();
				m.setItemName(newName, false);
			break;
			case 2: 
				System.out.println("New Price");
				double newPrice = sc.nextDouble();
				m.setItemPrice(newPrice, false);
			break;
			case 3: 
				sc.nextLine();
				System.out.println("New Description");
				String newDescript = sc.nextLine();
				m.setItemDescription(newDescript, false);
			break;
			case 4: 
				sc.nextLine();
				System.out.println("New Category");
				MenuCategory newCate = MenuCategory.valueOf(sc.nextLine().toUpperCase());
				m.setMenuCategory(newCate, false);
			break;
		}
		System.out.println("");
		System.out.println("======================= UPDATED =========================");
		PrintFromMenuListItem(index);
		System.out.println("=========================================================");
		System.out.println("");
	}
	
	public void UpdatePromotion(int index) {	
		Scanner sc = new Scanner(System.in);
		Promotion p = menuPromoList.get(index);
		
		System.out.println("(1) Change Name");
		System.out.println("(2) Change Price");
		System.out.println("(3) Add Item");
		System.out.println("(4) Remove Item");
		int option = sc.nextInt();
		
		switch(option) {
			case 1: 
				sc.nextLine();
				System.out.println("New Name");
				String newName = sc.nextLine();
				p.setPromoName(newName);
			break;
			case 2: 
				System.out.println("New Price");
				double newPrice = sc.nextDouble();
				p.setPromoPrice(newPrice);
			break;
			case 3: 
				System.out.println("Number of items to add");
				int num = sc.nextInt();
				addItemsToPromoSet(p, num);
			break;
			case 4:
				p.displayPromotionItems();
				System.out.println("Select which item to be remove from current promotion set");
				int itemIndex = sc.nextInt();
				p.getPromoList().remove(itemIndex -1);
				//Set the current promotional set price 
				System.out.println("current promotion set price base on original item price: $" + p.getOriginalPrice());
				System.out.println("Set current promotion Set price: ");
				double curPromoPrice = sc.nextDouble();
				p.setPromoPrice(curPromoPrice);
			break;
		}
		System.out.println("");
		System.out.println("=====================UPDATED PROMO========================");
		PrintFromPromoListItem(index);
		System.out.println();
		System.out.println("=========================================================");
		System.out.println("");
	}

	public void Remove() {
		Scanner sc = new Scanner(System.in);
		System.out.println("(1) Remove a Menu Item");
		System.out.println("(2) Remove a Promotion");
		
		int choice = sc.nextInt();
		
		if(choice == 1) {
			PrintMenu();
			System.out.println("Item Number:");
			int index = sc.nextInt();
			RemoveFromItemList(index - 1);
			PrintMenu();
		}
		else {
			printPromotionMenu();
			System.out.println("Promotion Number:");
			int index = sc.nextInt();
			RemoveFromPromoList(index - 1);
		}
	}
	
	public void RemoveFromItemList(int index) {
		MenuItem m = itemList.get(index);
		itemList.remove(index);

		System.out.println("");
		System.out.println("======================= REMOVED ITEM =========================");
		PrintItem(m);
		System.out.println("=========================================================");
		System.out.println("");
	
		//DecItemListSize();
	}
	
	//remove entire promotion Set from list
	public void RemoveFromPromoList(int index) {
		Promotion p = menuPromoList.get(index);
		menuPromoList.remove(index);
		System.out.println("");
		System.out.println("======================= REMOVED PROMOTION SET =========================");
		PrintPromotion(p);
		System.out.println("=========================================================");
		System.out.println("");
	
		//DecItemListSize();
	}
	
	// Prints entire current itemList and promotion set
	
	public void PrintMenu() {
		System.out.println("");
		System.out.println("==================== MENU ========================");
		
		System.out.println("");
		System.out.println("==================== ITEMS ========================");
		for(int i =0; i < itemList.size();i++) {
			PrintFromMenuListItem(i);
		}

		System.out.println("");
		System.out.println("==================== PROMOTIONS ========================");

		 //Prints all promotion items in MENU
		/*
		for(int i =0; i < menuPromoList.size();i++) {
			PrintFromPromotionalList(i);
		}*/
		printPromotionMenu();
		
		System.out.println("");
		System.out.println("=========================================================");		
		System.out.println("");
	}

	// Prints only the selected index from item list
	public void PrintFromMenuListItem(int index) {
		System.out.println("Item: " + (index+1));
		PrintItem(itemList.get(index));
	}
	
	//print entire promotion list menu only excluding promotion
	public void printItemsMenu() {
		System.out.println("");
		System.out.println("==================== MENU ========================");
		
		System.out.println("");
		System.out.println("==================== ITEMS ========================");
		for(int i =0; i < itemList.size();i++) {
			PrintFromMenuListItem(i);
		}

		System.out.println("");
	}
	
	
	
	
	//print entire promotion list menu only
	public void printPromotionMenu() {
		System.out.println("");
		System.out.println("==================== PROMOTION MENU ========================");
		
		System.out.println("");
		
		//System.out.println("");
		System.out.println("==================== PROMOTIONS SETS ========================");

		// Prints all promotion items in MENU
		for(int i =0; i < menuPromoList.size();i++) {
			PrintFromPromoListItem(i);
		}

		System.out.println("");
		System.out.println("=========================================================");		
		System.out.println("");
	}
	
	
	//print every item in specific promotion set in detail
	/*
	public void PrintFromPromotionalList(int index) {
		System.out.println("Promotional set: " + (index+1));
		Promotion pSet = menuPromoList.get(index);
		System.out.println("Promotion Set: " + pSet.getPromoName());
		System.out.println("Promotion Set price " + pSet.getPromoPrice());
		System.out.println();
		System.out.println("Alacart price:");
		for(MenuItem mItem : pSet.getPromoList()) {
			PrintItem(mItem);
		}
	}	
	*/
	
	public void PrintFromPromoListItem(int index) {
		System.out.println("");
		System.out.println("Promotion: " + (index+1));
	    PrintPromotion(menuPromoList.get(index));
		System.out.println("");
	}
	// Prints Menu Item in stated format
	public void PrintItem(MenuItem m) {
		System.out.println("");
		System.out.println("Name: " + m.getItemName());
		System.out.printf("Price: $%.2f\n" ,m.getItemPrice());
		System.out.println("Description: " + m.getItemDescription());
		System.out.println("Category: " + m.getMenuCategory());
		System.out.println("");
	}
	


	// Prints the specific promotion 
	public void PrintPromotion(Promotion p) {
		System.out.println("");
		System.out.println("Promotion set: " +p.getPromoName());
		System.out.println("Promotion set price: " + p.getPromoPrice());
		System.out.println("Ala cart price");
		p.displayPromotionItems();
		System.out.println("");
	}

	// Increment itemList size by 1
	/*
	public void IncItemListSize() {
		System.out.println("called");
		int size = getMenuSize();
		size += 1;
		setMenuSize(size);
		PrintSize();
	}
	 */
	// Increment promoList size by 1
	
	/*public void IncMenuPromoListSize() {
		int size = getMenuPromoSize();
		setPromoSize(size++);
		PrintSize();
	}*/
	
	/*
	// Decrement itemList size by 1
	public void DecItemListSize() {
		int size = getMenuSize();
		setMenuSize(size--);
		PrintSize();
	}
	*/
	// Decrement promoList size by 1
	
	/*public void DecMenuPromoListSize() {
		int size = getMenuPromoSize();
		setPromoSize(size--);
		PrintSize();
	}*/

	// Prints current size of itemList & promoList
	
	public void PrintSize() {
		System.out.println("---------------------------------------------------");
		//System.out.println("ITEM SIZE = " + getMenuSize());
		System.out.println("ITEM SIZE = " + getMainMenuSize());
		//System.out.println("PROMOTION SIZE = " + getPromoSize());
		//System.out.println("MENU SIZE = " + (getMenuSize() + getPromoSize()));
		System.out.println("---------------------------------------------------");
	}

	/*
	 * I REPLACE ALL THE GET SIZE WITH SOMETHING EASIER MANAGE IF YOU OK
	 * 
	 * 
	 */
	public int getMainMenuSize() {
		return itemList.size();
	}
	
	private void updateMenuItemSize() {
		menuItemSize = getMainMenuSize();
	}
	
	public int getPromotionMenuSize() {
		return menuPromoList.size();
	}
	
	private void updateMenuPromoSize() {
		menuPromoSize = getPromotionMenuSize();
	}
	
	// Getter Methods
	/*
	public int getMenuSize() {
		return menuItemSize;
	}
*/
	public ArrayList<MenuItem> getItemList() {
		return itemList;
	}
	

	public MenuItem getMenuItem(int index) {
		//get item from array,array 0 base index
		//item display from 1 onwards but start from 0 base index
		return itemList.get(index);
	}
	
	public ArrayList<Promotion> getMenuPromoList() {
		return menuPromoList;
	}
	
	public Promotion getPromotion(int index) {  
		  //index 0 is first promotion 
		  //but on display 1)promo 1 is index 0  
		  //so typing 1 will give promotion 1 at index 0 
		  return menuPromoList.get(index); 
		 }

	/*public int getMenuPromoSize() {
		return promoSize;
	}*/

	// Setter Methods
	/*
	public void setMenuSize(int size) {
		menuItemSize = size;
	}

	public void setItemList(ArrayList<MenuItem> list) {
		itemList = list;
	}
*/
	/*public void setMenuPromoList(ArrayList<Promotion> list) {
		menuPromoList = list;
	}*/

	/*public void setMenuPromoSize(int size) {
		promoSize = size;
	}*/
}
