package Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import Exceptions.FuncionarioDesligadoException;
import Exceptions.FuncionarioNaoEncontradoException;
import Model.DAO.FuncionarioDAO;
import Model.Entites.Funcionario;
import Model.Entites.ProdutoEletronico;
import View.MenuBuscasView;
import View.MenuCadastrosView;
import View.MenuListagensView;
import View.MenuMovimentacoesView;
import View.MenuPrincipalView;
import View.MenuRelatoriosView;
import View.MenuVendasView;
import View2.FuncionarioView;
import View2.Menu;
import View2.MovimentacaoView;
import View2.ProdutoView;
import View2.RelatorioView;
import View2.VendaView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerPrincipal  implements Initializable{

    @FXML
    private Button btnMenuCadastro;

    @FXML
    private Button btnMenuVendas;

    @FXML
    private Button btnMenuBuscas;

    @FXML
    private Button btnMenuMovimentacoes;

    @FXML
    private Button btnMenuListagens;

    @FXML
    private Button btnMenuRelatorio;
    
    
    
	private Menu menu;
	private Scanner sc = new Scanner(System.in);
	boolean repetir = true;
	FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
	}
	
	public void btnMenuCadastro_Action() {
		MenuPrincipalView.getStage().close();
		MenuCadastrosView menuCadastros = new MenuCadastrosView(); 
		menuCadastros.start(new Stage());
	}
	
	public void btnMenuVendas_Action() {
		MenuPrincipalView.getStage().close();
		MenuVendasView menuVendas = new MenuVendasView(); 
		menuVendas.start(new Stage());
	}
	
	
	public void btnMenuRelatorios_Action() {
		MenuPrincipalView.getStage().close();
		MenuRelatoriosView menuRelatorios = new MenuRelatoriosView(); 
		menuRelatorios.start(new Stage());
	}
	
	public void btnMenuMovimentacoes_Action() {
		MenuPrincipalView.getStage().close();
		MenuMovimentacoesView menuMovimentacoes = new MenuMovimentacoesView(); 
		menuMovimentacoes.start(new Stage());
	}
	
	public void btnMenuListagens_Action() {
		MenuPrincipalView.getStage().close();
		MenuListagensView menuListagens = new MenuListagensView(); 
		menuListagens.start(new Stage());
	}
	
	public void btnMenuBuscas_Action() {
		MenuPrincipalView.getStage().close();
		MenuBuscasView menuBuscas = new MenuBuscasView(); 
		menuBuscas.start(new Stage());
	}
	
	public void controllerVendas() {
		boolean running;
		try {
			Funcionario funcionario = autentificacao();
			running = true;
			MenuVendasController vendas = new MenuVendasController(funcionario); 
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
		}
		else {
			System.out.println("Opção invalida!!!");
			this.retornarMenuPrincipal();
			}
	}
}
