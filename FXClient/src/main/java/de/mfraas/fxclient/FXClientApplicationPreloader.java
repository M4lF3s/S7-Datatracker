package de.mfraas.fxclient;

import de.mfraas.fxclient.ui.CusomNotification;
import de.mfraas.fxclient.ui.controller.SplashscreenController;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by marcelfraas on 18.01.17.
 */
public class FXClientApplicationPreloader extends Preloader {

    private Stage stage;

    private SplashscreenController c;

    @Override
    public void start(Stage stage) throws Exception {


        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Splashscreen.fxml"));
        Region root = null;
        try {
            root = loader.load();
            this.c = loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/Splashscreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        */

    }

    @Override
    public void handleApplicationNotification(PreloaderNotification pn) {

        if (pn instanceof StateChangeNotification) {
            stage.hide();
        } else if (pn instanceof ProgressNotification) {
            this.c.setProgress(((ProgressNotification) pn).getProgress());
        } else if (pn instanceof CusomNotification) {
            this.c.setBeanText(((CusomNotification) pn).getText());
        }
    }
}
