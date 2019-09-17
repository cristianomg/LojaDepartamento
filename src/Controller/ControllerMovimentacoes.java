package Controller;

import Exceptions.DepartamentoNaoEncontradoException;
import Exceptions.FuncionarioNaoEncontradoException;
import Exceptions.ProdutoNaoEncontradoException;
import Model.DAO.DepartamentoDAO;
import Model.DAO.FuncionarioDAO;
import Model.DAO.ProdutoDAO;
import Model.Entites.Departamento;
import Model.Entites.Funcionario;
import Model.Entites.Produto;

public class ControllerMovimentacoes {
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	
	public static void comprarProduto(){
		try {
			Produto produto = produtos.getProduto(0); // pedir pra view qual o produto
			produto.addQuantidade(100); // pedir pra view a quantidade
			System.out.println("Compra realizada!!!");
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void moverProdutoDepartamento() {
		Produto produto;
		try {
			produto = produtos.getProduto(0); // pedir pra view qual o produto
			Departamento departamento = departamentos.getDepartamento(1); // pedir pra view o departamento
			produto.setDepartamento(departamento);
			System.out.println("Produto movido de departamento.");
		}
		catch(DepartamentoNaoEncontradoException e1) {
			System.out.println(e1);
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e);
		}

	}
	public static void moverFuncionarioDepartamento() {
		Funcionario funcionario;
		try {
			funcionario = funcionarios.getFuncionario("323232"); // pedir pra view qual o FUNCIONARIO
			Departamento departamento = departamentos.getDepartamento(1); // pedir pra view o departamento
			funcionario.setDepartamento(departamento);
			System.out.println("Produto movido de departamento.");
		} catch (FuncionarioNaoEncontradoException e) {
			System.out.println(e);
		}
		catch (DepartamentoNaoEncontradoException e1) {
			System.out.println(e1);
		}

	}
}
