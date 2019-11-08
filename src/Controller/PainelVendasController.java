package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.DAO.ClienteDAO;
import Model.DAO.ProdutoDAO;
import Model.Entites.Cliente;
import Model.Entites.Funcionario;
import Model.Entites.Produto;
import Model.Entites.VendaProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PainelVendasController implements Initializable{


    @FXML
    private TableView<VendaProduto> tabelaVendaProduto;

    @FXML
    private Button btnFinalizar;
    
    @FXML
    private Button btnAddProduto;
    
    @FXML
    private Button btnMenuPrincipal;
    
    @FXML
    private TextField txfQuantidade;

    @FXML
    private ChoiceBox<Cliente> choiceBoxCliente;

    @FXML
    private TableView<Produto> tabelaProdutos;
    
    
    
	private ClienteDAO clientesDAO = ClienteDAO.getInstance();
	private ProdutoDAO produtosDAO = ProdutoDAO.getInstance();
	private List<VendaProduto> listaVendaProduto = new ArrayList<VendaProduto>();

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregaListaVendaProduto();
		carregarListaProdutos();
		tabelaProdutos.getColumns().forEach(x -> {
    		x.setResizable(false);
    		x.setReorderable(false);
    		});
    	tabelaVendaProduto.getColumns().forEach(x -> {
    		x.setResizable(false);
    		x.setReorderable(false);
    		});    	
		
	}

	public void btnFinalizar_Action() {

    }
	
	public void btnAddProduto_Action() {
		
	}
	
	public void btnMenuPrincipal_Action() {

    }

	private void carregarListaProdutos() {
		Funcionario funcionario = MenuPrincipalController.getUserLogado();
		List<Produto> produtosFuncionario = produtosDAO.getProdutoDepartamento(funcionario.getDepartamento().getId());
		ObservableList<Produto> produtosObservados = FXCollections.observableList(produtosFuncionario);
		tabelaProdutos.getItems().setAll(produtosObservados);
		
	}
	
	private void carregaListaVendaProduto() {
		ObservableList<VendaProduto> vendaProdutoObservados = FXCollections.observableList(this.listaVendaProduto);
		tabelaVendaProduto.getItems().setAll(vendaProdutoObservados);
	}
}

