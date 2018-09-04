/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 24 "../../../../../RestoAppPersistence.ump"
// line 52 "../../../../../RestoApp v3(2).ump"
public class TogoOrder implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TogoOrder Attributes
  private String name;
  private Date date;
  private Time time;

  //Autounique Attributes
  private int number;

  //TogoOrder Associations
  private List<TogoOrderItem> toGoOrderItems;
  private RestoApp restoApp;
  private List<TogoBill> togoBills;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TogoOrder(String aName, Date aDate, Time aTime, RestoApp aRestoApp)
  {
    name = aName;
    date = aDate;
    time = aTime;
    number = nextNumber++;
    toGoOrderItems = new ArrayList<TogoOrderItem>();
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create toGoOrder due to restoApp");
    }
    togoBills = new ArrayList<TogoBill>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTime(Time aTime)
  {
    boolean wasSet = false;
    time = aTime;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Date getDate()
  {
    return date;
  }

  public Time getTime()
  {
    return time;
  }

  public int getNumber()
  {
    return number;
  }

  public TogoOrderItem getToGoOrderItem(int index)
  {
    TogoOrderItem aToGoOrderItem = toGoOrderItems.get(index);
    return aToGoOrderItem;
  }

  public List<TogoOrderItem> getToGoOrderItems()
  {
    List<TogoOrderItem> newToGoOrderItems = Collections.unmodifiableList(toGoOrderItems);
    return newToGoOrderItems;
  }

  public int numberOfToGoOrderItems()
  {
    int number = toGoOrderItems.size();
    return number;
  }

  public boolean hasToGoOrderItems()
  {
    boolean has = toGoOrderItems.size() > 0;
    return has;
  }

  public int indexOfToGoOrderItem(TogoOrderItem aToGoOrderItem)
  {
    int index = toGoOrderItems.indexOf(aToGoOrderItem);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public TogoBill getTogoBill(int index)
  {
    TogoBill aTogoBill = togoBills.get(index);
    return aTogoBill;
  }

  public List<TogoBill> getTogoBills()
  {
    List<TogoBill> newTogoBills = Collections.unmodifiableList(togoBills);
    return newTogoBills;
  }

  public int numberOfTogoBills()
  {
    int number = togoBills.size();
    return number;
  }

  public boolean hasTogoBills()
  {
    boolean has = togoBills.size() > 0;
    return has;
  }

  public int indexOfTogoBill(TogoBill aTogoBill)
  {
    int index = togoBills.indexOf(aTogoBill);
    return index;
  }

  public static int minimumNumberOfToGoOrderItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TogoOrderItem addToGoOrderItem(int aQuantity, PricedMenuItem aPricedMenuItem)
  {
    return new TogoOrderItem(aQuantity, aPricedMenuItem, this);
  }

  public boolean addToGoOrderItem(TogoOrderItem aToGoOrderItem)
  {
    boolean wasAdded = false;
    if (toGoOrderItems.contains(aToGoOrderItem)) { return false; }
    TogoOrder existingTogoOrder = aToGoOrderItem.getTogoOrder();
    boolean isNewTogoOrder = existingTogoOrder != null && !this.equals(existingTogoOrder);
    if (isNewTogoOrder)
    {
      aToGoOrderItem.setTogoOrder(this);
    }
    else
    {
      toGoOrderItems.add(aToGoOrderItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeToGoOrderItem(TogoOrderItem aToGoOrderItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aToGoOrderItem, as it must always have a togoOrder
    if (!this.equals(aToGoOrderItem.getTogoOrder()))
    {
      toGoOrderItems.remove(aToGoOrderItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addToGoOrderItemAt(TogoOrderItem aToGoOrderItem, int index)
  {  
    boolean wasAdded = false;
    if(addToGoOrderItem(aToGoOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfToGoOrderItems()) { index = numberOfToGoOrderItems() - 1; }
      toGoOrderItems.remove(aToGoOrderItem);
      toGoOrderItems.add(index, aToGoOrderItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveToGoOrderItemAt(TogoOrderItem aToGoOrderItem, int index)
  {
    boolean wasAdded = false;
    if(toGoOrderItems.contains(aToGoOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfToGoOrderItems()) { index = numberOfToGoOrderItems() - 1; }
      toGoOrderItems.remove(aToGoOrderItem);
      toGoOrderItems.add(index, aToGoOrderItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addToGoOrderItemAt(aToGoOrderItem, index);
    }
    return wasAdded;
  }

  public boolean setRestoApp(RestoApp aRestoApp)
  {
    boolean wasSet = false;
    if (aRestoApp == null)
    {
      return wasSet;
    }

    RestoApp existingRestoApp = restoApp;
    restoApp = aRestoApp;
    if (existingRestoApp != null && !existingRestoApp.equals(aRestoApp))
    {
      existingRestoApp.removeToGoOrder(this);
    }
    restoApp.addToGoOrder(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfTogoBills()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TogoBill addTogoBill(RestoApp aRestoApp)
  {
    return new TogoBill(this, aRestoApp);
  }

  public boolean addTogoBill(TogoBill aTogoBill)
  {
    boolean wasAdded = false;
    if (togoBills.contains(aTogoBill)) { return false; }
    TogoOrder existingToGoOrder = aTogoBill.getToGoOrder();
    boolean isNewToGoOrder = existingToGoOrder != null && !this.equals(existingToGoOrder);
    if (isNewToGoOrder)
    {
      aTogoBill.setToGoOrder(this);
    }
    else
    {
      togoBills.add(aTogoBill);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTogoBill(TogoBill aTogoBill)
  {
    boolean wasRemoved = false;
    //Unable to remove aTogoBill, as it must always have a toGoOrder
    if (!this.equals(aTogoBill.getToGoOrder()))
    {
      togoBills.remove(aTogoBill);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTogoBillAt(TogoBill aTogoBill, int index)
  {  
    boolean wasAdded = false;
    if(addTogoBill(aTogoBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTogoBills()) { index = numberOfTogoBills() - 1; }
      togoBills.remove(aTogoBill);
      togoBills.add(index, aTogoBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTogoBillAt(TogoBill aTogoBill, int index)
  {
    boolean wasAdded = false;
    if(togoBills.contains(aTogoBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTogoBills()) { index = numberOfTogoBills() - 1; }
      togoBills.remove(aTogoBill);
      togoBills.add(index, aTogoBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTogoBillAt(aTogoBill, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (toGoOrderItems.size() > 0)
    {
      TogoOrderItem aToGoOrderItem = toGoOrderItems.get(toGoOrderItems.size() - 1);
      aToGoOrderItem.delete();
      toGoOrderItems.remove(aToGoOrderItem);
    }
    
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeToGoOrder(this);
    }
    for(int i=togoBills.size(); i > 0; i--)
    {
      TogoBill aTogoBill = togoBills.get(i - 1);
      aTogoBill.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "time" + "=" + (getTime() != null ? !getTime().equals(this)  ? getTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 27 "../../../../../RestoAppPersistence.ump"
  private static final long serialVersionUID = 12345678L ;

  
}