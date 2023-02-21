package org;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/* Programmer Brian Kies */

public class DeleteRow 
{
    private Connection myConn;
    private GetConnection gc;
    private GetResultSet grs;
    ResultSet rs;
    JFrame messageFrame = new JFrame();
    
    public boolean IsLastRow (java.sql.Date dateToDelete ) throws SQLException, ParseException
    {
        String dateToDeleteStr, lastDateStr;
        grs = new GetResultSet( );
        rs = grs.getResultSet();
        
        int row = Reference.FindCurrentRow(dateToDelete);
        rs.absolute(row);
       
        rs.last( );
        
        java.sql.Date lastDate = rs.getDate(1);
        dateToDeleteStr = dateToDelete.toString().trim();
        lastDateStr = lastDate.toString().trim();
        
        if (dateToDeleteStr.equals(lastDateStr))
        {
            // store deletedDate, deletedCases, and deletedDeaths for other possible calculations
            Reference.deletedDate = dateToDelete;
            Reference.deletedCases = rs.getInt(4);
            Reference.deletedDeaths = rs.getInt(5);
            rs.close();
            grs.myConn.close();
            return true;
        }
        else
            return false;
    }
    
    public boolean InputDataValid(java.sql.Date sqlDateToDelete, JDateChooser JDeleteDateChooser) throws SQLException
    {
   
        String dateToDeleteStr;
        // convert to String for Message 3 dialog box
        dateToDeleteStr = sqlDateToDelete.toString().trim();
      
        String message1 = "Enter date to delete.";
        String message2 = "You are not permitted to delete 2020-03-01 as it is first date in table.";
        String message3 = "No row for " + dateToDeleteStr + ".";
        String message4 = "Are you sure you want to delete " + sqlDateToDelete + "?";
   
        if ( sqlDateToDelete == null)
        {
            JOptionPane.showMessageDialog(messageFrame, message1);
            return false;
        }   
        
        else if (sqlDateToDelete.toString().equals("2020-03-01"))
        {
            JOptionPane.showMessageDialog(messageFrame, message2);
            return false;  
        }
        
        else if (Reference.SearchResultSetForRow(sqlDateToDelete))
        {
            int input = JOptionPane.showConfirmDialog(messageFrame, message4, "Delete", JOptionPane.YES_NO_OPTION);
           
            if ( input == 0)
            {
                JDeleteDateChooser.setCalendar(null);
                return true;
            }   
            else if ( input == 1)
            {
                JDeleteDateChooser.setCalendar(null);
                Reference.ROW_DELETED = false;
            }
        }
        else
        {
              JOptionPane.showMessageDialog(null, message3);
              JDeleteDateChooser.setDate(null);
        }         
        return false;
    }
    
    public boolean DeleteRow(Date dateToDelete, JDateChooser JDeleteDateChooser) throws SQLException, ParseException
    {
        messageFrame = new JFrame( ); 
        myConn = gc.init();
        String message = "You can only delete one row at a time.";
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        if (Reference.ROW_DELETED == true)
        {
            JOptionPane.showMessageDialog(null, message);
            return false; 
        }
            
        if (IsLastRow(dateToDelete))
        {
            Reference.ROW_DELETED = false;
            Reference.UPDATE_MADE = true;
        }
    //    else
     //        Reference.ROW_DELETED = true;
  
        if (InputDataValid(dateToDelete, JDeleteDateChooser))
        {
            PreparedStatement stmt = myConn.prepareStatement("DELETE FROM covid19table WHERE Date = ?");
           
            stmt.setDate(1, dateToDelete);
            stmt.executeUpdate();
            return true;
        }    
        return false;              
    }  
}
