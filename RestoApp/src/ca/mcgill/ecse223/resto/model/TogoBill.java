/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;

// line 37 "../../../../../RestoAppPersistence.ump"
// line 66 "../../../../../RestoApp v3(2).ump"
public class TogoBill implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TogoBill Associations
  private TogoOrder toGoOrder;
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TogoBill(TogoOrder aToGoOrder, RestoApp aRestoApp)
  {
    boolean didAddToGoOrder = setToGoOrder(aToGoOrder);
    if (!didAddToGoOrder)
    {
      throw new RuntimeException("Unable to create togoBill due to toGoOrder");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create toGoBill due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public TogoOrder getToGoOrder()
  {
    return toGoOrder;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public boolean setToGoOrder(TogoOrder aToGoOrder)
  {
    boolean wasSet = false;
    if (aToGoOrder == null)
    {
      return wasSet;
    }

    TogoOrder existingToGoOrder = toGoOrder;
    toGoOrder = aToGoOrder;
    if (existingToGoOrder != null && !existingToGoOrder.equals(aToGoOrder))
    {
      existingToGoOrder.removeTogoBill(this);
    }
    toGoOrder.addTogoBill(this);
    wasSet = true;
    return wasSet;
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
      existingRestoApp.removeToGoBill(this);
    }
    restoApp.addToGoBill(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TogoOrder placeholderToGoOrder = toGoOrder;
    this.toGoOrder = null;
    if(placeholderToGoOrder != null)
    {
      placeholderToGoOrder.removeTogoBill(this);
    }
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    if(placeholderRestoApp != null)
    {
      placeholderRestoApp.removeToGoBill(this);
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 40 "../../../../../RestoAppPersistence.ump"
  private static final long serialVersionUID = 23333333L ;

  
}