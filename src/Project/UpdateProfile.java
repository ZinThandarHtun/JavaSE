package Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class UpdateProfile extends JFrame implements ActionListener {
	/*global declaration radio buttons,button group,panels,labels,text fields,password field
	and buttons  with related frame*/
	JLabel nameLabel,passwordLabel,genderLabel,emailLabel,addressLabel,phoneNoLabel;
	JTextField nameField,emailField,addressField,phoneNoField;
	JRadioButton male,female;
	ButtonGroup genderBg;
	JButton save,cancel;
	JPanel genderPanel,framePanel;
	JPasswordField passwordField;
	
	int cusid;
	String name;
	String custpassword;
	String gender;
	String email;
	String address;
	String phoneno;
	
	//global declaration connection,used database name,used user name,used password ,result set,and statement 
	Connection conn;
	String db="jdbc:mysql://localhost/project";
	String username="root";
	String password1="root";
	String sql;
	Statement stm;
	ResultSet rs;
	
	public void createDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(db,username,password1);
			stm=conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public UpdateProfile(int id)
	{
	    cusid=id;
	  //setting frame with title and boundary
		this.setTitle("Update user information ");
		this.setBounds(200,300,500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createDB( );
		sql="select*from user where id="+cusid;
		
		try {
			rs=stm.executeQuery(sql);
			while(rs.next())
			{
				name=rs.getString("name");
				custpassword=rs.getString("password");
				gender=rs.getString("gender");
				email=rs.getString("email");
				address=rs.getString("address");
				phoneno=rs.getString("phoneno");
				
						
			}
			stm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
		
		//Initialized labels of the frame 
		nameLabel=new JLabel("Name");
		passwordLabel=new JLabel("Password");
		genderLabel=new JLabel("Gender");
		emailLabel=new JLabel("Email");
		addressLabel=new JLabel("Address");
		phoneNoLabel=new JLabel("PhoneNo");
		
		//Initialized text fields of the frame
		nameField=new JTextField();
		nameField.setText(name);
		emailField=new JTextField();
		emailField.setText(email);
		addressField=new JTextField();
		addressField.setText(address);
		phoneNoField=new JTextField();
		phoneNoField.setText(phoneno);
		
		passwordField=new JPasswordField(10);
		passwordField.setEchoChar('*');
		passwordField.setText(custpassword);
		
		genderPanel=new JPanel();
		genderPanel.setBorder(BorderFactory.createEtchedBorder());
		male=new JRadioButton("Male");
		female=new JRadioButton("Female");
		genderBg=new ButtonGroup();
		genderBg.add(male);
		genderBg.add(female);
		genderPanel.add(male);
		genderPanel.add(female);
		if(gender.equals("Male"))
			male.setSelected(true);
		else
			female.setSelected(true);
		save=new JButton("Save");
		cancel=new JButton("Cancel");
		save.setBackground(Color.PINK);
		cancel.setBackground(Color.PINK);
		save.addActionListener(this);
		cancel.addActionListener(this);
		
		//Initialized panel,insert labels , text fields ,radio buttons and buttons into panel
		framePanel=new JPanel();
		framePanel.setLayout(new GridLayout(7,2));
		framePanel.setBorder(BorderFactory.createEtchedBorder());
		framePanel.add(nameLabel);
		framePanel.add(nameField);
		framePanel.add(passwordLabel);
		framePanel.add(passwordField);
		framePanel.add(genderLabel);
		framePanel.add(genderPanel);
		framePanel.add(emailLabel);
		framePanel.add(emailField);
		framePanel.add(addressLabel);
		framePanel.add(addressField);
		framePanel.add(phoneNoLabel);
		framePanel.add(phoneNoField);
		framePanel.add(save);
		framePanel.add(cancel);
		this.setLayout(new FlowLayout());
		this.add(framePanel);
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if save button is clicked
		if(e.getSource()==save)
		{
			//catch all data from all text fields
			String name=nameField.getText();
			String pass=passwordField.getText();
			String gender=male.isSelected()? "Male" :"Female";
			String email=emailField.getText();
			String address=addressField.getText();
			String  phoneno=phoneNoField.getText();
			
			createDB();//call create database
			//write sql >update data from user table 
			sql="update user set name='"+name+"',password='"+pass+"',gender='"+gender+ "',email='"+email
					+"',address='"+address+"',phoneno='"+phoneno+"' where id="+cusid+"";
			

			try {
				stm.executeUpdate(sql);//set sql to the statement
				//after all done,show alert
				JOptionPane.showMessageDialog(save, "Welcome "+name+" Your account is successfully updated");
				//close statement and connection
				stm.close();
				conn.close();
				dispose();//dispose page
				new logintest();//go logintest
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
