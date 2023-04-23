
package org;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

// Programmer Brian Kies

public class JAveragesFrame extends JFrame 
{
    boolean TotalCases, TotalDeaths, NewCases, NewDeaths = false;
    String startDateStr = null, endDateStr = null;
    int selectedColumn;
    Date startDate, endDate = null;
    Color paleYellow = new Color(255, 255, 128);
    GetResultSet grs = new GetResultSet();
    ResultSet rs; 
    ButtonGroup bGroup = new ButtonGroup();
    enum Categories { AvgCases, TotalCases, AvgDeaths, TotalDeaths}
    Categories category;
    MainFrame mainFrame;
    Font myFont = new Font ("Times New Roman", Font.PLAIN, 20);
    boolean begin = false; 
    boolean firstTimeThru = true;
    BigDecimal avg, total;
    JTable globalTable;
    JScrollPane JPanelSP = new JScrollPane( );
    DefaultTableCellRenderer renderer;
    String globalStat;
    DefaultTableModel tm;
    String dataHolderArr [][];
    String column [] = {"RESULTS"};
    int globalRowCount = 0;
    String result [][]; 
    ArrayList <String> list;
    Object [][] data;
    
    public JAveragesFrame( )
    {
        
    }
    
    public JAveragesFrame(int selectedRB, MainFrame mf) 
    {
        list = new ArrayList<>();
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        mainFrame = mf;
        
        // enable category selected from main menu
        switch (selectedRB)
        {
            case 1:
                JAvgCasesRB.setSelected(true);
                category = Categories.AvgCases;
                break;
            case 2:
                JTotalCasesRB.setSelected(true);
                category = Categories.TotalCases;
                break;
            case 3:
                JAvgDeathsRB.setSelected(true);
                category = Categories.AvgDeaths;
                break;
            case 4:
                JTotalDeathsRB.setSelected(true);
                category = Categories.TotalDeaths;
                break;       
        }
          
        bGroup.add(JAvgCasesRB);
        bGroup.add(JTotalCasesRB);
        bGroup.add(JAvgDeathsRB);
        bGroup.add(JTotalDeathsRB);
       
        JEndDateChooser.getDateEditor().addPropertyChangeListener
        ((PropertyChangeEvent e) -> {
            if (firstTimeThru) 
            {
                tm = new DefaultTableModel( );
                firstTimeThru = false;
            }
            else
            {
                tm.setRowCount(0);
                tm = new DefaultTableModel( );
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlsPanel = new javax.swing.JPanel();
        JStartDateChooser = new com.toedter.calendar.JDateChooser();
        JEndDateChooser = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        JAvgCasesRB = new javax.swing.JRadioButton();
        JAvgDeathsRB = new javax.swing.JRadioButton();
        JTotalCasesRB = new javax.swing.JRadioButton();
        JTotalDeathsRB = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        submitBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        startDateLbl = new javax.swing.JLabel();
        endDateLbl = new javax.swing.JLabel();
        JLowerPnl = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        JPrinttMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controlsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TimeFrame", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        controlsPanel.setPreferredSize(new java.awt.Dimension(770, 185));

        JStartDateChooser.setDateFormatString("yyyy-MM-dd");

        JEndDateChooser.setDateFormatString("yyyy-MM-dd");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Category", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 51, 102))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(310, 240));

        JAvgCasesRB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JAvgCasesRB.setText("Average Cases");
        JAvgCasesRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JAvgCasesRBActionPerformed(evt);
            }
        });

        JAvgDeathsRB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JAvgDeathsRB.setText("Average Deaths");
        JAvgDeathsRB.setToolTipText("");
        JAvgDeathsRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JAvgDeathsRBActionPerformed(evt);
            }
        });

        JTotalCasesRB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTotalCasesRB.setText("Total Cases");
        JTotalCasesRB.setToolTipText("");
        JTotalCasesRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTotalCasesRBActionPerformed(evt);
            }
        });

        JTotalDeathsRB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTotalDeathsRB.setText("Total Deaths");
        JTotalDeathsRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTotalDeathsRBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JAvgDeathsRB)
                    .addComponent(JAvgCasesRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTotalDeathsRB)
                    .addComponent(JTotalCasesRB))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JAvgCasesRB)
                    .addComponent(JTotalCasesRB))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JAvgDeathsRB)
                    .addComponent(JTotalDeathsRB))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204), null, null));
        jPanel2.setPreferredSize(new java.awt.Dimension(310, 240));

        submitBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        clearBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        clearBtn.setText("Clear");
        clearBtn.setPreferredSize(new java.awt.Dimension(75, 25));
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        closeBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        closeBtn.setText("Close");
        closeBtn.setPreferredSize(new java.awt.Dimension(75, 25));
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitBtn))
                .addGap(58, 58, 58))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(submitBtn)
                .addGap(18, 18, 18)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        startDateLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        startDateLbl.setText("Start Date:");

        endDateLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        endDateLbl.setText("End Date:");

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startDateLbl)
                    .addComponent(endDateLbl))
                .addGap(18, 18, 18)
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDateLbl)
                            .addComponent(JStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endDateLbl)))
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JLowerPnl.setBackground(new java.awt.Color(51, 51, 51));
        JLowerPnl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout JLowerPnlLayout = new javax.swing.GroupLayout(JLowerPnl);
        JLowerPnl.setLayout(JLowerPnlLayout);
        JLowerPnlLayout.setHorizontalGroup(
            JLowerPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JLowerPnlLayout.setVerticalGroup(
            JLowerPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        JPrinttMenuItem.setText("Print");
        JPrinttMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPrinttMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(JPrinttMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(JLowerPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JLowerPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  

    private void JAvgCasesRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JAvgCasesRBActionPerformed
      
        category = Categories.AvgCases;
        selectedColumn = 1;
    }//GEN-LAST:event_JAvgCasesRBActionPerformed

    private void JAvgDeathsRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JAvgDeathsRBActionPerformed
       
        category = Categories.AvgDeaths;
        selectedColumn = 3;
    }//GEN-LAST:event_JAvgDeathsRBActionPerformed

    private void JTotalCasesRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTotalCasesRBActionPerformed
      
        category = Categories.TotalCases;
        selectedColumn = 2;
    }//GEN-LAST:event_JTotalCasesRBActionPerformed

    private void JTotalDeathsRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTotalDeathsRBActionPerformed
        
        category = Categories.TotalDeaths;
        selectedColumn = 4;
    }//GEN-LAST:event_JTotalDeathsRBActionPerformed

    public boolean CheckInputData (Date firstDate, Date lastDate, JDateChooser startDateChooser, JDateChooser endDateChooser) throws ParseException
    {
        JFrame messageFrame = new JFrame( );
   
        String message1 = "Start Date must be on or after " + firstDate + " and before " + lastDate + ".";
        String message2 = "End Date must be greater than Start Date.";
        String message3 = "End Date cannot be greater than: " + lastDate + ".";
        
        if ( startDateChooser.getDate() == null && endDateChooser.getDate() == null)
        {
            JOptionPane.showMessageDialog(messageFrame, "Enter Dates.");
            return false;
        }
        else if (startDateChooser.getDate() == null) 
        {
            JOptionPane.showMessageDialog(messageFrame, "Enter Start Date.");
            return false;
        }
        else if ( endDateChooser.getDate() == null)
        {
            JOptionPane.showMessageDialog(messageFrame, "Enter End Date.");
            return false;
        }
        else
        {
            startDate = new java.sql.Date (startDateChooser.getDate().getTime());
            startDateStr = startDate.toString();
            endDate = new java.sql.Date (endDateChooser.getDate().getTime());
            endDateStr = endDate.toString();
            if (startDate.compareTo(firstDate) <  0 || startDate.compareTo(lastDate) >= 0 )
            {
                JOptionPane.showMessageDialog(messageFrame, message1);
                startDateChooser.setCalendar(null);
                return false;
            }
            else if (endDate.compareTo(startDate) <=  0   ) 
            {
                JOptionPane.showMessageDialog(messageFrame, message2);
                endDateChooser.setCalendar(null);
                return false;
            }
            else if (endDateStr.compareTo(lastDate.toString()) >  0 )  //|| startDate.compareTo(lastDate)== 0)
            {
                JOptionPane.showMessageDialog(messageFrame, message3);
                endDateChooser.setCalendar(null);
                return false;
            }
        }    
        return true;
    } 
   
    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed

        Date firstDate = null, lastDate = null;
        NumberFormat myFormat = NumberFormat.getInstance();
        begin = true;
        
        switch (category)
        {
           case AvgCases:  
               JAvgCasesRB.setSelected(true);
               break;
           case TotalCases: 
               JTotalCasesRB.setSelected(true);
               break;
           case AvgDeaths: 
               JAvgDeathsRB.setSelected(true);
               break;
           case TotalDeaths: 
               JTotalDeathsRB.setSelected(true);
               break;
           default:
               break;
       }
       
        grs = new GetResultSet();

        try
        {
            rs = grs.getResultSet();
            rs.first();
            firstDate = rs.getDate(1);
            rs.last();
            lastDate = rs.getDate(1);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(JAveragesFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        startDate = JStartDateChooser.getDate();
        endDate = JEndDateChooser.getDate();

        try 
        {
            if (CheckInputData(firstDate, lastDate, JStartDateChooser, JEndDateChooser))
            {
                globalTable = CreateTable( );
                avg = null;   
                total = null;
                // convert java.util.Dates to java.sql.Dates
                java.sql.Date sqlStartDate = new java.sql.Date (startDate.getTime());
                java.sql.Date sqlEndDate = new java.sql.Date (endDate.getTime());
      
                switch (category)
                {
                    case AvgCases: 
                    try
                    {
                        avg =  mainFrame.GetAverage(sqlStartDate, sqlEndDate, 1);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(JAveragesFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    list.add(String.format("The average number of cases between %s and %s is %s.", startDateStr, endDateStr, myFormat.format(avg)));
                    break;

                    case TotalCases:  
                    try
                    {
                        avg = mainFrame.GetAverage(sqlStartDate, sqlEndDate, 2);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(JAveragesFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    list.add(String.format("The total number of cases between %s and %s is %s.", startDateStr, endDateStr, myFormat.format(avg)));
                    break;

                    case AvgDeaths:
                    try
                    {
                        total = mainFrame.GetAverage(sqlStartDate, sqlEndDate, 3);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(JAveragesFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

 
                    list.add(String.format("The average number of deaths between %s and %s is %s.", startDateStr, endDateStr, myFormat.format(total.intValue())));
                    break;

                    case TotalDeaths:  
                    try
                    {
                        total =  mainFrame.GetAverage(sqlStartDate, sqlEndDate, 4);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(JAveragesFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    list.add(String.format("The total number of deaths between %s and %s is %s.", startDateStr, endDateStr, myFormat.format(total.intValue())));
                    break;
                }
                
                 globalRowCount += 1;
                 AddTableData( );
                 // copy data from ArrayList list into String array [][] result so that result can be copied to Object data[][] for DefaultTableModel 
                 for (int r = 0; r < list.size(); r++)
                      result[r][0] = list.get(r);
                  
                  data = result;
     
                  tm = new DefaultTableModel(data, column);
                  globalTable.setModel(tm);
                  SetScrollPane( );           
            }
        } 
        catch (ParseException ex) 
        {
            Logger.getLogger(JAveragesFrame.class.getName()).log(Level.SEVERE, null, ex);
        }                            
    }//GEN-LAST:event_submitBtnActionPerformed

     public void AddTableData ( )
     {
        globalTable = CreateTable( );
            
        renderer = new DefaultTableCellRenderer( );
        renderer.setHorizontalAlignment(JLabel.LEFT);
        TableCellRenderer rendererFromHeader = globalTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
           
        if (tm != null)
            tm.setRowCount(0);
        result = new String [globalRowCount][1]; 
        for (int r = 0; r < list.size(); r++)
                      result[r][0] = list.get(r);
                  
        data = result;
     
        tm = new DefaultTableModel(data, column);
        globalTable.setModel(tm);
        globalTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
    }
     
    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed

        JStartDateChooser.setCalendar(null);
        JEndDateChooser.setCalendar(null);
        bGroup.clearSelection();
        if (tm != null)
        {
            tm.setRowCount(0);
        }
        
        tm = new DefaultTableModel( );
        list.clear();
        // reset result String Array to 0;
        globalRowCount = 0;
    }//GEN-LAST:event_clearBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed

        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void JPrinttMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPrinttMenuItemActionPerformed
        try 
        {
            globalTable.print();
        } 
        catch (PrinterException ex) 
        {
            Logger.getLogger(JAveragesFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JPrinttMenuItemActionPerformed

    public void SetScrollPane ( )
    {
        JPanelSP.getViewport().add(globalTable);
        JPanelSP.getViewport().setBackground(Color.DARK_GRAY);
        JPanelSP.setBounds(0, 0, JLowerPnl.getWidth(), JLowerPnl.getHeight());
        JLowerPnl.add(JPanelSP);
    }
    
    @Override
    public void paint (Graphics g)
    {
        super.paintComponents(g);
        
        System.out.println("In Paint");
     
        if (begin)
        {
            
        }         
    }
    
    public JTable CreateTable( )
    {
        JTable jt;
        
        if (tm != null)
            tm.setRowCount(0);
        
        jt = new JTable( );
       
        TableCellRenderer rendererFromHeader = jt.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
   
        jt.setFont(myFont);
        jt.setFillsViewportHeight(true);
        jt.setBackground(Color.DARK_GRAY);
        jt.setForeground(Color.WHITE);
        jt.setGridColor(paleYellow);
        jt.setShowGrid(true);
        jt.setRowHeight(30);
         
        return jt;
     }                          
   
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JAveragesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JAveragesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JAveragesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JAveragesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new JAveragesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JAvgCasesRB;
    private javax.swing.JRadioButton JAvgDeathsRB;
    private com.toedter.calendar.JDateChooser JEndDateChooser;
    private javax.swing.JPanel JLowerPnl;
    private javax.swing.JMenuItem JPrinttMenuItem;
    private com.toedter.calendar.JDateChooser JStartDateChooser;
    private javax.swing.JRadioButton JTotalCasesRB;
    private javax.swing.JRadioButton JTotalDeathsRB;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JLabel endDateLbl;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel startDateLbl;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
