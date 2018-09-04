/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;
import java.util.*;

// line 90 "../../../../../RestoAppPersistence.ump"
// line 89 "../../../../../RestoApp v3(2).ump"
public class PricedMenuItem implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PricedMenuItem Attributes
  private double price;

  //PricedMenuItem Associations
  private RestoApp restoApp;
  private List<TogoOrderItem> toGoOrderItem;
  private List<OrderItem> orderItems;
  private MenuItem menuItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PricedMenuItem(double aPrice, RestoApp aRestoApp, MenuItem aMenuItem)
  {
    price = aPrice;
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create pricedMenuItem due to restoApp");
    }
    toGoOrderItem = new ArrayList<TogoOrderItem>();
    orderItems = new ArrayList<OrderItem>();
    boolean didAddMenuItem = setMenuItem(aMenuItem);
    if (!didAddMenuItem)
    {
      throw new RuntimeException("Unable to create pricedMenuItem due to menuItem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public double getPrice()
  {
    return price;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public TogoOrderItem getToGoOrderItem(int index)
  {
    TogoOrderItem aToGoOrderItem = toGoOrderItem.get(index);
    return aToGoOrderItem;
  }

  public List<TogoOrderItem> getToGoOrderItem()
  {
    List<TogoOrderItem> newToGoOrderItem = Collections.unmodifiableList(toGoOrderItem);
    return newToGoOrderItem;
  }

  public int numberOfToGoOrderItem()
  {
    int number = toGoOrderItem.size();
    return number;
  }

  public boolean hasToGoOrderItem()
  {
    boolean has = toGoOrderItem.size() > 0;
    return has;
  }

  public int indexOfToGoOrderItem(TogoOrderItem aToGoOrderItem)
  {
    int index = toGoOrderItem.indexOf(aToGoOrderItem);
    return index;
  }

  public OrderItem getOrderItem(int index)
  {
    OrderItem aOrderItem = orderItems.get(index);
    return aOrderItem;
  }

  public List<OrderItem> getOrderItems()
  {
    List<OrderItem> newOrderItems = Collections.unmodifiableList(orderItems);
    return newOrderItems;
  }

  public int numberOfOrderItems()
  {
    int number = orderItems.size();
    return number;
  }

  public boolean hasOrderItems()
  {
    boolean has = orderItems.size() > 0;
    return has;
  }

  public int indexOfOrderItem(OrderItem aOrderItem)
  {
    int index = orderItems.indexOf(aOrderItem);
    return index;
  }

  public MenuItem getMenuItem()
  {
    return menuItem;
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
      existingRestoApp.removePricedMenuItem(this);
    }
    restoApp.addPricedMenuItem(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfToGoOrderItem()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TogoOrderItem addToGoOrderItem(int aQuantity, TogoOrder aTogoOrder)
  {
    return new TogoOrderItem(aQuantity, this, aTogoOrder);
  }

  public boolean addToGoOrderItem(TogoOrderItem aToGoOrderItem)
  {
    boolean wasAdded = false;
    if (toGoOrderItem.contains(aToGoOrderItem)) { return false; }
    PricedMenuItem existingPricedMenuItem = aToGoOrderItem.getPricedMenuItem();
    boolean isNewPricedMenuItem = existingPricedMenuItem != null && !this.equals(existingPricedMenuItem);
    if (isNewPricedMenuItem)
    {
      aToGoOrderItem.setPricedMenuItem(this);
    }
    else
    {
      toGoOrderItem.add(aToGoOrderItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeToGoOrderItem(TogoOrderItem aToGoOrderItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aToGoOrderItem, as it must always have a pricedMenuItem
    if (!this.equals(aToGoOrderItem.getPricedMenuItem()))
    {
      toGoOrderItem.remove(aToGoOrderItem);
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
      if(index > numberOfToGoOrderItem()) { index = numberOfToGoOrderItem() - 1; }
      toGoOrderItem.remove(aToGoOrderItem);
      toGoOrderItem.add(index, aToGoOrderItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveToGoOrderItemAt(TogoOrderItem aToGoOrderItem, int index)
  {
    boolean wasAdded = false;
    if(toGoOrderItem.contains(aToGoOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfToGoOrderItem()) { index = numberOfToGoOrderItem() - 1; }
      toGoOrderItem.remove(aToGoOrderItem);
      toGoOrderItem.add(index, aToGoOrderItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addToGoOrderItemAt(aToGoOrderItem, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOrderItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderItem addOrderItem(int aQuantity, Order aOrder, Seat... allSeats)
  {
    return new OrderItem(aQuantity, this, aOrder, allSeats);
  }

  public boolean addOrderItem(OrderItem aOrderItem)
  {
    boolean wasAdded = false;
    if (orderItems.contains(aOrderItem)) { return false; }
    PricedMenuItem existingPricedMenuItem = aOrderItem.getPricedMenuItem();
    boolean isNewPricedMenuItem = existingPricedMenuItem != null && !this.equals(existingPricedMenuItem);
    if (isNewPricedMenuItem)
    {
      aOrderItem.setPricedMenuItem(this);
    }
    else
    {
      orderItems.add(aOrderItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrderItem(OrderItem aOrderItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrderItem, as it must always have a pricedMenuItem
    if (!this.equals(aOrderItem.getPricedMenuItem()))
    {
      orderItems.remove(aOrderItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOrderItemAt(OrderItem aOrderItem, int index)
  {  
    boolean wasAdded = false;
    if(addOrderItem(aOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderItems()) { index = numberOfOrderItems() - 1; }
      orderItems.remove(aOrderItem);
      orderItems.add(index, aOrderItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderItemAt(OrderItem aOrderItem, int index)
  {
    boolean wasAdded = false;
    if(orderItems.contains(aOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderItems()) { index = numberOfOrderItems() - 1; }
      orderItems.remove(aOrderItem);
      orderItems.add(index, aOrderItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderItemAt(aOrderItem, index);
    }
    return wasAdded;
  }

  public boolean setMenuItem(MenuItem aMenuItem)
  {
    boolean wasSet = false;
    //Must provide menuItem to pricedMenuItem
    if (aMenuItem == null)
    {
      return wasSet;
    }

    if (menuItem != null && menuItem.numberOfPricedMenuItems() <= MenuItem.minimumNumberOfPricedMenuItems())
    {
      return wasSet;
    }

    MenuItem existingMenuItem = menuItem;
    menuItem = aMenuItem;
    if (existingMenuItem != null && !existingMenuItem.equals(aMenuItem))
    {
      boolean didRemove = existingMenuItem.removePricedMenuItem(this);
      if (!didRemove)
      {
        menuItem = existingMenuItem;
        return wasSet;
      }
    }
    menuItem.addPricedMenuItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removePricedMenuItem(this);
    }
    for(int i=toGoOrderItem.size(); i > 0; i--)
    {
      TogoOrderItem aToGoOrderItem = toGoOrderItem.get(i - 1);
      aToGoOrderItem.delete();
    }
    for(int i=orderItems.size(); i > 0; i--)
    {
      OrderItem aOrderItem = orderItems.get(i - 1);
      aOrderItem.delete();
    }
    MenuItem placeholderMenuItem = menuItem;
    this.menuItem = null;
    if(placeholderMenuItem != null)
    {
      placeholderMenuItem.removePricedMenuItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "menuItem = "+(getMenuItem()!=null?Integer.toHexString(System.identityHashCode(getMenuItem())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 93 "../../../../../RestoAppPersistence.ump"
  private static final long serialVersionUID = -4855219931984388104L ;

  
}