import java.util.Scanner;
import java.util.Random;

public class RPSLS {
  enum Move {
     ROCK, PAPER, SCISSORS, LIZARD, SPOCK
  }

  static int winner(Move a, Move b) {
     if (a == b) {
        return 0;
     }

      boolean win = switch (a) {
         case ROCK -> b == Move.SCISSORS || b == Move.LIZARD;
         case PAPER -> b == Move.ROCK || b == Move.SPOCK;
         case SCISSORS -> b == Move.PAPER || b == Move.LIZARD;
         case LIZARD -> b == Move.PAPER || b == Move.SPOCK;
         case SPOCK -> b == Move.ROCK || b == Move.SCISSORS;
      };

      return win ? 1 : -1;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    int playerScore = 0, computerScore = 0;

      Move[] moves = Move.values();

      for (int i = 1; i <= 5; i++) {
         System.out.print("Enter move: ");
         Move player = Move.valueOf(sc.next().toUpperCase());

        Move computer = moves[random.nextInt(moves.length)];

        System.out.println("You: " + player);
        System.out.println("Computer: " + computer);

        int result = winner(player, computer);

        if (result == 1) {
           System.out.println("You win this round");
           playerScore++;
        } else if (result == -1) {
           System.out.println("Computer wins this round");
           computerScore++;
        } else {
           System.out.println("Tie");
        }
        }

        System.out.println("You: " + playerScore);
        System.out.println("Computer: " + computerScore);

        if (playerScore > computerScore) {
           System.out.println("You win");
        } else if (computerScore > playerScore) {
           System.out.println("Computer wins");
        } else {
           System.out.println("Match tied");
        }

        sc.close();
    }
}
