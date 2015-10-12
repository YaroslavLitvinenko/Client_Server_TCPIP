package sample;

import javafx.event.ActionEvent;
import javafx.stage.*;
import javafx.stage.Window;

public class Controller extends Window {
    public static Main general;
    FileChooser fc = new FileChooser();

    Client client;

    public void initialize() {
        client = new Client();
        new Thread(client).start();
    }

    public void File(ActionEvent actionEvent) {
        client.sendData(fc.showOpenDialog(this));
    }

    public void Close(ActionEvent actionEvent) {
        client.close();
    }
}
