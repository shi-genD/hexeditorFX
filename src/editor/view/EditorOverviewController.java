package editor.view;

import editor.util.HexByteConverter;
import editor.util.IncorrectInputCodeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by shi on 18.08.16.
 */
public class EditorOverviewController {

    @FXML
    private TextField outputTextField;

    @FXML
    private Button newBtn;

    @FXML
    private Button openBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button closeBtn;

    private Stage stage;
    private String filePath;

    @FXML
    private void initialize() {

    }

    @FXML
    public void handleNew() {
        outputTextField.deleteText(0, outputTextField.getText().length());
        File f = new File(filePath);
    }

    @FXML
    public void handleOpen() {
        try (FileInputStream fr = new FileInputStream(filePath)) {
            outputTextField.deleteText(0, outputTextField.getText().length());
            int c;
            StringBuffer buf = new StringBuffer();
            while ((c = fr.read()) != -1) {
                buf.append(HexByteConverter.convertToHex((byte) c));
            }
            outputTextField.setText(buf.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleSave() {
        try (FileOutputStream fr = new FileOutputStream(filePath)) {
            for (int i = 0; i<outputTextField.getText().length(); i+=2) {
                fr.write(HexByteConverter.convertFromHex(outputTextField.getText().substring(i, i+2)));
            }

        } catch (IOException | IncorrectInputCodeException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void handleClose() {
        outputTextField.deleteText(0, outputTextField.getText().length());
    }


    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
}
