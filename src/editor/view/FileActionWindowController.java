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
    private TextField textField;

    private String fileName;
    private Stage stage;
    private boolean okClicked = false;

    @FXML public void handleOk() {
        fileName = new String(textField.getText());
        okClicked = true;
        stage.close();
    }

    @FXML public void handleCancel() {
        stage.close();
    }

    @FXML
    private void initialize() {
        textField.setText(fileName);
    }

    public boolean isOkClicked() {return okClicked;}
    public String getFileName() {return fileName;}


    public void setDialogStage(Stage stage, String fileName) {
        this.stage = stage;
        this.fileName = fileName;
    }
}
