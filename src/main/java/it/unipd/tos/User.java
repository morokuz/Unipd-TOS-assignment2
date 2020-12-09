////////////////////////////////////////////////////////////////////
// MARKO VUKOVIC 1193427
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

public class User {
  private final String name;
  private final int age;

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {return name;}
  public int getAge() {return age;}

  @Override
  public boolean equals(Object obj) {
    User other = (User) obj;
    if (this.name != other.name) {
      return false;
    }
    if (this.age != other.age) {
      return false;
    }
    return true;
  }
}
