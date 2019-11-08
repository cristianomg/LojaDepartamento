package Model.Entites;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.ProdutoDAO;
public class ProdutoEletronico extends Produto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300711613510005988L;
	private ProdutoEletronico produtoMarca;

	public ProdutoEletronico(String nome, String descricao,
			float preco, int quantidade,Departamento departamento) {
		
		super(nome, descricao, preco, quantidade, departamento);
		this.produtoMarca = null;
	}

	
	public ProdutoEletronico(String nome, String descricao, float preco, int quantidade,
			Departamento departamento, ProdutoEletronico produtoMarca) {
		super(nome, descricao, preco, quantidade, departamento);
		this.produtoMarca = produtoMarca;
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

	public boolean ehProdutoSimilar() {
		return produtoMarca != null;
	}


	public List<ProdutoEletronico> getListaSimilar() {
		List<Produto> produtos = ProdutoDAO.getInstance().getLista();
		List<ProdutoEletronico> lista = new ArrayList<ProdutoEletronico>();
		if(this.ehProdutoMarca()) {
			produtos.stream().filter(x -> x instanceof ProdutoEletronico)
			.map(x -> (ProdutoEletronico) x).filter(x -> x.ehProdutoSimilar())
			.filter(x -> x.getProdutoMarca().equals(this)).forEach(x -> lista.add(x));
		}else {
			lista.add(getProdutoMarca());
			produtos.stream().filter(x -> x instanceof ProdutoEletronico)
			.map(x -> (ProdutoEletronico) x).filter(x -> x.ehProdutoSimilar())
			.filter(x -> x.getProdutoMarca().equals(this.produtoMarca))
			.filter(x -> x.getId() != this.id).forEach(x -> lista.add(x));
		}
		return lista;
	}

	public ProdutoEletronico getProdutoMarca() {
		return produtoMarca;
	}

	public void setProdutoMarca(ProdutoEletronico produtoMarca) {
		this.produtoMarca = produtoMarca;
	}

}