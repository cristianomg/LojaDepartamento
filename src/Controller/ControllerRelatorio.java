package Controller;

import java.util.List;

import Model.Entites.Venda;
import StrategyRelatorio.MesFactory;
import StrategyRelatorio.RelatorioMensal;
import View.RelatorioView;
import View.VendaView;

public class ControllerRelatorio {
	RelatorioView relatorioView = new RelatorioView();

	public void relatorioMensal() {
		int solicitarMes;
		solicitarMes = relatorioView.solicitarMes();
		MesFactory mesFactory = MesFactory.values()[solicitarMes-1];
		RelatorioMensal mes = mesFactory.obterMes();	
		List<Venda> relatorioMensal = mes.fazerRelatorioMensal();
		VendaView.listarVendas(relatorioMensal);
		}
		
}
