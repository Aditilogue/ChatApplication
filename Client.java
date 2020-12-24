import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class Client implements ActionListener{
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JPanel a1;
    static JFrame f1 = new JFrame();
    
    static Box vertical = Box.createVerticalBox();
    
    
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    Boolean typing;
    
    Client(){
	f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        f1.getContentPane().add(p1);
      
       JLabel l1 = new JLabel();
       l1.setBounds(5, 17, 30, 30);
       p1.add(l1);
       
       l1.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });
      
       JLabel l2 = new JLabel();
       l2.setBounds(40, 5, 60, 60);
       p1.add(l2);
       
       JLabel l5 = new JLabel();
       l5.setBounds(290, 20, 30, 30);
       p1.add(l5);
       
       JLabel l7 = new JLabel();
       l7.setBounds(410, 20, 13, 25);
       p1.add(l7);
       
       
        
       JLabel l3 = new JLabel("Daddy");
       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
       l3.setForeground(Color.WHITE);
       l3.setBounds(110, 15, 100, 18);
       p1.add(l3);   
       
       
       JLabel l4 = new JLabel("Active Now");
       l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
       l4.setForeground(Color.WHITE);
       l4.setBounds(110, 35, 100, 20);
       p1.add(l4);   
       
       JLabel label = new JLabel(":");
       label.setFont(new Font("Lucida Grande", Font.BOLD, 25));
       label.setForeground(Color.WHITE);
       label.setBounds(384, 17, 30, 30);
       p1.add(label);
       
       JLabel label_1 = new JLabel(".");
       label_1.setFont(new Font("Lucida Grande", Font.BOLD, 24));
       label_1.setForeground(Color.WHITE);
       label_1.setBounds(384, 32, 48, 25);
       p1.add(label_1);
       
       JPanel panel = new JPanel();
       panel.setBounds(25, 7, 62, 58);
       p1.add(panel);
       
        Timer t = new Timer(1, new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               if(!typing){
                   l4.setText("Active Now");
               }
           }
       });
       
       t.setInitialDelay(2000);

       a1 = new JPanel();
       a1.setBounds(5, 75, 440, 568);
       a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       f1.getContentPane().add(a1);
       
       
       t1 = new JTextField();
       t1.setBounds(45, 653, 328, 40);
       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       f1.getContentPane().add(t1);
       
       t1.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent ke){
               l4.setText("typing...");
               
               t.stop();
               
               typing = true;
           }
           
           public void keyReleased(KeyEvent ke){
               typing = false;
               
               if(!t.isRunning()){
                   t.start();
               }
           }
       });
       
       b1 = new JButton("-▸");
       b1.setBounds(375, 653, 75, 40);
       b1.setBackground(new Color(7, 94, 84));
       b1.setForeground(new Color(47, 79, 79));
       b1.setFont(new Font("Dialog", Font.BOLD, 23));
       b1.addActionListener(this);
       f1.getContentPane().add(b1);
        
       f1.getContentPane().setBackground(Color.WHITE);
       f1.getContentPane().setLayout(null);
       
       JLabel label_2 = new JLabel(". .");
       label_2.setFont(new Font("Lucida Grande", Font.BOLD, 25));
       label_2.setBounds(15, 642, 48, 31);
       f1.getContentPane().add(label_2);
       
       JLabel lblO = new JLabel(" O");
       lblO.setFont(new Font("Lucida Grande", Font.BOLD, 13));
       lblO.setBounds(15, 665, 32, 22);
       f1.getContentPane().add(lblO);
       
       JLabel label_3 = new JLabel("_ _");
       label_3.setFont(new Font("Lucida Grande", Font.BOLD, 15));
       label_3.setBounds(15, 642, 61, 16);
       f1.getContentPane().add(label_3);
       f1.setSize(450, 700);
       f1.setLocation(800, 100); 
       f1.setUndecorated(true);
       f1.setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        try{
            String out = t1.getText();
            
            JPanel p2 = formatLabel(out);
            
            a1.setLayout(new BorderLayout());
            
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            
            a1.add(vertical, BorderLayout.PAGE_START);
            
            //a1.add(p2);
            dout.writeUTF(out);
            t1.setText("");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static JPanel formatLabel(String out){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        
        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15,15,15,50));
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        
        p3.add(l1);
        p3.add(l2);
        return p3;
    }
    
    public static void main(String[] args){
        new Client().f1.setVisible(true);
        
        try{
            
            s = new Socket("127.0.0.1", 6001);
            din  = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            String msginput = "";
            
	    while(true){
                a1.setLayout(new BorderLayout());
	        msginput = din.readUTF();
            	JPanel p2 = formatLabel(msginput);
                JPanel left = new JPanel(new BorderLayout());
                left.add(p2, BorderLayout.LINE_START);
                
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);
                f1.validate();
            }
            
        }catch(Exception e){}
    }    
}