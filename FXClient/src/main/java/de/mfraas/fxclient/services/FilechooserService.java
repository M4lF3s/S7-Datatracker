package de.mfraas.fxclient.services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * Created by marcelfraas on 22.01.17.
 */
public class FilechooserService {

    public static File saver(List<String> dataTypes, String title) {
        FileChooser saver = new FileChooser();
        saver.setTitle(title);
        dataTypes.forEach((e) -> {
            if(e.equals("")) {
                saver.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("all Files", "*.*")
                );
            } else {
                saver.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("."+e+" Files", "*."+e)
                );
            }
        });
        return saver.showSaveDialog(new Stage());
    }

    public static File opener(List<String> dataTypes, String title, String initialDirectory) {
        FileChooser opener = new FileChooser();
        opener.setTitle(title);
        opener.setInitialDirectory(
                new File(initialDirectory)
        );
        dataTypes.forEach((e) -> {
            if(e.equals("")) {
                opener.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("all Files", "*.*")
                );
            } else {
                opener.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("."+e+" Files", "*."+e)
                );
            }
        });
        return opener.showOpenDialog(new Stage());
    }

}
