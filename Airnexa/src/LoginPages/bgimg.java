/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginPages;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author User
 */
public class bgimg extends JPanel {
     private final Image bgImage;

    public bgimg() {
        // background image load
        bgImage = new ImageIcon(getClass().getResource("/Asset/bg.jpg")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // pura panel e image ta resize kore draw hobe
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
}
}