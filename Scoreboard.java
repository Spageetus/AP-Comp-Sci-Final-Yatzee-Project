import java.util.ArrayList;
import java.util.Arrays;
class ScoreCard extends Player
{
  private String[][] scoreBoard =
  { //     0                 1       2       3       4       5       6
    {"UPPER SECTION:   |", "_1_|", "_2_|", "_3_|", "_4_|", "_5_|", "_6_|"},//0
    {"1. Aces          |", "___|", "___|", "___|", "___|", "___|", "___|"},//1
    {"2. Twos          |", "___|", "___|", "___|", "___|", "___|", "___|"},//2
    {"3. Threes        |", "___|", "___|", "___|", "___|", "___|", "___|"},//3
    {"4. Fours         |", "___|", "___|", "___|", "___|", "___|", "___|"},//4
    {"5. Fives         |", "___|", "___|", "___|", "___|", "___|", "___|"},//5
    {"6. Sixes         |", "___|", "___|", "___|", "___|", "___|", "___|"},//6
    {"SUBTOTAL         |", "___|", "___|", "___|", "___|", "___|", "___|"},//7
    {"BONUS            |", "___|", "___|", "___|", "___|", "___|", "___|"},//8
    {"HIGH TOTAL       |", "___|", "___|", "___|", "___|", "___|", "___|"},//9
    {"LOWER SECTION:   |", "___|", "___|", "___|", "___|", "___|", "___|"},//10
    {"7. 3 of a kind   |", "___|", "___|", "___|", "___|", "___|", "___|"},//11
    {"8. 4 of a kind   |", "___|", "___|", "___|", "___|", "___|", "___|"},//12
    {"9. Full House    |", "___|", "___|", "___|", "___|", "___|", "___|"},//13
    {"10. Sm. Straight |", "___|", "___|", "___|", "___|", "___|", "___|"},//14
    {"11. Lg. Straight |", "___|", "___|", "___|", "___|", "___|", "___|"},//15
    {"12. YAHTZEE      |", "___|", "___|", "___|", "___|", "___|", "___|"},//16
    {"13. Chance       |", "___|", "___|", "___|", "___|", "___|", "___|"},//17
    {"-YAHTZEE-        |", "___|", "___|", "___|", "___|", "___|", "___|"},//18
    {"-BONUS-          |", "___|", "___|", "___|", "___|", "___|", "___|"},//19
    {"HIGH TOTAL:      |", "___|", "___|", "___|", "___|", "___|", "___|"},//20
    {"LOW TOTAL:       |", "___|", "___|", "___|", "___|", "___|", "___|"},//21
    {"GRAND TOTAL:     |", "___|", "___|", "___|", "___|", "___|", "___|"} //22
  };

  private boolean[] usedScoring = {false, false, false, false, false, false, false, false, false, false, false, false, false, false}; //boolean at index 0 will be used for top bonus

  private int highTotal = 0;
  private int lowTotal = 0;
  private int grandTotal = 0;
  private int bonus = 0;

  private int[] rolls = new int[5];
  private final int yahtzeeBonus = 18;
  private final int bonusYahtzee = 19;


  public ScoreCard()
  {

  }

  public int getFinalScore()
  {
    return highTotal + lowTotal + bonus;
  }

  // calculate the value of the roll:
  public void calculateValues(ArrayList<Dice> roll)
  {
    // extract the int values of all six dice:
    for(int i = 0; i < 5; i++)
    {
     rolls[i] = roll.get(i).getValue();
    }
    int aces = calcAces(rolls);
    if(usedScoring[1] == true)
    {
      aces = 0;
    }
    int twos = calcTwos(rolls);
    if(usedScoring[2] == true)
    {
      twos = 0;
    }
    int threes = calcThrees(rolls);
    if(usedScoring[3] == true)
    {
      threes = 0;
    }
    int fours = calcFours(rolls);
    if(usedScoring[4] == true)
    {
      fours = 0;
    }
    int fives = calcFives(rolls);
    if(usedScoring[5] == true)
    {
      fives = 0;
    }
    int sixes = calcSixes(rolls);
    if(usedScoring[6] == true)
    {
      sixes = 0;
    }
    int threeOfKind = threeSame(rolls);
    if(usedScoring[7] == true)
    {
      threeOfKind = 0;
    }
    int fourOfKind = fourSame(rolls);
    if(usedScoring[8] == true)
    {
      fourOfKind = 0;
    }
    int fullHouse = testFullHouse(rolls);
    if(usedScoring[9] == true)
    {
      fullHouse = 0;
    }
    int smallStraight = testSmall(rolls);
    if(usedScoring[10] == true)
    {
      smallStraight = 0;
    }
    int largeStraight = testLarge(rolls);
    if(usedScoring[11] == true)
    {
      largeStraight = 0;
    }
    int yahtzee = testYahtzee(rolls);
    removeYahtzee();

    int chance = testChance(rolls);
    if(usedScoring[13] == true)
    {
      chance = 0;
    }

    System.out.println("\n\nPOSSIBLE POINTS: ");
    System.out.println("1. Aces: " + aces + "\n2. Twos: " + twos + "\n3. Threes: " + threes + "\n4. Fours: " + fours + "\n5. Fives: " + fives + "\n6. Sixes: " + sixes + "\n7. 3 of a Kind: " + threeOfKind + "\n8. 4 of a Kind: " + fourOfKind + "\n9. Full House: " + fullHouse + "\n10. Sm. Straight: " + smallStraight + "\n11. Lg. Straight: " + largeStraight + "\n12 Yahtzee: " + yahtzee + "\n13. Chance: " + chance);
  }

