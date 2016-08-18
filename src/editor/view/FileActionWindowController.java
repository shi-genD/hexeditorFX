package editor.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by shi on 18.08.16.
 */
public class FileActionWindowController {

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField fileName;

    private Stage stage;

    @FXML public void handleOk() {

    }

    @FXML public void handleCancel() {

    }


    private void initialize() {

    }


    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
}
