package ca.mcgill.ecse223.resto.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.wb.swt.SWTResourceManager;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoController;
import ca.mcgill.ecse223.resto.model.Bill;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;
import ca.mcgill.ecse223.resto.model.Order;
import ca.mcgill.ecse223.resto.model.OrderItem;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.TogoBill;
import ca.mcgill.ecse223.resto.model.TogoOrder;
import ca.mcgill.ecse223.resto.model.TogoOrderItem;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;

public class RestoPage {
	private Display display = Display.getDefault();
	protected Shell shlRestotableManager;
	private CCombo currentOrderCombo;
	private CCombo billOrderCombo;
	private CCombo billNumberCombo;
	private CCombo menuItemCombo;
	private Text insertTableNumberText;
	private Text numberOfSeatsText;
	private Text newXText;
	private Text newYText;
	private ArrayList<Button> menuItemButtons = new ArrayList<Button>();
	private ScrolledComposite scrolledMenuItemsComponent;
	private Composite menuItemComposite;
	private List menuItemDecList;
	private Composite tableComposite;
	//Hashtable for tables
	private Hashtable<Integer, Table> tableHashtable = new Hashtable<Integer, Table>();
	private Hashtable<String, MenuItem> menuItemHashtable = new Hashtable<String, MenuItem>();
	private Hashtable<String, MenuItem> currentItemHashtable = new Hashtable<String, MenuItem>();
	private Hashtable<Integer, Order> orderHashtable = new Hashtable<Integer, Order>();
	private Hashtable<Integer, TogoOrder>toGoOrderHashTable = new Hashtable<Integer, TogoOrder>();
	private ArrayList<Integer> selectedTableNumbers = new ArrayList<Integer>();
	private ArrayList<Seat> selectedSeats = new ArrayList<Seat>();
	private ArrayList<Seat> inUseSeats = new ArrayList<Seat>();
	private ArrayList<Seat> billedSeats = new ArrayList<Seat>();
	private Text xCoordinateText;
	private Text yCoordinateText;
	private Text tableWidthText;
	private Text tableLengthText;
	private Text insertTableNumber2Text;
	private Text dateText;
	private Text timeText;
	private CCombo toGoOrderCombo;
	private Text nameText;
	private Text peoplenumText;
	private Text phoneNumText;
	private Text emailText;
	private Text selectTableText2;
	private Text tableSeatText;
	private Text tableNumberText;
	private Text selecteditemText;
	private Text quantityText;
	private Image ButtonImage = SWTResourceManager.getImage("gradient2_inline.png");
	//table number to draw if no table has been selected by the cursor
	int selectedTableNumber = -1; //Default no table being selected
	private Text selectedTableText;
	private Text tableNumbers;
	private Text moveTableNumbers;
	private Text newItemNameText;
	private Text newItemPriceText;
	private Text selectedSeatFromTableText;
	private Text relatedOrderNumberText;
	private List reservationList;
	private List orderItemList;
	private Text orderInfoNumber;
	private Text numberOfBillText;
	private Text toGoOrderName;
	private List toGoOrderInfoList;
	/**
	 * Open the window.
	 */
	public void open() {
		//shlRestotableManager = new Shell();
		//shlRestotableManager.setImage(SWTResourceManager.getImage("image\\Graphicloads-Colorful-Long-Shadow-Restaurant.ico"));
		
		createContents();
		shlRestotableManager.setSize(new Point(1113, 1040));
		shlRestotableManager.setLocation(404,0);
		shlRestotableManager.open();
		while (!shlRestotableManager.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlRestotableManager = new Shell();
		
		shlRestotableManager.setImage(SWTResourceManager.getImage("image\\Graphicloads-Colorful-Long-Shadow-Restaurant.ico"));
		shlRestotableManager.setSize(1200, 1104);
		shlRestotableManager.setText("Resto:Table Manager");
		
		
		TabFolder controlPanel = new TabFolder(shlRestotableManager, SWT.None);
		controlPanel.setBounds(542, 10, 549, 500);
		MessageBox errorDialog =
		new MessageBox(shlRestotableManager, SWT.ICON_ERROR | SWT.OK);
		errorDialog.setText("RESTO");
		
		MessageBox successDialog = new MessageBox(shlRestotableManager, SWT.ICON_INFORMATION | SWT.OK);
		errorDialog.setText("RESTO");
	    //read table from Resto
		for(Table table : RestoController.getCurrentTables()) {
			tableHashtable.put(table.getNumber(), table);
		}
	    //moveTableCombo list loader
		for(Table table : RestoController.getCurrentTables()) {
			tableHashtable.put(table.getNumber(), table);
		}
		
	    //load all in use and billed seats
		inUseSeats.addAll(RestoController.findAllInUseSeat());
		billedSeats.addAll(RestoController.findAllBilledSeat());
		
		
		
	    //Menu Tap
		TabItem menuTab = new TabItem(controlPanel, SWT.NONE);
		menuTab.setText("Menu");
		
		Composite composite_2 = new Composite(controlPanel, SWT.NONE);
		menuTab.setControl(composite_2);
		
		
	    //menu item composite
		
		scrolledMenuItemsComponent = new ScrolledComposite(composite_2, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		scrolledMenuItemsComponent.setBounds(10, 69, 470, 177);
		menuItemComposite = new Composite(scrolledMenuItemsComponent, SWT.NONE);
		CCombo categoryCombo = new CCombo(composite_2, SWT.BORDER);
		categoryCombo.setEditable(false);
		categoryCombo.setBounds(10, 0, 395, 25);
		categoryCombo.select(0);
	    //add selection listener for selecting category of category label
		categoryCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ItemCategory c = ItemCategory.valueOf(categoryCombo.getText());
				setMenuItemButtons(c);
			}
		});
		scrolledMenuItemsComponent.setContent(menuItemComposite);
		scrolledMenuItemsComponent.setExpandHorizontal(false);
		scrolledMenuItemsComponent.setExpandVertical(true);
		scrolledMenuItemsComponent.setMinSize(menuItemComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		CLabel lblNewLabel = new CLabel(composite_2, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getImage("image\\gradient2_inline.png"));
		lblNewLabel.setBounds(10, 41, 470, 26);
		lblNewLabel.setText("Menu Item:");
		
	  //Orderitem Tab 
		   //add orderitem composite
		    //TODO
		Label selecteditemlabel = new Label(composite_2, SWT.NONE);
		selecteditemlabel.setBounds(10, 328, 90, 20);
		selecteditemlabel.setText("Selected Item:");
		
		Label quanititylabel = new Label(composite_2, SWT.NONE);
		quanititylabel.setBounds(269, 328, 90, 20);
		quanititylabel.setText("Quanitity:");
		
		Label noticelabel = new Label(composite_2, SWT.NONE);
		noticelabel.setBounds(10, 310,489,23);
		noticelabel.setText("Select a seat from left, or select mutiple seats if you want to share.");
		
		
		selecteditemText = new Text(composite_2,SWT.BORDER | SWT.MULTI);
		selecteditemText.setBounds(10,354,222,23);
		selecteditemText.setEditable(false);
		
		quantityText = new Text(composite_2,SWT.BORDER | SWT.MULTI);
		quantityText.setBounds(272,354,65,23);
		
		//Orderitem button and listener
		//TODO
		Button presstoorderbutton = new Button(composite_2, SWT.PUSH);
		presstoorderbutton.setBounds(10, 439 , 170, 25);
		presstoorderbutton.setText("Press to Order this item");
		
		Button btnToGo = new Button(composite_2, SWT.CHECK);
		btnToGo.setBounds(10, 408, 111, 20);
		btnToGo.setText("To GO");
		presstoorderbutton.addSelectionListener(new SelectionAdapter() {  
			@Override  
			public void widgetSelected(SelectionEvent e) {
				if(!btnToGo.getSelection()) {
					try {
						if(selecteditemText.getText()==""||quantityText.getText()=="") {
							throw new Exception();
						}
						int qtity = Integer.parseInt(quantityText.getText());
						ArrayList<Seat> selectedseats_fororder = selectedSeats;
						MenuItem selecteditem_2 = null;
						selecteditem_2 = MenuItem.getWithName(selecteditemText.getText());
						Seat reference_seat = selectedseats_fororder.get(0);
						RestoController.orderMenuItem(selecteditem_2, qtity, selectedseats_fororder);
						inUseSeats.addAll(selectedseats_fororder);
						tableComposite.redraw();
						successDialog.setMessage(reference_seat.getOrderItem(
							reference_seat.getOrderItems().size()-1).getQuantity()+" " + reference_seat.getOrderItem(
							reference_seat.getOrderItems().size()-1).getPricedMenuItem().getMenuItem().getName() + " ordered"   );
						successDialog.open();
						
					}
					catch(InvalidInputException e33){
						errorDialog.setMessage(e33.getMessage());
						errorDialog.open();
					} catch (Exception e1) {
						errorDialog.setMessage("One of the field is missing");
						errorDialog.open();
					}
	        	}else {
	        		try {
	        			int i = Integer.parseInt(toGoOrderCombo.getText());
	        			TogoOrder order = toGoOrderHashTable.get(i);
		        		int qtity = Integer.parseInt(quantityText.getText());
	        			RestoController.orderItemForOrder(order, qtity, MenuItem.getWithName(selecteditemText.getText()));
	        		}catch(InvalidInputException e33){
		        		errorDialog.setMessage(e33.getMessage());
		        		errorDialog.open();
		        	} catch (Exception e1) {
						errorDialog.setMessage("One of the field is missing");
						errorDialog.open();
					}
	        	}
	        }});
		
		
	    //menu description list
	    
	    menuItemDecList = new List(composite_2, SWT.BORDER);
	    menuItemDecList.setBounds(10, 252, 470, 52);
	    
	    Label lblTogoordernumber = new Label(composite_2, SWT.NONE);
	    lblTogoordernumber.setText("toGoOrderNumber:");
	    lblTogoordernumber.setBounds(269, 382, 145, 20);
	    
	    toGoOrderCombo = new CCombo(composite_2, SWT.BORDER);
	    toGoOrderCombo.setEnabled(false);
	    toGoOrderCombo.setEditable(false);
	    toGoOrderCombo.setBounds(272, 408, 65, 25);
	    
	    
	    
	    
	    TabItem orderTab = new TabItem(controlPanel, SWT.NONE);
	    orderTab.setText("Order");
	    
	    Composite orderComposite = new Composite(controlPanel, SWT.NONE);
	    orderTab.setControl(orderComposite);
	    
	    Label lblNewLabel_2 = new Label(orderComposite, SWT.NONE);
	    lblNewLabel_2.setBounds(10, 10, 180, 20);
	    lblNewLabel_2.setText("Current Orders:");
	    
	    currentOrderCombo = new CCombo(orderComposite, SWT.BORDER);
	    currentOrderCombo.setEditable(false);
	    currentOrderCombo.setBounds(10, 36, 279, 25);
	    List orderInfoList = new List(orderComposite, SWT.BORDER);
	    orderInfoList.setBounds(10, 67, 429, 177);
	    currentOrderCombo.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent e) {
	        	Order currentOrder = orderHashtable.get(Integer.parseInt(currentOrderCombo.getText()));
	        	orderInfoList.removeAll();
	        	String information = String.format("Order Time: %s %s%n", currentOrder.getDate(), currentOrder.getTime());
	        	orderInfoList.add(information);
	        	information = "Table:  ";
	        	for(Table table : currentOrder.getTables()) {
	        		information+= table.getNumber() +"  ";
	        	}
	        	orderInfoList.add(information);
	        	orderInfoList.add(String.format("%-30s%-10s$%-10s", "Name","Quantity","Price(per unit)"));
	        	for(OrderItem item : currentOrder.getOrderItems()) {
	        		orderInfoList.add(String.format("%-30s%-10d$%-10.2f",item.getPricedMenuItem().getMenuItem().getName(), item.getQuantity(),item.getPricedMenuItem().getPrice()));
	        	}
	        }
	    });
	      
	      Label lblNewLabel_3 = new Label(orderComposite, SWT.NONE);
	      lblNewLabel_3.setBounds(10, 312, 251, 20);
	      lblNewLabel_3.setText("Create An New Order:");
	      
	      Label lblNewLabel_4 = new Label(orderComposite, SWT.NONE);
	      lblNewLabel_4.setBounds(10, 341, 180, 25);
	      lblNewLabel_4.setText("Selected Tables:");
	      
	      selectedTableText = new Text(orderComposite, SWT.BORDER);
	      selectedTableText.setText("Please select tables at left");
	      selectedTableText.setEditable(false);
	      selectedTableText.setBounds(10, 372, 374, 26);
	      
	      Button btnCreate = new Button(orderComposite, SWT.NONE);
	      btnCreate.setBounds(10, 404, 105, 30);
	      btnCreate.setText("Create");
	      btnCreate.addListener(SWT.Selection, new Listener() {
	      			@Override
					public void handleEvent(org.eclipse.swt.widgets.Event event) {
	      				if(selectedTableText.getText().equals("Please select tables at left")) {
	      					errorDialog.setMessage("Please select tables at left");
	      					errorDialog.open();
	      					return;
	      				}
	      				String[] tableNumbers = selectedTableText.getText().split(", ");
	      				ArrayList<Table> tables = new ArrayList<Table>();
	      				for(String number : tableNumbers) {
	      					int n = Integer.parseInt(number);
	      					tables.add(tableHashtable.get(n));
	      				}
	      				try {
	      					RestoController.startOrder(tables);
	      					Order orderCreated = RestoController.getCurrentOrders().get(RestoController.getCurrentOrders().size()-1);
	      					ArrayList<Table> failedTables = new ArrayList<Table>();
	      					for(Table table : tables) {
	      						if(table.getOrder(table.getOrders().size()-1) != orderCreated) {
	      							failedTables.add(table);
	      						}
	      					}
	      					
	      					if(failedTables.size() > 0) {
	      						String errorMessage = "WARNNING: Failed To add Table:";
	      						for(Table table : failedTables) {
	      							errorMessage += (" "+table.getNumber());
	      						}
	      						errorDialog.setMessage(errorMessage);
	      						errorDialog.open();
	      					}
	      					reloadOrders();
	      					reloadBillOrderInfo();
	      					tableComposite.redraw();
	      					
	      				}catch (InvalidInputException e) {
	      					errorDialog.setMessage(e.getMessage());
	      					errorDialog.open();
	      				}
				}
			});
		
		
	    //menu description list

		
		CCombo combo = new CCombo(composite_2, SWT.BORDER);
		combo.setEnabled(false);
		combo.setEditable(false);
		combo.setBounds(272, 408, 65, 25);
		
	
		lblNewLabel_2.setBounds(10, 10, 180, 20);
		lblNewLabel_2.setText("Current Orders:");
		


		Button btnCanceOrderItem = new Button(orderComposite, SWT.NONE);
		btnCanceOrderItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCanceOrderItem.setBounds(125, 250, 135, 30);
		btnCanceOrderItem.setText("Cancel OrderItem");
	      //TODO add listener for cancerlOrderItem
		btnCanceOrderItem.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				if(currentOrderCombo.getText().equals("Order Number...")) {
					errorDialog.setMessage("select an order number to delete");
					errorDialog.open();
					return;
				}
				int selectedIndex = orderInfoList.getSelectionIndex();
				int orderNumber = Integer.parseInt(currentOrderCombo.getText());
				OrderItem orderItemtoCancel = orderHashtable.get(orderNumber).getOrderItem(selectedIndex-3);		
				try {
					RestoController.cancelOrderItem(orderItemtoCancel);
				}catch (InvalidInputException e) {
					errorDialog.setMessage(e.getMessage());
					errorDialog.open();
				}
				reloadOrders();
				reloadBillOrderInfo();
				orderInfoList.removeAll();
				tableComposite.redraw();
			}
		});
		Button btnCanceOrderTable = new Button(orderComposite, SWT.NONE);
	      btnCanceOrderTable.setBounds(270, 250, 190, 30);
	      btnCanceOrderTable.setText("Cancel Order for a Table");
	      
	      btnCanceOrderTable.addListener(SWT.Selection, new Listener() {
	  		@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
	  			
	  			if (selectedTableNumbers.isEmpty()) {
	  			errorDialog.setMessage("select an table to cancel");
				errorDialog.open();
				return;
				}
	  			
	  			Table tabletoCancel = tableHashtable.get(selectedTableNumbers.get(0));
	  	  		try {
					RestoController.cancelOrder(tabletoCancel);
					
				}catch (InvalidInputException e) {
					errorDialog.setMessage(e.getMessage());
					errorDialog.open();
				}
		  	  	reloadOrders();
				reloadBillOrderInfo();
				orderInfoList.removeAll();
				tableComposite.redraw();
	  		}
	      });
		Button btnEndOrder = new Button(orderComposite, SWT.NONE);
		btnEndOrder.setBounds(10, 250, 105, 30);
		btnEndOrder.setText("End Order");
		btnEndOrder.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				if(currentOrderCombo.getText().equals("Order Number...")) {
					errorDialog.setMessage("select an order number to delete");
					errorDialog.open();
					return;
				}
				
				int orderNumber = Integer.parseInt(currentOrderCombo.getText());
				Order orderToEnd = orderHashtable.get(orderNumber);
				try {
					RestoController.endOrder(orderToEnd);
					reloadOrders();
					reloadBillOrderInfo();
					inUseSeats.clear();
					billedSeats.clear();
					orderInfoList.removeAll();
					tableComposite.redraw();
				}catch (InvalidInputException e) {
					errorDialog.setMessage(e.getMessage());
					errorDialog.open();
				}
				
			}
});
	      Label label_1 = new Label(orderComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
	      label_1.setBounds(10, 286, 421, 20);
	      
	      TabItem toGoOrderTab = new TabItem(controlPanel, SWT.NONE);
	      toGoOrderTab.setText("To Go Order");
	      
	      Composite composite_1 = new Composite(controlPanel, SWT.NONE);
	      toGoOrderTab.setControl(composite_1);
	      
	      CCombo toGoOrderNumberCombo = new CCombo(composite_1, SWT.BORDER);
	      toGoOrderNumberCombo.setEditable(false);
	      toGoOrderNumberCombo.setBounds(10, 34, 321, 25);
	      for(TogoOrder order : RestoController.getCurrentTogoOrders()) {
	    	  toGoOrderNumberCombo.add(order.getNumber()+"");
	    	  toGoOrderCombo.add(order.getNumber()+"");
	    	  toGoOrderHashTable.put(order.getNumber(), order);
	      }
	      CCombo ToGoBillCombo = new CCombo(composite_1, SWT.BORDER);
	      ToGoBillCombo.setEditable(false);
	      ToGoBillCombo.setBounds(10, 300, 321, 25);
	      List toGoBillInfoList = new List(composite_1, SWT.BORDER);
	      toGoBillInfoList.setBounds(10, 331, 321, 143);
	      ToGoBillCombo.addSelectionListener(new SelectionAdapter(){
	    	  public void widgetSelected(SelectionEvent e) {
	    		  if(!ToGoBillCombo.getText().equals("")) {
			    	  showToGoBillInfo(Integer.parseInt(toGoOrderNumberCombo.getText()),Integer.parseInt(ToGoBillCombo.getText()));
	    		  }
	    		  for(int i = 0; i<toGoOrderHashTable.get(Integer.parseInt(ToGoBillCombo.getText())).getTogoBills().size();i++) {
	    			  ToGoBillCombo.add(i+"");
	    		  }
		      }

	    	  private void showToGoBillInfo(int orderNum, int billNum) {
	    		  toGoBillInfoList.removeAll();
	    		  TogoOrder order = toGoOrderHashTable.get(orderNum);
	    		  TogoBill bill = order.getTogoBill(billNum);
	    		  int q = order.numberOfTogoBills();
	    		  int price = 0;
	    		  for(TogoOrderItem item : order.getToGoOrderItems()) {
	    			  price+= item.getPricedMenuItem().getPrice()/(float)q;
	    			  toGoBillInfoList.add(String.format("%-32s|%-10.2f|$%-10.2f", item.getPricedMenuItem().getMenuItem().getName(),(float)item.getQuantity()/(float)q,item.getPricedMenuItem().getPrice() ));
	    		  }
	    		  toGoBillInfoList.add("Sum: $"+price);
	    	  }
	    	  
	      });
	      toGoOrderNumberCombo.addSelectionListener(new SelectionAdapter(){
	    	  public void widgetSelected(SelectionEvent e) {
	    		  if(!toGoOrderNumberCombo.getText().equals("")) {
			    	  showToGoOrderInfo(Integer.parseInt(toGoOrderNumberCombo.getText()));
	    		  }
	    		  for(int i = 0; i<toGoOrderHashTable.get(Integer.parseInt(toGoOrderNumberCombo.getText())).getTogoBills().size();i++) {
	    			  ToGoBillCombo.add(i+"");
	    		  }
		      }
	    	  
	      });
	      Label lblToGoOrder = new Label(composite_1, SWT.NONE);
	      lblToGoOrder.setBounds(10, 8, 189, 20);
	      lblToGoOrder.setText("To Go Order Number");
	      
	      toGoOrderInfoList = new List(composite_1, SWT.BORDER);
	      toGoOrderInfoList.setBounds(10, 96, 321, 143);
	      
	  
	      
	      Label lblBill_1 = new Label(composite_1, SWT.NONE);
	      lblBill_1.setText("Bill");
	      lblBill_1.setBounds(10, 274, 189, 20);
	      

	      
	      numberOfBillText = new Text(composite_1, SWT.BORDER);
	      numberOfBillText.setBounds(153, 245, 83, 26);
	      
	      Label lblNumberOfBills = new Label(composite_1, SWT.NONE);
	      lblNumberOfBills.setBounds(10, 248, 139, 20);
	      lblNumberOfBills.setText("Number of Bills");
	      
	      Button btnCreatbill = new Button(composite_1, SWT.NONE);
	      btnCreatbill.addSelectionListener(new SelectionAdapter() {
	      	@Override
	      	public void widgetSelected(SelectionEvent e) {
	      		if(!toGoOrderNumberCombo.getText().equals("")) {
	      			try {
	      				int i = Integer.parseInt(numberOfBillText.getText());
	      				RestoController.issueBill(toGoOrderHashTable.get(Integer.parseInt(toGoOrderNumberCombo.getText())),i);
	      				successDialog.setMessage(i+" Bills has been created");
	      				successDialog.open();
	      			}catch(NumberFormatException ex) {
	      				errorDialog.setMessage("Enter a valid number");
	      				errorDialog.open();
	      			}
	    		  }
	      	}
	      });
	      btnCreatbill.setBounds(242, 243, 90, 30);
	      btnCreatbill.setText("CreatBill");
	      
	      Label label = new Label(composite_1, SWT.SEPARATOR | SWT.VERTICAL);
	      label.setBounds(347, -5, 2, 479);
	      
	      toGoOrderName = new Text(composite_1, SWT.BORDER);
	      toGoOrderName.setBounds(355, 33, 139, 26);
	      
	      Label lblName = new Label(composite_1, SWT.NONE);
	      lblName.setText("Name");
	      lblName.setBounds(355, 8, 189, 20);
	      
	      Button endToGoOrderButton = new Button(composite_1, SWT.NONE);
	      endToGoOrderButton.addSelectionListener(new SelectionAdapter() {
	      	@Override
	      	public void widgetSelected(SelectionEvent e) {
	      		if(toGoOrderNumberCombo.getText()!=null) {
	      			int i = Integer.parseInt(toGoOrderNumberCombo.getText());
	      			RestoController.endToGoOrder(toGoOrderHashTable.get(i));
	      		}
	      	}
	      });
	      endToGoOrderButton.setText("End Order");
	      endToGoOrderButton.setBounds(10, 65, 109, 30);
	      
	      Button btnCreateToGoOrder = new Button(composite_1, SWT.NONE);
	      btnCreateToGoOrder.addSelectionListener(new SelectionAdapter() {
	      	@Override
	      	public void widgetSelected(SelectionEvent e) {
	      		try {
	      			RestoController.createTogoOrder(toGoOrderName.getText());
	      			toGoOrderNumberCombo.removeAll();
	      			toGoOrderCombo.removeAll();
	      			for(TogoOrder order : RestoController.getCurrentTogoOrders()) {
	      	    	  toGoOrderNumberCombo.add(order.getNumber()+"");
	      	    	  toGoOrderCombo.add(order.getNumber()+"");
	      	    	  toGoOrderHashTable.put(order.getNumber(), order);
	      	      }
	      			successDialog.setMessage("Create Successful");
	      			successDialog.open();
	      		}catch(InvalidInputException ex) {
	      			errorDialog.setMessage(ex.getMessage());
	      			errorDialog.open();
	      		}
	      	}
	      });
	      btnCreateToGoOrder.setText("Create To Go Order");
	      btnCreateToGoOrder.setBounds(355, 65, 139, 30);
	      
	      TabItem billTab = new TabItem(controlPanel, SWT.NONE);
	      billTab.setText("Bill");
	      
	      Composite composite = new Composite(controlPanel, SWT.NONE);
	      billTab.setControl(composite);
	      
	      Label lblSelectSeatFrom = new Label(composite, SWT.NONE);
	      lblSelectSeatFrom.setBounds(10, 33, 202, 20);
	      lblSelectSeatFrom.setText("Select Seat From Table:");
	      
	       selectedSeatFromTableText = new Text(composite, SWT.BORDER);
	       selectedSeatFromTableText.setEditable(false);
	       selectedSeatFromTableText.setBounds(10, 59, 202, 26);
	       
	   
	       Label lblRelatedOrderNumber = new Label(composite, SWT.NONE);
	       lblRelatedOrderNumber.setBounds(10, 91, 202, 20);
	       lblRelatedOrderNumber.setText("Related Order Number");
	       relatedOrderNumberText = new Text(composite, SWT.BORDER);
	       relatedOrderNumberText.setEditable(false);
	       relatedOrderNumberText.setBounds(10, 117, 389, 26);
	       
       	    Label label_6 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
       	    label_6.setBounds(0, 185, 549, 2);
       	    
       	    Button createBillButton = new Button(composite, SWT.NONE);
       	    List orderBillList = new List(composite, SWT.BORDER);
       	    orderBillList.setBounds(10, 299, 504, 154);
       	    createBillButton.addSelectionListener(new SelectionAdapter() {
       	    	@Override
       	    	public void widgetSelected(SelectionEvent e) {
       	    		if(selectedSeats.size()>0) {
       	    			try{
       	    				RestoController.issueBillForSeats(selectedSeats);
       	    				successDialog.setMessage("Bill Created Successfully!!");
       	    				reloadBills();
       	    				billedSeats.addAll(selectedSeats);
       	    				orderBillList.removeAll();
       	    				successDialog.open();
       	    			}catch (InvalidInputException ex) {
       	    				errorDialog.setMessage(ex.getMessage());
       	    				errorDialog.open();
       	    			}
       	    		}
       	    	}
       	    });
       	    createBillButton.setBounds(10, 149, 152, 30);
       	    createBillButton.setText("Create Bill");
       	    
       	    Label lblOrderNumber = new Label(composite, SWT.NONE);
       	    lblOrderNumber.setText("Order Number:");
       	    lblOrderNumber.setBounds(10, 195, 109, 20);
       	    
       	    Label lblBill = new Label(composite, SWT.NONE);
       	    lblBill.setText("Bill:");
       	    lblBill.setBounds(10, 247, 109, 20);

       	    
		
		List toGoOrderInfoList = new List(composite_1, SWT.BORDER);
		toGoOrderInfoList.setBounds(10, 76, 321, 143);
	
		
		toGoOrderName = new Text(composite_1, SWT.BORDER);
		toGoOrderName.setBounds(355, 33, 139, 26);
		

		orderBillList.setBounds(10, 299, 504, 154);

		
		billNumberCombo = new CCombo(composite, SWT.NONE);
		billNumberCombo.setBounds(10, 273, 97, 20);
		billNumberCombo.setEditable(false);
		billOrderCombo = new CCombo(composite, SWT.NONE);
		billOrderCombo.setEditable(false);
		billOrderCombo.setBounds(10, 221, 97, 20);
		
		
		billOrderCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
       	        	//add bill number of order to the combo
				reloadBills();
			}
		});
		
		billNumberCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
       	        	//add bill information to the text
				Order orderToCheck = orderHashtable.get(Integer.parseInt(billOrderCombo.getText()));
				Bill bill = orderToCheck.getBill(Integer.parseInt(billNumberCombo.getText()));
				orderBillList.removeAll();
				float overallPrice = 0;
				orderBillList.add(String.format("%-32s|%-10s|%s%n","Name","Quantity","Price(per unit)" ));
				for(Seat s : bill.getIssuedForSeats()) {
					if(s.hasOrderItems()) {
						for(int i = s.numberOfOrderItems()-1; i>=0; i--) {
							OrderItem item = s.getOrderItem(i);
							if(orderToCheck.getOrderItems().contains(item)) {
								
								String string = String.format("%-32s",item.getPricedMenuItem().getMenuItem().getName()+":");
								string += String.format("%.2f%-10s", (item.getQuantity()/(float)item.numberOfSeats()),"");
								string += String.format("$%.2f%n",(item.getPricedMenuItem().getPrice()));
								overallPrice += item.getPricedMenuItem().getPrice()*item.getQuantity()/item.getSeats().size();
								orderBillList.add(string);
							}else {
								break;
							}
						}
					}
					
				}
				if(overallPrice > 0) {
					orderBillList.add(String.format("Sum: $%.2f",overallPrice ));
				}
			}
		});
		//set initial menu items
		setMenuItemButtons(ItemCategory.Appetizer);
		
		TabFolder tabFolder = new TabFolder(shlRestotableManager, SWT.NONE);
		tabFolder.setBounds(10, 514, 526, 485);
		
		
	    //makeReservation Components
		TabItem makeReserveTab = new TabItem(tabFolder, SWT.NONE);
		makeReserveTab.setText("Reserve");
		
		Composite makeReserveComposite = new Composite(tabFolder, SWT.NONE);
		makeReserveTab.setControl(makeReserveComposite);
		
		Label tableslabel = new Label(makeReserveComposite, SWT.NONE);
		tableslabel.setBounds(10, 10, 70, 20);
		tableslabel.setText("Tables:");
		
		Label Datelabel = new Label(makeReserveComposite, SWT.NONE);
		Datelabel.setBounds(10, 130, 150, 20);
		Datelabel.setText("Date(yyyy/MM/dd):");
		
		Label timelabel = new Label(makeReserveComposite, SWT.NONE);
		timelabel.setBounds(220,130,100,20);
		timelabel.setText("Time(HH):");
		
		Label contactlabel = new Label(makeReserveComposite, SWT.NONE);
		contactlabel.setBounds(10, 200, 100, 20);
		contactlabel.setText("Contact Name:");
		
		Label numberOFseats = new Label(makeReserveComposite, SWT.NONE);
		numberOFseats.setBounds(10,67,50,20);
		numberOFseats.setText("Seats:");
		
		Label peoplelabel = new Label(makeReserveComposite, SWT.NONE);
		peoplelabel.setBounds(220, 200, 170, 20);
		peoplelabel.setText("Number of People:");
		
		Label phonelabel = new Label(makeReserveComposite, SWT.NONE);
		phonelabel.setBounds(10, 290, 150, 20);
		phonelabel.setText("Contact Phone Number:");
		
		Label emaillabel = new Label(makeReserveComposite, SWT.NONE);
		emaillabel.setBounds(220, 290, 100, 20);
		emaillabel.setText("E-mail Address:");
		
		Button reservebutton = new Button(makeReserveComposite, SWT.NONE);
		reservebutton.setBounds(10,370,130, 30);
		reservebutton.setText("Reserve");
		
		Button cancelbutton = new Button(makeReserveComposite, SWT.NONE);
		cancelbutton.setBounds(220,370,130, 30);
		cancelbutton.setText("Clean");
		
		insertTableNumber2Text = new Text(makeReserveComposite, SWT.BORDER);
		insertTableNumber2Text.setEditable(false);
		insertTableNumber2Text.setBounds(10, 33, 270, 23);
		insertTableNumber2Text.setText("Please Select a table at left");
		
		selectTableText2 = new Text(makeReserveComposite, SWT.BORDER);
		selectTableText2.setBounds(10,90,270,23);
		selectTableText2.setText("Showing Number of Seats");
		selectTableText2.setEditable(false);
		
		dateText = new Text(makeReserveComposite, SWT.BORDER);
		dateText.setBounds(10, 153, 160, 23);
		dateText.setText("");
		
		nameText = new Text(makeReserveComposite, SWT.BORDER);
		nameText.setBounds(10,223,160,23);
		nameText.setText("");
		
		timeText = new Text(makeReserveComposite, SWT.BORDER);
		timeText.setBounds(220,153,160,23);
		timeText.setText("");
		
		peoplenumText = new Text(makeReserveComposite, SWT.BORDER);
		peoplenumText.setBounds(220, 223, 160, 23);
		peoplenumText.setText("");
		
		phoneNumText = new Text(makeReserveComposite, SWT.BORDER);
		phoneNumText.setBounds(10, 313, 160,23);
		phoneNumText.setText("");
		
		emailText = new Text(makeReserveComposite, SWT.BORDER);
		emailText.setBounds(220, 313, 160, 23);
		emailText.setText("");
		
		
		
		
		//display reservation 
		TabItem timeTable = new TabItem(tabFolder, SWT.NONE);
		timeTable.setText("Table Info");
		
		Composite timeTableComposite = new Composite(tabFolder, SWT.NONE);
		timeTable.setControl(timeTableComposite);
		
		reservationList = new List(timeTableComposite, SWT.BORDER);
		reservationList.setBounds(10, 66, 506, 156);
		
		orderItemList = new List(timeTableComposite, SWT.BORDER);
		orderItemList.setBounds(10, 263, 506, 164);
		
		orderInfoNumber = new Text(timeTableComposite, SWT.BORDER);
		orderInfoNumber.setBounds(10, 10, 237, 26);
		
		Label lblReservations = new Label(timeTableComposite, SWT.NONE);
		lblReservations.setBounds(10, 42, 111, 20);
		lblReservations.setText("Reservations:");
		
		Label lblOrderedItems = new Label(timeTableComposite, SWT.NONE);
		lblOrderedItems.setBounds(10, 237, 134, 20);
		lblOrderedItems.setText("Ordered Items:");
		
		//CancelButton, ReserveButton's listener
		cancelbutton.addSelectionListener(new SelectionAdapter() {  
			@Override  
			public void widgetSelected(SelectionEvent e) {
				resetReserveInfo();
				tableComposite.redraw();
			}
		});  
		
		reservebutton.addSelectionListener(new SelectionAdapter() {  
			@Override  
			public void widgetSelected(SelectionEvent e) {
				try {
					SimpleDateFormat sdfm1 = (SimpleDateFormat)DateFormat.getDateInstance();
					sdfm1.applyPattern("yyyy/MM/dd");
					java.util.Date adate = sdfm1.parse(dateText.getText());
					java.sql.Date dateIn = new java.sql.Date(adate.getTime());
					
					SimpleDateFormat sdfm2 = (SimpleDateFormat)DateFormat.getDateInstance();
					sdfm2.applyPattern("HH");
					java.util.Date atime = sdfm2.parse(timeText.getText());
					java.sql.Time timeIn = new java.sql.Time(atime.getTime());
					
					String emailIn = emailText.getText();
					String phoneIn = phoneNumText.getText();
					String nameIn = nameText.getText();
					int numberOfpeople = Integer.parseInt(peoplenumText.getText());
					ArrayList<Table> Tables = new ArrayList<Table>();
					for(int i =0; i < selectedTableNumbers.size();i++) {
						Tables.add(tableHashtable.get(selectedTableNumbers.get(i)));
					}
					
					RestoController.makeReservation(numberOfpeople,nameIn,emailIn,phoneIn,dateIn,timeIn,Tables);
					resetReserveInfo2();
					
					tableComposite.redraw();
					
					successDialog.setMessage("Reservation of " + selectedTableNumbers + " Made for "+nameIn +" On " + dateIn.toString());
					successDialog.open();
				}
				catch(InvalidInputException e1) {
					errorDialog.setMessage(e1.getMessage());
					errorDialog.open();
				} 
				catch (ParseException e2) {
					errorDialog.setMessage("Please enter valid Date or Time");
					errorDialog.open();
				}
				catch (Exception e3) {
					errorDialog.setMessage("Please enter valid Information");
					errorDialog.open();
				}
			}
		});  
		
		
		
		
		
		
	    //Add Graphic component
		tableComposite = new Composite(shlRestotableManager, SWT.NONE);
		tableComposite.setBounds(10, 10, 526, 500);
		tableComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
	    tableComposite.addPaintListener(new PaintListener(){  //step one: add paintlistener
	        public void paintControl(PaintEvent e){  //step two: add callback method
	        	
	        	//get cursor position relative to table composite
	        	Point cursorLocation = Display.getCurrent().getCursorLocation();
	        	Point relativeCursorLocation = tableComposite.toControl(cursorLocation);
	        	boolean isAnyTableSelected = false;
	        	boolean isAnySeatSelected = false; 
        		//see if the cursor selected any table
	        	int gap = TableGraphics.seatSpace;
	        	for(Table table : tableHashtable.values()) {
	        		if(relativeCursorLocation.x > table.getX()-gap && relativeCursorLocation.x < table.getX()+table.getWidth()+gap&&
	        			relativeCursorLocation.y > table.getY()-gap&&relativeCursorLocation.y<table.getY()+table.getLength()+gap)
	        		{
	        			if(relativeCursorLocation.x > table.getX() && relativeCursorLocation.x < table.getX()+table.getWidth()&&
	        				relativeCursorLocation.y > table.getY()&&relativeCursorLocation.y<table.getY()+table.getLength()){
	        				if(!selectedTableNumbers.contains(table.getNumber())) {
	        					selectedTableNumbers.add(table.getNumber());
	        				}
	        				isAnyTableSelected = true;
	        			}
	        			
	        			java.util.List<Integer[]> seatLocations=TableGraphics.getSeatsLocations(table.getNumber()+"");
	        			int seatSize = TableGraphics.lengthOfChair;
	        			for(int i=0; i<seatLocations.size(); i++){
	        				int locationX = seatLocations.get(i)[0];
	        				int locationY = seatLocations.get(i)[1];
	        				if(relativeCursorLocation.x > locationX && relativeCursorLocation.x < locationX+seatSize&&
	        					relativeCursorLocation.y > locationY&&relativeCursorLocation.y<locationY+seatSize){
	        					if(!selectedSeats.contains(table.getCurrentSeat(i))) {
	        						selectedSeats.add(table.getCurrentSeat(i));
	        					}
	        					showTableNumbersOfSelectedSeat();
	        					isAnySeatSelected=true;
	        				}
	        			}
	        			
	        		}
	        		
	        	}
        		//TODO
	        	
        		//if the cursor click somewhere else on the page, reset the selected tables
	        	if(!isAnyTableSelected) {
	        		selectedTableNumbers.clear();
	        		resetMoveTableInfo();
	        		resetUpdateTableInfo();
	        	}
	        	if(!isAnySeatSelected) {
	        		selectedSeats.clear();
	        		resetTableNUmbersOfSelectedSeat();
	        	}
	        	drawTable(e);  //draw the table and return selected table
    			selectedTableNumber = -1; //reset selected table number to null

	        	//if tableSelected is not null, set information in control panel
    			showReserveTableInfo();
    			showTableForOrderInfo();
    			showSelectedSeats();
    			
    			if(selectedTableNumbers.size() == 1){
    				Table tableSelected = tableHashtable.get(selectedTableNumbers.get(0));
    				if(!tableNumbers.getText().equals(tableSelected.getNumber()+"")){
    					showUpdateTableInfo(tableSelected.getNumber());
    				}
    				if(!moveTableNumbers.getText().equals(tableSelected.getNumber()+"")){
    					showMoveTableInfo(tableSelected.getNumber());
    				}
    				orderInfoNumber.setText("Selected table: "+ tableSelected.getNumber());
    				showTableInfo(tableSelected.getNumber());

    			}else {
    				resetMoveTableInfo();
    				resetUpdateTableInfo();
    				reservationList.removeAll();
    			}
    		} 
    	}); 
	    
	    tableComposite.getDisplay().addFilter(SWT.MouseDown, new Listener() {
	    	@Override
	    	public void handleEvent(Event event) {
	    		if (event.widget instanceof Control) {
	    			Point cursorLocation = Display.getCurrent().getCursorLocation();
	    			Point relativeCursorLocation = tableComposite.toControl(cursorLocation);
	        		//redraw only when the cursor is within the composite
	    			if(relativeCursorLocation.x >0 && relativeCursorLocation.x < tableComposite.getBounds().width
	    				&& relativeCursorLocation.y > 0 && relativeCursorLocation.y < tableComposite.getBounds().height){		
	    				tableComposite.redraw();
	    		}

	    	}
	    }

	});
	    
	    TabFolder tabFolder_1 = new TabFolder(shlRestotableManager, SWT.NONE);
	    tabFolder_1.setBounds(542, 516, 549, 485);
	    
	    
	    //updateMenuTab 260708306
	    TabItem updateMenuTab = new TabItem(tabFolder_1, SWT.NONE);
	    updateMenuTab.setText("Update Menu");
	    
	    Composite updateMenuComposite = new Composite(tabFolder_1, SWT.NONE);
	    updateMenuTab.setControl(updateMenuComposite);
	    
	    Label newItemNameLabel = new Label(updateMenuComposite, SWT.NONE);
	    newItemNameLabel.setText("Name:");
	    newItemNameLabel.setBounds(10, 10, 70, 20);
	    
	    newItemNameText = new Text(updateMenuComposite, SWT.BORDER);
	    newItemNameText.setBounds(10, 36, 270, 26);
	    
	    newItemPriceText = new Text(updateMenuComposite, SWT.BORDER);
	    newItemPriceText.setBounds(10, 94, 270, 26);
	    
	    Label newItemPriceLabel = new Label(updateMenuComposite, SWT.NONE);
	    newItemPriceLabel.setText("Price:");
	    newItemPriceLabel.setBounds(10, 68, 70, 20);
	    
	    Label newItemCategoryLabel = new Label(updateMenuComposite, SWT.NONE);
	    newItemCategoryLabel.setText("Category:");
	    newItemCategoryLabel.setBounds(10, 126, 70, 20);
	    
	    Button addItemButton = new Button(updateMenuComposite, SWT.NONE);
	    addItemButton.setText("Add Item");
	    addItemButton.setBounds(10, 197, 130, 30);
	    
	    Button removeItemButton = new Button(updateMenuComposite, SWT.NONE);
	    removeItemButton.setText("Remove Item");
	    removeItemButton.setBounds(10, 333, 130, 30);
	    
	    CCombo newItemCategoryCombo = new CCombo(updateMenuComposite, SWT.BORDER);
	    newItemCategoryCombo.setEditable(false);
	    newItemCategoryCombo.setBounds(10, 152, 270, 26);
	    
	    menuItemCombo = new CCombo(updateMenuComposite, SWT.BORDER);
	    menuItemCombo.setEditable(false);
	    menuItemCombo.setBounds(10, 285, 270, 26);
	    
	    Label menuItemLabel = new Label(updateMenuComposite, SWT.NONE);
	    menuItemLabel.setText("Menu Item:");
	    menuItemLabel.setBounds(10, 259, 70, 20);
	    
	    //updateMenu UI logic
	    addItemButton.addListener(SWT.Selection, new Listener() {
		@Override
			public void handleEvent(Event event) {
				try {
					double price = Double.parseDouble(newItemPriceText.getText());
					ItemCategory aItemCategory = ItemCategory.valueOf(newItemCategoryCombo.getText());
					String name=newItemNameText.getText();
					RestoController.addMenuItem(name, aItemCategory, price);
					reloadCurrentMenuItems();
					setMenuItemButtons(ItemCategory.Appetizer);
					newItemCategoryCombo.setText("Category...");
					newItemNameText.setText("");
					newItemPriceText.setText("");
					successDialog.setMessage("Item is added!");
					successDialog.open();
				} catch (NumberFormatException e) {
					errorDialog.setMessage("Please enter a valid price!");
					errorDialog.open();
				} catch (IllegalArgumentException e1) {
					errorDialog.setMessage("Please select a category!");
					errorDialog.open();
				} catch (Exception e2) {
					errorDialog.setMessage(e2.getMessage());
					errorDialog.open();
				}
			
			}    	
	    });
    
	    removeItemButton.addListener(SWT.Selection, new Listener() {
	    		@Override
	    		public void handleEvent(Event event) {
	    			try {
	    				MenuItem aMenuItem=currentItemHashtable.get(menuItemCombo.getText());
	    				RestoController.removeMenuItem(aMenuItem);
	    				reloadCurrentMenuItems();
	    				setMenuItemButtons(ItemCategory.Appetizer);
	    				menuItemCombo.setText("Items...");
	    				successDialog.setMessage("Item is removed!");
	    				successDialog.open();
	    			} catch (IllegalArgumentException e) {
	    				errorDialog.setMessage("Please select an item!");
	    				errorDialog.open();
	    			} catch (Exception e1) {
	    				errorDialog.setMessage("Please select an item!");
	    				errorDialog.open();
	    			}
	    		}    	
	    });
	
	    for(ItemCategory c : RestoController.getAllCategories()) {
    			newItemCategoryCombo.add(c.toString());
	    }
	    newItemCategoryCombo.setText("Category...");;
    
	    reloadCurrentMenuItems();
    

	    
	    
	    
	    
	    	//Add table
	    TabItem addTableTab= new TabItem(tabFolder_1, SWT.NONE);
	    addTableTab.setText("Add Table");
	    
	    Composite addTableComposite = new Composite(tabFolder_1, SWT.NONE);
	    addTableTab.setControl(addTableComposite);
	    
	    Label xCoordinateLabel = new Label(addTableComposite, SWT.NONE);
	    xCoordinateLabel.setBounds(10, 10, 130, 23);
	    xCoordinateLabel.setText("X Coordinate:");
	    
	    Label yCoordinateLabel = new Label(addTableComposite, SWT.NONE);
	    yCoordinateLabel.setBounds(10, 68, 130, 23);
	    yCoordinateLabel.setText("Y Coordinate:");
	    
	    Label tableLengthLable = new Label(addTableComposite, SWT.NONE);
	    tableLengthLable.setBounds(10, 193, 55, 23);
	    tableLengthLable.setText("Length:");
	    
	    Label tableWidthLable = new Label(addTableComposite, SWT.NONE);
	    tableWidthLable.setBounds(10, 137, 55, 21);
	    tableWidthLable.setText("Width:");
	    
	    xCoordinateText = new Text(addTableComposite, SWT.BORDER);
	    xCoordinateText.setText("");
	    xCoordinateText.setBounds(10, 39, 270, 23);
	    
	    yCoordinateText = new Text(addTableComposite, SWT.BORDER);
	    yCoordinateText.setText("");
	    yCoordinateText.setBounds(10, 97, 270, 23);
	    
	    tableWidthText = new Text(addTableComposite, SWT.BORDER);
	    tableWidthText.setText("");
	    tableWidthText.setBounds(10, 164, 270, 23);
	    
	    tableLengthText = new Text(addTableComposite, SWT.BORDER);
	    tableLengthText.setText("");
	    tableLengthText.setBounds(10, 222, 270, 23);
	    
	    Button newTableButton = new Button(addTableComposite, SWT.NONE);
	    
	    Label tableSeatLabel = new Label(addTableComposite, SWT.NONE);
	    tableSeatLabel.setBounds(10, 251, 55, 23);
	    tableSeatLabel.setText("Seat:");
	    
	    tableSeatText = new Text(addTableComposite, SWT.BORDER);
	    tableSeatText.setText("");
	    tableSeatText.setBounds(10, 280, 270, 21);
	    
	    Label tableNumberLabel = new Label(addTableComposite, SWT.NONE);
	    tableNumberLabel.setBounds(10, 307, 162, 23);
	    tableNumberLabel.setText("Seat Number:");
	    
	    tableNumberText = new Text(addTableComposite, SWT.BORDER);
	    tableNumberText.setText("");
	    tableNumberText.setBounds(10, 336, 270, 21);
	    
	    
	    
	    
	    
	    	    //newTable button and listener
	    newTableButton.setBounds(10, 380, 130, 30);
	    newTableButton.setText("Create Table");
	    
	    
	    
	    
		//Update Table
	    TabItem updateTableTab = new TabItem(tabFolder_1, SWT.NONE);
	    updateTableTab.setText("Update Table");
	    
	    Composite updateTableComposite = new Composite(tabFolder_1, SWT.NONE);
	    updateTableTab.setControl(updateTableComposite);
	    
	    Label lblNewLabel_1 = new Label(updateTableComposite, SWT.NONE);
	    lblNewLabel_1.setBounds(10, 10, 70, 20);
	    lblNewLabel_1.setText("Table:");
	    
	    Label lblNewTableNum = new Label(updateTableComposite, SWT.NONE);
	    lblNewTableNum.setText("New Table Number:");
	    lblNewTableNum.setBounds(10, 80, 180, 20);
	    
	    insertTableNumberText = new Text(updateTableComposite, SWT.BORDER);
	    insertTableNumberText.setText("");
	    insertTableNumberText.setBounds(10, 100, 270, 23);
	    
	    Label lblSeats = new Label(updateTableComposite, SWT.NONE);
	    lblSeats.setText("Number of Seats:");
	    lblSeats.setBounds(10, 130, 180, 20);
	    
	    numberOfSeatsText = new Text(updateTableComposite, SWT.BORDER);
	    numberOfSeatsText.setText("");
	    numberOfSeatsText.setBounds(10, 150, 270, 23);
	    
	    
	    //updateTable button and listener
	    Button updateButton = new Button(updateTableComposite, SWT.NONE);
	    updateButton.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		try {
	    			int oldNumber = Integer.parseInt(tableNumbers.getText());
	    			int newNumber = Integer.parseInt(insertTableNumberText.getText());
	    			int numberOfSeats = Integer.parseInt(numberOfSeatsText.getText());
	    			RestoController.updateTable(oldNumber, newNumber, numberOfSeats);
	    			Table tableChanged = tableHashtable.get(oldNumber);
	    			tableHashtable.values().remove(tableChanged);
	    			tableHashtable.put(newNumber, tableChanged);
	    			
	    			resetUpdateTableInfo();
	    			tableComposite.redraw();
	    		} catch (NumberFormatException e2) {
	    			errorDialog.setMessage("Please select a table and enter valid numbers");
	    			errorDialog.open();
	    		} catch (InvalidInputException e1) {
	    			errorDialog.setMessage(e1.getMessage());
	    			errorDialog.open();
	    		}
	    		
	    	}
	    });
	    
	    updateButton.setBounds(10, 215, 130, 30);
	    updateButton.setText("Update Table");
	    
	    //deleteTable button and listener
	    Button deleteButton = new Button(updateTableComposite, SWT.PUSH);
	    deleteButton.setBounds(10, 281, 130, 30);
	    deleteButton.setText("Remove Table");
	    
	    tableNumbers = new Text(updateTableComposite, SWT.BORDER);
	    tableNumbers.setEditable(false);
	    tableNumbers.setBounds(10, 43, 270, 26);
	    
	    
	    
	    
	    
	    
	    //moveTable Tab
	    TabItem moveTableTab= new TabItem(tabFolder_1, SWT.NONE);
	    moveTableTab.setText("Move Table");
	    
	    Composite moveTableComposite = new Composite(tabFolder_1, SWT.NONE);
	    moveTableTab.setControl(moveTableComposite);
	    
	    Label selectTableLabel = new Label(moveTableComposite, SWT.NONE);
	    selectTableLabel.setBounds(10, 10, 70, 20);
	    selectTableLabel.setText("Table:");
	    
	    Label newXLabel = new Label(moveTableComposite, SWT.NONE);
	    newXLabel.setBounds(10, 92, 80, 16);
	    newXLabel.setText("X Coordinate:");
	    
	    Label newYLabel = new Label(moveTableComposite, SWT.NONE);
	    newYLabel.setBounds(10, 145, 80, 16);
	    newYLabel.setText("Y Coordinate:");
	    
	    newXText = new Text(moveTableComposite, SWT.BORDER);
	    newXText.setText("Please enter new X coordinate");
	    newXText.setBounds(10, 114, 270, 23);
	    
	    newYText = new Text(moveTableComposite, SWT.BORDER);
	    newYText.setText("Please enter new Y coordiane");
	    newYText.setBounds(10, 167, 270, 23);
	    
	    Button moveTableButton = new Button(moveTableComposite, SWT.NONE);
	    moveTableButton.setBounds(10, 237, 130, 30);
	    moveTableButton.setText("Move Table");
	    //Move Table Listener
	    moveTableButton.addListener(SWT.Selection, new Listener() {
	    	@Override
	    	public void handleEvent(Event event) {
	    		try {
	    			int xInput = Integer.parseInt(newXText.getText());
	    			int yInput = Integer.parseInt(newYText.getText());
	    			Table table = tableHashtable.get(Integer.parseInt(moveTableNumbers.getText()));
	    			RestoController.moveTable(table, xInput, yInput);
	    			successDialog.setMessage("Table "+moveTableNumbers.getText()+" is moved to "+"("+newXText.getText()+","+newYText.getText()+")");
	    			successDialog.open();
	    			resetMoveTableInfo();
	    			tableComposite.redraw();
	    			
	    		} catch (NumberFormatException e) {
	    			errorDialog.setMessage("Please select a table and enter valid numbers");
	    			errorDialog.open();
	    		} catch (Exception e) {
	    			errorDialog.setMessage(e.getMessage());
	    			errorDialog.open();
	    		}
	    		
	    	}    	
	    });
	    
	    moveTableNumbers = new Text(moveTableComposite, SWT.BORDER);
	    moveTableNumbers.setEditable(false);
	    moveTableNumbers.setBounds(10, 36, 270, 26);
	    //removeTable button
	    deleteButton.addListener(SWT.Selection, new Listener() {
	    	@Override
	    	public void handleEvent(Event event) {
	    		if ("Select a table".equals(tableNumbers.getText())) {
	    			errorDialog.setMessage("Select a table");
	    			errorDialog.open();
	    			return;
	    		}
	    		try {
	    			int tableNumber = Integer.parseInt(tableNumbers.getText());
	    			Table table = tableHashtable.get(tableNumber);
	    			RestoController.removeTable(table);
	    			tableHashtable.values().remove(table);
	    			resetUpdateTableInfo();
	    			tableComposite.redraw();
	    		} catch (InvalidInputException e) {
	    			errorDialog.setMessage(e.getMessage());
	    			errorDialog.open();
	    		} catch (NumberFormatException e1){
	    			errorDialog.setMessage("Please select a table and enter valid numbers");
	    			errorDialog.open();
	    		}
	    	}
	    });
	    newTableButton.addListener(SWT.Selection, new Listener() {
	    	@Override
	    	public void handleEvent(Event event) {
	    		try {
	    			int xInput = Integer.parseInt(xCoordinateText.getText());
	    			int yInput = Integer.parseInt(yCoordinateText.getText());
	    			int widthInput = Integer.parseInt(tableWidthText.getText());
	    			int lengthInput = Integer.parseInt(tableLengthText.getText());
	    			int Seat = Integer.parseInt(tableSeatText.getText());
	    			int Number = Integer.parseInt(tableNumberText.getText());
	    			Table newTable = RestoController.createTable(Number, Seat, xInput, yInput, widthInput, lengthInput);
	    			tableHashtable.put(newTable.getNumber(), newTable);
	    			moveTableNumbers.setText("Please select a table at left");
	    			tableComposite.redraw();
	    		} catch (InvalidInputException e) {
	    			errorDialog.setMessage(e.getMessage());
	    			errorDialog.open();
	    			
	    		} catch (NumberFormatException e1){
	    			errorDialog.setMessage("Input has to be Numbers!");
	    			errorDialog.open();
	    		}
	    	}    	
	    });
	  //add menu item categories
	    for(ItemCategory c : RestoController.getAllCategories()) {
	    	categoryCombo.add(c.toString());
	    	categoryCombo.select(0);
	    }
	  //load orders
	    reloadOrders();
	    showTableNumbersOfSelectedSeat();
	    reloadBillOrderInfo();
	    
	}
	
	private void reloadBillOrderInfo() {
		billOrderCombo.removeAll();
		for(Order order : RestoController.getCurrentOrders()) {
			billOrderCombo.add(order.getNumber()+"");
		}
		billNumberCombo.removeAll();
		
	}
	protected void resetMoveTableInfo() {
		newXText.setText("");
		newYText.setText("");
		moveTableNumbers.setText("Please select a table at left");
		
	}
	
	protected void resetReserveInfo() {
		insertTableNumber2Text.setText("Please Select a table at left");
		dateText.setText("");
		selectTableText2.setText("Showing Number of Seats");
		nameText.setText("");
		timeText.setText("");
		peoplenumText.setText("");
		phoneNumText.setText("");
		emailText.setText("");
	}
	
	protected void resetReserveInfo2() {
		insertTableNumber2Text.setText("Please Select a table at left");
		selectTableText2.setText("Showing Number of Seats");
	}
	
	protected void resetUpdateTableInfo() {
		tableNumbers.setText("Please Select a table at left");
		moveTableNumbers.setText("Please select a table at left");
		insertTableNumberText.setText("");
		numberOfSeatsText.setText("");
	}
	


	private void showUpdateTableInfo(int tableNumber){
		tableNumbers.setText(tableNumber+"");
		Table tableSelected = tableHashtable.get(tableNumber);
		numberOfSeatsText.setText(tableSelected.numberOfCurrentSeats()+"");
		selectedTableNumber = tableNumber;
		//tableComposite.redraw();
	}
	
	private void showReserveTableInfo() {
		String tAbleText = "Please select tables at left";
		String seatText = "Showing Number of Seats";
		if(selectedTableNumbers.size()!=0) {
			tAbleText = "";
			tAbleText += selectedTableNumbers.get(0);
			seatText = "";
			seatText += tableHashtable.get(selectedTableNumbers.get(0)).numberOfCurrentSeats();	
		}
		for(int i =1; i < selectedTableNumbers.size();i++){
			tAbleText += (", " + selectedTableNumbers.get(i));
			seatText += (", " + tableHashtable.get(selectedTableNumbers.get(i)).numberOfCurrentSeats());
		}
		insertTableNumber2Text.setText(tAbleText);
		selectTableText2.setText(seatText);
	}
	
	private void showSelectedSeats() {
		String forwhotext = "";
		if(selectedSeats.size()!=0) {
			forwhotext += selectedSeats.size();
			//TODO
		}
	}
	private void showTableInfo(int tableNumber) {
		String empty = "";
		reservationList.removeAll();
		for(int i =0; i <( tableHashtable.get(tableNumber).numberOfReservations());i++){
			empty = ((tableHashtable.get(tableNumber).getReservation(i).getDate().toString())+ " - " +
				(tableHashtable.get(tableNumber).getReservation(i).getTime().getHours()+ ":00 - " +
					(tableHashtable.get(tableNumber).getReservation(i).getContactName())+ " - " +
					(tableHashtable.get(tableNumber).getReservation(i).getNumberInParty())+ " people - " +
					(tableHashtable.get(tableNumber).getReservation(i).getContactPhoneNumber())));
			reservationList.add(empty);
		}
		orderItemList.removeAll();
		try {
			
			for(OrderItem item : RestoController.getOrderItems(tableHashtable.get(tableNumber))) {
				orderItemList.add(String.format("%-30s%-10s$%-10s", item.getPricedMenuItem().getMenuItem().getName(), item.getQuantity(), item.getPricedMenuItem().getPrice()));
			}
		}catch(InvalidInputException e) {
			orderItemList.add(e.getMessage());
		}
	}
	private void showMoveTableInfo(int tableNumber){
		moveTableNumbers.setText(tableNumber+"");
		Table tableSelected = tableHashtable.get(tableNumber);
		newXText.setText(tableSelected.getX()+"");
		newYText.setText(tableSelected.getY()+"");
		selectedTableNumber = tableNumber;
		//tableComposite.redraw();
		
	}
	
	private void showTableForOrderInfo() {
		String tableText = "Please select tables at left";
		if(selectedTableNumbers.size()!=0) {
			//reset tableText if there are any table selected
			tableText = "";
			tableText += selectedTableNumbers.get(0);
		}
		for(int i = 1; i < selectedTableNumbers.size(); i++) {
			tableText += (", " + selectedTableNumbers.get(i));
		}
		selectedTableText.setText(tableText);
	}
	

	private void showTableNumbersOfSelectedSeat() {
		ArrayList<Integer> tableNumbers = new ArrayList<Integer>();
		String s = "";
		int orderNumberSelected = -1;
		for(int i=0; i<selectedSeats.size(); i++) {
			Seat seat = selectedSeats.get(i);
			int tableNumber = seat.getTable().getNumber();
			if(!tableNumbers.contains(tableNumber)) {
				tableNumbers.add(tableNumber);
			}
			if(seat.getTable().numberOfOrders()>0 && orderHashtable.contains(seat.getTable().getOrder(seat.getTable().numberOfOrders()-1)) ) {
				if(orderNumberSelected == -1) {
					orderNumberSelected = seat.getTable().getOrder(seat.getTable().numberOfOrders()-1).getNumber();
				}else{
					if(orderNumberSelected != seat.getTable().getOrder(seat.getTable().numberOfOrders()-1).getNumber()) {
						orderNumberSelected = -2;
						break;
					}
				}
			}else {
				orderNumberSelected = -1;
				break;
			}
		}
		
		if(tableNumbers.size()>0) {
			for(int i=0; i<tableNumbers.size()-1; i++) {
				s+=tableNumbers.get(i)+", ";
			}
			s+= tableNumbers.get(tableNumbers.size()-1);
		}
		selectedSeatFromTableText.setText(s);
		if(orderNumberSelected>0){
			relatedOrderNumberText.setText(orderNumberSelected+"");
		}else if(orderNumberSelected == -1) {
			relatedOrderNumberText.setText("");
		}else if(orderNumberSelected == -2) {
			relatedOrderNumberText.setText("Tables are not in the same current order");
		}
	}
	
	private void reloadBills() {
		billNumberCombo.removeAll();
		try {
			Order orderToCheck = orderHashtable.get(Integer.parseInt(billOrderCombo.getText()));
			for(int i=0; i<orderToCheck.getBills().size(); i++) {
				billNumberCombo.add(i+"");
			}
		}catch (NumberFormatException e) {
			
		}
		
	}
	
	private void showToGoOrderInfo(int number) {
		toGoOrderInfoList.removeAll();
		TogoOrder order = this.toGoOrderHashTable.get(number);
		toGoOrderInfoList.add("Name: "+ order.getName());
		for(TogoOrderItem item : order.getToGoOrderItems()) {
			toGoOrderInfoList.add(String.format("%-30s%-10s%-10.sf", item.getPricedMenuItem().getMenuItem().getName(), item.getQuantity(), item.getPricedMenuItem().getPrice()));
		}
	}
	
	private void resetTableNUmbersOfSelectedSeat(){
		selectedSeatFromTableText.setText("");
		relatedOrderNumberText.setText("");
	}
	private void drawTable(PaintEvent e){
		for(Table table : tableHashtable.values()) {
			
			Color color = display.getSystemColor(SWT.COLOR_GRAY);
			if(table.getStatus() != Table.Status.Available) {
				color = display.getSystemColor(SWT.COLOR_RED);
			}
			if(selectedTableNumbers.contains(table.getNumber())){
				color = display.getSystemColor(SWT.COLOR_CYAN);
			}
			
			TableGraphics.drawTable(color, e, table.getX()
				, table.getY(), table.getWidth(), 
				table.getLength(), table.numberOfCurrentSeats(), table.getNumber()+"");
			java.util.List<Integer[]> seatPositions = TableGraphics.getSeatsLocations(table.getNumber()+"");
			for(int i=0; i<seatPositions.size(); i++){
				color = null;
				Seat seat = table.getCurrentSeat(i);
				if(inUseSeats.contains(seat)) {
					color = display.getSystemColor(SWT.COLOR_RED);
				}
				if(billedSeats.contains(seat)) {
					color = display.getSystemColor(SWT.COLOR_GREEN);
				}
				if(selectedSeats.contains(seat)){
					color = display.getSystemColor(SWT.COLOR_CYAN);
				}
				TableGraphics.drawSeat(color, e, seatPositions.get(i));
			}
		}
	}
	
	private void reloadOrders() {
		orderHashtable = new Hashtable<Integer, Order>();
		currentOrderCombo.removeAll();
		currentOrderCombo.setText("Order Number...");
		for(Order order : RestoController.getCurrentOrders()) {
			orderHashtable.put(order.getNumber(), order);
			currentOrderCombo.add(order.getNumber()+"");	
		}
		
	}
	private void setMenuItemButtons(ItemCategory c) {
		for(Button b : menuItemButtons) {
			b.dispose();
		}
		menuItemButtons = new ArrayList<Button>();
		try {
			java.util.List<MenuItem> menuItems =  RestoController.getMenuItemByCategory(c);
			for(int i = 0; i < menuItems.size(); i++) {
				MenuItem item = menuItems.get(i);
				Button itemButton = new Button(menuItemComposite, SWT.PUSH);
				itemButton.setBounds(10 + (i%2)*160, 0 + (i/2) * 40, 150, 30);
				itemButton.setBackgroundImage(ButtonImage);
				itemButton.setText(item.getName());
				
				itemButton.addListener(SWT.Selection, new Listener() {
					@Override
					public void handleEvent(org.eclipse.swt.widgets.Event event) {
						selecteditemText.setText(item.getName());
						menuItemDecList.removeAll();
						MenuItem item = menuItemHashtable.get(itemButton.getText());
						menuItemDecList.add("Name: "+item.getName());
						menuItemDecList.add("Price: $"+item.getCurrentPricedMenuItem().getPrice());
					}
				});
				
				menuItemButtons.add(itemButton);
				menuItemHashtable.put(item.getName(), item);
			}
			scrolledMenuItemsComponent.setContent(menuItemComposite);
			scrolledMenuItemsComponent.setMinSize(400,400);
			scrolledMenuItemsComponent.setExpandHorizontal(true);
			scrolledMenuItemsComponent.setExpandVertical(true);
		}catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
    private void reloadCurrentMenuItems() {
		currentItemHashtable=new Hashtable <String, MenuItem>();
		menuItemCombo.removeAll();
		menuItemCombo.setText("Items...");
		try {
		for(MenuItem item : RestoController.getCurrentMenuItem()) {
			currentItemHashtable.put(item.getName(), item);
			menuItemCombo.add(item.getName());
		}
		}catch(Exception e) {
			
	}
}

}
