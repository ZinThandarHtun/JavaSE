package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class logintest extends JFrame implements ActionListener {
	/*
	 * global declaration panels,labels,text fields and buttons with related
	 * frame
	 */
	JLabel titleLabel, userName, Password;
	JTextField t1;
	JPasswordField pf;
	JButton loginButton;
	JPanel p1, p2, p3;

	//global declaration connection,used database name,used user name,used password ,result set,and statement 
	Connection conn;
	String db = "jdbc:mysql://localhost/project";
	String username = "root";
	String password = "root";
	Statement stm;
	ResultSet rs;

	public logintest() {
		//setting frame with title and boundary
		this.setTitle("Login");
		this.setBounds(200, 200, 300, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//initialized  image icons for the labels
		ImageIcon passwordIcon = new ImageIcon(
				"C:\\Users\\GICM\\Desktop\\project image\\password.png");
		ImageIcon usernameIcon = new ImageIcon(
				"C:\\Users\\GICM\\Desktop\\project image\\username.png");
		//initialized title labels
		titleLabel = new JLabel("User Login");
		titleLabel.setFont(new Font("Calibri", Font.ITALIC, 30));//set font style and size 
		//initialized labels with image icons of the frame
		userName = new JLabel(usernameIcon);
		Password = new JLabel(passwordIcon);
		//initialized text field and password field of the frame
		t1 = new JTextField(12);
		pf = new JPasswordField(12);
		pf.setEchoChar('*');//set echo character (*)

		//initialized button with background color
		loginButton = new JButton("Login");
		loginButton.setBackground(Color.MAGENTA);
		
		//to click.add action listener to the login button
		loginButton.addActionListener(this);
		
		//initialized panel
		p1 = new JPanel();
		//set grid layout to the panel
		p1.setLayout(new GridLayout(2, 2));
		//set labels and text fields to the panel
		p1.add(userName);
		p1.add(t1);
		p1.add(Password);
		p1.add(pf);

		//set flow layout to the frame
		this.setLayout(new FlowLayout());
		//set labels,panel and button to the frame
		this.add(titleLabel);
		this.add(p1);
		this.add(loginButton);
		//show frame
		this.setVisible(true);

	}

	//main method
	public static void main(String args[]) {
		new logintest();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if login button is clicked
		if (e.getSource() == loginButton) {

			//catch data from text fields
			String name = t1.getText();
			String pass = pf.getText();
			boolean b = false;
			try {
				//create database
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(db, username, password);
				stm = conn.createStatement();
				//write sql, selected all data from user table 
				String sql = "select * from user where name='" + name
						+ "' and password='" + pass + "'";
				rs = stm.executeQuery(sql);//set sql to the result set
				//as long as there is data
				while (rs.next()) {
					b = true;
					int id = rs.getInt("id");//select id from user table
					
					dispose();//dispose page
					new homePageForm(id);//call home page form class
					
				}
				//if user name and password is incorrect,show alert 
				if (!b) {
					JOptionPane.showMessageDialog(this, "Invalid login,",
							"Login fail", 0);
				}
				//statement and connection are closed
				stm.close();
				conn.close();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
