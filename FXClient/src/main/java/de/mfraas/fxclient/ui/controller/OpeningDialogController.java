package de.mfraas.fxclient.ui.controller;

import com.jfoenix.controls.JFXButton;
import de.mfraas.fxclient.services.FilechooserService;
import de.mfraas.fxclient.services.ProjectService;
import de.mfraas.fxclient.ui.FxRouter;
import insidefx.undecorator.UndecoratorScene;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.Arrays;

/**
 * Created by marcelfraas on 20.01.17.
 */

public class OpeningDialogController {

    @FXML
    private JFXButton newButton;

    @FXML
    private JFXButton openButton;

    @FXML
    private AnchorPane root;

    private FxRouter router;

    public void setRouter(FxRouter router) {
        this.router = router;
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    Stage newProjectStage = new Stage();

    @FXML
    private void openProject(){
        // File Chooser

        File file = FilechooserService.opener(Arrays.asList("txt",""), "Projektdatei auswählen", System.getProperty("user.home"));
        if (file != null) {
            String status = ProjectService.loadProject(file);
            if(status == "OK"){
                router.checkServerStatus();
            } //TODO: Exception when bad File is loaded.
        }
    }

    @FXML
    private void newProject() throws Exception {
        stage.hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/NewProjectDialog.fxml"));
        Region root = loader.load();
        NewProjectDialogController c = loader.getController();
        c.setRouter(router);
        c.init();
        final UndecoratorScene undecoratorScene = new UndecoratorScene(newProjectStage, null, root, "/gui/view/StageDecorationNoResize.fxml");
        newProjectStage.setScene(undecoratorScene);
        newProjectStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Close Request of Type: " + event.getEventType() + " fired");
                if(event.getEventType().equals(new String("WINDOW_FINISHED_REQUEST"))) {
                    // Close current Stage and load Main Window
                    System.out.println("Opening Main Window");
                    router.checkServerStatus();
                }
                // Reopen OpeningDialog
                router.checkServerStatus();
            }
        });
        newProjectStage.show();
    }

}
