/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;
import java.util.*;

// line 55 "../../../../../RestoAppPersistence.ump"
// line 81 "../../../../../RestoApp v3(2).ump"
public class MenuItem implements Serializable
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum ItemCategory { Appetizer, Main, Dessert, AlcoholicBeverage, NonAlcoholicBeverage }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, MenuItem> menuitemsByName = new HashMap<String, MenuItem>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private String name;
  private ItemCategory itemCategory;

  //MenuItem Associations
  private List<PricedMenuItem> pricedMenuItems;
  private PricedMenuItem currentPricedMenuItem;
  private Menu menu;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuItem(String aName, Menu aMenu)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    pricedMenuItems = new ArrayList<PricedMenuItem>();
    boolean didAddMenu = setMenu(aMenu);
    if (!didAddMenu)
    {
      throw new RuntimeException("Unable to create menuItem due to menu");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      menuitemsByName.remove(anOldName);
    }
    menuitemsByName.put(aName, this);
    return wasSet;
  }

  public boolean setItemCategory(ItemCategory aItemCategory)
  {
    boolean wasSet = false;
    itemCategory = aItemCategory;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public static MenuItem getWithName(String aName)
  {
    return menuitemsByName.get(aName);
  }

  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public ItemCategory getItemCategory()
  {
    return itemCategory;
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

  public PricedMenuItem getCurrentPricedMenuItem()
  {
    return currentPricedMenuItem;
  }

  public boolean hasCurrentPricedMenuItem()
  {
    boolean has = currentPricedMenuItem != null;
    return has;
  }

  public Menu getMenu()
  {
    return menu;
  }

  public boolean isNumberOfPricedMenuItemsValid()
  {
    boolean isValid = numberOfPricedMenuItems() >= minimumNumberOfPricedMenuItems();
    return isValid;
  }

  public static int minimumNumberOfPricedMenuItems()
  {
    return 1;
  }

  public PricedMenuItem addPricedMenuItem(double aPrice, RestoApp aRestoApp)
  {
    PricedMenuItem aNewPricedMenuItem = new PricedMenuItem(aPrice, aRestoApp, this);
    return aNewPricedMenuItem;
  }

  public boolean addPricedMenuItem(PricedMenuItem aPricedMenuItem)
  {
    boolean wasAdded = false;
    if (pricedMenuItems.contains(aPricedMenuItem)) { return false; }
    MenuItem existingMenuItem = aPricedMenuItem.getMenuItem();
    boolean isNewMenuItem = existingMenuItem != null && !this.equals(existingMenuItem);

    if (isNewMenuItem && existingMenuItem.numberOfPricedMenuItems() <= minimumNumberOfPricedMenuItems())
    {
      return wasAdded;
    }
    if (isNewMenuItem)
    {
      aPricedMenuItem.setMenuItem(this);
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
    //Unable to remove aPricedMenuItem, as it must always have a menuItem
    if (this.equals(aPricedMenuItem.getMenuItem()))
    {
      return wasRemoved;
    }

    //menuItem already at minimum (1)
    if (numberOfPricedMenuItems() <= minimumNumberOfPricedMenuItems())
    {
      return wasRemoved;
    }

    pricedMenuItems.remove(aPricedMenuItem);
    wasRemoved = true;
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

  public boolean setCurrentPricedMenuItem(PricedMenuItem aNewCurrentPricedMenuItem)
  {
    boolean wasSet = false;
    currentPricedMenuItem = aNewCurrentPricedMenuItem;
    wasSet = true;
    return wasSet;
  }

  public boolean setMenu(Menu aMenu)
  {
    boolean wasSet = false;
    if (aMenu == null)
    {
      return wasSet;
    }

    Menu existingMenu = menu;
    menu = aMenu;
    if (existingMenu != null && !existingMenu.equals(aMenu))
    {
      existingMenu.removeMenuItem(this);
    }
    menu.addMenuItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    menuitemsByName.remove(getName());
    for(int i=pricedMenuItems.size(); i > 0; i--)
    {
      PricedMenuItem aPricedMenuItem = pricedMenuItems.get(i - 1);
      aPricedMenuItem.delete();
    }
    currentPricedMenuItem = null;
    Menu placeholderMenu = menu;
    this.menu = null;
    if(placeholderMenu != null)
    {
      placeholderMenu.removeMenuItem(this);
    }
  }

  // line 61 "../../../../../RestoAppPersistence.ump"
   public static  void renitializeNameToHashmap(List<MenuItem> items){
    menuitemsByName = new HashMap<String, MenuItem>();
		 for(MenuItem item : items) {
			 menuitemsByName.put(item.getName(), item);
		 }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "itemCategory" + "=" + (getItemCategory() != null ? !getItemCategory().equals(this)  ? getItemCategory().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentPricedMenuItem = "+(getCurrentPricedMenuItem()!=null?Integer.toHexString(System.identityHashCode(getCurrentPricedMenuItem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "menu = "+(getMenu()!=null?Integer.toHexString(System.identityHashCode(getMenu())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 58 "../../../../../RestoAppPersistence.ump"
  private static final long serialVersionUID = -1776230320092632776L ;

  
}