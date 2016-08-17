package editor.view;

import editor.MainApp;
import editor.model.TextIn;
import editor.util.HexByteConverter;
import editor.util.HexBytesStringParser;
import editor.util.IncorrectInputCodeException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Created by shi on 17.08.16.
 */
public class EditorOverviewController {

    @FXML
    private TextArea inputTextField;

    @FXML
    private TextArea outputTextField;

    private MainApp mainApp;



    @FXML
    private void initialize() {

    }

    @FXML
    private void handleApply() throws IncorrectInputCodeException {
        TextIn text = new TextIn(inputTextField.getText());
        StringBuffer bufOut = new StringBuffer();
        for (int i =0; i < text.getText().length(); i++) {
            bufOut.append(HexByteConverter.convertToHex((byte)text.getText().charAt(i)));
        }
        outputTextField.setText(bufOut.toString());

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
