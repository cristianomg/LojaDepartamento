package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.VendaNaoEncontradaException;
import Model.DAO.VendaDAO;
import Model.Entites.Venda;
import Model.Entites.VendaProduto;
import View.ListagemVendasView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ListagemVendasController implements Initializable{

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private TableView<Venda> tabelaVendas;
    
    @FXML
    private TableView<VendaProduto> tabelaVendasProduto;
    
    
    @FXML
    private TextField txtTotal;
    
	private VendaDAO vendasDAO = VendaDAO.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.tabelaVendas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.tabelaVendas.getSelectionModel().selectedItemProperty()
		.addListener((observador, vendaAntiga, vendaNova)->{
			if(vendaNova != null) {
				try {
					Venda venda = vendasDAO.getVenda(vendaNova.getCodigo());
					List<VendaProduto> listaVendaProduto = venda.getListaVendaProduto();
					carregarTabelaVendaProduto(listaVendaProduto);
					this.txtTotal.setText(String.valueOf(venda.getPrecoTotal()));
				} catch (VendaNaoEncontradaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		carregarTabelaVenda();
		
	}

    public void btnMenuPrincipal_Action() {
		ListagemVendasView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());
    }

    private void carregarTabelaVenda() {
    	List<Venda> vendas = vendasDAO.getLista();
    	ObservableList<Venda> vendasObservados = FXCollections.observableList(vendas);
    	this.tabelaVendas.getItems().setAll(vendasObservados);
    }

    private void carregarTabelaVendaProduto(List<VendaProduto> vendaProduto) {
    	ObservableList<VendaProduto> vendasProdutoObservados = FXCollections.observableList(vendaProduto);
    	this.tabelaVendasProduto.getItems().setAll(vendasProdutoObservados);
    }

}
