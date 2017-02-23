package de.mfraas.fxclient.ui.controller;

import com.jfoenix.controls.JFXTabPane;
import de.mfraas.fxclient.services.FilechooserService;
import de.mfraas.fxclient.services.ProjectService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by marcelfraas on 22.01.17.
 */
public class ProjektController implements Initializable {

    @FXML
    private TextField location;

    @FXML
    private TextField tfFaserhalbzeug;

    @FXML
    private TextField tfHarzsystem;

    @FXML
    private TextField tfTrägermaterial;

    @FXML
    private TextField tfKaschiermaterial;

    @FXML
    private TextField tfSpalthöhe;

    @FXML
    private TextField tfVerfahren;

    @FXML
    private TextField tfBahnverlauf;

    @FXML
    private TextField tfFadenspannung;

    @FXML
    private TextField tfDrehmoment;

    @FXML
    private TextField tfSpaltA;

    @FXML
    private TextField tfSpaltB;

    @FXML
    private TextField tfSpaltZugA;

    @FXML
    private TextField tfSpaltZugB;

    @FXML
    private TextField tfFlächengewicht;

    @FXML
    private TextField tfHarzgehalt;

    @FXML
    private TextField tfPrepregbreite;

    @FXML
    private JFXTabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JSONObject o = new JSONObject(ProjectService.getProject());
        this.location.setText(o.get("speicherort").toString());
    }

    @FXML
    private void selectLocation() {

        File file = FilechooserService.saver(Arrays.asList("proj"), "Speicherort auswählen");
        HashMap<String, String> map = new HashMap<>();
        map.put("speicherort", file.getAbsolutePath());

        location.setText(file.getAbsolutePath());

        ProjectService.updateProject(map);
    }

    @FXML
    private void save() {
        HashMap<String, String> projectData = new HashMap<>();
        Arrays.asList(this.getClass().getFields()).forEach(f -> {
            try {
                if(f.get(this) != null && !(((TextField)f.get(this)).getText().equals(""))) {
                    //System.out.println("Fieldname: " + f.getName() + "    Value: " + ((TextField)f.get(c)).getText());
                    projectData.put(f.getName(), ((TextField) f.get(this)).getText());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        ProjectService.updateProject(projectData);
    }

}
