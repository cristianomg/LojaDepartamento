package Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Exceptions.ValidateCpfException;
import Model.DAO.ClienteDAO;
import Model.Entites.Cliente;
import Model.Entites.Logradouro.Cidade;
import Model.Entites.Logradouro.Endereco;
import Model.Entites.Logradouro.Estado;
import View.CadastrarClienteView;
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

public class CadastrarClienteController implements Initializable{

    @FXML
    private TextField txfRua;

    @FXML
    private TextField txfNome;

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private TextField txfCpf_Cnpj;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnAtualizar;

    @FXML
    private TextField txfCep;

    @FXML
    private TextField txfCidade;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txfBairro;

    @FXML
    private TextField txfNumero;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<Cliente> tabelaCliente;

    @FXML
    private TextField txfEstado;
    
    @FXML
    private TextField txfTelefone;

    @FXML
    private Button btnExcluir;
    private Cliente clienteSelecionado;
	private boolean ehInserir;
	private ClienteDAO clientesDAO = ClienteDAO.getInstance(); 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.tabelaCliente.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.tabelaCliente.getSelectionModel().selectedItemProperty()
		.addListener((observador, clienteAntigo, clienteNovo)->{
			if(clienteNovo != null) {
				clienteSelecionado = clienteNovo;
				txfNome.setText(clienteSelecionado.getNome());
				txfCpf_Cnpj.setText(clienteSelecionado.getCpf_cnpj());
				txfEstado.setText(clienteSelecionado.getEndereco().getEstado().getNome());
				txfCidade.setText(clienteSelecionado.getEndereco().getCidade().getNome());
				txfBairro.setText(clienteSelecionado.getEndereco().getBairro());
				txfCep.setText(clienteSelecionado.getEndereco().getCep());
				txfNumero.setText(String.valueOf(clienteSelecionado.getEndereco().getNumero()));
				txfRua.setText(clienteSelecionado.getEndereco().getRua());
				txfTelefone.setText(String.valueOf(clienteSelecionado.getTelefone()));
			}
		});
		carregarTabela();
		habilitarBotoes(false);
		this.tabelaCliente.getSelectionModel().selectFirst();
		
	}
	
	public void btnMenuPrincipal_Action() {
		CadastrarClienteView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView(); 
		menuPrincipal.start(new Stage());

	}
	
	private void habilitarBotoes(boolean habilitar) {
		tabelaCliente.setDisable(habilitar);
		txfNome.setDisable(!habilitar);
		txfCpf_Cnpj.setDisable(!habilitar);
		txfRua.setDisable(!habilitar);
		txfBairro.setDisable(!habilitar);
		txfCidade.setDisable(!habilitar);
		txfEstado.setDisable(!habilitar);
		txfCep.setDisable(!habilitar);
		txfTelefone.setDisable(!habilitar);
		txfNumero.setDisable(!habilitar);
		btnInserir.setDisable(habilitar);
		btnExcluir.setDisable(habilitar);
		btnAtualizar.setDisable(habilitar);
		btnCancelar.setDisable(!habilitar);
		btnSalvar.setDisable(!habilitar);
	}

	private void carregarTabela() {
		List<Cliente> clientes = clientesDAO.getLista();
		ObservableList<Cliente> clientesObservados = FXCollections.observableList(clientes);
		this.tabelaCliente.getItems().setAll(clientesObservados);
	}
	
	public void btnInserir_Action() {
		ehInserir = true;
		habilitarBotoes(true);
		txfNome.setText("");
		txfCpf_Cnpj.setText("");
		txfEstado.setText("");
		txfCidade.setText("");
		txfBairro.setText("");
		txfCep.setText("");
		txfNumero.setText("");
		txfRua.setText("");
		txfTelefone.setText("");
	}
	
	public void btnAtualizar_Action() {
		ehInserir = false;
		habilitarBotoes(true);
		txfNome.setDisable(true);
		txfCpf_Cnpj.setDisable(true);
	}
	
	public void btnExcluir_Action() {
		Alert confirmacao = new Alert(AlertType.CONFIRMATION);
		confirmacao.setTitle("Confirmação");
		confirmacao.setHeaderText("Confirmação de exclusão do contato");
		confirmacao.setContentText("Tem certeza deseja excluir o contato?");
		Optional<ButtonType> resultadoConfirmacao = confirmacao.showAndWait();
		if (resultadoConfirmacao.isPresent() && resultadoConfirmacao.get() == ButtonType.OK) {
			clientesDAO.deletar(this.clienteSelecionado);
			carregarTabela();
			this.tabelaCliente.getSelectionModel().selectFirst();
		}
	}
	
	public void btnSalvar_Action() {	
		try {
			if (!txfNome.getText().isBlank() && !txfCpf_Cnpj.getText().isBlank() &&
					!txfBairro.getText().isBlank() && !txfCidade.getText().isBlank() &&
					!txfEstado.getText().isBlank() && !txfCep.getText().isBlank() &&
					!txfNumero.getText().isBlank() && !txfTelefone.getText().isBlank()) {
				if (ehInserir) {
					boolean cadastro = cadastrarCliente(txfNome.getText(), txfCpf_Cnpj.getText(), txfEstado.getText(),
							txfCidade.getText(), txfRua.getText(), Integer.parseInt(txfNumero.getText()),
							txfBairro.getText(), txfCep.getText(), Long.parseLong(txfTelefone.getText()));
					if (cadastro) {
						Alert cadastroRealizado = new Alert(AlertType.INFORMATION);
						cadastroRealizado.setTitle("Cadastro Realizado");
						cadastroRealizado.setHeaderText("Cadastro Realizado com sucesso");
						cadastroRealizado.show();
					}else {
						Alert cadastroRealizado = new Alert(AlertType.WARNING);
						cadastroRealizado.setTitle("Cadastro Falhou");
						cadastroRealizado.setHeaderText("Houve um problema, o cadastro não foi realizado.");
						cadastroRealizado.show();
					}
				} else {
					Cliente clienteAtualizar = clienteSelecionado;
					Estado estadoAtualizar = new Estado(txfEstado.getText());
					clienteAtualizar.getEndereco().setEstado(estadoAtualizar);
					clienteAtualizar.getEndereco().setCidade(new Cidade(txfCidade.getText(), estadoAtualizar));
					clienteAtualizar.getEndereco().setBairro(txfBairro.getText());
					clienteAtualizar.getEndereco().setCep(txfCep.getText());
					clienteAtualizar.getEndereco().setNumero(Integer.valueOf(txfNumero.getText()));
					clienteAtualizar.getEndereco().setRua(txfRua.getText());
					clienteAtualizar.setTelefone(Integer.parseInt(txfTelefone.getText()));
					clientesDAO.atualizar(clienteAtualizar);
					Alert cadastroRealizado = new Alert(AlertType.INFORMATION);
					cadastroRealizado.setTitle("Atualização Realizada");
					cadastroRealizado.setHeaderText("Atualização Realizada com sucesso");
					cadastroRealizado.show();
				}
				habilitarBotoes(false);
				carregarTabela();
				this.tabelaCliente.getSelectionModel().selectFirst();
			}else {
				Alert erro = new Alert(AlertType.ERROR);
				erro.setTitle("Erro");
				erro.setHeaderText("Informe todos os dados");
				erro.show();
			}
	}catch(NumberFormatException e){
		Alert erro = new Alert(AlertType.ERROR);
		erro.setTitle("Erro");
		erro.setHeaderText("Erro Formato Invalido.");
		erro.setContentText("Dados invalidos, tente novamente.");
		erro.show();
	}catch(ValidateCpfException e){
		Alert erro = new Alert(AlertType.ERROR);
		erro.setTitle("Erro");
		erro.setHeaderText("Erro Formato Invalido.");
		erro.setContentText(e.getMessage());
		erro.show();
	}	
	}

	public void btnCancelar_Action() {
		habilitarBotoes(false);
		ehInserir = false;
		carregarTabela();
		this.tabelaCliente.getSelectionModel().selectFirst();
	}
	
	private boolean cadastrarCliente(String nome, String cpf_cnpj, String estado, String cidade,
									String rua, int numero, String bairro, String cep, long telefone) throws ValidateCpfException{
		Estado _estado = new Estado(estado);
		Cidade _cidade = new Cidade(cidade, _estado);
		Cliente cliente = new Cliente(nome, cpf_cnpj, telefone);
		Endereco endereco = cadastrarEndereco(rua, numero, bairro, cep, _cidade, _estado);
		cliente.setEndereco(endereco);
		if(cpf_cnpj.length()!= 11) {
			throw new ValidateCpfException("Cpf invalido.");
		}
		if (!clientesDAO.testClienteExiste(cliente)) {
			clientesDAO.inserir(cliente);
			return true;
		}
		else {
			return false;
		}
	}
	
	private Endereco cadastrarEndereco(String rua , int numero, String bairro, String cep, Cidade cidade, Estado estado) {
		Endereco endereco = new Endereco(rua , numero, bairro, cep, cidade, estado);
		cidade.addEnderecoLista(endereco);
		estado.addCidadeLista(cidade);
		return endereco;
	}

}
