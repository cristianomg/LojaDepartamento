package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.DAO.FuncionarioDAO;
import Model.Entites.Funcionario;
import View.ListagemFuncionariosView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListagemFuncionariosController implements Initializable{
    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private TableView<Funcionario> tabelaFuncionarios;
    
	private static FuncionarioDAO funcionariosDAO = FuncionarioDAO.getInstance();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTabela();
		
	}
	
	public void btnMenuPrincipal_Action() {
		ListagemFuncionariosView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());
	}
	
	private void carregarTabela() {
		List<Funcionario> funcionarios = funcionariosDAO.getLista();
		ObservableList<Funcionario> funcionariosObservados = FXCollections.observableList(funcionarios);
		this.tabelaFuncionarios.getItems().setAll(funcionariosObservados);
	}
}
