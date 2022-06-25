import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class Player{

  private int score;
  private int numYahtzees;
  private ArrayList<Dice> rolledDie = new ArrayList<Dice>();
  Scanner input = new Scanner(System.in);

  public Player()
  {
    score = 0;
    numYahtzees = 0;
  }

  public int getScore()
  {
    return score;
  }

  public int getNumYahtzees()
  {
    return numYahtzees;
  }

  public ArrayList<Dice> getAllDie()
  {
    return rolledDie;
  }

  public void addScore(int points)
  {
    score += points;
  }

  public void addYahtzee()
  {
    numYahtzees++;
  }

  public void removeYahtzee()
  {
    numYahtzees--;
  }


  public void rollAllDice() //rolls all 5 die
  {
    Dice.clearDie(rolledDie);
    for(int i = 0; i < 5; i++)
    {
      rolledDie.add(Dice.rollDice(false, 0));
    }
    rolledDie = Dice.sort(rolledDie);
    printDice();
  }

  public void rollAllDice(int value) //rolls all 5 die with a forced value
  {
    boolean isForced = true;
    Dice.clearDie(rolledDie);
    for(int i = 0; i < 5; i++)
    {
      rolledDie.add(Dice.rollDice(isForced, value));
    }
    rolledDie = Dice.sort(rolledDie);
    printDice();
  }

  /*
  NOTE: I was tired while making the reRoll method so it looks like shit.
  apologies in advance
  */
  public void reRoll() //allows rerolling
  {
    System.out.print("Would you like to reroll any die? (y/n): "); //asks if reroll
    String yesOrNo = input.nextLine().toLowerCase();
    while(yesOrNo.indexOf("y") == -1 && yesOrNo.indexOf("n") == -1)
    {
      System.out.print("\nPlease enter either y (yes) or n (no): ");
      yesOrNo = input.nextLine().toLowerCase();
    }
    if(yesOrNo.equals("y")) //if yes loops following code 2x to allow 2 rerolls
      {
      for(int loop = 0; loop < 2; loop++)
      {
        System.out.print("\nHow many die would you like to reroll? (enter 0 for none): ");
        int numRerolls = input.nextInt();
        while(numRerolls > 5 || numRerolls < 0) //makes sure you only enter valid numbers
        {
          if (numRerolls == 0)
          {
            break;
          }
          System.out.print("\nPlease enter a valid number between 0 and 5: ");
          numRerolls = input.nextInt();
        }
        int arr[] = new int[numRerolls];
        if(numRerolls != 0)
        {
          System.out.println("\nWhich " + numRerolls + " die would you like to reroll? (Enter one number at a time) ");
        }

        for (int i = 0; i < numRerolls; i++)
        {
          System.out.print("\nReroll " + (i+1) +"/" +numRerolls + ": ");
          int num = input.nextInt();
          if(num == 0)
          {
            num = 1;
            break;
          }
          while (num > 5 || num <= 0)
          {
            System.out.print("\nPlease enter a valid number from 1-5: ");
            num = input.nextInt();
          }
          for (int x = 0; x < arr.length; x++)
          {
            if (arr[x] == 0)
            {
              break;
            }
            if(arr[x] == num) //EAT SHIT PAST ME IT DOESNT DUPLICATE ANYMORE
            {
              System.out.print("\nPlease enter an unused number: ");
              int newNum = input.nextInt();
              boolean usedNumber = true;
              while(newNum == num || !usedNumber)
              {
                for(int y = 0; y < arr.length; y++)
                {
                  if(arr[y] == num)
                  {
                    usedNumber = true;
                  }
                }
                System.out.print("\nPlease enter an unused number: ");
                newNum = input.nextInt();
              }

              num = newNum;
              x = 0;
            }
          }
          arr[i] = num;
        }
        Arrays.sort(arr);

        for(int i = arr.length-1; i >= 0; i--)
        {
          while(arr[i] == 0 && i != 0)
          {
            i--;
          }
          if(arr[i] != 0)
          {
            rolledDie.remove(arr[i]-1);
          }

        }
        for(int i = 0; i < numRerolls; i++)
        {
          rolledDie.add(Dice.rollDice(false, 0));
          if(rolledDie.size() > 5)
          {
            rolledDie.remove(rolledDie.size()-1);
          }
        }

        Dice.sort(rolledDie);
        System.out.println();
        printDice();
      }
    }
    yesOrNo = "";
  }

  public void printDice()
  {
    for(int i = 0; i < rolledDie.size(); i++)
    {
      System.out.println(rolledDie.get(i).getDie() + "\t" + (i+1)+ "\n");
    }
  }
}