  public void chooseScoringMethod(int method, int round)
  {
    int points = 0;

    if(method == 1)
    {
      points = calcAces(rolls);
      if(usedScoring[1] == true)
      {
        points = 0;
      }
      usedScoring[1] = true;
    }

    else if(method == 2)
    {
      points = calcTwos(rolls);
      if(usedScoring[2] == true)
      {
        points = 0;
      }
      usedScoring[2] = true;
    }

    else if(method == 3)
    {
      points = calcThrees(rolls);
      if(usedScoring[3] == true)
      {
        points = 0;
      }
      usedScoring[3] = true;
    }

    else if(method == 4)
    {
      points = calcFours(rolls);
      if(usedScoring[1] == true)
      {
        points = 0;
      }
      usedScoring[1] = true;
    }

    else if(method == 5)
    {
      points = calcFives(rolls);
      if(usedScoring[5] == true)
      {
        points = 0;
      }
      usedScoring[5] = true;
    }

    else if(method == 6)
    {
      points = calcSixes(rolls);
      if(usedScoring[6] == true)
      {
        points = 0;
      }
      usedScoring[6] = true;
    }

    else if(method == 7)
    {
      points = threeSame(rolls);
      if(usedScoring[7] == true)
      {
        points = 0;
      }
      usedScoring[7] = true;
    }

    else if(method == 8)
    {
      points = fourSame(rolls);
      if(usedScoring[8] == true)
      {
        points = 0;
      }
      usedScoring[8] = true;
    }

    else if(method == 9)
    {
      points = testFullHouse(rolls);
      if(usedScoring[9] == true)
      {
        points = 0;
      }
      usedScoring[9] = true;
    }

    else if(method == 10)
    {
      points = testSmall(rolls);
      if(usedScoring[10] == true)
      {
        points = 0;
      }
      usedScoring[10] = true;
    }

    else if(method == 11)
    {
      points = testLarge(rolls);
      if(usedScoring[11] == true)
      {
        points = 0;
      }
      usedScoring[11] = true;
    }

    else if(method == 12)
    {
      points = testYahtzee(rolls);
      if(getNumYahtzees() > 1)
      {
        scoreBoard[yahtzeeBonus][round] = "_X_|";
      }
    }
    else if(method == 13)
    {
      points = testChance(rolls);
      if(usedScoring[13] == true)
      {
        points = 0;
      }
      usedScoring[13] = true;
    }

    if(method <= 6)
    {
      highTotal += points;
    }
    else if(method > 6)
    {
      lowTotal += points;
    }
    grandTotal = highTotal + lowTotal + bonus;
    updateScoreBoard(method, round, points);
  }

  public void updateScoreBoard(int method, int round, int points)
  {
    int yIndex = method;
    if(method > 6)
    {
      yIndex += 4;
    }

    if(method == 12 && getNumYahtzees() > 1)
    {
      yIndex = bonusYahtzee;
    }

    String addedPoints = pointsToString(points);
    scoreBoard[yIndex][round] = addedPoints;

    if(grandTotal >= 63 && usedScoring[0] == false)
    {
      bonus = 35;
      grandTotal += bonus;
      usedScoring[0] = true;
      scoreBoard[8][round] = "_35|";
    }

    String subtotal = pointsToString(highTotal);
    scoreBoard[7][round] = subtotal;


    String theHighTotal = pointsToString(highTotal + bonus);
    scoreBoard[9][round] = theHighTotal;
    scoreBoard[20][round] = theHighTotal;

    String theLowTotal = pointsToString(lowTotal);
    scoreBoard[21][round] = theLowTotal;

    String theGrandTotal = pointsToString(grandTotal);
    scoreBoard[22][round] = theGrandTotal;

    System.out.println("\nPress enter to roll again");
    input.nextLine();
  }

  public String pointsToString(int points)
  {
    String value = "" + points;
    if(value.length() == 1)
    {
      value = "_" + value + "_|";
    }

    else if(value.length() == 2)
    {
      value = "_" + value + "|";
    }

    else if(value.length() == 3)
    {
      value = value + "|";
    }
    return value;
  }

  //Aces
  public int calcAces(int[] values)
  {
    int count = 0;
    for(int i = 0; i < values.length; i++)
    {
      if(values[i] == 1)
      {
        count++;
      }
    }
    return count;
  }

  //Twos
  public int calcTwos(int[] values)
  {
    int count = 0;
    for(int i = 0; i < values.length; i++)
    {
      if (values[i] == 2)
      {
        count += 2;
      }
    }
    return count;
  }

