package org;

import com.toedter.calendar.JDateChooser;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/* Programmer Brian Kies */

public class InsertRow 
{
    private Connection myConn;
    private GetConnection gc;
    private GetResultSet grs;
    private ResultSet rs;
    Date currentDate = null, lastDate;
    int newCases, newDeaths, correctedNewCases = 0, correctedNewDeaths = 0, currentRow, lastRow;
    JFrame messageFrame = new JFrame();
    
    public boolean InputDataValid(String newCasesStr, String newDeathsStr, Date dateToInsert, Date lastDate) throws SQLException
    {
        if (dateToInsert == null)
        {
            JOptionPane.showMessageDialog(messageFrame, "Enter Date To Insert.");
            return false;
        }
        else if (dateToInsert.after(lastDate))
        {
            JOptionPane.showMessageDialog(messageFrame, "Use Add Row when adding row to end of table.");
            return false;
        }
        else if (Reference.SearchResultSetForRow(dateToInsert))
        {
            JOptionPane.showMessageDialog(messageFrame, "A Row For This Date Already Exists.");
            return false;
        }
        else if (newCasesStr.trim().isEmpty() && newDeathsStr.trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter New Cases And Deaths.");
            return false;
        }
        else if ( newCasesStr.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter New Cases.");
            return false;
        }
        else if ( newDeathsStr.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter New Deaths.");
            return false;
        }  
        return true;
    }
    
    public boolean InsertRow(String newCasesStr, String newDeathsStr, Date dateToInsert) throws SQLException, ParseException
    {
        // modifyOrInsert String is for the Reference class methods that update the Covid19table
        // when inserting a row you do not additionally increment new cases and new deaths as when you modify a row
        String modifyOrInsert = "Insert"; 
        int prevTotalCases, prevTotalDeaths, prevDayCases, prevDayDeaths;
        int prevRow = 0, totalCases, totalDeaths;
        long diff;
        java.util.Date dayBefore;
        java.sql.Date sqlDate = null;
        double percentChangeInCases, percentChangeInDeaths, deathPercent;
        String strPercentChangeInCases, strPercentChangeInDeaths, strDeathPercent, prevDate;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        
        grs = new GetResultSet();
        rs = grs.getResultSet();
        rs.last();
        Date lastDate = rs.getDate(1);
    
        if (InputDataValid(newCasesStr, newDeathsStr, dateToInsert, lastDate))
        {
            newCases = Integer.parseInt(newCasesStr.trim());
            newDeaths = Integer.parseInt(newDeathsStr.trim());
            diff = dateToInsert.getTime();
            diff -= 1 * 24 * 60 * 60 * 1000;
            dayBefore = new java.util.Date(diff);
           // convert java.util.date to java.sql.date for database table
            sqlDate = new java.sql.Date(dayBefore.getTime());
            prevRow = Reference.FindCurrentRow(sqlDate);
            rs.absolute(prevRow);
            // convert java.util.date to java.sql.date for database table
            sqlDate = new java.sql.Date(dateToInsert.getTime());
            prevTotalCases = rs.getInt(2);
            totalCases = prevTotalCases + newCases;
            prevTotalDeaths = rs.getInt(3);
            totalDeaths = prevTotalDeaths + newDeaths;
            prevDayCases = rs.getInt(4);
            prevDayDeaths = rs.getInt(5);
            percentChangeInCases = ((float)newCases / prevDayCases - 1) * 100;
            strPercentChangeInCases = decimalFormat.format(percentChangeInCases);
            strPercentChangeInCases += "%";
          
            if (newDeaths == 0)
                strPercentChangeInDeaths = "0.00%";
            else
            {
                percentChangeInDeaths = ((float)newDeaths / prevDayDeaths - 1) * 100;
                strPercentChangeInDeaths = decimalFormat.format(percentChangeInDeaths);
                strPercentChangeInDeaths += "%";
            }
                
            deathPercent = ((float)totalDeaths / totalCases)*100.0;
            strDeathPercent = decimalFormat.format(deathPercent);
            strDeathPercent += "%";
           
            PreparedStatement stmt = grs.myConn.prepareStatement("INSERT INTO covid19table (Date, Total_Cases, Total_Deaths, New_Cases, New_Deaths, "
                                                         + "Change_In_Cases, Change_In_Deaths, Death_Percent) VALUE (?, ?, ?, ?, ?, ?, ?, ?)");
        
            stmt.setDate(1, sqlDate);
            stmt.setInt(2, totalCases);
            stmt.setInt(3, totalDeaths);
            stmt.setInt(4, newCases);
            stmt.setInt(5, newDeaths);
            stmt.setString(6, strPercentChangeInCases);
            stmt.setString(7, strPercentChangeInDeaths);
            stmt.setString(8, strDeathPercent);   
            stmt.execute();
            Reference.ROW_DELETED = false;
            Reference.UPDATE_MADE = true;
            
            if (newCases != Reference.deletedCases)
                Reference.UpdateCaseColumns(Reference.FindCurrentRow(sqlDate), dateToInsert, Reference.deletedCases, newCases, modifyOrInsert);
      
        
            if (newDeaths != Reference.deletedDeaths)
                Reference.UpdateDeathColumns(Reference.FindCurrentRow(sqlDate), sqlDate, Reference.deletedDeaths, newDeaths, modifyOrInsert);
            rs.close(); 
            grs.myConn.close();
            return true;
        }
        else
            return false;     
    }                                 
}
