public class FoodRecord 
{
  
  int custId;
  int foodId;
  String dateTime;
  int calories;
  
  public FoodRecord(int cid, int fid, String dt, int c)
  {
    custId = cid;
    foodId = fid;
    dateTime = dt;
    calories = c;
  }
  public int getCustId()
  {
    return custId;
  }
  public int getFoodId()
  {
    return foodId;
  }
  public String getDateTime()
  {
    return dateTime;
  }
  public int getCalories()
  {
    return calories;
  }
  
  public String toString()
  {
    return custId + ", " + foodId + ", " + dateTime + ", " + calories;
  }
}


