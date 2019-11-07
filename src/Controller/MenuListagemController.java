package Controller;

import View.ListagemClientesView;
import View.ListagemDepartamentosView;
import View.ListagemFuncionariosView;
import View.ListagemProdutosView;
import View.ListagemVendasView;
import View.MenuListagensView;
import View.MenuPrincipalView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuListagemController {
	
	   @FXML
	    private Button bntListarClientes;

	    @FXML
	    private Button btnMenuPrincipal;

	    @FXML
	    private Button btnListarFuncionarios;

	    @FXML
	    private Button btnListarProdutos;

	    @FXML
	    private Button btnListarDepartamentos;
	    
	    @FXML
	    private Button btnListarVendas;

	    public void btnListarClientes_Action() {
			MenuListagensView.getStage().close();
			ListagemClientesView listagemClientes = new ListagemClientesView();
			listagemClientes.start(new Stage());
	    }

	    public void btnListarFuncionarios_action() {
			MenuListagensView.getStage().close();
			ListagemFuncionariosView listagemFuncionarios = new ListagemFuncionariosView();
			listagemFuncionarios.start(new Stage());
	    }

	    public void btnListarDepartamentos_Action() {
			MenuListagensView.getStage().close();
			ListagemDepartamentosView listagemDepartamentos = new ListagemDepartamentosView();
			listagemDepartamentos.start(new Stage());
	    }

	    public void btnListarProdutos_Action() {
			MenuListagensView.getStage().close();
			ListagemProdutosView listagemProdutos = new ListagemProdutosView();
			listagemProdutos.start(new Stage());
	    }
	    
	    public void btnListarVendas_Action() {
			MenuListagensView.getStage().close();
			ListagemVendasView listagemVendas = new ListagemVendasView();
			listagemVendas.start(new Stage());
	    }


	    public void btnMenuPrincipal_Action() {
			MenuListagensView.getStage().close();
			MenuPrincipalView menuPrincipal = new MenuPrincipalView();
			menuPrincipal.start(new Stage());
	    }

}
