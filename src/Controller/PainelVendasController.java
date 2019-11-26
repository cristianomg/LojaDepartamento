package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.DAO.ClienteDAO;
import Model.DAO.ProdutoDAO;
import Model.DAO.VendaDAO;
import Model.Entites.Cliente;
import Model.Entites.Funcionario;
import Model.Entites.Produto;
import Model.Entites.Venda;
import Model.Entites.VendaProduto;
import View.MenuPrincipalView;
import View.PainelVendasView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField txfDesconto;
    
    @FXML
    private TextField txfPreco;

    @FXML
    private ChoiceBox<Cliente> choiceBoxCliente;

    @FXML
    private TableView<Produto> tabelaProdutos;

    @FXML
    private Button btnRemoverProduto;
    
    
    
	private ClienteDAO clientesDAO = ClienteDAO.getInstance();
	private ProdutoDAO produtosDAO = ProdutoDAO.getInstance();
	private List<VendaProduto> listaVendaProduto = new ArrayList<VendaProduto>();
	private Funcionario funcionario = MenuPrincipalController.getUserLogado();
	private List<Produto> produtosFuncionario = produtosDAO.getProdutoDepartamento(funcionario.getDepartamento().getId());
	private Produto produtoSelecionado;
	private VendaProduto vendaProdutoSelecionado;
	private float total = 0;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabelaProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tabelaProdutos.getSelectionModel().selectFirst();
		tabelaProdutos.getSelectionModel().selectedItemProperty()
		.addListener((observador, produtoAntigo, produtoNovo) -> {
			produtoSelecionado = produtoNovo;
			
		});
		tabelaVendaProduto.getSelectionModel().selectedItemProperty()
		.addListener((observador, vendaProdutoAntigo, vendaProdutoNovo) -> {
			vendaProdutoSelecionado = vendaProdutoNovo;
			
		});
		carregarListaProdutos();
		carregarChoiceBox();
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
		if (choiceBoxCliente.getValue() != null && listaVendaProduto.size() > 0) {
			List<VendaProduto> vendaProdutoSetar = new ArrayList<VendaProduto>();
			Venda venda = new Venda(LocalDate.now(), this.total, choiceBoxCliente.getValue(), this.funcionario, listaVendaProduto);
			listaVendaProduto.stream().forEach(x -> {
				x.setVenda(venda);	
				vendaProdutoSetar.add(x);
			});
			venda.finalizarVenda();
			venda.setListaVendaProduto(vendaProdutoSetar);
			System.out.println(listaVendaProduto);
			VendaDAO.getInstance().inserir(venda);
			Alert vendaRealizada = new Alert(AlertType.CONFIRMATION);
			vendaRealizada.setTitle("Confirmação");
			vendaRealizada.setContentText("Venda Realizada com Sucesso");
			listaVendaProduto.clear();
			carregaListaVendaProduto();
			ProdutoDAO.getInstance().save();
			vendaRealizada.show();
		}
    }
	
	public void btnAddProduto_Action() {
		try {
			if(produtoSelecionado != null) {
				if (produtoSelecionado.getQuantidade() >= Integer.parseInt(txfQuantidade.getText())) {
					int quantidade = txfQuantidade.getText().isBlank() ? 1 : Integer.parseInt(txfQuantidade.getText());
					int desconto = txfDesconto.getText().isBlank() ? 0 : Integer.parseInt(txfDesconto.getText()) ;
					VendaProduto vendaProduto = new VendaProduto(produtoSelecionado, quantidade, desconto);	
					produtosFuncionario.stream().filter(x-> x.equals(produtoSelecionado)).forEach(x-> x.removerQuantidade(quantidade));
					listaVendaProduto.add(vendaProduto);
					carregaListaVendaProduto();			
					carregarListaProdutos();
				}else {
					Alert erro = new Alert(AlertType.ERROR);
					erro.setTitle("Erro");
					erro.setContentText("Quantidade em estoque insuficiente.");
					erro.show();
				}
			}
		}catch (NumberFormatException e) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro");
			erro.setContentText(e.getMessage());
			erro.show();
		}
	}
	
	public void btnRemoverProduto_Action() {
		try {
			listaVendaProduto.remove(vendaProdutoSelecionado);
			vendaProdutoSelecionado.getProduto().addQuantidade(vendaProdutoSelecionado.getQuantidade());
			carregaListaVendaProduto();
			carregarListaProdutos();
		}catch (NullPointerException e) {
			
		}
	}
	
	public void btnMenuPrincipal_Action() {
		for (VendaProduto vendaProduto: listaVendaProduto) {
			for (Produto produto: produtosFuncionario) {
				if (vendaProduto.getProduto().equals(produto)) {
					produto.addQuantidade(vendaProduto.getQuantidade());
				}
			}
		}
		PainelVendasView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView(); 
		menuPrincipal.start(new Stage());
    }
	
	private void carregarChoiceBox() {
		List<Cliente> clientes = clientesDAO.getLista();
		ObservableList<Cliente> clientesObservados = FXCollections.observableList(clientes);
		choiceBoxCliente.getItems().setAll(clientesObservados);
	}

	private void carregarListaProdutos() {
		ObservableList<Produto> produtosObservados = FXCollections.observableList(produtosFuncionario);
		tabelaProdutos.getItems().setAll(produtosObservados);
		
	}
	
	private void carregaListaVendaProduto() {
		ObservableList<VendaProduto> vendaProdutoObservados = FXCollections.observableList(this.listaVendaProduto);
		tabelaVendaProduto.getItems().setAll(vendaProdutoObservados);
		atualizarTxtPreco();
	}
	
	private void atualizarTxtPreco() {
		this.total = 0;
		listaVendaProduto.stream().forEach( x -> this.total += x.getSubTotal());
		txfPreco.setText("RS: " + total);
	}
	
}


