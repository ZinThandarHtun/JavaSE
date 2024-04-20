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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class saleInventory extends JFrame implements ActionListener {
	/*global declaration carried id,text area,scroll pane,labels,text fields,panels
	and buttons  with related frame*/
	JLabel dateLabel, codeLabel, categoryLabel;
	JTextField dateField, codeField, categoryField;
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

	public saleInventory() {
		// TODO Auto-generated constructor stub
		//setting frame with title and boundary
		this.setTitle("Sale Inventory ");
		this.setBounds(10, 10, 600, 700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//initialized labels with label names
		dateLabel = new JLabel("Date");
		codeLabel = new JLabel("Product Code");
		categoryLabel = new JLabel("Product Category");

		//initialized text fields
		dateField = new JTextField(15);
		codeField = new JTextField(15);
		categoryField = new JTextField(15);

		//initialized buttons with color
		searchButton = new JButton("Search");
		searchButton.setBackground(Color.MAGENTA);
		//to click,add action listener
		searchButton.addActionListener(this);

		//initialized panel 
		salePanel = new JPanel();
		//set flow layout to the sale panel 
		salePanel.setLayout(new GridLayout(3, 2));
		//set labels and text fields  to the sale panel
		salePanel.add(dateLabel);
		salePanel.add(dateField);
		salePanel.add(codeLabel);
		salePanel.add(codeField);
		salePanel.add(categoryLabel);
		salePanel.add(categoryField);

		//initialized text area with height and width
		saleTextArea = new JTextArea(30, 40);
		//initialized scroll bar and set text area to the scroll bar
		saleJScrollPane = new JScrollPane(saleTextArea);
		//initialized search panel 
		JPanel searchPanel = new JPanel();
		//set etched border layout  to the search panel 
		searchPanel.setLayout(new BorderLayout());
		searchPanel.setBorder(BorderFactory.createEmptyBorder());
		//set sale panel and search button to the search panel
		searchPanel.add(salePanel, BorderLayout.NORTH);
		searchPanel.add(searchButton, BorderLayout.CENTER);

		//initialized back to home button 
		backToHomeButton = new JButton("Go Back To Home Page Form");
		//set empty  border   to the back to home  button
		backToHomeButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		//set color to the back to home button
		backToHomeButton.setForeground(Color.BLUE);
		//to click,add action listener to the back to home button
		backToHomeButton.addActionListener(this);
		this.setLayout(new FlowLayout());//flow layout to the frame
		//set search panel,button to the frame
		this.add(searchPanel);
		this.add(saleJScrollPane);
		this.add(backToHomeButton);
		//show frame
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//if search button is clicked
		if(e.getSource()==searchButton) {
			//catch data from text fields
			String date = dateField.getText();
			String code=codeField.getText();
			String category=categoryField.getText();
			//if search button is clicked nothing were not filled 
			if((date.equals(""))&&code.equals("")&&category.equals(""))
				all();//call all method,select all data
			//if search button is clicked only date was filled 
			else if(!(date.equals(""))&&code.equals("")&&category.equals(""))
				equalDate();//call equalDate method,select all data
			//if search button is clicked only code was filled 
			else if(date.equals("")&&!(code.equals(""))&&category.equals(""))
				equalCode();//call equalCode method,select all data
			//if search button is clicked only category was filled 
			else if(date.equals("")&&code.equals("")&&!(category.equals("")))
				equalCategory();//call equalCategory method ,select all data
			//if search button is clicked date,code were  filled 
			else if(!(date.equals(""))&&!(code.equals(""))&&category.equals(""))
				equalDateAndCode();//call equalDateAndCode method ,select all data
			//if search button is clicked date,category were  filled
			else if(!(date.equals(""))&&code.equals("")&&!(category.equals("")))
				equalDateAndCategory();//call equalDateAndCategory method ,select all data
		
			
		}
		//if back to home page is clicked
		else {
			new homePageForm(id);//go back home page form
			dispose();//dispose page
		} 

		}
	
	
	/*
	 * selected all data from sale table where date in the sale table 
	 * is equal to date in the date text field and then show in the text area
	 */
	public void equalDate() {
		String date = dateField.getText();

		createDB();
		
		String dateSql = "select*from sale where Date='"+date +"'";

		try {
			rs = stm.executeQuery(dateSql);
			saleTextArea.setText("Date" + "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Sale Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbSalePrice = rs.getString("Sale_Price");
				saleTextArea.append(dbDate + "\t" + dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbSalePrice
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
	 * selected all data from sale table  and then show in the text area
	 */
	public void all() {
		createDB();
		String allSql="select*from sale order by Category";
		try {
			rs = stm.executeQuery(allSql);
			saleTextArea.setText("Date" + "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Sale Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbSalePrice = rs.getString("Sale_Price");
				saleTextArea.append(dbDate + "\t" + dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbSalePrice
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
	 * selected all data from sale table where code in the sale table 
	 * is equal to code in the code text field and then show in the text area
	 */
	public void equalCode() {
		String code = codeField.getText();

		createDB();
		String codeSql = "select * from sale where Code='" + code + "'";

		try {
			rs = stm.executeQuery(codeSql);
			saleTextArea.setText("Date" + "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Sale Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbSalePrice = rs.getString("Sale_Price");
				saleTextArea.append(dbDate + "\t" + dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbSalePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from sale table where date and code in the sale table 
	 * are equal to date and code in the date and code text fields and then show in the text area
	 */
	public void equalDateAndCode() {
		String code = codeField.getText();
		String date = dateField.getText();

		createDB();
		String dateAndCodeSql="select*from sale where Date='"+date+"'and Code='"+code+"'";
		System.out.print(dateAndCodeSql);
		try {
			rs = stm.executeQuery(dateAndCodeSql);
			System.out.print(dateAndCodeSql);
			saleTextArea.setText("Date" + "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Sale Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbSalePrice = rs.getString("Sale_Price");
				saleTextArea.append(dbDate + "\t" + dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbSalePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from sale table where date and category in the sale table 
	 * are equal to date and category in the date and category text fields and then show in the text area
	 */
	public void equalDateAndCategory(){
		String category = categoryField.getText();
		String date = dateField.getText();

		createDB();
		
		String dateAndCategorySql="select * from sale where Date='"+date+"'and Category='" +category+"'";
		try {
			rs = stm.executeQuery(dateAndCategorySql);
			saleTextArea.setText("Date" + "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Sale Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbSalePrice = rs.getString("Sale_Price");
				saleTextArea.append(dbDate + "\t" + dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbSalePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * selected all data from sale table where category in the sale table 
	 * is equal to category in the category text field and then show in the text area
	 */
	public void equalCategory() {
		String category = categoryField.getText();
		//String date = dateField.getText();

		createDB();
		String categorySql = "select * from sale where Category='" + category + "'";
		
		try {
			rs = stm.executeQuery(categorySql);
			saleTextArea.setText("Date" + "\t" + "Code" + "\t" + "Description"
					+ "\t" + "Category" + "\t" + "Quantity" + "\t"
					+ "Sale Price" + "\n");
			while (rs.next()) {
				String dbDate = rs.getString("Date");
				String dbCode = rs.getString("Code");
				String dbDes = rs.getString("Description");
				String dbCategory = rs.getString("Category");
				String dbQty = rs.getString("Quantity");
				String dbSalePrice = rs.getString("Sale_Price");
				saleTextArea.append(dbDate + "\t" + dbCode + "\t" + dbDes
						+ "\t" + dbCategory + "\t" + dbQty + "\t" + dbSalePrice
						+ "\n");

			}
			stm.close();
			conn.close();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new saleInventory();

	}
}