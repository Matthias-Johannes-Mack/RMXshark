package newDesign;

import java.io.PrintStream;

import Utilities.ByteUtil;
import Utilities.Constants;
import connection.Sender;
import connection.SocketConnector;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import makro.Makro;

public class ConsoleController extends Application {
	@FXML
	private TextArea txtArea;

	private static int bus;

	private static Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Start the fx
	 */
	public synchronized void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Console.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add("ConsoleStyle.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			txtArea = (TextArea) scene.lookup("#jTextArea");
			// the stream for the console -> redirect everything
			PrintStream printStream = new PrintStream(System.out) {
				@Override
				public void print(String text) {
					Platform.runLater(() -> txtArea.appendText(text + "\n"));
				}
			};

			PrintStream printStreamErr = new PrintStream(System.err) {
				@Override
				public void print(String text) {
					Platform.runLater(() -> txtArea.appendText(text + "\n"));
				}
			};
			// redirect the whole streams to the textfield
			System.setOut(printStream);
			System.setErr(printStreamErr);
			head();
			javafx.scene.control.TextField txtInput = (javafx.scene.control.TextField) scene.lookup("#txtCommand");
			// call the message hander if enter is used
			txtInput.setOnKeyPressed((event) -> {
				if (event.getCode() == KeyCode.ENTER) {
					messageHandler(txtInput);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void messageHandler(javafx.scene.control.TextField txtInput) {
		// check the message for the right type
		// SystemAdress Value
		// [0-111] [0-7][Value]
		String txtFieldTxt = txtInput.getText();
		// remove all the whitespaces
		txtFieldTxt = txtFieldTxt.replaceAll("\\s+", "");
		System.out.println(txtFieldTxt);
		// if the txtfieldtest is legit, then move on
		if (!txtFieldTxt.isEmpty()
				&& txtFieldTxt.matches("^([1-9]{1}[0-1]{1}[0-1]{1}|[1-9]{1}[0-9]{1}|[1-9]{1}),[0-7]{1},[0-1]{1}$")) {
			// print out the text
			System.out.println("------------------------------");
			// switch the bus
			ComboBox<String> cmbBus = (ComboBox<String>) scene.lookup("#cmbBus");
			switch (cmbBus.getValue().toString()) {
			case "RMX_0":
				setBus(1);
				break;
			case "RMX_1":
				setBus(2);
				break;
			default:
				setBus(1);
			}
			String[] tempArr = txtFieldTxt.split(",");
			// create message rmx OPCODE [busId](1-4) [systemAdress](0-111) [bitIndex](0-7)
			// [bitValue] (0-1) format
			int bus = getBus();
			int systemAdress = Integer.parseInt(tempArr[0]);
			int bitIndex = Integer.parseInt(tempArr[1]);
			int bitValue = Integer.parseInt(tempArr[2]);
			int calcVal = 0;
			// calculate the actual bit value if bit is not set
			if (!ByteUtil.bitIsSet(bitValue, bitIndex)) {
				calcVal = ByteUtil.calcBinaryValueFromInt(bitIndex);
			} else {
				// TODO implement the other side
				System.out.println("Bit bereits gesetzt!");
			}

			int[] message = new int[] { Constants.RMX_HEAD, 6, 5, bus, systemAdress, calcVal };
			System.out.println("Bus: " + getBus());
			System.out.println("SystemAdress: " + tempArr[0]);
			System.out.println("Value: " + tempArr[1]);
			System.out.println("------------------------------");
			// send the things to the sender
			Sender.addMessageQueue(message);
			// if the Makro recorder is on
//			if (Makro.isState()) {
//				if (makro == null) {
//					// create a new makro instance
//					makro = new Makro();
//				}
//				// add lines to the makro arraylist
//				Makro.addLines(message);
//			}
		} else

		{ // put out the error message
			System.out.println("------------------------------");
			System.out.println(Constants.DE_WRONG_MESSAGETYPE);
			System.out.println("------------------------------");
		}

		// clear textfield
		txtInput.setText("");

	}

	/**
	 * Method for the head
	 * 
	 */
	private static void head() {
		System.out.println(
				"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------RMXshark-----------------------------------");
		System.out.println(
				"--------------------------------------------------------------------------------------------");
		System.out.println(
				"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}

	/**
	 * @return the bus
	 */
	public static int getBus() {
		return bus;
	}

	/**
	 * @param bus the bus to set
	 */
	public static void setBus(int bus) {
		ConsoleController.bus = bus;
	}

}
