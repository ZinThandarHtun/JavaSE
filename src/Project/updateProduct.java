package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class updateProduct extends JFrame implements ActionListener {
	/*global declaration carried code,text area,scroll pane,labels,text fields,combo boxes
	panels and buttons  with related frame*/
	String updateCode;
	JLabel code,name,group,purchasePrice,salePrice;
	JButton update,cancel,homePageForm;
	JTextField codeField,nameField,purchaseField,saleField;
	JPanel gridPanel,buttonPanel,comboPanel;
	JComboBox groupCombo;
	
	//global declaration connection,used database name,used user name,used password ,result set,and statement 
	Connection conn;
	String db="jdbc:mysql://localhost/project";
	String username="root";
	String password1="root";
	String sql;
	Statement stm;
	ResultSet rs;
	
	//global declaration String dbCode,dbDescription,dbCategory,dbPurchase,dbSale for selected date from database
	
	String dbCode;
	String dbDescription;
	String dbCategory;
	String dbPurchase;
	String dbSale;
	
	
	//create database
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
	//build constructor
	 public updateProduct(String productcode) {
		updateCode=productcode;
		//setting frame with title and boundary
		 this.setTitle("Update Product's Data");
			this.setBounds(300,200, 400, 400);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			//initialized labels of the frame with label names
			code=new JLabel("Product Code");
			name=new JLabel("Description");
			group=new JLabel("Product Category");
			purchasePrice=new JLabel("Purchase Price");
			salePrice=new JLabel("Sale Price");
			
			//initialized text fields of the frame
			codeField=new JTextField();
			nameField=new JTextField();
			purchaseField=new JTextField();
			saleField=new JTextField();
			
			
			//initialized combo box of the frame with category names
			groupCombo=new JComboBox();
			groupCombo.addItem("---Selected Category---");
			groupCombo.addItem("Lipstick");
			groupCombo.addItem("Foundation");
			groupCombo.addItem("Mascara");
			groupCombo.addItem("Lotion");
			groupCombo.addItem("Essence");
			groupCombo.addItem("Toner");
			groupCombo.addItem("Cream Puff");
			groupCombo.setSelectedIndex(0);
			
			//initialized buttons of the frame with button names
			update=new JButton("Save");
			cancel=new JButton("Cancel");
			homePageForm=new JButton("lets go back>> ");
			
			/*initialized delete form panel 
			 * set labels ,text fields and buttons to the panel
			*/
			gridPanel=new JPanel();
			gridPanel.setLayout(new GridLayout(6,2));
			gridPanel.setBorder(BorderFactory.createEtchedBorder());
			gridPanel.add(code);
			gridPanel.add(codeField);
			gridPanel.add(name);
			gridPanel.add(nameField);
			gridPanel.add(group);
			gridPanel.add(groupCombo);
			gridPanel.add(purchasePrice);
			gridPanel.add(purchaseField);
			gridPanel.add(salePrice);
			gridPanel.add(saleField);
			gridPanel.add(update);
			gridPanel.add(cancel);
			
			//to click buttons,add action listener to the buttons
			update.addActionListener(this);
			cancel.addActionListener(this);
			homePageForm.addActionListener(this);
			
			//create background color and empty border for the home page button 
			homePageForm.setBorder(new EmptyBorder(0,0,0,0));
			homePageForm.setForeground(Color.BLUE);
			
			//create background color for the update and cancel buttons
			update.setBackground(Color.PINK);
			cancel.setBackground(Color.PINK);
			
			//set flow layout in the frame
			this.setLayout(new FlowLayout());
			
			//set grid panel and home page button to the frame
			this.add(gridPanel,BorderLayout.NORTH);
			this.add(homePageForm,BorderLayout.CENTER);
			this.setVisible(true);//show frame
			
			createDB();//create database
			sql="select*from stock where Code="+updateCode;//Write sql->select all data from stock tables
			try {
				rs=stm.executeQuery(sql);//set sql to the result set
				//as long as there is data , select all data from stock table 
				while(rs.next())
				{				
					/*select code,description,category,purchase price and 
					purchase price from stock tables*/
					dbCode=rs.getString("Code");
					dbDescription=rs.getString("Description");
					dbCategory=rs.getString("Category");
					dbPurchase=rs.getString("Purchase_Price");
					dbSale=rs.getString("Sale_Price");
				}
				//close statement and connection
				stm.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//insert data from the stock table to the text fields
			codeField.setText(dbCode);
			nameField.setText(dbDescription);
			purchaseField.setText(dbPurchase);
			saleField.setText(dbSale);
			
			//test condition categories from table and frame
			if(dbCategory.equals("Lipstick"))
				groupCombo.setSelectedIndex(1);
			else if(dbCategory.equals("Foundation"))
				groupCombo.setSelectedIndex(2);
			else if(dbCategory.equals("Mascara"))
				groupCombo.setSelectedIndex(3);
			else if(dbCategory.equals("Lotion"))
				groupCombo.setSelectedIndex(4);
			else if(dbCategory.equals("Essence"))
				groupCombo.setSelectedIndex(5);
			else if(dbCategory.equals("Toner"))
				groupCombo.setSelectedIndex(6);
			else if(dbCategory.equals("Cream Puff"))
				groupCombo.setSelectedIndex(7);
			
			
			
	}

	@Override
	public void actionPerformed(ActionEvent e ) {
		//if delete button is clicked
		if(e.getSource()==update)
		{
			//catch data from the text fields
			String code=codeField.getText();
			String description=nameField.getText();
			String category=groupCombo.getSelectedItem().toString();
			String purchase=purchaseField.getText();
			String sale=saleField.getText();
			//create database
			createDB();
			//write update sql
			sql="update stock set Code='"+code+"',Description='"+
			description+"',Category='"+category+"',Purchase_Price='"+
					purchase+"',Sale_Price='"+sale+"' where Code="+updateCode;
			try {
				stm.executeUpdate(sql);//set sql to the result
				//if delete is ok,show alert
				JOptionPane.showMessageDialog(update, "Code number "+code+"  is successfully updated");
				//close connection and statement
				stm.close();
				conn.close();
				dispose();//dispose page
				new updateProductForm();//go update product form
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		//if cancel button is clicked, clear all text fields
		else if(e.getSource()==cancel){
			codeField.setText("");
			nameField.setText("");
			groupCombo.setSelectedIndex(0);
			purchaseField.setText("");
			saleField.setText("");
			
		}
	//if home page button button is clicked
		else
		{
			new updateProductForm();// go update product form
		}
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
