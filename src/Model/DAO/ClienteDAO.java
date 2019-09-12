package Model.DAO;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ClienteNaoEncontradoException;
import Model.Entites.Cliente;

public class ClienteDAO implements InterfaceDAO <Cliente> {
	private static ClienteDAO uniqueInstance;
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	
	private ClienteDAO(){
	}
	
	public static synchronized ClienteDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new ClienteDAO();
		}
		return uniqueInstance;
	}

	@Override
	public List<Cliente> getLista() {
		return listaClientes;
	}

	@Override
	public boolean inserir(Cliente objeto) {
		try{
			listaClientes.add(objeto);
			return true;
		}
		catch (Exception e){
		return false;
		}
	}

	@Override
	public boolean deletar(Cliente objeto) {
		for(Cliente cliente: listaClientes) {
			if (objeto.getCpf_cnpj().equals(cliente.getCpf_cnpj())){
				listaClientes.remove(objeto);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean atualizar(Cliente antigo, Cliente novo) {
		for(Cliente cliente: listaClientes) {
			if (cliente.getCpf_cnpj().equals(antigo.getCpf_cnpj())){
				listaClientes.remove(cliente);
				listaClientes.add(novo);
				return true;
			}
		}
		return false;
	}
	
	public Cliente getCliente(String cpf_cnpj) throws ClienteNaoEncontradoException{
		for(Cliente cliente: listaClientes) {
			if (cliente.getCpf_cnpj().equals(cpf_cnpj)){
				return cliente;
			}
		}
		throw new ClienteNaoEncontradoException("Erro: cpf_cnpj invalido, tente novamente!!!");
	}
	
}
