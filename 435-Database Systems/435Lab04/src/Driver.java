//Written by Danzel Rana
//
//Solves CS 435, Fall 2015, Lab 04.
//
import java.sql.*;
import java.util.*;
////////////////////////////////////////////////////////////////////////////////
public class Driver
{
	static boolean flag;
	static Scanner kb;
//------------------------------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		int input = 0;
		flag = false;
		while (flag == false)
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab4", "root", "secret");
			Statement s = myConn.createStatement();
			ResultSet rs = null;
			kb = new Scanner(System.in);
			System.out.println("1. Display the schedule of all trips given a starting location, destination, and date.");
			System.out.println("2. Edit schedule.");
			System.out.println("3. Display the stops of a given trip.");
			System.out.println("4. Display the weekly schedule of a given driver and date.");
			System.out.println("5. Add a driver.");
			System.out.println("6. Add a bus.");
			System.out.println("7. Delete a bus.");
			System.out.println("8. Record the actual data of a given trip offering specified by its key.");
			System.out.print("\n> ");
			input = kb.nextInt();
			switch(input)
			{
				case 1:	System.out.println();
							op1(myConn, s, rs);
							break;
				case 2:	System.out.println();
							op2(myConn, s, rs);
							break;
				case 3:	System.out.println();
							op3(myConn, s, rs);
							break;
				case 4:	System.out.println();
							op4(myConn, s, rs);
							break;
				case 5:	System.out.println();
							op5(myConn, s, rs);
							break;
				case 6:	System.out.println();
							op6(myConn, s, rs);
							break;
				case 7:	System.out.println();
							op7(myConn, s, rs);
							break;
				case 8:	System.out.println();
							op8(myConn, s, rs);
							break;
				default:	flag = true;
							break;
			}
			System.out.println();
		}		
	}
//------------------------------------------------------------------------------
	public static void op1(Connection myConn, Statement s, ResultSet rs) throws SQLException 
	{
		System.out.print("starting loc.: ");
		String sl = kb.next();
		System.out.print("destination: ");
		String dest = kb.next();
		System.out.print("date: ");
		String date = kb.next();
		System.out.println();
		String varSQL = "select trip_offering.ScheduledStartTime, trip_offering.ScheduledArrivalTime, trip_offering.DriverName, trip_offering.BusID" +
				  			 " from trip_offering, trip" +
				  			 " where trip.TripNumber = trip_offering.TripNumber" +
				  			 " and trip.StartLocationName = \"" + sl + "\"" +
				  			 " and trip.DestinationName = \"" + dest + "\"" +
				  			 " and trip_offering.Date = \"" + date + "\"";
		rs = s.executeQuery(varSQL);
		ResultSetMetaData rsMeta = rs.getMetaData();
		int varColCount = rsMeta.getColumnCount();
		for (int col = 1; col <= varColCount; col++)
			System.out.print(rsMeta.getColumnName(col) + "\t");
		System.out.println();
		while (rs.next()) 
		{
			for (int col = 1; col <= varColCount; col++) 
				System.out.print(rs.getString(col) + "\t\t\t");
			System.out.println();
		}
		rs.close();
		s.close();
		myConn.close();
	}
