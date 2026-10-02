import java.util.Objects;

class Fraction {
   private int num, den;

    public Fraction(int num, int den) {
      int g = gcd(Math.abs(num), Math.abs(den));
      this.num = num / g;
      this.den = den / g;
    }

    private int gcd(int a, int b) {
       while (b != 0) {
          int temp = b;
          b = a % b;
          a = temp;
       }
       return a;
    }

    public String toString() {
      return num + "/" + den;
    }

    public boolean equals(Object o) {
      if (!(o instanceof Fraction)) {
         return false;
      }
      Fraction f = (Fraction) o;
      return num == f.num && den == f.den;
    }

    public int hashCode() {
      return Objects.hash(num, den);
    }
}

public class Driver {
  public static void main(String[] args) {
     Fraction f1 = new Fraction(1, 2);
     Fraction f2 = new Fraction(2, 4);
     Fraction f3 = new Fraction(3, 6);

        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f1.equals(f2));
        System.out.println(f2.equals(f3));
    }
}
