package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Exceptions.FuncionarioDesligadoException;
import Exceptions.FuncionarioNaoEncontradoException;
import Model.DAO.FuncionarioDAO;
import Model.Entites.Funcionario;
import Model.Entites.ProdutoEletronico;
import View.ClienteView;
import View.DepartamentoView;
import View.FuncionarioView;
import View.Menu;
import View.MovimentacaoView;
import View.ProdutoView;
import View.RelatorioView;
import View.VendaView;

public class ControllerPrincipal {
	private Menu menu;
	private Scanner sc = new Scanner(System.in);
	boolean repetir = true;
	FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstance();

	
	public ControllerPrincipal() {
		menu = new Menu();
	}
	public void controllerPrincipal() {
		int resp = menu.menuPrincipal();
		switch(resp) {
		case 1:
			this.controllerCadastro();
			break;
		case 2:
			this.controllerVendas();
			break;
		case 3:
			this.controllerRelatorios();
			break;
		case 4:
			this.controllerMovimentacoes();
			break;
		case 5:
			this.controllerListagem();
			break;
		case 6: 
			this.controllerBusca();
			break;
		}
		
	}
	public void controllerCadastro() {
		int respCadastro = menu.menuCadastro();
		ControllerCadastro controllerCadastro = new ControllerCadastro();
		String result;
		switch(respCadastro) {
		case 1:
			ClienteView clienteView = new ClienteView();
			result = controllerCadastro.cadastrarCliente(clienteView.cadastroCliente());
			System.out.println(result);
			break;
		case 2:
			FuncionarioView funcionarioView = new FuncionarioView();
			result = controllerCadastro.cadastrarFuncionario(funcionarioView.solicitarDepartamentoID());
			System.out.println(result);
			break;
		case 3:
			DepartamentoView departamentoView = new DepartamentoView();
			result = controllerCadastro.cadastrarDepartamento(departamentoView.cadastrarDepartamento());
			System.out.println(result);
			break;
		case 4:
			ProdutoView produtoView = new ProdutoView();
			result = controllerCadastro.cadastrarProduto(produtoView.cadastrarProduto());
			System.out.println(result);
			break;
		case 5:
			ProdutoView produtoView1 = new ProdutoView();
			result = controllerCadastro.cadastrarProdutoSimilar(produtoView1.cadastrarProdutoSimilar());
			System.out.println(result);
			break;
		case 6:
			this.controllerPrincipal();
			break;
		}
		this.retornarMenuPrincipal();
	}
	public void controllerVendas() {
		boolean running;
		try {
			Funcionario funcionario = autentificacao();
			running = true;
			ControllerVendas vendas = new ControllerVendas(funcionario); 
			while (running) {
				int opc = menu.menuVenda();
				VendaView vendaView = new VendaView();
				switch (opc) {
				case 1:
					vendas.abrirVender(vendaView.requestAbrirVenda());
					break;
				case 2:
					vendas.adicionarProduto(vendaView.requestBuscaPorVenda());
					break;
				case 3:
					vendas.removerProdutos(vendaView.requestBuscaPorVenda());
					break;
				case 4:
					vendas.calcularValorVenda(vendaView.requestBuscaPorVenda());
					break;
				case 5:
					vendas.finalizarVenda(vendaView.requestBuscaPorVenda());
					break;
				case 6:
					running = false;
					this.controllerPrincipal();
					break;
				}
				
			}
		} catch (FuncionarioNaoEncontradoException | FuncionarioDesligadoException e) {
			System.out.println(e.getMessage());
			running = false;
			this.retornarMenuPrincipal();
		}
		

		
	}
	public void controllerRelatorios() {
		ControllerRelatorio controllerRelatorio = new ControllerRelatorio();
		RelatorioView relatorioView = new RelatorioView();
		controllerRelatorio.relatorioMensal(relatorioView.solicitarMes());
		this.retornarMenuPrincipal();
		
	}
	public void controllerMovimentacoes() {
		int respostaListagem = menu.menuMovimentacoes();
		ControllerMovimentacoes controllerMovimentacoes = new ControllerMovimentacoes();
		MovimentacaoView movimentacaoView = new MovimentacaoView();
		String result;
		switch(respostaListagem) {
		case 1:
			result = controllerMovimentacoes.comprarProduto(movimentacaoView.dadosComprarProduto());
			System.out.println(result);
			break;
		case 2:
			result = controllerMovimentacoes.moverProdutoDepartamento(movimentacaoView.dadosMoverProduto());
			System.out.println(result);
			break;
		case 3:
			result = controllerMovimentacoes.moverFuncionarioDepartamento(movimentacaoView.solicitarDepartamento());
			System.out.println(result);
			break;
		case 4:
			result = controllerMovimentacoes.promoverFuncionarioChefe(movimentacaoView.solicitarDepartamento());
			System.out.println(result);
			break;
		case 5:
			result = controllerMovimentacoes.modificarComissao(movimentacaoView.solicitarDepartamento());
			System.out.println(result);
		case 6:
			result = controllerMovimentacoes.demitirFuncionario(movimentacaoView.solicitarMatricularFuncionario());
			System.out.println(result);
		case 7:
			this.controllerPrincipal();
			break;
		}
		this.retornarMenuPrincipal();
	}
	
	

	public void controllerListagem() {
		int respostaListagem = menu.menuListagem();
		ControllerListagem controllerListagem = new ControllerListagem();
		switch(respostaListagem) {
		case 1:
			controllerListagem.listarClientes();
			break;
		case 2:
			controllerListagem.listaDepartamentos();
			break;
		case 3:
			controllerListagem.listaFuncionario();
			break;
		case 4:
			controllerListagem.listarProdutos();
			break;
		case 5:
			controllerListagem.listarVendas();
			break;
		case 6:
			this.controllerPrincipal();
			break;
		}
		this.retornarMenuPrincipal();
	}
	
	public void controllerBusca() {
		int respostaListagem = menu.menuBusca();
		ControllerBusca controllerBusca = new ControllerBusca();
		switch(respostaListagem) {
		case 1:
			FuncionarioView funcionarioView = new FuncionarioView();
			controllerBusca.buscarVendedor(funcionarioView.buscarFuncionarioRequest());
			break;
		case 2:
			VendaView vendaView = new VendaView();
			controllerBusca.buscarRegistroVenda(vendaView.requestBuscaPorVenda());
			break;
		case 3:
			ProdutoView produtoView = new ProdutoView();
			List<ProdutoEletronico> result = controllerBusca.buscarProdutoSimiliar(produtoView.buscarProdutoRequest());
			produtoView.listarProdutoSimilar(result);
			break;
		case 4:
			this.controllerPrincipal();
			break;
		}
		this.retornarMenuPrincipal();
	}

	
	public Funcionario autentificacao() throws FuncionarioNaoEncontradoException, FuncionarioDesligadoException {
		HashMap<String, String> dadosFuncionario = menu.autorizacaoView();
		Funcionario funcionario = funcionarioDAO.getFuncionario(dadosFuncionario.get("matricula"), dadosFuncionario.get("senha"));
		if(!funcionario.isDesligado()) {
			return funcionario;
		}
		else {
			throw new FuncionarioDesligadoException("O funcionario está desligado da loja e não possui acesso a vendas.");
		}
	}
	
	private void retornarMenuPrincipal() {
		System.out.println("Digite '1' para voltar pro menu principal");
		int opc = sc.nextInt();
		if (opc==1) {
			this.controllerPrincipal();
		}
		else {
			System.out.println("Opção invalida!!!");
			this.retornarMenuPrincipal();
			}
	}
}
