package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Model.DAO.DepartamentoDAO;
import Model.DAO.VendaDAO;
import Model.Entites.Departamento;
import Model.Entites.Venda;
import View.MenuPrincipalView;
import View.RelatorioMensalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class RelatorioMensalController implements Initializable {

	@FXML
	private ChoiceBox<String> choiceBoxMes;

	@FXML
	private Button btnMenuPrincipal;

	@FXML
	private Button btnExibirRelatorio;

	@FXML
	private TableView<Comissao> tabelaComissoes;

	@FXML
	private TableView<Venda> tabelaVendas;

	private VendaDAO vendaDAO = VendaDAO.getInstance();
	private DepartamentoDAO departamentoDAO = DepartamentoDAO.getInstance();
	private String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
							"Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarChoiceBox();
	}

	public void btnMenuPrincipal_Action() {
		RelatorioMensalView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());
	}

	public void btnExibirRelatorio_Action() {
		int mesValue = 0;
		if (choiceBoxMes.getValue() != null) {
			switch(choiceBoxMes.getValue()) {
			case "Janeiro":
				mesValue = 1;
				break;
			case "Fevereiro":
				mesValue = 2;
				break;
			case "Março":
				mesValue = 3;
				break;
			case "Abril":
				mesValue = 4;
				break;
			case "Maio":
				mesValue = 5;
				break;
			case "Junho":
				mesValue = 6;
				break;
			case "Julho":
				mesValue = 7;
				break;
			case "Agosto":
				mesValue = 8;
				break;
			case "Setembro":
				mesValue = 9;
				break;
			case "Outubro":
				mesValue = 10;
				break;
			case "Novembro":
				mesValue = 11;
				break;
			case "Dezembro":
				mesValue = 12;
				break;
			}
			fazerRelatorioMensal(mesValue);
		}
	}

	public void fazerRelatorioMensal(int mes) {
		List<Venda> vendasMes = new ArrayList<Venda>();
		Map<Departamento, Double> comissaoPorDepartamento = new HashMap<Departamento, Double>();
		departamentoDAO.getLista().stream().forEach(departamento -> {
			comissaoPorDepartamento.put(departamento, (double) 0);
		});
		vendaDAO.getLista().stream().filter(x -> x.getData().getMonthValue() == mes).forEach(x -> vendasMes.add(x));
		vendasMes.stream().forEach(x -> {
			double valor = comissaoPorDepartamento.get(x.getDepartamento());
			comissaoPorDepartamento.put(x.getDepartamento(), x.getComissaoFuncionario() + valor);
		});
		carregarTabelaVenda(vendasMes);
		carregarTabelaComissoes(comissaoPorDepartamento);
	}
	
	public void carregarChoiceBox() {
		ObservableList<String> mesesObservados = FXCollections.observableArrayList(meses);
		choiceBoxMes.getItems().setAll(mesesObservados);
	}
	
	public void carregarTabelaVenda(List<Venda> vendas) {
		ObservableList<Venda> vendasObservadas = FXCollections.observableList(vendas);
		tabelaVendas.getItems().setAll(vendasObservadas);
	}
	
	public void carregarTabelaComissoes(Map<Departamento, Double> comissoesMes) {
		List<Comissao> comissoes = new ArrayList<Comissao>();
		comissoesMes.keySet().forEach( x -> comissoes.add(new Comissao(x, comissoesMes.get(x))));
		ObservableList<Comissao> comissoesObservadas = FXCollections.observableList(comissoes);
		tabelaComissoes.getItems().setAll(comissoesObservadas);
	}
	
	public class Comissao {
		private Departamento departamento;
		private Double comissao;
		
		public Comissao(Departamento departamento, Double comissao) {
			super();
			this.departamento = departamento;
			this.comissao = comissao;
		}
		public Departamento getDepartamento() {
			return departamento;
		}
		public void setDepartamento(Departamento departamento) {
			this.departamento = departamento;
		}
		public Double getComissao() {
			return comissao;
		}
		public void setComissao(Double comissao) {
			this.comissao = comissao;
		}
	}
}
