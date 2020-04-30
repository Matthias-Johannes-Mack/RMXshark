package newDesign;

import java.awt.TextField;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

	public class ConsoleController extends Application {
		@FXML
		private TextField txtIP;

		public static void main(String[] args) {
			launch(args);
		}

		/**
		 * Start the fx
		 */
		public void start(Stage primaryStage) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("Console.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add("ConsoleStyle.css");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

