package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import View.MenuPrincipalView;
import View.MenuRelatoriosView;
import View.RelatorioAnualView;
import View.RelatorioMensalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuRelatorioController implements Initializable{

    @FXML
    private Button btnRelatorioMensal;

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private Button btnRelatorioAnual;

    @FXML
    void btnRelatorioMensal_Action() {
		MenuRelatoriosView.getStage().close();
		RelatorioMensalView relatorioMensal = new RelatorioMensalView();
		relatorioMensal.start(new Stage());
    }

    public void btnMenuPrincipal_Action() {
		MenuRelatoriosView.getStage().close();
		MenuPrincipalView   menuPrincipal = new MenuPrincipalView();
		menuPrincipal.start(new Stage());
    }

    public void btnRelatorioAnual_Action() {
    	MenuRelatoriosView.getStage().close();
		RelatorioAnualView relatorioAnual = new RelatorioAnualView();
		relatorioAnual.start(new Stage());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
