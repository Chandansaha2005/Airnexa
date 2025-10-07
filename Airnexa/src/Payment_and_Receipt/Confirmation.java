/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Payment_and_Receipt;
import UserEnd.SearchFlight;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author User
 */
public class Confirmation extends javax.swing.JFrame {
    
    Connection con;
    PreparedStatement pst, pst1;
    ResultSet rs;
    String u_id,b_id,f_id,c_id;//*****b_id is must*****
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Confirmation.class.getName());

    /**
     * Creates new form Confirmation
     * @param b_id
     */
    public Confirmation(String b_id) {
        initComponents();        
        try{
            this.b_id = "2";
            this.u_id = "2";
            this.f_id = "2";                    
            //this.b_id = b_id;
            con = UserEnd.DatabaseConnection.getConnection();            
            b1.setEnabled(c1.isSelected());
            setAllFalse();
            loadUserName();
            loadFlightDetails();
            loadPassengerDetails();
            load();
            
            c2.addActionListener((ActionEvent e) -> {
                load();
            });
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    
    private void loadUserName(){
        try{
            String s = "select username from user where user_id = ?";
            pst = this.con.prepareStatement(s);
            pst.setString(1, this.u_id);
            rs = pst.executeQuery();
            if(rs.next()){
                pr1.setText(rs.getString("username"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,e);
        }
    }
    public void refreshData(){
        loadUserName();      
    }
    
    private void loadFlightDetails(){
        String h = h1.getText(), sql;    
        try{
            if(h.equalsIgnoreCase("Flight Details")){
                sql = "select * from flight where flight_id = ?";
                pst = this.con.prepareStatement(sql);
                pst.setString(1,f_id);//f_id
                rs = pst.executeQuery();

                if(rs.next()){
                    t1.setText(rs.getString("flight_no"));
                    t2.setText(rs.getString("airline"));
                    t3.setText(rs.getString("depart_from"));
                    t4.setText(rs.getString("departure_time"));
                    t5.setText(rs.getString("arrive_at"));
                    t6.setText(rs.getString("arrival_time"));
                    t7.setText(rs.getString("ticket_price"));
                    
                }
                
            } else if(h.equalsIgnoreCase("Passenger Details")){

            } else if(h.equalsIgnoreCase("Co-Passenger Details")){

            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void loadPassengerDetails(){
        try{
            String sql;
            sql = "select * from co_passengers where booking_id = ?";
            pst = this.con.prepareStatement(sql);
            pst.setString(1, b_id);            
            rs = pst.executeQuery();
            
            while(rs.next()){
                c2.addItem(rs.getString("co_passenger_id"));
            }            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void load(){
        try{
            this.c_id = c2.getSelectedItem().toString();
            t9.setText(b_id);
            String sql;
            sql = "select * from co_passengers where booking_id = ? and co_passenger_id = ?";
            pst = this.con.prepareStatement(sql);
            pst.setString(1, b_id);
            pst.setString(2, c_id);
            rs = pst.executeQuery();
            if(rs.next()){
                t10.setText(rs.getString("name"));
                t11.setText(rs.getString("age"));
                t12.setText(rs.getString("aadhar"));
                t13.setText(rs.getString("seat_number"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void deleteBooking(){
        try{
            String sql,sql1;
            sql = "delete from booking where booking_id = ?";
            pst = this.con.prepareStatement(sql);
            pst.setString(1, b_id);
            
            sql1 = "delete from co_passengers where booking_id = ?";
            pst1 = this.con.prepareStatement(sql1);
            pst1.setString(1, b_id);
            if(pst.executeUpdate() == 1 && pst1.executeUpdate() == 1){
                JOptionPane.showMessageDialog(rootPane, "Booking Cancelled Succesfully!!");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void setAllFalse(){
        t1.setEnabled(false);
        t2.setEnabled(false);
        t3.setEnabled(false);
        t4.setEnabled(false);
        t5.setEnabled(false);
        t6.setEnabled(false);
        t7.setEnabled(false);        
        t9.setEnabled(false);
        t10.setEnabled(false);
        t11.setEnabled(false);
        t12.setEnabled(false);
        t13.setEnabled(false);       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pr1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        leftPanel = new javax.swing.JPanel();
        downPanel = new javax.swing.JPanel();
        leftPanel1 = new javax.swing.JPanel();
        b3 = new javax.swing.JButton();
        heading = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        main = new javax.swing.JPanel();
        c1 = new javax.swing.JCheckBox();
        b1 = new javax.swing.JButton();
        Flight = new javax.swing.JPanel();
        h1 = new javax.swing.JLabel();
        l1 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        l5 = new javax.swing.JLabel();
        l6 = new javax.swing.JLabel();
        l7 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        t3 = new javax.swing.JTextField();
        t4 = new javax.swing.JTextField();
        t5 = new javax.swing.JTextField();
        t6 = new javax.swing.JTextField();
        t7 = new javax.swing.JTextField();
        Passengers = new javax.swing.JPanel();
        h2 = new javax.swing.JLabel();
        l8 = new javax.swing.JLabel();
        c2 = new javax.swing.JComboBox<>();
        t9 = new javax.swing.JTextField();
        l9 = new javax.swing.JLabel();
        t10 = new javax.swing.JTextField();
        l10 = new javax.swing.JLabel();
        t11 = new javax.swing.JTextField();
        l11 = new javax.swing.JLabel();
        t12 = new javax.swing.JTextField();
        l12 = new javax.swing.JLabel();
        t13 = new javax.swing.JTextField();
        l13 = new javax.swing.JLabel();
        b4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

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

        b2.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        b2.setText("<--");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        titlePanel.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 30, 75, 35));

        leftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        downPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        leftPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        b3.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        b3.setText("<--");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        leftPanel1.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 75, 35));

        heading.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Confirm Details");

        javax.swing.GroupLayout headingLayout = new javax.swing.GroupLayout(heading);
        heading.setLayout(headingLayout);
        headingLayout.setHorizontalGroup(
            headingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headingLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        headingLayout.setVerticalGroup(
            headingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1700, 760));

        main.setMinimumSize(new java.awt.Dimension(1700, 1200));
        main.setPreferredSize(new java.awt.Dimension(1700, 1875));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        c1.setFont(new java.awt.Font("Bookman Old Style", 0, 24)); // NOI18N
        c1.setForeground(new java.awt.Color(255, 255, 255));
        c1.setText("   I confirm that the above details are correct.");
        c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1ActionPerformed(evt);
            }
        });
        main.add(c1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1730, 580, 30));

        b1.setBackground(new java.awt.Color(204, 204, 204));
        b1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        b1.setForeground(new java.awt.Color(51, 51, 51));
        b1.setText("Confirm & Pay");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        main.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 1790, 300, 50));

        Flight.setBackground(new java.awt.Color(255, 255, 255));
        Flight.setForeground(new java.awt.Color(0, 0, 0));
        Flight.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        h1.setFont(new java.awt.Font("Times New Roman", 0, 44)); // NOI18N
        h1.setForeground(new java.awt.Color(0, 0, 0));
        h1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h1.setText("Flight Details");
        h1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Flight.add(h1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, -1));

        l1.setBackground(new java.awt.Color(0, 0, 0));
        l1.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l1.setForeground(new java.awt.Color(0, 0, 0));
        l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l1.setText("Flight Number");
        Flight.add(l1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 250, -1));

        l2.setBackground(new java.awt.Color(0, 0, 0));
        l2.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l2.setForeground(new java.awt.Color(0, 0, 0));
        l2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l2.setText("Airline");
        Flight.add(l2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 250, -1));

        l3.setBackground(new java.awt.Color(0, 0, 0));
        l3.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l3.setForeground(new java.awt.Color(0, 0, 0));
        l3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l3.setText("Depart From");
        Flight.add(l3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 250, -1));

        l4.setBackground(new java.awt.Color(0, 0, 0));
        l4.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l4.setForeground(new java.awt.Color(0, 0, 0));
        l4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l4.setText("Departure Time");
        Flight.add(l4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 250, -1));

        l5.setBackground(new java.awt.Color(0, 0, 0));
        l5.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l5.setForeground(new java.awt.Color(0, 0, 0));
        l5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l5.setText("Arrive At");
        Flight.add(l5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 250, -1));

        l6.setBackground(new java.awt.Color(0, 0, 0));
        l6.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l6.setForeground(new java.awt.Color(0, 0, 0));
        l6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l6.setText("Arrival Time");
        Flight.add(l6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 600, 250, -1));

        l7.setBackground(new java.awt.Color(0, 0, 0));
        l7.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l7.setForeground(new java.awt.Color(0, 0, 0));
        l7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l7.setText("Ticket Price");
        Flight.add(l7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 700, 250, 35));

        t1.setBackground(new java.awt.Color(51, 51, 51));
        t1.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t1.setForeground(new java.awt.Color(255, 255, 255));
        t1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Flight.add(t1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 100, 350, -1));

        t2.setBackground(new java.awt.Color(51, 51, 51));
        t2.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t2.setForeground(new java.awt.Color(255, 255, 255));
        t2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Flight.add(t2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 200, 350, -1));

        t3.setBackground(new java.awt.Color(51, 51, 51));
        t3.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t3.setForeground(new java.awt.Color(255, 255, 255));
        t3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Flight.add(t3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 300, 350, -1));

        t4.setBackground(new java.awt.Color(51, 51, 51));
        t4.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t4.setForeground(new java.awt.Color(255, 255, 255));
        t4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Flight.add(t4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 400, 350, -1));

        t5.setBackground(new java.awt.Color(51, 51, 51));
        t5.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t5.setForeground(new java.awt.Color(255, 255, 255));
        t5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Flight.add(t5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 500, 350, -1));

        t6.setBackground(new java.awt.Color(51, 51, 51));
        t6.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t6.setForeground(new java.awt.Color(255, 255, 255));
        t6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Flight.add(t6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 600, 350, -1));

        t7.setBackground(new java.awt.Color(51, 51, 51));
        t7.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t7.setForeground(new java.awt.Color(255, 255, 255));
        t7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Flight.add(t7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 700, 350, -1));

        main.add(Flight, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1600, 830));

        Passengers.setBackground(new java.awt.Color(255, 255, 255));
        Passengers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        h2.setFont(new java.awt.Font("Times New Roman", 0, 44)); // NOI18N
        h2.setForeground(new java.awt.Color(0, 0, 0));
        h2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h2.setText("Passenger Details");
        h2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Passengers.add(h2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, -1));

        l8.setBackground(new java.awt.Color(0, 0, 0));
        l8.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l8.setForeground(new java.awt.Color(0, 0, 0));
        l8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l8.setText("Passenger ID");
        Passengers.add(l8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 250, -1));

        c2.setBackground(new java.awt.Color(51, 51, 51));
        c2.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        c2.setForeground(new java.awt.Color(255, 255, 255));
        c2.setRenderer(new CenterAlignmentRenderer());
        c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c2ActionPerformed(evt);
            }
        });
        c2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                c2PropertyChange(evt);
            }
        });
        Passengers.add(c2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 100, 350, -1));

        t9.setBackground(new java.awt.Color(51, 51, 51));
        t9.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t9.setForeground(new java.awt.Color(255, 255, 255));
        t9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Passengers.add(t9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 200, 350, -1));

        l9.setBackground(new java.awt.Color(0, 0, 0));
        l9.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l9.setForeground(new java.awt.Color(0, 0, 0));
        l9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l9.setText("Booking ID");
        Passengers.add(l9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 250, -1));

        t10.setBackground(new java.awt.Color(51, 51, 51));
        t10.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t10.setForeground(new java.awt.Color(255, 255, 255));
        t10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Passengers.add(t10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 300, 350, -1));

        l10.setBackground(new java.awt.Color(0, 0, 0));
        l10.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l10.setForeground(new java.awt.Color(0, 0, 0));
        l10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l10.setText("Name");
        Passengers.add(l10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 250, -1));

        t11.setBackground(new java.awt.Color(51, 51, 51));
        t11.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t11.setForeground(new java.awt.Color(255, 255, 255));
        t11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Passengers.add(t11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 400, 350, -1));

        l11.setBackground(new java.awt.Color(0, 0, 0));
        l11.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l11.setForeground(new java.awt.Color(0, 0, 0));
        l11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l11.setText("Age");
        Passengers.add(l11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 250, -1));

        t12.setBackground(new java.awt.Color(51, 51, 51));
        t12.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t12.setForeground(new java.awt.Color(255, 255, 255));
        t12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Passengers.add(t12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 500, 350, -1));

        l12.setBackground(new java.awt.Color(0, 0, 0));
        l12.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l12.setForeground(new java.awt.Color(0, 0, 0));
        l12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l12.setText("Aadhar Number");
        Passengers.add(l12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 500, 250, -1));

        t13.setBackground(new java.awt.Color(51, 51, 51));
        t13.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        t13.setForeground(new java.awt.Color(255, 255, 255));
        t13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Passengers.add(t13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 600, 350, -1));

        l13.setBackground(new java.awt.Color(0, 0, 0));
        l13.setFont(new java.awt.Font("Verdana", 0, 28)); // NOI18N
        l13.setForeground(new java.awt.Color(0, 0, 0));
        l13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l13.setText("Seat Number");
        Passengers.add(l13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 600, 250, -1));

        main.add(Passengers, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 950, 1600, 750));

        b4.setBackground(new java.awt.Color(204, 204, 204));
        b4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        b4.setForeground(new java.awt.Color(51, 51, 51));
        b4.setText("Cancel Booking");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        main.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 1790, 300, 50));

        jScrollPane1.setViewportView(main);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(downPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1810, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1810, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1700, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(leftPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(downPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(leftPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pr1ActionPerformed
        UserEnd.profile pr = new UserEnd.profile(this.u_id,this);
        //this.setVisible(false);
        pr.setVisible(true);
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_pr1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_b2ActionPerformed

    private void c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1ActionPerformed
        
        b1.setEnabled(c1.isSelected());
        // TODO add your handling code here:
    }//GEN-LAST:event_c1ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
       
        this.setVisible(false);
        Payment ob = new Payment(b_id);
        ob.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    private void c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c2ActionPerformed
       
        
        // TODO add your handling code here:
    }//GEN-LAST:event_c2ActionPerformed

    private void c2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_c2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_c2PropertyChange

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        this.setVisible(false);
        SearchFlight ob = new SearchFlight(f_id, this.u_id);
        ob.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        try{
            if(JOptionPane.showConfirmDialog(rootPane, "Are You Sure..?", "Cancel Booking", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                deleteBooking();
                
                this.setVisible(false);
                new LastOne(false).setVisible(true);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
        // TODO add your handling code here:
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
        java.awt.EventQueue.invokeLater(() -> new Confirmation("1").setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Flight;
    private javax.swing.JPanel Passengers;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JCheckBox c1;
    private javax.swing.JComboBox<String> c2;
    private javax.swing.JPanel downPanel;
    private javax.swing.JLabel h1;
    private javax.swing.JLabel h2;
    private javax.swing.JPanel heading;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l10;
    private javax.swing.JLabel l11;
    private javax.swing.JLabel l12;
    private javax.swing.JLabel l13;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel leftPanel1;
    private javax.swing.JPanel main;
    private javax.swing.JButton pr1;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t10;
    private javax.swing.JTextField t11;
    private javax.swing.JTextField t12;
    private javax.swing.JTextField t13;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    private javax.swing.JTextField t5;
    private javax.swing.JTextField t6;
    private javax.swing.JTextField t7;
    private javax.swing.JTextField t9;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
