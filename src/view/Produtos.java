package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Produtos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtBarcode;
	private JTextField txtCodigo;
	private JTextField txtPesquisarFor;
	private JTextField txtProduto;
	private JTable tblProduto;
	private JTextField txtIdFor;
	private JTextField txtDescricao;
	private JTextField txtFabricante;
	private JTextField txtLocal;
	private JTextField txtEstoqueMin;
	private JTextField txtEstoque;
	private JTextField txtCusto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
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
	public Produtos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtBarcode.requestFocus();
			}
		});
		setModal(true);
		setTitle("Produtos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("/img/favicon.png")));
		setBounds(100, 100, 669, 424);
		getContentPane().setLayout(null);

		txtBarcode = new JTextField();
		txtBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				pesquisarProdutoCodigoBarras();
			}
		});
		txtBarcode.setBounds(94, 23, 202, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		lblNewLabel.setBounds(20, 11, 64, 45);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Código");
		lblNewLabel_1.setBounds(20, 66, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Produto");
		lblNewLabel_3.setBounds(20, 97, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(94, 63, 103, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);

		txtProduto = new JTextField();
		txtProduto.setBounds(94, 94, 202, 20);
		getContentPane().add(txtProduto);
		txtProduto.setColumns(10);

		JButton btnPesquisarFor = new JButton("Pesquisar");
		btnPesquisarFor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				pesquisarProdutoCodigo();
			}
		});
		btnPesquisarFor.setBounds(207, 62, 99, 23);
		getContentPane().add(btnPesquisarFor);

		JPanel panel = new JPanel();
		panel.setBounds(316, 11, 329, 193);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("ID");
		lblNewLabel_5.setBounds(201, 43, 20, 14);
		panel.add(lblNewLabel_5);

		txtIdFor = new JTextField();
		txtIdFor.setBounds(221, 40, 86, 20);
		panel.add(txtIdFor);
		txtIdFor.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(161, 29, 30, 36);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(Produtos.class.getResource("/img/01-magnifying-glass-1-16.png")));

		txtPesquisarFor = new JTextField();
		txtPesquisarFor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				pesquisarFornecedorTabela();
				
			}
			
		
			
		});
		txtPesquisarFor.setBounds(15, 40, 136, 20);
		panel.add(txtPesquisarFor);
		txtPesquisarFor.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fornecedor");
		lblNewLabel_2.setBounds(15, 11, 69, 14);
		panel.add(lblNewLabel_2);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(94, 132, 202, 72);
		getContentPane().add(txtDescricao);
		txtDescricao.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Observação");
		lblNewLabel_6.setBounds(20, 132, 75, 20);
		getContentPane().add(lblNewLabel_6);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirPoodutos();
			}
		});
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxdel.png")));
		btnExcluir.setBounds(559, 313, 86, 61);
		getContentPane().add(btnExcluir);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProdutos();
			}
		});
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxadd.png")));
		btnAdicionar.setBounds(383, 313, 75, 61);
		getContentPane().add(btnAdicionar);

		btnAtualizar = new JButton("");
		btnAtualizar.setEnabled(false);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarProdutos();
			}
		});
		btnAtualizar.setContentAreaFilled(false);
		btnAtualizar.setBorderPainted(false);
		btnAtualizar.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxupdate.png")));
		btnAtualizar.setBounds(468, 313, 81, 61);
		getContentPane().add(btnAtualizar);

		txtFabricante = new JTextField();
		txtFabricante.setBounds(94, 226, 202, 20);
		getContentPane().add(txtFabricante);
		txtFabricante.setColumns(10);

		JLabel Fabricante = new JLabel("Fabricante");
		Fabricante.setBounds(20, 232, 75, 14);
		getContentPane().add(Fabricante);

		JLabel txtTamanho = new JLabel("Tamanho");
		txtTamanho.setBounds(20, 313, 64, 14);
		getContentPane().add(txtTamanho);

		txtLocal = new JTextField();
		txtLocal.setBounds(210, 313, 86, 20);
		getContentPane().add(txtLocal);
		txtLocal.setColumns(10);

		txtEstoqueMin = new JTextField();
		txtEstoqueMin.setBounds(243, 265, 53, 20);
		getContentPane().add(txtEstoqueMin);
		txtEstoqueMin.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Estoque");
		lblNewLabel_8.setBounds(20, 268, 46, 14);
		getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Estoque minimo");
		lblNewLabel_9.setBounds(157, 268, 75, 14);
		getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Local");
		lblNewLabel_10.setBounds(173, 313, 33, 14);
		getContentPane().add(lblNewLabel_10);

		txtEstoque = new JTextField();
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(94, 265, 53, 20);
		getContentPane().add(txtEstoque);

		cboTamanho = new JComboBox();
		cboTamanho.setModel(new DefaultComboBoxModel(new String[] {"", "PP", "P", "M", "G", "GG"}));
		cboTamanho.setBounds(94, 313, 53, 22);
		getContentPane().add(cboTamanho);

		JLabel lblNewLabel_11 = new JLabel("Entrada");
		lblNewLabel_11.setBounds(316, 229, 46, 14);
		getContentPane().add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Custo");
		lblNewLabel_12.setBounds(316, 268, 46, 14);
		getContentPane().add(lblNewLabel_12);

		JLabel lblNewLabel_14 = new JLabel("Lucro%");
		lblNewLabel_14.setBounds(484, 268, 46, 14);
		getContentPane().add(lblNewLabel_14);

		txtCusto = new JTextField();
		txtCusto.setBounds(372, 265, 86, 20);
		getContentPane().add(txtCusto);
		txtCusto.setColumns(10);

		txtLucro = new JTextField();
		txtLucro.setBounds(540, 265, 86, 20);
		getContentPane().add(txtLucro);
		txtLucro.setColumns(10);

		dateEntrada = new JDateChooser();
		dateEntrada.setBounds(372, 226, 252, 20);
		getContentPane().add(dateEntrada);
		
		JButton btnAdicionar_1 = new JButton("");
		btnAdicionar_1.setEnabled(false);
		btnAdicionar_1.setContentAreaFilled(false);
		btnAdicionar_1.setBorderPainted(false);
		btnAdicionar_1.setBounds(474, 313, 75, 61);
		getContentPane().add(btnAdicionar_1);
		
		
		
		
		// Validação com o uso da biblioteca Atxy2k
		
		RestrictedTextField validarCodigo = new RestrictedTextField(txtCodigo);
		validarCodigo.setLimit(4);
		validarCodigo.setOnlyNums(true);
		
		RestrictedTextField validarBarcode = new RestrictedTextField(txtBarcode);
		validarBarcode.setLimit(15);
		validarBarcode.setOnlyNums(true);
		
		RestrictedTextField validarProduto = new RestrictedTextField(txtProduto);
		validarProduto.setLimit(50);
		
		
		RestrictedTextField validarDescricao = new RestrictedTextField(txtDescricao);
		validarDescricao.setLimit(30);
		
		
		RestrictedTextField validarFabricante = new RestrictedTextField(txtFabricante);
		validarFabricante .setLimit(30);
		
		
		RestrictedTextField validarEstoque = new RestrictedTextField(txtEstoque);
		validarEstoque.setLimit(5);
		validarEstoque.setOnlyNums(true);
		
		RestrictedTextField validarEstoqueMin = new RestrictedTextField(txtEstoqueMin);
		validarEstoqueMin.setLimit(5);
		validarEstoqueMin.setOnlyNums(true);
		
		RestrictedTextField validarLocal = new RestrictedTextField(txtLocal);
		validarLocal.setLimit(10);
		
		
		RestrictedTextField validarCusto = new RestrictedTextField(txtCusto);
		validarCusto.setLimit(9);
		validarCusto.setOnlyNums(true);
		
		RestrictedTextField validarLucro = new RestrictedTextField(txtLucro);
		validarLucro.setLimit(9);
		validarLucro.setOnlyNums(true);
		
		RestrictedTextField validarIdFor = new RestrictedTextField(txtIdFor);
		
				JScrollPane btnTabela = new JScrollPane();
				btnTabela.setBounds(14, 76, 293, 93);
				panel.add(btnTabela);
				
						tblProduto = new JTable();
						tblProduto.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {

								setarCaixasTexto();
							}
						});
						btnTabela.setViewportView(tblProduto);
		validarIdFor.setLimit(4);
		validarIdFor.setOnlyNums(true);
		
		
		
		
		
		

	}
	// Fim do contrutor

	// criar objeto de acessar banco de dados
	DAO dao = new DAO();
	private JButton btnPesquisar;
	private JTextField txtLucro;
	private JComboBox cboTamanho;
	private JDateChooser dateEntrada;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnAtualizar;

	// pesquisar protudo por codigo
	private void pesquisarProdutoCodigo() {
		String read = "select * from produtos where codigo = ?";
		try {
			// Estabelecer a conex�o
			Connection con = dao.conectar();
			// Preparar a execu��o da query
			PreparedStatement pst = con.prepareStatement(read);
			// Setar o argumento (fantasia)
			// Substituir o ? pelo conte�do da caixa de texto
			pst.setString(1, txtCodigo.getText());
			ResultSet rs = pst.executeQuery();
			limparCampos();
			if (rs.next()) {

				// preencher(setar) os campos do formulario
				txtCodigo.setText(rs.getString(1));
				txtBarcode.setText(rs.getString(2));
				txtProduto.setText(rs.getString(3));
				txtDescricao.setText(rs.getString(4));
				txtFabricante.setText(rs.getString(5));
				String setarDataEntrada = rs.getString(6);
				System.out.println(setarDataEntrada);
				Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataEntrada);
				dateEntrada.setDate(dataVal);
				txtEstoque.setText(rs.getString(8));
				txtEstoqueMin.setText(rs.getString(9));
				cboTamanho.setSelectedItem(rs.getString(10));
				txtLocal.setText(rs.getString(11));
				txtCusto.setText(rs.getString(12));
				txtLucro.setText(rs.getString(13));
				txtIdFor.setText(rs.getString(14));
				// JCaledar para inserção correta na Jtext
				
				
				btnAtualizar.setEnabled(true);
				btnExcluir.setEnabled(true);

			}

			else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");
				btnAdicionar.setEnabled(true);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// pesquisar protudo por codigo de barras
	private void pesquisarProdutoCodigoBarras() {
		String read2 = "select * from produtos where barcode = ?";
		try {
			// Estabelecer a conex�o
			Connection con = dao.conectar();
			// Preparar a execu��o da query
			PreparedStatement pst = con.prepareStatement(read2);
			// Setar o argumento (fantasia)
			// Substituir o ? pelo conte�do da caixa de texto
			pst.setString(1, txtBarcode.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				// preencher(setar) os campos do formulario
				txtCodigo.setText(rs.getString(1));
				txtBarcode.setText(rs.getString(2));
				txtProduto.setText(rs.getString(3));
				txtDescricao.setText(rs.getString(4));
				txtFabricante.setText(rs.getString(5));
				// txtDatacad.setText(rs.getString(6));
				// txtDataval.setText(rs.getString(7));
				txtEstoque.setText(rs.getString(8));
				txtEstoqueMin.setText(rs.getString(9));
				cboTamanho.setSelectedItem(rs.getString(10));
				txtLocal.setText(rs.getString(11));
				txtCusto.setText(rs.getString(12));
				txtLucro.setText(rs.getString(13));
				txtIdFor.setText(rs.getString(14));

				// JCaledar para inserção correta na Jtext

				btnAtualizar.setEnabled(true);
				btnExcluir.setEnabled(true);


			} else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");
				btnAdicionar.setEnabled(true);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// criar setar caixas
	private void setarCaixasTexto() {
		// criar uma vari�vel para receber a linha da tabela
		int setar = tblProduto.getSelectedRow();
		txtIdFor.setText(tblProduto.getModel().getValueAt(setar, 0).toString());
		// txtForFantasia.setText(tblFornecedores.getModel().getValueAt(setar,
		// 1).toString());
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
			pst.setString(1, txtPesquisarFor.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			tblProduto.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	
	

	// Adicionar produtos
	private void adicionarProdutos() {

		if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Produto");
			txtProduto.requestFocus();
		} 
		else if (txtDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a Descrição do Produto ");
			txtDescricao.requestFocus();
		} 
		else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Fabricante do porduto");
			txtFabricante.requestFocus();
		}
		else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Estoque do produto");
			txtEstoque.requestFocus();
		}
		else if (txtEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Estoque minimo do produto");
			txtEstoqueMin.requestFocus();
		}
		else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Custo do produto");
			txtCusto.requestFocus();
		} else if (txtIdFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o ID do produto");
			txtIdFor.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into produtos(barcode,produto,descricao,fabricante,estoque,estoquemin,unidade,localizacao,custo,lucro,idfor) values (?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de textor
				

				pst.setString(1, txtBarcode.getText());
				pst.setString(2, txtProduto.getText());
				pst.setString(3, txtDescricao.getText());
				pst.setString(4, txtFabricante.getText());
				
				pst.setString(5, txtEstoque.getText());
				pst.setString(6, txtEstoqueMin.getText());
				pst.setString(7, cboTamanho.getSelectedItem().toString());
				pst.setString(8, txtLocal.getText());
				pst.setString(9, txtCusto.getText());
				pst.setString(10, txtLucro.getText());
				pst.setString(11, txtIdFor.getText());

				// Executar a query e inserir o fornecedor no banco
				pst.executeUpdate();

				// confirma��o
				JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
				
				txtCodigo.setText(null);
				limparCampos();
				limparCamposFornecedor();
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Barcode ja cadastrado.\nDigite outro Produto");
				txtBarcode.setText(null);
				txtBarcode.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	
	
	
	//alterar Fornecedores
	private void alterarProdutos() {
		// valida��o
		if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Produto");
			txtProduto.requestFocus();
		} else if (txtDescricao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Descrição");
			txtDescricao.requestFocus();
		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Fabricante");
			txtFabricante.requestFocus();
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Estoque");
			txtEstoque.requestFocus();
		} else if (txtEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba EstoqueMin");
			txtEstoqueMin.requestFocus();
		} else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Custo");
			txtCusto.requestFocus();
		} else if (txtIdFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a aba Id");
			txtIdFor.requestFocus();
		
			
			
		} else {
			// l�gica principal
			String update = "update produtos set barcode=?,produto=?,descricao=?,fabricante=?,datacad=?,estoque=?,estoquemin=?,unidade=?,localizacao=?,custo=?,lucro=?,idfor=? where codigo=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir os ????? pelo conte�do das caixas de texto
				
				pst.setString(1, txtBarcode.getText());
				pst.setString(2, txtProduto.getText());
				pst.setString(3, txtDescricao.getText());
				pst.setString(4, txtFabricante.getText());
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataEntrada = formatador.format(dateEntrada.getDate());
				pst.setString(5, dataEntrada);
				pst.setString(6, txtEstoque.getText());
				pst.setString(7, txtEstoqueMin.getText());
				pst.setString(8, cboTamanho.getSelectedItem().toString());
				pst.setString(9, txtLocal.getText());
				pst.setString(10, txtCusto.getText());
				pst.setString(11, txtLucro.getText());
				pst.setString(12, txtIdFor.getText());
				pst.setString(13, txtCodigo.getText());
				
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				//corfimação
				//limpar cmapos produtos
				JOptionPane.showMessageDialog(null, "Dados do Produtos alterados com sucesso");
				// Encerrar a conex�o
				limparCampos();
				limparCamposFornecedor();
				txtCodigo.setText(null);
				
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Barcode existente.\\nDigite outro");
				txtBarcode.setText(null);
				txtBarcode.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	}
	
	
	
	
	
	
	private void excluirPoodutos() {
		// valida��o (confirma��o de exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclus�o do usu�rio?", "Aten��o!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// l�gica principal
			String delete = "delete from produtos where codigo=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a query(comando sql) substituindo a ? pelo idusu
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtCodigo.getText());
				// Executar a query
				pst.executeUpdate();
				// confima��o
				
				
				JOptionPane.showMessageDialog(null, "Produto exclu�do com sucesso.");
				// encerrar a conex�o
				limparCampos();
				limparCamposFornecedor();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
	}
	
	private void limparCamposFornecedor() {
		//Limpar Campos
		((DefaultTableModel) tblProduto.getModel()).setRowCount(0);
	}
	

	// limparCampos();
	private void limparCampos() {
		txtBarcode.setText(null);
		txtProduto.setText(null);
		txtDescricao.setText(null);
		txtFabricante.setText(null);
		dateEntrada.setDate(null);
		txtEstoque.setText(null);
		txtEstoqueMin.setText(null);
		cboTamanho.setSelectedItem(null);
		txtLocal.setText(null);
		txtCusto.setText(null);
		txtLucro.setText(null);
		txtIdFor.setText(null);
		btnAdicionar.setEnabled(false);
		btnAtualizar.setEnabled(false);
		btnExcluir.setEnabled(false);
        txtPesquisarFor.setText(null);
	}
}
