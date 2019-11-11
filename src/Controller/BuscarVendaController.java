package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.VendaNaoEncontradaException;
import Model.DAO.VendaDAO;
import Model.Entites.Venda;
import Model.Entites.VendaProduto;
import View.BuscarVendaView;
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

public class BuscarVendaController implements Initializable{

    @FXML
    private TextField txfCodigoVenda;
    
    @FXML
    private TextField txfTotal;

    @FXML
    private TableView<VendaProduto> tabelaListaProduto;

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private TableView<Venda> tabelaVenda;

    @FXML
    private Button btnBuscar;
    private Venda vendaSelecionada;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabelaVenda.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tabelaVenda.getSelectionModel().selectedItemProperty().addListener((observador, vendaAntiga, vendaNova)-> {
			vendaSelecionada = vendaNova;
		});
		tabelaListaProduto.getColumns().forEach(x -> {
			x.setResizable(false);
			x.setReorderable(false);
		});
		tabelaVenda.getColumns().forEach(x -> {
			x.setResizable(false);
			x.setReorderable(false);
		});
		
    	tabelaVenda.setDisable(true);
    	tabelaVenda.setOpacity(100);
		
	}

    public void btnMenuPrincipal_Action() {
		BuscarVendaView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());
    }

    public void btnBuscar_Action() {
    	carregarVenda();
    	carregarListaProdutos();
    }

    private void carregarVenda() {
    	VendaDAO vendaDAO = VendaDAO.getInstance();
    	try {
			Venda venda = vendaDAO.getVenda(Integer.parseInt(txfCodigoVenda.getText()));
			tabelaVenda.getItems().setAll(venda);
			tabelaVenda.getSelectionModel().selectFirst();
			txfTotal.setText(String.valueOf(venda.getPrecoTotal()));

		} catch (NumberFormatException | VendaNaoEncontradaException e) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro");
			erro.setContentText(e.getMessage());
			erro.show();
		}
    }
    
    private void carregarListaProdutos() {
    	List<VendaProduto> listaProdutos = vendaSelecionada.getListaVendaProduto();
    	ObservableList<VendaProduto> listaProdutosObservados = FXCollections.observableList(listaProdutos);
    	tabelaListaProduto.getItems().setAll(listaProdutosObservados);
    }


}
