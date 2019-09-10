package Controller;

import java.util.HashMap;
import java.util.Scanner;

import Exceptions.ObjetoNaoEncontradoException;
import Model.DAO.FuncionarioDAO;
import Model.Entites.Funcionario;
import View.Menu;

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
			this.controllerRealocacoes();
			break;
		case 5:
			this.controllerListagem();
			break;
		}
		
	}
	public void controllerCadastro() {
		int respCadastro = menu.menuCadastro();
		switch(respCadastro) {
		case 1:
			ControllerCadastro.cadastrarCliente();
			break;
		case 2:
			ControllerCadastro.cadastrarFuncionario();
			break;
		case 3:
			ControllerCadastro.cadastrarDepartamento();
			break;
		case 4:
			ControllerCadastro.cadastrarProduto();
			break;
		case 5:
			ControllerCadastro.cadastrarProdutoSimilar();
			break;
		}
		repetir = true;
		while(repetir) {
			System.out.println("Digite '1' para voltar pro menu principal");
			String opc = sc.next();
			if (opc.equals("1")) {
				repetir = false;
				this.controllerPrincipal();
			}
			else {
				System.out.println("Op��o invalida!!!");
			}
	}
	}
	public void controllerVendas() {
		boolean running;
		try {
			Funcionario funcionario = autentificacao();
			running = true;
			ControllerVendas vendas = new ControllerVendas(funcionario); 
			while (!running) {
				int opc = menu.menuVenda();
				switch (opc) {
				case 1:
					vendas.abrirVender();
					break;
				case 2:
					vendas.adicionarProduto();
					break;
				case 3:
					vendas.removerProdutos();
					break;
				case 4:
					vendas.calcularValorVenda();
					break;
				case 5:
					vendas.finalizarVenda();
					break;
				case 6:
					running = false;
					this.controllerPrincipal();
					break;
				}
				
			}
			
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println(e.getMessage());
			running = false;
			e.printStackTrace();
			repetir = true;
			while(repetir) {
				System.out.println("Digite '1' para voltar pro menu principal");
				String opc = sc.next();
				if (opc.equals("1")) {
					repetir = false;
					this.controllerPrincipal();
				}
				else {
					System.out.println("Op��o invalida!!!");
				}
		}
		}
		

		
	}
	public void controllerRelatorios() {
		
	}
	public void controllerRealocacoes() {
		
	}
	public void controllerListagem() {
		int respostaListagem = menu.menuListagem();
		switch(respostaListagem) {
		case 1:
			ControllerListagem.listarClientes();
			break;
		case 2:
			ControllerListagem.listaDepartamentos();
			break;
		case 3:
			ControllerListagem.listaFuncionario();
			break;
		case 4:
			ControllerListagem.listarProdutos();
			break;
		}
		repetir = true;
		while(repetir) {
			System.out.println("Digite '1' para voltar pro menu principal");
			String opc = sc.next();
			if (opc.equals("1")) {
				repetir = false;
				this.controllerPrincipal();
			}
			else {
				System.out.println("Op��o invalida!!!");
			}
	}
	}
	
	public Funcionario autentificacao() throws ObjetoNaoEncontradoException {
		HashMap<String, String> dadosFuncionario = menu.autorizacaoView();
		Funcionario funcionario = funcionarioDAO.getFuncionario(dadosFuncionario.get("matricula"), dadosFuncionario.get("senha"));
		return funcionario;
	}
}