////////////////////////////////////////////////////////////////////
// MARKO VUKOVIC 1193427
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import java.util.List;

public interface TakeAwayBill {
  public double getOrderPrice(List<MenuItem> itemsOrdered, User user) 
    throws RestaurantBillException;
}
