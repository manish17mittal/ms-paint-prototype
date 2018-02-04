
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import rotateimage.color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Manish
 */
public class Paint extends javax.swing.JFrame {

    /**
     * Creates new form Paint
     */
    public Paint() {
        initComponents();
    }

  private int xStart;
private int yStart;
private int xEnd,x_min,x_max;
private int yEnd,y_min,y_max;
private int height;
private int width;
private double dside;
private double rad;
private Line2D lineBuffer;
private Rectangle2D srectangleBuffer;
private Rectangle2D rectangleBuffer;
private Ellipse2D ovalBuffer;
private Graphics2D g2;
private Graphics g_bucket;

private List<Line2D>lineContainer =new ArrayList();
private List<Rectangle2D>srectangleContainer =new ArrayList();
private List<Rectangle2D>rectangleContainer =new ArrayList();
private List<Rectangle2D>squareContainer =new ArrayList();
private List<Ellipse2D>circleContainer =new ArrayList();
private List<Ellipse2D>ovalContainer =new ArrayList();
  
    
    
Color initialcolor=Color.BLACK; 
Color color,color_obj,color_par; 
int want_to_color=0;
int currx,curry,oldx,oldy;
int activate_pencil=0,activate_rubber=0,activate_brush=0;
Graphics2D g1,g_rubber,g_brush;
int brush_x,brush_y;
String file_undo=null;
BufferedImage screen_img,restore_img;
    
 
void clear_screen()
{
     jPanel1.update(g2);
     circleContainer.clear();
     lineContainer.clear();
     ovalContainer.clear();
     squareContainer.clear();
     rectangleContainer.clear();
     srectangleContainer.clear();
}


void screen_image()
{
    try {
                
                Toolkit tk = Toolkit.getDefaultToolkit(); //Toolkit class returns the default toolkit
                Dimension d = tk.getScreenSize();
                Point pt = new Point(jPanel1.getLocation()); 
                SwingUtilities.convertPointToScreen(pt, jPanel1); 

//Dimension class object stores width & height of the toolkit screen
// toolkit.getScreenSize() determines the size of the screen

                Rectangle rec;  
//Creates a Rectangle with screen dimensions, here we are capturing the entire screen,if you want you can change it accordingly (i.e you can also capture a particular area on the screen)          
                rec = new Rectangle((int) pt.getX(), (int) pt.getY(), jPanel1.getWidth(), jPanel1.getHeight());
                          
                Robot ro = new Robot(); //a very important class to capture the screen image
                screen_img = ro.createScreenCapture(rec);
    }
    catch(Exception e)
    {
        
    }
}


private void DrawAll()
    { 
        g2.setColor(color);
        for(int i=0;i<lineContainer.size();i++)
        { 
            g2.draw(lineContainer.get(i));
        }
        
        
        for(int i=0;i<rectangleContainer.size();i++)
        { 
            g2.draw(rectangleContainer.get(i));
        }
        
        for(int i=0;i<ovalContainer.size();i++)
        { 
            g2.draw(ovalContainer.get(i));
        }
    }


void color_it(int x,int y)
{
    
    if( check_color(x,y)==1)
    {

        g_bucket.drawLine(x,y,x,y);
    } 
    else
    {
        return;
    }
    color_it(x+1,y);
    color_it(x,y+1);
    color_it(x-1,y);
    color_it(x,y-1);
    
    
    
}
/*
void color_it(int x,int y)
{
    int [][] mat_scr=new int [650][450];
    int i,j;
    for(i=0;i<650;++i)
        for(j=0;j<450;++j)
        {
            mat_scr[i][j]=0;
           
        }
    
    for(i=x;i<650;++i)
    {
        for(j=y;j<450;++j)
        {
           if(check_color(i,j)!=1)
           {
               break;
           }
           mat_scr[i][j]=1;
            // g_bucket.drawLine(i,j,i,j);
        }
    }
     for(i=0;i<650;++i)
        for(j=0;j<450;++j)
        {
            if(mat_scr[i][j]==1)
            {
                g_bucket.drawLine(i, j, i,j);
            }
           
        }
    
}
*/
 
int check_color(int x, int y)
{

    Robot robot = null;
    try {
        robot = new Robot();
    } catch (AWTException ex) {
        Logger.getLogger(rotateimage.color.class.getName()).log(Level.SEVERE, null, ex);
    }

            //
            // The the pixel color information at 20, 20
            //
            color_obj = robot.getPixelColor(x, y); 
        
            if(color_obj.equals(color_par))
            {
              
                return 1;
            }
            else
            {
                return 0;
            }
}
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        N_ew = new javax.swing.JButton();
        S_ave = new javax.swing.JButton();
        O_pen = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        B_ackup = new javax.swing.JButton();
        R_estore1 = new javax.swing.JButton();
        C_oordinates = new javax.swing.JLabel();
        circle = new javax.swing.JRadioButton();
        oval = new javax.swing.JRadioButton();
        square = new javax.swing.JRadioButton();
        rectangle = new javax.swing.JRadioButton();
        P_encil = new javax.swing.JButton();
        R_ubber = new javax.swing.JButton();
        B_rush = new javax.swing.JButton();
        B_ucket = new javax.swing.JButton();
        line = new javax.swing.JRadioButton();
        R_estore3 = new javax.swing.JButton();
        R_estore2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        C_oordinates_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        N_ew.setText("New");
        N_ew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                N_ewMouseClicked(evt);
            }
        });

        S_ave.setText("Save As");
        S_ave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                S_aveMouseClicked(evt);
            }
        });

        O_pen.setText("Open");
        O_pen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                O_penMouseClicked(evt);
            }
        });

        jButton6.setText("Color ->");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        B_ackup.setText("Backup");
        B_ackup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_ackupMouseClicked(evt);
            }
        });

        R_estore1.setText("Restore 1");
        R_estore1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                R_estore1MouseClicked(evt);
            }
        });
        R_estore1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R_estore1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(circle);
        circle.setText("Circle");
        circle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                circleMouseClicked(evt);
            }
        });
        circle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                circleActionPerformed(evt);
            }
        });

        buttonGroup1.add(oval);
        oval.setText("Oval");
        oval.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ovalMouseClicked(evt);
            }
        });
        oval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ovalActionPerformed(evt);
            }
        });

        buttonGroup1.add(square);
        square.setText("Square");
        square.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                squareMouseClicked(evt);
            }
        });
        square.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                squareActionPerformed(evt);
            }
        });

        buttonGroup1.add(rectangle);
        rectangle.setText("Rectangle");
        rectangle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rectangleMouseClicked(evt);
            }
        });
        rectangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rectangleActionPerformed(evt);
            }
        });

        P_encil.setText("Pencil");
        P_encil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                P_encilMouseClicked(evt);
            }
        });

        R_ubber.setText("Rubber");
        R_ubber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                R_ubberMouseClicked(evt);
            }
        });

        B_rush.setText("Brush");
        B_rush.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_rushMouseClicked(evt);
            }
        });

        B_ucket.setText("Fill Color");
        B_ucket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_ucketMouseClicked(evt);
            }
        });

        buttonGroup1.add(line);
        line.setText("Line");
        line.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lineMouseClicked(evt);
            }
        });

        R_estore3.setText("Restore 3");
        R_estore3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                R_estore3MouseClicked(evt);
            }
        });

        R_estore2.setText("Restore 2");
        R_estore2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                R_estore2MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        C_oordinates_label.setText("Coordinates");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(P_encil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R_ubber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_rush)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_ucket)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(line)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rectangle)
                            .addComponent(square))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(circle)
                            .addComponent(oval))
                        .addGap(49, 49, 49)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(O_pen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(N_ew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(S_ave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(R_estore2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(B_ackup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(R_estore1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(R_estore3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(C_oordinates_label)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(C_oordinates)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(N_ew, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(O_pen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(S_ave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(B_ackup, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R_estore1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R_estore2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(R_estore3)
                        .addGap(26, 26, 26)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B_ucket)
                            .addComponent(P_encil)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(R_ubber)
                                .addComponent(B_rush)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(C_oordinates_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(C_oordinates))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(oval)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(circle))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rectangle)
                                    .addComponent(line))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(square)))
                        .addGap(0, 10, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void circleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_circleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_circleActionPerformed

    private void ovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ovalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ovalActionPerformed

    private void squareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_squareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_squareActionPerformed

    private void rectangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rectangleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rectangleActionPerformed

    private void O_penMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_O_penMouseClicked
 JFileChooser fc=new JFileChooser();  
    int i=fc.showOpenDialog(this);  
              
    if(i==JFileChooser.APPROVE_OPTION){  
    File f=fc.getSelectedFile();  
    String filepath=f.getPath();  
    
        
        
        
    BufferedImage img = null;
try {
    img = ImageIO.read(new File(filepath));
} catch (IOException e) {
}
        Graphics2D g=(Graphics2D) getGraphics();
Point pt = new Point(jPanel1.getLocation());
SwingUtilities.convertPointToScreen(pt, jPanel1); 
int y=(int)jPanel1.getHeight();
int x=(int)jPanel1.getWidth();
g.drawImage(img, (int)pt.getX(),(int) pt.getY(), x, y, this);


screen_img=img;// TODO add your handling code here:
    }        // TODO add your handling code here:
    }//GEN-LAST:event_O_penMouseClicked

    private void S_aveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S_aveMouseClicked
 try {
                
                Toolkit tk = Toolkit.getDefaultToolkit(); //Toolkit class returns the default toolkit
                Dimension d = tk.getScreenSize();
                Point pt = new Point(jPanel1.getLocation()); 
SwingUtilities.convertPointToScreen(pt, jPanel1); 

//Dimension class object stores width & height of the toolkit screen
// toolkit.getScreenSize() determines the size of the screen

                Rectangle rec;  
//Creates a Rectangle with screen dimensions, here we are capturing the entire screen,if you want you can change it accordingly (i.e you can also capture a particular area on the screen)          
       rec = new Rectangle((int) pt.getX(), (int) pt.getY(), jPanel1.getWidth(), jPanel1.getHeight());
                          
                Robot ro = new Robot(); //a very important class to capture the screen image
                BufferedImage img = ro.createScreenCapture(rec);

//createScreenCapture() method takes Rectangle class instance as argument and returns BufferedImage
File f = null ;
    JFrame parentFrame = new JFrame();
 
JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Specify a file to save");   
 
int userSelection = fileChooser.showSaveDialog(parentFrame);
 
if (userSelection == JFileChooser.APPROVE_OPTION) {
   f = fileChooser.getSelectedFile();
}
  
                
             try{
                  ImageIO.write(img, "jpg", f);
             }
             catch(Exception e)
             {
                 
             }
                //f = new File("myimage.jpg"); // File class is used to write the above generated buffered image to a file
               
//ImageIO is an API which is used to convert BufferedImage to a png/jpg image     
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }        // TODO add your handling code here:
    }//GEN-LAST:event_S_aveMouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
if(g2==null)
    {
    g2=(Graphics2D)jPanel1.getGraphics();
    }
    xStart=evt.getX();
    yStart=evt.getY();
    xEnd=evt.getX();
    yEnd=evt.getY();
    oldx=evt.getX()+9;
    oldy=evt.getY()+38;// TODO add your handling code here:
    
    
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        
       if(true)
       {
           String str;
        str = (currx-9)+" "+ (curry-38);
        C_oordinates.setText(str);
       }
        
        
        
        if(line.isSelected())
        {
          jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          lineBuffer= new Line2D.Float((float)xStart, (float)yStart, (float)xEnd,(float)yEnd);
          DrawAll();
          g2.drawImage(screen_img, null, 0, 0);
          g2.draw(lineBuffer);
        }
        
        
        if(square.isSelected())
        {
          jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          height=yEnd-yStart;
          width=xEnd-xStart;
          
          height=abs(height);
          width=abs(width);
          
          x_min=xStart;
          y_min=yStart;
          if(xEnd<xStart)
          {
              x_min=xEnd;
          }
          if(yEnd<yStart)
          {
              y_min=yEnd;
          }
          int max_dimension=height;
          if(width>height)
          {
          max_dimension=width;
          }
          rectangleBuffer= new Rectangle2D.Float((float)x_min, (float)y_min, (float)max_dimension,(float)max_dimension);
          DrawAll();
          g2.drawImage(screen_img, null, 0, 0);
          g2.draw(rectangleBuffer);
        }
        
        if(rectangle.isSelected())
        {
          jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          height=yEnd-yStart;
          width=xEnd-xStart;
          height=abs(height);
          width=abs(width);
          
          x_min=xStart;
          y_min=yStart;
          if(xEnd<xStart)
          {
              x_min=xEnd;
          }
          if(yEnd<yStart)
          {
              y_min=yEnd;
          }
          rectangleBuffer= new Rectangle2D.Float(x_min, y_min, (float)width,(float)height);
          DrawAll();
          g2.drawImage(screen_img, null, 0, 0);
          g2.draw(rectangleBuffer);
        }
        
        if(oval.isSelected())
        {
          jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          height=yEnd-yStart;
          width=xEnd-xStart;
          height=abs(height);
          width=abs(width);
          
          x_min=xStart;
          y_min=yStart;
          if(xEnd<xStart)
          {
              x_min=xEnd;
          }
          if(yEnd<yStart)
          {
              y_min=yEnd;
          }
          
          ovalBuffer= new Ellipse2D.Float((float)x_min, (float)y_min, (float)width, (float)height);
          DrawAll();
          g2.drawImage(screen_img, null, 0, 0);
          g2.draw(ovalBuffer);
        }
        
        if(circle.isSelected())
        {
           jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          height=yEnd-yStart;
          width=xEnd-xStart;
          height=abs(height);
          width=abs(width);
          
          x_min=xStart;
          y_min=yStart;
          if(xEnd<xStart)
          {
              x_min=xEnd;
          }
          if(yEnd<yStart)
          {
              y_min=yEnd;
          }
          int max_diameter=height;
          if(width>height)
          {
              max_diameter=width;
          }
          ovalBuffer= new Ellipse2D.Float((float)x_min, (float)y_min, (float)max_diameter, (float)max_diameter);
          DrawAll();
          g2.drawImage(screen_img, null, 0, 0);
          g2.draw(ovalBuffer);
        } 
        

        if(activate_pencil==1)
        {
        currx=evt.getX()+9;
        curry=evt.getY()+38; 
        
        g1.setColor(color);
        if((currx-9)<jPanel1.getWidth()&&(curry-30)<jPanel1.getHeight() && (currx-9)>0&&(curry-30)>0)
        {
        g1.drawLine(oldx+1,oldy-3,currx+1,curry-3);  
        oldx=currx;
        oldy=curry;
        }
        }
        
        
        if(activate_rubber==1) 
        {
            currx=evt.getX()+9;
            curry=evt.getY()+38;
           
            g_rubber.setColor(Color.white);
            if((currx+6)<jPanel1.getWidth()&&(curry-17)<jPanel1.getHeight() && (currx-9)>0&&(curry-30)>0)
            { 
                g_rubber.fillRect(currx, curry, 15, 15);
            }
        }
        
        if(activate_brush==1) 
        {
            currx=evt.getX()+9;
            curry=evt.getY()+38;
           
            g_brush.setColor(color);
            if((currx-2)<jPanel1.getWidth()&&(curry-26)<jPanel1.getHeight() && (currx-9)>0&&(curry-30)>0)
            { 
                g_brush.fillOval(currx, curry, 7, 7);
            }
        }
        
        //TODO add your handling code here:
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
  /*
        if(true)
        {
          //delete file 1
            File f1;
           
                f1=new File("temp\\1.jpg");
            f1.delete();
            
            
            
          //rename file 2 to file 1
            File f2;
           
                f1 = new File("temp\\1.jpg");
            f2 = new File("temp\\2.jpg");
            f2.renameTo(f1);
            
            
            
           //rename file 3 to file 2
             File f3;
             
                 f2 = new File("temp\\2.jpg");
            f3 = new File("temp\\3.jpg");
            f3.renameTo(f2);
            
            
            
            
            //create file 3
            try {
                
                Toolkit tk = Toolkit.getDefaultToolkit(); //Toolkit class returns the default toolkit
                Dimension d = tk.getScreenSize();
                Point pt = new Point(jPanel1.getLocation()); 
                SwingUtilities.convertPointToScreen(pt, jPanel1); 

//Dimension class object stores width & height of the toolkit screen
// toolkit.getScreenSize() determines the size of the screen

                Rectangle rec;  
//Creates a Rectangle with screen dimensions, here we are capturing the entire screen,if you want you can change it accordingly (i.e you can also capture a particular area on the screen)          
                rec = new Rectangle((int) pt.getX(), (int) pt.getY(), jPanel1.getWidth(), jPanel1.getHeight());
                          
                Robot ro = new Robot(); //a very important class to capture the screen image
                BufferedImage img = ro.createScreenCapture(rec);

//createScreenCapture() method takes Rectangle class instance as argument and returns BufferedImage
         
                f3 = new File("temp\\3.jpg");
                
             try{
                  ImageIO.write(img, "jpg", f3);
             }
             catch(Exception e)
             {
                 
             }
                //f = new File("myimage.jpg"); // File class is used to write the above generated buffered image to a file
               
//ImageIO is an API which is used to convert BufferedImage to a png/jpg image     
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }        // TODO add your handling code here:
 
        }
        
        
       if(true)
        {
            BufferedImage img = null;
                try {
                 img = ImageIO.read(new File("temp\\3.jpg"));
                    } catch (IOException e) {
                                            }
                
              Graphics2D g=(Graphics2D) getGraphics();
                Point pt = new Point(jPanel1.getLocation());
                SwingUtilities.convertPointToScreen(pt, jPanel1); 
                int y=(int)jPanel1.getHeight();
                int x=(int)jPanel1.getWidth();
                g.drawImage(img, (int)pt.getX(),(int) pt.getY(), x, y, this);  
        } 
        
        */
  
        screen_image();
        if(line.isSelected())
        {
          jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          lineBuffer= new Line2D.Float((float)xStart, (float)yStart, (float)xEnd,(float)yEnd);
          lineContainer.add(lineBuffer);
          clear_screen();
          g2.drawImage(screen_img, null, 0, 0);
          DrawAll();
         
        }
       
         
        if(square.isSelected())
        {
          jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          height=yEnd-yStart;
          width=xEnd-xStart;
          
          height=abs(height);
          width=abs(width);
          
          x_min=xStart;
          y_min=yStart;
          if(xEnd<xStart)
          {
              x_min=xEnd;
          }
          if(yEnd<yStart)
          {
              y_min=yEnd;
          }
          int max_dimension=height;
          if(width>height)
          {
              max_dimension=width;
          }
          rectangleBuffer= new Rectangle2D.Float(x_min, y_min, max_dimension,max_dimension);
          rectangleContainer.add(rectangleBuffer);
          clear_screen();
          g2.drawImage(screen_img, null, 0, 0);
          
          DrawAll();
          
        }
        
        if(rectangle.isSelected())
        { jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          height=yEnd-yStart;
          width=xEnd-xStart;
          height=abs(height);
          width=abs(width);
          
          x_min=xStart;
          y_min=yStart;
          if(xEnd<xStart)
          {
              x_min=xEnd;
          }
          if(yEnd<yStart)
          {
              y_min=yEnd;
          }
          rectangleBuffer= new Rectangle2D.Float(x_min, y_min, (float)width,(float)height);
          rectangleContainer.add(rectangleBuffer);
          clear_screen();
          g2.drawImage(screen_img, null, 0, 0);
          DrawAll();
        }
        
        if(oval.isSelected())
        {
          jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          height=yEnd-yStart;
          width=xEnd-xStart;
          height=abs(height);
          width=abs(width);
          
          x_min=xStart;
          y_min=yStart;
          if(xEnd<xStart)
          {
              x_min=xEnd;
          }
          if(yEnd<yStart)
          {
              y_min=yEnd;
          }
          
          ovalBuffer= new Ellipse2D.Float((float)x_min, (float)y_min, (float)width, (float)height);
          ovalContainer.add(ovalBuffer);
          clear_screen();
          g2.drawImage(screen_img, null, 0, 0);
          DrawAll();
        }
             
        if(circle.isSelected())
        {
          jPanel1.update(g2);
          xEnd=evt.getX();
          yEnd=evt.getY();
          height=yEnd-yStart;
          width=xEnd-xStart;
          height=abs(height);
          width=abs(width);
          
          x_min=xStart;
          y_min=yStart;
          if(xEnd<xStart)
          {
              x_min=xEnd;
          }
          if(yEnd<yStart)
          {
              y_min=yEnd;
          }
          int max_diameter=height;
          if(width>height)
          {
              max_diameter=width;
          }
          ovalBuffer= new Ellipse2D.Float((float)x_min, (float)y_min, (float)max_diameter, (float)max_diameter);
          ovalContainer.add(ovalBuffer);
          clear_screen();
          g2.drawImage(screen_img, null, 0, 0);
          DrawAll();
        }      // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseReleased

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
color=JColorChooser.showDialog(this,"Select a color",initialcolor); 
initialcolor=color;
jPanel2.setBackground(color);// TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseClicked

    private void B_ucketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_ucketMouseClicked

        buttonGroup1.clearSelection();
        want_to_color=1;  
        try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/bucket.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){}// TODO add your handling code here:
    }//GEN-LAST:event_B_ucketMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
if(activate_pencil==1)
        {
        currx=evt.getX()+9;
        curry=evt.getY()+38; 
        
        g1.setColor(color);
        if((currx-9)<jPanel1.getWidth()&&(curry-30)<jPanel1.getHeight() && (currx-9)>0&&(curry-30)>0)
        {
        g1.drawLine(oldx+1,oldy-3,currx+1,curry-3);  
        oldx=currx;
        oldy=curry;
        }
        }
        
        
        if(activate_rubber==1) 
        {
            currx=evt.getX()+9;
            curry=evt.getY()+38;
           
            g_rubber.setColor(Color.white);
            if((currx+6)<jPanel1.getWidth()&&(curry-17)<jPanel1.getHeight() && (currx-9)>0&&(curry-30)>0)
            { 
                g_rubber.fillRect(currx, curry, 15, 15);
            }
        }
        
        if(activate_brush==1) 
        {
            currx=evt.getX()+9;
            curry=evt.getY()+38;
           
            g_brush.setColor(color);
            if((currx-2)<jPanel1.getWidth()&&(curry-26)<jPanel1.getHeight() && (currx-9)>0&&(curry-30)>0)
            { 
                g_brush.fillOval(currx, curry, 7, 7);
            }
        }

if(want_to_color==1)
{
    
    int initial_x,initial_y;
    initial_x=evt.getX()+35;
    initial_y=evt.getY()+63;
    g_bucket.setColor(color);
    
    Robot robot = null;
    try {
        robot = new Robot();
    } catch (AWTException ex) {
        Logger.getLogger(rotateimage.color.class.getName()).log(Level.SEVERE, null, ex);
    }

            //
            // The the pixel color information at 20, 20
            //
            color_par = robot.getPixelColor(initial_x, initial_y); 
    color_it(initial_x,initial_y);
    want_to_color=0;
}        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
g_bucket=(Graphics2D) getGraphics();
g1=(Graphics2D) getGraphics();
g_rubber=(Graphics2D) getGraphics();
g_brush=(Graphics2D) getGraphics();// TODO add your handling code here:
    }//GEN-LAST:event_jPanel1AncestorAdded

    private void P_encilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P_encilMouseClicked
activate_pencil=1; 
buttonGroup1.clearSelection();
activate_rubber=0;
activate_brush=0;
try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/pencil.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){}// TODO add your handling code here:
    }//GEN-LAST:event_P_encilMouseClicked

    private void R_ubberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R_ubberMouseClicked
