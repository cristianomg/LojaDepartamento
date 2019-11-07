package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.DAO.ProdutoDAO;
import Model.Entites.Produto;
import View.ListagemProdutosView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListagemProdutosController implements Initializable{

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private TableView<Produto> tabelaProdutos;
    
	private ProdutoDAO produtosDAO = ProdutoDAO.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	carregarTabela();
    	
    }
    
    public void btnMenuPrincipal_Action() {
		ListagemProdutosView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());
    }

    private void carregarTabela() {
    	List<Produto> produtos = produtosDAO.getLista();
    	ObservableList<Produto> produtosObservados = FXCollections.observableList(produtos);
    	this.tabelaProdutos.getItems().setAll(produtosObservados);
    }


}
