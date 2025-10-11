/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserEnd;

/**
 *
 * @author User
 */

import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import javax.imageio.ImageIO;

public class UserDashboard extends javax.swing.JFrame {
    
    Connection con;
    Statement stmt, stmt2;
    PreparedStatement pst,pst1;
    ResultSet rs,rs1, rs2;
    String dept, arrv, u_id, fl_no;
    private TableRowSorter<TableModel> sorter;
    private DefaultTableModel tableModel;
    double maxPriceValue;
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserDashboard.class.getName());

    /**
     * Creates new form UserDashboard
     * @param u_id
     */
    public UserDashboard(String u_id) {
        initComponents();            
        try{
            con = UserEnd.DatabaseConnection.getConnection();
//            this.u_id = u_id;
            this.u_id = "1";
            
            loadData();
            
            sp1.setVisible(false);            
            b4.setVisible(false);
            b3.setVisible(false);
            b2.setVisible(false);                                                                     
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }              
    }     

    public void refreshData(){
        loadData();
    }
    
    private void loadData(){
        try{
            String s = "select username from user where user_id = ?";
            pst1 = this.con.prepareStatement(s);
            pst1.setString(1, this.u_id);
            rs1 = pst1.executeQuery();
            if(rs1.next()){
                pr1.setText(rs1.getString("username"));
            }
            
            String sql = "select distinct depart_from from flight order by depart_from asc";
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(sql);
            
            String sql1 = "select distinct arrive_at from flight order by arrive_at asc";
            stmt2 = this.con.createStatement();
            rs2 = stmt2.executeQuery(sql1);
            c1.addItem("Select");
            c2.addItem("Select");
            
            while(rs.next()){
                c1.addItem(rs.getString("depart_from"));               
            }                
            while(rs2.next()){
                 c2.addItem(rs2.getString("arrive_at"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    private void applyFilters(String airline, double minPrice, double maxPrice) {
        if (airline.equals("All") && minPrice == 0.0 && maxPrice == 99999.0) {
            sorter.setRowFilter(null); // No filters, show all data
            return;
        }

        RowFilter<TableModel, Object> rowFilter = new RowFilter<TableModel, Object>() {
            @Override
            public boolean include(Entry<? extends TableModel, ? extends Object> entry) {
                String entryAirline = (String) entry.getValue(5); // Airline is at index 5

                // Your "Ticekt Price" column is stored as a String in the table model,
                // so you need to parse it to a number for comparison.
                double entryPrice;
                try {
                    entryPrice = Double.parseDouble((String) entry.getValue(7)); // Price is at index 7
                } catch (NumberFormatException ex) {
                    entryPrice = 0.0; // Handle cases where the price is not a number
                }

                // Check if the row matches the filter criteria
                boolean airlineMatch = airline.equals("All") || airline.equals(entryAirline);
                boolean priceMatch = entryPrice >= minPrice && entryPrice <= maxPrice;

                return airlineMatch && priceMatch;
            }
        };
        sorter.setRowFilter(rowFilter);
    }
    
    private String getFlightId(){
        String id = "";
        try{
            con = UserEnd.DatabaseConnection.getConnection();
            String sql = "select flight_id from flight where flight_no = ?";
            pst = this.con.prepareStatement(sql);
            pst.setString(1, fl_no);
            rs = pst.executeQuery();
            
            if(rs.next()){
                id = rs.getString("flight_id");
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
        return id;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>
    
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        workingPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        l1 = new javax.swing.JLabel();
        jp5 = new javax.swing.JPanel();
        h1 = new javax.swing.JLabel();
        c1 = new javax.swing.JComboBox<>();
        h2 = new javax.swing.JLabel();
        c2 = new javax.swing.JComboBox<>();
        b1 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        sp1 = new javax.swing.JScrollPane();
        t1 = new javax.swing.JTable();
        b3 = new javax.swing.JButton();
        leftPanel = new javax.swing.JPanel();
        b2 = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        downPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pr1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1920, 1080));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setMaximumSize(new java.awt.Dimension(1700, 860));
        mainPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workingPanel.setBackground(new java.awt.Color(255, 255, 255));
        workingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        l1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 36)); // NOI18N
        l1.setForeground(new java.awt.Color(0, 0, 0));
        l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l1.setText("Book A Flight");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, 1700, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        workingPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1700, 100));

        jp5.setBackground(new java.awt.Color(255, 255, 255));

        h1.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        h1.setForeground(new java.awt.Color(0, 0, 0));
        h1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h1.setText("From");

        c1.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1ActionPerformed(evt);
            }
        });

        h2.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        h2.setForeground(new java.awt.Color(0, 0, 0));
        h2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h2.setText("To");

        c2.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2ActionPerformed(evt);
            }
        });

        b1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        b1.setText("Search");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b4.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        b4.setText("Filter");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        t1.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        t1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Flight Number", "Departure From", "Departure Time", "Arrival At", "Ariival Time", "Airline", "Available Seat", "Ticekt Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        t1.setMinimumSize(new java.awt.Dimension(100, 80));
        sp1.setViewportView(t1);

        b3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 28)); // NOI18N
        b3.setText("Book");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp5Layout = new javax.swing.GroupLayout(jp5);
        jp5.setLayout(jp5Layout);
        jp5Layout.setHorizontalGroup(
            jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp5Layout.createSequentialGroup()
                .addGroup(jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp5Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 1550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp5Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(h1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(h2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp5Layout.createSequentialGroup()
                        .addGap(1475, 1475, 1475)
                        .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {b1, b4});

        jp5Layout.setVerticalGroup(
            jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp5Layout.createSequentialGroup()
                .addGroup(jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp5Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(h2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(h1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jp5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b1, b4, c1, c2, h1, h2});

        workingPanel.add(jp5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1700, 760));

        mainPanel.add(workingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1700, 860));

        leftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        b2.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        b2.setText("<--");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        leftPanel.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 75, 35));

        rightPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        downPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titlePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("AirNexa");
        titlePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 110));

        pr1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pr1ActionPerformed(evt);
            }
        });
        titlePanel.add(pr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 30, 350, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1810, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1810, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(downPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pr1ActionPerformed
        profile pr = new profile(this.u_id,this);
        //this.setVisible(false);
        pr.setVisible(true);
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_pr1ActionPerformed

    private void c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c1ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
           
        if (c2.getSelectedItem().toString().equalsIgnoreCase("Select") && c1.getSelectedItem().toString().equalsIgnoreCase("Select")) {
            JOptionPane.showMessageDialog(rootPane, "Please select the source and destination");
            return;
        }

        dept = c1.getSelectedItem().toString();
        arrv = c2.getSelectedItem().toString();

        try {
            if (!dept.equals("Select") && arrv.equals("Select")) {
                pst = con.prepareStatement("SELECT * FROM flight WHERE depart_from = ?");
                pst.setString(1, dept);
            } else if (dept.equals("Select") && !arrv.equals("Select")) {
                pst = con.prepareStatement("SELECT * FROM flight WHERE arrive_at = ?");
                pst.setString(1, arrv);
            } else if (!dept.equals("Select") && !arrv.equals("Select")) {
                pst = con.prepareStatement("SELECT * FROM flight WHERE depart_from = ? AND arrive_at = ?");
                pst.setString(1, dept);
                pst.setString(2, arrv);
            }

            rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(rootPane, "No flights found for the selected route.");
                // Hide all flight-related UI elements
                sp1.setVisible(false);
                b4.setVisible(false);
                b3.setVisible(false);
                b2.setVisible(false);
            } else {
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(new String[] {
                    "Flight Number", "Departure From", "Departure Time", "Arrival At",
                    "Arrival Time", "Airline", "Available Seat", "Ticket Price"
                });

                // Collect airlines and find max price while populating the table
                List<String> airlinesList = new java.util.ArrayList<>();
                maxPriceValue = 0.0;

                while (rs.next()) {
                    String f_no = rs.getString("flight_no");
                    String f = rs.getString("depart_from");
                    String dt = rs.getString("departure_time");
                    String ar = rs.getString("arrive_at");
                    String at = rs.getString("arrival_time");
                    String air = rs.getString("airline");
                    String s = rs.getString("seat_availability");
                    String p = rs.getString("ticket_price");

                    Object row[] = {f_no, f, dt, ar, at, air, s, p};
                    model.addRow(row);

                    if (!airlinesList.contains(air)) {
                        airlinesList.add(air);
                    }

                    try {
                        double currentPrice = Double.parseDouble(p);
                        if (currentPrice > maxPriceValue) {
                            maxPriceValue = currentPrice;
                        }
                    } catch (NumberFormatException ex) {
                        // Log or handle the case where a price is not a number
                    }
                }

                tableModel = model;
                t1.setModel(tableModel);

                sorter = new TableRowSorter<>(tableModel);
                t1.setRowSorter(sorter);

                l1.setText("Flight Details");
                sp1.setVisible(true);
                b4.setVisible(true);
                b3.setVisible(true);
                b2.setVisible(true);
                b1.setVisible(false);
                h1.setVisible(false);
                h2.setVisible(false);
                c1.setVisible(false);
                c2.setVisible(false);

                // Add action listener to the filter button here
                // This ensures it has valid data to work with
                b4.addActionListener((java.awt.event.ActionEvent evt1) -> {
                    FlightFilterDialog dialog = new FlightFilterDialog(UserDashboard.this, airlinesList, maxPriceValue);
                    dialog.setVisible(true);
                    String selectedAirline = dialog.getSelectedAirline();
                    double minPrice = dialog.getMinPrice();
                    double maxPrice1 = dialog.getMaxPrice();
                    applyFilters(selectedAirline, minPrice, maxPrice1);
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        if(b4.getText().equalsIgnoreCase("Filter") && b4.isVisible()){
            l1.setText("Book A Flight");
            b1.setText("Search");
            
            h1.setVisible(true);
            h2.setVisible(true);
            
            c1.setVisible(true);
            c2.setVisible(true);
            
            sp1.setVisible(false);
            
            b4.setVisible(false);
            b3.setVisible(false);
            b2.setVisible(false);
            
            b1.setVisible(true);                                         
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_b2ActionPerformed

    private void c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        
        int selectedRow = t1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Please select a flight to book.");
        }
        else if(JOptionPane.showConfirmDialog(rootPane, "Confirm...?", "Confirm Booking", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            
            
            this.fl_no = t1.getValueAt(selectedRow, 0).toString();            
            
            String f_id = getFlightId();
            this.dispose();
            new PassengerDetails(f_id, this.u_id).setVisible(true);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
       
    }//GEN-LAST:event_b4ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UserDashboard("testuser").setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JComboBox<String> c1;
    private javax.swing.JComboBox<String> c2;
    private javax.swing.JPanel downPanel;
    private javax.swing.JLabel h1;
    private javax.swing.JLabel h2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jp5;
    private javax.swing.JLabel l1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton pr1;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JScrollPane sp1;
    private javax.swing.JTable t1;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel workingPanel;
    // End of variables declaration//GEN-END:variables

}
