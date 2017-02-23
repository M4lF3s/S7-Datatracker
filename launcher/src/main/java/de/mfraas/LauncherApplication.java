package de.mfraas;

import de.mfraas.fxclient.FXClientApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LauncherApplication {

	public static void main(String[] args) {
		FXClientApplication s7 = new FXClientApplication();
		Thread gui = new Thread(s7);
		gui.start();
		SpringApplication.run(de.mfraas.datatracker.S7serverApplication.class, args);
	}


}
