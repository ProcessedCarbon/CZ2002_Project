package CZ2002_Assignment;

import java.util.ArrayList;

// List of predefined menu items

public class BaseMenu {
	int size = 4;
	public ArrayList<MenuItem> baseItemList = new ArrayList<MenuItem> ();
	//private ArrayList<Promotion> promoList = new ArrayList<Promotion> ();
	MenuItem m1 = new MenuItem("Hand Tossed Salad", "strawberries, dried cranberries, raisins, pineapple" , 5.00, MenuCategory.SIDES);
	MenuItem m2 = new MenuItem("Caesar Salad", "parmesan cheese, lemon juice" , 8.00, MenuCategory.SIDES);
	MenuItem m3 = new MenuItem("Fresh Juice", "orange / apple / watermelon" , 2.00, MenuCategory.DRINKS);
	MenuItem m4 = new MenuItem("Roast Beef with Mushroom Sauce", "Beef + Mushroom" , 17.00, MenuCategory.MAINS);

	public ArrayList<MenuItem> CreateBase() {
		baseItemList.add(m1);
		baseItemList.add(m2);
		baseItemList.add(m3);
		baseItemList.add(m4);
		
		return baseItemList;
	}
	
	public int baseMenuSize() {
		return size;
	}
}

