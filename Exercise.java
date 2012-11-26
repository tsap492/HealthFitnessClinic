public class Exercise 
{
  
  int custId;
  int exerciseId;
  String dateTime;
  int calories;
  
  public Exercise(int cid, int eid, String dt, int c)
  {
    custId = cid;
    exerciseId = eid;
    dateTime = dt;
    calories = c;
  }
  public int getCustId()
  {
    return custId;
  }
  public int getExerciseId()
  {
    return exerciseId;
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
    return custId + ", " + exerciseId + ", " + dateTime + ", " + calories;
  }
}


