/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;

// line 30 "../../../../../RestoAppPersistence.ump"
// line 60 "../../../../../RestoApp v3(2).ump"
public class TogoOrderItem implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TogoOrderItem Attributes
  private int quantity;

  //TogoOrderItem Associations
  private PricedMenuItem pricedMenuItem;
  private TogoOrder togoOrder;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TogoOrderItem(int aQuantity, PricedMenuItem aPricedMenuItem, TogoOrder aTogoOrder)
  {
    quantity = aQuantity;
    boolean didAddPricedMenuItem = setPricedMenuItem(aPricedMenuItem);
    if (!didAddPricedMenuItem)
    {
      throw new RuntimeException("Unable to create toGoOrderItem due to pricedMenuItem");
    }
    boolean didAddTogoOrder = setTogoOrder(aTogoOrder);
    if (!didAddTogoOrder)
    {
      throw new RuntimeException("Unable to create toGoOrderItem due to togoOrder");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public PricedMenuItem getPricedMenuItem()
  {
    return pricedMenuItem;
  }

  public TogoOrder getTogoOrder()
  {
    return togoOrder;
  }

  public boolean setPricedMenuItem(PricedMenuItem aPricedMenuItem)
  {
    boolean wasSet = false;
    if (aPricedMenuItem == null)
    {
      return wasSet;
    }

    PricedMenuItem existingPricedMenuItem = pricedMenuItem;
    pricedMenuItem = aPricedMenuItem;
    if (existingPricedMenuItem != null && !existingPricedMenuItem.equals(aPricedMenuItem))
    {
      existingPricedMenuItem.removeToGoOrderItem(this);
    }
    pricedMenuItem.addToGoOrderItem(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setTogoOrder(TogoOrder aTogoOrder)
  {
    boolean wasSet = false;
    if (aTogoOrder == null)
    {
      return wasSet;
    }

    TogoOrder existingTogoOrder = togoOrder;
    togoOrder = aTogoOrder;
    if (existingTogoOrder != null && !existingTogoOrder.equals(aTogoOrder))
    {
      existingTogoOrder.removeToGoOrderItem(this);
    }
    togoOrder.addToGoOrderItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    PricedMenuItem placeholderPricedMenuItem = pricedMenuItem;
    this.pricedMenuItem = null;
    if(placeholderPricedMenuItem != null)
    {
      placeholderPricedMenuItem.removeToGoOrderItem(this);
    }
    TogoOrder placeholderTogoOrder = togoOrder;
    this.togoOrder = null;
    if(placeholderTogoOrder != null)
    {
      placeholderTogoOrder.removeToGoOrderItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "pricedMenuItem = "+(getPricedMenuItem()!=null?Integer.toHexString(System.identityHashCode(getPricedMenuItem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "togoOrder = "+(getTogoOrder()!=null?Integer.toHexString(System.identityHashCode(getTogoOrder())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 33 "../../../../../RestoAppPersistence.ump"
  private static final long serialVersionUID = 66666666L ;

  
}