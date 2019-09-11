package Controller;

import Model.DAO.ClienteDAO;
import Model.DAO.DepartamentoDAO;
import Model.DAO.FuncionarioDAO;
import Model.DAO.ProdutoDAO;
import Model.DAO.VendaDAO;
import View.ClienteView;
import View.DepartamentoView;
import View.FuncionarioView;
import View.ProdutoView;
import View.VendaView;

public class ControllerListagem {
	private static ClienteDAO clientes = ClienteDAO.getInstance(); 
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	private static ProdutoDAO produtos = ProdutoDAO.getInstance();
	private static VendaDAO vendas = VendaDAO.getInstance();
	
	public static void listarClientes(){
		ClienteView.listarClientes(clientes.getLista());
	}
	public static void listaFuncionario() {
		FuncionarioView.listarFuncionarios(funcionarios.getLista());
		}
	public static void listaDepartamentos() {
		DepartamentoView.listarDepartamentos(departamentos.getLista());
	}
	public static void listarProdutos() {
		ProdutoView.listaProdutos(produtos.getLista());
	}
	public static void listarVendas() {
		VendaView.listarVendas(vendas.getLista());
	}
}
