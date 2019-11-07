package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

	public class CadastrarProdutoSimilarView extends Application {

		private static Stage stage;
		
		@Override
		public void start(Stage primaryStage) {
			try {
				VBox root = (VBox)FXMLLoader.load(getClass().getResource("/ViewFXML/CadastrarProdutoSimilar.fxml"));
				Scene scene = new Scene(root,600,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.centerOnScreen();
				primaryStage.setTitle("Loja de Departamento");
				primaryStage.show();
				setStage(primaryStage);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}

		public static Stage getStage() {
			return stage;
		}

		public static void setStage(Stage stage) {
			CadastrarProdutoSimilarView.stage = stage;
		}
	}


