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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class inventoryManagement extends JFrame implements ActionListener {
	/*global declaration carried id,text area,scroll pane,labels,text fields,combo boxes
	and buttons  with related frame*/
	JLabel codeLabel, descriptionLabel, categoryLabel, saleLabel,
			purchaseLabel;
	JComboBox codeCombo, descriptionCombo, categoryCombo, saleCombo,
			purchaseCombo;
	JTextField codeField, descriptionField, categoryField, saleField,
			purchaseField;
	JButton searchButton,backToHomeButton;
	JTextArea codeTextArea;
	JScrollPane codeScrollPane;
	JPanel userPanel;
	int id;
	
	//global declaration connection,used database name,used user name,used password ,result set,and statement 
	Connection conn;
	String db = "jdbc:mysql://localhost/project";
	String username = "root";
	String password = "root";
	Statement stm;
	ResultSet rs;
	String stockSql;

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
	public inventoryManagement() {

		//setting frame with title and boundary
		this.setTitle("Inventory Management");
		this.setBounds(10, 10, 700, 700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//initialized label,text field and combo box of the product code with label names and data into combo box
		codeLabel = new JLabel("Product Code");
		codeCombo = new JComboBox();
		codeCombo.addItem("Select way to search");
		codeCombo.addItem("Equal to");
		codeCombo.addItem("Begin with");
		codeCombo.addItem("Contains");
		codeCombo.setSelectedIndex(0);
		codeField = new JTextField(10);

		//initialized label,text field and combo box of the product description with label names and data into combo box
		descriptionLabel = new JLabel("Product Description");
		descriptionCombo = new JComboBox();
		descriptionCombo.addItem("Select way to search");
		descriptionCombo.addItem("Equal to");
		descriptionCombo.addItem("Begin with");
		descriptionCombo.addItem("Contains");
		descriptionCombo.setSelectedIndex(0);
		descriptionField = new JTextField(10);

		//initialized label,text field and combo box of the product category with label names and data into combo box
		categoryLabel = new JLabel("Product Category");
		categoryCombo = new JComboBox();
		categoryCombo.addItem("Select way to search");
		categoryCombo.addItem("Equal to");
		categoryCombo.addItem("Begin with");
		categoryCombo.addItem("Contains");
		categoryCombo.setSelectedIndex(0);
		categoryField = new JTextField(10);

		//initialized label,text field and combo box of the product sale price with label names and data into combo box
		saleLabel = new JLabel("Sale Price");
		saleCombo = new JComboBox();
		saleCombo.addItem("Select way to search");
		saleCombo.addItem("Equal to");
		saleCombo.addItem("Begin with");
		saleCombo.addItem("Contains");
		saleCombo.setSelectedIndex(0);
		saleField = new JTextField(10);

		//initialized label,text field and combo box of the product purchase price with label names and data into combo box
		purchaseLabel = new JLabel("Purchase Price");
		purchaseCombo = new JComboBox();
		purchaseCombo.addItem("Select way to search");
		purchaseCombo.addItem("Equal to");
		purchaseCombo.addItem("Begin with");
		purchaseCombo.addItem("Contains");
		purchaseCombo.setSelectedIndex(0);
		purchaseField = new JTextField(10);

		//initialized search button with background color 
		searchButton = new JButton("Search");
		searchButton.setBackground(Color.MAGENTA);
		//to click,add action listener to the search button
		searchButton.addActionListener(this);

		//initialized text area 
		codeTextArea = new JTextArea(25, 50);
		codeScrollPane = new JScrollPane(codeTextArea);//set text area to the scroll bar

		//initialized user panel 
		userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(5, 3));//set grid layout to the user panel
		//set labels,combo boxes,text fields to the user panel
		userPanel.add(codeLabel);
		userPanel.add(codeCombo);
		userPanel.add(codeField);
		userPanel.add(descriptionLabel);
		userPanel.add(descriptionCombo);
		userPanel.add(descriptionField);
		userPanel.add(categoryLabel);
		userPanel.add(categoryCombo);
		userPanel.add(categoryField);
		userPanel.add(saleLabel);
		userPanel.add(saleCombo);
		userPanel.add(saleField);
		userPanel.add(purchaseLabel);
		userPanel.add(purchaseCombo);
		userPanel.add(purchaseField);

		//initialized search panel
		JPanel searchPanel=new JPanel();
		searchPanel.setLayout(new FlowLayout());//set flow layout to the search panel
		//set user panel and search button to search panel
		searchPanel.add(userPanel);
		searchPanel.add(searchButton);
		
		//initialized back to home button with background color and button's name
		backToHomeButton = new JButton("Go Back To Home Page Form");
		backToHomeButton.setBorder(new EmptyBorder(0,0,0,0));
		backToHomeButton.setForeground(Color.BLUE);
		//to click ,add action listener to the back to home button
		backToHomeButton.addActionListener(this);
		
		this.setLayout(new FlowLayout());//set flow layout to the frame
		//set search panel, scroll bar and back to home button to the frame
		this.add(searchPanel);
		this.add(codeScrollPane);
		this.add(backToHomeButton);
		//show frame
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if search button is clicked
		if (e.getSource() == searchButton) {
			String code = codeField.getText();//catch data from code text field
			//if all combo boxes is not chosen something
			if(codeCombo.getSelectedIndex() == 0&&descriptionCombo.getSelectedIndex() == 0&&
					categoryCombo.getSelectedIndex() == 0&&saleCombo.getSelectedIndex() == 0&&purchaseCombo.getSelectedIndex() == 0)
				all();//call all method
		 
			if (codeCombo.getSelectedIndex() == 1)//if equal to is chosen in code combo box
				equalCode();//call equalCode method

			if (codeCombo.getSelectedIndex() == 2)//if begin with is chosen in code combo box
				beginCode();//call beginCode method

			if (codeCombo.getSelectedIndex() == 3)//if contains is chosen in code combo box
				containCode();//call containCode method
			if (descriptionCombo.getSelectedIndex() == 1)//if equal to is chosen in description combo box
				equalDescription();//call equalDescription method

			if (descriptionCombo.getSelectedIndex() == 2)//if begin with is chosen in description combo box
				beginDescription();//call beginDescription method

			if (descriptionCombo.getSelectedIndex() == 3)//if contains is chosen in description combo box
				containDescription();//call containDescription method

			if (categoryCombo.getSelectedIndex() == 1)//if equal to is chosen in category combo box
				equalCategory();//call equalCategory method

			if (categoryCombo.getSelectedIndex() == 2)//if begin with is chosen in category combo box
				beginCategory();//call beginCategory method

			if (categoryCombo.getSelectedIndex() == 3)//if contains is chosen in category combo box
				containCategory();//call containCategory method

			if (saleCombo.getSelectedIndex() == 1)//if equal to is chosen in sale price combo box
				equalSale();//call equalSale method

			if (saleCombo.getSelectedIndex() == 2)//if begin with is chosen in sale price combo box
				beginSale();//call beginSale method

			if (saleCombo.getSelectedIndex() == 3)//if contains is chosen in sale price combo box
				containSale();//call containSale method

			if (purchaseCombo.getSelectedIndex() == 1)//if equal to is chosen in purchase price combo box
				equalPurchase();//call equalPurchase method

			if (purchaseCombo.getSelectedIndex() == 2)//if begin with is chosen in purchase price combo box
				beginPurchase();//call beginPurchase method

			if (purchaseCombo.getSelectedIndex() == 3)//if contains is chosen in purchase price combo box
				containPurchase();//call containPurchase method
			//if back to home button id clicked
			
		}else if(e.getSource()==backToHomeButton)
		{
			new homePageForm(id);//call homePageForm class
		dispose();//dispose page
		}

	}
	/*
	 * selected all data from stock table and show in the text area
	 */
	public void all() {

		createDB();
		String equalSql = "select * from stock";

		try {
			rs = stm.executeQuery(equalSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * selected all data from stock table where code in the stock table 
	 * is equal to code in the code text field and then show in the text area
	 */
	public void equalCode() {
		String code = codeField.getText();

		createDB();
		String equalSql = "select * from stock where Code='" + code + "'";

		try {
			rs = stm.executeQuery(equalSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where code in the stock table 
	 * begins with code in the code text field and then show in the text area
	 */
	public void beginCode() {
		String code = codeField.getText();

		createDB();
		String beginSql = "select * from stock where Code like '" + code + "%'";

		try {
			rs = stm.executeQuery(beginSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where code in the code text field  
	 * contains in code in the stock table and then show in the text area
	 */
	public void containCode() {
		String code = codeField.getText();

		createDB();
		String containSql = "select * from stock where Code like '%" + code
				+ "%'";

		try {
			rs = stm.executeQuery(containSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where description in the stock table 
	 * is equal to description name in the description text field and then show in the text area
	 */
	public void equalDescription() {
		String description = descriptionField.getText();

		createDB();
		String equalSql = "select * from stock where Description='"
				+ description + "'";

		try {
			rs = stm.executeQuery(equalSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where description in the stock table 
	 * begins with description in the description text field and then show in the text area
	 */
	public void beginDescription() {
		String descrption = descriptionField.getText();

		createDB();
		String beginSql = "select * from stock where Description like '"
				+ descrption + "%'";

		try {
			rs = stm.executeQuery(beginSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where description in the description text field  
	 * contains in description in the stock table and then show in the text area
	 */
	public void containDescription() {
		String descrption = descriptionField.getText();

		createDB();
		String containSql = "select * from stock where Description like '%"
				+ descrption + "%'";

		try {
			rs = stm.executeQuery(containSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where category in the stock table 
	 * is equal to category name in the category text field and then show in the text area
	 */
	public void equalCategory() {
		String category = categoryField.getText();

		createDB();
		String equalSql = "select * from stock where Category='" + category
				+ "'";

		try {
			rs = stm.executeQuery(equalSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where category name in the stock table 
	 * begins with category name category name in the category text field and then show in the text area
	 */
	public void beginCategory() {
		String category = categoryField.getText();

		createDB();
		String beginSql = "select * from stock where Category like '"
				+ category + "%'";

		try {
			rs = stm.executeQuery(beginSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where category name in the category text field  
	 * contains in category name in the stock table and then show in the text area
	 */
	public void containCategory() {
		String category = categoryField.getText();

		createDB();
		String containSql = "select * from stock where Category like '%"
				+ category + "%'";

		try {
			rs = stm.executeQuery(containSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where sale price in the stock table 
	 * is equal to sale price in the sale price text field and then show in the text area
	 */
	public void equalSale() {
		String salePrice = saleField.getText();

		createDB();
		String equalSql = "select * from stock where Sale_Price='" + salePrice + "'";

		try {
			rs = stm.executeQuery(equalSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where sale price in the stock table 
	 * begins with sale price in the sale price text field and then show in the text area
	 */
	public void beginSale() {
		String salePrice = saleField.getText();

		createDB();
		String beginSql = "select * from stock where Sale_Price like '" + salePrice + "%'";

		try {
			rs = stm.executeQuery(beginSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where sale price in the sale price text field  
	 * contains in sale price in the stock table and then show in the text area
	 */
	public void containSale() {
		String salePrice = saleField.getText();

		createDB();
		String containSql = "select * from stock where Sale_Price like '%" + salePrice
				+ "%'";

		try {
			rs = stm.executeQuery(containSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where purchase price in the stock table 
	 * is equal to purchase price in the purchase price text field and then show in the text area
	 */
	public void equalPurchase() {
		String purchasePrice = purchaseField.getText();

		createDB();
		String equalSql = "select * from stock where Purchase_Price='" + purchasePrice + "'";

		try {
			rs = stm.executeQuery(equalSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where purchase price in the stock table 
	 * begins with purchase price in the purchase price text field and then show in the text area
	 */
	public void beginPurchase() {
		String purchasePrice = purchaseField.getText();

		createDB();
		String beginSql = "select * from stock where Purchase_Price like '" + purchasePrice + "%'";

		try {
			rs = stm.executeQuery(beginSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from stock table where purchase price in the purchase price text field  
	 * contains in purchase price in the stock table and then show in the text area
	 */
	public void containPurchase() {
		String purchasePrice = purchaseField.getText();

		createDB();
		String containSql = "select * from stock where Purchase_Price like '%" + purchasePrice
				+ "%'";

		try {
			rs = stm.executeQuery(containSql);
			codeTextArea.setText("Code" + "\t" + "Description" + "\t"
					+ "Category" + "\t" + "Quantity" + "\t" + "Purchase Price"
					+ "\t" + "Sale Price" + "\n");
			while (rs.next()) {
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbPurchasePrice = rs.getString("Purchase_Price");
				String dbSalePrice = rs.getString("Sale_Price");
				codeTextArea.append(dbCode + "\t" + dbDes + "\t" + dbCategory
						+ "\t" + dbQty + "\t" + dbPurchasePrice + "\t"
						+ dbSalePrice + "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new inventoryManagement();

	}

}
