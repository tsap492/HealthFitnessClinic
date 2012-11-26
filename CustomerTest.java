//CustomerTest.java by Tessa Seiders on 11/17/2012
//To run the JUnit test type:
//java org.junit.runner.JUnitCore CustomerTest

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class CustomerTest
{
  private Customer cust;
  
  @Before
  public void setUp()
  {
    cust = new Customer(2, "tessa", "seiders", "tessa@hotmail.com", "20121212");
  }

  @Test
  public void testGetCustId()
  {
    assertEquals("Customer getCustId() failed", 2, cust.getCustId());
    System.out.println("@Test - testGetCustId()");
  } 

  @Test
  public void testGetFirstName()
  { 
    assertEquals("Customer getFirstName() failed", "tessa", cust.getFirstName());
    System.out.println("@Test - testGetFirstName()");
  }
 
  @Test 
  public void testGetLastName()
  { 
    assertEquals("Customer getLastName() failed", "seiders", cust.getLastName());
    System.out.println("@Test - testGetLastName()");
  }
  
  @Test
  public void testGetEmail()
  { 
    assertEquals("Customer getEmail() failed", "tessa@hotmail.com", cust.getEmail());
    System.out.println("@Test - testGetEmail()");
  }
  
  @Test
  public void testBirthDate()
  { 
    assertEquals("Customer getBirthDate() failed", "20121212", cust.getBirthDate());
    System.out.println("@Test - testGetBirthDate()");
  }

}
