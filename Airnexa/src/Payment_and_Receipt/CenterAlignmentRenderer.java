/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Payment_and_Receipt;

/**
 *
 * @author User
 */
import java.awt.Component;
import javax.swing.*;

public class CenterAlignmentRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // The default renderer returns a JLabel component
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        // Set the horizontal alignment to center
        label.setHorizontalAlignment(SwingConstants.CENTER);
        
        return label;
    }
}