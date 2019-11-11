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
import View.RelatorioAnualView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RelatorioAnualController implements Initializable {

	@FXML
	private TextField txfAno;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void btnMenuPrincipal_Action() {
		RelatorioAnualView.getStage().close();
		MenuPrincipalView menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());
	}

	public void btnExibirRelatorio_Action() {
		try {
			if (txfAno.getText() != null) {
				fazerRelatorioAnual(Integer.parseInt(txfAno.getText()));
			}	
		}catch (NumberFormatException e) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro");
			erro.setContentText("Insira um ano valido.");
			erro.show();
		}
	}

	public void fazerRelatorioAnual(int ano) {
		List<Venda> vendasAno = new ArrayList<Venda>();
		Map<Departamento, Double> comissaoPorDepartamento = new HashMap<Departamento, Double>();
		departamentoDAO.getLista().stream().forEach(departamento -> {
			comissaoPorDepartamento.put(departamento, (double) 0);
		});
		vendaDAO.getLista().stream().filter(x -> x.getData().getYear() == ano).forEach(x -> vendasAno.add(x));
		vendasAno.stream().forEach(x -> {
			double valor = comissaoPorDepartamento.get(x.getDepartamento());
			comissaoPorDepartamento.put(x.getDepartamento(), x.getComissaoFuncionario() + valor);
		});
		carregarTabelaVenda(vendasAno);
		carregarTabelaComissoes(comissaoPorDepartamento);
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
