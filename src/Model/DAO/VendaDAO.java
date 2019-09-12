package Model.DAO;

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
		throw new VendaNaoEncontradaException("Erro: Codigo de venda não encontrado, tente novamente!!!");
	}
}

