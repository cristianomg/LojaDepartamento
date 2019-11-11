package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.DepartamentoNaoEncontradoException;
import Model.DAO.DepartamentoDAO;
import Model.Entites.Departamento;
import Model.Entites.Produto;
import View.ListagemDepartamentosView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListagemDepartamentosController implements Initializable{

    @FXML
    private Button btn_MenuPrincipal;

    @FXML
    private TableView<Departamento> tabelaDepartamentos;

    @FXML
    private TableView<Produto> tabelaProdutos;
    
    private DepartamentoDAO departamentosDAO = DepartamentoDAO.getInstance();
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	this.tabelaDepartamentos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	this.tabelaDepartamentos.getSelectionModel().selectedItemProperty()
    	.addListener((observador, departamentoAntigo, departamentoNovo) -> {
    		if (departamentoNovo != null) {
    			try {
					List<Produto> listaProdutos = this.departamentosDAO.getDepartamento(departamentoNovo.getId()).getProdutos();
					carregarTabelaProdutos(listaProdutos);
				} catch (DepartamentoNaoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
       	tabelaDepartamentos.getColumns().forEach(x -> {
    		x.setResizable(false);
    		x.setReorderable(false);
    		});
    	tabelaProdutos.getColumns().forEach(x -> {
    		x.setResizable(false);
    		x.setReorderable(false);
    		});    	
 
    	
    	carregarTabelaDepartamento();
    	
    }
    
    public void btnMenuPrincipal_Action() {
		ListagemDepartamentosView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());
    }
    private void carregarTabelaDepartamento() {
	List<Departamento> departamentos = departamentosDAO.getLista();
	ObservableList<Departamento> departamentosObservados = FXCollections.observableList(departamentos);
	this.tabelaDepartamentos.getItems().setAll(departamentosObservados);
    }
    
	private void carregarTabelaProdutos(List<Produto> listaProdutos) {
		ObservableList<Produto> produtosObservados = FXCollections.observableList(listaProdutos);
		this.tabelaProdutos.getItems().setAll(produtosObservados);
	}

}
