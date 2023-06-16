package boundary;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import dataBase.DataBase;

public class FormPrincipal extends Base {

    private DefaultListModel<DataBase> listaModel;

    public FormPrincipal() {
        super("CONTROLE DE ESTOQUE");
        try {

            listaModel = new DefaultListModel<>();
            ArrayList<DataBase> produto = DataBase.listar();
            JList<DataBase> listaProdutos = new JList<>(listaModel);

            JLabel labelNome = new JLabel("Nome:");
            JTextField textFieldNome = new JTextField(20);

            for (int i = 0; i < produto.size(); i++) {
                DataBase p = produto.get(i);
                listaModel.addElement(p);
            }
            JScrollPane scrollPane = new JScrollPane(listaProdutos);

            JButton buttonAdicionar = new JButton("Pesquisar");
            buttonAdicionar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String textoPesquisa = textFieldNome.getText();
                    listaModel.clear(); // Limpa a lista de produtos antes de realizar a pesquisa

                    for (int i = 0; i < produto.size(); i++) {
                        DataBase p = produto.get(i);
                        if (p.getNmProduto().toLowerCase().contains(textoPesquisa.toLowerCase())) {
                            listaModel.addElement(p);
                        }
                    }
                }
            });

            JButton buttonRemover = new JButton("Remover");
            buttonRemover.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int index = listaProdutos.getSelectedIndex();
                    if (index != -1) {
                        DataBase produto = listaModel.getElementAt(index);
                        try {
                            produto.delete(); // Exclui os dados do banco de dados
                            listaModel.remove(index); // Remove o item da lista
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao excluir o produto: " + ex.getMessage());
                        }
                    }
                }
            });

            JButton btnCriar = new JButton("Novo Produto");
            btnCriar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new FormProduto();
                }
            });

            JPanel panelSuperior = new JPanel(new FlowLayout());
            panelSuperior.add(labelNome);
            panelSuperior.add(textFieldNome);
            panelSuperior.add(buttonAdicionar);

            JPanel pnlinferior = new JPanel(new FlowLayout());
            pnlinferior.add(btnCriar);
            pnlinferior.add(buttonRemover);

            JPanel panelPrincipal = (JPanel) this.getContentPane();
            panelPrincipal.setLayout(new BorderLayout());
            panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
            panelPrincipal.add(scrollPane, BorderLayout.CENTER);
            panelPrincipal.add(pnlinferior, BorderLayout.SOUTH);

            this.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erros" + e);
        }
    }
}
