package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.FuncionarioNaoEncontradoException;
import Model.DAO.FuncionarioDAO;
import Model.Entites.Funcionario;
import Model.Entites.Venda;
import View.BuscarVendedorView;
import View.MenuPrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuscarVendedorController implements Initializable {

	@FXML
	private Button btnMenuPrincipal;

	@FXML
	private DatePicker txfDateFinal;

	@FXML
	private TableView<Busca> tabelaFuncionario;

	@FXML
	private DatePicker txfDateInitial;

	@FXML
	private TextField txfMatricula;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableView<Venda> tabelaVendas;
	private FuncionarioDAO funcionariosDAO = FuncionarioDAO.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabelaFuncionario.getColumns().forEach(x -> {
			x.setResizable(false);
			x.setReorderable(false);
		});
		tabelaVendas.getColumns().forEach(x -> {
			x.setResizable(false);
			x.setReorderable(false);
		});

	}

	public void btnMenuPrincipal_Action() {
		BuscarVendedorView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());

	}

	public void btnBuscar_Action() {
		carregarVendas();
	}

	private void carregarVendas() {
		Funcionario funcionario;
		try {
			if (!txfMatricula.getText().isBlank() && txfDateInitial.getValue() != null
					&& txfDateFinal.getValue() != null
					&& txfDateFinal.getValue().compareTo(txfDateInitial.getValue()) > 0) {
				funcionario = funcionariosDAO.getFuncionario(txfMatricula.getText());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataInicio = txfDateInitial.getValue();
				LocalDate dataFinal = txfDateFinal.getValue();
				Busca busca = new Busca(txfMatricula.getText(), dataInicio.format(formatter),
						dataFinal.format(formatter));
				tabelaFuncionario.getItems().setAll(busca);
				buscarVendaPeriodo(funcionario, dataInicio, dataFinal);
				
			} else {
				Alert erro = new Alert(AlertType.ERROR);
				erro.setTitle("Erro");
				erro.setHeaderText("Informações incompletas.");
				erro.setContentText("Preencha todos campos para completar a busca.");
				erro.show();
			}
		} catch (FuncionarioNaoEncontradoException e) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro");
			erro.setHeaderText("Funcionario não encontrado.");
			erro.setContentText(e.getMessage());
			erro.show();
		}
	}

	public void buscarVendaPeriodo(Funcionario funcionario, LocalDate dataInicial, LocalDate dataFinal) {
		try {
			List<Venda> vendasFuncionario = funcionario.getListaVenda();
			List<Venda> vendasPeriodo = new ArrayList<Venda>();
			vendasFuncionario.stream().filter(x -> x.getData().compareTo(dataInicial) >= 0 && x.getData().compareTo(dataFinal) <= 0)
			.forEach(x -> vendasPeriodo.add(x));
			ObservableList<Venda> vendasPeriodo1 = FXCollections.observableList(vendasPeriodo);
			tabelaVendas.getItems().setAll(vendasPeriodo1);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public class Busca {
		private String matricula;
		private String dataInicial;
		private String dataFinal;

		public Busca(String matricula, String dataInicial, String dataFinal) {
			super();
			this.matricula = matricula;
			this.dataInicial = dataInicial;
			this.dataFinal = dataFinal;
		}

		public String getMatricula() {
			return matricula;
		}

		public String getDataInicial() {
			return dataInicial;
		}

		public String getDataFinal() {
			return dataFinal;
		}

	}
}
