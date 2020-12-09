////////////////////////////////////////////////////////////////////
// MARKO VUKOVIC 1193427
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

public class MenuItem {
  private final ItemType itemType;
  private final String name;
  private final double price;
  
  public enum ItemType {GELATO, BUDINO, BEVANDA;}

  public MenuItem(ItemType itemType, String name, double price) 
    throws RestaurantBillException{
    if(price < 0 || itemType == null || name == null){
      throw new RestaurantBillException();
    }
    this.itemType = itemType;
    this.name = name;
    this.price = price;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }
}