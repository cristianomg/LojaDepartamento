package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import View.BuscarProdutosSimilaresView;
import View.BuscarVendaView;
import View.BuscarVendedorView;
import View.MenuBuscasView;
import View.MenuPrincipalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuBuscasController implements Initializable{
	 @FXML
	    private Button btnBuscarVendedor;

	    @FXML
	    private Button btnMenuPrincipal;

	    @FXML
	    private Button btnBuscarProdutosSimilares;

	    @FXML
	    private Button btnBuscarRegistroVenda;
	    
	    
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}		
		
	    public void btnBuscarVendedor_Action() {
			MenuBuscasView.getStage().close();
			BuscarVendedorView buscarVendedor = new BuscarVendedorView();
			buscarVendedor.start(new Stage());
	    }

	    public void btnBuscarRegistroVenda_Action() {
			MenuBuscasView.getStage().close();
			BuscarVendaView buscarVendas = new BuscarVendaView();
			buscarVendas.start(new Stage());
	    }

	    public void btnBuscarProdutosSimilares() {
			MenuBuscasView.getStage().close();
			BuscarProdutosSimilaresView buscarProdutoSimiliares = new BuscarProdutosSimilaresView();
			buscarProdutoSimiliares.start(new Stage());
	    }

	    public void btnMenuPrincipal_Action() {
			MenuBuscasView.getStage().close();
			MenuPrincipalView menuPrincipal = new MenuPrincipalView();
			menuPrincipal.start(new Stage());
	    }




}
