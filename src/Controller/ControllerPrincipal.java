package Controller;

<<<<<<< HEAD
import View.Menu;

public class ControllerPrincipal {
=======
import java.util.List;

import Model.Entites.Cliente;
import View.ClienteView;
import View.Menu;

public class ControllerPrincipal<respLista> {
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
	private Menu menu;
	
	public ControllerPrincipal() {
		menu = new Menu();
	}
	public void controllerPrincipal() {
		int resp = menu.menuPrincipal();
		switch(resp) {
		case 1:
			this.controllerCadastro();
			break;
		case 2:
			this.controllerVendas();
			break;
		case 3:
			this.controllerRelatorios();
			break;
		case 4:
			this.controllerRealocacoes();
			break;
		case 5:
			this.controllerListagem();
			break;
		}
		
	}
	public void controllerCadastro() {
		int respCadastro = menu.menuCadastro();
		switch(respCadastro) {
		case 1:
			ControllerCadastro.cadastrarCliente();
			break;
		case 2:
<<<<<<< HEAD
			ControllerCadastro.cadastrarFuncionario();
			break;
		case 3:
			ControllerCadastro.cadastrarDepartamento();
			break;
		case 4:
			ControllerCadastro.cadastrarProduto();
=======
			System.out.println("Funcionario cadastrado");
			break;
		case 3:
			System.out.println("Departamento cadastrado");
			break;
		case 4:
			System.out.println("Produto Cadastrado");
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
			break;
		case 5:
			System.out.println("Produto similar Cadastrado");
			break;
		}
		this.controllerPrincipal();
	}
	public void controllerVendas() {
		
	}
	public void controllerRelatorios() {
		
	}
	public void controllerRealocacoes() {
		
	}
	public void controllerListagem() {
		int respostaListagem = menu.menuListagem();
		switch(respostaListagem) {
		case 1:
			ControllerListagem.listarClientes();
			break;
		case 2:
			ControllerListagem.listaDepartamento();
			break;
<<<<<<< HEAD
		case 3:
			ControllerListagem.listaFuncionario();
			break;
=======
>>>>>>> c3bab182c08f15b05017c58f8833971787188b40
		}
		this.controllerPrincipal();
	}
	
}
