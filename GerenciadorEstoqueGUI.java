package gerenciador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GerenciadorEstoqueGUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> listaModel;
    private Connection conexao;

    public GerenciadorEstoqueGUI() {
        super("Gerenciador de Estoque");

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
                adicionarProduto(nome, quantidade);
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

        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.add(labelNome);
        panelSuperior.add(textFieldNome);
        panelSuperior.add(labelQuantidade);
        panelSuperior.add(textFieldQuantidade);
        panelSuperior.add(buttonAdicionar);
        panelSuperior.add(buttonRemover);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }

    public void adicionarProduto(String nome, String quantidade) {
        try {
            Statement stmt = conexao.createStatement();
            String query = "INSERT INTO produtos (nome, quantidade) VALUES ('" + nome + "', " + quantidade + ")";
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void conectarBancoDados() {
        String url = "jdbc:mysql://localhost:3306/nome_do_banco_de_dados";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GerenciadorEstoqueGUI gui = new GerenciadorEstoqueGUI();
                gui.conectarBancoDados();
                gui.setVisible(true);
            }
        });
    }
}
