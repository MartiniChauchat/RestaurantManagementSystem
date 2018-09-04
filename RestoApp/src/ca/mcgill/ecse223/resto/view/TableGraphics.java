package ca.mcgill.ecse223.resto.view;

import java.security.InvalidParameterException;
import java.util.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Point;


public class TableGraphics implements Drawable {

/**
 * 
 * @param color: color for table
 * @param e: paint event
 * @param x
 * @param y
 * @param widthOfTable
 * @param lengthOfTable
 * @param numOfSeats
 * @param text: text to be shown in the table 
 * @throws InvalidParameterException
 */
  public final static int seatSpace = 20;
  public final static int tableGap = 40;
  public final static int lengthOfChair = 15;	
  private static HashMap<String, List<Integer[]>> tableSeatsLocations = new HashMap<String, List<Integer[]>>();

	public static List<Integer[]> getSeatsLocations(String tableNumber){
		return tableSeatsLocations.get(tableNumber);
	}
	public static void drawTable(Color color, PaintEvent e, int x, int y, int widthOfTable, int lengthOfTable, int numOfSeats, String text) throws InvalidParameterException {
		
		//arguments check
		if(tableSeatsLocations.containsKey(text)){
			tableSeatsLocations.remove(text);
		}
		tableSeatsLocations.put(text, new ArrayList<Integer[]>());

		
		
		//outline of drawTable method
		e.gc.setLineStyle(SWT.LINE_DOT);
        e.gc.drawRectangle(x-tableGap/2,y-tableGap/2, widthOfTable+tableGap,tableGap+lengthOfTable); 
        e.gc.setLineStyle(SWT.LINE_SOLID);
        
		//drawTable
		//e.gc.drawRectangle(x+30, y+30, widthOfTable, lengthOfTable);
        e.gc.setBackground(color);
		e.gc.fillRectangle(x, y, widthOfTable, lengthOfTable);

		//draw chairs at top side of the table
		int numOfChairsToDraw = widthOfTable/20;
		if(numOfChairsToDraw > numOfSeats) 
		{
			numOfChairsToDraw =  numOfSeats;
		}
		for(int i = 0; i < numOfChairsToDraw ; i++) {
			Integer[] position = new Integer[2];
			position[0] = x+5+20*i;
			position[1] = y-20;
			tableSeatsLocations.get(text).add(position);
		}
		numOfSeats -= numOfChairsToDraw;
		
		//draw chairs at he right side of the table 
		numOfChairsToDraw = lengthOfTable/20;
		if(numOfChairsToDraw > numOfSeats) 
		{
			numOfChairsToDraw =  numOfSeats;
		}
		for( int i = 0; i < numOfChairsToDraw ; i++) {
			Integer[] position = new Integer[2];
			position[0] = x+5+widthOfTable;
			position[1] = y+20*i;
			tableSeatsLocations.get(text).add(position);
		}
		numOfSeats -= numOfChairsToDraw;
		
		//draw chairs at the bottom of the table
		numOfChairsToDraw = widthOfTable/20;
		if(numOfChairsToDraw > numOfSeats) 
		{
			numOfChairsToDraw =  numOfSeats;
		}
		for( int i = 0; i < numOfChairsToDraw ; i++) {
			Integer[] position = new Integer[2];
			position[0] = x-20+widthOfTable-20*i;
			position[1] = y+lengthOfTable+5;
			tableSeatsLocations.get(text).add(position);
		}
		numOfSeats -= numOfChairsToDraw;
		
		//draw chairs at the left of the table
		numOfChairsToDraw = lengthOfTable/20;
		if(numOfChairsToDraw > numOfSeats) 
		{
			numOfChairsToDraw =  numOfSeats;
		}
		for( int i = 0; i < numOfChairsToDraw ; i++) {
			Integer[] position = new Integer[2];
			position[0] = x-20;
			position[1] = y+lengthOfTable-20-20*i;
			tableSeatsLocations.get(text).add(position);
		}
		numOfSeats -= numOfChairsToDraw;
		
		//draw the text of the table
		Point p = e.gc.textExtent(text);
		e.gc.drawText(text, x+(widthOfTable)/2-p.x/2,y+(lengthOfTable)/2-p.y/2);
		
	}  
	public static void drawSeat(Color color, PaintEvent e, Integer[] location){
		if(color == null){
			e.gc.drawOval(location[0], location[1], lengthOfChair, lengthOfChair);
		}else{
	        e.gc.setBackground(color);
			e.gc.fillOval(location[0], location[1], lengthOfChair, lengthOfChair);
		}
	}


	@Override
	public long internal_new_GC(GCData data) {
		return 0;
	}

	@Override
	public void internal_dispose_GC(long handle, GCData data) {
		
	}

}
