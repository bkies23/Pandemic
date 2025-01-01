
package org;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;

public class AddRow 
{
    ResultSet rs;
    
    int prevTotalCases, prevTotalDeaths, prevDayCases, prevDayDeaths, totalCases, totalDeaths;
    double percentChangeInCases, percentChangeInDeaths, deathPercent;
    String strPercentChangeInCases, strPercentChangeInDeaths, strDeathPercent, prevDate;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    
    public boolean InputDataValid (Date dateToAdd, String newCases, String newDeaths) throws SQLException
    {
        JFrame messageFrame = new JFrame();
        rs = Reference.GetResultSet();
        
        // convert to String for Message 2 dialog box
        String dateToAddStr = dateToAdd.toString().trim();
        rs.last();
        Date lastDate = rs.getDate(1);
      
        String message1 = "New date must follow most recent date in table.";
        String message2 = dateToAddStr + " already exists.";
        String message3 = "Use Insert button to insert row.";
        String message4 = "Enter cases and deaths.";
        String message5 = "Enter cases.";
        String message6 = "Enter deaths.";
        String message7 = "Data for new cases must be integer.";
        String message8 = "Data for new deaths must be integer.";
      
        if (!Reference.IsConsecutive(lastDate, dateToAdd))
        {
            JOptionPane.showMessageDialog(messageFrame, message1);
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
        int newCases, newDeaths;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        
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
           
            
            // to prevent division by zero
            if (prevDayCases == 0)
                strPercentChangeInCases = "0.00";
            else
            {
                percentChangeInCases = ((float)newCases / prevDayCases - 1) * 100;
                strPercentChangeInCases = decimalFormat.format(percentChangeInCases);
                strPercentChangeInCases += "%";
               
            }
            // to prevent division by zero
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
            
            PreparedStatement stmt = MainFrame.mainFrame.myConn.prepareStatement("INSERT INTO covid19table (Date, Total_Cases, Total_Deaths, New_Cases, New_Deaths, "
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
            return true;
        }
        // return false to not add row
        return false;
    }
}
