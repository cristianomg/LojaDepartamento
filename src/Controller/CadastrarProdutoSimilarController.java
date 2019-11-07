package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import View.CadastrarProdutoSimilarView;
import View.MenuPrincipalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarProdutoSimilarController implements Initializable {
	@FXML
	private TextField txfNome;

	@FXML
	private Button btnSalvar;

	@FXML
	private TextField txfPreco;

	@FXML
	private Button btnMenuPrincipal;

	@FXML
	private TextField txfDescricao;

	@FXML
	private ChoiceBox<?> choiceBox;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button botaoAtualizar;

	@FXML
	private TableView<?> tabelaProdutoSimilar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Pane btnAtualizar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void btnMenuPrincipal_Action() {
		CadastrarProdutoSimilarView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());

	}

}
