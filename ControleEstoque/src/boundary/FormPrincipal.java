package boundary;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import dataBase.DataBase;
public class FormPrincipal extends Base{

	private DefaultListModel<String> listaModel;
	public FormPrincipal() {
		super("CONTROLE DE ESTOQUE");
		
	       listaModel = new DefaultListModel<>();
	        JList<String> listaProdutos = new JList<>(listaModel);
	        JScrollPane scrollPane = new JScrollPane(listaProdutos);

	        JLabel labelNome = new JLabel("Nome:");
	        JTextField textFieldNome = new JTextField(20);

	        JLabel labelQuantidade = new JLabel("Quantidade:");
	        JTextField textFieldQuantidade = new JTextField(5);

	        JButton buttonAdicionar = new JButton("Adicionar");
	        buttonAdicionar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String nome = textFieldNome.getText();
	                String quantidade = textFieldQuantidade.getText();
	                String produto = nome + " - " + quantidade;
	                listaModel.addElement(produto);
//	                adicionarProduto(nome, quantidade);
	            }
	        });

	        JButton buttonRemover = new JButton("Remover");
	        buttonRemover.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int index = listaProdutos.getSelectedIndex();
	                if (index != -1) {
	                    listaModel.remove(index);
	                }
	            }
	        });
	        JButton btncriar = new JButton("Novo Produto");
	        btncriar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new FormProduto();
	        	}
	        });
	        
	        JPanel panelSuperior = new JPanel(new FlowLayout());
	        panelSuperior.add(labelNome);
	        panelSuperior.add(textFieldNome);
	        panelSuperior.add(labelQuantidade);
	        panelSuperior.add(textFieldQuantidade);
	        panelSuperior.add(buttonAdicionar);
	        panelSuperior.add(buttonRemover);
	        
	        JPanel pnlinferior = new JPanel(new FlowLayout());
	        pnlinferior.add(btncriar);
	        
	        JPanel panelPrincipal = (JPanel)this.getContentPane();
	        panelPrincipal.setLayout(new BorderLayout());
	        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
	        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
	        panelPrincipal.add(pnlinferior, BorderLayout.SOUTH);
		//this.pack();
		this.setVisible(true);

	}
}
