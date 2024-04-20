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
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class saleManagement extends JFrame implements ActionListener,
		KeyListener {
	/*global declaration formatted text fields,table,default table model,
	  text area,scroll pane,labels,text fields,panels and buttons  with related frame*/
	JPanel salePanel1, salePanel, addButtonPanel,tablePanel;
	JLabel dateLabel, codeLabel, descriptionLabel,categotyLabel, quantityLabel, priceLabel,
			amountLabel, totalLabel,discountLabel,netAmountLabel, payLabel, balanceLabel;
	JTextField codeField, descriptionField,categoryField, qtyField, priceField, amountField,
			totalField,discountField,netAmountField, payField, balanceField;
	JButton addButton, printBillButton,netAmountButton, saveButton,newButton,deleteButton;
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
	String sql, sql1, saleSql,stockUpdateSql,discountAllowedSql;
	String saleCode;

	//declaration dbCode, dbDescription, dbPrice,dbCategory,tempSaleSql,deleteTempSale for selected data
	String dbCode, dbDescription, dbPrice,dbCategory,tempSaleSql,deleteTempSale;

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
	public saleManagement() {
		// TODO Auto-generated constructor stub
		//setting frame with title and boundary
		this.setTitle("Sale Management");
		this.setBounds(10, 10, 1000, 900);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//initialized FormattedTextField 
		dateField = new JFormattedTextField(
				DateFormat.getDateInstance(DateFormat.SHORT));
		dateField.setValue(new Date());//set new current date
		dateField.setPreferredSize(new Dimension(130, 30));//set size FormattedTextField

		//initialized all  labels with labels
		dateLabel = new JLabel("Date");
		categotyLabel = new JLabel("Category");
		codeLabel = new JLabel("Product Code");
		descriptionLabel = new JLabel("Description");
		quantityLabel = new JLabel("Quantity");
		priceLabel = new JLabel("Sale Price");
		amountLabel = new JLabel("Total");
		totalLabel = new JLabel("Total amount");
		netAmountLabel=new JLabel("Net Amount");
		discountLabel=new JLabel("Discount Amount");
		payLabel = new JLabel("Pay Amount");
		balanceLabel = new JLabel("Balace Amount");
		JLabel blank=new JLabel("");
		JLabel blank1=new JLabel("");
		JLabel blank2=new JLabel("");
		JLabel blank3=new JLabel("");

		//initialized all text fields
		categoryField = new JTextField(10);
		codeField = new JTextField(10);
		descriptionField = new JTextField(10);
		priceField = new JTextField(10);
		amountField = new JTextField(10);
		totalField = new JTextField(10);
		discountField=new JTextField(10);
		netAmountField=new JTextField(10);
		payField = new JTextField(10);
		balanceField = new JTextField(10);
		qtyField = new JTextField(10);

		//initialized all buttons with button names and colors
		addButton = new JButton("Add");
		printBillButton = new JButton("Print Bill");
		saveButton = new JButton("Save");
		saveButton.setBackground(Color.MAGENTA);
		newButton=new JButton("New");
		newButton.setBackground(Color.MAGENTA);
		deleteButton=new JButton("Delete");
		deleteButton.setBackground(Color.MAGENTA);
		netAmountButton=new JButton("Net Amount");
		printBillButton.setBackground(Color.MAGENTA);
		netAmountButton.setBackground(Color.MAGENTA);
		addButton.setBackground(Color.MAGENTA);


		//to click buttons,add action listener to the all buttons
		addButton.addActionListener(this);
		printBillButton.addActionListener(this);
		saveButton.addActionListener(this);
		newButton.addActionListener(this);
		netAmountButton.addActionListener(this);
		deleteButton.addActionListener(this);
		codeField.addKeyListener(this);
		qtyField.addKeyListener(this);

		//initialized button panel 
		addButtonPanel = new JPanel();
		//set add button to the add button panel
		addButtonPanel.add(addButton);

		//initialized text area with width and height
		billTextArea = new JTextArea(25, 35);

		//initialized sale panel 
		salePanel = new JPanel();
		//set grid layout to the sale panel
		salePanel.setLayout(new GridLayout(7, 2));
		//set labels and text fields  to the sale  panel
		salePanel.add(dateLabel);
		salePanel.add(dateField);
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
		//set title border  to the sale panel1 and background color
		salePanel1.setBorder(BorderFactory.createTitledBorder("Sale"));
		salePanel1.setBackground(Color.BLUE);
		//set border layout to the sale panel1
		salePanel1.setLayout(new BorderLayout());
		//set sale panle and add button to the sale panel1 at location center and south
		salePanel1.add(salePanel, BorderLayout.CENTER);
		salePanel1.add(addButtonPanel, BorderLayout.SOUTH);

		//initialized table and DefaultTableModel
		table = new JTable();
		model = new DefaultTableModel();
		model = (DefaultTableModel) table.getModel();
		//add columns into the table
		model.addColumn("Code");
		model.addColumn("Description");
		model.addColumn("Category");
		model.addColumn("Quantity");
		model.addColumn("Sale Price");
		model.addColumn("Amount");
		
		//initialized pay panel 
		JPanel payPanel = new JPanel();
		//set grid layout to the pay panel
		payPanel.setLayout(new GridLayout(4, 4));
		//set labels , text fields and button to the pay panel
		payPanel.add(totalLabel);
		payPanel.add(totalField);
		payPanel.add(netAmountLabel);
		payPanel.add(netAmountField);
		payPanel.add(discountLabel);
		payPanel.add(discountField);
		payPanel.add(payLabel);
		payPanel.add(payField);
		payPanel.add(blank);
		payPanel.add(netAmountButton);
		payPanel.add(balanceLabel);
		payPanel.add(balanceField);
		payPanel.add(blank1);
		payPanel.add(blank2);
		payPanel.add(blank3);
		payPanel.add(printBillButton);
		

		//initialized table panel 
		tablePanel=new JPanel();
		//set border layout to the pay panel
		tablePanel.setLayout(new BorderLayout());
		//initialized scroll bar  
		tableJs = new JScrollPane(table);
		//set text area to the scroll bar
		textAreaJs = new JScrollPane(billTextArea);
		//set scroll bar,delete button to the table panel
		tablePanel.add(tableJs,BorderLayout.CENTER);
		tablePanel.add(deleteButton,BorderLayout.PAGE_END);

		//set flow layout to the frame
		this.setLayout(new FlowLayout());
		//set sale panel,pay panel ,scroll bar,table panel and buttons to the frame
		this.add(salePanel1);
		this.add(payPanel);
		this.add(tablePanel);
		this.add(textAreaJs);
		this.add(saveButton);
		this.add(newButton);
		//show frame
		this.setVisible(true);

		//do now show text area scroll bar
		textAreaJs.setVisible(false);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//if code is inserted , press enter key
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		
				System.out.print("code ok");//show output on console
				String code = codeField.getText().toString();//catch code from text field
				boolean b = false;
				try {
					createDB();
					sql = "select * from stock where Code='" + code + "'";//write sql ,select all data from stock table
					rs = stm.executeQuery(sql);//set sql to the result set
					//as long as there is data
					while (rs.next()) {
						b = true;
						saleCode = rs.getString("Code").toString();//select code from stock table
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
	 * selected all data from stock table where code is equal to in the sale table 
	 *  and then auto show related text fields 
	 */
	public void callCode(String sCode) {
		// TODO Auto-generated method stub

		saleCode = sCode;
		createDB();
		sql1 = "select*from stock where Code=" + saleCode;
		try {
			rs1 = stm.executeQuery(sql1);
			while (rs1.next()) {
				dbCode = rs1.getString("Code").toString();
				dbDescription = rs1.getString("Description").toString();
				dbCategory=rs1.getString("Category").toString();
				dbPrice = rs1.getString("Sale_Price").toString();
			}
			stm.close();
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(sql1);
		codeField.setText(dbCode);
		descriptionField.setText(dbDescription);
		categoryField.setText(dbCategory);
		priceField.setText(dbPrice);

	}

	/*catch quantity and price from related text fields and 
	then calculate total amount and then set amount text field this 
	calculated amount*/
	@Override
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
		// TODO Auto-generated method stub
		//if add button is clicked
		if (e.getSource() == addButton) {
			System.out.println("ok");//show ok on the console
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
		//if net amount button is clicked
		else if(e.getSource()==netAmountButton)
		{
			/*catch discount amount from text field and  minus 
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
		}
		//if print bill  button is clicked
		else if (e.getSource() == printBillButton) {
			//if was not filled customer payment,show alert
			if (payField.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
						"Enter customer payment amount", "Customer Payment",
						JOptionPane.ERROR_MESSAGE);
			} 
			//after customer payment was filled , calculate balance
			else {
				textAreaJs.setVisible(true);//show text area scroll bar
				Balance();//to calculate balance amount call balance method 
				Bill();//to print slip call bill method
			}
		}
		//if  save  button is clicked
		else if (e.getSource() == saveButton) {
			//catch date and net amount from related text fields
			int netAmount=Integer.parseInt(netAmountField.getText().toString());
			String date = dateField.getText().toString();
			//declare variables for data in the table
			int discount;
			int quantity;
			String des ,category, price, amount, code;
			
			//as long as there is data in the table ,select all
			DefaultTableModel model = new DefaultTableModel();
			model = (DefaultTableModel) table.getModel();
			for (int i = 0; i < model.getRowCount(); i++) {
				code = (String) model.getValueAt(i, 0);
				des = (String) model.getValueAt(i, 1);
				category=(String)model.getValueAt(i, 2);
				quantity=Integer.parseInt((String) model.getValueAt(i, 3));
				price = (String) model.getValueAt(i, 4);
				amount = (String) model.getValueAt(i, 5);
			
			createDB();//call create DB() for database join
			//insert data into sale table
			saleSql = "insert into sale values ('" + date + "','" + code + "','"
					+ des + "','" +category+"','"+ quantity + "','" + price + "'," + amount+ ")";
			//insert data into tempSale table
			tempSaleSql="insert into tempSale values ('" + date + "','" + code + "','"
					+ des + "','" +category+"','"+ quantity + "','" + price + "'," + amount+ ")";
			//update stock quantity after purchase quantity
			stockUpdateSql="update stock set Quantity=Quantity-(select sum(tempSale.Quantity) from tempSale where tempSale.Code=stock.Code) where exists (select 1 from tempSale where tempSale.Code=stock.Code)";
			//delete all data tempSale table because only temporarily
			deleteTempSale="delete from tempSale";
			try {
				//update tables,set to the statement
				System.out.print(saleSql);//show output on console
				stm.executeUpdate(saleSql);
				stm.executeUpdate(tempSaleSql);
				stm.executeUpdate(stockUpdateSql);
				stm.executeUpdate(deleteTempSale);
				//close statement and connection
				stm.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		

}
			// all done ,show alert
			JOptionPane.showMessageDialog(this, "Sale is successfully completed");
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
				discountAllowedSql="insert into discount_allowed values('"+date+"',"+discount+")";

				
				try {
					stm.executeUpdate(discountAllowedSql);
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
	
			discountField.setText("");
			netAmountField.setText("");
			payField.setText("");
			totalField.setText("");
			balanceField.setText("");
			billTextArea.setText("");
			table.setModel(new DefaultTableModel(null, new String[] { "Code",
					"Description","Category", "Quantity", "Sale Price", "Amount" }));
			textAreaJs.setVisible(false);

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

		new saleManagement();

	}

	//calculate balance amount> pay amount - net amount
	public void Balance() {
		
		int total = Integer.parseInt(totalField.getText());
		int netAmount=Integer.parseInt(netAmountField.getText().toString());
		int pay = Integer.parseInt(payField.getText());
		
		int balance = pay-netAmount;
		balanceField.setText(String.valueOf(balance));

	}

	//to print slip , selected all data in the table is inserted into the text area and then print slip containing net amount,pay maount and balance amount
	public void Bill() {
		System.out.print("bill bill bill\n");
		String total = totalField.getText();
		String pay = payField.getText();
		String balance = balanceField.getText();
		String dis=discountField.getText();

		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) table.getModel();

		billTextArea
				.setText(billTextArea.getText()
						+ "****************************************************************************\n");
		billTextArea
				.setText(billTextArea.getText()
						+ "                                         SALE BOUCHER                       \n");
		billTextArea
				.setText(billTextArea.getText()
						+ "***************************************************************************\n");

		// Heading
		billTextArea.setText(billTextArea.getText() + "Product" + "\t" + " Qty"
				+ "\t" + "Price" + "\t" + "Amount" + "\n");

		for (int i = 0; i < model.getRowCount(); i++) {
			String des = (String) model.getValueAt(i, 1);
			String quantity = (String) model.getValueAt(i, 3);
			String price = (String) model.getValueAt(i, 4);
			String amount = (String) model.getValueAt(i, 5);
		
			billTextArea.setText(billTextArea.getText() + des + "\t" + quantity
					+ "\t" + price + "\t" + amount + "\n");

		}
		billTextArea.setText(billTextArea.getText() + "\n");
		billTextArea.setText(billTextArea.getText() + "\n");

		billTextArea.setText(billTextArea.getText() + "\t \t \t" + "SubTotal: "
				+ total + "\n");
		billTextArea.setText(billTextArea.getText() + "\t \t \t"
				+ "Discount : " + dis + "\n");
		billTextArea.setText(billTextArea.getText() + "\t \t \t"
				+ "Pay           : " + pay + "\n");
		billTextArea.setText(billTextArea.getText() + "\t \t \t"
				+ "Balance   : " + balance + "\n");
		billTextArea.setText(billTextArea.getText() + "\n");
		billTextArea
				.setText(billTextArea.getText()
						+ "******************************************************************************\n");
		billTextArea
				.setText(billTextArea.getText()
						+ "                               Thank you, come again      \n");
	}
	

}
