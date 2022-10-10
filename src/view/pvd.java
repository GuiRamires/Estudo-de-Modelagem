package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class pvd extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			pvd dialog = new pvd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public pvd() {
		setTitle("Ajuda");
		setIconImage(Toolkit.getDefaultToolkit().getImage(pvd.class.getResource("/img/help.png")));
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Author: Guilherme Ramires");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(29, 40, 210, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(pvd.class.getResource("/img/mit.png")));
		lblNewLabel_1.setBounds(29, 114, 64, 64);
		contentPanel.add(lblNewLabel_1);
		
		JTextPane txtpnALicenaMit = new JTextPane();
		txtpnALicenaMit.setText("A licen\u00E7a MIT. Ela \u00E9 uma licen\u00E7a permissiva utilizada tanto em software livre quanto em software propriet\u00E1rio.");
		txtpnALicenaMit.setBounds(29, 69, 312, 34);
		contentPanel.add(txtpnALicenaMit);
	}
}