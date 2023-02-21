
package org;

import static java.lang.Math.abs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* Programmer Brian Kies */

// Reference holds various methods for global accessability
public class Reference 
{
    static boolean UPDATE_MADE = false;
    static boolean IMPORT_FILE = false;
    static boolean FILE_IMPORTED = false;
    static boolean ROW_ADDED = false;
    static boolean ROW_INSERTED = false;
    static boolean ROW_MODIFIED = false;
    static boolean ROW_DELETED = false;
    static int deletedCases;
    static int deletedDeaths;
    static Date deletedDate;
    static String globalStat;
  
    public static boolean SearchResultSetForRow (Date currentDate) throws SQLException
    {
        GetConnection newConnection = null;
        GetResultSet grs = new GetResultSet();
        Connection myConn = newConnection.init();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM covid19table WHERE Date = ?";
 
        ResultSet rs = grs.getResultSet();
        ps = myConn.prepareStatement(sql);
        //convert currentDate to sql date
        java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
        ps.setDate(1, sqlCurrentDate);
        rs = ps.executeQuery();
       
        if (rs.next())
            return true;   
        
        return false;
    }
     
    public static boolean DaysBetween (Date lastDate, Date dateToAdd)
    {
        long difference;
        int daysBetween = 0;
        
        java.sql.Date sqlDateToAdd = new java.sql.Date(dateToAdd.getTime());
        
        try 
        {
            difference = lastDate.getTime() - sqlDateToAdd.getTime();
	    daysBetween = (int) (difference / (1000*60*60*24));
	} 
         catch (Exception e) 
         {
	    e.printStackTrace();
	 }
         
         if (abs(daysBetween) > 1)
            return false;
         else
            return true;
    }
    
    public static int DifferenceInDates (java.sql.Date startDate, java.sql.Date endDate) throws SQLException, ParseException
    {
        GetResultSet grs = new GetResultSet();
        ResultSet rs = grs.getResultSet();
        int numOfRows = 1;
       
        int currentRow = FindCurrentRow (startDate);
        rs.absolute(currentRow);
        
        if (startDate.toString().compareTo(endDate.toString()) == 0)
            return numOfRows;
        
        do 
        {
            numOfRows++;
            rs.next();
            startDate = rs.getDate(1);
        }
        
        while (startDate.toString().compareTo(endDate.toString()) < 0);
            
        rs.close();       
            
        return numOfRows;
    }
    
