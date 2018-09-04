package ca.mcgill.ecse223.resto.controller;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.Menu;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.PricedMenuItem;
import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.model.Table;
public class RestoControllerTest {
	RestoApp resto;
	//set up before each test
	@Before
	public void setUp() {
		RestoApplication.setFilename("test/test1.resto");
		resto = RestoApplication.getResto();
	}
	
	//run after every test
	@After
	public void tearDown() {
		//RestoApplication.resetResto();
	}
	
	//Test for controller method
	@Test
	public void getCategoryTest() {
		ItemCategory[] categories = ItemCategory.values();
		//Appetizer, Main, Dessert, AlcoholicBeverage, NonAlcoholicBeverage
		assertEquals(categories[0], ItemCategory.Appetizer);
		assertEquals(categories[1], ItemCategory.Main);
		assertEquals(categories[2], ItemCategory.Dessert);
		assertEquals(categories[3], ItemCategory.AlcoholicBeverage);
		assertEquals(categories[4], ItemCategory.NonAlcoholicBeverage);
		
	}
	
	@Test
	public void getMenuItemTest() {
		Menu menu = resto.getMenu();
		MenuItem item1 = menu.addMenuItem("Prok Bone Soup");
		item1.setItemCategory(ItemCategory.Main);
		PricedMenuItem price1 = new PricedMenuItem(12.5, resto, item1);
		item1.setCurrentPricedMenuItem(price1);
		MenuItem item2 = new MenuItem("Prok Bone", menu);
		item2.setItemCategory(ItemCategory.Main);
		try{
			ArrayList<MenuItem> items = RestoController.getMenuItemByCategory(ItemCategory.Main); 
			assertEquals(items.size(), 1);
			assertEquals(items.get(0).getName(),"Prok Bone Soup" );
			RestoApplication.save();
		}catch (InvalidInputException error){
			
		}
		
		try{
			ArrayList<MenuItem> items = RestoController.getMenuItemByCategory(null); 
		}catch (InvalidInputException error){
			 assertEquals(error.getMessage(), "Invalid Category!");
		}
	}
	
	//test for moveTable
	@Test
	public void moveTableTest() {
		resto.addTable(0, 5, 5, 2, 2);
		Table a=resto.addTable(1, 8, 8, 2, 2);
		try {
			RestoController.moveTable(a,5,5);
			assertEquals(a.getX(), 8);
			assertEquals(a.getY(), 8);
			RestoApplication.save();

		}
		catch (InvalidInputException error){
			
		}
	}

	//Test for remove table
		@Test
		public void removeTableTest() {
			RestoApp resto = RestoApplication.getResto();
			Date newDate = new Date(234234234242L);
			Time newTime = new Time(12345L);
			Table a=resto.addTable(5,5,5,2,2);
			a.addSeat();
			resto.addReservation(newDate,newTime, 2, "tianzhu", "gfutianzhu@gmail.com", "43894849309", a);
		}


	
	//addTable 
	@Test
	public void addTableTest(){
		resto.addTable(7, 2, 2, 3, 1);
		//Table testTable = Table.getWithNumber(8);
		try{
			RestoController.createTable(8, 6, 2, 2, 3, 1);
			Table testTable = Table.getWithNumber(8);
			assertEquals(testTable.getNumber(), 8);
			assertEquals(testTable.numberOfCurrentSeats(), 6);
		
		}
		catch (InvalidInputException error){
		}
	}

}
