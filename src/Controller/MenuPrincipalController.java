package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.DAO.FuncionarioDAO;
import Model.Entites.Funcionario;
import View.LoginView;
import View.MenuBuscasView;
import View.MenuCadastrosView;
import View.MenuListagensView;
import View.MenuMovimentacoesView;
import View.MenuPrincipalView;
import View.MenuRelatoriosView;
import View2.Menu;
import View2.MovimentacaoView;
import View2.RelatorioView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipalController  implements Initializable{

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
    private static Funcionario userLogado;
    private Menu menu;
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
		LoginView loginView = new LoginView(); 
		loginView.start(new Stage());
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
	
	public void controllerRelatorios() {
		ControllerRelatorio controllerRelatorio = new ControllerRelatorio();
		RelatorioView relatorioView = new RelatorioView();
		controllerRelatorio.relatorioMensal(relatorioView.solicitarMes());
		
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
	}
	

	public static Funcionario getUserLogado() {
		return userLogado;
	}

	public static void setUserLogado(Funcionario userLogado) {
		MenuPrincipalController.userLogado = userLogado;
	}
	
}
