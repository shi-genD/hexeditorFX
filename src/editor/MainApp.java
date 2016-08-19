package editor;/**
 * Created by shi on 17.08.16.
 */

import editor.view.ConverterOverviewController;
import editor.view.EditorOverviewController;
import editor.view.FileActionWindowController;
import editor.view.MainOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    public void showFileActionWindow(Stage ownerStage) {
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
            controller.setDialogStage(stage);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
