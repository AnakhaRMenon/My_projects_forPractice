package SignUP;

import Login_Sys.Login_S;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import MyProject.JavaCrud;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SignIn {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn window = new SignIn();
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
	public SignIn() {
		initialize();
		Connect();
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


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 676, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel IbIUsername = new JLabel("USERNAME");
		IbIUsername.setBackground(Color.GRAY);
		IbIUsername.setForeground(Color.WHITE);
		IbIUsername.setFont(new Font("Times New Roman", Font.BOLD, 25));
		IbIUsername.setBounds(113, 97, 142, 43);
		frame.getContentPane().add(IbIUsername);
		
		JLabel IbIPassword = new JLabel("PASSWORD");
		IbIPassword.setBackground(Color.GRAY);
		IbIPassword.setForeground(Color.WHITE);
		IbIPassword.setFont(new Font("Times New Roman", Font.BOLD, 25));
		IbIPassword.setBounds(113, 168, 142, 43);
		frame.getContentPane().add(IbIPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(Color.LIGHT_GRAY);
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtUsername.setBounds(348, 97, 216, 43);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBackground(Color.LIGHT_GRAY);
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtPassword.setColumns(10);
		txtPassword.setBounds(348, 168, 216, 43);
		frame.getContentPane().add(txtPassword);
		
		JButton btnSign_in = new JButton("Sign In");
		btnSign_in.setBackground(Color.LIGHT_GRAY);
		btnSign_in.setForeground(Color.BLACK);
		btnSign_in.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnSign_in.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				String User_name,pass;
				User_name = txtUsername.getText();
				pass = txtPassword.getText();
				
				try {
				pst = con.prepareStatement("insert into login(Username,Password)values(?,?)");
				pst.setString(1, User_name);
				pst.setString(2, pass);
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null,"!!! YOU HAVE SUCCESSFULLY SIGNED UP, KINDLY LOGIN TO OUR PAGE !!!");
				
				          
				txtUsername.setText("");
				txtPassword.setText("");
				txtUsername.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
					
				        }
				
			}
			
			
		});
		btnSign_in.setBounds(113, 302, 191, 80);
		frame.getContentPane().add(btnSign_in);
		
		JButton btnLogins = new JButton("Login");
		btnLogins.setBackground(Color.LIGHT_GRAY);
		btnLogins.setForeground(Color.BLACK);
		btnLogins.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				Login_S info = new Login_S();
				Login_S.main(null);
			}
			
			
		});
		btnLogins.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnLogins.setBounds(373, 302, 191, 80);
		frame.getContentPane().add(btnLogins);
		
		JLabel lblNewLabel = new JLabel("Sign In");
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 50));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(243, 21, 176, 53);
		frame.getContentPane().add(lblNewLabel);
	}
}
