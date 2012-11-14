import java.sql.*;
import java.util.ArrayList;

//Be sure to change the dbURL/username/password to your own!
public class DB {
  private Connection connect = null;
  private String dbURL = "jdbc:mysql://157.62.23.146/seiderstj15";
  private String username = "seiderstj15";
  private String password = "seiderstj15";

  public DB() {
    getConnection();
  }

  private void getConnection()
  {
    try
    {
      connect = DriverManager.getConnection(dbURL, username, password);
    }
    catch (SQLException e)
    {
      System.out.println("Exception thrown calling getConnection.\n" + e.getMessage());
    }
  }

  public String addCustomer(Customer c)
  {
    String result = "";
    PreparedStatement ps = null;
    try
    {
      String q = "insert into fitnessCustomer (custId, firstName, lastName, email, birthDate) "
          + "values (null, ?, ?, ?, ?)";
      ps = connect.prepareStatement(q);
      ps.setString(1, c.getFirstName());
      ps.setString(2, c.getLastName());
      ps.setString(3, c.getEmail());
      ps.setString(4, c.getBirthDate());
      ps.executeUpdate();
      result = c.getFirstName() + " " + c.getLastName() + " has been added.";
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return result;
  }

  public ArrayList<Customer> listCustomers(int orderBy, int startRecord, int numberToDisplay)
  {
    ArrayList<Customer> cList = new ArrayList<Customer>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "";
      if (1==orderBy)
      {
        q = "select custId, firstName, lastName, email, birthDate from fitnessCustomer"
            + " order by custId limit ?, ?";
      }
      else
      {
        q = "select custId, firstName, lastName, email, birthDate from fitnessCustomer"
            + " order by lastName, firstName limit ?, ?";
      }
      ps = connect.prepareStatement(q);
      ps.setInt(1, startRecord-1);
      ps.setInt(2, numberToDisplay);
      rs = ps.executeQuery();

      while (rs.next())
      {
        cList.add( new Customer(
            rs.getInt("custId"), rs.getString("firstName"),
            rs.getString("lastName"), rs.getString("email"), rs.getString("birthDate") ) );
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return cList;
  }

  public ArrayList<DiaryEntry> listDiaryEntry(int orderBy, int startRecord, int numberToDisplay)
  {
    ArrayList<DiaryEntry> dList = new ArrayList<DiaryEntry>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "";
      if (1==orderBy)
      {
        q = "select custId, diaryId, dateTime, comment from diaryEntry"
            + " order by custId limit ?, ?";
      }
      else
      {
        q = "select custId, diaryId, dateTime, comment from diaryEntry"
            + " order by diaryId, comment limit ?, ?";
      }
      ps = connect.prepareStatement(q);
      ps.setInt(1, startRecord-1);
      ps.setInt(2, numberToDisplay);
      rs = ps.executeQuery();

      while (rs.next())
      {
        dList.add( new DiaryEntry(
            rs.getInt("custId"), rs.getInt("diaryId"),
            rs.getString("dateTime"), rs.getString("comment") ) );
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return dList;
  }

  public ArrayList<Customer> findCustomerByName(String first, String last)
  {
    ArrayList<Customer> cList = new ArrayList<Customer>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
      String q = "select custId,firstName, lastName, birthDate, email from fitnessCustomer "
          + "where firstName like ? and lastName like ? order by custId";
      ps = connect.prepareStatement(q);
      ps.setString(1, first + "%");
      ps.setString(2, last + "%");
      rs = ps.executeQuery();

      while (rs.next())
      {
        cList.add( new Customer(
            rs.getInt("custId"), rs.getString("firstName"),
            rs.getString("lastName"), rs.getString("birthDate"), rs.getString("email") ) );
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return cList;
  }

  public String addWeight(WeighIn w)
  {
    String result = "";
    PreparedStatement ps = null;
    try
    {
      String q = "insert into weighIn (custId, weightId, dateTime, weight) "
          + "values (null, ?, ?, ?)";
      ps = connect.prepareStatement(q);
      ps.setInt(1, w.getWeightId());
      ps.setString(2, w.getDateTime());
      ps.setInt(3, w.getWeight());
      ps.executeUpdate();
      result = w.getDateTime() + " " + w.getWeight() + " has been added.";
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return result;
  }

  public String addExercise(Exercise ex)
  {
    String result = "";
    PreparedStatement ps = null;
    try
    {
      String q = "insert into Exercise (custId, exerciseId, dateTime, calories) "
          + "values (null, ?, ?, ?)";
      ps = connect.prepareStatement(q);
      ps.setInt(1, ex.getExerciseId());
      ps.setString(2, ex.getDateTime());
      ps.setInt(3, ex.getCalories());
      ps.executeUpdate();
      result = ex.getDateTime() + " " + ex.getCalories() + " has been added.";
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return result;
  }

  public String addFoodRecord(FoodRecord f)
  {
    String result = "";
    PreparedStatement ps = null;
    try
    {
      String q = "insert into FoodRecord (custId, foodId, dateTime, calories) "
          + "values (null, ?, ?, ?)";
      ps = connect.prepareStatement(q);
      ps.setInt(1, f.getFoodId());
      ps.setString(2, f.getDateTime());
      ps.setInt(3, f.getCalories());
      ps.executeUpdate();
      result = f.getDateTime() + " " + f.getCalories() + " has been added.";
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return result;
  }

  public String addDiaryEntry(DiaryEntry d)
  {
    String result = "";
    PreparedStatement ps = null;
    try
    {
      String q = "insert into DiaryEntry (custId, diaryId, dateTime, comment) "
          + "values (null, ?, ?, ?)";
      ps = connect.prepareStatement(q);
      ps.setInt(1, d.getDiaryId());
      ps.setString(2, d.getDateTime());
      ps.setString(3, d.getComment());
      ps.executeUpdate();
      result = d.getDateTime() + " " + d.getComment() + " has been added.";
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }
    return result;
  }
  public int weightLog(String date)
  {
    PreparedStatement ps = null;
    ResultSet rs = null;
    int weight = 0;
    try
    {
      String q = "select weight from weighIn where dateTime = ?";
      ps = connect.prepareStatement(q);
      ps.setString(1, date);
      rs = ps.executeQuery();

      if (rs.next())
      {
        weight = rs.getInt("weight"); 
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return weight;
  }
  public int exerciseLog(String date)
  {
    PreparedStatement ps = null;
    ResultSet rs = null;
    int calories = 0;
    try
    {
      String q = "select calories from Exercise where dateTime = ?";
      ps = connect.prepareStatement(q);
      ps.setString(1, date);
      rs = ps.executeQuery();

      if (rs.next())
      {
        calories = rs.getInt("calories"); 
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return calories;
  }
  public int calorieLog(String date)
  {
    PreparedStatement ps = null;
    ResultSet rs = null;
    int calories = 0;
    try
    {
      String q = "select calories from FoodRecord where dateTime = ?";
      ps = connect.prepareStatement(q);
      ps.setString(1, date);
      rs = ps.executeQuery();

      if (rs.next())
      {
        calories = rs.getInt("calories"); 
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return calories;
  }
  public String diaryLog(String date)
  {
    PreparedStatement ps = null;
    ResultSet rs = null;
    String comment = "";
    try
    {
      String q = "select comment from diaryEntry where dateTime = ?";
      ps = connect.prepareStatement(q);
      ps.setString(1, date);
      rs = ps.executeQuery();

      if (rs.next())
      {
        comment = rs.getString("comment"); 
      }
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("\nQUERY: " + ps.toString());
    }

    return comment;
  }
  public static void main(String[] args) throws Exception
  {
    DB mydb = new DB();

    ArrayList<Customer> cList = mydb.listCustomers(2, 1, 20);

    for (Customer c : cList)
    {
      System.out.printf("%10s, %20s, %20s, %15s, %15s\n",
          c.getCustId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getBirthDate());
    }
  }
}
