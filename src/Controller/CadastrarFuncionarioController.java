package Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.DAO.DepartamentoDAO;
import Model.DAO.FuncionarioDAO;
import Model.Entites.Departamento;
import Model.Entites.Funcionario;
import View.CadastrarFuncionarioView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarFuncionarioController implements Initializable {

	@FXML
	private PasswordField txfSenha;

	@FXML
	private TextField txfNome;

	@FXML
	private Button btnSalvar;

	@FXML
	private TextField txfMatricula;

	@FXML
	private CheckBox CheckCurso;
	
    @FXML
    private ChoiceBox<Departamento> choiceBoxDep;

	@FXML
	private Button botaoInserir2;

	@FXML
	private Button btnMenuPrincipal;

	@FXML
	private TableView<Funcionario> tabelaFuncionarios;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnAtualizar;
	
	@FXML
	private Button btnCancelar;
	
	private FuncionarioDAO funcionariosDAO = FuncionarioDAO.getInstance();
	private DepartamentoDAO departamentosDAO = DepartamentoDAO.getInstance();
	private Funcionario funcionarioSelecionado;
	private boolean ehInserir;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.tabelaFuncionarios.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.tabelaFuncionarios.getSelectionModel().selectedItemProperty()
		.addListener((observador, funcionarioAntigo, funcionarioNovo)->{
			if(funcionarioNovo != null) {
				funcionarioSelecionado = funcionarioNovo;
				txfNome.setText(funcionarioSelecionado.getNome());
				txfMatricula.setText(funcionarioSelecionado.getMatricula());
				txfSenha.setText(funcionarioSelecionado.getSenha());
				if (funcionarioSelecionado.isEnsinoSuperior()) {
					CheckCurso.setSelected(true);
				}else CheckCurso.setSelected(false);
			}
		});
		CheckCurso.setStyle(
			    "-fx-border-color: lightblue; "
			    + "-fx-border-insets: -5; "
			    + "-fx-border-radius: 5;"
			    + "-fx-border-style: dotted;"
			    + "-fx-border-width: 2;");
		carregarTabela();
		carregarChoiceBox();
		habilitarBotoes(false);
	}
	
	private void habilitarBotoes(boolean habilitar) {
		txfNome.setDisable(!habilitar);
		txfMatricula.setDisable(!habilitar);
		txfSenha.setDisable(!habilitar);
		btnInserir.setDisable(habilitar);
		btnExcluir.setDisable(habilitar);
		btnAtualizar.setDisable(habilitar);
		btnExcluir.setDisable(habilitar);
		btnSalvar.setDisable(!habilitar);
		btnCancelar.setDisable(!habilitar);
		choiceBoxDep.setDisable(!habilitar);
		CheckCurso.setDisable(!habilitar);
	}
	
	private void carregarTabela() {
		List<Funcionario> funcionarios = funcionariosDAO.getLista();
		ObservableList<Funcionario> funcionarioObservados = FXCollections.observableList(funcionarios);
		this.tabelaFuncionarios.getItems().setAll(funcionarioObservados);
	}
	
	private void carregarChoiceBox() {
		List<Departamento> departamentos = departamentosDAO.getLista();
		ObservableList<Departamento> departamentosObservados = FXCollections.observableList(departamentos);
		this.choiceBoxDep.getItems().setAll(departamentosObservados);
	}
	
	public void btnMenuPrincipal_Action() {
		CadastrarFuncionarioView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView(); 
		menuPrincipal.start(new Stage());

	}
	
	public void btnInserir_Action() {
		ehInserir = true;
		habilitarBotoes(true);
		txfNome.setText("");
		txfMatricula.setText("");
		txfSenha.setText("");
		CheckCurso.setSelected(false);
	}
	
	public void btnAtualizar_Action() {
		ehInserir = false;
		habilitarBotoes(true);
		txfMatricula.setDisable(true);
		txfNome.setDisable(true);
		CheckCurso.setDisable(true);
		choiceBoxDep.setDisable(true);
	}
	
	public void btnExcluir_Action() {
		Alert confirmacao = new Alert(AlertType.CONFIRMATION);
		confirmacao.setTitle("Confirmação");
		confirmacao.setHeaderText("Confirmação de exclusão do contato");
		confirmacao.setContentText("Tem certeza deseja excluir o contato?");
		Optional<ButtonType> resultadoConfirmacao = confirmacao.showAndWait();
		if (resultadoConfirmacao.isPresent() && resultadoConfirmacao.get() == ButtonType.OK) {
			funcionariosDAO.deletar(this.funcionarioSelecionado);
			carregarTabela();
			this.tabelaFuncionarios.getSelectionModel().selectFirst();
		}
	}
	
	public void btnSalvar_Action() {	
		if (!txfNome.getText().isBlank() && !txfMatricula.getText().isBlank() &&
				!txfSenha.getText().isBlank() && choiceBoxDep.getValue() != null) {
			if (ehInserir) {
				cadastrarFuncionario(txfNome.getText(), txfMatricula.getText(), 
									 txfSenha.getText(), choiceBoxDep.getValue(),
									 CheckCurso.isSelected());
			}else {
				Funcionario funcionarioAtualizar = funcionarioSelecionado;
				funcionarioAtualizar.setSenha(txfSenha.getText());
				funcionariosDAO.atualizar(funcionarioAtualizar);
			}
			habilitarBotoes(false);
			carregarTabela();
			this.tabelaFuncionarios.getSelectionModel().selectFirst();
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
		this.tabelaFuncionarios.getSelectionModel().selectFirst();
	}
	

	public void cadastrarFuncionario(String nome, String matricula, String senha,
			Departamento departamento, boolean cursoSuperior) {
			if(!funcionariosDAO.testeFuncionarioExiste(matricula)) {
				Funcionario f = new Funcionario(nome, matricula, senha);
				f.setEnsinoSuperior(cursoSuperior);
				f.setDepartamento(departamento);
				departamento.addFuncionarioList(f);
				funcionariosDAO.inserir(f);
				departamentosDAO.atualizar(departamento);
				Alert cadastroRealizado = new Alert(AlertType.INFORMATION);
				cadastroRealizado.setTitle("Cadastro Realizado");
				cadastroRealizado.setHeaderText("Cadastro Realizado com sucesso");
				cadastroRealizado.show();
			}
			else {
				Alert cadastroRealizado = new Alert(AlertType.WARNING);
				cadastroRealizado.setTitle("Cadastros invalido");
				cadastroRealizado.setHeaderText("O funcionário já está cadastrado");
				cadastroRealizado.show();	
			}
	}
}
