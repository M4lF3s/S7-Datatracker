package de.mfraas.fxclient.ui.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import de.mfraas.fxclient.services.FilechooserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.util.Arrays;

/**
 * Created by marcelfraas on 21.01.17.
 */
public class NewProjectDialogIndexController {

    @FXML
    public JFXTextField protokollführer;

    @FXML
    public JFXTextField speicherort;

    @FXML
    public JFXTextField faserhalbzeug;

    @FXML
    public JFXTextField harzsystem;

    @FXML
    public JFXTextField trägermaterial;

    @FXML
    public JFXTextField kaschiermaterial;

    @FXML
    public JFXTextField spalthöhe;

    @FXML
    public JFXTextField verfahren;

    @FXML
    public JFXTextField bahnverlauf;

    @FXML
    public JFXTextField fadenspannung;

    @FXML
    public JFXTextField drehmoment;

    @FXML
    public JFXTextField spaltA;

    @FXML
    public JFXTextField spaltB;

    @FXML
    public JFXTextField zugA;

    @FXML
    public JFXTextField zugB;

    @FXML
    public JFXTextField flächengewicht;

    @FXML
    public JFXTextField harzgehalt;

    @FXML
    public JFXTextField prepregbreite;

    @FXML
    public Label errorLabel;

    @FXML
    public JFXCheckBox skip;

    @FXML
    private void select() {

        File file = FilechooserService.saver(Arrays.asList("proj"), "Speicherort auswählen");

        this.speicherort.setText(file.getAbsolutePath());
    }


}
