package view;


import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import javax.swing.ImageIcon;

public class Relatorios extends JDialog {

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
			Relatorios dialog = new Relatorios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Relatorios() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnRelFornecedores = new JButton("");
		btnRelFornecedores.setIcon(new ImageIcon(Relatorios.class.getResource("/img/icone fornecedores.png")));
		btnRelFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioFornecedores();
			}
		});
		btnRelFornecedores.setBounds(167, 19, 99, 96);
		contentPanel.add(btnRelFornecedores);
		
		////////////////////////////////////////////////
		
		JButton btnRelReposicao = new JButton("");
		btnRelReposicao.setIcon(new ImageIcon(Relatorios.class.getResource("/img/icone reposição estoque.png")));
		btnRelReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioReposicao();
			}
		});
		btnRelReposicao.setBounds(167, 145, 99, 95);
		contentPanel.add(btnRelReposicao);
		
		///////////////////////////////////////////////
		
		JButton btnRelInventario = new JButton("");
		btnRelInventario.setIcon(new ImageIcon(Relatorios.class.getResource("/img/icone marketing.png")));
		btnRelInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioInventario(); 
			}
			
		});
		btnRelInventario.setBounds(314, 19, 99, 96);
		contentPanel.add(btnRelInventario);
		
		///////////////////////////////////////////////
		
		JButton btnRelClientes = new JButton("");
		btnRelClientes.setIcon(new ImageIcon(Relatorios.class.getResource("/img/icone clientes.png")));
		btnRelClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnRelClientes.setBounds(20, 20, 99, 95);
		contentPanel.add(btnRelClientes);
		
		///////////////////////////////////////////////
		
		JButton btnRelVencidos = new JButton("");
		btnRelVencidos.setIcon(new ImageIcon(Relatorios.class.getResource("/img/icone produtos vencidos.png")));
		btnRelVencidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioVencidos();
			}
		});
		btnRelVencidos.setBounds(20, 145, 99, 95);
		contentPanel.add(btnRelVencidos);
		
		///////////////////////////////////////////////
		
		JButton btnRelMarketing = new JButton("");
		btnRelMarketing.setIcon(new ImageIcon(Relatorios.class.getResource("/img/icone marketying.png")));
		btnRelMarketing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioMarketing();
			}
		});
		btnRelMarketing.setBounds(314, 145, 99, 95);
		contentPanel.add(btnRelMarketing);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	//Fim do construtor 
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	DAO dao = new DAO();
	
	
	
	
	
	//método responsável pela impressão do relatório de clientes
		private void relatorioClientes() {
			//criar objeto para construir a página pdf
			Document document = new Document();
			//gerar o documento pdf
			try {
				//cria um documento pdf em branco de nome clientes.pdf
				PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
				document.open();
				//gerar o conteúdo do documento
				Date data = new Date();			
		        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				document.add(new Paragraph(new Paragraph(formatador.format(data))));
				document.add(new Paragraph(" "));
				document.add(new Paragraph("Clientes cadastrados"));
				document.add(new Paragraph(" "));
				//... Demais conteúdos (imagem, tabela, gráfico, etc)
				PdfPTable tabela = new PdfPTable(4);
				PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
				PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				// Acessar o banco de dados
				 
				document.add(tabela);
			} catch (Exception e) {
				System.out.println(e);
			} finally { //executa o código independente do resultado OK ou não
				document.close();
			}
			
			//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
			try {
				Desktop.getDesktop().open(new File("clientes.pdf"));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		
	//////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		//método responsável pela impressão do relatório de Fornecedores
				private void relatorioFornecedores() {
					//criar objeto para construir a página pdf
					Document document = new Document();
					//gerar o documento pdf
					try {
						//cria um documento pdf em branco de nome clientes.pdf
						PdfWriter.getInstance(document, new FileOutputStream("fornecedores.pdf"));
						document.open();
						//gerar o conteúdo do documento
						Date data = new Date();			
				        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
						document.add(new Paragraph(new Paragraph(formatador.format(data))));
						document.add(new Paragraph(" "));
						document.add(new Paragraph("Fornecedores cadastrados"));
						document.add(new Paragraph(" "));
						//... Demais conteúdos (imagem, tabela, gráfico, etc)
						PdfPTable tabela = new PdfPTable(4);
						PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
						PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
						PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
						PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
						tabela.addCell(col1);
						tabela.addCell(col2);
						tabela.addCell(col3);
						tabela.addCell(col4);
						// Acessar o banco de dados
						 
						document.add(tabela);
					} catch (Exception e) {
						System.out.println(e);
					} finally { //executa o código independente do resultado OK ou não
						document.close();
					}
					
					//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
					try {
						Desktop.getDesktop().open(new File("Fornecedores.pdf"));
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				
				
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				

				//método responsável pela impressão do relatório Inventario
						private void relatorioInventario() {
							//criar objeto para construir a página pdf
							Document document = new Document();
							//gerar o documento pdf
							try {
								//cria um documento pdf em branco de nome clientes.pdf
								PdfWriter.getInstance(document, new FileOutputStream("Inventario.pdf"));
								document.open();
								//gerar o conteúdo do documento
								Date data = new Date();			
						        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
								document.add(new Paragraph(new Paragraph(formatador.format(data))));
								document.add(new Paragraph(" "));
								document.add(new Paragraph("Inventario cadastrados"));
								document.add(new Paragraph(" "));
								//... Demais conteúdos (imagem, tabela, gráfico, etc)
								PdfPTable tabela = new PdfPTable(4);
								PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
								PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
								PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
								PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
								tabela.addCell(col1);
								tabela.addCell(col2);
								tabela.addCell(col3);
								tabela.addCell(col4);
								// Acessar o banco de dados
								 
								document.add(tabela);
							} catch (Exception e) {
								System.out.println(e);
							} finally { //executa o código independente do resultado OK ou não
								document.close();
							}
							
							//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
							try {
								Desktop.getDesktop().open(new File("Inventario.pdf"));
							} catch (Exception e) {
								System.out.println(e);
							}
						}
						
				
						
						
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						
						
						
						
						//método responsável pela impressão do relatório Produtos Vencidos 
						private void relatorioVencidos() {
							//criar objeto para construir a página pdf
							Document document = new Document();
							//gerar o documento pdf
							try {
								//cria um documento pdf em branco de nome clientes.pdf
								PdfWriter.getInstance(document, new FileOutputStream("Vencidos.pdf"));
								document.open();
								//gerar o conteúdo do documento
								Date data = new Date();			
						        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
								document.add(new Paragraph(new Paragraph(formatador.format(data))));
								document.add(new Paragraph(" "));
								document.add(new Paragraph("Produtos Vencidos cadastrados"));
								document.add(new Paragraph(" "));
								//... Demais conteúdos (imagem, tabela, gráfico, etc)
								PdfPTable tabela = new PdfPTable(4);
								PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
								PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
								PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
								PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
								tabela.addCell(col1);
								tabela.addCell(col2);
								tabela.addCell(col3);
								tabela.addCell(col4);
								// Acessar o banco de dados
								 
								document.add(tabela);
							} catch (Exception e) {
								System.out.println(e);
							} finally { //executa o código independente do resultado OK ou não
								document.close();
							}
							
							//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
							try {
								Desktop.getDesktop().open(new File("Vencidos.pdf"));
							} catch (Exception e) {
								System.out.println(e);
							}
						}
						
						
						
						
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
		
						
						
						//método responsável pela impressão do relatório Marketing
						private void relatorioMarketing() {
							//criar objeto para construir a página pdf
							Document document = new Document();
							//gerar o documento pdf
							try {
								//cria um documento pdf em branco de nome clientes.pdf
								PdfWriter.getInstance(document, new FileOutputStream("Marketing.pdf"));
								document.open();
								//gerar o conteúdo do documento
								Date data = new Date();			
						        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
								document.add(new Paragraph(new Paragraph(formatador.format(data))));
								document.add(new Paragraph(" "));
								document.add(new Paragraph(" Marketing"));
								document.add(new Paragraph(" "));
								//... Demais conteúdos (imagem, tabela, gráfico, etc)
								PdfPTable tabela = new PdfPTable(4);
								PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
								PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
								PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
								PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
								tabela.addCell(col1);
								tabela.addCell(col2);
								tabela.addCell(col3);
								tabela.addCell(col4);
								// Acessar o banco de dados
								 
								document.add(tabela);
							} catch (Exception e) {
								System.out.println(e);
							} finally { //executa o código independente do resultado OK ou não
								document.close();
							}
							
							//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
							try {
								Desktop.getDesktop().open(new File("Marketing.pdf"));
							} catch (Exception e) {
								System.out.println(e);
							}
						}
						
						
						
						
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
						

						
						//método responsável pela impressão do relatório Reposição de estoque 
						private void relatorioReposicao() {
							//criar objeto para construir a página pdf
							Document document = new Document();
							//gerar o documento pdf
							try {
								//cria um documento pdf em branco de nome clientes.pdf
								PdfWriter.getInstance(document, new FileOutputStream("Reposicao.pdf"));
								document.open();
								//gerar o conteúdo do documento
								Date data = new Date();			
						        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
								document.add(new Paragraph(new Paragraph(formatador.format(data))));
								document.add(new Paragraph(" "));
								document.add(new Paragraph("Resposição de Estoques cadastrados"));
								document.add(new Paragraph(" "));
								//... Demais conteúdos (imagem, tabela, gráfico, etc)
								PdfPTable tabela = new PdfPTable(4);
								PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
								PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
								PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
								PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
								tabela.addCell(col1);
								tabela.addCell(col2);
								tabela.addCell(col3);
								tabela.addCell(col4);
								// Acessar o banco de dados
								 
								document.add(tabela);
							} catch (Exception e) {
								System.out.println(e);
							} finally { //executa o código independente do resultado OK ou não
								document.close();
							}
							
							//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
							try {
								Desktop.getDesktop().open(new File("Reposicao.pdf"));
							} catch (Exception e) {
								System.out.println(e);
							}
						}
		
		
}
