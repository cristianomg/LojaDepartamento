package Model.DAO;

import java.util.List;

public interface InterfaceDAO <T> {

	public List<T> getLista();
	public boolean inserir(T objeto);
	public boolean deletar(T objeto);
	public boolean atualizar(T novoObjeto);
	
}