activate_rubber=1;
activate_pencil=activate_brush=0;
buttonGroup1.clearSelection();
try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/rubber.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){}// TODO add your handling code here:
    }//GEN-LAST:event_R_ubberMouseClicked

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
       // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
if(true)
       {
           String str;
           currx=evt.getX()+9;
           curry=evt.getY()+38;
        str = (currx-9)+" "+ (curry-38);
        C_oordinates.setText(str);
       }        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseMoved

    private void B_rushMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_rushMouseClicked
activate_pencil=0; 
buttonGroup1.clearSelection();
activate_rubber=0;
activate_brush=1;
try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/brush.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){}// TODO add your handling code here:
    }//GEN-LAST:event_B_rushMouseClicked

    private void N_ewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_N_ewMouseClicked
        clear_screen(); 
        screen_img=null;
        restore_img=null;
        g_rubber.setColor(Color.white);
        Point pt = new Point(jPanel1.getLocation()); 
SwingUtilities.convertPointToScreen(pt, jPanel1);
        g_rubber.fillRect((int)pt.getX(), (int)pt.getY(), jPanel1.getWidth(), jPanel1.getHeight());
        
        // TODO add your handling code here:
    }//GEN-LAST:event_N_ewMouseClicked

    private void lineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lineMouseClicked
