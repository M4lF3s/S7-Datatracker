package de.mfraas.fxclient.ui;

import de.mfraas.fxclient.services.CommunicationsService;
import de.mfraas.fxclient.ui.controller.MainWindowController;
import de.mfraas.fxclient.ui.controller.OpeningDialogController;
import insidefx.undecorator.UndecoratorScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Created by marcelfraas on 20.01.17.
 */

public class FxRouter {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private FXMLLoader mainWindowLoader;

    public void setMainWindowLoader(FXMLLoader mainWindowLoader) {
        this.mainWindowLoader = mainWindowLoader;
    }

    private String serverStatus;

    private Stage newProjectStage;


    public void checkServerStatus() {
        ResponseEntity<String> result = CommunicationsService.status();
        if(result.getStatusCode().equals(HttpStatus.OK)){
            this.serverStatus = result.getBody();
        } else {
            //TODO: Handle Server Errors
            System.out.println("Server Error");
        }
        switch(serverStatus){
            case "NoProject": handleNoProjectStatus();
                break;
            case "NoConfig": handleNoConfigStatus();
                break;
            case "Normal": handleNormalStatus();
                break;
            default: //TODO: Handle unknown Status Error
                break;
        }
    }

    private void handleNoProjectStatus() {
        newProjectStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/OpeningDialog.fxml"));
        Region root = null;
        try {
            root = loader.load();
            OpeningDialogController c = loader.getController();
            c.setRouter(this);
            c.setStage(newProjectStage);
            final UndecoratorScene undecoratorScene = new UndecoratorScene(newProjectStage, null, root, "/gui/view/StageDecorationNoResize.fxml");
            newProjectStage.setScene(undecoratorScene);
            newProjectStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleNoConfigStatus() {
        MainWindowController c = mainWindowLoader.getController();
        c.deactivateItems();
        //TODO: Show No Config Message
        primaryStage.show();
    }

    private void handleNormalStatus() {
        primaryStage.show();
    }

    public FXMLLoader loadAnchorPane(AnchorPane ap, String a) {
        //Platform.runLater(() -> {
            AnchorPane p = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/"+a));
            try {
                p = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ap.getChildren().setAll(p);
        //});
        return loader;
    }
}
