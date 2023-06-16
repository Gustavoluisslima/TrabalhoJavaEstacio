package boundary;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dataBase.DataBase;
public class FormProduto extends Base{

	public FormProduto() {
		super("Novo Produto");
		JLabel lblNome, lblUnidade, lblValor, lblQuant;
		JTextField txtNome, txtUnidade, txtValor, txtQuant;
		JButton btnSalvar;
		JPanel panel = (JPanel)this.getContentPane();
		panel.setLayout(new FlowLayout());
		
		lblNome = new JLabel("Nome do Produto");
		txtNome = new JTextField(10);
		
		lblUnidade = new JLabel("Tipo de Unidade");
		txtUnidade = new JTextField(10);
		
		lblValor = new JLabel("Valor");
		txtValor = new JTextField(10);
		
		lblQuant = new JLabel("Quantidade");
		txtQuant = new JTextField(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
					float valor = Integer.parseInt(txtValor.getText());
					float quant = Integer.parseInt(txtQuant.getText());
					
					DataBase produto = new DataBase(txtNome.getText(),txtUnidade.getText(), valor, quant);
					
					produto.inserir();
					
					}catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "valores inválidos"+ e2);
					}
					JOptionPane.showMessageDialog(null, "Produto criado com sucesso!");
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Erro ao gravar os dados."+ ex);
				}
			}
		});
		
		panel.add(lblNome);
		panel.add(txtNome);
		
		panel.add(lblUnidade);
		panel.add(txtUnidade);
		
		panel.add(lblValor);
		panel.add(txtValor);
		
		panel.add(lblQuant);
		panel.add(txtQuant);
		
		panel.add(btnSalvar);
		this.setVisible(true);
	}
}
