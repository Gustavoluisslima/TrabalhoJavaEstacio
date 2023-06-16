package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {
    private int id;
    private String nmProduto, tpUnidade;
    private float valor, quant;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmProduto() {
        return this.nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public String getTpUnidade() {
        return this.tpUnidade;
    }

    public void setTpUnidade(String tpUnidade) {
        this.tpUnidade = tpUnidade;
    }

    public float getValor() {
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getQuant() {
        return this.quant;
    }

    public void setQuant(float quant) {
        this.quant = quant;
    }

    public DataBase(String nmProduto, String tpUnidade, float valor, float quant) {
        this.nmProduto = nmProduto;
        this.tpUnidade = tpUnidade;
        this.valor = valor;
        this.quant = quant;
    }

    public DataBase() {
    }

    public void inserir() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String strcon = "jdbc:mysql://localhost/estoque?user=root&password=root";
            Connection cx = DriverManager.getConnection(strcon);
            Statement st = cx.createStatement();
            String ins = "insert into produto (nome_produto, tipo_unidade, valor, quantidade) values ('" + this.nmProduto + "','" + this.tpUnidade + "'," + this.valor + "," + this.quant + ");";
            st.executeUpdate(ins);
            st.close();
            cx.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static ArrayList<DataBase> listar() throws Exception {
        try {
            ArrayList<DataBase> produtos = new ArrayList<DataBase>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String strcon = "jdbc:mysql://localhost:3306/estoque?user=root&password=root";
            Connection cx = DriverManager.getConnection(strcon);
            Statement st = cx.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM produto");
            while (rs.next()) {
                DataBase produto = new DataBase();
                produto.setId(rs.getInt("id_produto"));
                produto.setNmProduto(rs.getString("nome_produto"));
                produto.setTpUnidade(rs.getString("tipo_unidade"));
                produto.setValor(rs.getFloat("valor"));
                produto.setQuant(rs.getFloat("quantidade"));
                produtos.add(produto);
            }
            st.close();
            cx.close();
            return produtos;
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String strcon = "jdbc:mysql://localhost/estoque?user=root&password=root";
            Connection cx = DriverManager.getConnection(strcon);
            Statement st = cx.createStatement();
            String del = "DELETE FROM produto WHERE id_produto = " + this.getId();
            st.executeUpdate(del);
            st.close();
            cx.close();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
