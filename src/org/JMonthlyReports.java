
package org;

import java.awt.*;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

// Programmer Brian Kies

public class JMonthlyReports extends JFrame 
{
    Color royalBlue = new Color(0, 102, 153);
    Color paleYellow = new Color(255, 255, 205);
    Color gray = new Color(153, 153, 153);
    Font myFont = new Font ("Times New Roman", Font.PLAIN, 18);
    
    // programmer must manually add month and year String name for on the 1st of each new month in order for Monthly Reports to work
    String [] months = new String [] { "MARCH 2020", "APRIL 2020", "MAY 2020", "JUNE 2020", "JULY 2020", "AUGUST 2020", "SEPTEMBER 2020", "OCTOBER 2020", "NOVEMBER 2020", 
                                       "DECEMBER 2020", "JANUARY 2021", "FEBRUARY 2021", "MARCH 2021", "APRIL 2021", "MAY 2021", "JUNE 2021", "JULY 2021", "AUGUST 2021", 
                                       "SEPTEMBER 2021", "OCTOBER 2021", "NOVEMBER 2021", "DECEMBER 2021", "JANUARY 2022", "FEBRUARY 2022", "MARCH 2022", "APRIL 2022", 
                                       "MAY 2022", "JUNE 2022", "JULY 2022", "AUGUST 2022", "SEPTEMBER 2022", "OCTOBER 2022", "NOVEMBER 2022", "DECEMBER 2022",
                                       "JANUARY 2023", "FEBRUARY 2023", "MARCH 2023"};
    
    // These four arrays hold data from a Covid19 MySql database I have maintained since March 1, 2020.
    ArrayList <Object> mnthAvgCases = new ArrayList <>(); 
    ArrayList <Object> mnthTtlCases = new ArrayList <>();
    ArrayList <Object> mnthAvgDeaths = new ArrayList <>(); 
    ArrayList <Object> mnthTtlDeaths = new ArrayList <>();
    String globalStat = "AVERAGE CASES", statistic, startMonth, endMonth;
    int finalMonth, startMonthIndex, endMonthIndex;
    JScrollPane lowerPnlSP = new JScrollPane( );
    ButtonGroup group = new ButtonGroup();
    JFrame messageFrame = new JFrame( ); // for dialog popups
    int numOfRows = 0;
    boolean firstTimeThru = true;
    JTable globalTable;
    DataHolder dh;
    DefaultTableModel tm; 
    String column [] = {"MONTH(S)", globalStat};
    ComputeMonthlyAvgTotalCases monthlyCases = new ComputeMonthlyAvgTotalCases( );
 
    LocalDate ld = LocalDate.now();
    
    public JMonthlyReports( ) throws SQLException 
    {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(royalBlue);
        setResizable(false);
        // retrieve current month
        Month currentMonth = ld.getMonth();
        // retrieve current year
        int year = ld.getYear();
        String yearStr = String.valueOf(year);
        String currentMonthYearStr = currentMonth.toString() + " " + yearStr;
        // finalMonth will be used in SetComboBoxes to set ComboBox choices up through most recently complete month
        finalMonth = RetrieveLastMonth(currentMonthYearStr);
        SetComboBoxes( );
        // set Radio Buttons as Group
        group.add(JAvgCasesRB);
        group.add(JTotalCasesRB);
        group.add(JAvgDeathsRB);
        group.add(JTotalDeathsRB);
        
        // assign to arrays average and total case numbers from March, 2020 thru most recent complete month
        mnthAvgCases = monthlyCases.mnthAvgCases;
        mnthTtlCases = monthlyCases.mnthTtlCases;
        // assign to arrays average and total death numbers from March, 2020 thru most recent complete month
        mnthAvgDeaths = monthlyCases.mnthAvgDeaths;
        mnthTtlDeaths = monthlyCases.mnthTtlDeaths;
    }
    
    private int RetrieveLastMonth (String yearMonth)
    {
        int lastMonth = -1;
        
        do
        {
            lastMonth++;
        } 
        while (! yearMonth.trim().equals(months[lastMonth].trim()));
        
        return lastMonth;
    }
   
