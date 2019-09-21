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
import Model.Entites.ProdutoEletronico;

public class ControllerMovimentacoes {
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	
	public void comprarProduto(HashMap<String, Integer> request){
		try {
			Produto produto = produtos.getProduto(request.get("idProduto"));
			produto.addQuantidade(request.get("quantidade"));
			produtos.save();
			System.out.println("Compra realizada!!!");
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void moverProdutoDepartamento(HashMap<String, Integer> request) {
		Produto produto;
		try {
			produto = produtos.getProduto(request.get("idProduto"));
			Departamento departamento = departamentos.getDepartamento(request.get("idDepartamento"));
			if (produto.getDepartamento().getNome().equals("Eletronico")) {
				ProdutoEletronico produto1 = (ProdutoEletronico) produto;
				for (ProdutoEletronico p: produto1.getListaSimilar()) {
					p.setDepartamento(departamento);
				}
			}
			produto.setDepartamento(departamento);

			produtos.save();
			System.out.println("Produto movido de departamento.");
		}
		catch(DepartamentoNaoEncontradoException e1) {
			System.out.println(e1);
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e);
		}

	}
	public void moverFuncionarioDepartamento(HashMap<String, String> request) {
		Funcionario funcionario;
		String matricula = request.get("matricula");
		int idDepartamento = Integer.parseInt(request.get("idDepartamento"));
		try {
			funcionario = funcionarios.getFuncionario(matricula);
			Departamento departamento = departamentos.getDepartamento(idDepartamento);
			funcionario.setDepartamento(departamento);
			funcionarios.save();
			System.out.println("Funcionario movido de departamento.");
		} catch (FuncionarioNaoEncontradoException e) {
			System.out.println(e);
		}
		catch (DepartamentoNaoEncontradoException e1) {
			System.out.println(e1);
		}

	}
}
