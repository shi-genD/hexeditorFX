package editor.view;

import editor.MainApp;
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
    private String filePath;

    @FXML
    private void initialize() {

    }

    @FXML
    public void handleNew() {

        outputTextField.deleteText(0, outputTextField.getText().length());
        filePath = new String(mainApp.showFileActionWindow(stage, ""));
        //File f = new File(filePath);
    }

    @FXML
    public void handleOpen() {
        filePath = new String(mainApp.showFileActionWindow(stage, ""));
        try (FileInputStream fr = new FileInputStream(filePath)) {
            outputTextField.deleteText(0, outputTextField.getText().length());
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

    @FXML
    public void handleSave() {
        int l;
        if ((l=outputTextField.getText().length()%2)!=0)
            mainApp.showErrorWindow(stage, "Not valid representation of byte");
        else {
            try {
                byte[] buf = new byte[l / 2];
                for (int i = 0, j = 0; i < l; i += 2, j++) {
                    buf[j] = HexByteConverter.convertFromHex(outputTextField.getText().substring(i, i + 2));
                }
                filePath = new String(mainApp.showFileActionWindow(stage, filePath));
                try (FileOutputStream fr = new FileOutputStream(filePath)) {
                    fr.write(buf);
                }

            } catch (IncorrectInputCodeException e1) {
                mainApp.showErrorWindow(stage, e1.getMessage());
            } catch (IOException e) {
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
