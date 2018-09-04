package ca.mcgill.ecse223.resto.controller;
import java.util.ArrayList;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.view.TableGraphics;
import ca.mcgill.ecse223.resto.model.TogoOrder;
import ca.mcgill.ecse223.resto.model.TogoOrderItem;
import ca.mcgill.ecse223.resto.model.TogoBill;

/**
 * Controller class for resto app
 *
 */
public class RestoController {
	public static ItemCategory[] getAllCategories(){
		return ItemCategory.values();
	}
	
	public static ArrayList<MenuItem> getMenuItemByCategory(ItemCategory c) throws InvalidInputException{
		if(c == null) {
			throw new InvalidInputException("Invalid Category!");
		}
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		RestoApp resto = RestoApplication.getResto();
		for(MenuItem item : resto.getMenu().getMenuItems()) {
			if(item.hasCurrentPricedMenuItem() && item.getItemCategory() == c) {
				items.add(item);
			}
		}
		return items;
	}
	
	//getCurrentMenuItem method(for UI use)
	public static ArrayList<MenuItem> getCurrentMenuItem() throws InvalidInputException{
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		RestoApp resto = RestoApplication.getResto();
		for(MenuItem item : resto.getMenu().getMenuItems()) {
			if(item.hasCurrentPricedMenuItem()) {
					items.add(item);
			}
		}
		return items;
	}	
	
	//addTable method
	
	public static Table createTable(int tableNum, int seats, int x, int y, int width, int length) throws InvalidInputException{
		
		RestoApp resto = RestoApplication.getResto();
		List<Table> tableList = resto.getCurrentTables();
		
		if (tableNum <= 0 || x <= 0 || y <= 0 || width <= 0 || length <= 0 ) {
			throw new InvalidInputException("Please create a valid table");
		}
		
		for(Table table : tableList){
			int tempX=table.getX();
			int tempY=table.getY();
			int tempWidth=table.getWidth();
			int tempLength=table.getLength();
			int gap = TableGraphics.seatSpace;
			if(((x-gap>tempX-gap&&x-gap<tempX+tempWidth+gap)&&(y-gap>tempY-gap&&y-gap<tempY+tempLength+gap))||
				((x-gap>tempX-gap&&x-gap<tempX+tempWidth+gap)&&(y+length+gap>tempY-gap&&y+length+gap<tempY+tempLength+gap))||
				((x+width+gap>tempX-gap&&x+width+gap<tempX+tempWidth+gap)&&(y-gap>tempY-gap&&y-gap<tempY+tempLength+gap))||
				((x+width+gap>tempX-gap&&x+width+gap<tempX+tempWidth+gap)&&(y+length+gap>tempY-gap&&y+length+gap<tempY+tempLength+gap))){ 
				throw new InvalidInputException("Overlapping table!");
		}
	}
	Table addedTable;
	try {
		addedTable = new Table(tableNum, x, y, width, length, resto);
		for(int i = 0; i < seats; i++){
			Seat seat = new Seat(addedTable);
			addedTable.addCurrentSeat(seat);
		}

		resto.addTable(addedTable);
		resto.addCurrentTable(addedTable);
		
		
	}catch (RuntimeException e) {
		throw new InvalidInputException(e.getMessage());
	}
	
	
	try {
		RestoApplication.save();
	} catch (RuntimeException e) {
		throw new RuntimeException("Fail to save data");
	}
	
	return addedTable;
	
}

