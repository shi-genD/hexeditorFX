package editor.view;

import editor.util.HexByteConverter;
import editor.util.IncorrectInputCodeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by shi on 17.08.16.
 */
public class ConverterOverviewController {

    @FXML
    private TextArea inputTextField;

    @FXML
    private TextArea outputTextField;

    @FXML
    private RadioButton utf8Encoding;

    @FXML
    private RadioButton utf16Encoding;

    @FXML
    private RadioButton win1251Encoding;

    @FXML
    private RadioButton ansiEncoding;

    @FXML
    private RadioButton strToHex;

    @FXML
    private RadioButton hexToStr;


    private Stage stage;



    @FXML
    private void initialize() {
        ToggleGroup rdGroup = new ToggleGroup();
        utf8Encoding.setToggleGroup(rdGroup);
        utf8Encoding.setUserData("UTF-8");
        utf16Encoding.setToggleGroup(rdGroup);
        utf16Encoding.setUserData("UTF-16");
        win1251Encoding.setToggleGroup(rdGroup);
        win1251Encoding.setUserData("Cp1251");
        ansiEncoding.setToggleGroup(rdGroup);
        ansiEncoding.setUserData("Cp1252");

        inputTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (strToHex.isSelected()) {
                StringBuffer buf = new StringBuffer();
                for (int i = 0; i < newValue.length(); i++) {
                    buf.append(HexByteConverter.convertToHex((byte) newValue.charAt(i)));
                }
                outputTextField.setText(buf.toString());
            } else {
                    hexToString(newValue, rdGroup.getSelectedToggle().getUserData().toString());
            }
        });

        hexToStr.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (hexToStr.isSelected()) {
                utf8Encoding.setDisable(false);
                utf16Encoding.setDisable(false);
                win1251Encoding.setDisable(false);
                ansiEncoding.setDisable(false);
            } else {
                utf8Encoding.setDisable(true);
                utf16Encoding.setDisable(true);
                win1251Encoding.setDisable(true);
                ansiEncoding.setDisable(true);
            }
        });

        rdGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            hexToString(inputTextField.getText(), rdGroup.getSelectedToggle().getUserData().toString());
        });
    }

    private void hexToString(String string, String encoding) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (string.length()%2==0) {
            try {
                for (int i = 0; i < string.length(); i += 2) {
                    baos.write(HexByteConverter.convertFromHex(string.substring(i, i + 2)));
                }
                outputTextField.setText(baos.toString(encoding));
            } catch (IncorrectInputCodeException | UnsupportedEncodingException e) {
                outputTextField.setText(e.getMessage());
            }
        }
    }

    public void radioSelect(ActionEvent event) {
        inputTextField.setText("");
        outputTextField.setText("");
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }


}
