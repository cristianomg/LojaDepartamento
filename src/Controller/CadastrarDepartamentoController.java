package Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.DAO.DepartamentoDAO;
import Model.Entites.Departamento;
import View.CadastrarDepartamentoView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarDepartamentoController implements Initializable {
	@FXML
	private TextField txfSigla;

	@FXML
	private TextField txfNome;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnMenuPrincipal;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnCancelar;

	@FXML
	private TableView<Departamento> tabelaDepartamentos;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnAtualizar;
	private boolean ehInserir;
	private DepartamentoDAO departamentosDAO = DepartamentoDAO.getInstance();
	private Departamento departamentoSelecionado;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.tabelaDepartamentos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.tabelaDepartamentos.getSelectionModel().selectedItemProperty()
		.addListener((observador, departamentoAntigo, departamentoNovo)->{
			if(departamentoNovo != null) {
				departamentoSelecionado = departamentoNovo;
				txfNome.setText(departamentoSelecionado.getNome());
				txfSigla.setText(departamentoSelecionado.getSigla());
			}
		});
		carregarTabela();
		habilitarBotoes(false);
		this.tabelaDepartamentos.getSelectionModel().selectFirst();
	}

	private void carregarTabela() {
		List<Departamento> departamentos = departamentosDAO.getLista();
		ObservableList<Departamento> departamentosObservados = FXCollections.observableList(departamentos);
		this.tabelaDepartamentos.getItems().setAll(departamentosObservados);
	}
	
	private void habilitarBotoes(boolean habilitar) {
		tabelaDepartamentos.setDisable(habilitar);
		txfNome.setDisable(!habilitar);
		txfSigla.setDisable(!habilitar);
		btnInserir.setDisable(habilitar);
		btnExcluir.setDisable(!habilitar);
		btnAtualizar.setDisable(!habilitar);
		btnCancelar.setDisable(!habilitar);
		btnSalvar.setDisable(!habilitar);
	}
	
	public void btnMenuPrincipal_Action() {
		CadastrarDepartamentoView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView(); 
		menuPrincipal.start(new Stage());

	}
	
	public void btnInserir_Action() {
		ehInserir = true;
		habilitarBotoes(true);
		btnExcluir.setDisable(true);
		btnAtualizar.setDisable(true);
		txfNome.setText("");
		txfSigla.setText("");

	}
	
	public void btnAtualizar_Action() {
		ehInserir = false;
		habilitarBotoes(true);
	}
	
	public void btnExcluir_Action() {
		Alert confirmacao = new Alert(AlertType.CONFIRMATION);
		confirmacao.setTitle("Confirmação");
		confirmacao.setHeaderText("Confirmação de exclusão do contato");
		confirmacao.setContentText("Tem certeza deseja excluir o contato?");
		Optional<ButtonType> resultadoConfirmacao = confirmacao.showAndWait();
		if (resultadoConfirmacao.isPresent() && resultadoConfirmacao.get() == ButtonType.OK) {
			departamentosDAO.deletar(this.departamentoSelecionado);
			carregarTabela();
			this.tabelaDepartamentos.getSelectionModel().selectFirst();
		}
	}
	
	public void btnSalvar_Action() {	
		if (!txfNome.getText().isBlank() && !txfSigla.getText().isBlank()) {
			if (ehInserir) {
				cadastrarDepartamento(txfNome.getText(), txfSigla.getText());
			}
			habilitarBotoes(false);
			carregarTabela();
			this.tabelaDepartamentos.getSelectionModel().selectFirst();
		}else {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro");
			erro.setHeaderText("Informe todos os dados");
			erro.show();
		}

	}
	
	public void btnCancelar_Action() {
		habilitarBotoes(false);
		ehInserir = false;
		carregarTabela();
		this.tabelaDepartamentos.getSelectionModel().selectFirst();
	}
		
	private void cadastrarDepartamento(String nome, String sigla) {
		Departamento departamento = new Departamento(nome, sigla);
		if(!departamentosDAO.contains(departamento)) {
			departamentosDAO.inserir(departamento);
			Alert cadastroRealizado = new Alert(AlertType.INFORMATION);
			cadastroRealizado.setTitle("Cadastro Realizado");
			cadastroRealizado.setHeaderText("Cadastro Realizado com sucesso");
			cadastroRealizado.show();			
		}else {
			Alert cadastroRealizado = new Alert(AlertType.WARNING);
			cadastroRealizado.setTitle("Cadastros invalido");
			cadastroRealizado.setHeaderText("O departamento já está cadastrado");
			cadastroRealizado.show();	
		}
		
	}
}
