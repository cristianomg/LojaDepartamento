package Controller;

import java.util.List;

import Model.Entites.Venda;
import StrategyRelatorio.MesRelatorio;
import StrategyRelatorio.RelatorioMensal;
import View.VendaView;

public class ControllerRelatorio {

	public void relatorioMensal() {
		int solicitarMes = 9;
		MesRelatorio mesFactory = MesRelatorio.values()[solicitarMes-1];
		RelatorioMensal mes = mesFactory.obterMes();	
		List<Venda> relatorioMensal = mes.fazerRelatorioMensal();
		VendaView.listarVendas(relatorioMensal);
		}
		
}
