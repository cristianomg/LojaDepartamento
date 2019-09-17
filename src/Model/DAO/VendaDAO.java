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
	private List<Venda> listaVendas = new ArrayList<Venda>();
	private VendaDAO(){
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
		return false;
	}

	@Override
	public boolean deletar(Venda venda) {
		for(Venda v: listaVendas) {
			if(v.getCodigo() == venda.getCodigo()) {
				listaVendas.remove(venda);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean atualizar(Venda vendaAntiga, Venda novaVenda) {
		for(Venda v: listaVendas) {
			if(v.getCodigo() == vendaAntiga.getCodigo()) {
				listaVendas.remove(vendaAntiga);
				listaVendas.add(novaVenda);
				return true;

			}
		}
		return false;
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
			FileOutputStream out = new FileOutputStream("vendas");
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
	
	public List<Venda> load() {
		if(new File("vendas").canRead() == true) {
			try {
				FileInputStream input = new FileInputStream("vendas");
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

