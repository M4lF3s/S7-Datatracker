package de.mfraas.fxclient.ui.controller;

import com.jfoenix.controls.JFXProgressBar;
import de.mfraas.fxclient.ui.animations.FadeInLeftTransition;
import de.mfraas.fxclient.ui.animations.FadeInRightTransition;
import de.mfraas.fxclient.ui.animations.FadeInTransition;
import de.mfraas.fxclient.ui.animations.FadeOutLeftTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by marcelfraas on 18.01.17.
 */
public class SplashscreenController implements Initializable {

    @FXML
    private Text lblWelcome;

    @FXML
    private AnchorPane apTitle;

    @FXML
    private Text lblLoading;

    @FXML
    private VBox vboxBottom;

    @FXML
    private Label lblClose;

    @FXML
    private ImageView imgLoading;

    @FXML
    private JFXProgressBar progressbar;

    @FXML
    private Text beanText;

    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.progressbar.setProgress(-1.0f);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new FadeInLeftTransition(apTitle).play();
        new FadeInRightTransition(lblLoading).play();
        new FadeInTransition(vboxBottom).play();
    }

    public void setProgress(double i) {
        this.progressbar.setProgress(i);
    }

    public void setLoadingText(String t) {
        FadeOutLeftTransition transition = new FadeOutLeftTransition(lblLoading);
        /*
        transition.setOnFinished(event -> {
            lblLoading.setText("Starting Services");
            new FadeInRightTransition(lblLoading).play();
        });
        */
        transition.play();
    }

    public String getLoadingText() {
        return this.lblLoading.getText();
    }

    public void setBeanText(String text) {
        this.beanText.setText(text);
    }

}