    public void Process(String stat) throws SQLException 
    {
        globalStat = stat;
  
        switch (stat)
        {
            case "AVERAGE CASES":
                JAvgCasesRB.setSelected(true);
                break;
            case "TOTAL CASES":
                JTotalCasesRB.setSelected(true);
                break;
            case "AVERAGE DEATHS":
                JAvgDeathsRB.setSelected(true);
                break;
            case "TOTAL DEATHS":
                JTotalDeathsRB.setSelected(true);
                break;  
        }
    }
    
    private void SetComboBoxes ( )
    {
        for ( int i = 0; i < finalMonth; i++)
        {
            JStartMonthCB.addItem(months[i]);    
            JEndMonthCB.addItem(months[i]);
        }
    }
     
    public void SetScrollPane ( )
    {
        lowerPnlSP.getViewport().add(globalTable);
        lowerPnlSP.getViewport().setBackground(gray);
        lowerPnlSP.setBounds(0, 0, JLowerPnl.getWidth(), JLowerPnl.getHeight());
        JLowerPnl.add(lowerPnlSP);
    } 
    
    public void paint(Graphics g)
    {
        super.paint(g);
        
        if (startMonth != null && endMonth != null)
        {
         
        }        
   }                                           
    
   public int GetMonth (int index)
   {
       int monthIndex = 0;
        
       do 
       {
           monthIndex++;
       }
       while (monthIndex != index);
        
       return monthIndex - 1;
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() 
    {
        JButtonGroup = new javax.swing.ButtonGroup();
        JUpperPnl = new javax.swing.JPanel();
        JMonthPnl = new javax.swing.JPanel();
        JStartMonthCB = new javax.swing.JComboBox();
        JEndMonthCB = new javax.swing.JComboBox();
        JSelectedMonthsLbl = new javax.swing.JLabel();
        JStatisticPnl = new javax.swing.JPanel();
        JTotalDeathsRB = new javax.swing.JRadioButton();
        JAvgCasesRB = new javax.swing.JRadioButton();
        JTotalCasesRB = new javax.swing.JRadioButton();
        JAvgDeathsRB = new javax.swing.JRadioButton();
        JMonthlyReportsSubmitBtn = new javax.swing.JButton();
        JMonthlyReportResetBtn = new javax.swing.JButton();
        JLowerPnl = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        JPrintMenuItem = new javax.swing.JMenu();
        JPrintFile = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 153));
        setMaximumSize(new java.awt.Dimension(870, 800));
        setSize(new java.awt.Dimension(870, 800));

