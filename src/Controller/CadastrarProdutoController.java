package Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.DAO.DepartamentoDAO;
import Model.DAO.ProdutoDAO;
import Model.Entites.Departamento;
import Model.Entites.Produto;
import Model.Entites.ProdutoEletronico;
import View.CadastrarProdutoView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarProdutoController implements Initializable {

	@FXML
	private TextField txfNome;

	@FXML
	private Button btnSalvar;

	@FXML
	private TextField txfPreco;

	@FXML
	private ChoiceBox<Departamento> choiceBoxDep;

	@FXML
	private Button btnMenuPrincipal;

	@FXML
	private TextField txfDescricao;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnExcluir;

	@FXML
	private TableView<Produto> tabelaProduto;

	@FXML
	private Button btnAtualizar;
	
	private ProdutoDAO produtosDAO = ProdutoDAO.getInstance();
	private DepartamentoDAO departamentosDAO = DepartamentoDAO.getInstance();
	private Produto produtoSelecionado;
	private boolean ehInserir;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.tabelaProduto.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.tabelaProduto.getSelectionModel().selectedItemProperty()
		.addListener((observador, produtoAntigo, produtoNovo)->{
			if(produtoNovo != null) {
				produtoSelecionado = produtoNovo;
				txfNome.setText(produtoSelecionado.getNome());
				txfPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
				txfDescricao.setText(produtoSelecionado.getDescricao());
				choiceBoxDep.setValue(produtoSelecionado.getDepartamento());
			}
		});
	   	tabelaProduto.getColumns().forEach(x -> {
    		x.setResizable(false);
    		x.setReorderable(false);
    		});
 
		carregarTabela();
		carregarChoiceBox();
		habilitarBotoes(false);
		this.tabelaProduto.getSelectionModel().selectFirst();
		
	}

	public void btnMenuPrincipal_Action() {
		CadastrarProdutoView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());

	}
	
	private void habilitarBotoes(boolean habilitar) {
		tabelaProduto.setDisable(habilitar);
		txfNome.setDisable(!habilitar);
		txfPreco.setDisable(!habilitar);
		txfDescricao.setDisable(!habilitar);
		btnInserir.setDisable(habilitar);
		btnExcluir.setDisable(habilitar);
		btnAtualizar.setDisable(habilitar);
		btnExcluir.setDisable(habilitar);
		btnSalvar.setDisable(!habilitar);
		btnCancelar.setDisable(!habilitar);
		choiceBoxDep.setDisable(!habilitar);
	}
	
	private void carregarTabela() {
		List<Produto> produtos = produtosDAO.getLista();
		ObservableList<Produto> produtosObservados = FXCollections.observableList(produtos);
		this.tabelaProduto.getItems().setAll(produtosObservados);
	}
	
	private void carregarChoiceBox() {
		List<Departamento> departamentos = departamentosDAO.getLista();
		ObservableList<Departamento> departamentosObservados = FXCollections.observableList(departamentos);
		this.choiceBoxDep.getItems().setAll(departamentosObservados);
	}
	
	public void btnInserir_Action() {
		ehInserir = true;
		habilitarBotoes(true);
		txfNome.setText("");
		txfDescricao.setText("");
		txfPreco.setText("");
	}
	
	public void btnAtualizar_Action() {
		ehInserir = false;
		habilitarBotoes(true);
		choiceBoxDep.setDisable(true);
		txfNome.setDisable(true);
	}
	
	public void btnExcluir_Action() {
		if(produtoSelecionado != null) {
			Alert confirmacao = new Alert(AlertType.CONFIRMATION);
			confirmacao.setTitle("Confirmação");
			confirmacao.setHeaderText("Confirmação de exclusão do contato");
			confirmacao.setContentText("Tem certeza deseja excluir o contato?");
			Optional<ButtonType> resultadoConfirmacao = confirmacao.showAndWait();
			if (resultadoConfirmacao.isPresent() && resultadoConfirmacao.get() == ButtonType.OK) {
				produtosDAO.deletar(this.produtoSelecionado);
				carregarTabela();
				this.tabelaProduto.getSelectionModel().selectFirst();
			}	
		}
		
	}
	
	public void btnSalvar_Action() {	
		try {
			if (!txfNome.getText().isBlank() && !txfDescricao.getText().isBlank() &&
					!txfPreco.getText().isBlank() && choiceBoxDep.getValue() != null) {
				if (ehInserir) {
					cadastrarProduto(txfNome.getText(), txfDescricao.getText(),
							Integer.parseInt(txfPreco.getText()), choiceBoxDep.getValue(), 100);
				}else {
					Produto produtoAtualizar = produtoSelecionado;
					produtoAtualizar.setPreco(Integer.parseInt(txfPreco.getText()));
					produtoAtualizar.setDescricao(txfDescricao.getText());
					produtosDAO.atualizar(produtoAtualizar);
				}
				habilitarBotoes(false);
				carregarTabela();
				this.tabelaProduto.getSelectionModel().selectFirst();
			}else {
				Alert erro = new Alert(AlertType.ERROR);
				erro.setTitle("Erro");
				erro.setHeaderText("Informe todos os dados");
				erro.show();
			}
		} catch(NumberFormatException e) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro");
			erro.setHeaderText("Erro Formato Invalido.");
			erro.setContentText("Dados invalidos, tente novamente.");
			erro.show();
		}

	}
	
	public void btnCancelar_Action() {
		habilitarBotoes(false);
		ehInserir = false;
		carregarTabela();
		this.tabelaProduto.getSelectionModel().selectFirst();
	}
	
	private void cadastrarProduto(String nome, String descricao, float preco, Departamento departamento, int quantidade) {
			if (departamento.getNome().equals("Eletronico")) {
				ProdutoEletronico p = new ProdutoEletronico(nome, descricao, preco, quantidade, departamento);
				if(!produtosDAO.existe(p)) {
					produtosDAO.inserir(p);
					Alert cadastroRealizado = new Alert(AlertType.INFORMATION);
					cadastroRealizado.setTitle("Cadastro Produto Eletrônico");
					cadastroRealizado.setHeaderText("Realizado com sucesso");
					cadastroRealizado.show();					
				}else {
					Alert cadastroRealizado = new Alert(AlertType.WARNING);
					cadastroRealizado.setTitle("Cadastros invalido");
					cadastroRealizado.setHeaderText("O produto já está cadastrado");
					cadastroRealizado.show();
				}
			}
			else {
				Produto p = new Produto(nome, descricao, preco, quantidade, departamento);
				if(!produtosDAO.existe(p)) {
					produtosDAO.inserir(p);
					Alert cadastroRealizado = new Alert(AlertType.INFORMATION);
					cadastroRealizado.setTitle("Cadastro Produto");
					cadastroRealizado.setHeaderText("Realizado com sucesso");
					cadastroRealizado.show();
				}else {
					Alert cadastroRealizado = new Alert(AlertType.WARNING);
					cadastroRealizado.setTitle("Cadastros invalido");
					cadastroRealizado.setHeaderText("O produto já está cadastrado");
					cadastroRealizado.show();	
				}
			}
		}

}
