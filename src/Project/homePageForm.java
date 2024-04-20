package Project;

import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class homePageForm extends JFrame implements ActionListener {
	/*global declaration carried id,menu bar,menu items and menus with related frame*/
	JMenuBar mb;
	JMenu File,view,updateProduct,account;
	JMenuItem newProduct,sale,purchase,inventory,saleinventory,purchaseinventory,logout,update,delete,switchAccount,newAccount,updateAccount;
	int updateId;
	//build constructor
	public homePageForm(int id)
	{ 
		//initialized id into update id from id
		updateId=id;
		//setting frame with title and boundary
		this.setTitle("Product Management");
		this.setBounds(200,200,500,400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//initialized menu bar,menu  of the frame
		mb=new JMenuBar();
		File=new JMenu("File");
		updateProduct=new JMenu("Delete & Update Product");
		view=new JMenu("View");
		account=new JMenu("Account");
		
		//to insert image icons into each menu item initialized image icons
		ImageIcon newproductIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\newProduct.png");
		ImageIcon updateIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\updateMenuitem.jpg");
		ImageIcon deleteIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\deleteMenuitem.jpg");
		ImageIcon saleIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\sale.png");
		ImageIcon purchaseIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\purchase.png");
		ImageIcon inventoryIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\inventory.png");
		ImageIcon saleInventoryIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\saleinventory.png");
		ImageIcon purchaseInventoryIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\purchaseinventory.png");
		ImageIcon switchAccountIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\switchaccount.png");
		ImageIcon newAccountIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\newUser.png");
		ImageIcon updateAccountIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\updateUserData.png");
		ImageIcon logOutIcon=new ImageIcon("C:\\Users\\GICM\\Desktop\\project image\\logout.jpg");

		//initialized menu items with image icons of the frame
		newProduct=new JMenuItem("New Product",newproductIcon);
		sale=new JMenuItem("Sale",saleIcon);
		purchase=new JMenuItem("Purchase",purchaseIcon);
		inventory=new JMenuItem("Inventory",inventoryIcon);
		saleinventory=new JMenuItem("Sale Details",saleInventoryIcon);
		purchaseinventory=new JMenuItem("Purchasee Details",purchaseInventoryIcon);
		switchAccount=new JMenuItem("Switch Account",switchAccountIcon);
		updateAccount=new JMenuItem("Update User Data",updateAccountIcon);
		newAccount=new JMenuItem("New Account",newAccountIcon);
		logout=new JMenuItem("Log Out",logOutIcon);
		update=new JMenuItem("Update",updateIcon);
		delete=new JMenuItem("Delete",deleteIcon);
		
		//set menu bar to the frame
		this.setJMenuBar(mb);
		//set menu to the menu bar
		mb.add(File);
		mb.add(view);
		mb.add(account);
	
		newProduct.setMnemonic('n');//underline n at new product menu item
		KeyStroke k1=KeyStroke.getKeyStroke(KeyEvent.VK_F1,KeyEvent.CTRL_MASK);//initialized keystroke for new product menu item
		newProduct.setAccelerator(k1);//set keystroke at new product menu item
		File.add(newProduct);//set new product menu item to the file menu
		
		update.setMnemonic('U');//underline u at update product menu item
		KeyStroke ku=KeyStroke.getKeyStroke(KeyEvent.VK_U,KeyEvent.CTRL_MASK);//initialized keystroke for update product menu item
		update.setAccelerator(ku);//set keystroke at update product menu item
		updateProduct.add(update);//set update product menu item to the update product menu
		
		delete.setMnemonic('D');//underline d at delete product menu item
		KeyStroke kd=KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_MASK);//initialized keystroke for delete product menu item
		delete.setAccelerator(kd);//set keystroke at delete product menu item
		updateProduct.add(delete);//set delete product menu item to the update product menu
		
		updateProduct.setMnemonic('p');//underline p at update product menu item
		File.add(updateProduct);//set update product menu item to the file menu
		File.addSeparator();//set horizontal line after update product menu
		
		
		sale.setMnemonic('s');//underline s at sale  menu item
		KeyStroke k3=KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_MASK);
		sale.setAccelerator(k3);//set keystroke at sale menu item
		File.add(sale);//set sale menu item to the file menu
		File.addSeparator();//set horizontal line after sale management menu item
		
		purchase.setMnemonic('p');//underline p at purchase menu item
		KeyStroke k4=KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_MASK);
		purchase.setAccelerator(k4);//set keystroke at purchase menu item
		File.add(purchase);//set purchase menu item to the file menu
		
		inventory.setMnemonic('i');//underline i at inventory menu item
		KeyStroke k6=KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.ALT_MASK);
		inventory.setAccelerator(k6);//set keystroke at inventory menu item
		view.add(inventory);//set inventory menu item to the view menu
		view.addSeparator();//set horizontal line after inventory menu item
		
		saleinventory.setMnemonic('S');//underline s at sale inventory menu item
		KeyStroke k7=KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.ALT_MASK);
		saleinventory.setAccelerator(k7);//set keystroke at new product item
		view.add(saleinventory);//set sale inventory menu item to the view menu
		view.addSeparator();//set horizontal line after sale inventory menu item
		
		purchaseinventory.setMnemonic('P');//underline p at purchase inventory menu item
		KeyStroke k8=KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.ALT_MASK);
		purchaseinventory.setAccelerator(k8);//set keystroke at purchase inventory menu item
		view.add(purchaseinventory);//set purchase inventory menu item to the view menu
	
		switchAccount.setMnemonic('s');//underline s at switch account menu item
		account.add(switchAccount);//set switch account menu item to the account menu
		
		updateAccount.setMnemonic('u');//underline u at update account menu item
		account.add(updateAccount);//set update account menu item to the account menu
		
		newAccount.setMnemonic('n');//underline n at new account menu item
		account.add(newAccount);//set new account menu item to the account menu
		
		logout.setMnemonic('l');//underline l at log out menu item
		account.add(logout);//set log out menu item to the account menu
		
		//to click menu items, add action listener to the menu items
		newProduct.addActionListener(this);
		updateProduct.addActionListener(this);
		sale.addActionListener(this);
		purchase.addActionListener(this);
		inventory.addActionListener(this);
		switchAccount.addActionListener(this);
		newAccount.addActionListener(this);
		updateAccount.addActionListener(this);
		logout.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		saleinventory.addActionListener(this);
		purchaseinventory.addActionListener(this);
		//show frame
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if new product menu item is clicked
		if(e.getSource()==newProduct)
		{
			dispose();//dispose page
			new newProductForm();//call new product class
		}
		//if update menu item is clicked
		if(e.getSource()==update)
		{
			new updateProductForm();//call update product class
			dispose();//dispose page
		}
		////if delete menu item is clicked
		if(e.getSource()==delete)
		{
			new deleteProductForm();//call delete product class
			dispose();//dispose page
		}
		//if sale menu item is clicked
		 if(e.getSource()==sale){
			new saleManagement();//call sale management class
		}
		//if purchase menu item is clicked
		 if(e.getSource()==purchase)
		{
			new purchaseManagement();//call purchase management class
		}
		//if switch account menu item is clicked
		 if(e.getSource()==switchAccount)
		{
		
			 //insert show alert message into a
			int a=JOptionPane.showConfirmDialog(switchAccount, "Are you sure ? you want to switch");
			//if yes is clicked
			if(a==JOptionPane.YES_OPTION)
			{
				dispose();//dispose page
				logintest login=new logintest();//call login test class
				login.setVisible(true);//show login from
			}
			//if no is clicked
			else
			{
				dispose();//dispose page
				new homePageForm(updateId);//call home page form class
			}
		
		}
		//if update account menu item is clicked
		 if(e.getSource()==updateAccount)
		{
			dispose();//dispose page
			new UpdateProfile(updateId);//call update profile class
			
		}
		 //if new account menu item is clicked
		 if(e.getSource()==newAccount)
		{
			dispose();//dispose page
			new Register();//call new user register class
			
		}
		//if logout menu item is clicked
		if(e.getSource()==logout)
		{

			System.exit(0);//exit window
		}
		//if inventory menu item is clicked
		if(e.getSource()==inventory)
		{
			dispose();//dispose page
			new inventoryManagement();//call inventory management class
			
		}
		//if sale inventory menu item is clicked
		if(e.getSource()==saleinventory)
		{
			dispose();//dispose page
			new saleInventory();//call sale inventory class
			
		}
		//if purchase inventory menu item is clicked
		if(e.getSource()==purchaseinventory)
		{
			dispose();//dispose page
			new purchaseInventory();//call purchase inventory
			
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new homePageForm();

	}

}
