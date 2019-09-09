package Controller;

import java.util.List;

import Model.DAO.ClienteDAO;
import Model.DAO.DepartamentoDAO;
import Model.Entites.Cliente;
import View.ClienteView;

public class ControllerListagem {
	private static ClienteDAO clientes = ClienteDAO.getInstance(); 
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	
	public static void listarClientes(){
		ClienteView.listarClientes(clientes.getLista());
	}
	
	public static void listaDepartamento() {
	}
		
}
