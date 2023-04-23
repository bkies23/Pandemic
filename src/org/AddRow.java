package org;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;

// Programmer Brian Kies

public class AddRow
{
    private Connection myConn;
    private GetConnection gc;
    private GetResultSet grs;
    ResultSet rs;
    
    int prevTotalCases, prevTotalDeaths, prevDayCases, prevDayDeaths, totalCases, totalDeaths;
    double percentChangeInCases, percentChangeInDeaths, deathPercent;
    String strPercentChangeInCases, strPercentChangeInDeaths, strDeathPercent, prevDate;
    DecimalFormat decimalFormat = new DecimalFormat("#.00");
    
    public boolean InputDataValid (Date dateToAdd, String newCases, String newDeaths) throws SQLException
    {
        JFrame messageFrame = new JFrame();
        grs = new GetResultSet();
        rs = grs.getResultSet();
        
        // convert to String for Message 2 dialog box
        String dateToAddStr = dateToAdd.toString().trim();
        rs.last();
        Date lastDate = rs.getDate(1);
        IsConsecutive consecutive = new IsConsecutive( );
        
        String message1 = "Enter date.";
        String message2 = "New date must follow the most recent date in table.";
        String message3 = dateToAddStr + " already exists.";
        String message4 = "Use Insert button when inserting row.";
        String message5 = "Enter New Cases and Deaths.";
        String message6 = "Enter New Cases.";
        String message7 = "Enter New Deaths.";
        String message8 = "Data For New Cases Must Be Integer.";
        String message9 = "Data For New Deaths Must Be Integer.";
      
        if ( dateToAdd == null)
        {
            JOptionPane.showMessageDialog(messageFrame, message1);
            return false;
        }
        else if (!consecutive.Check(dateToAdd, lastDate))
        {
            JOptionPane.showMessageDialog(messageFrame, message2);
            return false;
        }
        else if (Reference.SearchResultSetForRow(dateToAdd))
        { 
            JOptionPane.showMessageDialog(null, message2 );
            return false;
        }
        else if (dateToAdd.before(lastDate))
        {
            JOptionPane.showMessageDialog(messageFrame, message3);
            return false;
        } 
        else if (newCases.isEmpty() && newDeaths.isEmpty())
        {
            JOptionPane.showMessageDialog(messageFrame, message4);
            return false;  
        }       
        else if (newCases.isEmpty())
        {
            JOptionPane.showMessageDialog(messageFrame, message5);
            return false;
        }        
        else if (newDeaths.isEmpty())
        {
            JOptionPane.showMessageDialog(messageFrame, message6);
            return false;
        }        
        else if (!Reference.MakeSureCasesNumeric(newCases.trim()))
        {
             JOptionPane.showMessageDialog(messageFrame, message7);
             return false;
        }        
        else if (!Reference.MakeSureDeathsNumeric(newDeaths.trim()))
        {
            JOptionPane.showMessageDialog(messageFrame, message8);
            return false;   
        }        
        else
            return true;
    }
    
    public boolean AddRow (Date dateToAdd, String newCasesStr, String newDeathsStr) throws SQLException, ParseException
    {
        myConn = gc.init();
        int newCases, newDeaths;
        
        if (InputDataValid (dateToAdd, newCasesStr, newDeathsStr))
        {
            prevTotalCases = rs.getInt(2);
            //convert newCasesStr to integer
            newCases = Integer.parseInt(newCasesStr);
            totalCases = prevTotalCases + newCases;
            prevTotalDeaths = rs.getInt(3);
            //convert newDeathsStr to integer
            newDeaths = Integer.parseInt(newDeathsStr);
            totalDeaths = prevTotalDeaths + newDeaths;
            prevDayCases = rs.getInt(4);
            prevDayDeaths = rs.getInt(5);
            percentChangeInCases = ((float)newCases / prevDayCases - 1) * 100;
            strPercentChangeInCases = decimalFormat.format(percentChangeInCases);
            strPercentChangeInCases += "%";
        
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            
            // to prevent dividing by zero
            if (prevDayDeaths == 0)
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
            
            PreparedStatement stmt = myConn.prepareStatement("INSERT INTO covid19table (Date, Total_Cases, Total_Deaths, New_Cases, New_Deaths, "
                                                             + "Change_In_Cases, Change_In_Deaths, Death_Percent) VALUE (?, ?, ?, ?, ?, ?, ?, ?)");
        
            //convert java.util.date to java.sql.date for PreparedStatement
            java.sql.Date sqlDateToAdd = new java.sql.Date(dateToAdd.getTime());
        
            stmt.setDate(1, sqlDateToAdd);
            stmt.setInt(2, totalCases);
            stmt.setInt(3, totalDeaths);
            stmt.setInt(4, newCases);
            stmt.setInt(5, newDeaths);
            stmt.setString(6, strPercentChangeInCases);
            stmt.setString(7, strPercentChangeInDeaths);
            stmt.setString(8, strDeathPercent);   
            stmt.execute();
            
            // record that update has been made for HandleClosing method in MainFrame
            Reference.UPDATE_MADE = true;
      
            myConn.close();
            rs.close();
            return true;
        }    
        return false;
    }  
}
