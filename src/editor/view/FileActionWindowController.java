package editor.view;

import editor.model.FileName;
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
    private TextField textField;

    private Stage stage;
    private boolean okClicked = false;
    private FileName fileName;

    @FXML public void handleOk() {
        fileName.setFileName(new String(textField.getText()));
        okClicked = true;
        stage.close();
    }

    @FXML public void handleCancel() {
        stage.close();
    }

    @FXML
    private void initialize() {
    }

    public boolean isOkClicked() {return okClicked;}


    public void setDialogStage(Stage stage, FileName fileName) {
        this.stage = stage;
        this.fileName = fileName;
        textField.setText(fileName.getFileName());
    }
}