    public static boolean MakeSureCasesNumeric (String newCases)
    {
        if ("".equals(newCases.trim()))
        {
            return true;
        }
        else
        {
            try   
            {
                Integer.parseInt(newCases);
                return true;
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
    }
    
    public static boolean MakeSureDeathsNumeric (String newDeaths)
    {
        if ("".equals(newDeaths.trim()))
        {
            return true;
        }
        else
        {
            try   
            {
                Integer.parseInt(newDeaths);
                return true;
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
    } 
    
    // these two methods, UpdateCaseColumns and UpdateDeathColumns, do the bulk of the work for
    // inserting, modifying, or deleting data with PreparedStatements
    
    public static void UpdateCaseColumns (int currentRow, java.sql.Date sqlCurrentDate, int prevNewCases, int newCases, String modifyOrInsert) throws SQLException
    {
        GetConnection newConnection = null;
        GetResultSet grs = new GetResultSet();
        PreparedStatement stmt;
        String prevDate = " ", nextDate;
        int lastRow;
        
        ResultSet rs = grs.getResultSet();
        rs.last( );
        lastRow = rs.getRow();
        rs.absolute(currentRow);
        
       // go back one row to access New_Cases in order to compute Change_In_Cases percentage with updated New_Cases value unless first row
       if (currentRow > 1) 
       {
           rs.previous();
        // retrieve prevDate for later calculation of updated Change_In_Cases percentage
           prevDate = rs.getString(1);
           rs.next();
       }
   
        Connection myConn = newConnection.init();
       
        stmt = myConn.prepareStatement("UPDATE covid19table SET New_Cases = ? WHERE Date = ?");
        stmt.setInt(1, newCases);
        stmt.setDate(2, sqlCurrentDate);
        stmt.execute();
                       
        stmt = myConn.prepareStatement("SELECT @incrementValue := ? - ?");
        stmt.setInt(1, newCases);
        stmt.setInt(2, prevNewCases);
        stmt.execute();
        
        // if inserting row, you do not increment the current row so PreparedStatement is for Date > row of inserted date
        // if modifying row you do incement row being modified so PreparedStatement is for Date >= row being modified 
        if (modifyOrInsert.equals("Insert"))
            stmt = myConn.prepareStatement("UPDATE covid19table SET Total_Cases = Total_Cases + @incrementValue WHERE Date > ?");
        else
            stmt = myConn.prepareStatement("UPDATE covid19table SET Total_Cases = Total_Cases + @incrementValue WHERE Date >= ?");
        stmt.setDate(1, sqlCurrentDate);
        stmt.execute();
        
        // now update Death_Percent
        stmt = myConn.prepareStatement("UPDATE covid19table SET Death_Percent := CONCAT ( FORMAT (( Total_Deaths / Total_Cases ) * 100, 2), '%') WHERE Date >= ?");
        stmt.setDate(1, sqlCurrentDate);
        stmt.execute();
 
        // now update previous and current Change_In_Cases fields affected by change in New_Cases unless first row
        if (currentRow != 1)
        {
            stmt = myConn.prepareStatement("SELECT @prevRowCases := New_Cases FROM covid19table where Date = ?");
            stmt.setString(1, prevDate);
            stmt.execute();
            stmt = myConn.prepareStatement("UPDATE covid19table SET Change_In_Cases := CONCAT ( FORMAT (( New_Cases / @prevRowCases - 1 ) * 100, 2), '%') WHERE Date = ?");
            stmt.setDate(1, sqlCurrentDate);
            stmt.execute();
        }
        
        // or not last row
        if (currentRow != lastRow )
        {
            rs.next();
            nextDate = rs.getString(1);
        
            stmt = myConn.prepareStatement("UPDATE covid19table SET Change_In_Cases := CONCAT ( FORMAT ((New_Cases / ? - 1 ) * 100, 2), '%') WHERE Date = ?");
            stmt.setInt(1, newCases);
            stmt.setString(2, nextDate);
            stmt.execute();
        }
  
        rs.close();
        myConn.close();
    }
    
    public static void UpdateDeathColumns (int currentRow, java.sql.Date sqlCurrentDate, int prevNewDeaths, int newDeaths, String modifyOrInsert) throws SQLException
    {
        Connection myConn;
        GetConnection newConnection = null;
        GetResultSet grs = new GetResultSet();
        PreparedStatement stmt;
        String prevDate = null;
        String nextDate;
        int lastRow;
        
        ResultSet rs = grs.getResultSet();
        rs.last( );
        lastRow = rs.getRow();
        rs.absolute(currentRow);
        
        // go back one row to access New_Deaths in order to compute Change_In_Deaths percentage with updated New_Deaths value unless first row
        if (currentRow > 1)
        {
            rs.previous();
            // store previous date for later calculation of updated Change_In_Deaths percentage
            prevDate = rs.getString(1);
            rs.next();
        }
        
        myConn = newConnection.init();
        // convert java.util.date to java.sql.date
        // java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
              
        stmt = myConn.prepareStatement("UPDATE covid19table SET New_Deaths = ? WHERE Date = ?");
        stmt.setInt(1, newDeaths);
        stmt.setDate(2, sqlCurrentDate);
        stmt.execute();
        
        stmt = myConn.prepareStatement("SELECT @incrementValue := ? - ?");
        stmt.setInt(1, newDeaths);
        stmt.setInt(2, prevNewDeaths);
        stmt.execute();
        
        // increment Total_Deaths in all remaining rows by adding the difference of updated deaths - previous deaths
        // if inserting row, you do not increment the current row so PreparedStatement is for Date > row of inserted date
        // if modifying row you do incement row being modified so PreparedStatement is for Date >= row being modified
        if (modifyOrInsert.equals("Insert"))
            stmt = myConn.prepareStatement("UPDATE covid19table SET Total_Deaths = Total_Deaths + @incrementValue WHERE Date > ?");
        else
            stmt = myConn.prepareStatement("UPDATE covid19table SET Total_Deaths = Total_Deaths + @incrementValue WHERE Date >= ?");
        stmt.setDate(1, (java.sql.Date) sqlCurrentDate);
        stmt.execute();
        
          // now update Death_Percent
        
        stmt = myConn.prepareStatement("UPDATE covid19table SET Death_Percent := CONCAT ( FORMAT (( Total_Deaths / Total_Cases ) * 100, 2), '%') WHERE Date >= ?");
        stmt.setDate(1, sqlCurrentDate);
        stmt.execute();
        
        // now update two Change_In_Deaths fields affected by change in New_Deaths unless first row
        if (currentRow != 1)
        {
            stmt = myConn.prepareStatement("SELECT @prevRowDeaths := New_Deaths FROM covid19table where Date = ?");
            stmt.setString(1, prevDate);
            stmt.execute();
            stmt = myConn.prepareStatement("UPDATE covid19table SET Change_In_Deaths := CONCAT ( FORMAT (( New_Deaths / @prevRowDeaths - 1 ) * 100, 2), '%') WHERE Date = ?");
            stmt.setDate(1, sqlCurrentDate);
            stmt.execute();
        }
        // or last row
        if (currentRow != lastRow)
        {
            rs.next();
            nextDate = rs.getString(1);
       
            stmt = myConn.prepareStatement("UPDATE covid19table SET Change_In_Deaths := CONCAT ( FORMAT ((New_Deaths / ? - 1 ) * 100, 2), '%') WHERE Date = ?");
            stmt.setInt(1, newDeaths);
            stmt.setString(2, nextDate);
            stmt.execute();
        }
      
        myConn.close();
        rs.close();                          
    }
    
    public static int FindCurrentRow (Date date) throws ParseException, SQLException
    {
        int row = 1;
        java.sql.Date tempDate;
        PreparedStatement ps;
        GetResultSet grs = new GetResultSet();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
      
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String currentDateStr = formatter.format(date);
        java.util.Date formattedDate = formatter.parse(currentDateStr);
    
        String dateStr = dFormat.format(date);
        
        if (dateStr.equals("2020-03-01"))
            return row; 
        else 
            
        try 
        {
            ResultSet rs = grs.getResultSet();
      
            rs.first();
            do
            {
                row++;
                rs.next();
                tempDate = rs.getDate(1);
            }
            while (!tempDate.equals(formattedDate));
            
            rs.close();      
        } 
        catch (SQLException ex) 
        {
        }
        return row;
    }
     
     public static java.util.Date RemoveTime(java.util.Date date) 
     {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return  cal.getTime();
    }
}
