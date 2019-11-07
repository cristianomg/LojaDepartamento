package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.DAO.DepartamentoDAO;
import Model.DAO.VendaDAO;
import Model.Entites.Venda;
import View2.RelatorioView;

public class ControllerRelatorio {
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	private static VendaDAO vendas = VendaDAO.getInstance();
	
	public void relatorioMensal(Integer solicitarMes) {
		fazerRelatorioMensal(solicitarMes);
		}
		
	public void fazerRelatorioMensal(int mes){
		HashMap<Integer, Double> comissaoPorDepartamento = new HashMap<Integer, Double>();
		departamentos.getLista().stream().forEach(departamento -> {
			comissaoPorDepartamento.put(departamento.getId(), (double) 0);
		});
		List<Venda> vendasRealizadasMes = new ArrayList<Venda>();
		for (Venda venda : vendas.getLista()) {
			if (venda.isVendaFinalizada()) {
				if (venda.getData().getMonthValue() == mes) {
					int idDepartamento = venda.getFuncionario().getDepartamento().getId();
					double newValor = comissaoPorDepartamento.get(idDepartamento) + venda.getComissaoFuncionario();
					comissaoPorDepartamento.put(idDepartamento, newValor);
					vendasRealizadasMes.add(venda);
				}
			}
		}
		RelatorioView.relatorio(vendasRealizadasMes, comissaoPorDepartamento);

	}
}
