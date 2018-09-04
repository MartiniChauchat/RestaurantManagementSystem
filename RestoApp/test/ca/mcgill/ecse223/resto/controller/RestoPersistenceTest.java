package ca.mcgill.ecse223.resto.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.RestoApp;

public class RestoPersistenceTest {
	RestoApp resto;
	String filename = "test/menu.resto";
	@Before
	public void setUp() {
		
	}
	
	//run after every test
	@After
	public void tearDown() {
		//RestoApplication.resetResto();
	}
	
	@Test
	public void readPersistence() {
		RestoApplication.setFilename(filename);
		resto = RestoApplication.getResto();
		for(MenuItem item : resto.getMenu().getMenuItems()) {
			System.out.println(item.getItemCategory());
		}
	}
}
