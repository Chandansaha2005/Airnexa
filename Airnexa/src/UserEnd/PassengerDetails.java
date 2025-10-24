/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserEnd;
import Payment_and_Receipt.Confirmation;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 *
 * @author User
 */
public class PassengerDetails extends javax.swing.JFrame {
    
    int u_id, f_id, b_id;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int seats;
    double total, price;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PassengerDetails.class.getName());

    /**
     * Creates new form SearchFlight
     * @param f_id
     * @param u_id
     * @throws java.sql.SQLException
     */
    public PassengerDetails(int f_id, int u_id) throws SQLException {
        initComponents();
        this.con = UserEnd.DatabaseConnection.getConnection();
        
        this.u_id = u_id;
        this.f_id = f_id;
        this.price = getPrice();
        b1.setEnabled(false);
        loadData();
        setLocationRelativeTo(null);
    }        

    public void refreshData(){
        loadData();
    }    

    private boolean isValidPassengerDetails() {

        // --- Get text from your JTextFields (replace with your variable names) ---
        String fullName = t1.getText().trim();
        String seatNumber = t2.getText().trim();
        String aadharNumber = t4.getText().trim();
        String ageStr = t3.getText().trim();
        String totalPassengersStr = t5.getText().trim();

        // --- Validation Checks ---

        // Check for any empty fields first
        if (fullName.isEmpty() || seatNumber.isEmpty() || aadharNumber.isEmpty() || ageStr.isEmpty() || totalPassengersStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required. Please fill them all.");
            return false;
        }

        // 1. Full Name: Must be letters and spaces only
        if (!fullName.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Invalid Full Name. Please use only letters and spaces.");
            return false;
        }

        // 2. Seat Number: Must be in a format like "12A" or "3F" (case-insensitive)
        if (!seatNumber.matches("(?i)^\\d{1,2}[A-Z]$")) {
            JOptionPane.showMessageDialog(this, "Invalid Seat Number. Format must be like '23A'.");
            return false;
        }

        // 3. AADHAR Number: Must be exactly 12 numeric digits
        if (!aadharNumber.matches("^\\d{12}$")) {
            JOptionPane.showMessageDialog(this, "Invalid AADHAR Number. Must be exactly 12 numeric digits.");
            return false;
        }

        // 4. Age: Must be a number between 1 and 120
        try {
            int age = Integer.parseInt(ageStr);
            if (age < 1 || age > 120) {
                JOptionPane.showMessageDialog(this, "Invalid Age. Must be between 1 and 120.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Age. Must be a valid number.");
            return false;
        }

        // 5. Total Passengers: Must be a number greater than 0
        try {
            int totalPassengers = Integer.parseInt(totalPassengersStr);
            if (totalPassengers < 1) {
                JOptionPane.showMessageDialog(this, "Invalid Total Passengers. Must be at least 1.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Total Passengers. Must be a valid number.");
            return false;
        }

        // If all checks pass, return true
        return true;
    }
    
    private void getBookinID(){
        try{
            this.con = UserEnd.DatabaseConnection.getConnection();
            pst = con.prepareStatement("select booking_id from booking where user_id = ? and flight_id = ?");
            pst.setInt(1, u_id);
            pst.setInt(2, f_id);
            rs = pst.executeQuery();
            
            while(rs.next()){
                this.b_id = rs.getInt("booking_id");
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void updateCoPassenger(){
        try{                       
            String nm, s_no, a_no;   
            int age;
            getBookinID();            
            nm = t1.getText();
            age = Integer.parseInt(t3.getText());
            s_no = t2.getText();
            a_no = t4.getText();
                        
            if(nm.equals("") || t3.getText().equals("") || s_no.equals("") || a_no.equals("") || t5.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane,"Please Fill all of the fields!!");
            }
            else{
                String sql;
                sql ="INSERT INTO co_passengers (booking_id, name, age, seat_number, aadhar) VALUES (?, ?, ?, ?, ?)";
                pst = this.con.prepareStatement(sql);
                pst.setInt(1, this.b_id);
                pst.setString(2, nm);
                pst.setInt(3, age);
                pst.setString(4, s_no);
                pst.setString(5, a_no);
                if(JOptionPane.showConfirmDialog(rootPane, "Confirm..?","Updating Co-Passenger Details",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION && pst.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(rootPane,"Updation Successful...!!");
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,"Error!!\n" + e);
        }
    }
    
    private void updateBooking(){
        try{            
            String sql;
            String paymentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            sql ="INSERT INTO booking (user_id, flight_id, date_time, ticket_price, number_of_seats) VALUES ( ?, ?, ?, ?, ?)";
            pst = this.con.prepareStatement(sql);            
            pst.setInt(1,this.u_id);
            pst.setInt(2,this.f_id);
            pst.setTimestamp(3, stringToTimestamp(paymentDate));
            pst.setDouble(4,this.total);
            pst.setInt(5,this.seats);
            if(pst.executeUpdate() == 1){
                JOptionPane.showMessageDialog(rootPane,"Updation Successful...!!");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,"Error!!\n" + e);
        }
    }
    
    private Timestamp stringToTimestamp(String s){
        Timestamp t = null;
        try {
            // Use a standard SimpleDateFormat to parse the string into a java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parsedDate = sdf.parse(s);

            // Create a java.sql.Timestamp using the milliseconds from the java.util.Date
            t = new Timestamp(parsedDate.getTime());
        } catch (ParseException ex) {
            // Log the error instead of just printing it
            logger.log(java.util.logging.Level.SEVERE, "Date parsing failed", ex);
            // Display an error message to the user
            JOptionPane.showMessageDialog(this, "Error parsing date: " + ex.getMessage(), "Date Error", JOptionPane.ERROR_MESSAGE);
        }
        return t;
    }
    
    private void goToNextPage(int n){        
        if(n > 1){            
            Co_PassengersDetails ob = new Co_PassengersDetails(b_id, n);
            ob.setVisible(true);
        }        
        else {           
            Confirmation ob = new Confirmation(this.b_id);
            ob.setVisible(true);
        }        
    }            
    
    private PassengerDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private double getPrice(){
        double pr = 0.0;
        try{
            String sql = "select * from flight where flight_id = ?";
            pst = this.con.prepareStatement(sql);
            pst.setInt(1, this.f_id);
            rs = pst.executeQuery();
            
            if(rs.next()){
                pr = rs.getDouble("ticket_price");
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Flight not found");
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
        return pr;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        pr1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        workingPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        t3 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        t5 = new javax.swing.JTextField();
        b1 = new javax.swing.JButton();
        cb1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titlePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 0, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("AirNexa");
        titlePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 186, 38));

        jButton2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jButton2.setText("<--");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        titlePanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 60, 25));

        pr1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pr1ActionPerformed(evt);
            }
        });
        titlePanel.add(pr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 220, 25));

        MainPanel.add(titlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        MainPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 1000, 50));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        MainPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 50, 550));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        MainPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, -1, -1));

        workingPanel.setBackground(new java.awt.Color(255, 255, 255));
        workingPanel.setPreferredSize(new java.awt.Dimension(900, 600));
        workingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("passenger details");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        workingPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Full Name:");

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Age:");

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("AADHAR Number :");

        jLabel9.setBackground(new java.awt.Color(153, 153, 153));
        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Seat Number:");

        jLabel7.setBackground(new java.awt.Color(153, 153, 153));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Total Passengers");

        t1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        t2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        t3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        t4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        t4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t4ActionPerformed(evt);
            }
        });

        t5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        t5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t5ActionPerformed(evt);
            }
        });

        b1.setBackground(new java.awt.Color(153, 153, 153));
        b1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        b1.setForeground(new java.awt.Color(0, 0, 0));
        b1.setText("NEXT");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        cb1.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        cb1.setText("I here by declare that the above mentioned details are true as per my knowledge");
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(80, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(cb1)
                .addGap(35, 35, 35)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        workingPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 900, 500));

        MainPanel.add(workingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 900, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadData(){
        try{
            String s = "select username from user where user_id = ?";
            pst = this.con.prepareStatement(s);
            pst.setInt(1, this.u_id);
            rs = pst.executeQuery();
            if(rs.next()){
                pr1.setText(rs.getString("username"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        UserDashboard ob = new UserDashboard(u_id);
        this.setVisible(false);
        ob.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void pr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pr1ActionPerformed
        profile pr = new profile(this.u_id,this);
        //this.setVisible(false);
        pr.setVisible(true);
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_pr1ActionPerformed

    private void t5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t5ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        if(isValidPassengerDetails()){
            this.seats = Integer.parseInt(t5.getText());
            this.total = this.price * this.seats;        
            updateBooking();
            updateCoPassenger();
            goToNextPage(seats);           
            this.dispose();
        }
    }//GEN-LAST:event_b1ActionPerformed

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
        // TODO add your handling code here:
        b1.setEnabled(cb1.isSelected());
        
    }//GEN-LAST:event_cb1ActionPerformed

    private void t4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t4ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new PassengerDetails().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton b1;
    private javax.swing.JCheckBox cb1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton pr1;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    private javax.swing.JTextField t5;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel workingPanel;
    // End of variables declaration//GEN-END:variables
}
