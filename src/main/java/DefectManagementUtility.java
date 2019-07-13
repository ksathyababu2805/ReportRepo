import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DefectManagementUtility {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefectManagementUtility window = new DefectManagementUtility();
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
	public DefectManagementUtility() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDefectManagementUtility = new JLabel("Defect Management Utility");
		lblDefectManagementUtility.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDefectManagementUtility.setBounds(166, 24, 194, 25);
		frame.getContentPane().add(lblDefectManagementUtility);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(51, 94, 59, 25);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(113, 96, 126, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(51, 117, 59, 25);
		frame.getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(113, 119, 126, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel lblJiraUrl = new JLabel("JIRA URL");
		lblJiraUrl.setBounds(51, 60, 59, 25);
		frame.getContentPane().add(lblJiraUrl);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(113, 62, 327, 20);
		frame.getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Proceed");
		btnNewButton.setBounds(255, 117, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(351, 118, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.setBounds(351, 266, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblProjectDirectory = new JLabel("Project Directory");
		lblProjectDirectory.setBounds(51, 169, 89, 25);
		frame.getContentPane().add(lblProjectDirectory);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(150, 171, 290, 20);
		frame.getContentPane().add(textField_3);
		
		JButton btnLoadReport = new JButton("Load Report");
		btnLoadReport.setBounds(330, 202, 110, 23);
		frame.getContentPane().add(btnLoadReport);
	}
}
