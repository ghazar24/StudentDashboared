import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Date;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StudentInfo {

	private JFrame frame;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldFees;
	private JTable table;
	private JTextField textFieldSearch;
	private Connection connection=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInfo window = new StudentInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void loadTable() {
				
				
				try {
					
					String query = "select ID, Name, Fees, DateOfBirth from StudentInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
			}

	/**
	 * Create the application.
	 */
	public StudentInfo() {
		initialize();
		connection = sqlitConnection.dbConnecter();
		loadTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 832, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LaSalle College- Student Dashboeard");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(168, 23, 515, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("International School - Montreal Canada");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(303, 67, 345, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\ghaza\\eclipse-workspace\\Ghazarian_Ghazar_2015953\\Img\\download.png"));
		lblNewLabel_2.setBounds(21, 76, 190, 183);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Student Information");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(361, 92, 199, 33);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ID:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(252, 158, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(400, 154, 142, 26);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton btnNew = new JButton("New");
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNew.setIcon(new ImageIcon("C:\\Users\\ghaza\\eclipse-workspace\\Ghazarian_Ghazar_2015953\\Img\\button_violet_delete.png"));
		btnNew.setBounds(630, 158, 137, 44);
		frame.getContentPane().add(btnNew);
		
		JLabel lblNewLabel_4_1 = new JLabel("Student Name:");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4_1.setBounds(252, 206, 142, 14);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Fees");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4_2.setBounds(252, 253, 46, 14);
		frame.getContentPane().add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("Date Of Birth");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4_3.setBounds(252, 286, 132, 14);
		frame.getContentPane().add(lblNewLabel_4_3);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(400, 202, 142, 26);
		frame.getContentPane().add(textFieldName);
		
		textFieldFees = new JTextField();
		textFieldFees.setColumns(10);
		textFieldFees.setBounds(400, 249, 142, 26);
		frame.getContentPane().add(textFieldFees);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(400, 286, 142, 26);
		frame.getContentPane().add(dateChooser);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateFormat df= new SimpleDateFormat("dd-MMM-yyyy");
				
				String query = "insert into StudentInfo (ID,Name,Fees,DateOfBirth) values (?,?,?,?)";
				try {
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldFees.getText());
					pst.setString(4, df.format(dateChooser.getDate()).toString());
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"One student Inserted Succefully");
					pst.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
					JOptionPane.showMessageDialog(null,"Duplicatd Student ID");
				}
				
				loadTable();
				
				
			}
		});
		btnInsert.setIcon(new ImageIcon("C:\\Users\\ghaza\\eclipse-workspace\\Ghazarian_Ghazar_2015953\\Img\\button_blue_add.png"));
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInsert.setBounds(630, 214, 137, 45);
		frame.getContentPane().add(btnInsert);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateFormat df= new SimpleDateFormat("dd-MMM-yyyy");
				
				try {
									
				
									String query = "update StudentInfo set ID='"+ textFieldID.getText() + "',Name='" + textFieldName.getText() +
											"',Fees='" + textFieldFees.getText() + "',DateOfBirth='"+ df.format(dateChooser.getDate()).toString() + "' where ID='" + 
											textFieldID.getText() + "'";
									
									PreparedStatement pst = connection.prepareStatement(query);
									
									pst.execute();
									
									JOptionPane.showMessageDialog(null, "Data Updated");
									pst.close();
							
							
						} catch (Exception e2) {
							// TODO: handle exception
							
							JOptionPane.showMessageDialog(null, e2);
						}
				
				loadTable();
				
			}
		});
		btnUpdate.setIcon(new ImageIcon("C:\\Users\\ghaza\\eclipse-workspace\\Ghazarian_Ghazar_2015953\\Img\\button_pink_close.png"));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBounds(630, 267, 137, 47);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
int action = JOptionPane.showConfirmDialog(null, "Are Yu sure to delete?", "Delete",JOptionPane.YES_NO_OPTION);
				
				if (action == 0) {
					
					
					try {
						

						String query = "delete from StudentInfo where ID='" + textFieldID.getText() + "'";
						
						PreparedStatement pst = connection.prepareStatement(query);
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Data Removed");
						pst.close();
				
				
					} catch (Exception e2) {
						// TODO: handle exception
						
						JOptionPane.showMessageDialog(null, e2);
					}
					
					
					
				}
				
				
		
				loadTable();
				
				
				
			}
		});
		btnDelete.setIcon(new ImageIcon("C:\\Users\\ghaza\\eclipse-workspace\\Ghazarian_Ghazar_2015953\\Img\\button_red_stop.png"));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(630, 325, 137, 38);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 402, 583, 164);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DateFormat df= new SimpleDateFormat("dd-MMM-yyyy");
				
				
				try {
					
					int row = table.getSelectedRow();
					String ID= (table.getModel().getValueAt(row, 0)).toString();
					
					
					String query ="select * from StudentInfo where ID ='"+ID+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					String aDate=rs.getString("DateOfBirth");
					Date date = (Date) df.parse(aDate);
					
					
					while(rs.next()) {
						
						textFieldID.setText(rs.getString("ID"));
						textFieldName.setText(rs.getString("Name"));
						textFieldFees.setText(rs.getString("Fees"));
						
						dateChooser.setDate(date);
						
						
						
					}
					
					rs.close();
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		table.setToolTipText("ID\r\nName\r\nFees\r\nDate of Birth");
		scrollPane.setViewportView(table);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			try {
								
							
			
								String query = "select ID, Name, Fees, DateOfBirth from StudentInfo where Name=?";
								
								PreparedStatement pst = connection.prepareStatement(query);
								pst.setString(1, textFieldSearch.getText());
								
								ResultSet rs = pst.executeQuery();
								
								table.setModel(DbUtils.resultSetToTableModel(rs));
								
								rs.close();
								pst.close();
						
						
							} catch (Exception e2) {
								// TODO: handle exception
								
								JOptionPane.showMessageDialog(null, e2);
							}
							
							
					
					
						}
							
							
						
		});
		textFieldSearch.setBounds(400, 350, 142, 33);
		frame.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Search Student By Name:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(168, 349, 191, 32);
		frame.getContentPane().add(lblNewLabel_5);
	}
}
