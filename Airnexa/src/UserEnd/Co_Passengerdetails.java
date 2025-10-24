/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.HeadlessException;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
/**
 *
 * @author CHANDAN
 */
public class Co_Passengerdetails extends javax.swing.JFrame {
    /**
     * Creates new form homepage
     */
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String c_id, b_id, f_id, u_id;
    double total;
    int seats;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Co_Passengerdetails.class.getName());
    public Co_Passengerdetails(String f_id, String u_id, double price, int seats) {
        initComponents();
        this.f_id = f_id;
        this.u_id = u_id;
        this.c_id = c_idGenerator();
        this.b_id = b_idGenerator();
        this.seats = seats;
        this.total = price * this.seats;
        B1.setEnabled(Check1.isSelected());
    }
    private String c_idGenerator(){
        int c = 1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline?useSSL=false","root","Meghna@1208");
            String sql;
            sql = "select * from co_passenger";
            pst = this.con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                c++;
            }                       
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(rootPane,"Error!!\n" + e);
        }
        return "" + c;
    }
    private String b_idGenerator(){
        int c=1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline?useSSL=false","root","Meghna@1208");
            String sql;
            sql = "select * from booking";
            pst = this.con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                c++;
            }                       
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(rootPane,"Error!!\n" + e);
        }
        return "" + c;
    } 
    private void updateCoPassenger(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline?useSSL=false","root","Meghna@1208");
            
            String nm, age, s_no, a_no;            
            nm = Text1.getText();
            age = Text2.getText();
            s_no = Text5.getText();
            a_no = Text3.getText();
                        
            if(nm.equals("") || age.equals("") || s_no.equals("") || a_no.equals("")){
                JOptionPane.showMessageDialog(rootPane,"Please Fill all of the fields!!");
            }
            else{
                String sql;
                sql ="INSERT INTO co_passenger (co_passenger_id, booking_id, name, age, seat_number, aadhar) VALUES (?, ?, ?, ?, ?, ?)";
                pst = this.con.prepareStatement(sql);
                pst.setString(1,this.c_id);
                pst.setString(2, this.b_id);
                pst.setString(3, nm);
                pst.setString(4, age);
                pst.setString(5, s_no);
                pst.setString(6, a_no);
                if(JOptionPane.showConfirmDialog(rootPane, "Confirm..?","Updating Co-Passenger Details",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION && pst.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(rootPane,"Updation Successful...!!");
                }
            }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(rootPane,"Error!!\n" + e);
        }
    }
    private void updateBooking(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline?useSSL=false","root","Meghna@1208");
            
            String sql;
            String paymentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            sql ="INSERT INTO booking (booking_id, user_id, flight_id, date_time, ticket_price, number_of_seats) VALUES (?, ?, ?, ?, ?, ?)";
            pst = this.con.prepareStatement(sql);
            pst.setString(1,this.b_id);
            pst.setString(2,this.u_id);
            pst.setString(3,this.f_id);
            pst.setTimestamp(4, stringToTimestamp(paymentDate));
            pst.setDouble(5,this.total);
            pst.setInt(6,this.seats);
            if(pst.executeUpdate() == 1){
                JOptionPane.showMessageDialog(rootPane,"Updation Successful...!!");
            }
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(rootPane,"Error!!\n" + e);
        }
    }
    private void update(){
        updateCoPassenger();
        updateBooking();
    }
    private Timestamp stringToTimestamp(String s){
        Timestamp t = null;
        try {
            // Use a standard SimpleDateFormat to parse the string into a java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Text1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Text2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Text3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Text5 = new javax.swing.JTextField();
        Check1 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        B1 = new javax.swing.JButton();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setBackground(new java.awt.Color(0, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("       Co-Passenger Details");

        jLabel2.setBackground(new java.awt.Color(0, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 0));
        jLabel2.setText("Full Name:");

        Text1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text1ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 51, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 0));
        jLabel4.setText("Age:");

        Text2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text2ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 0));
        jLabel3.setText(" AADHAR Number :");

        Text3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text3ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 51, 51));
        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 0));
        jLabel9.setText("Seat Number:");

        Text5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text5ActionPerformed(evt);
            }
        });

        Check1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Check1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 0));
        jLabel12.setText("I HERE BY DECLARE ALL THE ABOVE STATEMENTS STATED TRUE TO MY KNOWLEDGE.");

        B1.setText("NEXT>");
        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Check1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(525, 525, 525)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Text5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Text2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Text3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Text1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(259, 259, 259))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B1)
                .addGap(376, 376, 376))
            .addGroup(layout.createSequentialGroup()
                .addGap(485, 485, 485)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Text1))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Text2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Text3)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Text5))
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Check1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addGap(22, 22, 22)
                .addComponent(B1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Text1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text1ActionPerformed

    }//GEN-LAST:event_Text1ActionPerformed

    private void Text2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text2ActionPerformed

    private void Text3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text3ActionPerformed

    private void Text5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Text5ActionPerformed

    private void Check1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Check1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Check1ActionPerformed

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
   
        update();
        //Confirmation ob = new Confirmation(this.b_id);
        //ob.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_B1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
    /* Set the Nimbus look and feel */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info :
                javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException |
             InstantiationException |
             IllegalAccessException |
             javax.swing.UnsupportedLookAndFeelException ex) {
        // or use a proper logger if you have one
        
    }

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        new Co_Passengerdetails("1", "1", 0.0, 0).setVisible(true);
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B1;
    private javax.swing.JCheckBox Check1;
    private javax.swing.JTextField Text1;
    private javax.swing.JTextField Text2;
    private javax.swing.JTextField Text3;
    private javax.swing.JTextField Text5;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}