package Model.DAO;

import java.util.ArrayList;
import java.util.List;

import Model.Entites.Funcionario;

public class FuncionarioDAO implements InterfaceDAO<Funcionario> {
	private static FuncionarioDAO uniqueInstance;
	private List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
	
	private FuncionarioDAO(){
	}
	
	public static synchronized FuncionarioDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new FuncionarioDAO();
		}
		return uniqueInstance;
	}

	@Override
	public List<Funcionario> getLista() {
		return listaFuncionario;
	}

	@Override
	public boolean inserir(Funcionario funcionario) {
		listaFuncionario.add(funcionario);
		return false;
	}

	@Override
	public boolean deletar(Funcionario funcionario) {
		for (Funcionario f: listaFuncionario) {
			if(funcionario.getMatricula().equals(f.getMatricula())) {
				listaFuncionario.remove(f);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean atualizar(Funcionario objetoAntigo, Funcionario novoObjeto) {
		for (Funcionario f: listaFuncionario) {
			if(objetoAntigo.getMatricula().equals(f.getMatricula())) {
				listaFuncionario.remove(f);
				listaFuncionario.add(novoObjeto);
				return true;
			}
		}
		return false;
	}
}
