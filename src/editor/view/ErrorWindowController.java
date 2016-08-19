package editor.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by shi on 19.08.16.
 */
public class ErrorWindowController {

    private Stage stage;
    private String errorText;

    @FXML
    private Label errorMessage;

    @FXML
    private Button okBtn;

    @FXML
    public void handleOk() {
        stage.close();
    }

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage stage, String errorText) {
        this.stage = stage;
        this.errorText = new String(errorText);
        errorMessage.setText(errorText);
    }
}
