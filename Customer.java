import java.sql.Date;

public class Customer 
{
  int custId;
  String firstName;
  String lastName;
  String email;
  String birthDate;
  
  public Customer(int cid, String fn, String ln, String e, String bd)
  {
    custId = cid;
    firstName = fn;
    lastName = ln;
    email = e;
    birthDate = bd;
  }
  public int getCustId()
  {
    return custId;
  }
  public String getFirstName()
  {
    return firstName;
  }
  public String getLastName()
  {
    return lastName;
  }
  public String getEmail()
  {
    return email;
  }
  public String getBirthDate()
  {
    return birthDate;
  }
  
  public String toString()
  {
    return custId + ", " + firstName + ", " + lastName + ", " + email + ", " + birthDate;
  }
}

