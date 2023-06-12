package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DataBase {

	
	private String nmProduto,tpUnidade;
	private float valor, quant;
	
	/*public DataBase(String nmProduto) {
		this.nmProduto = nmProduto;
		//this.tpUnidade = tpUnidade;
		//this.valor = valor;
		//this.quant = quant;
	}
	*/
	
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
	
	public void inserir() throws Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String strcon = "jdbc:mysql://localhost/estoque?user=root&password=root";
			Connection cx = DriverManager.getConnection(strcon);
			Statement st = cx.createStatement();
			String ins = "insert into produto values (0,'"+ this.nmProduto +"','"+this.tpUnidade+"',"+this.valor+","+this.quant+");";
			st.executeUpdate(ins);
			st.close();
			cx.close();
		} catch (Exception ex) {
			throw ex;
		}
	}
	public static ArrayList<DataBase> listar() throws Exception{
		try {
				ArrayList<DataBase> produto = new ArrayList<DataBase>();
				Class.forName("com.mysql.cj.jdbc.Driver");
				String strcon = "jdbc:mysql://localhost:3306/estoque?user=root&password=root";
				Connection cx = DriverManager.getConnection(strcon);
				Statement st = cx.createStatement();
				ResultSet rs = st.executeQuery("Select * from produto");
				float val = Integer.parseInt(rs.getString("valor"));
				float quan = Integer.parseInt(rs.getString("quantidade"));
				while(rs.next()) {
				produto.add(new DataBase(rs.getString("nome_produto"),rs.getString("tipo_unidade"),val,quan));
					
				}
				return produto;
		} catch (Exception e) {
			throw e;
		}
	}
}
