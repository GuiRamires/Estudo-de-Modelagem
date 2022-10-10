 package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/favicon.png")));
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(29, 29, 37, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBounds(83, 26, 203, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(25, 67, 37, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(83, 64, 203, 20);
		contentPane.add(txtSenha);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(7, 106, 64, 64);
		contentPane.add(lblStatus);

		JButton btnLogar = new JButton("Acessar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogar.setBounds(197, 117, 89, 23);
		contentPane.add(btnLogar);

		// validação Atxy2k 
		RestrictedTextField login = new RestrictedTextField(txtLogin);
		login.setLimit(10);

		RestrictedTextField senha = new RestrictedTextField(txtSenha);
		senha.setLimit(255);
		
		// Usar o Enter ao inves de "clicar" no botap para logar
		getRootPane().setDefaultButton(btnLogar);
	}// fim do construtor

	// Cria��o de um objeto para acessar a camada model
	DAO dao = new DAO();
	private JLabel lblStatus;

	/**
	 * M�todo usado para verificar o status do servidor
	 */
	private void status() {
		try {
			// abrir a conex�o
			Connection con = dao.conectar();
			if (con == null) {
				// escolher a imagem dboff
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
			} else {
				// escolher a imagem dbon
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dbon.png")));
			}
			// N�o esquecer de fechar a conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * M�todo usado para autenticar um usu�rio
	 */
	private void logar() {
		// validação da senha (captura segura)
		String capturaSenha = new String(txtSenha.getPassword());
		// validação
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o seu Login");
			txtLogin.requestFocus();
		} else if (txtSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Digite a sua senha");
			txtSenha.requestFocus();
		} else {
			// logica principal
			String read = "select * from usuarios where login=? and senha = md5(?)";
			try {
				// abrir a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar os argumentos (login e senha)
				// Substituir os ?(argumentos) pelo conte�do das caixas de texto
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				// Executar a query e de acordo com o resultado liberar os recursos da tela
				// principal
				ResultSet rs = pst.executeQuery();
				// Valida��o (autentica��o de usu�rio)
				// rs.next() -> exist�ncia de login e senha correspondente
				if (rs.next()) {
					//Verificar o perfil do usu�rio
					String perfil = rs.getString(5);
					//System.out.println(perfil);
					Principal principal = new Principal();
					if (perfil.equals("admin")) {						
						//abrir a tela principal
						principal.setVisible(true);
						//habilitar recursos
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						//personalizar
						principal.panelUsuario.setBackground(Color.RED);
						//setar o nome do usu�rio na tela principal
						principal.lblUsuario.setText(rs.getString(2));
					} else {
						//abrir a tela principal
						principal.setVisible(true);
						//setar o nome do usu�rio na tela principal
						principal.lblUsuario.setText(rs.getString(2));
					}
					//encerrar a conex�o
					con.close();
					//fechar a tela de login
					this.dispose();
					
				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inv�lido(s)");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}// fim do c�digo
