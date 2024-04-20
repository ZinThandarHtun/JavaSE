package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream.PutField;
import java.sql.Connection;
import java.sql.DriverManager;

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

public class newProductForm extends JFrame implements ActionListener {
	/*
	 * global declaration carried id,labels,text fields,combo boxes,formatted
	 * text fields,panels and buttons with related frame
	 */
	JLabel date, code, name, group, qtyLabel, purchasePrice, salePrice;
	JButton save, cancel, homePageForm;
	JTextField codeField, nameField, qtyField, purchaseField, saleField;
	JPanel gridPanel, buttonPanel, comboPanel;
	JComboBox groupCombo;
	JFormattedTextField dateField;
	int id;

	// global declaration connection,used database name,used user name,used
	// password and statement
	Connection conn;
	String db = "jdbc:mysql://localhost/project";
	String username = "root";
	String password = "root";
	String sql;
	Statement stm;

	// build constructor
	public newProductForm() {
		// setting frame with title and boundary
		this.setTitle("New Product Form");
		this.setBounds(200, 200, 400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// initialized labels of the frame with labels
		date = new JLabel("Date");
		code = new JLabel("Product Code");
		name = new JLabel("Description");
		group = new JLabel("Product Category");
		qtyLabel = new JLabel("Opening Quantity");
		purchasePrice = new JLabel("Purchase Price");
		salePrice = new JLabel("Sale Price");

		// initialized text fields of the frame
		codeField = new JTextField();
		nameField = new JTextField();
		qtyField = new JTextField();
		purchaseField = new JTextField();
		saleField = new JTextField();

		// initialized formatted text fields for the date of the frame
		dateField = new JFormattedTextField(
				DateFormat.getDateInstance(DateFormat.SHORT));
		dateField.setValue(new Date());// set date at the frame
		dateField.setPreferredSize(new Dimension(130, 30));// set size of the
															// formatted field

		// initialized combo box with items of the frame
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

		// initialized buttons with button's names and colors of the frame
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		homePageForm = new JButton("lets go back>> ");
		save.setBackground(Color.PINK);
		cancel.setBackground(Color.PINK);
		// initialized panel of the frame
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(8, 2));// set grid layout at the
													// panel
		gridPanel.setBorder(BorderFactory.createEtchedBorder());// set etched
																// border at the
																// panel
		// set labels,text fields,formatted fields,buttons and combo box to the
		// panel
		gridPanel.add(date);
		gridPanel.add(dateField);
		gridPanel.add(code);
		gridPanel.add(codeField);
		gridPanel.add(name);
		gridPanel.add(nameField);
		gridPanel.add(group);
		gridPanel.add(groupCombo);
		gridPanel.add(qtyLabel);
		gridPanel.add(qtyField);
		gridPanel.add(purchasePrice);
		gridPanel.add(purchaseField);
		gridPanel.add(salePrice);
		gridPanel.add(saleField);
		gridPanel.add(save);
		gridPanel.add(cancel);

		// to click,add action listener to the button
		save.addActionListener(this);
		cancel.addActionListener(this);
		homePageForm.addActionListener(this);

		// set empty border to the homePageButton with color
		homePageForm.setBorder(new EmptyBorder(0, 0, 0, 0));
		homePageForm.setForeground(Color.BLUE);

		// set flow layout to the frame
		this.setLayout(new FlowLayout());
		// set grid panel,button to the frame
		this.add(gridPanel, BorderLayout.NORTH);
		this.add(homePageForm, BorderLayout.CENTER);
		this.setVisible(true);// show frame
	}

	// main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new newProductForm();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// if save button is clicked,
		if (e.getSource() == save) {
			// if code was not filled , show message dialog
			if (codeField.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Enter  a product code",
						"Product Code", JOptionPane.WARNING_MESSAGE);
			// if description was not filled , show message dialog
			else if (nameField.getText().equals(""))
				JOptionPane.showMessageDialog(this,
						"Enter  a product description", "Product Description",
						JOptionPane.WARNING_MESSAGE);
			// if category was not chosen , show message dialog
			else if (groupCombo.getSelectedIndex() == 0)
				JOptionPane.showMessageDialog(this,
						"Choose category of product", "Product Category",
						JOptionPane.WARNING_MESSAGE);
			// if quantity was not filled , show message dialog
			else if (qtyField.getText().equals(""))
				JOptionPane.showMessageDialog(this,
						"Enter product's opening quantity ",
						"Opening Quantity", JOptionPane.WARNING_MESSAGE);
			// if purchase price was not filled , show message dialog
			else if (purchaseField.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Enter  purchase price ",
						"Purchase Price", JOptionPane.WARNING_MESSAGE);
			// if sale price was not filled , show message dialog
			else if (saleField.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Enter  a sale price",
						"Sale Price", JOptionPane.WARNING_MESSAGE);
			// if all text fields was filled,
			else {

				//catch all data from all text fields
				String date = dateField.getText();
				String code = codeField.getText();
				String description = nameField.getText().toString();
				String quantity = qtyField.getText().toString();
				String category = groupCombo.getSelectedItem().toString();
				String purchase = purchaseField.getText().toString();
				String sale = saleField.getText().toString();
				System.out.println(date + code + description + category
						+ quantity + purchase + sale);//show console output
				
				try {
					//create database
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(db, username, password);
					stm = conn.createStatement();

					// write insert query, insert stock data into stock table
					sql = "insert into stock values ('" + date + "','" + code
							+ "','" + description + "','" + category + "','"
							+ quantity + "','" + purchase + "','" + sale + "')";
					System.out.print(sql);

					stm.executeUpdate(sql);// update customer table in database

					// close statement,connection
					stm.close();
					conn.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

		//if cancel button is clicked
		else if (e.getSource() == cancel) {
			//all text fields is clear and combo boxes are unchecked
			codeField.setText(" ");
			nameField.setText(" ");
			qtyField.setText(" ");
			purchaseField.setText(" ");
			saleField.setText(" ");
			groupCombo.setSelectedIndex(0);
		} 
		//if home page button is clicked
		else {
			new homePageForm(id);//call home page form class
			dispose();//dispose page
		}

	}

}
