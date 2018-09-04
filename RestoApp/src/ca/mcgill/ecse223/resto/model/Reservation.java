/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

// line 96 "../../../../../RestoAppPersistence.ump"
// line 20 "../../../../../RestoApp v3(2).ump"
public class Reservation implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextReservationNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private Date date;
  private Time time;
  private int numberInParty;
  private String contactName;
  private String contactEmailAddress;
  private String contactPhoneNumber;

  //Autounique Attributes
  private int reservationNumber;

  //Reservation Associations
  private List<Table> tables;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(Date aDate, Time aTime, int aNumberInParty, String aContactName, String aContactEmailAddress, String aContactPhoneNumber, RestoApp aRestoApp, Table... allTables)
  {
    date = aDate;
    time = aTime;
    numberInParty = aNumberInParty;
    contactName = aContactName;
    contactEmailAddress = aContactEmailAddress;
    contactPhoneNumber = aContactPhoneNumber;
    reservationNumber = nextReservationNumber++;
    tables = new ArrayList<Table>();
    boolean didAddTables = setTables(allTables);
    if (!didAddTables)
    {
      throw new RuntimeException("Unable to create Reservation, must have at least 1 tables");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create reservation due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setNumberInParty(int aNumberInParty)
  {
    boolean wasSet = false;
    numberInParty = aNumberInParty;
    wasSet = true;
    return wasSet;
  }

  public boolean setContactName(String aContactName)
  {
    boolean wasSet = false;
    contactName = aContactName;
    wasSet = true;
    return wasSet;
  }

  public boolean setContactEmailAddress(String aContactEmailAddress)
  {
    boolean wasSet = false;
    contactEmailAddress = aContactEmailAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setContactPhoneNumber(String aContactPhoneNumber)
  {
    boolean wasSet = false;
    contactPhoneNumber = aContactPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public Time getTime()
  {
    return time;
  }

  public int getNumberInParty()
  {
    return numberInParty;
  }

  public String getContactName()
  {
    return contactName;
  }

  public String getContactEmailAddress()
  {
    return contactEmailAddress;
  }

  public String getContactPhoneNumber()
  {
    return contactPhoneNumber;
  }

  public int getReservationNumber()
  {
    return reservationNumber;
  }

  public Table getTable(int index)
  {
    Table aTable = tables.get(index);
    return aTable;
  }

  /**
   * only from currentTables
   */
  public List<Table> getTables()
  {
    List<Table> newTables = Collections.unmodifiableList(tables);
    return newTables;
  }

  public int numberOfTables()
  {
    int number = tables.size();
    return number;
  }

  public boolean hasTables()
  {
    boolean has = tables.size() > 0;
    return has;
  }

  public int indexOfTable(Table aTable)
  {
    int index = tables.indexOf(aTable);
    return index;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public boolean isNumberOfTablesValid()
  {
    boolean isValid = numberOfTables() >= minimumNumberOfTables();
    return isValid;
  }

  public static int minimumNumberOfTables()
  {
    return 1;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    tables.add(aTable);
    if (aTable.indexOfReservation(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTable.addReservation(this);
      if (!wasAdded)
      {
        tables.remove(aTable);
      }
    }
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    if (!tables.contains(aTable))
    {
      return wasRemoved;
    }

    if (numberOfTables() <= minimumNumberOfTables())
    {
      return wasRemoved;
    }

    int oldIndex = tables.indexOf(aTable);
    tables.remove(oldIndex);
    if (aTable.indexOfReservation(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTable.removeReservation(this);
      if (!wasRemoved)
      {
        tables.add(oldIndex,aTable);
      }
    }
    return wasRemoved;
  }

  public boolean setTables(Table... newTables)
  {
    boolean wasSet = false;
    ArrayList<Table> verifiedTables = new ArrayList<Table>();
    for (Table aTable : newTables)
    {
      if (verifiedTables.contains(aTable))
      {
        continue;
      }
      verifiedTables.add(aTable);
    }

    if (verifiedTables.size() != newTables.length || verifiedTables.size() < minimumNumberOfTables())
    {
      return wasSet;
    }

    ArrayList<Table> oldTables = new ArrayList<Table>(tables);
    tables.clear();
    for (Table aNewTable : verifiedTables)
    {
      tables.add(aNewTable);
      if (oldTables.contains(aNewTable))
      {
        oldTables.remove(aNewTable);
      }
      else
      {
        aNewTable.addReservation(this);
      }
    }

    for (Table anOldTable : oldTables)
    {
      anOldTable.removeReservation(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addTableAt(Table aTable, int index)
  {  
    boolean wasAdded = false;
    if(addTable(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTableAt(Table aTable, int index)
  {
    boolean wasAdded = false;
    if(tables.contains(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTableAt(aTable, index);
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
      existingRestoApp.removeReservation(this);
    }
    restoApp.addReservation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Table> copyOfTables = new ArrayList<Table>(tables);
    tables.clear();
    for(Table aTable : copyOfTables)
    {
      aTable.removeReservation(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeReservation(this);
    }
  }

  // line 101 "../../../../../RestoAppPersistence.ump"
   public static  void reinitializeNextReservationNumber(List<Reservation> reservations){
    nextReservationNumber = 1;
		for(Reservation r : reservations) {
			if(r.getReservationNumber() > nextReservationNumber) {
			nextReservationNumber = r.getReservationNumber();
		  	}
	  	}
  }


  public String toString()
  {
    return super.toString() + "["+
            "reservationNumber" + ":" + getReservationNumber()+ "," +
            "numberInParty" + ":" + getNumberInParty()+ "," +
            "contactName" + ":" + getContactName()+ "," +
            "contactEmailAddress" + ":" + getContactEmailAddress()+ "," +
            "contactPhoneNumber" + ":" + getContactPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "time" + "=" + (getTime() != null ? !getTime().equals(this)  ? getTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 99 "../../../../../RestoAppPersistence.ump"
  private static final long serialVersionUID = 2315072607928790501L ;

  
}