	//moveTable method 
public static void moveTable(Table table, int x, int y) throws InvalidInputException {
	if(table == null) {
		throw new InvalidInputException("Please select a table.");
	}
	if (x<0||y<0) {
		throw new InvalidInputException("Please enter positive location.");
	}
	int width,length;
	width=table.getWidth();
	length=table.getLength();
	RestoApp resto = RestoApplication.getResto();
	boolean isAvailable=true;
	for(Table tempTable : resto.getCurrentTables()) {
		if (tempTable.equals(table)) {
			continue;
		}
		int tempX=tempTable.getX();
		int tempY=tempTable.getY();
		int tempWidth=tempTable.getWidth();
		int tempLength=tempTable.getLength();
		int gap = TableGraphics.seatSpace;
		if(((x-gap>tempX-gap&&x-gap<tempX+tempWidth+gap)&&(y-gap>tempY-gap&&y-gap<tempY+tempLength+gap))||
			((x-gap>tempX-gap&&x-gap<tempX+tempWidth+gap)&&(y+length+gap>tempY-gap&&y+length+gap<tempY+tempLength+gap))||
			((x+width+gap>tempX-gap&&x+width+gap<tempX+tempWidth+gap)&&(y-gap>tempY-gap&&y-gap<tempY+tempLength+gap))||
			((x+width+gap>tempX-gap&&x+width+gap<tempX+tempWidth+gap)&&(y+length+gap>tempY-gap&&y+length+gap<tempY+tempLength+gap))) 
		{
			isAvailable=false;
			throw new InvalidInputException("Please choose another location.");
		}
	} 
	if (isAvailable) {
		table.setX(x);
		table.setY(y);
	}
	try {
		RestoApplication.save();
	}catch (RuntimeException e) {
		throw new RuntimeException("fail to save data");
	}
}

	//update table number and number of seats
public static void updateTable(int Old_Num, int New_Num, int Numseats) throws InvalidInputException{

	Table target_table;
	target_table = Table.getWithNumber(Old_Num);

	RestoApp resto = RestoApplication.getResto();
	List<Table>currentTablelist = resto.getCurrentTables();
	if(currentTablelist.contains(Table.getWithNumber(New_Num)))
	{
		if(New_Num!=Old_Num) {
			throw new InvalidInputException("Pick another Table number!");
		}
	}
	if(target_table == null)
	{
		throw new InvalidInputException("This table number does not exist");
	}
	if(New_Num <0)
	{
		throw new InvalidInputException("Table Number Cannot be Negative!");
	}
	if(target_table.hasReservations())
	{
		throw new InvalidInputException("The table is reserved");
	}
	if(target_table.hasOrders())
	{
		throw new InvalidInputException("This table is in use");
	}
	if(Numseats < Table.minimumNumberOfCurrentSeats())
	{
		throw new InvalidInputException("Number of Seats Cannot be Negative!");
	}
	target_table.setNumber(New_Num);
	if(target_table.getNumber() == Old_Num){
		throw new InvalidInputException("Number cannot be same with other table!");
	}
	int Now_SeatNum = target_table.numberOfCurrentSeats();
	if (Numseats > Now_SeatNum)
	{
		for(int j = 0; j < (Numseats-Now_SeatNum);j++)
		{
			target_table.addCurrentSeat(target_table.addSeat());
		}
	}
	if (Numseats <= Now_SeatNum)
	{
		for(int k = Now_SeatNum-1; k >=(Numseats);k--)
		{
			target_table.removeCurrentSeat(target_table.getCurrentSeat(k));
		}
	}
	try {
		RestoApplication.save();
	}catch (RuntimeException e) {
		throw new RuntimeException("fail to save data");
	}
}

public static List<Table> getCurrentTables(){
	RestoApp resto = RestoApplication.getResto();
	return resto.getCurrentTables();
}

public static List<Table> getTables(){
	RestoApp resto = RestoApplication.getResto();
	return resto.getTables();
}


	//removeTable method

public static void removeTable(Table table) throws InvalidInputException{
	if (table == null) {
		throw new InvalidInputException("InvaliTable!");
	}
	if(table.hasOrders()) {
		if(table.hasReservations()) {
			throw new InvalidInputException("the table is reserved");
		}
	}
	RestoApp r = RestoApplication.getResto();
	List<Order> currentOrders = r.getCurrentOrders();
	for(Order order: currentOrders) {
		for(Table tableInOrder : order.getTables()) {
			if(tableInOrder.getNumber() == table.getNumber()) {
				throw new InvalidInputException("this table is in use");
				
			}
		}
		
	}
	r.removeCurrentTable(table);
	try {
		RestoApplication.save();
	}catch (RuntimeException e) {
		throw new RuntimeException("fail to save data");
	}
}

