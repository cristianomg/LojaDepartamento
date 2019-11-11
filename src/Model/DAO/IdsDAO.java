package Model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import Model.Entites.Ids;

public class IdsDAO {
	private static IdsDAO uniqueInstance;
	private Ids ids;
	
	private IdsDAO(){
		this.ids = this.load();
	}
	
	public static synchronized IdsDAO getInstance() {
		
		if (uniqueInstance == null){
			uniqueInstance = new IdsDAO();
		}
		return uniqueInstance;
	}
	
	public HashSet<Integer> getIdsClientes(){
		return this.ids.getIdClientes();
	}

	public HashSet<Integer> getIdsDepartamentos(){
		return this.ids.getIdDepartamentos();
	}
	
	public HashSet<Integer> getIdsProdutos(){
		return this.ids.getIdProdutos();
	}
	
	public HashSet<Integer> getIdsVendas(){
		return this.ids.getIdVenda();
	}
	
	public void save() {
		try {
			FileOutputStream out = new FileOutputStream("database/ids");
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			
			objOut.writeObject(this.ids);
			objOut.close();
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Ids load() {
		if(new File("database/ids").canRead() == true) {
			try {
				FileInputStream input = new FileInputStream("database/ids");
				ObjectInputStream objIn = new ObjectInputStream(input);
				Ids ids = (Ids) objIn.readObject();
				objIn.close();
				return ids;
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
		return new Ids();
		
	}
}
