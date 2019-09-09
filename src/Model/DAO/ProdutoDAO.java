package Model.DAO;

import java.util.ArrayList;
import java.util.List;

import Model.Entites.Produto;

public class ProdutoDAO implements InterfaceDAO<Produto> {
	private static ProdutoDAO uniqueInstance;
	private List<Produto> listaProdutos = new ArrayList<Produto>();
	
	private ProdutoDAO(){
	}
	
	public static synchronized ProdutoDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new ProdutoDAO();
		}
		return uniqueInstance;
	}

	@Override
	public List<Produto> getLista() {
		return listaProdutos;
	}

	@Override
	public boolean inserir(Produto produto) {
		listaProdutos.add(produto);
		return true;
	}

	@Override
	public boolean deletar(Produto produto) {
		try {
			listaProdutos.remove(produto);
			return true;
		}
		catch (Exception e){
			e.getStackTrace();
			return false;
		}
	}

	@Override
	public boolean atualizar(Produto objetoAntigo, Produto novoObjeto) {
		try {
			listaProdutos.remove(objetoAntigo);
			listaProdutos.add(novoObjeto);
			return true;
		}
		catch (Exception e) {
			e.getStackTrace();
			return false;
		}
	}
	
	public List<Produto> getProdutoDepartamento(int idDepartamento) {
		List<Produto> produtosDepartamento = new ArrayList<Produto>();
		for (Produto p: listaProdutos) {
			if (p.getDepartamento().getId()== idDepartamento) {
				produtosDepartamento.add(p);
			}
		}
		return produtosDepartamento;
	}
}
