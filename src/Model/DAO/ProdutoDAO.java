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

import Exceptions.ProdutoNaoEncontradoException;
import Model.Entites.Produto;

public class ProdutoDAO implements InterfaceDAO<Produto> {
	private static ProdutoDAO uniqueInstance;
	private List<Produto> listaProdutos = new ArrayList<Produto>();
	
	private ProdutoDAO(){
		this.listaProdutos = this.load();
	}
	
	public static synchronized ProdutoDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new ProdutoDAO();
		}
		return uniqueInstance;
	}

	@Override
	public List<Produto> getLista() {
		return listaProdutos;
	}

	@Override
	public boolean inserir(Produto produto) {
		listaProdutos.add(produto);
		this.save();
		return true;
	}

	@Override
	public boolean deletar(Produto produto) {
		try {
			listaProdutos.remove(produto);
			this.save();
			return true;
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean atualizar(Produto produto) {
		Produto p;
		try {
			p = this.getProduto(produto.getId());
			listaProdutos.remove(p);
			listaProdutos.add(produto);
			this.save();
			return true;
		} catch (ProdutoNaoEncontradoException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}
	
	public List<Produto> getProdutoDepartamento(int idDepartamento) {
		List<Produto> produtosDepartamento = new ArrayList<Produto>();
		for (Produto p: listaProdutos) {
			if (p.getDepartamento().getId()== idDepartamento) {
				produtosDepartamento.add(p);
			}
		}
		return produtosDepartamento;
	}
	public Produto getProduto(int idProduto) throws ProdutoNaoEncontradoException{
		for (Produto p: listaProdutos) {
			if(p.getId()==idProduto) {
				return p;
			}
		}
		throw new ProdutoNaoEncontradoException("Erro: Produto não encontrado!!!");
	}
	
	public boolean existe(Produto produto) {
		for(Produto p: listaProdutos) {
			if(p.equals(produto)) return true;
		}
		return false;
	}
	public void save() {
		try {
			FileOutputStream out = new FileOutputStream("database/produtos");
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			
			objOut.writeObject(this.listaProdutos);
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
	public List<Produto> load() {
		if(new File("database/produtos").canRead() == true) {
			try {
				FileInputStream input = new FileInputStream("database/produtos");
				ObjectInputStream objIn = new ObjectInputStream(input);
				List<Produto> listaProdutos = (List<Produto>) objIn.readObject();
				objIn.close();
				return listaProdutos;
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
		return new ArrayList<Produto>();
		
	}
}

