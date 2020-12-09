////////////////////////////////////////////////////////////////////
// MARKO VUKOVIC 1193427
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Bill implements TakeAwayBill {
  public static double getTotal(List<MenuItem> itemsOrdered) {
    double total = 0;
    for (int i = 0; i < itemsOrdered.size(); i++) {
      total += itemsOrdered.get(i).getPrice();
    }
    return total;
  }
}
