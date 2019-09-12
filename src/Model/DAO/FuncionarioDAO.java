package Model.DAO;

import java.util.ArrayList;
import java.util.List;

import Exceptions.FuncionarioNaoEncontradoException;
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
	public Funcionario getFuncionario(String matricula, String senha) throws FuncionarioNaoEncontradoException {
		for (Funcionario f: listaFuncionario) {
			if (f.getMatricula().equals(matricula) && f.getSenha().equals(senha)) {
				return f;
			}
		}
		throw new FuncionarioNaoEncontradoException("Erro: Matricula ou senha invalida tente novamente!!!");
	}
	public Funcionario getFuncionario(String matricula) throws FuncionarioNaoEncontradoException {
		for (Funcionario f: listaFuncionario) {
			if (f.getMatricula().equals(matricula)) {
				return f;
			}
		}
		throw new FuncionarioNaoEncontradoException("Erro: Matricula ou senha invalida tente novamente!!!");
	}
}
