public class WeighIn 
{
  
  int custId;
  int weightId;
  String dateTime;
  int weight;
  
  public WeighIn(int cid, int wid, String dt, int w)
  {
    custId = cid;
    weightId = wid;
    dateTime = dt;
    weight = w;
  }
  public int getCustId()
  {
    return custId;
  }
  public int getWeightId()
  {
    return weightId;
  }
  public String getDateTime()
  {
    return dateTime;
  }
  public int getWeight()
  {
    return weight;
  }
  
  public String toString()
  {
    return custId + ", " + weightId + ", " + dateTime + ", " + weight;
  }
}


