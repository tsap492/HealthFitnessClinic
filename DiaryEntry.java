public class DiaryEntry 
{
  
  int custId;
  int diaryId;
  String dateTime;
  String comment;
  
  public DiaryEntry(int cid, int did, String dt, String c)
  {
    custId = cid;
    diaryId = did;
    dateTime = dt;
    comment = c;
  }
  public int getCustId()
  {
    return custId;
  }
  public int getDiaryId()
  {
    return diaryId;
  }
  public String getDateTime()
  {
    return dateTime;
  }
  public String getComment()
  {
    return comment;
  }
  
  public String toString()
  {
    return custId + ", " + diaryId + ", " + dateTime + ", " + comment;
  }
}


