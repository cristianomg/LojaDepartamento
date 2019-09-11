package Controller;

import Exceptions.ObjetoNaoEncontradoException;
import Model.DAO.DepartamentoDAO;
import Model.DAO.ProdutoDAO;
import Model.Entites.Departamento;
import Model.Entites.Produto;

public class ControllerMovimentacoes {
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	
	public static void comprarProduto(){
		try {
			Produto produto = produtos.getProduto(0); // pedir pra view qual o produto
			produto.addQuantidade(100); // pedir pra view a quantidade
			System.out.println("Compra realizada!!!");
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public static void moverProdutoDepartamento() {
		Produto produto;
		try {
			produto = produtos.getProduto(0); // pedir pra view qual o produto
			Departamento departamento = departamentos.getDepartamento(1); // pedir pra view o departamento
			produto.setDepartamento(departamento);
			System.out.println("Produto movido de departamento.");
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println("Erro");
		}

	}
}
