package Model.DAO;

import java.util.ArrayList;

import Model.Entites.Cliente;
import Model.Entites.Departamento;

public class DepartamentoDAO {
	private static DepartamentoDAO uniqueInstance;
	
	private DepartamentoDAO(){
	}
	
	public static synchronized DepartamentoDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new DepartamentoDAO();
		}
		return uniqueInstance;
	}

	public ArrayList<Departamento> getDepartamento(){
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		return departamentos;
	}
	
	public Departamento getDepartamento(String nome, String sigla) {
		Departamento departamento = new Departamento(0, sigla, sigla, null);
		return departamento;
	}
	
	public void inserirDepartamento(Departamento departamento) {
	}
	
	public void atualizarCliente(Cliente cliente) {
		
	}
	public void removerCliente(Cliente cliente) {
	}
	}