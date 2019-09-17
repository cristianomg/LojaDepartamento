package Model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Exceptions.ClienteNaoEncontradoException;
import Model.Entites.Cliente;

public class ClienteDAO implements InterfaceDAO <Cliente> {
	private static ClienteDAO uniqueInstance;
	private List<Cliente> listaClientes;
	
	private ClienteDAO(){
		this.listaClientes = this.load();
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
			this.save();
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
				this.save();
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
				this.save();
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
	
	public void save() {
		try {
			FileOutputStream out = new FileOutputStream("database/clientes");
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			
			objOut.writeObject(this.listaClientes);
			objOut.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Cliente> load() {
		if(new File("database/clientes").canRead() == true) {
			try {
				FileInputStream input = new FileInputStream("database/clientes");
				ObjectInputStream objIn = new ObjectInputStream(input);
				List<Cliente> listaClientes = (List<Cliente>) objIn.readObject();
				objIn.close();
				return listaClientes;
			}
			catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
			catch(ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return new ArrayList<Cliente>();
		
	}
}
