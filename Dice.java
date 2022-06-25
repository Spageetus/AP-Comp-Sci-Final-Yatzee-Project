import java.util.ArrayList;
class Dice{

  private int value;
  private String die;

  public Dice(int theValue, String diceASCII)
  {
    value = theValue;
    die = diceASCII;
  }


  public int getValue()
  {
    return value;
  }

  public String getDie()
  {
    return die;
  }

  public static void clearDie(ArrayList<Dice> arr) //clears all die from Dice ArrayList
  {
    while(arr.size() > 0)
    {
      arr.remove(0);
    }
  }

  public static ArrayList<Dice> sort(ArrayList<Dice> arrList) //sorts ArrayList of Die objs
  {
    for(int index = 0; index < arrList.size() - 1; index++)
    {
      int minIndex = index;
      for(int i = index; i < arrList.size(); i ++)
      {
        if(arrList.get(i).getValue() < arrList.get(minIndex).getValue())
      	{
      		minIndex = i;
      	}
      }
      Dice tempValue = arrList.get(index);
      arrList.set(index, arrList.get(minIndex));
      arrList.set(minIndex, tempValue);
    }
    return arrList;
  }
  public static Dice rollDice(boolean isForced, int number) // rolls one die and returns it (also allows forced rolls)
  {
    int num = (int)(Math.random()*6+1);
    int value = 0;
    String theDice = "";

    if(isForced && number > 0 && number < 7) //changes all die to chosen value if you are choosing to force it
    {
      num = number;
    }

    if (num == 1)
    {
      value = 1;
      theDice = "#########\n" +
                "#       #\n" +
                "#   O   #\n" +
                "#       #\n" +
                "#########\n";
    }

    else if (num == 2)
    {
      value = 2;
      theDice = "#########\n" +
                "# O     #\n" +
                "#       #\n" +
                "#     O #\n" +
                "#########\n";
    }

    else if (num == 3)
    {
      value = 3;
      theDice = "#########\n" +
                "# O     #\n" +
                "#   O   #\n" +
                "#     O #\n" +
                "#########\n";
    }

    else if (num == 4)
    {
      value = 4;
      theDice = "#########\n" +
                "# O   O #\n" +
                "#       #\n" +
                "# O   O #\n" +
                "#########\n";
    }

    else if(num == 5)
    {
      value = 5;
      theDice = "#########\n" +
                "# O   O #\n" +
                "#   O   #\n" +
                "# O   O #\n" +
                "#########\n";
    }

    else if(num == 6)
    {
      value = 6;
      theDice = "#########\n" +
                "# O   O #\n" +
                "# O   O #\n" +
                "# O   O #\n" +
                "#########\n";
    }

    else
    {
      System.out.println("Invalid diceroll"); //in case it breaks
    }
    Dice die = new Dice(value, theDice); //creates a new dice object using value of die and the ASCII art
    return die;
  }
}