	//startOrder method
public static void startOrder(List<Table>tables) throws InvalidInputException {
	RestoApp r=RestoApplication.getResto();
	List<Table> currentTables = r.getCurrentTables();
	for(Table table: currentTables) {
		if(!currentTables.contains(table)) {
			throw new InvalidInputException("Table is not a current table");
		}
	}
	boolean orderCreated = false;
	Order newOrder=null;
	for(Table table: tables) {
		if(orderCreated) {
			table.addToOrder(newOrder);
		}else {
			Order lastOrder=null;
			if(table.numberOfOrders()>0) {
				lastOrder=table.getOrder(table.numberOfOrders()-1);
			}
			table.startOrder();
			if(table.numberOfOrders()>0&&!table.getOrder(table.numberOfOrders()-1).equals(lastOrder)) {
				orderCreated =true;
				newOrder =table.getOrder(table.numberOfOrders()-1);
			}
		}
		
	}
	if(!orderCreated) {
		throw new InvalidInputException("order hasn't been created");
	}
	
	r.addCurrentOrder(newOrder);

	
	try {
		RestoApplication.save();
	}catch (RuntimeException e) {
		throw new RuntimeException("fail to save data");
	}

}

	//helper method for endOrder method (stated in sequence diagram)
private static boolean allTablesAvailableOrDifferentCurrentOrder(List<Table> tables, Order order){		
	
	for(Table table: tables){
		
		if (!(table.getStatus() == Table.Status.Available)) {
			if(table.numberOfOrders() > 0) {
				if(table.getOrder(table.getOrders().size()-1).equals(order)){
					return false;
				}
			}
		}
	}
	
	return true;
}

	//end order method
public static void endOrder(Order order) throws InvalidInputException{
	
	RestoApp resto = RestoApplication.getResto();
	List<Order> currentOrders = resto.getCurrentOrders();
	if (order == null)
		throw new InvalidInputException("Order can't be empty!");
	else if(!currentOrders.contains(order))
		throw new InvalidInputException("Can't find the order!");
	
	List<Table> orderTables = order.getTables();
	List<Table> orderTablescopy = new ArrayList<Table>();
	
	for(Table table: orderTables) {
		orderTablescopy.add(table);
	}
	
	for(Table table: orderTablescopy){
		if(table.numberOfOrders() > 0 && table.getOrder(table.numberOfOrders()-1).equals(order)){
			table.endOrder(order);
		}		
		
		if(allTablesAvailableOrDifferentCurrentOrder(orderTables, order)){ 
			resto.removeCurrentOrder(order);																				
		}
		
	}
	
	try {
		RestoApplication.save();
	} catch (RuntimeException e) {
		throw new RuntimeException("Fail to save data");
	}
	
	
}

	//make reservation method
@SuppressWarnings({ "deprecation" })
public static void makeReservation(int numInParty, String name, String email, String phoneNum, Date date, Time time, List<Table> tables) throws InvalidInputException{
	RestoApp resto = RestoApplication.getResto();
		//check time after current time
	Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
	Time currentTime = new Time(Calendar.getInstance().getTime().getTime());
	System.out.println(date.getYear());
	System.out.println(currentDate.getYear());
	boolean yearEqual = date.getYear()==currentDate.getYear();
	boolean monthEqual = date.getMonth()==currentDate.getMonth();
	boolean dateEqual = date.getDate() == currentDate.getDate();
	boolean timeBefore = time.getHours()<=currentTime.getHours();
	boolean dateBefore = date.before(currentDate) ;
	if (date.getYear()<currentDate.getYear() || date.getMonth() <currentDate.getMonth() || date.getDate() < currentDate.getMonth() || ((date.getYear()==currentDate.getYear()&&date.getMonth()==currentDate.getMonth()&&date.getDate() == currentDate.getDate() && time.getHours()<=currentTime.getHours()))) {
		throw new InvalidInputException("Please choose a time after current time");
	}
	int seatCapacity=0;
		//check time overlap
	for (Table table:tables) {
		seatCapacity+=table.numberOfCurrentSeats();
		for (Reservation reservation:table.getReservations()) {
			boolean timeCheck=false;
			Time tempTime=reservation.getTime();
			if (Math.abs(time.getHours()*60-tempTime.getHours()*60+time.getMinutes()-tempTime.getMinutes())<120) {
				timeCheck=true;
			}
			if(date.equals(reservation.getDate())&&timeCheck) {
				throw new InvalidInputException("Reservation Overlap. Please check time table");
			}
		}
	}
		//check capacity
	if (numInParty>seatCapacity) {
		throw new InvalidInputException("Number of people exceeds capacity");
	}
		//convert tableList to tableArray
	Table[] tableArray=new Table[tables.size()];
	tables.toArray(tableArray);
	resto.addReservation(date, time, numInParty, name, email, phoneNum, tableArray);
	try {
		RestoApplication.save();
	}catch (RuntimeException e) {
		throw new RuntimeException("Fail to save data");
	}
}

