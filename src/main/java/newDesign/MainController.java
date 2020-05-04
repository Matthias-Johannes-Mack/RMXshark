package newDesign;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import connection.SocketConnector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainController {

    @FXML
    private TextArea console;
    private PrintStream ps ;

    public void initialize() {
        ps = new PrintStream(new Console(console)) ;
    }

    public void button(ActionEvent event) {
        System.setOut(ps);
        System.setErr(ps);
        SocketConnector.Connect();
    }

    public class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        public void write(int b) throws IOException {
            appendText(String.valueOf((char)b));
        }
    }
}