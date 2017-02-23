package de.mfraas.fxclient.ui.controller;

import com.jfoenix.controls.JFXButton;
import de.mfraas.fxclient.services.ProjectService;
import de.mfraas.fxclient.ui.FxRouter;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.*;

/**
 * Created by marcelfraas on 21.01.17.
 */
public class NewProjectDialogController {

    private FxRouter router;

    public void setRouter(FxRouter router) {
        this.router = router;
    }

    private int currentIndex = 1;

    private FXMLLoader childLoader;

    private HashMap<String, String> projectData = new HashMap<>();

    public HashMap<String, String> getData() {
        return projectData;
    }

    @FXML
    private AnchorPane apIndex;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton nextButton;

    @FXML
    private Text titleText;

    @FXML
    private void next() {

        NewProjectDialogIndexController c = (NewProjectDialogIndexController) childLoader.getController();


        if(currentIndex == 1) {
            if(c.speicherort.getText().equals("")) {
                //System.out.println("Speicherort darf nicht leer sein!");
                c.errorLabel.setText("Speicherort darf nicht leer sein!");
                return ;
            }
            try{
                File file = new File(c.speicherort.getText());
                if(!file.getParentFile().isDirectory()){
                    //System.out.println("Kein valides Verzeichnis!");
                    c.errorLabel.setText("Kein valides Verzeichnis!");
                    return ;
                }
                if(file.isDirectory()) {
                    //System.out.println("Speicherort muss eine Datei sein!");
                    c.errorLabel.setText("Speicherort muss eine Datei sein!");
                    return;
                }
                if(FilenameUtils.getExtension(c.speicherort.getText()) != "proj"){
                    c.speicherort.setText(c.speicherort.getText().split("\\.")[0].concat(".proj"));
                }
            } catch (Exception e) {
                //System.out.println("Kein Valides Verzeichnis!");
                c.errorLabel.setText("Kein Valides Verzeichhnis!");
                return ;
            }

            System.out.println(c.speicherort.getText());


            if(c.skip.isSelected()) currentIndex = 7;
            /*
                    Save all Values from NewProjectDialogIndex Controller that are not Null / empty in a Hashmap.
                    Key: Fieldname in Controller
                    Value: Fieldvalue in Controller
             */
            Arrays.asList(c.getClass().getFields()).forEach(f -> {
                try {
                    //System.out.println("Fieldtype: " + f.getType().toString());
                    if(
                            f.get(c) != null &&     // Filter all Fields with no Values in them
                            f.getType().isAssignableFrom(javafx.scene.control.TextField.class) &&      // Only get the Values from TextField Elements
                            !(((TextField)f.get(c)).getText().equals(""))   // Only get non-empty values
                        ) {
                        //System.out.println("Fieldname: " + f.getName() + "    Value: " + ((TextField)f.get(c)).getText());
                        projectData.put(f.getName(), ((TextField) f.get(c)).getText());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }


        if(currentIndex == 7){
            // Create Project and return

            String status = ProjectService.createNewProject(projectData).replace("\"", "");
            System.out.println(status);
            if(status.equals(new String("OK"))){
                Stage stage = (Stage) nextButton.getScene().getWindow();
                stage.getOnCloseRequest().handle(new WindowEvent(apIndex.getScene().getWindow(), new EventType<Event>("WINDOW_FINISHED_REQUEST")));
                stage.close();
                //router.checkServerStatus();
            } else System.out.println("Doesn't work!");

        } else {
            currentIndex++;
            reload();
        }
    }

    @FXML
    private void back() {
        currentIndex--;
        reload();
    }

    public void init() {
        if(currentIndex == 1) {
            backButton.setVisible(false);
        }
        childLoader = router.loadAnchorPane(apIndex, "NewProjectDialog1.fxml");
        //getAllNodes(apIndex.getParent().getParent()).forEach(System.out::println);
    }

    /*
    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent)node, nodes);
        }
    }
    */

    private void reload() {
        if(currentIndex == 1) {
            backButton.setVisible(false);
        } else backButton.setVisible(true);
        if(currentIndex == 7) {
            nextButton.setText("Finish");
        }

        switch(currentIndex) {
            case 1: {
                titleText.setText("Neues Projekt:");
            } break;
            case 2: {
                titleText.setText("Materialien:");
            } break;
            case 3: {
                titleText.setText("Anlagenparameter:");
            } break;
            case 4: {
                titleText.setText("Zugspannungen:");
            } break;
            case 5: {
                titleText.setText("Kalander:");
            } break;
            case 6: {
                titleText.setText("Zugstation:");
            } break;
            case 7: {
                titleText.setText("Prepregeigenschaften:");
            } break;

        }

        childLoader = router.loadAnchorPane(apIndex, "NewProjectDialog"+currentIndex+".fxml");
    }


}