//------------------------------------------------------------------------------
	public static void op2(Connection myConn, Statement s, ResultSet rs) throws SQLException 
	{
		System.out.println("1. Delete trip.");
		System.out.println("2. Add trip offering.");
		System.out.println("3. Change the driver of a trip offering.");
		System.out.println("4. Change the bus of a trip offering.");
		System.out.print("\n> ");
		int choice = kb.nextInt();
		switch(choice)
		{
			case 1:	System.out.print("trip#: ");
						String tNum1 = kb.next();
						System.out.print("date: ");
						String date1 = kb.next();
						System.out.print("ScheduledStartTime: ");
						String sst1 = kb.next();
						System.out.println();
						String varSQL1 = "delete from trip_offering" +
											 " where TripNumber = \"" + tNum1 + "\"" +
											 " and Date = \"" + date1 + "\"" +
											 " and ScheduledStartTime = \"" + sst1 + "\"";
						s.executeUpdate(varSQL1);
						break;
			case 2:	boolean flag2 = false;
						while (flag2 == false)
						{	
							System.out.print("trip#: ");
							String tNum2 = kb.next();
							System.out.print("date: ");
							String date2 = kb.next();
							System.out.print("ScheduledStartTime: ");
							String sst2 = kb.next();
							System.out.print("ScheduledArrivalTime: ");
							String sat2 = kb.next();
							System.out.print("DriverName: ");
							String dn2 = kb.next();
							System.out.print("BusID: ");
							String bid2 = kb.next();
							String varSQL2 = "insert into trip_offering" +
											  	  " (TripNumber,Date,ScheduledStartTime,ScheduledArrivalTime,DriverName,BusID) values " +
											  	  "('" + tNum2 + "'," + "'" + date2 + "'," + "'" + sst2 + "'," + "'" + sat2 + "'," + 
											  	  "'" + dn2 + "'," + "'" + bid2 + "')";
							s.executeUpdate(varSQL2);
							System.out.println();
							System.out.print("add more trip offerings? ");
							String yOrN = kb.next();
							if(yOrN.equals("y")) flag2 = false;
							else flag2 = true;
						}
						break;
			case 3:	System.out.print("trip#: ");
						String tNum3 = kb.next();
						System.out.print("date: ");
						String date3 = kb.next();
						System.out.print("ScheduledStartTime: ");
						String sst3 = kb.next();
						System.out.print("new driver name: ");
						String dn3 = kb.next();
						System.out.println();
						String varSQL3 = "update trip_offering set" +
											  " DriverName = \"" + dn3 + "\"" +
											  " where TripNumber = \"" + tNum3 + "\"" +
											  " and Date = \"" + date3 + "\"" +
											  " and ScheduledStartTime = \"" + sst3 + "\"";
						s.executeUpdate(varSQL3);
						break;	
			case 4:	System.out.print("trip#: ");
						String tNum4 = kb.next();
						System.out.print("date: ");
						String date4 = kb.next();
						System.out.print("ScheduledStartTime: ");
						String sst4 = kb.next();
						System.out.print("new BusID: ");
						String bid4 = kb.next();
						System.out.println();
						String varSQL4 = "update trip_offering set" +
								  			  " BusID = \"" + bid4 + "\"" +
								  			  " where TripNumber = \"" + tNum4 + "\"" +
								  			  " and Date = \"" + date4 + "\"" +
								  			  " and ScheduledStartTime = \"" + sst4 + "\"";
						s.executeUpdate(varSQL4);
						break;
			default:	System.out.println("not an option");
						break;
		}
		s.close();
		myConn.close();
	}
//------------------------------------------------------------------------------
	public static void op3(Connection myConn, Statement s, ResultSet rs) throws SQLException 
	{
		System.out.print("trip#: ");
		String tNum = kb.next();
		System.out.println();
		String varSQL = "select *" +
				  			 " from trip_stop_info" +
				  			 " where trip_stop_info.TripNumber = \"" + tNum + "\"";
		rs = s.executeQuery(varSQL);
		ResultSetMetaData rsMeta = rs.getMetaData();
		int varColCount = rsMeta.getColumnCount();
		for (int col = 1; col <= varColCount; col++)
			System.out.print(rsMeta.getColumnName(col) + "\t");
		System.out.println();
		while (rs.next()) 
		{
			for (int col = 1; col <= varColCount; col++) 
				System.out.print(rs.getString(col) + "\t\t\t");
			System.out.println();
		}
		rs.close();
		s.close();
		myConn.close();
	}
