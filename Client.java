import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

public class Client  implements ActionListener{
	JPanel p1;//creates division
	JTextField t1;
	JButton b1;
	static JPanel ta;
	static JFrame f1 = new JFrame();
	
	static Box vertical = Box.createVerticalBox(); //msgs align vertically
	
	static Socket s;
	static DataInputStream di;
	static DataOutputStream dos;
	boolean typing;
	
	Client() {
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));//green color 
		p1.setBounds(0,0,450,60);//will create user defined layout 
		f1.add(p1);//will add layout on frame
		
		
		ImageIcon i1 = new ImageIcon(""); // add path where you have stored image
		Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);//getting image in label becoz image can't place directly on frame
		l1.setBounds(5,17,30,30);
		p1.add(l1);//image will on panel 
		//to back
		l1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.exit(0);
			}
		});
		
		ImageIcon i4 = new ImageIcon("");// add path where you have stored image
		Image i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2 = new JLabel(i6);
		l2.setBounds(40,5,60,60);
		p1.add(l2);
		
		ImageIcon i7 = new ImageIcon("");// add path where you have stored image
		Image i8 = i7.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel l5 = new JLabel(i9);
		l5.setBounds(290,20,30,30);
		p1.add(l5);
		
		ImageIcon i10 = new ImageIcon("");// add path where you have stored image
		Image i11 = i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel l6 = new JLabel(i12);
		l6.setBounds(350,20,35,30);
		p1.add(l6);
		
		ImageIcon i13 = new ImageIcon("");// add path where you have stored image
		Image i14 = i13.getImage().getScaledInstance(12,20,Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel l7 = new JLabel(i15);
		l7.setBounds(410,20,13,25);
		p1.add(l7);
		
		JLabel l3 = new JLabel("Nobita");//when we need to add content then use JLabel
		l3.setFont(new Font("SAN_SERIF",Font.BOLD,15));
		l3.setForeground(Color.WHITE);
		l3.setBounds(110,15,100,20);
		p1.add(l3);
		
		JLabel l4 = new JLabel("Active Now");
		l4.setFont(new Font("SAN_SERIF",Font.BOLD,10));
		l4.setForeground(Color.WHITE);
		l4.setBounds(110,30,100,20);
		p1.add(l4);
		
		Timer t = new Timer(1,new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(!typing) {
					l4.setText("Active Now");
					
				}
			}
		});
		t.setInitialDelay(2000);
		
		ta = new JPanel();
		ta.setFont(new Font("SAN_SERIF",Font.PLAIN,17));
		
		JScrollPane sp = new JScrollPane(ta);
		sp.setBounds(5, 65, 440, 470);
		sp.setBorder(BorderFactory.createEmptyBorder());
		f1.add(sp);
		
		ScrollBarUI ui = new BasicScrollBarUI();//abstract class child class
		
		
		t1 = new JTextField();
		t1.setBounds(6, 550, 335, 40);
		t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		f1.add(t1);
		
		t1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k1) {
				l4.setText("typing...");
				t.stop();
				typing = true;
			}
			
			public void keyReleased(KeyEvent ke) {
				typing = false;
				if(!t.isRunning()) {
					t.start();
				}
			}
		});
		
		b1 = new JButton("Send");
		b1.setBounds(345, 550, 89, 39);
		b1.setBackground(new Color(7,94,84));
		b1.setFont(new Font("SAN_SERIF",Font.BOLD,16));
		b1.setForeground(Color.white );
		b1.addActionListener(this);
		f1.add(b1);
		f1.setLayout(null);
		f1.setSize(450,600); //set frame size
		f1.setLocation(900,200);
		f1.setUndecorated(true);
		f1.setVisible(true);
	}
	
public void actionPerformed(ActionEvent me) {
		
		try {
			String out = t1.getText();
			JPanel p2 = formatLabel(out);
			ta.setLayout(new BorderLayout());
			JPanel right = new JPanel(new BorderLayout());
			right.add(p2,BorderLayout.LINE_END);
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(15));
			
			ta.add(vertical,BorderLayout.PAGE_START);
			
			dos.writeUTF(out);
			t1.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	public static JPanel formatLabel(String out) {
		 JPanel p3 = new JPanel();
		 p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));//timing will be print exactly below the msg border
		 
		 JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+ out +"</html>");
		 l1.setFont(new Font("Tohoma",Font.PLAIN,16));
		 l1.setBackground(new Color(37,211,102));
		 l1.setOpaque(true); //background color change
		 l1.setBorder(new EmptyBorder(15,15,15,50));
		 		 
		 Calendar cal = Calendar.getInstance();
		 SimpleDateFormat sdt = new SimpleDateFormat("hh:mm");
		 
		 JLabel l2 = new JLabel();
		 l2.setText(sdt.format(cal.getTime()));
		 
		 p3.add(l1);
		 p3.add(l2);
		 return p3;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client().f1.setVisible(true);
		
		try {
			s = new Socket("127.0.0.1",6001);
			di = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			
			String msginput = "";
			while(true) {
				msginput = di.readUTF();
				JPanel p2 = formatLabel(msginput);
				
				JPanel left = new JPanel(new BorderLayout());
				left.add(p2,BorderLayout.LINE_START);
				vertical.add(left);
				f1.validate();//to refresh setVisible			
			}				
		}catch(Exception e) {			
		}			
	}	
}
