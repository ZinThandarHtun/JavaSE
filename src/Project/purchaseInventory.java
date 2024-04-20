package Project;

import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class purchaseInventory extends JFrame implements ActionListener {
	/*global declaration carried id,text area,scroll pane,labels,text fields
	,panels and buttons  with related frame*/
	JLabel dateLabel, codeLabel, categoryLabel,supplierNameLabel;
	JTextField dateField, codeField, categoryField,supplierNameField;
	JButton searchButton, backToHomeButton;
	JPanel salePanel;
	JTextArea saleTextArea;
	JScrollPane saleJScrollPane;
	int id;
	//global declaration connection,used database name,used user name,used password ,result set,and statement 
	Connection conn;
	String db = "jdbc:mysql://localhost/project";
	String username = "root";
	String password = "root";
	Statement stm;
	ResultSet rs;
	
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
	public purchaseInventory() {
		// TODO Auto-generated constructor stub
		//setting frame with title and boundary
		this.setTitle("Purchase Inventory ");
		this.setBounds(10, 10, 600, 700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//initialized labels with label's name of the frame
		supplierNameLabel=new JLabel("Supplier Name");
		dateLabel = new JLabel("Date");
		codeLabel = new JLabel("Product Code");
		categoryLabel = new JLabel("Product Category");

		//initialized text fields of the frame
		supplierNameField=new JTextField(15);
		dateField = new JTextField(15);
		codeField = new JTextField(15);
		categoryField = new JTextField(15);

		//initialized search button with button's name and color
		searchButton = new JButton("Search");
		searchButton.setBackground(Color.MAGENTA);
		//to click,add action listener to the search button
		searchButton.addActionListener(this);

		//initialized sale panel 
		salePanel = new JPanel();
		//set grid layout to the sale panel
		salePanel.setLayout(new GridLayout(4, 2));
		
		//set labels and text fields to the sale panel
		salePanel.add(dateLabel);
		salePanel.add(dateField);
		salePanel.add(supplierNameLabel);
		salePanel.add(supplierNameField);
		salePanel.add(codeLabel);
		salePanel.add(codeField);
		salePanel.add(categoryLabel);
		salePanel.add(categoryField);

		//initialized text area of the frame
		saleTextArea = new JTextArea(30, 40);
		//set text area to the scroll bar
		saleJScrollPane = new JScrollPane(saleTextArea);
		
		//initialized search panel of the frame
		JPanel searchPanel = new JPanel();
		//set border layout to the search panel
		searchPanel.setLayout(new BorderLayout());
		searchPanel.setBorder(BorderFactory.createEmptyBorder());//set etched border to the search panel
		//set sale panel and search button to the search panel at location setting
		searchPanel.add(salePanel, BorderLayout.CENTER);
		searchPanel.add(searchButton, BorderLayout.SOUTH);

		//initialized back to home button with empty border and color
		backToHomeButton = new JButton("Go Back To Home Page Form");
		backToHomeButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		backToHomeButton.setForeground(Color.BLUE);
		backToHomeButton.addActionListener(this);//to click, add action listener to the button
		this.setLayout(new FlowLayout());//set flow layout to the frame
		//set search panel , scroll bar and button to the frame
		this.add(searchPanel);
		this.add(saleJScrollPane);
		this.add(backToHomeButton);
		this.setVisible(true);//show frame

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if backToHome button is clicked
		if (e.getSource() == backToHomeButton) {
			dispose();//dispose page
			new homePageForm(id);//call homePageFrom class
			
		} 
		//if search button is clicked
		else {
			//catch data of supplierName,date,code and category text fields
			String supplierName=supplierNameField.getText();
			String date = dateField.getText();
			String code=codeField.getText();
			String category=categoryField.getText();
			//if search button is clicked date,code,category,supplier name were not filled 
			if((date.equals(""))&&code.equals("")&&category.equals("")&&supplierName.equals(""))
				all();//call all method,select all data
			//if search button is clicked only date was filed
			else if(!(date.equals(""))&&code.equals("")&&category.equals("")&&supplierName.equals(""))
				equalDate();//call equalDate method,select all data
			//if search button is clicked,date and supplier name were filled
			else if(!(date.equals(""))&&(code.equals(""))&&category.equals("")&&!(supplierName.equals("")))
				equalDateAndSupplierName();//call equalDateAndSupplierName,select all data
			//if search button is clicked,date and supplier name and code were filled
			else if(!(date.equals(""))&&!(code.equals(""))&&category.equals("")&&!(supplierName.equals("")))
				equalDateAndSupplierNameAndCode();//call equalDateAndSupplierNameAndCode method ,select all data
			//if search button is clicked, date and supplier name and category were filled
			else if(!(date.equals(""))&&(code.equals(""))&&!(category.equals(""))&&!(supplierName.equals("")))
				equalDateAndSupplierNameAndCategory();//call equalDateAndSupplierNameAndCategory method,select all data
			//if search button is clicked, only supplier name was filled
			else if((date.equals(""))&&(code.equals(""))&&(category.equals(""))&&!(supplierName.equals("")))
				equalSupplierName();//call equalSupplierName method,select all date
			//if search button is clicked, supplier name and code were filled
			else if((date.equals(""))&&!(code.equals(""))&&(category.equals(""))&&!(supplierName.equals("")))
				equalSupplierNameandCode();//call equalSupplierNameandCode method,select all data
			//if search button is clicked, supplier name and category were filled
			else if((date.equals(""))&&(code.equals(""))&&!(category.equals(""))&&!(supplierName.equals("")))
				equalSupplierNameandCategory();//call equalSupplierNameandCategory method, select all data
			//if search button is clicked, only category was filled
			else if((date.equals(""))&&(code.equals(""))&&!(category.equals(""))&&(supplierName.equals("")))
				equalCategory();//call equalCategory method,select all data
			//if search button is clicked,only code was filled
			else if((date.equals(""))&&!(code.equals(""))&&(category.equals(""))&&(supplierName.equals("")))
				equalCode();//call equalCode method,select all data
			//if search button is clicked, date and code were filled
			else if(!(date.equals(""))&&!(code.equals(""))&&(category.equals(""))&&(supplierName.equals("")))
				equalDateAndCode();//call equalDateAndCode,select all data
			//if search button is clicked,data and category were filled
			else if(!(date.equals(""))&&(code.equals(""))&&!(category.equals(""))&&(supplierName.equals("")))
				equalDateAndCategory();//call equalDateAndCategory method,select all data
		
			
		}

		}
	/*
	 * selected all data from purchase table where date in the purchase table 
	 * is equal to date in the date text field and then show in the text area
	 */
	public void equalDate() {
		String date = dateField.getText();

		createDB();
		String dateSql = "select*from purchase where Date='"+date +"'";

		try {
			rs = stm.executeQuery(dateSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String suppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +suppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where date and code in the purchase table 
	 * are equal to date and code in the date and code text fields and then show in the text area
	 */
	public void equalDateAndCode() {
		String date = dateField.getText();
		String code=codeField.getText();

		createDB();
	
		String equalDateAndCodeSql = "select*from purchase where Date='"+date +"' and Code='"+code+"'";

		try {
			rs = stm.executeQuery(equalDateAndCodeSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String suppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +suppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where date and category in the purchase table 
	 * are equal to date and category in the date and category text fields and then show in the text area
	 */
	public void equalDateAndCategory() {
		String date = dateField.getText();
		String category=categoryField.getText();

		createDB();
		//String dateSql="select*from sale";
		String equalDateAndCategorySql = "select*from purchase where Date='"+date +"' and Category='"+category+"'";

		try {
			rs = stm.executeQuery(equalDateAndCategorySql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String suppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +suppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where category in the purchase table 
	 * is equal to category in the category text field and then show in the text area
	 */
	public void equalCategory() {
		String category = categoryField.getText();

		createDB();
		
		String equalCategorySql = "select*from purchase where Category='"+category +"'";

		try {
			rs = stm.executeQuery(equalCategorySql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String suppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +suppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where code in the purchase table 
	 * is equal to code in the code text field and then show in the text area
	 */
	public void equalCode() {
		String code = codeField.getText();

		createDB();
		String equalCodeSql = "select*from purchase where Code='"+code +"'";

		try {
			rs = stm.executeQuery(equalCodeSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String suppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +suppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table and then show in the text area
	 */
	public void all() {
		createDB();
		String allSql="select*from purchase order By Category ";
		//String  = "select*from purchase where Date='"+date +"'";

		try {
			rs = stm.executeQuery(allSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String suppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +suppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where supplier name in the purchase table 
	 * is equal to supplier name in the supplier name text field and then show in the text area
	 */
	public void equalSupplierName() {
		String suppliername = supplierNameField.getText();

		createDB();
		String equalSupplierNameSql = "select*from purchase where Supplier_Name='"+suppliername +"'";

		try {
			rs = stm.executeQuery(equalSupplierNameSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbSuppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +dbSuppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where supplier name and code in the purchase table 
	 * are equal to supplier name and code in the supplier name and code text fields and then show in the text area
	 */
	public void equalSupplierNameandCode() {
		String suppliername = supplierNameField.getText();
		String code=codeField.getText();
		createDB();
		String supplierNameandCodeSql = "select*from purchase where Supplier_Name='"+suppliername +"'and Code='"+code+"'";

		try {
			rs = stm.executeQuery(supplierNameandCodeSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbSuppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +dbSuppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where supplier name and category in the purchase table 
	 * are equal to supplier name and category in the supplier name and category text field and then show in the text area
	 */
	public void equalSupplierNameandCategory() {
		String suppliername = supplierNameField.getText();
		String category=categoryField.getText();
		createDB();
		//String dateSql="select*from sale";
		String supplierNameandCodeSql = "select*from purchase where Supplier_Name='"+suppliername +"'and Category='"+category+"'";

		try {
			rs = stm.executeQuery(supplierNameandCodeSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbSuppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +dbSuppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where date and supplier name in the purchase table 
	 * are equal to date and supplier name in the date and supplier name text fields and then show in the text area
	 */
	public void equalDateAndSupplierName() {
		String date = dateField.getText();
		String suppliername=supplierNameField.getText();

		createDB();
		//String dateSql="select*from sale";
		String equalDateAndSupplierNameSql = "select*from purchase where Date='"+date +"' and Supplier_Name='"+suppliername+"'";

		try {
			rs = stm.executeQuery(equalDateAndSupplierNameSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbSuppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +dbSuppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from purchase table where date,supplier name and code in the purchase table 
	 * are equal to date,supplier name and code in the date,supplier name and code text fields and then show in the text area
	 */
	public void equalDateAndSupplierNameAndCode() {
		String date = dateField.getText();
		String suppliername=supplierNameField.getText();
		String code=codeField.getText();

		createDB();
		//String dateSql="select*from sale";
		String equalDateAndSupplierNameAndCodeSql = "select*from purchase where Date='"+date +"' and Supplier_Name='"+suppliername+"' and Code='"+code+"'";

		try {
			rs = stm.executeQuery(equalDateAndSupplierNameAndCodeSql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbSuppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +dbSuppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	/*
	 * selected all data from purchase table where date,supplier name and category in the purchase table 
	 * are equal to date,supplier name and category in the date,supplier name and category text fields and then show in the text area
	 */
	public void equalDateAndSupplierNameAndCategory() {
		String date = dateField.getText();
		String suppliername=supplierNameField.getText();
		String category=categoryField.getText();

		createDB();
		
		String equalDateAndSupplierNameAndCategorySql = "select*from purchase where Date='"+date +"' and Supplier_Name='"+suppliername+"' and Category='"+category+"'";

		try {
			rs = stm.executeQuery(equalDateAndSupplierNameAndCategorySql);
			saleTextArea.setText("Date"+"\t"+"Supplier Name"+ "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Purchase Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbSuppliername=rs.getString("Supplier_Name");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				saleTextArea.append(dbDate + "\t" +dbSuppliername+"\t"+ dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbPurchasePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//new purchaseInventory();
	}

}
	
