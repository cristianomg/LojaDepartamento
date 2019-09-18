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
	
	public void listarClientes(){
		ClienteView.listarClientes(clientes.getLista());
	}
	public void listaFuncionario() {
		FuncionarioView.listarFuncionarios(funcionarios.getLista());
		}
	public void listaDepartamentos() {
		DepartamentoView.listarDepartamentos(departamentos.getLista());
	}
	public void listarProdutos() {
		ProdutoView.listaProdutos(produtos.getLista());
	}
	public void listarVendas() {
		VendaView.listarVendas(vendas.getLista());
	}
}
