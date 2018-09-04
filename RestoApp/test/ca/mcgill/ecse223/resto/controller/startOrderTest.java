package ca.mcgill.ecse223.resto.controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
public class startOrderTest {
	RestoApp resto;
	//set up before each test
	@Before
	public void setUp() {
		RestoApplication.setFilename("test/test3.resto");
		resto = new RestoApp();
		RestoApplication.setResto(resto);
	}
	
	//run after every test
	@After
	public void tearDown() {
		resto.delete();
	}
	
	@Test
	public void normalTest() {
		try {
			Table table1 = RestoController.createTable(8, 6, 2, 2, 3, 1);
			Table table2 = RestoController.createTable(1, 2, 10, 10, 100, 100);
			List<Table> tables = resto.getCurrentTables();
			RestoController.startOrder(tables);
			assertEquals(table1.getStatus(), Table.Status.NothingOrdered);
			assertEquals(table2.getStatus(), Table.Status.NothingOrdered);

		}catch (InvalidInputException e) {
			System.out.println(e.getLocalizedMessage());
			fail();
		}
	}
	
	@Test
	public void hasOrderTest() {
		try {
			Table table1 = RestoController.createTable(8, 6, 2, 2, 3, 1);
			Table table2 = RestoController.createTable(1, 2, 10, 10, 100, 100);
			ArrayList<Table> tables = new ArrayList<Table>();
			tables.add(table1);
			RestoController.startOrder(tables);
			tables.add(table2);
			RestoController.startOrder(tables);
			assertEquals(table1.getStatus(), Table.Status.NothingOrdered);
			assertEquals(table2.getStatus(), Table.Status.NothingOrdered);
			assertEquals(table2.getOrder(0).getNumber() != table1.getOrder(0).getNumber(), true);
		}catch (InvalidInputException e) {
			System.out.println(e.getLocalizedMessage());
			fail();
		}
	}
	
	@Test
	public void addOrderFailedTest() {
		try {
			Table table1 = RestoController.createTable(8, 6, 2, 2, 3, 1);
			Table table2 = RestoController.createTable(1, 2, 10, 10, 100, 100);
			List<Table> tables = resto.getCurrentTables();
			RestoController.startOrder(tables);
			assertEquals(table1.getStatus(), Table.Status.NothingOrdered);
			assertEquals(table2.getStatus(), Table.Status.NothingOrdered);
			RestoController.startOrder(tables);
		}catch (InvalidInputException e) {
			assertEquals(e.getMessage(), "order hasn't been created");
		}
	}
	
	@Test
	public void addDeletedTableTest() {
		try {
			Table table1 = RestoController.createTable(8, 6, 2, 2, 3, 1);
			Table table2 = RestoController.createTable(1, 2, 10, 10, 100, 100);
			List<Table> tables = resto.getCurrentTables();
			RestoController.removeTable(table1);
			RestoController.startOrder(tables);
		}catch (InvalidInputException e) {
			assertEquals(e.getMessage(), "Table is not a current table");
		}
	}
}