	//method to get all Orders
public static List<Order> getCurrentOrders(){
	return RestoApplication.getResto().getCurrentOrders();
}

public static List<TogoOrder> getCurrentTogoOrders(){
	return RestoApplication.getResto().getCurrentToGoOrders();
}

	//method to add a orderitem to seats
public static void orderMenuItem(MenuItem menuitem, int quantity, List<Seat> seats) throws InvalidInputException {
	
	RestoApp resto = RestoApplication.getResto();
	List<Table> tables = resto.getCurrentTables();
	if(menuitem == null||quantity<=0||seats==null) {
		throw new InvalidInputException("One of the input is invalid!");
	}
	if(!menuitem.hasCurrentPricedMenuItem()) {
		throw new InvalidInputException("This item is currently unavailable! ");
	}
	for(Seat aSeat: seats) {
		Table aTable = aSeat.getTable();
		if(!tables.contains(aTable)) {
			throw new InvalidInputException("One of the selected tables is not currently available!");
		}
		List<Seat>currentSeats = aTable.getCurrentSeats();
		if(!currentSeats.contains(aSeat)) {
			throw new InvalidInputException("One of the selected seats is not currently available!");
		}	
	}
	Table reference_table = seats.get(0).getTable();
	Order reference_order = null;
	if(reference_table.numberOfOrders()>0) {
		reference_order = reference_table.getOrder(reference_table.numberOfOrders()-1);
	}
	for(Seat isaSeat: seats) {
		if(isaSeat.getTable().getOrders().size()<1) {
			throw new InvalidInputException("At least of the seats has no order, create one first!");
		}
	}
	for(Seat aseat: seats) {
		if(!(aseat.getTable().getOrder(aseat.getTable().numberOfOrders()-1).equals(reference_order)))
		{
			throw new InvalidInputException("At least one seat has a different order!");
		}
	}
	Seat[] seatArray = new Seat[seats.size()];
	seats.toArray(seatArray);
	PricedMenuItem pmi = menuitem.getCurrentPricedMenuItem();
	boolean itemCreated = false;
	OrderItem newitem = null;
	for(Seat oneSeat: seats) {
		Table oneTable = oneSeat.getTable();
		if(itemCreated == true) {
			oneTable.addToOrderItem(newitem, oneSeat);
		}
		else {
			OrderItem lastitem = null;
			if(reference_order.numberOfOrderItems()>0) {
				lastitem = reference_order.getOrderItem(reference_order.numberOfOrderItems()-1);
			}
			oneTable.orderItem(quantity, reference_order, oneSeat, pmi);
			if(reference_order.numberOfOrderItems()>0 &&
					!reference_order.getOrderItem(reference_order.numberOfOrderItems()-1).equals(lastitem)) {
				itemCreated = true;
				newitem = reference_order.getOrderItem(reference_order.numberOfOrderItems()-1);
			}
		}
		try {
		RestoApplication.save();
	}catch (RuntimeException e) {
		throw new RuntimeException("Fail to save data");
	}	
	}
}



