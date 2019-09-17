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

import Exceptions.FuncionarioNaoEncontradoException;
import Model.Entites.Funcionario;

public class FuncionarioDAO implements InterfaceDAO<Funcionario> {
	private static FuncionarioDAO uniqueInstance;
	private List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
	
	private FuncionarioDAO(){
		this.listaFuncionario = this.load();
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
		this.save();
		return false;
	}

	@Override
	public boolean deletar(Funcionario funcionario) {
		for (Funcionario f: listaFuncionario) {
			if(funcionario.getMatricula().equals(f.getMatricula())) {
				listaFuncionario.remove(f);
				this.save();
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
				this.save();
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
	public void save() {
		try {
			FileOutputStream out = new FileOutputStream("funcionarios");
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			
			objOut.writeObject(this.listaFuncionario);
			objOut.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Funcionario> load() {
		if(new File("funcionarios").canRead() == true) {
			try {
				FileInputStream input = new FileInputStream("funcionarios");
				ObjectInputStream objIn = new ObjectInputStream(input);
				List<Funcionario> listaFuncionarios = (List<Funcionario>) objIn.readObject();
				objIn.close();
				return listaFuncionarios;
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
		return new ArrayList<Funcionario>();
		
	}
}
