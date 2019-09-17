package Controller;

import java.util.HashMap;

import Exceptions.DepartamentoNaoEncontradoException;
import Exceptions.FuncionarioNaoEncontradoException;
import Exceptions.ProdutoNaoEncontradoException;
import Model.DAO.DepartamentoDAO;
import Model.DAO.FuncionarioDAO;
import Model.DAO.ProdutoDAO;
import Model.Entites.Departamento;
import Model.Entites.Funcionario;
import Model.Entites.Produto;
import View.MovimentacaoView;

public class ControllerMovimentacoes {
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	
	public static void comprarProduto(){
		HashMap<String, Integer> request = MovimentacaoView.dadosComprarProduto();
		try {
			Produto produto = produtos.getProduto(request.get("idProduto"));
			produto.addQuantidade(request.get("quantidade")); // pedir pra view a quantidade
			produtos.save();
			System.out.println("Compra realizada!!!");
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void moverProdutoDepartamento() {
		Produto produto;
		HashMap<String, Integer> request = MovimentacaoView.dadosMoverProduto();
		try {
			produto = produtos.getProduto(request.get("idProduto"));
			Departamento departamento = departamentos.getDepartamento(request.get("idDepartamento"));
			produto.setDepartamento(departamento);
			for (Produto p: produto.getListaSimilar()) {
				p.setDepartamento(departamento);
			}
			produtos.save();
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
		HashMap<String, String> request = MovimentacaoView.dadosMoverFuncionario();
		String matricula = request.get("matricula");
		int idDepartamento = Integer.parseInt(request.get("idMatricula"));
		try {
			funcionario = funcionarios.getFuncionario(matricula); // pedir pra view qual o FUNCIONARIO
			Departamento departamento = departamentos.getDepartamento(idDepartamento); // pedir pra view o departamento
			funcionario.setDepartamento(departamento);
			funcionarios.save();
			System.out.println("Produto movido de departamento.");
		} catch (FuncionarioNaoEncontradoException e) {
			System.out.println(e);
		}
		catch (DepartamentoNaoEncontradoException e1) {
			System.out.println(e1);
		}

	}
}
