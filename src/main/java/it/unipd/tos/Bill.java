////////////////////////////////////////////////////////////////////
// MARKO VUKOVIC 1193427
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Bill implements TakeAwayBill {
  private static int possibleFreeOrders = 10;
  private static List<User> usersWithFreeOrder = new ArrayList<User>();
  private int ora;

  public Bill(int ora) {
    this.ora = ora;
  }
  
  public double getOrderPrice(List<MenuItem> itemsOrdered, User user) 
  throws RestaurantBillException {
    Random random = new Random();
    double total = getTotal(itemsOrdered);
    int quantity = itemsOrdered.size();

    if (quantity > 5) {
      total -= discountMoreThenFive(itemsOrdered);
    }
    if (getTotalGelatiBudini(itemsOrdered) > 50) {
      total -= discountExpensiveOrder(itemsOrdered);
    }
    if (quantity > 30) {
      System.out.println("ERROR: more then 30 items ordered");
      throw new RestaurantBillException();
    }
    if (total < 10) {
      total += 0.5;
    }
    if (ora == 18 && user.getAge() < 18 && !(usersWithFreeOrder.contains(user))) {
      if (random.nextBoolean() && possibleFreeOrders > 0) {
        total = 0;
        possibleFreeOrders--;
        usersWithFreeOrder.add(user);
      }
    }
    return total;
  }

  public static double getTotal(List<MenuItem> itemsOrdered) {
    double total = 0;
    for (int i = 0; i < itemsOrdered.size(); i++) {
      total += itemsOrdered.get(i).getPrice();
    }
    return total;
  }

  public static double 
  getTotalGelatiBudini(List<MenuItem> itemsOrdered) {
    double total = 0;
    for (int i = 0; i < itemsOrdered.size(); i++) {
      if (itemsOrdered.get(i).getItemType() == MenuItem.ItemType.GELATO
      || itemsOrdered.get(i).getItemType() == MenuItem.ItemType.BUDINO) {
        total += itemsOrdered.get(i).getPrice();
      }
    }
    return total;
  }

  private static double 
  discountMoreThenFive(List<MenuItem> itemsOrdered) {
    double cheaperGelato = itemsOrdered.get(0).getPrice();
    double tempPrice;
    for (int i = 1; i < itemsOrdered.size(); i++) {
      tempPrice = itemsOrdered.get(i).getPrice();
      if (tempPrice < cheaperGelato) {
        cheaperGelato = tempPrice;
      }
    }
    return cheaperGelato / 2;
  }

  private static double 
  discountExpensiveOrder(List<MenuItem> itemsOrdered) {
    return (getTotal(itemsOrdered) / 100) * 10;
  }
}
