package View2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private Scanner sc = new Scanner(System.in);

	
	public HashMap<String, String> autorizacaoView(){
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println();
		boolean inputConcluido = false;
		HashMap<String, String> dadosFuncionario = new HashMap<String, String>();
		do {
			System.out.print("Informe a matricula: ");
			String matricula = sc.next();
			System.out.print("Informe a senha: ");
			String senha = sc.next();
			if(matricula.length() > 0 && senha.length() > 0) {
				dadosFuncionario.put("matricula", matricula);
				dadosFuncionario.put("senha", senha);
				inputConcluido = true;
			}
			else {
				System.out.println("Erro: Informações insuficientes, tente novamente.");
			}
		}while(!inputConcluido);
		return dadosFuncionario;
		
	}
	
	public int menuPrincipal() {
		int respostaPrincipal = 0;
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println("");
		boolean inputConcluido = false;
		System.out.println("1. Menu de cadastro");
		System.out.println("2. Menu de vendas");
		System.out.println("3. Menu de relatorios");
		System.out.println("4. Menu de movimentações");
		System.out.println("5. Menu de listagens");
		System.out.println("6. Menu de Buscas");
		do {
			try {
				respostaPrincipal = sc.nextInt();
				if (respostaPrincipal > 0 && respostaPrincipal < 7){
					inputConcluido = true;
				}
				else {
					System.out.print("Erro: Opção Invalida, tente novamente: ");
				}
			}catch(InputMismatchException e) {
				System.out.print("Erro: Opção Invalida, tente novamente: ");
				sc.next();
			}
		}while(!inputConcluido);
		return respostaPrincipal;
	}
		
	public int menuCadastro(){
		int respostaCadastro = 0;
		boolean inputConcluido = false;
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println("");
		System.out.println("1. Cadastrar Cliente");
		System.out.println("2. Cadastrar Funcionario");
		System.out.println("3. Cadastrar Departamento");
		System.out.println("4. Cadastrar Produto");
		System.out.println("5. Cadastrar Produtos Similares");
		System.out.println("6. Retorna para menu principal");
		do {
			try {
				respostaCadastro = sc.nextInt();
				if (respostaCadastro > 0 && respostaCadastro < 7){
					inputConcluido = true;
				}else {
					System.out.print("Erro: Opção Invalida, tente novamente: ");
				}
			}catch(InputMismatchException e) {
				System.out.print("Erro: Opção Invalida, tente novamente: ");
				sc.next();
			}
		}while(!inputConcluido);
		return respostaCadastro;
	}
	
	public int menuVenda() {
		int respostaCadastro = 0;
		boolean inputConcluido = false;
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println("");
		System.out.println("1. abrir Venda");
		System.out.println("2. adicionar produto");
		System.out.println("3. remover Produto");
		System.out.println("4. calcular valor da venda");
		System.out.println("5. finalizar venda");
		System.out.println("6. Retorna para menu principal");
		do {
			try {
				respostaCadastro = sc.nextInt();
				if (respostaCadastro > 0 && respostaCadastro < 7){
					inputConcluido = true;
				}else {
					System.out.print("Erro: Opção Invalida, tente novamente: ");
				}
			}catch(InputMismatchException e) {
				System.out.print("Erro: Opção Invalida, tente novamente: ");
				sc.next();
			}
		}while(!inputConcluido);
		return respostaCadastro;
	}
	
	public int menuListagem() {
		int respostaListagem = 0;
		boolean inputConcluido = false;
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println("");
		System.out.println("1. Listar Cliente");
		System.out.println("2. Listar Departamento");
		System.out.println("3. Listar Funcionario");
		System.out.println("4. Listar Produto");
		System.out.println("5. Listar Vendas");
		System.out.println("6. Retorna para menu principal");
		do {
			try {
				respostaListagem = sc.nextInt();
				if (respostaListagem > 0 && respostaListagem < 7){
					inputConcluido = true;
				}else {
					System.out.print("Erro: Opção Invalida, tente novamente: ");
				}
			}catch(InputMismatchException e) {
				System.out.print("Erro: Opção Invalida, tente novamente: ");
				sc.next();
			}
		}while(!inputConcluido);
		return respostaListagem;
	}
	
	public int menuMovimentacoes() {
		int respostaMovimentacoes = 0;
		boolean inputConcluido = false;
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println("");
		System.out.println("1. Comprar Produto para o estoque");
		System.out.println("2. Mover produto de departamento");
		System.out.println("3. Mover funcionario de departamento");
		System.out.println("4. Promover funcionario a chefe");
		System.out.println("5. Alterar comissão do departamento");
		System.out.println("6. Demitir funcionario");
		System.out.println("7. Retorna para menu principal");
		do {
			try {
				respostaMovimentacoes = sc.nextInt();
				if (respostaMovimentacoes > 0 && respostaMovimentacoes < 8){
					inputConcluido = true;
				}else {
					System.out.print("Erro: Opção Invalida, tente novamente: ");
				}
			}catch(InputMismatchException e) {
				System.out.print("Erro: Opção Invalida, tente novamente: ");
				sc.next();
			}
		}while(!inputConcluido);
		return respostaMovimentacoes;
	}
	
	public int menuBusca() {
		int response = 0;
		boolean inputConcluido = false;
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println("");
		System.out.println("1. Buscar Vendedor");
		System.out.println("2. Buscar Registro de Venda	");
		System.out.println("3. Buscar Produtos similares de um produto Marca");
		System.out.println("4. Retorna para menu principal");
		do {
			try {
				response = sc.nextInt();
				if (response > 0 && response < 5){
					inputConcluido = true;
				}else {
					System.out.print("Erro: Opção Invalida, tente novamente: ");
				}
			}catch(InputMismatchException e) {
				System.out.print("Erro: Opção Invalida, tente novamente: ");
				sc.next();
			}
		}while(!inputConcluido);
		return response;
	}
	
}