//------------------------------------------------------------------------------
	public static void op4(Connection myConn, Statement s, ResultSet rs) throws SQLException 
	{
		System.out.print("DriverName: ");
		String dn = kb.next();
		System.out.print("date: ");
		String date = kb.next();
		System.out.println();
		String varSQL = "select *" +
							 " from trip_offering" +
							 " where DriverName = \"" + dn + "\"" +
							 " and Date = \"" + date + "\"";
		rs = s.executeQuery(varSQL);
		ResultSetMetaData rsMeta = rs.getMetaData();
		int varColCount = rsMeta.getColumnCount();
		for (int col = 1; col <= varColCount; col++)
			System.out.print(rsMeta.getColumnName(col) + "\t");
		System.out.println();
		while (rs.next()) 
		{
			for (int col = 1; col <= varColCount; col++) 
				System.out.print(rs.getString(col) + "\t\t\t");
			System.out.println();
		}
		rs.close();
		s.close();
		myConn.close();
	}
//------------------------------------------------------------------------------
	public static void op5(Connection myConn, Statement s, ResultSet rs) throws SQLException 
	{
		System.out.print("DriverName: ");
		String dn = kb.next();
		System.out.print("DriverTelephoneNumber: ");
		String dt = kb.next();
		String varSQL = "insert into driver" +
			  	  			 " (DriverName,DriverTelephoneNumber) values " +
			  	  			 "('" + dn + "'," + "'" + dt + "')";
		s.executeUpdate(varSQL);
		s.close();
		myConn.close();
	}
//------------------------------------------------------------------------------
	public static void op6(Connection myConn, Statement s, ResultSet rs) throws SQLException 
	{
		System.out.print("BusID: ");
		String bid = kb.next();
		System.out.print("Model: ");
		String m = kb.next();
		System.out.print("Year: ");
		String y = kb.next();
		String varSQL = "insert into bus" +
			  	  			 " (BusID,Model,Year) values " +
			  	  			 "('" + bid + "'," + "'" + m + "'," + "'" +  y + "')";
		s.executeUpdate(varSQL);
		s.close();
		myConn.close();
	}
//------------------------------------------------------------------------------
	public static void op7(Connection myConn, Statement s, ResultSet rs) throws SQLException 
	{
		System.out.print("BusID: ");
		String bid = kb.next();
		System.out.println();
		String varSQL = "delete from bus" +
							 " where BusID = \"" + bid + "\"";
		s.executeUpdate(varSQL);
		s.close();
		myConn.close();
	}
//------------------------------------------------------------------------------
	public static void op8(Connection myConn, Statement s, ResultSet rs) throws SQLException 
	{
		System.out.print("trip#: ");
		String tNum = kb.next();
		System.out.print("date: ");
		String date = kb.next();
		System.out.print("ScheduledStartTime: ");
		String sst = kb.next();
		System.out.print("StopNumber: ");
		String sn = kb.next();
		System.out.print("ScheduledArrivalTime: ");
		String sat = kb.next();
		System.out.print("ActualStartTime: ");
		String ast = kb.next();
		System.out.print("ActualArrivalTime: ");
		String aat = kb.next();
		System.out.print("NumberOfPassengerIn: ");
		String numIn = kb.next();
		System.out.print("NumberofPassengerOut: ");
		String numOut = kb.next();
		System.out.println();
		String varSQL = "insert into actual_trip_stop_info" +
			  	  			 " (TripNumber,Date,ScheduledStartTime,StopNumber,ScheduledArrivalTime,ActualStartTime,ActualArrivalTime,NumberOfPassengerIn,NumberofPassengerOut) values " +
			  	  			 "('" + tNum + "'," + "'" + date + "'," + "'" + sst + "'," + "'" + sn + "'," + 
			  	  			 "'" + sat + "'," + "'" + ast + "'," + "'" + aat + "'," + "'" + numIn + "'," + "'" + numOut + "')";
		s.executeUpdate(varSQL);
		s.close();
		myConn.close();
	}
//------------------------------------------------------------------------------
} // end class Driver
////////////////////////////////////////////////////////////////////////////////