	//method to issue a bill
public static void issueBillForSeats(List<Seat> seats) throws InvalidInputException {
		//check if all seats are in current table and all seats belong to the same order
	List<Table> currentTables = RestoApplication.getResto().getCurrentTables();
	Order lastOrder = null;
	Order compareOrder = null;
	for(Seat seat : seats){
		Table seatTable = seat.getTable();
		boolean isCurrent = currentTables.contains(seatTable);
		List<Seat> currentSeats = seatTable.getCurrentSeats();
		isCurrent =  isCurrent && currentSeats.contains(seat);
		if(!isCurrent){
			throw new InvalidInputException("Seat in Table"+seat.getTable().getNumber()+
				" is not a current seat, or table is not a current table");
		}
		if(lastOrder == null){
			if(seatTable.numberOfOrders()>0){
				lastOrder = seatTable.getOrder(seatTable.numberOfOrders()-1);
			}else{
				throw new InvalidInputException("Table"+seatTable.getNumber()+" has no orders");
			}
		}else{
			compareOrder = null;
			if(seatTable.numberOfOrders()>0){
				compareOrder = seatTable.getOrder(seatTable.numberOfOrders()-1);
			}else{
				throw new InvalidInputException("Table"+seatTable.getNumber()+" has no orders");
			}
			if(compareOrder != lastOrder){
				throw new InvalidInputException("Table"+seatTable.getNumber()+" does not have the same order");
			}
			
		}
	}
	if(lastOrder == null){
		throw new InvalidInputException("No order found");
	}
	
		//bill all seats
	boolean billCreated = false;
	Bill createdBill = null;
	for(Seat seat : seats) {
		if(billCreated) {
			seat.getTable().addToBill(createdBill, seat);
		}else{
			Bill lastBill = null;
			if(seat.numberOfBills() > 0) {
				lastBill = seat.getBill(seat.numberOfBills()-1);
			}
			seat.getTable().billForSeat(lastOrder, seat);
			
			if(seat.numberOfBills() > 0 && !(lastBill==seat.getBill(seat.numberOfBills()-1))) {
				billCreated = true;
				createdBill = seat.getBill(seat.numberOfBills()-1);
			}
		}
	}
	
	if(!billCreated) {
		throw new InvalidInputException("No bill has been created");
	}
	
	try {
		RestoApplication.save();
	}catch (RuntimeException e) {
		throw new RuntimeException("Fail to save data");
	}
}

public static List<OrderItem> getOrderItems(Table table) throws InvalidInputException{
	if(table == null) {
		throw new InvalidInputException("This table does not exist");
	}
	RestoApp resto = RestoApplication.getResto();
	List<Table> tables = resto.getCurrentTables();
	List<Seat> seats = table.getCurrentSeats();
	List<OrderItem> result = new ArrayList<OrderItem>();
	
	
	if(!tables.contains(table)) {
		throw new InvalidInputException("This table does not exist");
	}
	else if(table.getStatus() == Table.Status.Available) {
		throw new InvalidInputException("This table has no orders");
	}
	for(Seat seat : seats){
		List<OrderItem> orderItems = seat.getOrderItems();
		
		for(OrderItem orderItem: orderItems){
			Order order = orderItem.getOrder();
			Order lastOrder = table.getOrder(table.numberOfOrders()-1);
			
			if(lastOrder.equals(order) && !result.contains(orderItem))
				result.add(orderItem);
		}
		
	}
	return result;
	
}

	/**Cancel an orderitem for all tables and seats
	 * 
	 * 
	 * @author:liJamie
	 * @param aOrderitem
	 */
	public static void cancelOrderItem(OrderItem aOrderitem) throws InvalidInputException {
		 if (aOrderitem == null) {
			throw new InvalidInputException("Orderitem cannot be empty");
		}
		List<Order> aOrderList = null;
		List<Table> currentTables = RestoApplication.getResto().getCurrentTables();
		List<OrderItem> aOrderItemList = null;
		for (int i = 0; i < currentTables.size() ; i++) {
			aOrderList = currentTables.get(i).getOrders();
			for (int j = 0; j < aOrderList.size(); j++) {
				aOrderItemList = RestoApplication.getResto().getCurrentTables().get(i).getOrder(j).getOrderItems();
				for (int j2 = 0; j2 < aOrderItemList.size(); j2++) {
					if(aOrderitem.equals(aOrderItemList.get(j2))) {
						//remove it
						RestoApplication.getResto().getCurrentTables().get(i).getOrder(j).getOrderItem(j2).delete();
					}
				}
			}
		}
	}
	
	
	
	
	/**cancel all orderitems for a table
	 * 
	 * @author lijamie
	 * @param aTable
	 */
	public static void cancelOrder(Table aTable) throws InvalidInputException  {
		if (aTable == null) {
			throw new InvalidInputException("Table cannot be empty");
		}

		List<Table> currentTables = RestoApplication.getResto().getCurrentTables();
		if (!currentTables.contains(aTable)) {
			throw new InvalidInputException("Table is not contained");
		}
		if(aTable.numberOfOrders()>0) {
			aTable.cancelOrder();
		}
		
		
	}
	
	
	
