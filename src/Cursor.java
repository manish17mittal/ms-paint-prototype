/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
class CustomImageCursor extends JFrame
{
    public CustomImageCursor()
    {
        createAndShowGUI();
    }
    
    private void createAndShowGUI()
    {
        setTitle("Image Cursor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        add(new JButton("Button"));
        add(new JCheckBox("Checkbox"));
        
        try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pencil.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){}
        
        setSize(400,400);
        setVisible(true);
    }
    
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomImageCursor k = new CustomImageCursor();
            }
        });
    }
}

