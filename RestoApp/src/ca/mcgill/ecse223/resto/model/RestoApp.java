/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 3 "../../../../../RestoAppPersistence.ump"
// line 6 "../../../../../RestoApp v3(2).ump"
public class RestoApp implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RestoApp Associations
  private List<Reservation> reservations;
  private List<Table> tables;
  private List<Table> currentTables;
  private List<Order> orders;
  private List<Order> currentOrders;
  private Menu menu;
  private List<PricedMenuItem> pricedMenuItems;
  private List<TogoOrder> toGoOrders;
  private List<TogoOrder> currentToGoOrders;
  private List<TogoBill> toGoBill;
  private List<Bill> bills;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RestoApp(Menu aMenu)
  {
    reservations = new ArrayList<Reservation>();
    tables = new ArrayList<Table>();
    currentTables = new ArrayList<Table>();
    orders = new ArrayList<Order>();
    currentOrders = new ArrayList<Order>();
    if (aMenu == null || aMenu.getRestoApp() != null)
    {
      throw new RuntimeException("Unable to create RestoApp due to aMenu");
    }
    menu = aMenu;
    pricedMenuItems = new ArrayList<PricedMenuItem>();
    toGoOrders = new ArrayList<TogoOrder>();
    currentToGoOrders = new ArrayList<TogoOrder>();
    toGoBill = new ArrayList<TogoBill>();
    bills = new ArrayList<Bill>();
  }

  public RestoApp()
  {
    reservations = new ArrayList<Reservation>();
    tables = new ArrayList<Table>();
    currentTables = new ArrayList<Table>();
    orders = new ArrayList<Order>();
    currentOrders = new ArrayList<Order>();
    menu = new Menu(this);
    pricedMenuItems = new ArrayList<PricedMenuItem>();
    toGoOrders = new ArrayList<TogoOrder>();
    currentToGoOrders = new ArrayList<TogoOrder>();
    toGoBill = new ArrayList<TogoBill>();
    bills = new ArrayList<Bill>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservations.get(index);
    return aReservation;
  }

  /**
   * sorted by date and time
   */
  public List<Reservation> getReservations()
  {
    List<Reservation> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }

  public Table getTable(int index)
  {
    Table aTable = tables.get(index);
    return aTable;
  }

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

  public Table getCurrentTable(int index)
  {
    Table aCurrentTable = currentTables.get(index);
    return aCurrentTable;
  }

  /**
   * subsets tables
   */
  public List<Table> getCurrentTables()
  {
    List<Table> newCurrentTables = Collections.unmodifiableList(currentTables);
    return newCurrentTables;
  }

  public int numberOfCurrentTables()
  {
    int number = currentTables.size();
    return number;
  }

  public boolean hasCurrentTables()
  {
    boolean has = currentTables.size() > 0;
    return has;
  }

  public int indexOfCurrentTable(Table aCurrentTable)
  {
    int index = currentTables.indexOf(aCurrentTable);
    return index;
  }

  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }

  public Order getCurrentOrder(int index)
  {
    Order aCurrentOrder = currentOrders.get(index);
    return aCurrentOrder;
  }

  /**
   * subsets orders
   */
  public List<Order> getCurrentOrders()
  {
    List<Order> newCurrentOrders = Collections.unmodifiableList(currentOrders);
    return newCurrentOrders;
  }

  public int numberOfCurrentOrders()
  {
    int number = currentOrders.size();
    return number;
  }

  public boolean hasCurrentOrders()
  {
    boolean has = currentOrders.size() > 0;
    return has;
  }

  public int indexOfCurrentOrder(Order aCurrentOrder)
  {
    int index = currentOrders.indexOf(aCurrentOrder);
    return index;
  }

  public Menu getMenu()
  {
    return menu;
  }

  public PricedMenuItem getPricedMenuItem(int index)
  {
    PricedMenuItem aPricedMenuItem = pricedMenuItems.get(index);
    return aPricedMenuItem;
  }

  public List<PricedMenuItem> getPricedMenuItems()
  {
    List<PricedMenuItem> newPricedMenuItems = Collections.unmodifiableList(pricedMenuItems);
    return newPricedMenuItems;
  }

  public int numberOfPricedMenuItems()
  {
    int number = pricedMenuItems.size();
    return number;
  }

  public boolean hasPricedMenuItems()
  {
    boolean has = pricedMenuItems.size() > 0;
    return has;
  }

  public int indexOfPricedMenuItem(PricedMenuItem aPricedMenuItem)
  {
    int index = pricedMenuItems.indexOf(aPricedMenuItem);
    return index;
  }

  public TogoOrder getToGoOrder(int index)
  {
    TogoOrder aToGoOrder = toGoOrders.get(index);
    return aToGoOrder;
  }

  public List<TogoOrder> getToGoOrders()
  {
    List<TogoOrder> newToGoOrders = Collections.unmodifiableList(toGoOrders);
    return newToGoOrders;
  }

  public int numberOfToGoOrders()
  {
    int number = toGoOrders.size();
    return number;
  }

  public boolean hasToGoOrders()
  {
    boolean has = toGoOrders.size() > 0;
    return has;
  }

  public int indexOfToGoOrder(TogoOrder aToGoOrder)
  {
    int index = toGoOrders.indexOf(aToGoOrder);
    return index;
  }

  public TogoOrder getCurrentToGoOrder(int index)
  {
    TogoOrder aCurrentToGoOrder = currentToGoOrders.get(index);
    return aCurrentToGoOrder;
  }

  public List<TogoOrder> getCurrentToGoOrders()
  {
    List<TogoOrder> newCurrentToGoOrders = Collections.unmodifiableList(currentToGoOrders);
    return newCurrentToGoOrders;
  }

  public int numberOfCurrentToGoOrders()
  {
    int number = currentToGoOrders.size();
    return number;
  }

  public boolean hasCurrentToGoOrders()
  {
    boolean has = currentToGoOrders.size() > 0;
    return has;
  }

  public int indexOfCurrentToGoOrder(TogoOrder aCurrentToGoOrder)
  {
    int index = currentToGoOrders.indexOf(aCurrentToGoOrder);
    return index;
  }

  public TogoBill getToGoBill(int index)
  {
    TogoBill aToGoBill = toGoBill.get(index);
    return aToGoBill;
  }

  public List<TogoBill> getToGoBill()
  {
    List<TogoBill> newToGoBill = Collections.unmodifiableList(toGoBill);
    return newToGoBill;
  }

  public int numberOfToGoBill()
  {
    int number = toGoBill.size();
    return number;
  }

  public boolean hasToGoBill()
  {
    boolean has = toGoBill.size() > 0;
    return has;
  }

  public int indexOfToGoBill(TogoBill aToGoBill)
  {
    int index = toGoBill.indexOf(aToGoBill);
    return index;
  }

  public Bill getBill(int index)
  {
    Bill aBill = bills.get(index);
    return aBill;
  }

  public List<Bill> getBills()
  {
    List<Bill> newBills = Collections.unmodifiableList(bills);
    return newBills;
  }

  public int numberOfBills()
  {
    int number = bills.size();
    return number;
  }

  public boolean hasBills()
  {
    boolean has = bills.size() > 0;
    return has;
  }

  public int indexOfBill(Bill aBill)
  {
    int index = bills.indexOf(aBill);
    return index;
  }

  public static int minimumNumberOfReservations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reservation addReservation(Date aDate, Time aTime, int aNumberInParty, String aContactName, String aContactEmailAddress, String aContactPhoneNumber, Table... allTables)
  {
    return new Reservation(aDate, aTime, aNumberInParty, aContactName, aContactEmailAddress, aContactPhoneNumber, this, allTables);
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    RestoApp existingRestoApp = aReservation.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aReservation.setRestoApp(this);
    }
    else
    {
      reservations.add(aReservation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReservation(Reservation aReservation)
  {
    boolean wasRemoved = false;
    //Unable to remove aReservation, as it must always have a restoApp
    if (!this.equals(aReservation.getRestoApp()))
    {
      reservations.remove(aReservation);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addReservationAt(Reservation aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTables()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Table addTable(int aNumber, int aX, int aY, int aWidth, int aLength)
  {
    return new Table(aNumber, aX, aY, aWidth, aLength, this);
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    RestoApp existingRestoApp = aTable.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aTable.setRestoApp(this);
    }
    else
    {
      tables.add(aTable);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    //Unable to remove aTable, as it must always have a restoApp
    if (!this.equals(aTable.getRestoApp()))
    {
      tables.remove(aTable);
      wasRemoved = true;
    }
    return wasRemoved;
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

  public static int minimumNumberOfCurrentTables()
  {
    return 0;
  }

  public boolean addCurrentTable(Table aCurrentTable)
  {
    boolean wasAdded = false;
    if (currentTables.contains(aCurrentTable)) { return false; }
    currentTables.add(aCurrentTable);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCurrentTable(Table aCurrentTable)
  {
    boolean wasRemoved = false;
    if (currentTables.contains(aCurrentTable))
    {
      currentTables.remove(aCurrentTable);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCurrentTableAt(Table aCurrentTable, int index)
  {  
    boolean wasAdded = false;
    if(addCurrentTable(aCurrentTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCurrentTables()) { index = numberOfCurrentTables() - 1; }
      currentTables.remove(aCurrentTable);
      currentTables.add(index, aCurrentTable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCurrentTableAt(Table aCurrentTable, int index)
  {
    boolean wasAdded = false;
    if(currentTables.contains(aCurrentTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCurrentTables()) { index = numberOfCurrentTables() - 1; }
      currentTables.remove(aCurrentTable);
      currentTables.add(index, aCurrentTable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCurrentTableAt(aCurrentTable, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(Date aDate, Time aTime, Table... allTables)
  {
    return new Order(aDate, aTime, this, allTables);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    RestoApp existingRestoApp = aOrder.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aOrder.setRestoApp(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a restoApp
    if (!this.equals(aOrder.getRestoApp()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfCurrentOrders()
  {
    return 0;
  }

  public boolean addCurrentOrder(Order aCurrentOrder)
  {
    boolean wasAdded = false;
    if (currentOrders.contains(aCurrentOrder)) { return false; }
    currentOrders.add(aCurrentOrder);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCurrentOrder(Order aCurrentOrder)
  {
    boolean wasRemoved = false;
    if (currentOrders.contains(aCurrentOrder))
    {
      currentOrders.remove(aCurrentOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCurrentOrderAt(Order aCurrentOrder, int index)
  {  
    boolean wasAdded = false;
    if(addCurrentOrder(aCurrentOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCurrentOrders()) { index = numberOfCurrentOrders() - 1; }
      currentOrders.remove(aCurrentOrder);
      currentOrders.add(index, aCurrentOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCurrentOrderAt(Order aCurrentOrder, int index)
  {
    boolean wasAdded = false;
    if(currentOrders.contains(aCurrentOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCurrentOrders()) { index = numberOfCurrentOrders() - 1; }
      currentOrders.remove(aCurrentOrder);
      currentOrders.add(index, aCurrentOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCurrentOrderAt(aCurrentOrder, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPricedMenuItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PricedMenuItem addPricedMenuItem(double aPrice, MenuItem aMenuItem)
  {
    return new PricedMenuItem(aPrice, this, aMenuItem);
  }

  public boolean addPricedMenuItem(PricedMenuItem aPricedMenuItem)
  {
    boolean wasAdded = false;
    if (pricedMenuItems.contains(aPricedMenuItem)) { return false; }
    RestoApp existingRestoApp = aPricedMenuItem.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aPricedMenuItem.setRestoApp(this);
    }
    else
    {
      pricedMenuItems.add(aPricedMenuItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePricedMenuItem(PricedMenuItem aPricedMenuItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aPricedMenuItem, as it must always have a restoApp
    if (!this.equals(aPricedMenuItem.getRestoApp()))
    {
      pricedMenuItems.remove(aPricedMenuItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPricedMenuItemAt(PricedMenuItem aPricedMenuItem, int index)
  {  
    boolean wasAdded = false;
    if(addPricedMenuItem(aPricedMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPricedMenuItems()) { index = numberOfPricedMenuItems() - 1; }
      pricedMenuItems.remove(aPricedMenuItem);
      pricedMenuItems.add(index, aPricedMenuItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePricedMenuItemAt(PricedMenuItem aPricedMenuItem, int index)
  {
    boolean wasAdded = false;
    if(pricedMenuItems.contains(aPricedMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPricedMenuItems()) { index = numberOfPricedMenuItems() - 1; }
      pricedMenuItems.remove(aPricedMenuItem);
      pricedMenuItems.add(index, aPricedMenuItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPricedMenuItemAt(aPricedMenuItem, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfToGoOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TogoOrder addToGoOrder(String aName, Date aDate, Time aTime)
  {
    return new TogoOrder(aName, aDate, aTime, this);
  }

  public boolean addToGoOrder(TogoOrder aToGoOrder)
  {
    boolean wasAdded = false;
    if (toGoOrders.contains(aToGoOrder)) { return false; }
    RestoApp existingRestoApp = aToGoOrder.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aToGoOrder.setRestoApp(this);
    }
    else
    {
      toGoOrders.add(aToGoOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeToGoOrder(TogoOrder aToGoOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aToGoOrder, as it must always have a restoApp
    if (!this.equals(aToGoOrder.getRestoApp()))
    {
      toGoOrders.remove(aToGoOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addToGoOrderAt(TogoOrder aToGoOrder, int index)
  {  
    boolean wasAdded = false;
    if(addToGoOrder(aToGoOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfToGoOrders()) { index = numberOfToGoOrders() - 1; }
      toGoOrders.remove(aToGoOrder);
      toGoOrders.add(index, aToGoOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveToGoOrderAt(TogoOrder aToGoOrder, int index)
  {
    boolean wasAdded = false;
    if(toGoOrders.contains(aToGoOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfToGoOrders()) { index = numberOfToGoOrders() - 1; }
      toGoOrders.remove(aToGoOrder);
      toGoOrders.add(index, aToGoOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addToGoOrderAt(aToGoOrder, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfCurrentToGoOrders()
  {
    return 0;
  }

  public boolean addCurrentToGoOrder(TogoOrder aCurrentToGoOrder)
  {
    boolean wasAdded = false;
    if (currentToGoOrders.contains(aCurrentToGoOrder)) { return false; }
    currentToGoOrders.add(aCurrentToGoOrder);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCurrentToGoOrder(TogoOrder aCurrentToGoOrder)
  {
    boolean wasRemoved = false;
    if (currentToGoOrders.contains(aCurrentToGoOrder))
    {
      currentToGoOrders.remove(aCurrentToGoOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCurrentToGoOrderAt(TogoOrder aCurrentToGoOrder, int index)
  {  
    boolean wasAdded = false;
    if(addCurrentToGoOrder(aCurrentToGoOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCurrentToGoOrders()) { index = numberOfCurrentToGoOrders() - 1; }
      currentToGoOrders.remove(aCurrentToGoOrder);
      currentToGoOrders.add(index, aCurrentToGoOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCurrentToGoOrderAt(TogoOrder aCurrentToGoOrder, int index)
  {
    boolean wasAdded = false;
    if(currentToGoOrders.contains(aCurrentToGoOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCurrentToGoOrders()) { index = numberOfCurrentToGoOrders() - 1; }
      currentToGoOrders.remove(aCurrentToGoOrder);
      currentToGoOrders.add(index, aCurrentToGoOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCurrentToGoOrderAt(aCurrentToGoOrder, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfToGoBill()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TogoBill addToGoBill(TogoOrder aToGoOrder)
  {
    return new TogoBill(aToGoOrder, this);
  }

  public boolean addToGoBill(TogoBill aToGoBill)
  {
    boolean wasAdded = false;
    if (toGoBill.contains(aToGoBill)) { return false; }
    RestoApp existingRestoApp = aToGoBill.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aToGoBill.setRestoApp(this);
    }
    else
    {
      toGoBill.add(aToGoBill);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeToGoBill(TogoBill aToGoBill)
  {
    boolean wasRemoved = false;
    //Unable to remove aToGoBill, as it must always have a restoApp
    if (!this.equals(aToGoBill.getRestoApp()))
    {
      toGoBill.remove(aToGoBill);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addToGoBillAt(TogoBill aToGoBill, int index)
  {  
    boolean wasAdded = false;
    if(addToGoBill(aToGoBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfToGoBill()) { index = numberOfToGoBill() - 1; }
      toGoBill.remove(aToGoBill);
      toGoBill.add(index, aToGoBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveToGoBillAt(TogoBill aToGoBill, int index)
  {
    boolean wasAdded = false;
    if(toGoBill.contains(aToGoBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfToGoBill()) { index = numberOfToGoBill() - 1; }
      toGoBill.remove(aToGoBill);
      toGoBill.add(index, aToGoBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addToGoBillAt(aToGoBill, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfBills()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bill addBill(Order aOrder, Seat... allIssuedForSeats)
  {
    return new Bill(aOrder, this, allIssuedForSeats);
  }

  public boolean addBill(Bill aBill)
  {
    boolean wasAdded = false;
    if (bills.contains(aBill)) { return false; }
    RestoApp existingRestoApp = aBill.getRestoApp();
    boolean isNewRestoApp = existingRestoApp != null && !this.equals(existingRestoApp);
    if (isNewRestoApp)
    {
      aBill.setRestoApp(this);
    }
    else
    {
      bills.add(aBill);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBill(Bill aBill)
  {
    boolean wasRemoved = false;
    //Unable to remove aBill, as it must always have a restoApp
    if (!this.equals(aBill.getRestoApp()))
    {
      bills.remove(aBill);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addBillAt(Bill aBill, int index)
  {  
    boolean wasAdded = false;
    if(addBill(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBillAt(Bill aBill, int index)
  {
    boolean wasAdded = false;
    if(bills.contains(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBillAt(aBill, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (reservations.size() > 0)
    {
      Reservation aReservation = reservations.get(reservations.size() - 1);
      aReservation.delete();
      reservations.remove(aReservation);
    }
    
    while (tables.size() > 0)
    {
      Table aTable = tables.get(tables.size() - 1);
      aTable.delete();
      tables.remove(aTable);
    }
    
    currentTables.clear();
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
    currentOrders.clear();
    Menu existingMenu = menu;
    menu = null;
    if (existingMenu != null)
    {
      existingMenu.delete();
    }
    while (pricedMenuItems.size() > 0)
    {
      PricedMenuItem aPricedMenuItem = pricedMenuItems.get(pricedMenuItems.size() - 1);
      aPricedMenuItem.delete();
      pricedMenuItems.remove(aPricedMenuItem);
    }
    
    while (toGoOrders.size() > 0)
    {
      TogoOrder aToGoOrder = toGoOrders.get(toGoOrders.size() - 1);
      aToGoOrder.delete();
      toGoOrders.remove(aToGoOrder);
    }
    
    currentToGoOrders.clear();
    while (toGoBill.size() > 0)
    {
      TogoBill aToGoBill = toGoBill.get(toGoBill.size() - 1);
      aToGoBill.delete();
      toGoBill.remove(aToGoBill);
    }
    
    while (bills.size() > 0)
    {
      Bill aBill = bills.get(bills.size() - 1);
      aBill.delete();
      bills.remove(aBill);
    }
    
  }

  // line 8 "../../../../../RestoAppPersistence.ump"
   public void reinitialize(){
    MenuItem.renitializeNameToHashmap(this.getMenu().getMenuItems());
	  Order.reinitializeNextNumber(this.getOrders());
	  Reservation.reinitializeNextReservationNumber(this.getReservations());
	  Table.reinitializeNumberToHashMap(this.getTables());
	   if(toGoOrders == null) {
		  toGoOrders = new ArrayList<TogoOrder>();
	  }
	  if(currentToGoOrders == null) {
		  currentToGoOrders = new ArrayList<TogoOrder>();
	  }
	  if(toGoBill == null) {
		  toGoBill = new ArrayList<TogoBill>();
	  }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 6 "../../../../../RestoAppPersistence.ump"
  private static final long serialVersionUID =  -2683593616927798071L ;

  
}