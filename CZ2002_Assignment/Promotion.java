package CZ2002_Assignment;

import java.util.*;


public class Promotion {
	//promotionItem are group of menu items on discounted price or special items not in menue
	private ArrayList<MenuItem> promotionSet;
	private String promoName;
	private double price;
	
	
	
	public void addMenuItem(MenuItem mItem) {
		promotionSet.add(mItem);
	}
	
	public void displayPromotionItems() {
		int index =  0;
		
		for(int i = 0; i< promotionSet.size();i++) {
			System.out.print("item " + (i + 1) + ": ");
			System.out.println(promotionSet.get(i).getItemName());
			System.out.println(promotionSet.get(i).getItemPrice());
			System.out.println(promotionSet.get(i).getItemDescription());
		}
	}
	
	
	public Promotion() {
		promotionSet = new ArrayList<>();
	}
	
	public void setPromoName(String name) {
		this.promoName = name;
	}
	
	public void setPromoPrice(double price) {
		this.price = price;
	}
	
	public String getPromoName() {
		return this.promoName;
	}
	public double getPromoPrice() {
		return this.price;
	}
	public ArrayList<MenuItem> getPromoList(){
		return this.promotionSet;
	}
	public MenuItem getMenuItem(int i){
		return promotionSet.get(i);
	}
	
	public double getOriginalPrice() {
		double sum = 0;
		for(MenuItem mItem : promotionSet) {
			sum = sum + mItem.getItemPrice();
		}
		return sum;
	}
	
}

