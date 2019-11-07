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
import View2.MovimentacaoView;

public class ControllerMovimentacoes {
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();

	public String comprarProduto(HashMap<String, Integer> request) {
		try {
			Produto produto = produtos.getProduto(request.get("idProduto"));
			produto.addQuantidade(request.get("quantidade"));
			produtos.save();
			return "Compra realizada com sucesso.";
		} catch (ProdutoNaoEncontradoException e) {
			return e.getMessage();
		}

	}

	public String moverProdutoDepartamento(HashMap<String, Integer> request) {
		Produto produto;
		try {
			produto = produtos.getProduto(request.get("idProduto"));
			Departamento departamento = departamentos.getDepartamento(request.get("idDepartamento"));
			if (produto.getDepartamento().getNome().equals("Eletronico")) {
				ProdutoEletronico produto1 = (ProdutoEletronico) produto;
				for (ProdutoEletronico p : produto1.getListaSimilar()) {
					p.setDepartamento(departamento);
				}
			}
			produto.setDepartamento(departamento);

			produtos.save();
			return "Produto movido de departamento.";
		} catch (DepartamentoNaoEncontradoException e1) {
			return e1.getMessage();
		} catch (ProdutoNaoEncontradoException e) {
			return e.getMessage();
		}

	}

	public String moverFuncionarioDepartamento(int idDepartamentoOrigem) {
		MovimentacaoView movimentacaoView = new MovimentacaoView();
		Funcionario funcionario;
		try {
			Departamento departamentoOrigem = departamentos.getDepartamento(idDepartamentoOrigem);
			System.out.println(departamentoOrigem.getListFuncionario().size());
			movimentacaoView.listarFuncionariosDepartamento(departamentoOrigem.getListFuncionario());
			if (!departamentoOrigem.getListFuncionario().isEmpty()) {
				try {
					HashMap<String, String> request = movimentacaoView.dadosMoverFuncionario();
					String matricula = request.get("matricula");
					int idDepartamento = Integer.parseInt(request.get("idDepartamento"));
					funcionario = funcionarios.getFuncionario(matricula);
					if(!funcionario.isDesligado()) {
						Departamento departamentoNovo = departamentos.getDepartamento(idDepartamento);
						funcionario.setDepartamento(departamentoNovo);
						departamentoOrigem.removerFuncionarioList(funcionario);
						departamentoNovo.addFuncionarioList(funcionario);
						funcionarios.save();
						departamentos.save();
						return "Funcionario movido de departamento.";
					}else {
						return "Mudança de departamento não realizada, o funcionario está desligado da loja.";
					}
					

				} catch (FuncionarioNaoEncontradoException e) {
					return e.getMessage();
				} catch (DepartamentoNaoEncontradoException e1) {
					return e1.getMessage();
				}
			} else {
				return "Nenhum funcionario cadastrado nesse departamento.";
			}
		} catch (DepartamentoNaoEncontradoException e2) {
			return e2.getMessage();
		}
	}

	public String promoverFuncionarioChefe(int idDepartamento) {
		MovimentacaoView movimentacaoView1 = new MovimentacaoView();
		Departamento departamento;
		try {
			departamento = departamentos.getDepartamento(idDepartamento);
			movimentacaoView1.listarFuncionariosDepartamento(departamento.getListFuncionario());
			String matricula = movimentacaoView1.solicitarMatricularFuncionario();
			Funcionario funcionario = funcionarios.getFuncionario(matricula);
			if(!funcionario.isDesligado()) {
				boolean chefiaRealizada = departamento.setChefe(funcionario);
				departamentos.save();
				funcionarios.save();
				if (chefiaRealizada) {
					return "Funcionario Promovido a chefe com sucesso.";
				} else {
					return "Promoção não concluida, verifique se o funcionario possui ensino superior.";
				}
			}else {
				return "Promoção não concluida, o funcionario está desligado da loja.";
			}

		} catch (DepartamentoNaoEncontradoException | FuncionarioNaoEncontradoException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
	public String modificarComissao(int idDepartamento) {
		try {
			Departamento departamento = departamentos.getDepartamento(idDepartamento);
			MovimentacaoView movimentacaoView = new MovimentacaoView();
			double comissao = movimentacaoView.solicitarNovaComissao();
			departamento.setPercentualComissao(comissao);
			departamentos.save();
			return "Comissão alterada com sucesso.";
		} catch (DepartamentoNaoEncontradoException e) {
			return e.getMessage();
		}
	}
	
	public String demitirFuncionario(String matriculaFuncionario) {
		try {
			Funcionario funcionario = funcionarios.getFuncionario(matriculaFuncionario);
			if(!funcionario.isDesligado()) {
				funcionario.setDesligado(true);
				funcionarios.save();
				return "Demissao realizada com sucesso.";
			}else {
				return "O funcionario já está desligado da Loja.";
			}
		}catch(FuncionarioNaoEncontradoException e) {
			return e.getMessage();
		}
	}
	
}
