package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Exceptions.FuncionarioNaoEncontradoException;
import Exceptions.VendaNaoEncontradaException;
import Model.DAO.FuncionarioDAO;
import Model.DAO.VendaDAO;
import Model.Entites.Funcionario;
import Model.Entites.Venda;
import View.FuncionarioView;
import View.VendaView;

public class ControllerBusca {
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	private static VendaDAO vendas = VendaDAO.getInstance();

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
					System.out.println(venda.getData());
					System.out.println(dataInicio);
					System.out.println(dataFinal);
					if(venda.getData().compareTo(dataInicio) >= 0 && venda.getData().compareTo(dataFinal) <= 0) {
						System.out.println("entrou");
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
	public void buscarProdutoSimiliar() {
		
	}
}
