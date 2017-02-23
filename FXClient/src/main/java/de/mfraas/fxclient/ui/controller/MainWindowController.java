package de.mfraas.fxclient.ui.controller;

import de.mfraas.fxclient.ui.FxRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by marcelfraas on 20.01.17.
 */
public class MainWindowController implements Initializable {

    @FXML
    private ListView<String> listMenuTop;

    @FXML
    private ListView<String> listMenuBot;

    @FXML
    private Text titleText;

    @FXML
    private AnchorPane apTop;

    @FXML
    private Button btnLogout;

    @FXML
    private ImageView logo;

    @FXML
    private AnchorPane paneData;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Button btnStart;

    @FXML
    private Label lblConnection;

    @FXML
    private Label lblStart;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtPerson;

    @FXML
    private TextField txtAnlage;

    private boolean isRunning = false;

    private FxRouter router = new FxRouter();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            listMenuTop.getItems().addAll(" Start", " Projekt");
            listMenuTop.getSelectionModel().select(0);
            txtDate.setText((new Date().toString()));
            listMenuBot.getItems().addAll(" Einstellungen", " Info", " Beenden");
            router.loadAnchorPane(paneData, "Start.fxml");
        });

    }

    public void deactivateItems() {
        Platform.runLater(() -> {
            listMenuTop.getSelectionModel().clearSelection();
            listMenuTop.setDisable(true);
            listMenuBot.getSelectionModel().select(0);
            apTop.getChildren().forEach(child -> child.setDisable(true));
            titleText.setText("No Configuration");
        });
    }


    @FXML
    private void resizeWindow(ActionEvent event) {
    }

    @FXML
    private void listMenuTop(MouseEvent event) {
        listMenuBot.getSelectionModel().clearSelection();
        switch(listMenuTop.getSelectionModel().getSelectedIndex()){
            case 0:{
                router.loadAnchorPane(paneData, "Start.fxml");
            }break;
            case 1:{
                router.loadAnchorPane(paneData, "Projekt.fxml");
            }break;
        }
    }

    @FXML
    private void listMenuBot(MouseEvent event) {
        listMenuTop.getSelectionModel().clearSelection();
        switch(listMenuBot.getSelectionModel().getSelectedIndex()){
            case 0:{
                router.loadAnchorPane(paneData, "Einstellungen.fxml");
            }break;
            case 1:{
                router.loadAnchorPane(paneData, "Info.fxml");
            }break;
            case 2:{
                Platform.exit();
            }
        }
    }
}
