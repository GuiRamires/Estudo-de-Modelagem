package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPesquisarFornecedor;
	private JTextField txtForId;
	private JTextField txtForCNPJ;
	private JTextField txtForIE;
	private JTextField txtForIM;
	private JTextField txtForRazao;
	private JTextField txtForFantasia;
	private JTextField txtForSite;
	private JTextField txtForFone;
	private JTextField txtForContato;
	private JTextField txtForEmail;
	private JTextField txtForCep;
	private JTextField txtForEndereco;
	private JTextField txtForNumero;
	private JTextField txtForComplemento;
	private JTextField txtForBairro;
	private JTextField txtForCidade;
	private JTable tblFornecedores;
	private JComboBox cboForUF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedores dialog = new Fornecedores();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Fornecedores() {
		setTitle("Fornecedores");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedores.class.getResource("/img/favicon.png")));
		setModal(true);
		setBounds(100, 100, 731, 505);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Fornecedor");
		lblNewLabel.setBounds(24, 16, 68, 14);
		getContentPane().add(lblNewLabel);

		txtPesquisarFornecedor = new JTextField();
		txtPesquisarFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// evento digita��o
				pesquisarFornecedorTabela();
			}
		});
		txtPesquisarFornecedor.setBounds(97, 15, 181, 20);
		getContentPane().add(txtPesquisarFornecedor);
		txtPesquisarFornecedor.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(24, 139, 24, 14);
		getContentPane().add(lblNewLabel_2);

		txtForId = new JTextField();
		txtForId.setBounds(52, 136, 33, 20);
		getContentPane().add(txtForId);
		txtForId.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("CNPJ");
		lblNewLabel_3.setBounds(197, 139, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtForCNPJ = new JTextField();
		txtForCNPJ.setBounds(243, 136, 112, 20);
		getContentPane().add(txtForCNPJ);
		txtForCNPJ.setColumns(10);
		

		JLabel lblNewLabel_4 = new JLabel("IE");
		lblNewLabel_4.setBounds(382, 139, 15, 14);
		getContentPane().add(lblNewLabel_4);

		txtForIE = new JTextField();
		txtForIE.setBounds(409, 136, 112, 20);
		getContentPane().add(txtForIE);
		txtForIE.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("IM");
		lblNewLabel_4_1.setBounds(547, 139, 15, 14);
		getContentPane().add(lblNewLabel_4_1);

		txtForIM = new JTextField();
		txtForIM.setColumns(10);
		txtForIM.setBounds(572, 136, 112, 20);
		getContentPane().add(txtForIM);

		JLabel lblNewLabel_5 = new JLabel("Raz\u00E3o Social");
		lblNewLabel_5.setBounds(24, 182, 81, 14);
		getContentPane().add(lblNewLabel_5);

		txtForRazao = new JTextField();
		txtForRazao.setBounds(108, 179, 245, 20);
		getContentPane().add(txtForRazao);
		txtForRazao.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Nome de fantasia");
		lblNewLabel_6.setBounds(371, 182, 107, 14);
		getContentPane().add(lblNewLabel_6);

		txtForFantasia = new JTextField();
		txtForFantasia.setBounds(480, 179, 204, 20);
		getContentPane().add(txtForFantasia);
		txtForFantasia.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Site");
		lblNewLabel_7.setBounds(24, 223, 33, 14);
		getContentPane().add(lblNewLabel_7);

		txtForSite = new JTextField();
		txtForSite.setBounds(63, 220, 187, 20);
		getContentPane().add(txtForSite);
		txtForSite.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Fone");
		lblNewLabel_8.setBounds(266, 223, 46, 14);
		getContentPane().add(lblNewLabel_8);

		txtForFone = new JTextField();
		txtForFone.setBounds(311, 220, 107, 20);
		getContentPane().add(txtForFone);
		txtForFone.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Contato");
		lblNewLabel_9.setBounds(441, 223, 46, 14);
		getContentPane().add(lblNewLabel_9);

		txtForContato = new JTextField();
		txtForContato.setBounds(505, 220, 179, 20);
		getContentPane().add(txtForContato);
		txtForContato.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("E-mail");
		lblNewLabel_10.setBounds(24, 266, 46, 14);
		getContentPane().add(lblNewLabel_10);

		txtForEmail = new JTextField();
		txtForEmail.setBounds(71, 263, 179, 20);
		getContentPane().add(txtForEmail);
		txtForEmail.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("CEP");
		lblNewLabel_11.setBounds(281, 266, 46, 14);
		getContentPane().add(lblNewLabel_11);

		txtForCep = new JTextField();
		txtForCep.setBounds(316, 263, 81, 20);
		getContentPane().add(txtForCep);
		txtForCep.setColumns(10);

		JButton btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCEP();
			}
		});
		btnBuscarCep.setBounds(414, 260, 107, 23);
		getContentPane().add(btnBuscarCep);

		JLabel lblNewLabel_12 = new JLabel("Endere\u00E7o");
		lblNewLabel_12.setBounds(24, 306, 59, 14);
		getContentPane().add(lblNewLabel_12);

		txtForEndereco = new JTextField();
		txtForEndereco.setBounds(94, 303, 218, 20);
		getContentPane().add(txtForEndereco);
		txtForEndereco.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("N\u00FAmero");
		lblNewLabel_13.setBounds(338, 306, 46, 14);
		getContentPane().add(lblNewLabel_13);

		txtForNumero = new JTextField();
		txtForNumero.setBounds(394, 303, 72, 20);
		getContentPane().add(txtForNumero);
		txtForNumero.setColumns(10);
	

		JLabel lblNewLabel_14 = new JLabel("Complemento");
		lblNewLabel_14.setBounds(495, 306, 87, 14);
		getContentPane().add(lblNewLabel_14);

		txtForComplemento = new JTextField();
		txtForComplemento.setBounds(585, 303, 99, 20);
		getContentPane().add(txtForComplemento);
		txtForComplemento.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Bairro");
		lblNewLabel_15.setBounds(24, 350, 46, 14);
		getContentPane().add(lblNewLabel_15);

		txtForBairro = new JTextField();
		txtForBairro.setBounds(71, 347, 200, 20);
		getContentPane().add(txtForBairro);
		txtForBairro.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Cidade");
		lblNewLabel_16.setBounds(294, 350, 46, 14);
		getContentPane().add(lblNewLabel_16);

		txtForCidade = new JTextField();
		txtForCidade.setBounds(349, 347, 200, 20);
		getContentPane().add(txtForCidade);
		txtForCidade.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("UF");
		lblNewLabel_17.setBounds(572, 350, 24, 14);
		getContentPane().add(lblNewLabel_17);

		cboForUF = new JComboBox();
		cboForUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboForUF.setBounds(603, 346, 81, 22);
		getContentPane().add(cboForUF);

		JLabel lblNewLabel_18 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_18.setBounds(18, 396, 74, 14);
		getContentPane().add(lblNewLabel_18);

		JTextArea txtForObs = new JTextArea();
		txtForObs.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtForObs.setBounds(94, 391, 313, 41);
		getContentPane().add(txtForObs);

		JButton btnForAdicionar = new JButton("");
		btnForAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFornecedores();
			}
		});
		btnForAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/add.png")));
		btnForAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnForAdicionar.setContentAreaFilled(false);
		btnForAdicionar.setBorderPainted(false);
		btnForAdicionar.setToolTipText("Adicionar");
		btnForAdicionar.setBounds(430, 378, 64, 64);
		getContentPane().add(btnForAdicionar);

		JButton btnForAlterar = new JButton("");
		btnForAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarFornecedor();
			}
		});
		btnForAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/back.png")));
		btnForAlterar.setToolTipText("Alterar");
		btnForAlterar.setContentAreaFilled(false);
		btnForAlterar.setBorderPainted(false);
		btnForAlterar.setBounds(519, 378, 64, 64);
		getContentPane().add(btnForAlterar);

		JButton btnForExcluir = new JButton("");
		btnForExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFornecedores();
			}
		});
		btnForExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/cacelar.png")));
		btnForExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnForExcluir.setToolTipText("Excluir");
		btnForExcluir.setContentAreaFilled(false);
		btnForExcluir.setBorderPainted(false);
		btnForExcluir.setBounds(613, 378, 64, 64);
		getContentPane().add(btnForExcluir);

		btnPesquisar = new JButton("Buscar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarFornecedores();
			}
		});
		btnPesquisar.setBounds(97, 135, 81, 23);
		getContentPane().add(btnPesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 46, 660, 70);
		getContentPane().add(scrollPane);

		tblFornecedores = new JTable();
		tblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// evento clicar com o mouse na tabela
				setarCaixasTexto();
			}
		});
		scrollPane.setViewportView(tblFornecedores);
		
		
		
		

		// Validação com o uso da biblioteca Atxy2k
		
		// txtForID
		RestrictedTextField validarId = new RestrictedTextField(txtForId);
		validarId.setOnlyNums(true);
		validarId.setLimit(50);
		
		// txtForCNPJ
		RestrictedTextField validarCNPJ = new RestrictedTextField(txtForCNPJ);
		validarCNPJ.setOnlyNums(true);
		validarCNPJ.setLimit(50);
		
		//txtForIE
		RestrictedTextField validarIE = new RestrictedTextField(txtForIE);
		validarIE.setOnlyNums(true);
		validarIE.setLimit(50);
		
		//txtForIM
		RestrictedTextField validarIM = new RestrictedTextField(txtForIM);
		validarIM.setOnlyNums(true);
		validarIM.setLimit(50);
		
		//txtForRazao
		RestrictedTextField validarRazao = new RestrictedTextField(txtForRazao);
		validarRazao.setLimit(50);
		
		//txtForFantasia
		RestrictedTextField validarFantasia = new RestrictedTextField(txtForFantasia);
		validarFantasia.setLimit(50);
		
		//txtForSite
		RestrictedTextField validarSite = new RestrictedTextField(txtForSite);
		validarSite.setLimit(50);
		
		//txtForfone
		RestrictedTextField validarFone = new RestrictedTextField(txtForFone);
		validarFone.setOnlyNums(true);
		validarFone.setLimit(50);
		
		//txtForContato
		RestrictedTextField validarContato = new RestrictedTextField(txtForContato);
		validarContato.setLimit(50);
		
		//txtForEmail
		RestrictedTextField validarEmail = new RestrictedTextField(txtForEmail);
		validarEmail.setLimit(50);
		
		//txtForCep
		RestrictedTextField validarCep = new RestrictedTextField(txtForCep);
		validarCep.setOnlyNums(true);
		validarCep.setLimit(50);
		
		//txtForEndereço
		RestrictedTextField validarEndereco = new RestrictedTextField(txtForEndereco);
		validarEndereco.setLimit(50);
		
		//txtForNumero
		RestrictedTextField validarNumero = new RestrictedTextField(txtForNumero);
		validarNumero.setOnlyNums(true);
		validarNumero.setLimit(50);
		
		//txtForComplemento
		RestrictedTextField validarComplemento = new RestrictedTextField(txtForEndereco);
		validarComplemento.setLimit(50);
		
		//txtForBairro
		RestrictedTextField validarBairro = new RestrictedTextField(txtForBairro);
		validarBairro.setLimit(50);
		
		//txtForCidade
		RestrictedTextField validarCidade = new RestrictedTextField(txtForCidade);
		validarCidade.setLimit(50);
		
		
		
	

	}// fim do construtor

	// Criar objeto para acessar o banco
	DAO dao = new DAO();
	private JButton btnPesquisar;

	/**
	 * M�todo respons�vel pela pesquisa avan�ada do fornecedor usando o nome de
	 * fantasia e a biblioteca rs2xml
	 */
	
	
	
	
	
	
	
	
	
	
	
	private void pesquisarFornecedores() { 
	
	if (txtForId.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Digite o ID do fornecedor");
		txtForId.requestFocus();
	} else {
		// lógica principal
		// query principal ( Instrução SQL)
		String read = "select * from fornecedores where idfor = ?";
		// tratar excessões sempre que lidar com o banco
		try {
			// estabelecer a conexÃ£o
			Connection con = dao.conectar();
			// Preparar a execução da Query
			PreparedStatement pst = con.prepareStatement(read);
			// Setar o argumento (id)
			// Substituir o ? pelo conteúdo da caixa de texto
			pst.setString(1, txtForId.getText());
			// Executar a query e exibir o resultado no formulário
			ResultSet rs = pst.executeQuery();
			// Validação (existÃªncia de fornecedor)
			// rs.next() -> existência de fornecedor
			
			
			
			if (rs.next()) {
				// preencher(setar) os campos do formulario
				txtForId.setText(rs.getString(1));
				txtForCNPJ.setText(rs.getString(2));
				txtForIE.setText(rs.getString(3));
				txtForIM.setText(rs.getString(4));
				txtForRazao.setText(rs.getString(5));
				txtForFantasia.setText(rs.getString(6));
				txtForSite.setText(rs.getString(7));
				txtForFone.setText(rs.getString(8));
				txtForContato.setText(rs.getString(9));
				txtForEmail.setText(rs.getString(10));
				txtForCep.setText(rs.getString(11));
				txtForEndereco.setText(rs.getString(12));
				txtForNumero.setText(rs.getString(13));
				txtForComplemento.setText(rs.getString(14));
				txtForBairro.setText(rs.getString(15));
				txtForCidade.setText(rs.getString(16));
				cboForUF.setSelectedItem(rs.getString(17));

				//btnAlterar.setEnabled(true);
				//btnExcluir.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado");
				//limparCampos();
				//btnAdicionar.setEnabled(true);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			
			limparCamposFornecedor();
		}
	}
}

		
		
	
	
	
	private void pesquisarFornecedorTabela() {
		String readT = "select idfor as ID,fantasia as fornecedor,fone,contato from fornecedores where fantasia like ?";
		try {
			// Estabelecer a conex�o
			Connection con = dao.conectar();
			// Preparar a execu��o da query
			PreparedStatement pst = con.prepareStatement(readT);
			// Setar o argumento (fantasia)
			// Substituir o ? pelo conte�do da caixa de texto
			pst.setString(1, txtPesquisarFornecedor.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			tblFornecedores.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	
	
	
	
	
	
	/**
	 * M�todo respons�vel por setar as caixas de texto de acordo com os campos da
	 * tabela (mouse click)
	 */
	private void setarCaixasTexto() {
		// criar uma vari�vel para receber a linha da tabela
		int setar = tblFornecedores.getSelectedRow();
		txtForId.setText(tblFornecedores.getModel().getValueAt(setar, 0).toString());
		// txtForFantasia.setText(tblFornecedores.getModel().getValueAt(setar,
		// 1).toString());
	}

	
	
	
	
	
	
	
	// demais m�todos do CRUD

	
	private void buscarCEP() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtForCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtForCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtForBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboForUF.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {

					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}

			}
			// Setar Campo Endereço
			txtForEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	
	/**
	 * Limpar campos
	 */
	private void limparCamposFornecedor() {
		// limpar a tabela
		((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
	}

	
	
	
	
	
	
	private void adicionarFornecedores() {

		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CNPJ");
			txtForCNPJ.requestFocus();
		} else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a raz�o social do fornecedor");
			txtForRazao.requestFocus();
		} else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		} else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do fornecedor");
			txtForFone.requestFocus();
		} else if (txtForCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CEP do fornecedor");
			txtForCep.requestFocus();
		} else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o endere�o do fornecedor");
			txtForEndereco.requestFocus();
		} else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o bairro do fornecedor");
			txtForBairro.requestFocus();
		} else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a cidade do fornecedor");
			txtForCidade.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into fornecedores (cnpj,ie,im,razao,fantasia,site,fone,contato,email,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForIM.getText());
				pst.setString(4, txtForRazao.getText());
				pst.setString(5, txtForFantasia.getText());
				pst.setString(6, txtForSite.getText());
				pst.setString(7, txtForFone.getText());
				pst.setString(8, txtForContato.getText());
				pst.setString(9, txtForEmail.getText());
				pst.setString(10, txtForCep.getText());
				pst.setString(11, txtForEndereco.getText());
				pst.setString(12, txtForNumero.getText());
				pst.setString(13, txtForComplemento.getText());
				pst.setString(14, txtForBairro.getText());
				pst.setString(15, txtForCidade.getText());
				pst.setString(16, cboForUF.getSelectedItem().toString());
				// Executar a query e inserir o fornecedor no banco
				pst.executeUpdate();
				

				
				// confirma��o
				JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CNPJ em uso.\nDigite outro CNPJ");
				txtForCNPJ.setText(null);
				txtForCNPJ.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
			
			limparCampos();
		}
	}
	
	
	
	
	
	
	
	private void excluirFornecedores() {
		// valida��o (confirma��o de exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclus�o do usu�rio?", "Aten��o!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// l�gica principal
			String delete = "delete from fornecedores where idfor=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a query(comando sql) substituindo a ? pelo idusu
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtForId.getText());
				// Executar a query
				pst.executeUpdate();
				// confima��o
				
			
				
				JOptionPane.showMessageDialog(null, "Usu�rio exclu�do com sucesso.");
				// encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			limparCampos();
		}
	}
	
	
	
	private void alterarFornecedor() {
		// valida��o
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba CNPJ");
			txtForCNPJ.requestFocus();
		} else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Razao");
			txtForRazao.requestFocus();
		} else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Fantasia");
			txtForFantasia.requestFocus();
		} else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Fone");
			txtForFone.requestFocus();
		} else if (txtForCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba CEP");
			txtForCep.requestFocus();
		} else if (txtForEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Endereço");
			txtForEndereco.requestFocus();
		} else if (txtForNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Numero");
			txtForNumero.requestFocus();
		} else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Bairro");
			txtForBairro.requestFocus();
		} else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Cidade");
			txtForCidade.requestFocus();
		} else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha a aba UF");
			cboForUF.requestFocus();
			
			
		} else {
			// l�gica principal
			String update = "update fornecedores set cnpj=?, ie=?,im=?,razao=?,fantasia=?,site=?,fone=?,contato=?,email=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? where idfor=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir os ????? pelo conte�do das caixas de texto
				
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForIM.getText());
				pst.setString(4, txtForRazao.getText());
				pst.setString(5, txtForFantasia.getText().toString());
				pst.setString(6, txtForSite.getText());
				pst.setString(7, txtForFone.getText());
				pst.setString(8, txtForContato.getText());
				pst.setString(9, txtForEmail.getText());
				pst.setString(10, txtForCep.getText());
				pst.setString(11, txtForEndereco.getText());
				pst.setString(12, txtForNumero.getText());
				pst.setString(13, txtForComplemento.getText());
				pst.setString(14, txtForBairro.getText());
				pst.setString(15, txtForCidade.getText());
				pst.setString(16, cboForUF.getSelectedItem().toString());
				pst.setString(17, txtForId.getText());
				
				
				
				
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// Encerrar a conex�o
				JOptionPane.showMessageDialog(null, "Dados do Fornecedor alterados com sucesso");
				
				
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CNPJ , IE ou IM em uso.\nIsira outros Desses Campos.");
				txtForCNPJ.setText(null);
				txtForCNPJ.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
			
			limparCampos();
		}
	}
	
	
	
	





private void limparCampos() {
	txtForId.setText(null);
	txtForCNPJ.setText(null);
	txtForIE.setText(null);
	txtForIM.setText(null);
	txtForRazao.setText(null);
	txtForFantasia.setText(null);	
	txtForSite.setText(null);
	txtForFone.setText(null);
	txtForContato.setText(null);
	txtForEmail.setText(null);
	txtForCep.setText(null);
	txtForEndereco.setText(null);
	txtForNumero.setText(null);
	txtForComplemento.setText(null);
	txtForBairro.setText(null);
	txtForCidade.setText(null);
	cboForUF.setSelectedItem(null);
	txtForId.setText(null);
	
}
}
