// onjava/Tuple2.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package tools;


public class Tuple2<A, B> {
  public final A a1;
  public final B a2;
  public Tuple2(A a, B b) { a1 = a; a2 = b; }
  public String rep() { return  a1 + ", " + a2; }
  @Override
  public String toString() {
    return "(" + rep() + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Tuple2) {
      Tuple2 other = (Tuple2) obj;
      return this.a1.equals(other.a1) && this.a2.equals(other.a2);
    }
    return false;
  }

}