	//Add MenuItem method
	public static void addMenuItem (String aName, ItemCategory aItemCategory, double price) throws InvalidInputException{
		RestoApp resto = RestoApplication.getResto();
		Menu menu=resto.getMenu();
		try {
			MenuItem newMenuItem=menu.addMenuItem(aName);
			newMenuItem.setItemCategory(aItemCategory);
			PricedMenuItem newPricedMenuItem=newMenuItem.addPricedMenuItem(price, resto);
			newMenuItem.setCurrentPricedMenuItem(newPricedMenuItem);
		} catch (Exception e) {
			throw new InvalidInputException("Cannot create due to duplicate name");
		}
		try {
			RestoApplication.save();
		}catch (RuntimeException e) {
			throw new RuntimeException("Fail to save data");
		}	
	}
	
	
	//remove MenuItem method
	public static void removeMenuItem(MenuItem aMenuItem) throws InvalidInputException{
		aMenuItem.setCurrentPricedMenuItem(null);
		try {
			RestoApplication.save();
		}catch (RuntimeException e) {
			throw new RuntimeException("Fail to save data");
		}	
	}
	
	//findAllInUseSeat
	public static List<Seat> findAllInUseSeat() {
		RestoApp resto = RestoApplication.getResto();
		List<Seat> seats = new ArrayList<Seat>();
		for(Order order : resto.getCurrentOrders()) {
			for(Table t : order.getTables()) {
				for(Seat s : t.getSeats()) {
					for(int i = s.numberOfOrderItems()-1; i>=0;) {
						if(order.getOrderItems().contains(s.getOrderItem(i))) {
							seats.add(s);
							break;
						}else {
							break;
						}
					}
				}
			}
		}
		return seats;
	}
	
	//findAllBilledSeat
	public static List<Seat> findAllBilledSeat() {
		RestoApp resto = RestoApplication.getResto();
		List<Seat> seats = new ArrayList<Seat>();
		for(Order order : resto.getCurrentOrders()) {
			for(Table t : order.getTables()) {
				for(Seat s : t.getSeats()) {
					for(int i = s.numberOfBills()-1; i>=0;) {
						if(order.getBills().contains(s.getBill(i))) {
							seats.add(s);
							break;
						}else {
							break;
						}
					}
				}
			}
		}
		return seats;
	}
	
	
	  //createTogoOrder
	public static void createTogoOrder(String name) throws InvalidInputException {
		if(name == null) {
			throw new InvalidInputException("Name cannot be null");
		}
		RestoApp r = RestoApplication.getResto();
		Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
		Time currentTime = new Time(Calendar.getInstance().getTime().getTime());
		TogoOrder order = new TogoOrder(name,currentDate, currentTime,r);
		r.addCurrentToGoOrder(order);
		RestoApplication.save();
	}
	
	  //orderItemForOrder(TogoOrder order, int quantity, MenuItem item)
	public static void orderItemForOrder(TogoOrder togoOrder,int quantity, MenuItem item) throws InvalidInputException {
		PricedMenuItem leballedItem;
		if(item.hasCurrentPricedMenuItem()) {
			leballedItem = item.getCurrentPricedMenuItem();
		}
		else {
			throw new InvalidInputException("the item does not have price");
		}
		new TogoOrderItem(quantity,leballedItem,togoOrder);
		RestoApplication.save();
	}
	
	  //cancelItemForOrder(toGoOrderItem item)
	  public static void cancelItemForOrder(TogoOrder order,TogoOrderItem item) {
		  order.removeToGoOrderItem(item);
		  RestoApplication.save();
	  }
	  
	  public static void endToGoOrder(TogoOrder order) {
		  RestoApp r = RestoApplication.getResto();
		  if(order.hasTogoBills()) {
			  r.removeCurrentToGoOrder(order);
		  }else {
			  order.delete();
		  }
	  }
	  

	

	  //issue bill
	public static void issueBill(TogoOrder order,int quantity) {
		RestoApp r = RestoApplication.getResto();
		for(int i=0;i<quantity;i++) {
			new TogoBill(order,r);
		}
		
		RestoApplication.save();
	}

}
