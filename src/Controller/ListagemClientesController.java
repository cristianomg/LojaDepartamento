package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.DAO.ClienteDAO;
import Model.Entites.Cliente;
import View.ListagemClientesView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListagemClientesController implements Initializable{
    @FXML
    private Button btnMenuPrincipal;
    @FXML
    private TableView<Cliente> tabelaClientes;

	public void btnMenuPrincipal_Action() {
		ListagemClientesView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView(); 
		menuPrincipal.start(new Stage());
	}
	
	private void carregarTabela() {
		ClienteDAO clientesDAO = ClienteDAO.getInstance(); 
		List<Cliente> clientes = clientesDAO.getLista();
		ObservableList<Cliente> clientesObservados = FXCollections.observableList(clientes);
		this.tabelaClientes.getItems().setAll(clientesObservados);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabelaClientes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		carregarTabela();
		
	}
}
