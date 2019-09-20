package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RelatorioView {
	Scanner sc = new Scanner(System.in);

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
				if(opc >0 && opc <=12) {
					inputRealizado = true;
				}
				else {
					System.out.print("Opção invalida, tente novamente: ");
				}
			}
			catch(InputMismatchException e){
				System.out.print("Opção invalida tente novamente: ");
				sc.next();
			}
		}while(!inputRealizado);
		return opc;
	}
}
