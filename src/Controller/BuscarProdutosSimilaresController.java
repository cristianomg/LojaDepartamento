package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.ProdutoNaoEncontradoException;
import Model.DAO.ProdutoDAO;
import Model.Entites.ProdutoEletronico;
import View.BuscarProdutosSimilaresView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuscarProdutosSimilaresController implements Initializable {

    @FXML
    private TextField txfIdProdutoMarca;

    @FXML
    private TableView<ProdutoEletronico> listaProdutosSimilares;

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private TableView<ProdutoEletronico> produtoInfo;

    @FXML
    private Button btnBuscar;
    private ProdutoEletronico produtoSelecionado;
    private ProdutoDAO produtos = ProdutoDAO.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	produtoInfo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	produtoInfo.getSelectionModel().selectedItemProperty()
    	.addListener((observador, produtoAntigo, produtoNovo)->{
        		produtoSelecionado = produtoNovo;
        		try {
        			carregarListaSimilares(produtoSelecionado.getListaSimilar());        			
        		}catch (NullPointerException e) {
        			
        		}
    	});
    	produtoInfo.setDisable(true);
    	produtoInfo.setOpacity(100);
    	produtoInfo.getColumns().forEach(x -> {
    		x.setResizable(false);
    		x.setReorderable(false);
    		});
    	listaProdutosSimilares.getColumns().forEach(x -> {
    		x.setResizable(false);
    		x.setReorderable(false);
    		});    	
    	
    }
    
    @FXML
    void btnMenuPrincipal_Action() {
		BuscarProdutosSimilaresView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView(); 
		menuPrincipal.start(new Stage());

    }

    @FXML
    void btnBuscar_Action() {
    	try {
    		carregarProduto(Integer.parseInt(txfIdProdutoMarca.getText()));
    		this.produtoInfo.getSelectionModel().selectFirst();
    	}catch (NumberFormatException e) {
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro");
			erro.setHeaderText("Erro Formato Invalido.");
			erro.setContentText(e.getMessage());
			erro.show();
		} 
    }

    private void carregarProduto(int idProduto) {
    	ProdutoEletronico produto;
		try {
			produto = (ProdutoEletronico) produtos.getProduto(idProduto);
			produtoInfo.getItems().setAll(produto);
		} catch (ProdutoNaoEncontradoException e) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro");
			erro.setHeaderText("Erro Produto Não encontrado.");
			erro.setContentText(e.getMessage());
			erro.show();
		}
    }
    private void carregarListaSimilares(List<ProdutoEletronico> listaProdutosSimilares) {
    	ObservableList<ProdutoEletronico> listaProdutosObservados = FXCollections.observableList(listaProdutosSimilares);
    	this.listaProdutosSimilares.getItems().setAll(listaProdutosObservados);
    }

}
