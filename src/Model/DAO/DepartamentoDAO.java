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

import Exceptions.DepartamentoNaoEncontradoException;
import Model.Entites.Cliente;
import Model.Entites.Departamento;

public class DepartamentoDAO implements InterfaceDAO <Departamento> {
	private static DepartamentoDAO uniqueInstance;
	private List<Departamento> listaDepartamentos;
	
	private DepartamentoDAO(){
		this.listaDepartamentos = this.load();
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
		this.save();
		return true;
	}

	@Override
	public boolean deletar(Departamento departamento){
		for(Departamento dep: listaDepartamentos) {
			if(dep.getId() == departamento.getId()) {
				listaDepartamentos.remove(dep);
				this.save();
				return true;
			}
		}
		System.out.println("Erro: Departamento não encontrado no sistema!!!");
		return false;
	}

	@Override
	public boolean atualizar(Departamento objetoAntigo, Departamento novoObjeto) {
		for(Departamento dep: listaDepartamentos) {
			if(objetoAntigo.getId() == dep.getId()) {
				listaDepartamentos.remove(dep);
				listaDepartamentos.add(novoObjeto);
				this.save();
			}
		}
		return false;
	}
	public Departamento getDepartamento(String nome) throws DepartamentoNaoEncontradoException{
		for(Departamento dep: listaDepartamentos) {
			if(dep.getNome().equals(nome)) {
				return dep;
			}
		}
		throw new DepartamentoNaoEncontradoException("Erro: Departamento não encontrado no sistema!!");
	}
	
	public Departamento getDepartamento(int id) throws DepartamentoNaoEncontradoException {
		for(Departamento dep: listaDepartamentos) {
			if(dep.getId() == id) {
				return dep;
			}
		}
		throw new DepartamentoNaoEncontradoException("Erro: Departamento não encontrado no sistema!!");

	}
	
	public void save() {
		try {
			FileOutputStream out = new FileOutputStream("departamentos");
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			
			objOut.writeObject(this.listaDepartamentos);
			objOut.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Departamento> load() {
		if(new File("departamentos").canRead() == true) {
			try {
				FileInputStream input = new FileInputStream("departamentos");
				ObjectInputStream objIn = new ObjectInputStream(input);
				List<Departamento> listaDepartamentos = (List<Departamento>) objIn.readObject();
				objIn.close();
				return listaDepartamentos;
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
		return new ArrayList<Departamento>();
		
	}


	}