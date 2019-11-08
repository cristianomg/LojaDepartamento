package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import View.CadastrarClienteView;
import View.CadastrarDepartamentoView;
import View.CadastrarFuncionarioView;
import View.CadastrarProdutoSimilarView;
import View.CadastrarProdutoView;
import View.MenuCadastrosView;
import View.MenuPrincipalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuCadastroController implements Initializable{    
    @FXML
    private Button btnCadastrarDepartamento;

    @FXML
    private Button btnCadastrarFuncionario;

    @FXML
    private Button btnCadastrarProdutoSimilar;

    @FXML
    private Button btnCadastrarProduto;

    @FXML
    private Button btnVoltarMenu;

    @FXML
    private Button btnCadastrarCliente;
    
	public void btnCadastrarCliente_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarClienteView cadastrarCliente = new CadastrarClienteView(); 
		cadastrarCliente.start(new Stage());
	}
		
	public void btnCadastrarFuncionario_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarFuncionarioView cadastrarFuncionario = new CadastrarFuncionarioView(); 
		cadastrarFuncionario.start(new Stage());
	}
	
	public void btnCadastrarDepartamento_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarDepartamentoView cadastrarDepartamento = new CadastrarDepartamentoView(); 
		cadastrarDepartamento.start(new Stage());
	}
	
	public void btnCadastrarProduto_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarProdutoView cadastrarProduto = new CadastrarProdutoView(); 
		cadastrarProduto.start(new Stage());
	}
	
	public void btnCadastrarProdutoSimilar_Action() {
		MenuCadastrosView.getStage().close();
		CadastrarProdutoSimilarView cadastrarProdutoSimilar = new CadastrarProdutoSimilarView(); 
		cadastrarProdutoSimilar.start(new Stage());
	}
	
	public void btnMenuPrincipal_Action() {
		MenuCadastrosView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}

