
package org;

import java.util.Date;
import javax.swing.JFrame;

/* Programmer Brian Kies */

public class IsConsecutive 
{
    JFrame frame;
    
    GetResultSet grs = new GetResultSet();
    GetConnection myCon = new GetConnection();
    
    public boolean Check (Date newDate, Date prevDate)
    {
        if (DaysBetween (newDate, prevDate) == 1)
            return true;
        else 
            return false;    
    }
    
    private int DaysBetween (Date newDate, Date prevDate)
    {
        long difference;
        int daysBetween = 0;	 
        
        try 
        {
            difference = newDate.getTime() - prevDate.getTime();
	    daysBetween = (int) (difference / (1000*60*60*24));
	} 
         catch (Exception e) 
         {
	  
	 }
         
         return daysBetween;
   }
    
}
