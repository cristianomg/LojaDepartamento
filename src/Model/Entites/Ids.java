package Model.Entites;

import java.io.Serializable;
import java.util.HashSet;

public class Ids implements Serializable{
	private static final long serialVersionUID = -3821688075275860411L;
	private HashSet<Integer> idClientes = new HashSet<Integer>();
	private HashSet<Integer> idDepartamentos = new HashSet<Integer>();
	private HashSet<Integer> idProdutos = new HashSet<Integer>();
	private HashSet<Integer> idVenda = new HashSet<Integer>();
	
	
	public HashSet<Integer> getIdClientes() {
		return idClientes;
	}
	public void setIdClientes(HashSet<Integer> idClientes) {
		this.idClientes = idClientes;
	}
	public HashSet<Integer> getIdDepartamentos() {
		return idDepartamentos;
	}
	public void setIdDepartamentos(HashSet<Integer> idDepartamentos) {
		this.idDepartamentos = idDepartamentos;
	}
	public HashSet<Integer> getIdProdutos() {
		return idProdutos;
	}
	public void setIdProdutos(HashSet<Integer> idProdutos) {
		this.idProdutos = idProdutos;
	}
	public HashSet<Integer> getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(HashSet<Integer> idVenda) {
		this.idVenda = idVenda;
	}
}