try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/line.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){} 

activate_pencil=0;
activate_rubber=0;
activate_brush=0;// TODO add your handling code here:
    }//GEN-LAST:event_lineMouseClicked

    private void rectangleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rectangleMouseClicked
try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/line.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){} 

activate_pencil=0;
activate_rubber=0;
activate_brush=0;// TODO add your handling code here:
    }//GEN-LAST:event_rectangleMouseClicked

    private void ovalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ovalMouseClicked
try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/line.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){} 

activate_pencil=0;
activate_rubber=0;
activate_brush=0;// TODO add your handling code here:
    }//GEN-LAST:event_ovalMouseClicked

    private void squareMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_squareMouseClicked
try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/line.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){}   

activate_pencil=0;
activate_rubber=0;
activate_brush=0;// TODO add your handling code here:
    }//GEN-LAST:event_squareMouseClicked

    private void circleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_circleMouseClicked
try
        {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pics/line.png").getImage(),new Point(0,0),"custom cursor"));
        }catch(IndexOutOfBoundsException | HeadlessException e){}   

activate_pencil=0;
activate_rubber=0;
activate_brush=0;// TODO add your handling code here:
    }//GEN-LAST:event_circleMouseClicked

    private void B_ackupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_ackupMouseClicked

    if(true)
        {
          //delete file 1
            File f1;
           
                f1=new File("temp/1.jpg");
            f1.delete();
            
            
            
          //rename file 2 to file 1
            File f2;
           
                f1 = new File("temp/1.jpg");
            f2 = new File("temp/2.jpg");
            f2.renameTo(f1);
            
            
            
           //rename file 3 to file 2
             File f3;
             
                 f2 = new File("temp/2.jpg");
            f3 = new File("temp/3.jpg");
            f3.renameTo(f2);
            
            
            
            
            //create file 3
            try {
                
                Toolkit tk = Toolkit.getDefaultToolkit(); //Toolkit class returns the default toolkit
                Dimension d = tk.getScreenSize();
                Point pt = new Point(jPanel1.getLocation()); 
                SwingUtilities.convertPointToScreen(pt, jPanel1); 

//Dimension class object stores width & height of the toolkit screen
// toolkit.getScreenSize() determines the size of the screen

                Rectangle rec;  
//Creates a Rectangle with screen dimensions, here we are capturing the entire screen,if you want you can change it accordingly (i.e you can also capture a particular area on the screen)          
                rec = new Rectangle((int) pt.getX(), (int) pt.getY(), jPanel1.getWidth(), jPanel1.getHeight());
                          
                Robot ro = new Robot(); //a very important class to capture the screen image
                BufferedImage img = ro.createScreenCapture(rec);

//createScreenCapture() method takes Rectangle class instance as argument and returns BufferedImage
         
                f3 = new File("temp/3.jpg");
                
             try{
                  ImageIO.write(img, "jpg", f3);
             }
             catch(Exception e)
             {
                 
             }
                //f = new File("myimage.jpg"); // File class is used to write the above generated buffered image to a file
               
//ImageIO is an API which is used to convert BufferedImage to a png/jpg image     
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }        // TODO add your handling code here:
 
        }
                // TODO add your handling code here:
    }//GEN-LAST:event_B_ackupMouseClicked

    private void R_estore1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R_estore1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_R_estore1ActionPerformed

    private void R_estore1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R_estore1MouseClicked
    if(true)
        {
           
                try {
                 restore_img = ImageIO.read(new File("temp/3.jpg"));
                    } catch (IOException e) {
                                            }
                
              Graphics2D g=(Graphics2D) getGraphics();
                Point pt = new Point(jPanel1.getLocation());
                SwingUtilities.convertPointToScreen(pt, jPanel1); 
                int y=(int)jPanel1.getHeight();
                int x=(int)jPanel1.getWidth();
                g.drawImage(restore_img, (int)pt.getX(),(int) pt.getY(), x, y, this);
                
                screen_img=restore_img;//to put this new image as the screen's bufferedimage
        } 
    
    
               // TODO add your handling code here:
    }//GEN-LAST:event_R_estore1MouseClicked

    private void R_estore2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R_estore2MouseClicked
    if(true)
        {
            
                try {
                 restore_img = ImageIO.read(new File("temp/2.jpg"));
                    } catch (IOException e) {
                                            }
                
              Graphics2D g=(Graphics2D) getGraphics();
                Point pt = new Point(jPanel1.getLocation());
                SwingUtilities.convertPointToScreen(pt, jPanel1); 
                int y=(int)jPanel1.getHeight();
                int x=(int)jPanel1.getWidth();
                g.drawImage(restore_img, (int)pt.getX(),(int) pt.getY(), x, y, this);
                
                screen_img=restore_img;//to put this new image as the screen's bufferedimage
        } 
               // TODO add your handling code here:
    }//GEN-LAST:event_R_estore2MouseClicked

    private void R_estore3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R_estore3MouseClicked
    if(true)
        {
           
                try {
                 restore_img = ImageIO.read(new File("temp/1.jpg"));
                    } catch (IOException e) {
                                            }
                
              Graphics2D g=(Graphics2D) getGraphics();
                Point pt = new Point(jPanel1.getLocation());
                SwingUtilities.convertPointToScreen(pt, jPanel1); 
                int y=(int)jPanel1.getHeight();
                int x=(int)jPanel1.getWidth();
                g.drawImage(restore_img, (int)pt.getX(),(int) pt.getY(), x, y, this); 
                
                screen_img=restore_img;//to put this new image as the screen's bufferedimage
        } 
               // TODO add your handling code here:
    }//GEN-LAST:event_R_estore3MouseClicked

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
            java.util.logging.Logger.getLogger(Paint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Paint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Paint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Paint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Paint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_ackup;
    private javax.swing.JButton B_rush;
    private javax.swing.JButton B_ucket;
    private javax.swing.JLabel C_oordinates;
    private javax.swing.JLabel C_oordinates_label;
    private javax.swing.JButton N_ew;
    private javax.swing.JButton O_pen;
    private javax.swing.JButton P_encil;
    private javax.swing.JButton R_estore1;
    private javax.swing.JButton R_estore2;
    private javax.swing.JButton R_estore3;
    private javax.swing.JButton R_ubber;
    private javax.swing.JButton S_ave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton circle;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton line;
    private javax.swing.JRadioButton oval;
    private javax.swing.JRadioButton rectangle;
    private javax.swing.JRadioButton square;
    // End of variables declaration//GEN-END:variables
}
