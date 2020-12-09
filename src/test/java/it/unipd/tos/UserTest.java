////////////////////////////////////////////////////////////////////
// MARKO VUKOVIC 1193427
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserTest {
  @Test
  public void User_Constructor() {
    String name = "Mario";
    int age = 14;
    User user = new User(name, age);
    assertEquals(name, user.getName());
    assertEquals(age, user.getAge());
  }
}