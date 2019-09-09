package Controller;

import Model.DAO.ClienteDAO;
import Model.DAO.DepartamentoDAO;
import Model.DAO.FuncionarioDAO;
import View.ClienteView;
import View.DepartamentoView;
import View.FuncionarioView;

public class ControllerListagem {
	private static ClienteDAO clientes = ClienteDAO.getInstance(); 
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
	public static void listarClientes(){
		ClienteView.listarClientes(clientes.getLista());
	}
	public static void listaFuncionario() {
		FuncionarioView.listarFuncionarios(funcionarios.getLista());
		}
	public static void listaDepartamentos() {
		DepartamentoView.listarDepartamentos(departamentos.getLista());
	}
}
