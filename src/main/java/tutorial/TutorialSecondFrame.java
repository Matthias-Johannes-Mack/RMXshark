package tutorial;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TutorialSecondFrame {
	@FXML
	private static Scene scene;
	private static Parent root;
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
}
