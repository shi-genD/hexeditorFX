package editor.view;

import editor.MainApp;
import editor.model.FileName;
import editor.util.HexByteConverter;
import editor.util.IncorrectInputCodeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by shi on 18.08.16.
 */
public class EditorOverviewController {

    @FXML
    private TextArea outputTextField;

    @FXML
    private Button newBtn;

    @FXML
    private Button openBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button closeBtn;

    private MainApp mainApp;
    private Stage stage;
    private FileName fileName = new FileName("");

    @FXML
    private void initialize() {

    }

    @FXML
    public void handleNew() {

        outputTextField.setText("");
        fileName = new FileName("");
    }

    @FXML
    public void handleOpen() {
        fileName = new FileName("");
        boolean okClicked = mainApp.showFileActionWindow(stage, fileName);
        if (okClicked) {
            try (FileInputStream fr = new FileInputStream(fileName.getFileName())) {
                outputTextField.setText("");
                int c;
                StringBuffer buf = new StringBuffer();
                while ((c = fr.read()) != -1) {
                    buf.append(HexByteConverter.convertToHex((byte) c));
                }
                outputTextField.setText(buf.toString());
            } catch (IOException e) {
                mainApp.showErrorWindow(stage, e.getMessage());
            }
        }
    }

    @FXML
    public void handleSave() {
        int len = outputTextField.getText().length();
        if (len % 2!=0)
            mainApp.showErrorWindow(stage, "Not valid representation of byte");
        else {
            try {
                    byte[] buf = new byte[len / 2];
                    for (int i = 0, j = 0; i < len; i += 2, j++) {
                        buf[j] = HexByteConverter.convertFromHex(outputTextField.getText().substring(i, i + 2));
                    }
                    boolean okClicked = mainApp.showFileActionWindow(stage, fileName);
                    if (okClicked) {
                        try (FileOutputStream fr = new FileOutputStream(fileName.getFileName())) {
                            fr.write(buf);
                        } catch (IOException e) {
                            mainApp.showErrorWindow(stage, e.getMessage());
                        }
                    }
            } catch (IncorrectInputCodeException e) {
                mainApp.showErrorWindow(stage, e.getMessage());
            }
        }
    }

    @FXML
    public void handleClose() {
        stage.close();
    }


    public void setDialogStage(Stage stage, MainApp mainApp) {
        this.stage = stage;
        this.mainApp = mainApp;
    }
}