  //Threes
  public int calcThrees(int[] values)
  {
    int count = 0;
    for(int i = 0; i < values.length; i++)
    {
      if(values[i] == 3)
      {
        count++;
      }
    }
    return count * 3;
  }

  //Fours
  public int calcFours(int[] values)
  {
    int count = 0;
    for(int i = 0; i < values.length; i++)
    {
      if(values[i] == 4)
      {
        count++;
      }
    }
    return count * 4;
  }

  //Fives
  public int calcFives(int[] values)
  {
    int count = 0;
    for(int i = 0; i < values.length; i ++)
    {
      if(values[i] == 5)
      {
        count++;
      }
    }
    return count * 5;
  }

  //Sixes
  public int calcSixes(int[] values)
  {
    int count = 0;
    for(int i = 0; i < values.length; i++)
    {
      if(values[i] == 6)
      {
        count++;
      }
    }
    return count*6;
  }

  //3 of a kind
  public int threeSame(int[] values)
  {
    int count = 0;
    int num = 0;
    for(int i = 0; i < values.length; i++)
    {

      for(int x = i; x < values.length; x++)
      {
        if(values[i] == values[x])
        {
          count++;
        }
      }
      if(count < 3)
      {
        count = 0;
      }
      if(count >= 3)
      {
        for(int y = 0; y < values.length; y++)
        {
          num += values[y];
        }
        return num;
      }
    }
    return 0;
  }

  //4 of a kind
  public int fourSame(int[] values)
  {
    int count = 0;
    int num = 0;
    for(int i = 0; i < values.length; i++)
    {

      for(int x = i; x < values.length; x++)
      {
        if(values[i] == values[x])
        {
          count++;
        }
      }
      if(count < 4)
      {
        count = 0;
      }
      if(count >= 4)
      {
        for(int y = 0; y < values.length; y++)
        {
          num += values[y];
        }
        return num;
      }
    }
    return 0;
  }

  //Full House
  public int testFullHouse(int[] values)
  {
    int count1 = 0;
    int count2 = 0;
    int num = 0;
    for(int i = 0; i < values.length; i++)
    {

      for(int x = i; x < values.length; x++)
      {
        if(values[i] == values[x])
        {
          count1++;
        }
      }
      if(count1 < 2)
      {
        count1 = 0;
      }
      if(count1 == 2 || count1 == 3)
      {
        num = values[i];
        break;
      }
      else
      {
        return 0;
      }
    }
    for(int i = 0; i < values.length; i++)
    {

      for(int x = 0; x < values.length; x++)
      {
        if(values[i] == values[x] && values[i] != num)
        {
          count2++;
        }
      }
      if(count2 < 2)
      {
        count2 = 0;
      }
      else if(count1 + count2 == 5)
      {
        return 25;
      }
    }
    return 0;
  }

  //Sm Straight
  public int testSmall(int[] values)
  {
    int count = 1;
    if(values[0] + 1 == values[1])
    {
      for(int i = 0; i < values.length - 1; i++)
      {
        if(values[i] + 1 == values[i+1])
        {
          count++;
        }
      }
      if(count < 4)
      {
        return 0;
      }
      else
      {
        return 30;
      }
    }
    else if(values[values.length-1] - 1 == values[values.length-2])
    {
      for(int i = values.length-1; i > 0; i--)
      {
        if(values[i] - 1 == values[i-1])
        {
          count++;
        }
      }
      if(count < 4)
      {
        return 0;
      }
      else
      {
        return 30;
      }
    }
    return 0;
  }

  //Lg Straight
  public int testLarge(int[] values)
  {
    int[] large1 = {1, 2, 3, 4, 5};
    int[] large2 = {2, 3, 4, 5, 6};

    if(Arrays.equals(values, large1) || Arrays.equals(values, large2))
    {
      return 40;
    }
    return 0;
  }

  //YAHTZEE
  public int testYahtzee(int[] values)
  {
    boolean hasYahtzee = false;
    if(values[0] == values[1] && values[1] == values[2] && values[2] == values[3] && values[3] == values[4])
    {
      hasYahtzee = true;
      addYahtzee();
    }
    if(hasYahtzee)
    {
      hasYahtzee = false;
      while(getNumYahtzees() < 0)
      {
        addYahtzee();
      }
      if (getNumYahtzees() == 1)
      {
        return 50;
      }
      if (getNumYahtzees() > 1)
      {
        return (getNumYahtzees() -1) * 100; //POSSIBLE CHANGE
      }
    }
    return 0;
  }


  //Chance
  public int testChance(int[] values)
  {
    int num = 0;
    for(int i = 0; i < values.length; i++)
    {
      num += values[i];
    }
    return num;
  }


  public void printScoreboard()
  {
    System.out.println();
    for(int y = 0; y < scoreBoard.length; y++)
    {
      for(int x = 0; x < scoreBoard[y].length; x++)
      {
        System.out.print(scoreBoard[y][x]);
      }
      System.out.println();
    }
  }
} 
