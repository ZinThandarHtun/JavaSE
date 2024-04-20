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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class deleteProduct extends JFrame implements ActionListener {
	/*global declaration delete code,labels,text fields,combo boxes
	buttons and panels with related frame*/
	String deleteCode;
	JLabel code, name, group, purchasePrice, salePrice;
	JButton delete, cancel, homePageForm;
	JTextField codeField, nameField, purchaseField, saleField;
	JPanel gridPanel, buttonPanel, comboPanel;
	JComboBox groupCombo;

	//Global declaration connection,used database name,used user name,used password and statement 
	Connection conn;
	String db = "jdbc:mysql://localhost/project";
	String username = "root";
	String password1 = "root";
	String sql;
	Statement stm;
	ResultSet rs;

	//global declaration code,description,category, purchase price and sale price from databases
	String dbCode;
	String dbDescription;
	String dbCategory;
	String dbPurchase;
	String dbSale;

	//create databases 
	public void createDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(db, username, password1);
			stm = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
  //build constructor
	public deleteProduct(String deletecode) {
		deleteCode = deletecode;//Insert delete code 
		//setting frame with title and boundary
		this.setTitle("Delete Product's Data");
		this.setBounds(300, 200, 400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//initialized labels of the frame with label names
		code = new JLabel("Product Code");
		name = new JLabel("Description");
		group = new JLabel("Product Category");
		purchasePrice = new JLabel("Purchase Price");
		salePrice = new JLabel("Sale Price");
		
		//initialized text fields of the frame
		codeField = new JTextField();
		nameField = new JTextField();
		purchaseField = new JTextField();
		saleField = new JTextField();

		//initialized combo box of the frame with category names
		groupCombo = new JComboBox();
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
		delete = new JButton("Delete");
		cancel = new JButton("Cancel");
		homePageForm = new JButton("lets go back>> ");

		/*initialized delete form panel 
		 * set labels ,text fields and buttons to the panel
		*/
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(6, 2));
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
		gridPanel.add(delete);
		gridPanel.add(cancel);

		//to click buttons,add action listener to the buttons
		delete.addActionListener(this);
		cancel.addActionListener(this);
		homePageForm.addActionListener(this);

		//create background color and empty border for the home page button 
		homePageForm.setBorder(new EmptyBorder(0, 0, 0, 0));
		homePageForm.setForeground(Color.BLUE);

		//create background color for the delete and cancel buttons
		delete.setBackground(Color.PINK);
		cancel.setBackground(Color.PINK);
		
		//set flow layout in the frame
		this.setLayout(new FlowLayout());
		//set grid panel and home page button to the frame
		this.add(gridPanel, BorderLayout.NORTH);
		this.add(homePageForm, BorderLayout.CENTER);
		//show frame
		this.setVisible(true);

		//create database
		createDB();
		sql = "select*from stock where Code=" + deleteCode;//Write sql->select all data from stock tables 
		try {
			rs = stm.executeQuery(sql);//add sql to the result set
			//As long as there is data
			while (rs.next()) {
				//select code,description,category,purchase price and sale price from stock tables
				dbCode = rs.getString("Code");
				dbDescription = rs.getString("Description");
				dbCategory = rs.getString("Category");
				dbPurchase = rs.getString("Purchase_Price");
				dbSale = rs.getString("Sale_Price");
			}
			//statement and connection are closed
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
		if (dbCategory.equals("Lipstick"))
			groupCombo.setSelectedIndex(1);
		else if (dbCategory.equals("Foundation"))
			groupCombo.setSelectedIndex(2);
		else if (dbCategory.equals("Mascara"))
			groupCombo.setSelectedIndex(3);
		else if (dbCategory.equals("Lotion"))
			groupCombo.setSelectedIndex(4);
		else if (dbCategory.equals("Essence"))
			groupCombo.setSelectedIndex(5);
		else if (dbCategory.equals("Toner"))
			groupCombo.setSelectedIndex(6);
		else if (dbCategory.equals("Cream Puff"))
			groupCombo.setSelectedIndex(7);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if delete button is clicked
		if(e.getSource()==delete)
		{
			//catch data from the text fields
			String code=codeField.getText();
			String description=nameField.getText();
			String category=groupCombo.getSelectedItem().toString();
			String purchase=purchaseField.getText();
			String sale=saleField.getText();
			
			//create database
			createDB();
			sql="delete from stock where Code="+deleteCode;//Write sql->delete all data from stock tables where code in stock table is equal to code text field  
			try {
				stm.executeUpdate(sql);//add sql to the statement
				//show message dialog after deleting code
				JOptionPane.showMessageDialog(delete, "Code number "+code+"  is successfully deleted");
				//statement and connection are closed
				stm.close();
				conn.close();
				dispose();//dispose page
				new deleteProductForm();//call delete product class
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
		}
		//if cancel button is clicked
		else if(e.getSource()==cancel){
			//clear code text field,name text field,purchase text field ,sale text field and combo box
			codeField.setText("");
			nameField.setText("");
			groupCombo.setSelectedIndex(0);
			purchaseField.setText("");
			saleField.setText("");
			
		}
		//if home page button button is clicked
	else
		{
		//call delete product class
		new deleteProductForm();
		}
		// TODO Auto-generated method stub


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
