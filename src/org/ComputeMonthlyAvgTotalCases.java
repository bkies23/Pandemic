
package org;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

// Programmer Brian Kies

/* PROGAMMER MUST MANUALLY ADD EACH NEW COMPLETED MONTH TO CASE AND DEATH ARRAYS BELOW */

public class ComputeMonthlyAvgTotalCases 
{
    // dynamic arrays hold monthly average daily and total cases and average daily and total deaths
    ArrayList <Object> mnthAvgCases = new ArrayList <>(); 
    ArrayList <Object> mnthTtlCases = new ArrayList <>();
    ArrayList <Object> mnthAvgDeaths = new ArrayList <>(); 
    ArrayList <Object> mnthTtlDeaths = new ArrayList <>();
    GetConnection newConnection;  // class takes care of connection to MySql covid19 database
    GetResultSet grs;  // class returns MySql result set with up-to-date statisitics from Covid19 table  
    ResultSet rs;  // will hold all and(or) portion of covid19table rows
    int febNumOfDays; // to check for Leap Year
    
    public ComputeMonthlyAvgTotalCases ( ) throws SQLException
    {
        FillCasesArray( );   
        FillDeathsArray( );
    }
    
    private ArrayList <Object> GetMonthlyAvgCases ( )
    {
        return mnthAvgCases;
    }
    
    private ArrayList<Object> GetMonthlyTotalCases ( )
    {
        return mnthTtlCases;                    
    }
    
    private ArrayList <Object> GetMonthlyAvgDeaths ( )
    {
        return mnthAvgDeaths;
    }
    
    private ArrayList <Object> GetMonthlyTotalDeaths ( )
    {
        return mnthTtlDeaths;
    }
    
    // uses ResultSet to store case data entered into Covid19table
    private void FillCasesArray ( ) throws SQLException
    {
        grs = new GetResultSet( );
        rs = grs.getResultSet();
    
        int sum = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        
        // March 2020
        rs.first();
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // April 2020
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // May 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // June 2020
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // July 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // August 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // September 2020
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // October 2020
         sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // November 2020
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
                
       // December 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // January 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // February 2021
        // check if Leap Year
        febNumOfDays = CheckIfLeapYear(2021) ? 29 : 28;
      
        sum = 0;
        for ( int i = 1; i <= febNumOfDays; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
       
        mnthAvgCases.add(sum / febNumOfDays);
        mnthTtlCases.add(sum);
        
        // March 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // April 2021
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // May 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // June 2021
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // July 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // August 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // September 2021
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // October 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // November 2021
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum); 
        
        // December 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum); 
        
        // January 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum); 
        
        // February 2022
        // check if Leap Year
        febNumOfDays = CheckIfLeapYear(2022) ? 29 : 28;
      
        sum = 0;
        for ( int i = 1; i <= febNumOfDays; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
       
        mnthAvgCases.add(sum / febNumOfDays);
        mnthTtlCases.add(sum);
        
        // March 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum); 
        
        // April 2022
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum); 
        
        // May 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum); 
        
        // June 2022
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // July 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // August 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum); 
        
        // September 2022
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // October 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum); 
        
        // November 2022
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 30);
        mnthTtlCases.add(sum);
        
        // December 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum);
        
        // January 2023
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
        
        mnthAvgCases.add(sum / 31);
        mnthTtlCases.add(sum); 
        
        // February 2023
        // check if Leap Year
        febNumOfDays = CheckIfLeapYear(2022) ? 29 : 28;
      
        sum = 0;
        for ( int i = 1; i <= febNumOfDays; i++)
        {
            sum += rs.getInt(4);
            rs.next();
        }
       
        mnthAvgCases.add(sum / febNumOfDays);
        mnthTtlCases.add(sum);                        
        
        rs.close();
        grs.myConn.close();
    }
    
    // uses ResultSet to store death data entered into Covid19table
    private void FillDeathsArray ( ) throws SQLException
    {
        grs = new GetResultSet( );
        rs = grs.getResultSet();
        int sum = 0;
        float avg = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        
        // March 2020
        rs.first();
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
       
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // April 2020
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // May 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // June 2020
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // July 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // August 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // September 2020
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // October 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // November 2020
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
                
       // December 2020
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // January 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // February 2021
        sum = 0;
        for ( int i = 1; i <= febNumOfDays; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
     
        mnthAvgDeaths.add(sum / febNumOfDays);
        mnthTtlDeaths.add(sum);
        
        // March 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // April 2021
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // May 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // June 2021
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // July 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // August 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // September 2021
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);  
        
        // October 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);  
        
        // November 2021
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // December 2021
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // January 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum); 
        
        // February 2022
        // check if Leap Year
        febNumOfDays = CheckIfLeapYear(2022) ? 29 : 28;
      
        sum = 0;
        for ( int i = 1; i <= febNumOfDays; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
       
        mnthAvgDeaths.add(sum / febNumOfDays);
        mnthTtlDeaths.add(sum);
        
        // March 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum); 
        
        // April 2022
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum); 
        
        // May 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum); 
        
        // June 2022
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // July 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum); 
        
        // August 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum); 
        
        // September 2022
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // October 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum); 
        
        // November 2022
        sum = 0;
        for ( int i = 1; i <= 30; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 30);
        mnthTtlDeaths.add(sum);
        
        // December 2022
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);
        
        // January 2023
        sum = 0;
        for ( int i = 1; i <= 31; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
        
        mnthAvgDeaths.add(sum / 31);
        mnthTtlDeaths.add(sum);                        
        
        // February 2023
        // check if Leap Year
        febNumOfDays = CheckIfLeapYear(2022) ? 29 : 28;
      
        sum = 0;
        for ( int i = 1; i <= febNumOfDays; i++)
        {
            sum += rs.getInt(5);
            rs.next();
        }
       
        mnthAvgCases.add(sum / febNumOfDays);
        mnthTtlCases.add(sum);                                   
       
        rs.close();
        grs.myConn.close();
    }
    
    // method uses standard formula to check if leap year and add 29th day to February
    private boolean CheckIfLeapYear(int year)
    {
        if(year % 4 == 0 && year % 100 != 0)
            return true;
        else if (year % 400 == 0)
            return true;
        else 
            return false;
    }   
}
