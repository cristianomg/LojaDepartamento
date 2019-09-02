package Model.DAO;

import java.util.ArrayList;

import Model.Entites.Cliente;

public class ClienteDAO {
	private static ClienteDAO uniqueInstance;
	
	private ClienteDAO(){
	}
	
	public static synchronized ClienteDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new ClienteDAO();
		}
		return uniqueInstance;
	}

	public ArrayList<Cliente> getClientes(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		return clientes;
	}
	
	public Cliente getCliente(String nome, String cpf_cnpj) {
		Cliente cliente = new Cliente();
		return cliente;
	}
	
	public void inserirCliente(Cliente cliente) {
	}
	
	public void atualizarCliente(Cliente cliente) {
		
	}
	public void removerCliente(Cliente cliente) {
		
	}
}
