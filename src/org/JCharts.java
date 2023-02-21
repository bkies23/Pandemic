package org;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

// Programmer Brian Kies

public class JCharts extends JPanel
{
    GetResultSet grs;
    boolean chartCases = false, chartDeaths = false, chartNewCases = false, 
            chartNewDeaths = false, chartChangeInCases = false, chartChangeInDeaths = false, chartDeathPercent = false;
    java.sql.Date firstDate, lastDate;
    
    public JCharts (java.sql.Date startDate, java.sql.Date endDate )
    {
        firstDate = startDate;
        lastDate = endDate;  
    }
    
    public void Draw(int currentRow, int numOfRows, String statistic) throws SQLException, ParseException 
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ResultSet rs = null;

        JFreeChart chart = null;
        
        grs = new GetResultSet();
        rs = grs.getResultSet();
   
        String [] chartDates = new String[numOfRows];
        chartDates = FormatChartDates(firstDate, lastDate, numOfRows, currentRow);
        int index = 0;

        rs.absolute(currentRow);
        
        switch (statistic)
        {
            case "TOTAL CASES":
                while (index < numOfRows)
                {
                    dataset.setValue(rs.getInt(2), "Total Cases", chartDates[index]);
                    index++;
                    rs.next();
                }

                chart = ChartFactory.createBarChart("Covid-19 Statistics", "Date", "Total Cases", dataset,
                                                     PlotOrientation.VERTICAL, false, true, false);
                break;
                
            case "NEW CASES":
                while (index < numOfRows)
                {
                    dataset.setValue(rs.getInt(4), "New Cases", chartDates[index]);
                    index++;
                    rs.next();
                }

                chart = ChartFactory.createBarChart("Covid-19 Statistics", "Date", "New Cases", dataset,
                                                     PlotOrientation.VERTICAL, false, true, false);
                break;
                
            case "CHANGE IN CASES":
                while (index < numOfRows)
                {
                    String str = rs.getString(6).trim();
                    str = str.substring(0, str.length() - 1);
                    if (str.equals("#DIV/0"))
                        str = "0.00";
                    double percent = Double.parseDouble(str);
                    dataset.setValue(percent, "Percent Change In Cases", chartDates[index]);
                    index++;
                    rs.next();
                }
           
                chart = ChartFactory.createBarChart("Covid-19 Statistics", "Date", "Percent Change In Cases", dataset,
                                                    PlotOrientation.VERTICAL, false, true, false); 
                break;
                
             case "TOTAL DEATHS":
                while (index < numOfRows)
                {
                    dataset.setValue(rs.getInt(3), "Total Deaths", chartDates[index]);
                    index++;
                    rs.next();
                }
             
               chart = ChartFactory.createBarChart("Covid-19 Statistics", "Date", "Total Deaths", dataset,
                                                    PlotOrientation.VERTICAL, false, true, false); 
               break;
             
            case "NEW DEATHS":
                while (index < numOfRows)
                {
                    dataset.setValue(rs.getInt(5), "New Deaths", chartDates[index]);
                    index++;
                    rs.next();
                }

                chart = ChartFactory.createBarChart("Covid-19 Statistics", "Date", "New Deaths", dataset,
                                                     PlotOrientation.VERTICAL, false, true, false);
                break;
                
             case "CHANGE IN DEATHS":
                while (index < numOfRows)
                {
                    String str = rs.getString(7).trim();
                    str = str.substring(0, str.length() - 1);
                    if (str.equals("#DIV/0"))
                        str = "0.00";
                    double percent = Double.parseDouble(str);
                    dataset.setValue(percent, "Percent Change In Deaths", chartDates[index]);
                    index++;
                    rs.next();
                }
             
               chart = ChartFactory.createBarChart("Covid-19 Statistics", "Date", "Change In Deaths", dataset,
                                                    PlotOrientation.VERTICAL, false, true, false);
               break;
             
             case "DEATH PERCENT":
                while (index < numOfRows)
                {
                    String str = rs.getString(8).trim();
                    str = str.substring(0, str.length() - 1);
                    if (str.equals("#DIV/0"))
                        str = "0.00";
                    double percent = Double.parseDouble(str);
                    dataset.setValue(percent, "Death Percent", chartDates[index]);
                    index++;
                    rs.next();
                }
             
               chart = ChartFactory.createBarChart("Covid-19 Statistics", "Date", "Death Percent", dataset,
                                                    PlotOrientation.VERTICAL, false, true, false);
               break;    
        }

    
        CategoryPlot cp = chart.getCategoryPlot();
        cp.setRangeGridlinePaint(Color.LIGHT_GRAY);

        CategoryAxis axis = cp.getDomainAxisForDataset(index);
        Font font = new Font("Arial Narrow", Font.TRUETYPE_FONT, 7);
        axis.setTickLabelFont(font);
        ChartFrame chartFrame = new ChartFrame("Bar Chart For Covid-19 Statistics", chart);

        chartFrame.setSize(1200, 900);
        chartFrame.setLocationRelativeTo(null);
        chartFrame.setVisible(true);

        rs.close();
    }
    
    public String [] FormatChartDates (Date startDate, Date endDate, int numOfRows, int currentRow) throws SQLException, ParseException
    {
        ResultSet rs = grs.getResultSet();
        Date nextDate;
        int index = 0;
        String [] datesHolder = new String [numOfRows];
        String yearStr, dateStr;
        int initialYear, year, day, month;
 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        rs.absolute(currentRow);
        initialYear = calendar.get(Calendar.YEAR);
        yearStr = "" + initialYear;
        yearStr = yearStr.substring(2);
        // calendar months are numbered 0 - 11 so add 1
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dateStr = "" + month + "-" + day;
        dateStr = month + "-" + day;
        dateStr = yearStr.concat("-" + month + "-" + day);
        datesHolder[index] = dateStr;
        index++;
 
        while (index < numOfRows)
        {
            rs.next();
            nextDate = rs.getDate(1);
            calendar.setTime(nextDate);
            year = calendar.get(Calendar.YEAR);
            if (year > initialYear)
            {
                initialYear++;
                yearStr = "" + year;
                yearStr = yearStr.substring(2);
                // calendar months are numbered 0 - 11 so add 1
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                dateStr = yearStr.concat("-" + month + "-" + day);
                datesHolder[index] = dateStr;
            }
            else
            {
                // calendar months are numbered 0 - 11 so add 1
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                dateStr = "" +month + "-" + day;
                datesHolder[index] = dateStr;    
            }
            
            index++;
        }
         
        return datesHolder;
    }  
}
