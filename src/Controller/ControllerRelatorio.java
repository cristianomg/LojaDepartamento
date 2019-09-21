package Controller;

import java.util.List;

import Model.Entites.Venda;
import StrategyRelatorio.MesFactory;
import StrategyRelatorio.RelatorioMensal;
import View.VendaView;

public class ControllerRelatorio {
	
	public void relatorioMensal(Integer solicitarMes) {
		MesFactory mesFactory = MesFactory.values()[solicitarMes-1];
		RelatorioMensal mes = mesFactory.obterMes();	
		List<Venda> relatorioMensal = mes.fazerRelatorioMensal();
		VendaView.listarVendas(relatorioMensal);
		}
		
}
