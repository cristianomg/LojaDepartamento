package Controller;

<<<<<<< HEAD
import Model.DAO.ClienteDAO;
import Model.DAO.DepartamentoDAO;
import Model.DAO.FuncionarioDAO;
import View.ClienteView;
import View.DepartamentoView;
import View.FuncionarioView;
=======
import java.util.List;

import Model.DAO.ClienteDAO;
import Model.DAO.DepartamentoDAO;
import Model.Entites.Cliente;
import View.ClienteView;
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40

public class ControllerListagem {
	private static ClienteDAO clientes = ClienteDAO.getInstance(); 
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
<<<<<<< HEAD
	private static FuncionarioDAO funcionarios = FuncionarioDAO.getInstance();
=======
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
	
	public static void listarClientes(){
		ClienteView.listarClientes(clientes.getLista());
	}
	
<<<<<<< HEAD
	public static void listaFuncionario() {
		FuncionarioView.listarFuncionarios(funcionarios.getLista());
		}
	
	public static void listaDepartamento() {
		DepartamentoView.listarDepartamentos(departamentos.getLista());
=======
	public static void listaDepartamento() {
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
	}
		
}
