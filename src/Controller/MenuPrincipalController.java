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
	boolean repetir = true;
	
	
	FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			btnMenuMovimentacoes.setDisable(true);
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
	
	public static Funcionario getUserLogado() {
		return userLogado;
	}

	public static void setUserLogado(Funcionario userLogado) {
		MenuPrincipalController.userLogado = userLogado;
	}
	
}
