////////////////////////////////////////////////////////////////////
// MARKO VUKOVIC 1193427
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class BillTest {
  private static final double DELTA = 1e-15;

  @Test
  public void GetOrderPrice_LessThenFive() 
  throws RestaurantBillException {
    Bill bill = new Bill(13);
    User user = new User("Mario", 42);

    double price = 4;

    List<MenuItem> items = new ArrayList<MenuItem>();
    items.add(new MenuItem(MenuItem.ItemType.BUDINO, "Crema",  price));
    items.add(new MenuItem(MenuItem.ItemType.GELATO, "Limone",  price));
    items.add(new MenuItem(MenuItem.ItemType.GELATO, "Pistacchio",  price));

    double total = Bill.getTotal(items);
    assertEquals(total, bill.getOrderPrice(items, user), DELTA);
  }

  @Test
  public void GetOrderPrice_DiscountMoreThenFive() 
  throws RestaurantBillException {
    Bill bill = new Bill(13);
    User user = new User("Mario", 42);

    double lowerPrice = 2;
    double discount = lowerPrice / 2;
    double otherPrice = 3;

    List<MenuItem> items = new ArrayList<MenuItem>();
    items.add(new MenuItem(MenuItem.ItemType.BUDINO, "Crema",  otherPrice));
    items.add(new MenuItem(MenuItem.ItemType.GELATO, "Limone",  lowerPrice));
    items.add(new MenuItem(MenuItem.ItemType.GELATO, "Pistacchio",  otherPrice));
    items.add(new MenuItem(MenuItem.ItemType.GELATO, "Fragola",  otherPrice));
    items.add(new MenuItem(MenuItem.ItemType.BEVANDA, "Cola",  otherPrice));
    items.add(new MenuItem(MenuItem.ItemType.BEVANDA, "Acqua",  otherPrice));

    double totalDiscounted = Bill.getTotal(items) - discount;
    assertEquals(totalDiscounted, bill.getOrderPrice(items, user), DELTA);
  }

  @Test
  public void GetOrderPrice_DiscountExpensiveOrder() 
  throws RestaurantBillException {
    Bill bill = new Bill(13);
    User user = new User("Mario", 42);

    double price = 51;

    List<MenuItem> items = new ArrayList<MenuItem>();
    items.add(new MenuItem(MenuItem.ItemType.BUDINO, "Crema",  price));

    double total = Bill.getTotal(items);
    double totalDiscounted = total - (total / 100) * 10;
    assertEquals(totalDiscounted, bill.getOrderPrice(items, user), DELTA);
  }

  @Test(expected = RestaurantBillException.class) 
  public void GetOrderPrice_TooMuchItems()
  throws RestaurantBillException {
    Bill bill = new Bill(13);
    User user = new User("Mario", 42);

    double price = 2;

    List<MenuItem> items = new ArrayList<MenuItem>();
    for (int i = 0; i < 35; i++){
      items.add(new MenuItem(MenuItem.ItemType.BUDINO, "Crema",  price));
    }

    bill.getOrderPrice(items, user);
  }

  @Test
  public void GetOrderPrice_FeeSmallOrder()
  throws RestaurantBillException {
    Bill bill = new Bill(13);
    User user = new User("Mario", 42);

    double price = 2;

    List<MenuItem> items = new ArrayList<MenuItem>();
    items.add(new MenuItem(MenuItem.ItemType.BUDINO, "Crema",  price));

    double total = Bill.getTotal(items);
    double totalWithFee = total + 0.5;
    assertEquals(totalWithFee, bill.getOrderPrice(items, user), DELTA);
  }

  @Test
  public void GetOrderPrice_ManyPossibleFreeOrders()
  throws RestaurantBillException {
      Bill bill;
      User user;
      List<MenuItem> items;
      double price = 2;

      for(int i = 0; i < 100; i++) {
        bill = new Bill(18);
        user = generateRandomUser();
        items =  new ArrayList<MenuItem>();
        items.add(new MenuItem(MenuItem.ItemType.BUDINO, "Crema",  price));
        double orderPrice = bill.getOrderPrice(items, user);
      }
  }

  private static User generateRandomUser() {
    List<String> names = new ArrayList<String>();
    names.add("Angela");
    names.add("Ada");
    names.add("Adelaide");
    names.add("Anna");
    names.add("Antonella");
    names.add("Anita");
    names.add("Alice");
    names.add("Amelia");
    names.add("Anna");
    names.add("Agnese");
    names.add("Alessandra");
    names.add("Alessia");

    List<Integer> ages = new ArrayList<Integer>();
    ages.add(6);
    ages.add(7);
    ages.add(13);
    ages.add(17);
    ages.add(21);
    ages.add(32);
    ages.add(48);
    ages.add(64);
    ages.add(76);
    
    Random random = new Random();
    String name = names.get(random.nextInt(names.size()));
    int age = ages.get(random.nextInt(ages.size()));
    return new User(name, age);
  }
}