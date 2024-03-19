package MyProject;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	/**
	 * Connection to SQL
	 */
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
        }
        catch (ClassNotFoundException ex)
        {
        	ex.printStackTrace();
        }
        catch (SQLException ex)
        {
        	ex.printStackTrace();
        }
 
    }

	  
	  
	  public void table_load()
	    {
	     try
	     {
	    pst = con.prepareStatement("select * from book");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	    }
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 972, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 45));
		lblNewLabel.setBounds(373, 11, 245, 63);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(41, 82, 411, 204);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(31, 49, 126, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(31, 98, 126, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(31, 145, 126, 23);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setForeground(Color.BLACK);
		txtbname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtbname.setBounds(198, 53, 151, 19);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setForeground(Color.BLACK);
		txtedition.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtedition.setColumns(10);
		txtedition.setBounds(198, 102, 151, 19);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setForeground(Color.BLACK);
		txtprice.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtprice.setColumns(10);
		txtprice.setBounds(198, 149, 151, 19);
		panel.add(txtprice);
		
		
		// CODE FOR SAVING RECORD
		
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				try {
				pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
				pst.setString(1, bname);
				pst.setString(2, edition);
				pst.setString(3, price);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null,"!!! YOUR RECORD HAS BEEN SUCCESSFULLY ADDED !!!");
				table_load();
				          
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
					
				        }
			}});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(41, 323, 107, 63);
		frame.getContentPane().add(btnNewButton);
		
		
		// CODE FOR EXITING PANEL
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
			
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(190, 323, 107, 63);
		frame.getContentPane().add(btnExit);
		
		
		// CODE FOR CLEARING TEXT
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.BLACK);
		btnClear.setBackground(Color.LIGHT_GRAY);
		btnClear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
			}
			
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnClear.setBounds(345, 323, 107, 63);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(483, 84, 434, 302);
		frame.getContentPane().add(scrollPane);
		
		// TABLE DESIGNING
		
		
		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(41, 416, 411, 75);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Book ID");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(30, 24, 99, 23);
		panel_1.add(lblNewLabel_1_3);
		
		txtbid = new JTextField();
		txtbid.setForeground(Color.BLACK);
		txtbid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String id = txtbid.getText();
		 
		                pst = con.prepareStatement("select name,edition,price from book where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String edition = rs.getString(2);
		                String price = rs.getString(3);
		                
		                txtbname.setText(name);
		                txtedition.setText(edition);
		                txtprice.setText(price);
		          
		            }  
		            else
		            {
		             txtbname.setText("");
		             txtedition.setText("");
		                txtprice.setText("");
		                
		            }
		 
		        }
		catch (SQLException ex) {
		          
		        }	
				
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(149, 24, 210, 23);
		panel_1.add(txtbid);
		
		
		// CODE FORE UPDATING RECORDS
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,bid;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid  = txtbid.getText();
				try {
				pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
							pst.setString(1, bname);
				            pst.setString(2, edition);
				            pst.setString(3, price);
				            pst.setString(4, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "!!! YOUR RECORD HAS BEEN SUCCESSFULLY UPDATED !!!");
				            table_load();
				          
				            txtbname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbname.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}	
				
			}
		});
		
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(526, 416, 144, 75);
		frame.getContentPane().add(btnUpdate);
		
		
		// CODE FOR DELETING RECORD
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String bid;
				bid  = txtbid.getText();
				try {
				pst = con.prepareStatement("delete from book where id =?");
				            pst.setString(1, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "!!! YOUR RECORD HAS BEEN SUCCESSFULLY DELETED !!!");
				            table_load();
				          
				            txtbname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbname.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}
				
			}
			
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(752, 416, 144, 75);
		frame.getContentPane().add(btnDelete);
	}
}
