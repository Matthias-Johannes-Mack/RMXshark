package tutorial;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TutorialController extends Application {
	@FXML
	private static Scene scene;
	private static Window primaryStage;
	private static Parent root;
	private static Stage stage;
	private static Stage finalStage;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Start the fx tutorial
	 */
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource("T_1.fxml"));
			scene = new Scene(root);
			primaryStage.setTitle("Tutorial RMXShark");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function that exits the tutorial
	 */
	public void exit() {
		((Stage) scene.getWindow()).close();
	}

	/**
	 * Action for the second slide
	 * 
	 * @param event
	 */
	public void pressButton(ActionEvent event) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("T_2.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Action for the second slide
	 * 
	 * @param event
	 */
	public void pressButtonFinal(ActionEvent event) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("T_3.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			finalStage = new Stage();
			finalStage.setScene(new Scene(root));
			finalStage.show();
			exitT2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function that exits the tutorial
	 */
	public void exitT2() {
		stage.close();
	}

	/**
	 * Function that exits the tutorial
	 */
	public void exitT3() {
		finalStage.close();
	}
}
