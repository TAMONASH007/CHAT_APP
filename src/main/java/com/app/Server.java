package main.java.com.app;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class Server implements ActionListener  {
	
	private static final int INTERFACE_WIDTH = 450;
	private static final int INTERFACE_HEIGHT = 700;
	private static final int WIDTH = 200;
	private static final int HEIGHT = 50;
	private static final int port = 6002;
	
	JTextField textField;
	JPanel gap;
	static Box verticalBox = Box.createVerticalBox();
	static DataOutputStream dOutputStream;
	
	static JFrame jf = new JFrame();
	
	public Server() {
		
		jf.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(8, 63, 158));
		panel1.setBounds(0, 0, INTERFACE_WIDTH, 70);
		panel1.setLayout(null);
		jf.add(panel1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("main/icons/3.png"));
		Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel back = new JLabel(i3);
		back.setBounds(5, 20, 20, 20);
		
		panel1.add(back);
		
		
		back.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent ev) {
				System.exit(0);
			}
		});
		
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("main/icons/dp1.jpg"));
		Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel dp1 = new JLabel(i6);
		dp1.setBounds(40, 10, 50, 50);
		panel1.add(dp1);
		
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("main/icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel video = new JLabel(i9);
		video.setBounds(300, 20, 20, 20);
		panel1.add(video);
		
		
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("main/icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel call = new JLabel(i12);
		call.setBounds(360, 20, 20, 20);
		panel1.add(call);
		
		
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("main/icons/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(10, 20, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel dots = new JLabel(i15);
		dots.setBounds(420, 20, 10, 20);
		panel1.add(dots);
		
		
		JLabel name1 = new JLabel("Kaleen Bhaiya");
		name1.setBounds(110, 15, 100, 18);
		name1.setForeground(Color.WHITE);
		name1.setFont(new Font("SAN_SERIF", Font.BOLD, 12));
		panel1.add(name1);
		
		
		JLabel status = new JLabel("online");
		status.setBounds(110, 35, 100, 18);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("SAN_SERIF", Font.BOLD, 9));
		panel1.add(status);
		
		
		gap = new JPanel();
		gap.setBounds(5, 75, 440, 570);
		jf.add(gap);
		
		textField = new JTextField();
		textField.setBounds(5, 655, 310, 40);
		textField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		jf.add(textField);
		
		
		JButton button = new JButton("Enter");
		button.setBounds(320, 655, 123, 40);
		button.setBackground(new Color(8, 63, 158));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		button.addActionListener(this);
		jf.add(button);
		
		jf.setSize(INTERFACE_WIDTH, INTERFACE_HEIGHT);
		jf.setLocation(WIDTH, HEIGHT);
		jf.setUndecorated(true);
		jf.getContentPane().setBackground(Color.WHITE);
		
		
		
		jf.setVisible(true);
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			String out = textField.getText();
			
			JPanel panel2 = formatLabel(out);
			
			gap.setLayout(new BorderLayout());
			
			JPanel rightPanel = new JPanel(new BorderLayout());
			rightPanel.add(panel2, BorderLayout.LINE_END);
			verticalBox.add(rightPanel);
			verticalBox.add(Box.createVerticalStrut(1));
			
			gap.add(verticalBox, BorderLayout.PAGE_START);
			
			dOutputStream.writeUTF(out);
			
			
			textField.setText("");
			jf.repaint();
			jf.invalidate();
			jf.validate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static JPanel formatLabel(String out) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel output = new JLabel("<html><p style = \"width: 120px\">" + out + "</p></html>");
		output.setFont(new Font("Tahoma", Font.PLAIN, 16));
		output.setBackground(new Color(158, 255, 169));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm");
		
		
		JLabel time = new JLabel();
		time.setText(sFormat.format(calendar.getTime()));
		panel.add(time);
		
		panel.add(output);
		
		return panel;
		
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		Server server= new Server();
		
		try {
			
			ServerSocket socket = new ServerSocket(port);
			while(true) {
				Socket socket2 = socket.accept();
				
				DataInputStream dInputStream = new DataInputStream(socket2.getInputStream());
				dOutputStream = new DataOutputStream(socket2.getOutputStream());
				
				while(true) {
					String message = dInputStream.readUTF();
					JPanel panel = formatLabel(message);
					
					
					JPanel leftJPanel = new JPanel(new BorderLayout());
					leftJPanel.add(panel, BorderLayout.LINE_START);
					
					verticalBox.add(leftJPanel);
					jf.validate();
					
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	

}
