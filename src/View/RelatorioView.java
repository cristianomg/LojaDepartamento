package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Model.DAO.DepartamentoDAO;
import Model.Entites.Departamento;
import Model.Entites.Venda;
import Model.Entites.VendaProduto;

public class RelatorioView {
	Scanner sc = new Scanner(System.in);
	private static DepartamentoDAO departamentos = DepartamentoDAO.getInstance();
	public Integer solicitarMes() {
		Integer opc = null;
		boolean inputRealizado = false;
		System.out.println("Informe o numero do mês que deseja mostrar o relatorio");
		System.out.println("1.  Janeiro");
		System.out.println("2.  Fevereiro");
		System.out.println("3.  Março");
		System.out.println("4.  Abril");
		System.out.println("5.  Maio");
		System.out.println("6.  Junho");
		System.out.println("7.  Julho");
		System.out.println("8.  Agosto");
		System.out.println("9.  Setembro");
		System.out.println("10. Outubro");
		System.out.println("11. Novembro");
		System.out.println("12. Dezembro");
		System.out.print("Opção");
		do {
			try {
				opc = sc.nextInt();
				if (opc > 0 && opc <= 12) {
					inputRealizado = true;
				} else {
					System.out.print("Opção invalida, tente novamente: ");
				}
			} catch (InputMismatchException e) {
				System.out.print("Opção invalida tente novamente: ");
				sc.next();
			}
		} while (!inputRealizado);
		return opc;
	}

	public static void relatorio(List<Venda> vendas, HashMap<Integer, Double> comissaoPorDepartamento) {
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ", "X Y Z Comercio LTDA", "  ",
				"-----------------------");
		System.out.println();
		System.out.printf("%-20s%s%-20s%s%-18s", "----------------------", "  ", "Relatorio Mensal", "  ",
				"-----------------------");
		System.out.println();
		System.out.println();
		System.out.println("Vendas: ");
		if (!vendas.isEmpty()) {
			for (Venda venda : vendas) {
				if (venda.isVendaFinalizada()) {
					System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", "Codigo", " ", "Funcionario", " ",
							"Departamento", " ", "Cliente", " ", "Data");
					System.out.println();
					System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", "------", " ",
							"--------------------------", " ", "------------", " ", "----------", " ", "---------");
					System.out.println();
					System.out.printf("%-6s%-1s%-28s%-1s%-10s%-1s%-10s%-1s%s", venda.getCodigo(), " ",
							venda.getFuncionario().getMatricula(), " ",
							venda.getFuncionario().getDepartamento().getNome(), " ", venda.getCliente().getNome(), " ",
							venda.getData());
					System.out.println();
					System.out.println();
					ArrayList<VendaProduto> vendaProdutos = venda.getListaVendaProduto();
					VendaView.listarProdutosVenda(vendaProdutos);
					System.out.println("Preço total: " + venda.getPrecoTotal());
					System.out.println();
					System.out.printf("%-58s", "^".repeat(69));
					System.out.println();
					System.out.println();
				}
			}

			System.out.println("Comissoes: ");
			System.out.printf("%-40s%s", "Nome do Departamento", "Total de comissão a pagar");
			System.out.println();
			System.out.printf("%-38s%-2s%s", "-".repeat(38), "  ", "-".repeat(29));
			System.out.println();
			for(Departamento d: departamentos.getLista()) {
				System.out.printf("%-38s%-2s%s", d.getNome(), "  ", comissaoPorDepartamento.get(d.getId()));
				System.out.println();
			}
			System.out.println();
		}else {
			System.out.println("Nenhuma venda cadastrada.");
		}
	}
}