        JUpperPnl.setBackground(royalBlue);
        JUpperPnl.setBorder(BorderFactory.createTitledBorder(null, " Monthly Statistics", javax.swing.border.TitledBorder.CENTER, 
                            javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14), Color.white) );
        JUpperPnl.setPreferredSize(new java.awt.Dimension(870, 190));

        JMonthPnl.setBackground(new java.awt.Color(204, 204, 204));
        JMonthPnl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        JStartMonthCB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JStartMonthCB.setMaximumRowCount(12);
        JStartMonthCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Starting Month" }));
        JStartMonthCB.setPreferredSize(new java.awt.Dimension(160, 25));
        JStartMonthCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JStartMonthCBActionPerformed(evt);
            }
        });

        JEndMonthCB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JEndMonthCB.setMaximumRowCount(12);
        JEndMonthCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ending Month" }));
        JEndMonthCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JEndMonthCBActionPerformed(evt);
            }
        });

        JSelectedMonthsLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JSelectedMonthsLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JSelectedMonthsLbl.setText(" ");
        JSelectedMonthsLbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JSelectedMonthsLbl.setPreferredSize(new java.awt.Dimension(200, 30));

        javax.swing.GroupLayout JMonthPnlLayout = new javax.swing.GroupLayout(JMonthPnl);
        JMonthPnl.setLayout(JMonthPnlLayout);
        JMonthPnlLayout.setHorizontalGroup(
            JMonthPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JMonthPnlLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(JStartMonthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(JEndMonthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JMonthPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JSelectedMonthsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        JMonthPnlLayout.setVerticalGroup(
            JMonthPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JMonthPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JMonthPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JStartMonthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JEndMonthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(JSelectedMonthsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        JStatisticPnl.setBackground(new java.awt.Color(204, 204, 204));
        JStatisticPnl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        JTotalDeathsRB.setBackground(new java.awt.Color(204, 204, 204));
        JTotalDeathsRB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTotalDeathsRB.setText("Total Deaths");
        JTotalDeathsRB.setPreferredSize(new java.awt.Dimension(140, 25));
        JTotalDeathsRB.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                JTotalDeathsRBActionPerformed(evt);
            }
        });

        JAvgCasesRB.setBackground(new java.awt.Color(204, 204, 204));
        JAvgCasesRB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JAvgCasesRB.setText("Average Daily Cases");
        JAvgCasesRB.setPreferredSize(new java.awt.Dimension(140, 25));
        JAvgCasesRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JAvgCasesRBActionPerformed(evt);
            }
        });

        JTotalCasesRB.setBackground(new java.awt.Color(204, 204, 204));
        JTotalCasesRB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTotalCasesRB.setText("Total Cases");
        JTotalCasesRB.setPreferredSize(new java.awt.Dimension(140, 25));
        JTotalCasesRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTotalCasesRBActionPerformed(evt);
            }
        });

        JAvgDeathsRB.setBackground(new java.awt.Color(204, 204, 204));
        JAvgDeathsRB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JAvgDeathsRB.setText("Average Daily Deaths");
        JAvgDeathsRB.setPreferredSize(new java.awt.Dimension(140, 25));
        JAvgDeathsRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JAvgDeathsRBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JStatisticPnlLayout = new javax.swing.GroupLayout(JStatisticPnl);
        JStatisticPnl.setLayout(JStatisticPnlLayout);
        JStatisticPnlLayout.setHorizontalGroup(
            JStatisticPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JStatisticPnlLayout.createSequentialGroup()
                .addGroup(JStatisticPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JStatisticPnlLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(JAvgCasesRB, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JTotalCasesRB, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JStatisticPnlLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(JAvgDeathsRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JTotalDeathsRB, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JStatisticPnlLayout.setVerticalGroup(
            JStatisticPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JStatisticPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JStatisticPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JAvgCasesRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTotalCasesRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JStatisticPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTotalDeathsRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JAvgDeathsRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        JMonthlyReportsSubmitBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JMonthlyReportsSubmitBtn.setText("Submit");
        JMonthlyReportsSubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMonthlyReportsSubmitBtnActionPerformed(evt);
            }
        });

        JMonthlyReportResetBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JMonthlyReportResetBtn.setText("Reset");
        JMonthlyReportResetBtn.setPreferredSize(new java.awt.Dimension(75, 25));
        JMonthlyReportResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMonthlyReportResetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JUpperPnlLayout = new javax.swing.GroupLayout(JUpperPnl);
        JUpperPnl.setLayout(JUpperPnlLayout);
        JUpperPnlLayout.setHorizontalGroup(
            JUpperPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JUpperPnlLayout.createSequentialGroup()
                .addComponent(JMonthPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JStatisticPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JUpperPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JMonthlyReportResetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JMonthlyReportsSubmitBtn))
                .addContainerGap())
        );
        JUpperPnlLayout.setVerticalGroup(
            JUpperPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JUpperPnlLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(JUpperPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JUpperPnlLayout.createSequentialGroup()
                        .addGroup(JUpperPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JStatisticPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JMonthPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(JUpperPnlLayout.createSequentialGroup()
                        .addComponent(JMonthlyReportsSubmitBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JMonthlyReportResetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );

        JLowerPnl.setBackground(new java.awt.Color(51, 51, 51));
        JLowerPnl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        JLowerPnl.setPreferredSize(new java.awt.Dimension(870, 510));

        javax.swing.GroupLayout JLowerPnlLayout = new javax.swing.GroupLayout(JLowerPnl);
        JLowerPnl.setLayout(JLowerPnlLayout);
        JLowerPnlLayout.setHorizontalGroup(
            JLowerPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );
        JLowerPnlLayout.setVerticalGroup(
            JLowerPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        JPrintMenuItem.setText("File");

        JPrintFile.setText("Print");
        JPrintFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPrintFileActionPerformed(evt);
            }
        });
        JPrintMenuItem.add(JPrintFile);

        jMenuBar1.add(JPrintMenuItem);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLowerPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
                    .addComponent(JUpperPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JUpperPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLowerPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void JStartMonthCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JStartMonthCBActionPerformed
        
        startMonth = JStartMonthCB.getSelectedItem().toString();
        startMonthIndex = JStartMonthCB.getSelectedIndex();
     
        if (startMonthIndex > endMonthIndex && endMonth != null)
        {
             JOptionPane.showMessageDialog(messageFrame, "Starting Month must preced Ending Month.");
             JStartMonthCB.setSelectedIndex(0);
        }
        else if (endMonth != null)
        {
            numOfRows = endMonthIndex - startMonthIndex + 1;
           
            if(firstTimeThru)
            {
                tm = new DefaultTableModel( );
                firstTimeThru = false;
            }
            else
            {
                tm.setRowCount(0);
                tm = new DefaultTableModel( );
            }
            
            JSelectedMonthsLbl.setText(startMonth + "  THROUGH  " + endMonth);
        }
            
    }

    private void JEndMonthCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JEndMonthCBActionPerformed

        endMonth = JEndMonthCB.getSelectedItem().toString();
        endMonthIndex = JEndMonthCB.getSelectedIndex();
        
        numOfRows = endMonthIndex - startMonthIndex + 1;

        if (startMonth != null && endMonthIndex < startMonthIndex)
        {
             JOptionPane.showMessageDialog(messageFrame, "Ending Month cannot precede Starting Month.");
             JEndMonthCB.setSelectedIndex(0);
        }
        else if (startMonth != null)
        {
            numOfRows = endMonthIndex - startMonthIndex + 1;
        
            if(firstTimeThru)
            {
                tm = new DefaultTableModel( );
                firstTimeThru = false;
            }
            else
            {
                tm.setRowCount(0);
                tm = new DefaultTableModel( );
            }
            
            JSelectedMonthsLbl.setText(startMonth + "  THROUGH  " + endMonth);
        }
    }

    private void JTotalDeathsRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTotalDeathsRBActionPerformed

        setEnabled(true);
        globalStat = "TOTAL DEATHS";
    }

    private void JAvgCasesRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JAvgCasesRBActionPerformed

        setEnabled(true);
        globalStat = "AVERAGE CASES";
    }

    private void JTotalCasesRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTotalCasesRBActionPerformed

        setEnabled(true);
        globalStat = "TOTAL CASES";
    }

    private void JAvgDeathsRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JAvgDeathsRBActionPerformed

        setEnabled(true);
        globalStat = "AVERAGE DEATHS";
    }

    private boolean CategorySelected( )
    {
        if (JAvgCasesRB.isSelected() || JTotalCasesRB.isSelected() || JAvgDeathsRB.isSelected() || JTotalDeathsRB.isSelected())
            return true;
        else
            return false;
    }
    
    private void JMonthlyReportsSubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMonthlyReportsSubmitBtnActionPerformed
        
        if (!CategorySelected( ))
             JOptionPane.showMessageDialog(messageFrame, "Select Category.");
        else if (startMonth != null && endMonth != null)
        {
            AddTableData(globalStat);
            SetScrollPane( );
        }
        else if (startMonth == null && endMonth == null)
             JOptionPane.showMessageDialog(messageFrame, "Select Starting Month and Ending Month.");
        else if (startMonth == null)
             JOptionPane.showMessageDialog(messageFrame, "Select Starting Month.");
        else
             JOptionPane.showMessageDialog(messageFrame, "Select Ending Month");     
    }

    private void JMonthlyReportResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMonthlyReportResetBtnActionPerformed
        
        JStartMonthCB.setSelectedIndex(0);
        JEndMonthCB.setSelectedIndex(0);
        startMonth = null;
        endMonth = null;
        JSelectedMonthsLbl.setText("");
        group.clearSelection();
        globalStat = "";
        tm.setRowCount(0);
    }

    private void JPrintFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPrintFileActionPerformed
        
        try
        {
            globalTable.print();
        } 
        catch (PrinterException ex) 
        {
            Logger.getLogger(JMonthlyReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class DataHolder
    {
        String dataHolderArr [][];
        private int startMonthIndex;
        private int endMonthIndex;
        
        public DataHolder (int start, int end)
        {
            dataHolderArr = new String [end - start + 1][2];
            startMonthIndex = start;
            endMonthIndex = end;
            FillDataHolderArr(startMonthIndex - 1);
        }
        
        public String [][] FillDataHolderArr (int offset)
        {
            if (offset == -1)
                offset = 1;
            for (int r = 0; r < endMonthIndex - startMonthIndex + 1; r++)
            {
               dataHolderArr[r][0] = months[offset];
               
               switch (globalStat)
               {
                   case "AVERAGE CASES":
                       dataHolderArr[r][1] = mnthAvgCases.get(offset).toString();
                       break;
                   case "AVERAGE DEATHS":
                       dataHolderArr[r][1] = mnthAvgDeaths.get(offset).toString();
                       break;
                   case "TOTAL CASES":
                       dataHolderArr[r][1] = mnthTtlCases.get(offset).toString();
                       break;
                   case "TOTAL DEATHS":
                       dataHolderArr[r][1] = mnthTtlDeaths.get(offset).toString();
                       break;     
               }
                 
               offset++;
            }
            
            return dataHolderArr;   
        }

        public int getStartMonthIndex() {
            return startMonthIndex;
        }

        public void setStartMonthIndex(int startMonthIndex) {
            this.startMonthIndex = startMonthIndex;
        }

        public int getEndMonthIndex() {
            return endMonthIndex;
        }

        public void setEndMonthIndex(int endMonthIndex) {
            this.endMonthIndex = endMonthIndex;
        }   
    }
    
     public void clearData ( )
     {
        tm.setRowCount(0);
     }
     
     public JTable CreateTable( )
     {
        JTable table;
        
        if (tm != null)
            tm.setRowCount(0);
        
        table = new JTable( );
        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        table.setFont(new Font ("Arial",Font.TRUETYPE_FONT,18));
        table.setFillsViewportHeight(true);
        table.setBackground(Color.DARK_GRAY);
        table.setForeground(Color.WHITE);
        table.setGridColor(paleYellow);
        table.setShowGrid(true);
        table.setRowHeight(30);
     
        return table;
    }
     
    public void AddTableData (String statistic)
    {
        globalTable = CreateTable( );
            
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer( );
        renderer.setHorizontalAlignment(JLabel.CENTER);
        TableCellRenderer rendererFromHeader = globalTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
            
        if (tm != null)
            tm.setRowCount(0);
        dh = new DataHolder(startMonthIndex, endMonthIndex);
        Object[][] data = dh.dataHolderArr; 
        tm = new DefaultTableModel(data, column);
        globalTable.setModel(tm);
        globalTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
        globalTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
        globalTable.getColumnModel().getColumn(1).setHeaderValue(globalStat);
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException 
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
            java.util.logging.Logger.getLogger(JMonthlyReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JMonthlyReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JMonthlyReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JMonthlyReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            JMonthlyReports monthlyReports = new JMonthlyReports( );
            public void run()
                    
            {
                monthlyReports.setVisible(true);
            }
       });          
    }                      

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JAvgCasesRB;
    private javax.swing.JRadioButton JAvgDeathsRB;
    private javax.swing.ButtonGroup JButtonGroup;
    private javax.swing.JComboBox JEndMonthCB;
    private javax.swing.JPanel JLowerPnl;
    private javax.swing.JPanel JMonthPnl;
    private javax.swing.JButton JMonthlyReportResetBtn;
    private javax.swing.JButton JMonthlyReportsSubmitBtn;
    private javax.swing.JMenuItem JPrintFile;
    private javax.swing.JMenu JPrintMenuItem;
    private javax.swing.JLabel JSelectedMonthsLbl;
    private javax.swing.JComboBox JStartMonthCB;
    private javax.swing.JPanel JStatisticPnl;
    private javax.swing.JRadioButton JTotalCasesRB;
    private javax.swing.JRadioButton JTotalDeathsRB;
    private javax.swing.JPanel JUpperPnl;
    private javax.swing.JMenuBar jMenuBar1;
}
