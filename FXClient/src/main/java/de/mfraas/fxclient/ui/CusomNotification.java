package de.mfraas.fxclient.ui;

import javafx.application.Preloader;

/**
 * Created by marcelfraas on 20.02.17.
 */
public class CusomNotification implements Preloader.PreloaderNotification {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CusomNotification(String text) {
        this.text = text;
    }
}
