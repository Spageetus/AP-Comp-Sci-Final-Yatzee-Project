import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    ScoreCard player = new ScoreCard();
    Scanner input = new Scanner(System.in);

    System.out.println("NUMBERS UNDER DIE ARE NOT THE DICE VALUES THEY ARE USED TO CHOOSE THE DICE YOU WANT TO REROLL (PRESS ENTER TO CONTINUE)");
    input.nextLine();

    for(int round = 1; round <=6; round++) //loops round 6 times
    {
      System.out.print("\nROUND " + round);
      System.out.println("\t(Press enter to continue)");
      input.nextLine();
      player.rollAllDice(); //add any num from 1-6 inside () to force yahtzee of that value
      player.reRoll();
      System.out.print("\nPress enter to show scoreboard ");
      input.nextLine();

      player.printScoreboard();

      player.calculateValues(player.getAllDie());
      System.out.println("\nHow would you like to add points? (Enter the number before your chosen scoring method)");

      int method = input.nextInt();
      player.chooseScoringMethod(method, round);
      player.printScoreboard();

      System.out.println("");
    }
    System.out.println("\n\nFINAL SCORE: " + player.getFinalScore());
    if(player.getFinalScore() > 999)
    {
      System.out.println("If you can see this you are either extremely lucky or cheating");
    }
  }
} //997 total lines 
