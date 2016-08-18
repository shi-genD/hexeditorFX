package editor.view;

import editor.MainApp;
import editor.util.HexByteConverter;
import editor.util.IncorrectInputCodeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Created by shi on 17.08.16.
 */
public class ConverterOverviewController {

    @FXML
    private TextArea inputTextField;

    @FXML
    private TextArea outputTextField;

    @FXML
    private RadioButton strToHex;

    @FXML
    private RadioButton hexToStr;


    private Stage stage;



    @FXML
    private void initialize() {
        inputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (strToHex.isSelected()) {
                StringBuffer buf = new StringBuffer();
                for (int i = 0; i < newValue.length(); i++) {
                    buf.append(HexByteConverter.convertToHex((byte) newValue.charAt(i)));
                }
                outputTextField.setText(buf.toString());
            } else {
                StringBuffer buf = new StringBuffer();
                if (newValue.length()%2==0) {
                    try {
                        for (int i = 0; i < newValue.length(); i += 2) {
                            buf.append((char)HexByteConverter.convertFromHex(newValue.substring(i, i + 2)));
                        }
                        outputTextField.setText(buf.toString());
                    } catch (IncorrectInputCodeException e) {
                        outputTextField.setText(e.getMessage());
                    }
                }
            }
        });
    }

    public void radioSelect(ActionEvent event) {
        inputTextField.deleteText(0, inputTextField.getText().length());
        outputTextField.deleteText(0, outputTextField.getText().length());
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }


}
