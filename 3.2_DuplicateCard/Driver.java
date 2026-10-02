import java.util.Objects;

class Card {
   private String rank;
   private String suit;

    Card(String rank, String suit) {
      this.rank = rank;
      this.suit = suit;
    }

    public String toString() {
      return rank + " of " + suit;
    }

    public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (!(obj instanceof Card)) {
         return false;
      }
      Card c = (Card) obj;
      return rank.equals(c.rank) && suit.equals(c.suit);
    }

    public int hashCode() {
      return Objects.hash(rank, suit);
    }
}

public class Driver {
  public static void main(String[] args) {
     Card[] cards = {
        new Card("King", "Hearts"),
        new Card("Queen", "Spades"),
        new Card("Ace", "Clubs"),
        new Card("King", "Hearts"),
        new Card("Jack", "Diamonds")
     };

      for (int i = 0; i < cards.length; i++) {
         for (int j = 0; j < i; j++) {
            if (cards[i].equals(cards[j])) {
               System.out.println("Duplicate found: " + cards[i]);
               return;
            }
         }
      }

      System.out.println("No duplicates found");
    }
}
