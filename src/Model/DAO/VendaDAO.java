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

import Exceptions.VendaNaoEncontradaException;
import Model.Entites.Venda;

public class VendaDAO implements InterfaceDAO<Venda>{
	private static VendaDAO uniqueInstance;
	private List<Venda> listaVendas;
	private VendaDAO(){
		listaVendas = this.load();
	}
	
	public static synchronized VendaDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new VendaDAO();
		}
		return uniqueInstance;
	}

	@Override
	public List<Venda> getLista() {
		return listaVendas;
	}

	@Override
	public boolean inserir(Venda venda) {
		listaVendas.add(venda);
		this.save();
		return false;
	}

	@Override
	public boolean deletar(Venda venda) {
		for(Venda v: listaVendas) {
			if(v.getCodigo() == venda.getCodigo()) {
				listaVendas.remove(venda);
				this.save();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean atualizar(Venda novaVenda) {
		Venda antiga;
		try {
			antiga = this.getVenda(novaVenda.getCodigo());
			listaVendas.remove(antiga);
			listaVendas.add(novaVenda);
			this.save();
			return true;
		} catch (VendaNaoEncontradaException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public Venda getVenda(int codigoVenda) throws VendaNaoEncontradaException {
		for (Venda v: listaVendas) {
			if(v.getCodigo() == codigoVenda) {
				return v;
			}
		}
		throw new VendaNaoEncontradaException("Erro: Codigo de venda n�o encontrado, tente novamente!!!");
	}
	
	public void save() {
		try {
			FileOutputStream out = new FileOutputStream("database/vendas");
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			
			objOut.writeObject(this.listaVendas);
			objOut.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> load() {
		if(new File("database/vendas").canRead() == true) {
			try {
				FileInputStream input = new FileInputStream("database/vendas");
				ObjectInputStream objIn = new ObjectInputStream(input);
				List<Venda> listaFuncionarios = (List<Venda>) objIn.readObject();
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
		return new ArrayList<Venda>();
		
	}
}

