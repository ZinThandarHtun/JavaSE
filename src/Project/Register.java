package Project;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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

import org.omg.CORBA.FREE_MEM;

public class Register extends JFrame implements ActionListener {
	
	/*global declaration labels,text fields,password field,radio buttons,
	button group ,buttons and panels with related frame*/
	JLabel nameLabel,passwordLabel,confirmPasswordLabel,genderLabel,emailLabel,addressLabel,phoneNoLabel;
	JTextField nameField,emailField,addressField,phoneNoField;
	JPasswordField passwordField,confirmPasswordField;
	JRadioButton male,female;
	ButtonGroup genderBg;
	JButton saveButton,cancelButton;
	JPanel genderPanel,framePanel;
	
	//Global declaration connection,used database name,used user name,used password and statement 
	Connection conn;
	String db="jdbc:mysql://localhost/project";
	String username="root";
	String password ="root";
	String sql;
	Statement stm;
	
	
	//build constructor
public Register()
{
	//setting frame with title and boundary
	this.setTitle("New Register ");
	this.setBounds(200,300,600,500);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//initialized labels of the frame with label names
	nameLabel=new JLabel("Name");
	passwordLabel=new JLabel("Password");
	genderLabel=new JLabel("Gender");
	emailLabel=new JLabel("Email");
	addressLabel=new JLabel("Address");
	phoneNoLabel=new JLabel("PhoneNo");
	confirmPasswordLabel=new JLabel("Confirm Password");
	//initialized text fields of the frame
	nameField=new JTextField();
	emailField=new JTextField();
	addressField=new JTextField();
	phoneNoField=new JTextField();
	passwordField=new JPasswordField(20);
	passwordField.setEchoChar('*');
	confirmPasswordField=new JPasswordField(20);
	confirmPasswordField.setEchoChar('*');
	
	//initialized panel,insert radio buttons into panel
	genderPanel=new JPanel();
	genderPanel.setBorder(BorderFactory.createEtchedBorder());
	male=new JRadioButton("Male");
	female=new JRadioButton("Female");
	genderBg=new ButtonGroup();
	genderBg.add(male);
	genderBg.add(female);
	genderPanel.add(male);
	genderPanel.add(female);
	
	//initialized buttons 
	saveButton=new JButton("Save");
	cancelButton=new JButton("Cancel");
	saveButton.setBackground(Color.magenta);
	cancelButton.setBackground(Color.magenta);
	saveButton.addActionListener(this);
	cancelButton.addActionListener(this);
	
	//initialized panel,insert labels , text fields ,radio buttons and buttons into panel
	framePanel=new JPanel();
	framePanel.setLayout(new GridLayout(8,2));
	framePanel.setBorder(BorderFactory.createEtchedBorder());
	framePanel.add(nameLabel);
	framePanel.add(nameField);
	framePanel.add(passwordLabel);
	framePanel.add(passwordField);
	framePanel.add(confirmPasswordLabel);
	framePanel.add(confirmPasswordField);
	framePanel.add(genderLabel);
	framePanel.add(genderPanel);
	framePanel.add(emailLabel);
	framePanel.add(emailField);
	framePanel.add(addressLabel);
	framePanel.add(addressField);
	framePanel.add(phoneNoLabel);
	framePanel.add(phoneNoField);
	framePanel.add(saveButton);
	framePanel.add(cancelButton);
	
	//set flow layout to the frame
	this.setLayout(new FlowLayout());
	//set frame panel to the frame
	this.add(framePanel);
	this.setVisible(true);//show frame
	
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if save button is clicked
		if(e.getSource()==saveButton)
			
		{//if name was not filled , show message dialog
			if(nameField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Enter your name", "Name",JOptionPane.QUESTION_MESSAGE);
			}
			//if password was not filled , show message dialog
			else if(passwordField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Enter your password", "Password",JOptionPane.QUESTION_MESSAGE);
			}
			else if(confirmPasswordField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Enter your confirm password", "Password",JOptionPane.QUESTION_MESSAGE);
			
					}
			//if gender was not choice , show message dialog
			else if(genderBg.getSelection()==null)
			{
				JOptionPane.showMessageDialog(this, "Choose gender", "Name",JOptionPane.QUESTION_MESSAGE);
			}
			//if email was not filled , show message dialog
			else if(emailField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Enter your email ", "Email",JOptionPane.QUESTION_MESSAGE);
			}
			//if address was not filled , show message dialog
			else if(addressField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Enter your address", "Address",JOptionPane.QUESTION_MESSAGE);
			}
			//if phone was not filled , show message dialog
			else if(phoneNoField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Enter your phone number", "Phone No:",JOptionPane.QUESTION_MESSAGE);
			}
			//catch data into text fields
			else{
			String name=nameField.getText();
			String pass=passwordField.getText();
			String gender=" ";
			String email=emailField.getText();
			String address=addressField.getText();
			String phoneno=phoneNoField.getText();
			if(male.isSelected())
				gender +="Male";
			else 
				gender +="Female";
			
			try {
				//create database
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(db,username,password);
				stm=conn.createStatement();
			
				//write insert query 
				sql="insert into user values(null"+",'"+name+"','"+pass+"','"+gender+"','"+email+"','"+address+"','"+phoneno+"')";
				stm.executeUpdate(sql);//update customer table in database
				
				//close statement,connection
				stm.close();
				conn.close();
				dispose();
				new logintest();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			}
			
		}else//If cancel button is clicked 
		{
			//all text fields is clear and radio buttons are unchecked
			nameField.setText("");
			passwordField.setText("");
			confirmPasswordField.setText("");
			emailField.setText("");
			addressField.setText("");
			phoneNoField.setText("");
			genderBg.clearSelection();	
			
		}
		
	}

	//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Register();

	}

}
