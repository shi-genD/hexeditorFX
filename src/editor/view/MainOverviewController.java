package editor.view;
/**
 * Created by shi on 18.08.16.
 */

import editor.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainOverviewController {

    @FXML
    private Button runEditor;

    @FXML
    private Button runConverter;

    private MainApp mainApp;



    @FXML
    private void initialize() {

    }

    @FXML
    private void handleRunEditor() {
        mainApp.showEditorOverview();
    }

    @FXML
    private void handleRunConverter() {
        mainApp.showConverterOverview();
    }



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


}
