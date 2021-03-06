package editor;/**
 * Created by shi on 17.08.16.
 */

import editor.model.FileName;
import editor.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hex Editor");
        this.primaryStage.getIcons().add(new Image("file:resources/images/accessories-text-editor-128.png"));

        initRootLayout();

        showMainOverview();

    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConverterOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ConverterOverview.fxml"));
            AnchorPane converterOverview = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Converter");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(converterOverview);
            stage.setScene(scene);

            ConverterOverviewController controller = loader.getController();
            controller.setDialogStage(stage);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditorOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditorOverview.fxml"));
            AnchorPane editorOverview = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Editor");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(editorOverview);
            stage.setScene(scene);

            EditorOverviewController controller = loader.getController();
            controller.setDialogStage(stage, this);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainOverview.fxml"));
            AnchorPane mainOverview = (AnchorPane) loader.load();

            rootLayout.setCenter((mainOverview));

            MainOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showFileActionWindow(Stage ownerStage, FileName fileName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FileActionWindow.fxml"));
            AnchorPane fileActionWindow = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Editor");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ownerStage);
            Scene scene = new Scene(fileActionWindow);
            stage.setScene(scene);

            FileActionWindowController controller = loader.getController();
            controller.setDialogStage(stage, fileName);

            stage.showAndWait();
            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showErrorWindow(Stage ownerStage, String errorText) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ErrorWindow.fxml"));
            AnchorPane errorWindow = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Warning!");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ownerStage);
            Scene scene = new Scene(errorWindow);
            stage.setScene(scene);

            ErrorWindowController controller = loader.getController();
            controller.setDialogStage(stage, errorText);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
