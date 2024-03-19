package Login_Sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.cj.xdevapi.Statement;

import MyProject.JavaCrud;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class Login_S {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_S window = new Login_S();
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
	public Login_S() {
		initialize();
		Connect();
	}
	
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
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.BLACK);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 779, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(new Color(248, 248, 255));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 60));
		lblNewLabel.setBounds(293, 32, 181, 80);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("USER NAME");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBackground(Color.WHITE);
		lblUsername.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		lblUsername.setBounds(110, 165, 223, 45);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		lblPassword.setBounds(110, 239, 223, 45);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(418, 165, 258, 45);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setBounds(418, 239, 258, 45);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			          
					String username = txtUsername.getText();
					String password = txtPassword.getText();
		 
		                pst = con.prepareStatement("select * from login where Username = '"+username+"' and Password = '"+password+"'");
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		            	txtPassword.setText(null);
						txtUsername.setText(null);
						
						JavaCrud info = new JavaCrud();
						JavaCrud.main(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "**INVALID LOGIN DETAILS**","OOPS!! LOGIN ERROR",JOptionPane.ERROR_MESSAGE);
						txtPassword.setText(null);
						txtUsername.setText(null);
						
					}
		 
		        }
		catch (SQLException ex) {
		          
		        }	
					
				
				
			}
		});
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setFont(new Font("Goudy Old Style", Font.BOLD, 30));
		btnLogin.setBounds(110, 367, 132, 65);
		frame.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnExit.setFont(new Font("Goudy Old Style", Font.BOLD, 30));
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.setBounds(529, 367, 132, 65);
		frame.getContentPane().add(btnExit);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtUsername.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setFont(new Font("Goudy Old Style", Font.BOLD, 30));
		btnReset.setBackground(Color.LIGHT_GRAY);
		btnReset.setBounds(326, 367, 132, 65);
		frame.getContentPane().add(btnReset);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(666, 413, -560, -93);
		frame.getContentPane().add(separator);
	}
}
