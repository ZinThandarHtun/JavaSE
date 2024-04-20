package Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class updateProductForm extends JFrame implements ActionListener {
	/*global declaration carried code and id,labels,text fields
	and buttons  with related frame*/
	JLabel Code;
	JButton updateButton,cancelButton,goback;
	JTextField codeField;
	String productCode;
	int id;
	
	//global declaration connection,used database name,used user name,used password ,result set,and statement 
	Connection conn;
	String db="jdbc:mysql://localhost/project";
	String username="root";
	String password="root";
	Statement stm;
	ResultSet rs;
	//build constructor
	public updateProductForm()
	{
		//setting frame with title and boundary
		this.setTitle("Update Product Form");
		this.setBounds(300,200, 300, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//initialized  label for code
		Code =new JLabel("Code");
		
		//initialized text fields for code
		codeField=new JTextField(20);
		
		//initialized all buttons with names and background color
		updateButton=new JButton("Update");
		cancelButton=new JButton("Cancel");
		updateButton.setBackground(Color.PINK);
		cancelButton.setBackground(Color.PINK);
		goback=new JButton("Go Back To Home Page Form");
		goback.setBorder(new EmptyBorder(0,0,0,0));
		goback.setForeground(Color.BLUE);
		
		//to click , add action listener to the buttons
		updateButton.addActionListener(this);
		cancelButton.addActionListener(this);
		goback.addActionListener(this);
		
		//set flow layout to the frame
		this.setLayout(new FlowLayout());
		
		//set label,text field and buttons to the frame
		this.add(Code);
		this.add(codeField);
		this.add(updateButton);
		this.add(cancelButton);
		this.add(goback);
		//show frame
		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if update button is clicked
		if(e.getSource()==updateButton)
		{
			//if text field of the code does not have something show alert
			if(codeField.getText().equals(""))
				JOptionPane.showMessageDialog(this,"Enter you want to update a corrected code");
			//if text field of the code has data
			else{
			System.out.print("ok");//show alert console
			String code=codeField.getText();//catch code from text fields
			boolean b=false;
			try {
				//create database
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(db,username,password);
				stm=conn.createStatement();
				//write sql > select all data from stock code from stock table is equal to code from text field
				String sql="select * from stock where Code='"+code+"'";
				rs=stm.executeQuery(sql);//set sql to the result set
				//as long as there is data , select all date
				while(rs.next())
				{
					b=true;
					 productCode=rs.getString("Code").toString();//catch code from stock table
					new updateProduct(productCode);//go update product form
					dispose();//wanna leave login form
				}
					if(!b)//if unavailable code is inserted, show alert
				{
					JOptionPane.showMessageDialog(this,"Please enter you want to update a corrected code","Invalid code,",0);
				}
				//close statement and connection
				stm.close();
				conn.close();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}}//if cancel button is clicked 
		else if(e.getSource()==cancelButton)
		{
			//clear data in the code field
			codeField.setText("");
		}
	//if back to home page button is clicked
		else{
		dispose();//dispose page
		new homePageForm(id);//go home page form
	}

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new updateProductForm();

	}

}
