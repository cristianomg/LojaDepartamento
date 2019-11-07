package Model.Entites;
import java.util.ArrayList;
import java.util.List;
public class ProdutoEletronico extends Produto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300711613510005988L;
	private List<ProdutoEletronico> listaSimilar = new ArrayList<ProdutoEletronico>();
	private ProdutoEletronico produtoMarca;

	public ProdutoEletronico(int id, String nome, String descricao,
			float preco, int quantidade,Departamento departamento) {
		
		super(id, nome, descricao, preco, quantidade, departamento);
		this.produtoMarca = null;
	}

	
	public ProdutoEletronico(int id, String nome, String descricao, float preco, int quantidade,
			Departamento departamento, ArrayList<ProdutoEletronico> listaSimilar, ProdutoEletronico produtoMarca) {
		super(id, nome, descricao, preco, quantidade, departamento);
		this.listaSimilar = listaSimilar;
		this.produtoMarca = produtoMarca;
	}


	public void cadastrarSimilar(ProdutoEletronico produto) {
		if(produto.getProdutoMarca().getId() == this.id) {
			this.listaSimilar.add(produto);
		}
	}
	
	public void excluirSmiliar(ProdutoEletronico produto) {
		this.listaSimilar.remove(produto);
		produto.setProdutoMarca(null);
	}
	@Override
	public String toString() {
		return this.nome;
	}


	public boolean ehProdutoMarca() {
		if(this.produtoMarca == null) {
			return true;
		}
		else {
			return false;
		}
	}
	//duvida
	public boolean ehProdutoSimilar() {
		return produtoMarca != null;
	}


	public List<ProdutoEletronico> getListaSimilar() {
		return listaSimilar;
	}


	public void setListaSimilar(ArrayList<ProdutoEletronico> listaSimilar) {
		this.listaSimilar = listaSimilar;
	}


	public ProdutoEletronico getProdutoMarca() {
		return produtoMarca;
	}

	public void setProdutoMarca(ProdutoEletronico produtoMarca) {
		this.produtoMarca = produtoMarca;
	}

}