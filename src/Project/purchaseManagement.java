package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class purchaseManagement extends JFrame implements ActionListener,
		KeyListener {
	/*global declaration panels ,formatted fields ,table ,default table model,
	text area,scroll pane,labels,text fields and buttons  with related frame*/
	JPanel salePanel1, salePanel, addButtonPanel,tablePanel;
	JLabel dateLabel,supplierNameLabel, codeLabel, descriptionLabel,categotyLabel, quantityLabel, priceLabel,
			amountLabel, totalLabel,discountLabel,netAmountLabel;
	JTextField codeField,supplierNameField, descriptionField,categoryField, qtyField, priceField, amountField,
			totalField,discountField,netAmountField;
	JButton addButton, saveButton,newButton,deleteButton;
	JFormattedTextField dateField;
	JTable table;
	DefaultTableModel model;
	JScrollPane tableJs, textAreaJs;
	JTextArea billTextArea;

	//global declaration connection,used database name,used user name,used password ,result set,and statement 
	Connection conn;
	String db = "jdbc:mysql://localhost/project";
	String username = "root";
	String password = "root";
	Statement stm;
	ResultSet rs, rs1, rs2;
	
	/*global declaration codeSelectSql, stockDataSelectSql, purchaseSql,stockUpdateSql,tempPurchaseSql,discountReceivedSql,stockCode,dbStockCode ;
	 purchaseCode,dbStockQty,deleteTempPurchase,dbCode, dbDescription, dbPrice,dbCategory;*/
	String codeSelectSql, stockDataSelectSql, purchaseSql,stockUpdateSql,tempPurchaseSql,discountReceivedSql,stockCode,dbStockCode ;
	String purchaseCode,dbStockQty,deleteTempPurchase;
	String dbCode, dbDescription, dbPrice,dbCategory;

	//create database
	public void createDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(db, username, password);
			stm = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//build constructor
	 public purchaseManagement() {
		// TODO Auto-generated constructor stub

		//setting frame with title and boundary
			this.setTitle("Purchase Management");
			this.setBounds(10, 10, 1000, 900);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			//initialized formatted field 
			dateField = new JFormattedTextField(
					DateFormat.getDateInstance(DateFormat.SHORT));
			dateField.setValue(new Date());//set current data
			dateField.setPreferredSize(new Dimension(130, 30));//set size formatted field

			//initialized all labels with label's name of the frame
			dateLabel = new JLabel("Date");
			supplierNameLabel=new JLabel("Supplier Name");
			categotyLabel = new JLabel("Category");
			codeLabel = new JLabel("Product Code");
			descriptionLabel = new JLabel("Description");
			quantityLabel = new JLabel("Quantity");
			priceLabel = new JLabel("Purchase Price");
			amountLabel = new JLabel("Total");
			totalLabel = new JLabel("Total amount");
			netAmountLabel=new JLabel("Net Amount");
			discountLabel=new JLabel("Discount Amount");
			
			//initialized all text fields of the frame
			categoryField = new JTextField(10);
			codeField = new JTextField(10);
			supplierNameField=new  JTextField(10);
			descriptionField = new JTextField(10);
			priceField = new JTextField(10);
			amountField = new JTextField(10);
			totalField = new JTextField(10);
			discountField=new JTextField(10);
			netAmountField=new JTextField(10);
			qtyField = new JTextField(10);

			//initialized buttons with button's name and color of the frame
			addButton = new JButton("Add");
			addButton.setBackground(Color.MAGENTA);
			saveButton = new JButton("Save");
			saveButton.setBackground(Color.MAGENTA);
			newButton = new JButton("New");
			newButton.setBackground(Color.MAGENTA);
			deleteButton=new JButton("Delete");
			deleteButton.setBackground(Color.MAGENTA);


			//to click,add action listener to the all buttons
			addButton.addActionListener(this);
			saveButton.addActionListener(this);
			newButton.addActionListener(this);
			deleteButton.addActionListener(this);
			//to enter, add key listener to the code and quantity text fields
			codeField.addKeyListener(this);
			qtyField.addKeyListener(this);

			//initialized addButtonPanel 
			addButtonPanel = new JPanel();
			addButtonPanel.add(addButton);//set add button to the addButtonPanel
			salePanel = new JPanel();//initialized sale panel
			salePanel.setLayout(new GridLayout(8, 2));//set grid layout to the sale panel
			//set labels ,text fields to the sale panel
			salePanel.add(dateLabel);
			salePanel.add(dateField);
			salePanel.add(supplierNameLabel);
			salePanel.add(supplierNameField);
			salePanel.add(codeLabel);
			salePanel.add(codeField);
			salePanel.add(descriptionLabel);
			salePanel.add(descriptionField);
			salePanel.add(categotyLabel);
			salePanel.add(categoryField);
			salePanel.add(quantityLabel);
			salePanel.add(qtyField);
			salePanel.add(priceLabel);
			salePanel.add(priceField);
			salePanel.add(amountLabel);
			salePanel.add(amountField);

			//initialized sale panel1 
			salePanel1 = new JPanel();
			//set title,background color and layout to the sale panel1
			salePanel1.setBorder(BorderFactory.createTitledBorder("Purchase"));
			salePanel1.setBackground(Color.BLUE);
			salePanel1.setLayout(new BorderLayout());
			//set salePanel and add button to the sale panel1
			salePanel1.add(salePanel, BorderLayout.CENTER);
			salePanel1.add(addButtonPanel, BorderLayout.SOUTH);

			//initialized table ,insert table's columns
			table = new JTable();
			model = new DefaultTableModel();
			model = (DefaultTableModel) table.getModel();
			model.addColumn("Code");
			model.addColumn("Description");
			model.addColumn("Category");
			model.addColumn("Quantity");
			model.addColumn("Purchase Price");
			model.addColumn("Amount");
		
			//initialized pay panel 
			JPanel payPanel = new JPanel();
			payPanel.setLayout(new GridLayout(3, 2));//set grid layout to the pay panel
			//set labels and text fields to the pay panel
			payPanel.add(totalLabel);
			payPanel.add(totalField);
			payPanel.add(discountLabel);
			payPanel.add(discountField);
			payPanel.add(netAmountLabel);
			payPanel.add(netAmountField);
	
			//initialized scroll bar for the table and set table to the scroll bar and then set this scroll bar to the panel
			tableJs = new JScrollPane(table);
			tablePanel=new JPanel();
			tablePanel.setLayout(new BorderLayout());
			tablePanel.add(tableJs,BorderLayout.CENTER);
			tablePanel.add(deleteButton,BorderLayout.PAGE_END);
			
			//set flow layout to the frame
			this.setLayout(new FlowLayout());
			//set sale panel1,pay panel ,table and buttons to the frame
			this.add(salePanel1);
			this.add(payPanel);
			this.add(saveButton);
			this.add(newButton);
			this.add(tablePanel);
			//show frame
			this.setVisible(true);

			

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		//if code is inserted , press enter key
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

		
				System.out.print("code ok");//show output at console
				String code = codeField.getText().toString();//catch code from code text field
				boolean b = false;
				try {
					createDB();
					//write sql,select all data from stock 
					codeSelectSql = "select * from stock where Code='" + code + "'";
					rs = stm.executeQuery(codeSelectSql);//to select,set codeSelectSql to the result set
					//as long as there is data
					while (rs.next()) {
						b = true;
						purchaseCode = rs.getString("Code").toString();//catch code from purchase table 
						callCode(code);//call callCode method
					}
					//if code was not filled,press enter key, show alert
					if (!b) {
						JOptionPane
								.showMessageDialog(
										this,
										"Please enter a corrected code,this code do not have!!!",
										"Invalid code,", 0);
					}
					//close statement and connection
					stm.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
	}
	/*
	 * selected all data from stock table where code is equal to in the purchase table 
	 *  and then auto show related text fields 
	 */
		public void callCode(String sCode) {
			
			purchaseCode = sCode;
			createDB();
			stockDataSelectSql = "select*from stock where Code=" + purchaseCode;
			try {
				rs1 = stm.executeQuery(stockDataSelectSql);
				while (rs1.next()) {
					dbCode = rs1.getString("Code").toString();
					dbDescription = rs1.getString("Description").toString();
					dbCategory=rs1.getString("Category").toString();
					dbPrice = rs1.getString("Purchase_Price").toString();
				}
				stm.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(sql1);
			codeField.setText(dbCode);
			descriptionField.setText(dbDescription);
			categoryField.setText(dbCategory);
			priceField.setText(dbPrice);

		
		}

	

	@Override
	/*catch quantity and price from related text fields and 
	then calculate total amount and then set amount text field this 
	calculated amount*/
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_F1) {
			int quantity = Integer.parseInt(qtyField.getText());
			int price = Integer.parseInt(priceField.getText());

			int total = quantity * price;

			amountField.setText(String.valueOf(total));
		}

	

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//if add button is clicked
		if(e.getSource()==addButton)
		{

			//if code was not filled , show message dialog
			if(codeField.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Enter a product code!!!");
			//if quantity was not filled , show message dialog
			else if(qtyField.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Enter quantity!!!");
			//if amount was not filled , show message dialog
			else if(amountField.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Enter quantity and calculate amount!!! ");

			//if all data was filled , show all data in the table
			else{
				model = new DefaultTableModel();
			model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] { codeField.getText(),
					descriptionField.getText(),categoryField.getText(), qtyField.getText(),
					priceField.getText(), amountField.getText(), });
			
			int sum = 0;//initialized 0 in sum
			//as long as there is data in table,add in sum
			for (int i = 0; i < table.getRowCount(); i++) {
				sum = sum + Integer.parseInt((String) table.getValueAt(i, 5));
			}
			totalField.setText(Integer.toString(sum));//set total field sum

			//clear all text fields
			codeField.setText("");
			descriptionField.setText("");
			categoryField.setText("");
			priceField.setText("");
			amountField.setText("");
			qtyField.setText("");
			
			}
		
		}
		//if save button is clicked
		else if(e.getSource()==saveButton)
		{/*catch discount amount from text field and  minus 
		in the amount and then show net amount this calculated amount  */
			int discount,total;
			if(discountField.getText().equals(""))
			{
				discount=0;
				netAmountField.setText(String.valueOf(Integer.parseInt(totalField.getText())));

			}
			
			else{
			total = Integer.parseInt(totalField.getText());
			 discount=Integer.parseInt(discountField.getText().toString());
			int netAmount=total-discount;
			netAmountField.setText(String.valueOf(netAmount));
		}

			//catch supplier name ,net amount ,date and discount from related text fields
			String supplierName=supplierNameField.getText();
			String netAmount=netAmountField.getText();
			String dis=discountField.getText();

			String date = dateField.getText().toString();
			//declare variables for data in the table
			String des ,category, quantity , purchasePrice, amount, code;
			//as long as there is data in the table,select all
			DefaultTableModel model = new DefaultTableModel();
			model = (DefaultTableModel) table.getModel();
			for (int i = 0; i < model.getRowCount(); i++) {
				code = (String) model.getValueAt(i, 0);
				des = (String) model.getValueAt(i, 1);
				category=(String)model.getValueAt(i, 2);
				quantity = (String) model.getValueAt(i, 3);
				purchasePrice = (String) model.getValueAt(i, 4);
				amount=(String)model.getValueAt(i, 5);
			
			//call createDB() for database join
			createDB();
			//insert data into purchase table
			purchaseSql= "insert into purchase values ('" + date +"','"+supplierName+ "','" + code + "','"
					+ des + "','" +category+"','"+ quantity + "','" + purchasePrice + "','" + amount+ "')";
			//insert data into tempPurchase table
			tempPurchaseSql="insert into tempPurchase values ('" + date +"','"+supplierName+ "','" + code + "','"
					+ des + "','" +category+"','"+ quantity + "','" + purchasePrice + "','" + amount+ "')";
			//update stock quantity after sale quantity 
			stockUpdateSql="update stock set Quantity=Quantity+(select sum(tempPurchase.Quantity) from tempPurchase where tempPurchase.Code=stock.Code) where exists (select 1 from tempPurchase where tempPurchase.Code=stock.Code)";
			//delete all data tempPurhcase table because only temporarily
			deleteTempPurchase="delete from tempPurchase";
			try {
				//update tables,set to the statement
				stm.executeUpdate(purchaseSql);
				stm.executeUpdate(tempPurchaseSql);
				stm.executeUpdate(stockUpdateSql);
				stm.executeUpdate(deleteTempPurchase);
				
				//close statement,connection
				stm.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			// all done ,show alert
			JOptionPane.showMessageDialog(this, "Purchase is successfully completed");

			//if discount was not payed, show no discount on the console
			if(discountField.getText().equals(""))
			{
				System.out.println("No Discount!");
				
			}
			//if discount was  payed, insert discount received table with date
			else
				{
				 discount=Integer.parseInt(discountField.getText().toString());

				createDB();
				discountReceivedSql="insert into discount_received values('"+date+"','"+supplierName+"','"+dis+"')";

				
				try {
					stm.executeUpdate(discountReceivedSql);
					stm.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}

		}
		//if new button is clicked,clear all text fields and table
		else if(e.getSource()==newButton)			
		{
			supplierNameField.setText("");
			discountField.setText("");
			netAmountField.setText("");
			totalField.setText("");
			table.setModel(new DefaultTableModel(null, new String[] { "Code",
					"Description","Category", "Quantity", "Purchase Price", "Amount" }));
			
		}
		//if delete button in the table is clicked, delete row you was selected row
		else if(e.getSource()==deleteButton)
		{
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			int row=table.getSelectedRow();
			
			while(row!=-1)
			{
				int modelRow=table.convertRowIndexToModel(row);
				model.removeRow(modelRow);
				row=table.getSelectedRow();
				
			}
		}



	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new purchaseManagement();
	}

}
