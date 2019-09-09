package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private Scanner sc;

	public int menuPrincipal() {
		int menuPrincipal = 1;
		int respostaPrincipal = 0;
		sc = new Scanner(System.in);
		System.out.printf("%-23s%s%-20s%s%-23s", "----------------------", " ","X Y Z Comercio LTDA","  ", "-----------------------");
		System.out.println("");
		try {
			while (menuPrincipal == 1){
				System.out.println("1. Menu de cadastro");
				System.out.println("2. Menu de vendas");
				System.out.println("3. Menu de relatorios");
				System.out.println("4. Menu de realocações");
				System.out.println("5. Menu de listagens");
					respostaPrincipal = sc.nextInt();
					if (respostaPrincipal > 0 && respostaPrincipal < 6){
						menuPrincipal = 0;
					}
					else {
						System.out.println("Opção Invalida!!!");
					}
				}
			}
		catch(InputMismatchException e) {
			System.out.println("Opção Invalida!!!");
			Menu m = new Menu();
			respostaPrincipal = m.menuPrincipal();
			}
		return respostaPrincipal;
	}
		
	public int menuCadastro(){
		int menuCadastro = 1;
		int respostaCadastro = 0;
		sc = new Scanner(System.in);
		System.out.printf("%-20s%s%-20s%s%-20s", "##################", " ","Menu de cadastros","  ", "#################");
		System.out.println("");
		try {
		while (menuCadastro == 1){
			System.out.println("1. Cadastrar Cliente");
			System.out.println("2. Cadastrar Funcionario");
			System.out.println("3. Cadastrar Departamento");
			System.out.println("4. Cadastrar Produto");
			System.out.println("5. Cadastrar Produtos Similares");
			respostaCadastro = sc.nextInt();
			if (respostaCadastro > 0 && respostaCadastro < 6){
				menuCadastro = 0;
			}
			else {
				System.out.println("Opção invalida!!!");
			}
		}
		}
		catch(InputMismatchException e) {
			Menu m = new Menu();
			respostaCadastro = m.menuCadastro();
		}
		return respostaCadastro;
		
	}
	public int menuListagem() {
		int menuListagem = 1;
		int respostaListagem = 0;
		sc = new Scanner(System.in);
		System.out.printf("%-20s%s%-20s%s%-20s", "##################", " ","Menu de Listagem","  ", "#################");
		System.out.println("");
		try {
		while (menuListagem == 1){
			System.out.println("1. Listar Cliente");
			System.out.println("2. Listar Departamento");
			System.out.println("3. Listar Funcionario");
			System.out.println("4. Listar Produto");
			System.out.println("5. Listar Vendas");
			respostaListagem = sc.nextInt();
			if ( respostaListagem > 0 && respostaListagem < 6){
				menuListagem = 0;
			}
			else {
				System.out.println("Opção invalida!!!");
			}
		}
		}
		catch(InputMismatchException e) {
			Menu m = new Menu();
			respostaListagem = m.menuListagem();
		}
		return respostaListagem;
	}
}