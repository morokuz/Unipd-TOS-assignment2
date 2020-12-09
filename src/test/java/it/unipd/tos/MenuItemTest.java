////////////////////////////////////////////////////////////////////
// MARKO VUKOVIC 1193427
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MenuItemTest {
  private static final double DELTA = 1e-15;

  @Test
  public void MenuItem_CorrectConstruction() {
    MenuItem.ItemType itemType = MenuItem.ItemType.GELATO;
    String name = "Cioccolato";
    double price = 1.5;
    try {
      MenuItem gelato = new MenuItem(itemType, name, price);
      assertEquals(itemType, gelato.getItemType());
      assertEquals(name, gelato.getName());
      assertEquals(price, gelato.getPrice(), DELTA);
    } 
    catch (RestaurantBillException e) {
      System.out.println(e);
    }
  } 

  @Test(expected = RestaurantBillException.class)
  public void MenuItem_NegativePrice() throws RestaurantBillException{
    MenuItem.ItemType itemType = MenuItem.ItemType.GELATO;
    String name = "Cioccolato";
    double price = -1.5;
    MenuItem gelato = new MenuItem(itemType, name, price);
  } 

  @Test(expected = RestaurantBillException.class)
  public void MenuItem_NullName() throws RestaurantBillException{
    MenuItem.ItemType itemType = MenuItem.ItemType.GELATO;
    String name = null;
    double price = 1.5;
    MenuItem gelato = new MenuItem(itemType, name, price);
  } 

  @Test(expected = RestaurantBillException.class)
  public void MenuItem_NullItemType() throws RestaurantBillException{
    MenuItem.ItemType itemType = null;
    String name = "Cioccolato";
    double price = 1.5;
    MenuItem gelato = new MenuItem(itemType, name, price);
  } 
}