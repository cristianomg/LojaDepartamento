package Model.DAO;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import Exceptions.ObjetoNaoEncontradoException;
=======
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
import Model.Entites.Departamento;

public class DepartamentoDAO implements InterfaceDAO <Departamento> {
	private static DepartamentoDAO uniqueInstance;
	private List<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	
	private DepartamentoDAO(){
	}
	
	public static synchronized DepartamentoDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new DepartamentoDAO();
		}
		return uniqueInstance;
	}

	@Override
	public List<Departamento> getLista() {
		return listaDepartamentos;
	}

	@Override
	public boolean inserir(Departamento departamento) {
		listaDepartamentos.add(departamento);
		return true;
	}

	@Override
	public boolean deletar(Departamento departamento){
		for(Departamento dep: listaDepartamentos) {
			if(dep.getId() == departamento.getId()) {
				listaDepartamentos.remove(dep);
				return true;
			}
		}
<<<<<<< HEAD
=======
		System.out.println("Erro: Departamento não encontrado no sistema!!!");
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
		return false;
	}

	@Override
	public boolean atualizar(Departamento objetoAntigo, Departamento novoObjeto) {
		for(Departamento dep: listaDepartamentos) {
			if(objetoAntigo.getId() == dep.getId()) {
				listaDepartamentos.remove(dep);
				listaDepartamentos.add(novoObjeto);
			}
		}
		return false;
<<<<<<< HEAD
	}
	public Departamento getDepartamento(String nome) throws ObjetoNaoEncontradoException{
		for(Departamento dep: listaDepartamentos) {
			if(dep.getNome().equals(nome)) {
				return dep;
			}
		}
		throw new ObjetoNaoEncontradoException("Erro: Departamento não encontrado no sistema!!");
	}
	
	public Departamento getDepartamento(int id) throws ObjetoNaoEncontradoException {
		for(Departamento dep: listaDepartamentos) {
			if(dep.getId() == id) {
				return dep;
			}
		}
		throw new ObjetoNaoEncontradoException("Erro: Departamento não encontrado no sistema!!");
=======
	}
	public Departamento getDepartamento(String nome) {
		for(Departamento dep: listaDepartamentos) {
			if(dep.getNome().equals(nome)) {
				return dep;
			}
		}
		System.out.println("Erro: Departamento não encontrado no sistema!!!");
		return null;
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
	}

	}