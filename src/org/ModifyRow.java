package org;

import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// Programmer Brian Kies

public class ModifyRow 
{
      int choice = 0, correctedNewCases = 0, correctedNewDeaths = 0, currentRow = 0, lastRow = 0;
      ResultSet rs;
      Date firstDate = null, lastDate = null;
      java.util.Date dateToModify;
      SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
      GetConnection newConnection = new GetConnection();
      GetResultSet grs = new GetResultSet();
     
    public boolean InputDataValid (String casesToModify, String deathsToModify, JDateChooser JNewDateChooser, JTextField JModifyCasesTF,
                                   JTextField JModifyDeathsTF) throws SQLException
    {
        // frame for dialog boxes
        JFrame messageFrame = new JFrame();
        // messages
        String message1 = "Enter Date.";
        String message2 = "Row does not exist."; 
        String message3 = "Enter New Cases and/or New Deaths.";
        String message4 = "Table start date of March 1, 2020 cannot be modified.";
        
        java.sql.Date sqlDateToModify;
        
        grs = new GetResultSet();
        rs = grs.getResultSet();
        rs.first();
        firstDate = rs.getDate(1);
       
        rs.last();
        lastDate = rs.getDate(1);
        
        dateToModify = JNewDateChooser.getDate();
        dateToModify = Reference.RemoveTime(dateToModify);
        sqlDateToModify = new java.sql.Date(dateToModify.getTime());
        
        if (dateToModify == null)
        {
             JOptionPane.showMessageDialog(messageFrame, message1);
             return false;  
        }        
        else if(sqlDateToModify.equals(firstDate))
        {
            JOptionPane.showMessageDialog(messageFrame, message4);
            JNewDateChooser.setCalendar(null);
            JModifyCasesTF.setText(null);
            JModifyDeathsTF.setText(null);
            return false;  
        }        
        else if (!Reference.SearchResultSetForRow(dateToModify))
        {
             JOptionPane.showMessageDialog(messageFrame, message2);
             return false;
        }        
        else if (casesToModify.isEmpty() && deathsToModify.isEmpty())
        {
            JOptionPane.showMessageDialog(null, message3);
            return false;
        }       
        else
            return true;
    }
    
    public boolean ModifyRow (String casesToModify, String deathsToModify, JDateChooser JModifyDateChooser, JTextField JModifyCasesTF, JTextField JModifyDeathsTF) throws ParseException, SQLException
    {
        String modifyOrInsert = "Modify";
        
        if (InputDataValid(casesToModify, deathsToModify, JModifyDateChooser, JModifyCasesTF, JModifyDeathsTF))
        {
            dateToModify = JModifyDateChooser.getDate();
            java.sql.Date sqlDateToModify = new java.sql.Date (dateToModify.getTime());
            if (!casesToModify.isEmpty() && !deathsToModify.isEmpty() )
            {
                correctedNewCases = Integer.parseInt(casesToModify);
                correctedNewDeaths = Integer.parseInt(deathsToModify);
                choice = 1;
                Reference.UPDATE_MADE = true;
            }
            else if (!casesToModify.isEmpty())
            {
                correctedNewCases = Integer.parseInt(casesToModify);
                choice = 2;
                Reference.UPDATE_MADE = true;
            }
            else if (!deathsToModify.isEmpty() )
            {
                correctedNewDeaths = Integer.parseInt(deathsToModify);
                choice = 3;
                Reference.UPDATE_MADE = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No changes entered.");
            }
       
            switch (choice)
            {
                case 1:
                try
                {
                    rs = grs.getResultSet();
                    currentRow = Reference.FindCurrentRow(dateToModify);
                    rs.absolute(currentRow);
                    int currentNewCases = rs.getInt(4);
                    int currentNewDeaths = rs.getInt(5);

                    JModifyCasesTF.setText("");
                    JModifyDeathsTF.setText("");
                    JModifyDateChooser.setCalendar(null);

                    Reference.UpdateCaseColumns(currentRow, sqlDateToModify, currentNewCases, correctedNewCases, modifyOrInsert);
                    Reference.UpdateDeathColumns(currentRow, sqlDateToModify, currentNewDeaths, correctedNewDeaths, modifyOrInsert);
                    return true;
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
                case 2:
                try
                {
                    rs = grs.getResultSet();
                    currentRow = Reference.FindCurrentRow(dateToModify);
                    rs.absolute(currentRow);
                    int currentNewCases = rs.getInt(4);

                    JModifyCasesTF.setText("");
                    JModifyDateChooser.setCalendar(null);

                    Reference.UpdateCaseColumns(currentRow, sqlDateToModify, currentNewCases, correctedNewCases, modifyOrInsert);
                    return true;
                }
                catch (SQLException | ParseException ex)
                {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
                case 3:
                try
                {
                    rs = grs.getResultSet();
                    currentRow = Reference.FindCurrentRow(dateToModify);
                    rs.absolute(currentRow);
                    int currentNewDeaths = rs.getInt(5);

                    JModifyDeathsTF.setText("");
                    JModifyDateChooser.setCalendar(null);

                    Reference.UpdateDeathColumns(currentRow, sqlDateToModify, currentNewDeaths, correctedNewDeaths, modifyOrInsert);
                    return true;
                }
                catch (SQLException | ParseException ex)
                {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }       
        return false;
    }  
}
