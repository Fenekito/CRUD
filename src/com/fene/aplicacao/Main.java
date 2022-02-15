package com.fene.aplicacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import com.fene.dao.PessoaDAO;
import com.fene.model.Pessoa;

public class Main {
	
	public static JFrame frame = new JFrame("Sistema de Cadastro");
	
	private static JPanel panel = new JPanel();
	private static JPanel panel2 = new JPanel();
	private static JPanel centerPanel = new JPanel();
	private static JLabel lblNome = new JLabel("Nome:");
	private static JLabel lblIdade = new JLabel("Idade:");
	private static JTextField txtNome = new JTextField(20);
	private static JTextField txtIdade = new JTextField(2);
	private static JButton btnCadastrar = new JButton("Cadastrar");
	private static JButton btnListar = new JButton("Listar");
	private static JButton btnRemover = new JButton("Remover");
	
	public static void main(String[] args) {
						
		panel.add(lblNome);
		panel.add(txtNome);
		panel.add(lblIdade);
		panel.add(txtIdade);
		panel.add(btnCadastrar);
		
		btnListar.setPreferredSize(new Dimension(100,40));
		btnRemover.setPreferredSize(new Dimension(100,40));
		
		panel2.add(btnListar);
		panel2.add(btnRemover);
		
		centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
		centerPanel.add(panel);
		centerPanel.add(panel2);
		
		frame.add(centerPanel, BorderLayout.CENTER);
		
		frame.setSize(640, 480);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		btnCadastrar.addActionListener(new ActionCadastrar());
		btnListar.addActionListener(new ActionListar());
		btnRemover.addActionListener(new ActionRemover());
		
	}
	
	static class ActionCadastrar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nome vázio, por favor insira um nome válido.", "AVISO!", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(txtNome.getText());
				pessoa.setIdade(Integer.parseInt(txtIdade.getText()));
				pessoa.setDataCadastro(new Date());
								
				PessoaDAO pessoaDao = new PessoaDAO();
				pessoaDao.save(pessoa);
				JOptionPane.showMessageDialog(frame, "Pessoa Cadastrada com Sucesso!", "Sucesso", JOptionPane.DEFAULT_OPTION);
				
				txtNome.setText("");
				txtIdade.setText("");
			} catch(NumberFormatException ee) {
				JOptionPane.showMessageDialog(frame, "Idade não inserida, por favor insira uma idade válida.", "AVISO!", JOptionPane.WARNING_MESSAGE);
			} catch(Exception ee) {
				JOptionPane.showMessageDialog(frame, "Falha ao realizar o Pedido, verificar conexão com o Servidor MySQL.", "ERRO!", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
	
	static class ActionListar implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			List<Object> pessoas = new ArrayList<Object>();
			
			try {
				for(Pessoa p :PessoaDAO.getContatos()) {
					pessoas.add(p.getNome() + " Idade:" + p.getIdade() + "\n");
				}
				JOptionPane.showMessageDialog(frame, pessoas, "Lista de Cadastros", JOptionPane.DEFAULT_OPTION);
			} catch(Exception ee) {
				JOptionPane.showMessageDialog(frame, "Falha ao realizar o Pedido, verificar conexão com o Servidor MySQL.", "ERRO!", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
	
	static class ActionRemover implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nome vázio, por favor insira um nome válido.", "AVISO!", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(txtNome.getText());
				pessoa.setIdade(Integer.parseInt(txtIdade.getText()));
								
				PessoaDAO pessoaDao = new PessoaDAO();
				pessoaDao.delete(pessoa);
				JOptionPane.showMessageDialog(frame, "Pessoa Removida com Sucesso!", "Sucesso", JOptionPane.DEFAULT_OPTION);
			} catch(NumberFormatException ee) {
				JOptionPane.showMessageDialog(frame, "Idade não inserida, por favor insira uma idade válida.", "AVISO!", JOptionPane.WARNING_MESSAGE);
			} catch(Exception ee) {
				JOptionPane.showMessageDialog(frame, "Falha ao realizar o Pedido, verificar conexão com o Servidor MySQL.", "ERRO!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
