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

public class deleteProductForm extends JFrame implements ActionListener {
	/*global declaration delete code,carried id,labels,text fields,
	and buttons  with related frame*/
	JLabel Code;
	JButton deleteButton,cancelButton,goback;
	JTextField codeField;
	String deleteCode;
	 int id;
	 
	//global declaration connection,used database name,used user name,used password ,result set,and statement 
	Connection conn;
	String db="jdbc:mysql://localhost/project";
	String username="root";
	String password="root";
	Statement stm;
	ResultSet rs;
	
	//build constructor
	public deleteProductForm()
	{
		//setting frame with title and boundary
		this.setTitle("Delete Product Form");
		this.setBounds(300,200, 300, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//initialized labels of the frame with label names
		Code =new JLabel("Code");
		codeField=new JTextField(20);
		
		//initialized buttons of the frame with buttons names and background color
		deleteButton=new JButton("Delete");
		cancelButton=new JButton("Cancel");
		deleteButton.setBackground(Color.MAGENTA);
		cancelButton.setBackground(Color.MAGENTA);
		goback=new JButton("Go Back To Home Page Form");
		goback.setBorder(new EmptyBorder(0,0,0,0));
		goback.setForeground(Color.BLUE);
		
		//to click buttons,add action listener to the buttons
		deleteButton.addActionListener(this);
		cancelButton.addActionListener(this);
		goback.addActionListener(this);
	
		//set flow layout in the frame
		this.setLayout(new FlowLayout());
		//set labels,text field and  buttons to the panel
		this.add(Code);
		this.add(codeField);
		this.add(deleteButton);
		this.add(cancelButton);
		this.add(goback);
		//show frame
		this.setVisible(true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if delete button is clicked
		if(e.getSource()==deleteButton)
		{
			//if text field of the code does not have something show alert
			if(codeField.getText().equals(""))
				JOptionPane.showMessageDialog(this,"Enter you want to delete a corrected code");
			//if text field of the code has data
			else{
			System.out.print("ok");//output at the console
			String code=codeField.getText();//catch data from the text field
			boolean b=false;
			try {
				//create database
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(db,username,password);
				stm=conn.createStatement();
				//write sql ,select all data from stock table
				String sql="select * from stock where Code='"+code+"'";
				rs=stm.executeQuery(sql);//add sql to the statement
				while(rs.next())//as long as there is data
				{
					b=true;
					//select code and quantity from stock table
					deleteCode=rs.getString("Code");
					int quantity=Integer.parseInt(rs.getString("Quantity"));
					//if quantity is equal to zero
					if(quantity==0)
					{
						//call deleteProduct class with delete code
					new deleteProduct(deleteCode);
					}
					//if quantity is not equal to zero , do not delete data
					else {
						JOptionPane.showMessageDialog(this, "Product's quantity exist!!! Don't delete!!!");
					}
					//dispose();//wanna leave login form
				}
					if(!b)//if unavailable code is inserted, show alert
				{
					JOptionPane.showMessageDialog(this,"Please enter you want to delete a corrected code","Invalid code,",0);
				}
				//statement and connection are closed
				stm.close();
				conn.close();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}}
		//if cancel button is clicked
		else if(e.getSource()==cancelButton)
		{
			//clear code text field
			codeField.setText("");
		}
		//if home page  button is clicked
	else{
		dispose();//dispose page
		new homePageForm(id);//call home page form class with id
	}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new deleteProductForm();

	}

}
