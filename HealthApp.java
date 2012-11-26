import java.text.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class HealthApp
{

  DB mydb;
  Scanner sc;

  public HealthApp()
  {
    mydb = new DB();
    sc = new Scanner(System.in);
  }
  public void showMenu()
  {
    System.out.println();
    System.out.println("1 = Add customer");
    System.out.println("2 = List customers");
    System.out.println("3 = List Diary Entry");
    System.out.println("4 = Find customer");
    System.out.println("5 = Add weight");
    System.out.println("6 = Add exercise");
    System.out.println("7 = Add Food Record");
    System.out.println("8 = Add Food Diary");
    System.out.println("9 = Weight Log");
    System.out.println("10 = Exercise Log");
    System.out.println("11 = Comparison report for weekly weight-ins");
    System.out.println("12 = Comparison report for weekly exercise");
    System.out.println("13 = Comparison report for weekly calorie loss");
    System.out.println("14 = Weekly Diary Entries");
    System.out.println("0 = Quit");
  }
  public void mainLoop() throws Exception
  {
    int choice = 999;
    while (choice != 0) {
      showMenu();
      choice = Validator.getInt(sc, "Enter choice: ", 0, 14);
      if (1 == choice) addCustomer();
      else if (2 == choice) listCustomers();
      else if (3 == choice) listDiaryEntry();
      else if (4 == choice) findCustomerByName();
      else if (5 == choice) addWeight();
      else if (6 == choice) addExercise();
      else if (7 == choice) addFoodRecord();
      else if (8 == choice) addDiaryEntry();
      else if (9 == choice) weightLog();
      else if (10 == choice) exerciseLog();
      else if (11 == choice) weeklyCompWeighIn();
      else if (12 == choice) weeklyCompExercise();
      else if (13 == choice) weeklyCompCalories();
      else if (14 == choice) weeklyDiaryEntries();
      else if (0 == choice) ;
      else System.out.println("\nInvalid Choice. Please try again.\n");
    }
  }
  public void addCustomer()
  {
    String date;
    int custId = 0;
    
    String firstName = Validator.getLine(sc, "Enter first name: ");
    String lastName  = Validator.getLine(sc, "Enter last name: ");
    String email  = Validator.getLine(sc, "Enter email address: ");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    date = sdf.format(new java.util.Date());
    date = Validator.getLine(sc, "Enter Birthdate(ex: 20121212): ");
    
    Customer c = new Customer(custId, firstName, lastName, email, date);
    
    String result = mydb.addCustomer(c);
    System.out.println(result);
  }
  public void listCustomers()
  {
    int orderBy =
        Validator.getInt(sc, "1 = sort by custId, 2 = sort by name: ", 1, 2);
    int startRecord =
        Validator.getInt(sc, "Index of starting record: ", 1, 999999999);
    int numberToDisplay =
        Validator.getInt(sc, "How many records to display: ", 1, 999999999);

    ArrayList<Customer> cList =
        mydb.listCustomers(orderBy, startRecord, numberToDisplay);

    for (Customer c : cList)
    {
      System.out.printf("%-10s %-20s %-20s %-20s %-15s\n",
          c.getCustId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getBirthDate());
    }
  }
  public void listDiaryEntry()
  {
    int orderBy =
        Validator.getInt(sc, "1 = sort by custId, 2 = sort by name: ", 1, 2);
    int startRecord =
        Validator.getInt(sc, "Index of starting record: ", 1, 999999999);
    int numberToDisplay =
        Validator.getInt(sc, "How many records to display: ", 1, 999999999);

    ArrayList<DiaryEntry> dList =
        mydb.listDiaryEntry(orderBy, startRecord, numberToDisplay);

    for (DiaryEntry d : dList)
    {
      System.out.printf("%-10s %-20s %-20s %-20s\n",
          d.getCustId(), d.getDiaryId(), d.getDateTime(), d.getComment());
    }
  }
  public void findCustomerByName()
  {
    String first = Validator.getLine(sc, "Enter customer first name: ");
    String last  = Validator.getLine(sc, "Enter last name: ");

    ArrayList<Customer> cList = mydb.findCustomerByName(first, last);

    for (Customer c : cList)
    {

      System.out.printf("%-10s %-20s %-20s %-15s %-15s\n",
          c.getCustId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getBirthDate());
    }
  }
  public void addWeight()
  {
    String date;
    int custId = 0;
    int weightId = 0;
    
    date = Validator.getLine(sc, "Enter Date(ex: 20121212): ");
    
    int weight  = Validator.getInt(sc, "Enter weight: ");
    
    WeighIn w = new WeighIn(custId, weightId, date, weight);
    
    String result = mydb.addWeight(w);
    System.out.println(result);
  }
  public void addExercise()
  {
    String date;
    int custId = 0;
    int exerciseId = 0;
    
    date = Validator.getLine(sc, "Enter Date(ex: 20121212): ");
    
    int calories  = Validator.getInt(sc, "Enter calories burned: ");
    
    Exercise e = new Exercise(custId, exerciseId, date, calories);
    
    String result = mydb.addExercise(e);
    System.out.println(result);
  }

  public void addFoodRecord()
  {
    String date;
    int custId = 0;
    int foodId = 0;
    
    date = Validator.getLine(sc, "Enter Date(ex: 20121212): ");
    
    int calories  = Validator.getInt(sc, "Enter calories consumed: ");
    
    FoodRecord f = new FoodRecord(custId, foodId, date, calories);
    
    String result = mydb.addFoodRecord(f);
    System.out.println(result);
  }
  public void addDiaryEntry()
  {
    String date;
    int custId = 0;
    int diaryId = 0;
    
    date = Validator.getLine(sc, "Enter Date(ex: 20121212): ");
    
    String comment  = Validator.getString(sc, "Enter Daily Comment/Log: ");
    
    DiaryEntry d = new DiaryEntry(custId, diaryId, date, comment);
    
    String result = mydb.addDiaryEntry(d);
    System.out.println(result);
  }
  public void weightLog()
  {
    String date;
    int weight;
    
    date = Validator.getLine(sc, "Enter Date to get weight (ex: 20121212): ");
    
    weight =  mydb.weightLog(date);
    System.out.println("Your weight was " + weight + " on " + date);
  }
  public void exerciseLog()
  {
    String date;
    int calories;
    
    date = Validator.getLine(sc, "Enter Date to get calories burned (ex: 20121212): ");
    
    calories =  mydb.exerciseLog(date);
    System.out.println("Your burned calories was " + calories + " on " + date);
  }
  public void calorieLog()
  {
    String date;
    int calories;

    date = Validator.getLine(sc, "Enter Date to get calories consumed (ex: 20121212): ");
    
    calories =  mydb.calorieLog(date);
    System.out.println("Your consumed calories was " + calories + " on " + date);
  } 
  public void weeklyCompWeighIn()
  {

    String day1 = Validator.getLine(sc, "Enter a start date to show a 7 day summary(ex: 20121212): ");
    String day2 = day1.substring(0,day1.length()-1) + (Integer.parseInt(day1.substring(day1.length()-1))+1); 
    String day3 = day2.substring(0,day2.length()-1) + (Integer.parseInt(day2.substring(day2.length()-1))+1); 
    String day4 = day3.substring(0,day3.length()-1) + (Integer.parseInt(day3.substring(day3.length()-1))+1); 
    String day5 = day4.substring(0,day4.length()-1) + (Integer.parseInt(day4.substring(day4.length()-1))+1); 
    String day6 = day5.substring(0,day5.length()-1) + (Integer.parseInt(day5.substring(day5.length()-1))+1); 
    String day7 = day6.substring(0,day6.length()-1) + (Integer.parseInt(day6.substring(day6.length()-1))+1); 
    int weight1 =  mydb.weightLog(day1);
    int weight2 =  mydb.weightLog(day2);
    int weight3 =  mydb.weightLog(day3);
    int weight4 =  mydb.weightLog(day4);
    int weight5 =  mydb.weightLog(day5);
    int weight6 =  mydb.weightLog(day6);
    int weight7 =  mydb.weightLog(day7);
      
    int weightLoss = weight1-weight7;
    // Have to multiply by 100.0 first to convert from int to double division!
    double weightLossPercentage = weightLoss * 100.0 / weight1;

    System.out.println("You weighed " + weight1 + " on " + day1 + "\n" +
                       "You weighed " + weight2 + " on " + day2 + "\n" +
                       "You weighed " + weight3 + " on " + day3 + "\n" +
                       "You weighed " + weight4 + " on " + day4 + "\n" +
                       "You weighed " + weight5 + " on " + day5 + "\n" +
                       "You weighed " + weight6 + " on " + day6 + "\n" +
                       "You weighed " + weight7 + " on " + day7 + "\n");
    System.out.printf("Your percentage of weight loss is: %2.2f%%\n", weightLossPercentage);
  }
  public void weeklyCompExercise()
  {
    String day1 = Validator.getLine(sc, "Enter a start date to show a 7 day summary(ex: 20121212): ");
    String day2 = day1.substring(0,day1.length()-1) + (Integer.parseInt(day1.substring(day1.length()-1))+1); 
    String day3 = day2.substring(0,day2.length()-1) + (Integer.parseInt(day2.substring(day2.length()-1))+1); 
    String day4 = day3.substring(0,day3.length()-1) + (Integer.parseInt(day3.substring(day3.length()-1))+1); 
    String day5 = day4.substring(0,day4.length()-1) + (Integer.parseInt(day4.substring(day4.length()-1))+1); 
    String day6 = day5.substring(0,day5.length()-1) + (Integer.parseInt(day5.substring(day5.length()-1))+1); 
    String day7 = day6.substring(0,day6.length()-1) + (Integer.parseInt(day6.substring(day6.length()-1))+1); 
    int ex1 =  mydb.exerciseLog(day1);
    int ex2 =  mydb.exerciseLog(day2);
    int ex3 =  mydb.exerciseLog(day3);
    int ex4 =  mydb.exerciseLog(day4);
    int ex5 =  mydb.exerciseLog(day5);
    int ex6 =  mydb.exerciseLog(day6);
    int ex7 =  mydb.exerciseLog(day7);
      
    System.out.println("Your Calories burned was " + ex1 + " on " + day1 + "\n" +
                       "Your Calories burned was " + ex2 + " on " + day2 + "\n" +
                       "Your Calories burned was " + ex3 + " on " + day3 + "\n" +
                       "Your Calories burned was " + ex4 + " on " + day4 + "\n" +
                       "Your Calories burned was " + ex5 + " on " + day5 + "\n" +
                       "Your Calories burned was " + ex6 + " on " + day6 + "\n" +
                       "Your Calories burned was " + ex7 + " on " + day7 + "\n"); 
  }
  public void weeklyCompCalories()
  {
    String day1 = Validator.getLine(sc, "Enter a start date to show a 7 day summary(ex: 20121212): ");
    String day2 = day1.substring(0,day1.length()-1) + (Integer.parseInt(day1.substring(day1.length()-1))+1); 
    String day3 = day2.substring(0,day2.length()-1) + (Integer.parseInt(day2.substring(day2.length()-1))+1); 
    String day4 = day3.substring(0,day3.length()-1) + (Integer.parseInt(day3.substring(day3.length()-1))+1); 
    String day5 = day4.substring(0,day4.length()-1) + (Integer.parseInt(day4.substring(day4.length()-1))+1); 
    String day6 = day5.substring(0,day5.length()-1) + (Integer.parseInt(day5.substring(day5.length()-1))+1); 
    String day7 = day6.substring(0,day6.length()-1) + (Integer.parseInt(day6.substring(day6.length()-1))+1); 
    int c1 =  mydb.calorieLog(day1);
    int c2 =  mydb.calorieLog(day2);
    int c3 =  mydb.calorieLog(day3);
    int c4 =  mydb.calorieLog(day4);
    int c5 =  mydb.calorieLog(day5);
    int c6 =  mydb.calorieLog(day6);
    int c7 =  mydb.calorieLog(day7);
      
    System.out.println("Your Consumed Calories was " + c1 + " on " + day1 + "\n" +
                       "Your Consumed Calories was " + c2 + " on " + day2 + "\n" +
                       "Your Consumed Calories was " + c3 + " on " + day3 + "\n" +
                       "Your Consumed Calories was " + c4 + " on " + day4 + "\n" +
                       "Your Consumed Calories was " + c5 + " on " + day5 + "\n" +
                       "Your Consumed Calories was " + c6 + " on " + day6 + "\n" +
                       "Your Consumed Calories was " + c7 + " on " + day7 + "\n");
  }
  public void weeklyDiaryEntries()
  {
    String day1 = Validator.getLine(sc, "Enter a start date to show a 7 day summary(ex: 20121212): ");
    String day2 = day1.substring(0,day1.length()-1) + (Integer.parseInt(day1.substring(day1.length()-1))+1); 
    String day3 = day2.substring(0,day2.length()-1) + (Integer.parseInt(day2.substring(day2.length()-1))+1); 
    String day4 = day3.substring(0,day3.length()-1) + (Integer.parseInt(day3.substring(day3.length()-1))+1); 
    String day5 = day4.substring(0,day4.length()-1) + (Integer.parseInt(day4.substring(day4.length()-1))+1); 
    String day6 = day5.substring(0,day5.length()-1) + (Integer.parseInt(day5.substring(day5.length()-1))+1); 
    String day7 = day6.substring(0,day6.length()-1) + (Integer.parseInt(day6.substring(day6.length()-1))+1); 
    String d1 =  mydb.diaryEntry(day1);
    String d2 =  mydb.diaryEntry(day2);
    String d3 =  mydb.diaryEntry(day3);
    String d4 =  mydb.diaryEntry(day4);
    String d5 =  mydb.diaryEntry(day5);
    String d6 =  mydb.diaryEntry(day6);
    String d7 =  mydb.diaryEntry(day7);

    System.out.println("Your log said: " + d1 + " on " + day1 + "\n" +
                       "Your log said: " + d2 + " on " + day2 + "\n" +
                       "Your log said: " + d3 + " on " + day3 + "\n" +
                       "Your log said: " + d4 + " on " + day4 + "\n" +
                       "Your log said: " + d5 + " on " + day5 + "\n" +
                       "Your log said: " + d6 + " on " + day6 + "\n" +
                       "Your log said: " + d7 + " on " + day7 + "\n");
  }

  public static void main(String[] args) throws Exception
  {
    HealthApp health = new HealthApp();
    health.mainLoop();
  }
}
