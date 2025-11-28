
package org;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class JNotesFrame extends JFrame
{
    Font myFont = new Font ("Times New Roman", Font.PLAIN, 22);
    Border blackline = BorderFactory.createLineBorder(Color.black);
    
    String programNotes = "This program tracks U.S. Covid-19 statistics from March 1st, 2020 through March 17th, 2023."
            + " I created a MySql database (covid19db) for storing and retrieving the data. New entries must follow the most recent date added to the database."
            + " User can delete a specific date, but only one date can be deleted at a time and the deleted row must be repopulated with data before closing "
            + "the program. \n\n"
            + "In the Chart Panel user can select various dates and categories to chart, however, end date cannot exceed start date by more than 365 days."
            + " And if user selects more than 30 days to view, not all dates will be readable along the X-Axis of chart."
            + "Covid-19 Data was provided from the New York Times daily tracker.";
    private JPanel JNotesPagePnl;
    private JScrollPane jScrollPane1;
    private JTextArea JNotesTA;
            
    public JNotesFrame() 
    {
        initComponents();
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // set color of text to teal
        JNotesTA.setForeground(new Color(0, 128 ,128));
        JNotesTA.setFont(myFont);
        // add padding to text in TextArea
        JNotesTA.setBorder(BorderFactory.createCompoundBorder(blackline, 
                           BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        // TextArea will not be edited
        JNotesTA.setEditable(false);
        JNotesTA.setLineWrap(true);
        JNotesTA.setWrapStyleWord(true);
        // add String programNotes text
        JNotesTA.setText(programNotes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        JNotesPagePnl = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JNotesTA = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JNotesPagePnl.setBackground(new java.awt.Color(102, 102, 102));

        JNotesTA.setColumns(20);
        JNotesTA.setRows(5);
        jScrollPane1.setViewportView(JNotesTA);

        javax.swing.GroupLayout JNotesPagePnlLayout = new javax.swing.GroupLayout(JNotesPagePnl);
        JNotesPagePnl.setLayout(JNotesPagePnlLayout);
        JNotesPagePnlLayout.setHorizontalGroup(
            JNotesPagePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JNotesPagePnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
                .addContainerGap())
        );
        JNotesPagePnlLayout.setVerticalGroup(
            JNotesPagePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JNotesPagePnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JNotesPagePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JNotesPagePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(JNotesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JNotesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JNotesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JNotesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JNotesFrame().setVisible(true);
            }
        });
    }  
}
