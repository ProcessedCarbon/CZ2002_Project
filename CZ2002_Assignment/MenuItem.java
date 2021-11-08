package CZ2002_Assignment;

public class MenuItem {

	private String name;
	private MenuCategory menuCategory;
	private String description;
	private double price;
	
	public MenuItem(String itemName, String itemDescription, double itemPrice, MenuCategory menuCat) {
		name = itemName;
		description = itemDescription;
		price = itemPrice;
		menuCategory = menuCat;
	}
	
	// Getter Methods
	public double getItemPrice() {
		return price;
	}
	
	public String getItemDescription() {
		return description;
	}
	
	public String getItemName() {
		return name;
	}
	public MenuCategory getMenuCategory() {
		return menuCategory;
	}
	
	// Setter Methods
	public void setItemPrice(double nPrice, boolean print) {
		if(print)
			System.out.println("Previous price: $" + price + " -> changed to: $" + nPrice);
		price = nPrice;
	}
	
	public void setItemDescription(String nDescription, boolean print) {
		if(print)
			System.out.println("Previous description: " + description + "  -> changed to " + nDescription);
		description = nDescription;
	}
	
	public void setItemName(String nName, boolean print) {
		if(print)
			System.out.println("Previous name: " + name + "  -> changed to " + nName);
		name = nName;
	}
	
	public void setMenuCategory(MenuCategory category, boolean print) {
		if(print)
			System.out.println("Previous name: " + menuCategory + "  -> changed to " + category);
		menuCategory = category;
	}
}