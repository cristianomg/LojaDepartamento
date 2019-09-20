package StrategyRelatorio;

import java.util.ArrayList;
import java.util.List;

import Model.DAO.VendaDAO;
import Model.Entites.Venda;

public class Fevereiro implements RelatorioMensal {
	private VendaDAO vendas = VendaDAO.getInstance();
	@Override
	public List<Venda> fazerRelatorioMensal() {
		List<Venda> response = new ArrayList<Venda>();
		for(Venda venda: vendas.getLista()) {
			if(venda.isVendaFinalizada()) {
				if(venda.getData().getMonthValue() == 2) {
					response.add(venda);
				}
			}
		}
		return response;}

}