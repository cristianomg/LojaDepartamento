package Controller;

import Exceptions.FuncionarioDesligadoException;
import Exceptions.FuncionarioNaoEncontradoException;
import Model.DAO.FuncionarioDAO;
import Model.Entites.Funcionario;
import View.LoginView;
import View.MenuPrincipalView;
import View.PainelVendasView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private PasswordField txfSenha;

	@FXML
	private Button btnCancel;

	@FXML
	private TextField txfMatricula;

	@FXML
	private Button btnLogin;
	private FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstance();

	public void btnCancel_Action() {
		LoginView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());

	}

	public void btnLotgin_Action() {
		try {
			Funcionario funcionario = autentificacao(txfMatricula.getText(), txfSenha.getText());
			MenuPrincipalController.setUserLogado(funcionario);
			LoginView.getStage().close();
			PainelVendasView painelVendas = new PainelVendasView();
			painelVendas.start(new Stage());

		} catch (FuncionarioNaoEncontradoException | FuncionarioDesligadoException e) {
			Alert loginErro = new Alert(AlertType.ERROR);
			loginErro.setTitle("Login Falhou");
			loginErro.setHeaderText(e.getMessage());
			loginErro.show();
		}
	}

	public Funcionario autentificacao(String matricula, String senha)
			throws FuncionarioNaoEncontradoException, FuncionarioDesligadoException {
		Funcionario funcionario = funcionarioDAO.getFuncionario(matricula, senha);
		if (!funcionario.isDesligado()) {
			return funcionario;
		} else {
			throw new FuncionarioDesligadoException(
					"O funcionario está desligado da loja e não possui acesso a vendas.");
		}
	}

}
