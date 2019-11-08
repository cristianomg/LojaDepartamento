package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.FuncionarioNaoEncontradoException;
import Exceptions.ProdutoNaoEncontradoException;
import Exceptions.VendaNaoEncontradaException;
import Model.DAO.FuncionarioDAO;
import Model.DAO.ProdutoDAO;
import Model.DAO.VendaDAO;
import Model.Entites.Funcionario;
import Model.Entites.Produto;
import Model.Entites.ProdutoEletronico;
import Model.Entites.Venda;
import View.BuscarProdutosSimilaresView;
import View.BuscarVendaView;
import View.BuscarVendedorView;
import View.MenuBuscasView;
import View.MenuPrincipalView;
import View2.FuncionarioView;
import View2.VendaView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuBuscasController implements Initializable{
	 @FXML
	    private Button btnBuscarVendedor;

	    @FXML
	    private Button btnMenuPrincipal;

	    @FXML
	    private Button btnBuscarProdutosSimilares;

	    @FXML
	    private Button btnBuscarRegistroVenda;
	    
	    
		private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
		private static VendaDAO vendas = VendaDAO.getInstance();
		private static ProdutoDAO produtos = ProdutoDAO.getInstance();
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}		
		
	    public void btnBuscarVendedor_Action() {
			MenuBuscasView.getStage().close();
			BuscarVendedorView buscarVendedor = new BuscarVendedorView();
			buscarVendedor.start(new Stage());
	    }

	    public void btnBuscarRegistroVenda_Action() {
			MenuBuscasView.getStage().close();
			BuscarVendaView buscarVendas = new BuscarVendaView();
			buscarVendas.start(new Stage());
	    }

	    public void btnBuscarProdutosSimilares() {
			MenuBuscasView.getStage().close();
			BuscarProdutosSimilaresView buscarProdutoSimiliares = new BuscarProdutosSimilaresView();
			buscarProdutoSimiliares.start(new Stage());
	    }

	    public void btnMenuPrincipal_Action() {
			MenuBuscasView.getStage().close();
			MenuPrincipalView menuPrincipal = new MenuPrincipalView();
			menuPrincipal.start(new Stage());
	    }


	public void buscarVendedor(HashMap<String, String> request) {
		try {
			Funcionario funcionario = funcionarios.getFuncionario(request.get("matricula"));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataInicio = LocalDate.parse(request.get("dataInicial"), formatter);
			LocalDate dataFinal = LocalDate.parse(request.get("dataFinal"), formatter);
			List<Venda> vendasFuncionario = funcionario.getListaVenda();
			List<Venda> vendasPeriodo = new ArrayList<Venda>();
			if(vendasFuncionario.size()>0){
				for (Venda venda: vendasFuncionario) {
					if(venda.getData().compareTo(dataInicio) >= 0 && venda.getData().compareTo(dataFinal) <= 0) {
						vendasPeriodo.add(venda);
					}
				}
				FuncionarioView.buscarFuncionarioResponse(funcionario, vendasPeriodo, dataInicio, dataFinal);
			}
			else {
				System.out.println("O vendedor não realizou nenhuma venda até o momento");
			}
		} catch (FuncionarioNaoEncontradoException e)  {
			System.out.println(e.getMessage()); 
		}catch (DateTimeParseException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarRegistroVenda(int codigo) {
		Venda vendaEncontrada;
		try {
			vendaEncontrada = vendas.getVenda(codigo);
			VendaView.responseBuscaPorVenda(vendaEncontrada);
		} catch (VendaNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}


	}
	public List<ProdutoEletronico> buscarProdutoSimiliar(int id) {
		Produto produto;
		try {
			produto = produtos.getProduto(id);
			if (produto instanceof ProdutoEletronico) {
				if (!((ProdutoEletronico) produto).getListaSimilar().isEmpty()) {
					return ((ProdutoEletronico) produto).getListaSimilar();
				}
			}
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}


}
