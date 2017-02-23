package de.mfraas.fxclient;

import com.sun.javafx.application.LauncherImpl;
import de.mfraas.fxclient.services.CommunicationsService;
import de.mfraas.fxclient.ui.FxRouter;

import insidefx.undecorator.UndecoratorScene;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.glassfish.jersey.media.sse.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.net.*;


/**
 * Created by marcelfraas on 18.01.17.
 */

@Component
public class FXClientApplication extends Application implements Runnable {

    private boolean serverIsStarted;

    private String serverState;

    public boolean isServerIsStarted() {
        return serverIsStarted;
    }

    public void setServerIsStarted(boolean serverIsStarted) {
        this.serverIsStarted = serverIsStarted;
    }

    public FXClientApplication(boolean start){
        this.serverIsStarted = start;
    }

    public FXClientApplication() { this.serverIsStarted = false; }

    private FxRouter router = new FxRouter();


    @Override
    public void init() throws InterruptedException, IOException {
        /*
        // Do some heavy lifting
        if(serverIsStarted) return;

        //TODO: Currently busy waiting. Should be done with Notification!

        try{
            ResponseEntity<String> result = CommunicationsService.status();
            if(result.getStatusCode().equals(HttpStatus.OK)){
                this.serverState = result.getBody();
                this.setServerIsStarted(true);
            }
        } catch (ResourceAccessException e) {
            //e.printStackTrace();
            System.out.println("Server not ready! Retrying...");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException i) {
                System.out.println("Waiting got interrupted");
            } finally {
                init();
            }
        } finally {
            if(!this.isServerIsStarted()){
                init();
            } else {
                return;
            }
        }*/

        SocketAddress sockaddr = new InetSocketAddress("127.0.0.1", 4200);
        // Create your socket
        Socket socket = new Socket();

        boolean online = false;

        try {
            socket.connect(sockaddr, 1000);
            online = true;
        } catch (SocketTimeoutException stex) {
            // treating timeout errors separately from other io exceptions
            // may make sense
            online=false;
        } catch (IOException iOException) {
            online = false;
        } finally {
            // As the close() operation can also throw an IOException
            // it must caught here
            try {
                socket.close();
            } catch (IOException ex) {
                // feel free to do something moderately useful here, eg log the event
            }

        }

        //System.out.println("Server ready.... Starting");

        if(online) {
            Client client = ClientBuilder.newBuilder()
                    .register(SseFeature.class).build();
            WebTarget target = client.target("http://localhost:4200/init.stream");

            int i = 0;

            try(
                    EventInput eventInput = target.request().get(EventInput.class);
            ) {
                while (!eventInput.isClosed()) {
                    final InboundEvent inboundEvent = eventInput.read();
                    if (inboundEvent == null) {
                        // connection has been closed
                        //System.out.println(i);
                        return;
                    }
                    //System.out.println(inboundEvent.readData(String.class));
                    i++;
                    double percent = ((100 * (double)i / 230)/100);
                    notifyPreloader(new Preloader.ProgressNotification(percent));
                    switch(inboundEvent.readData()) {
                        case "dataSource" : notifyPreloader(new de.mfraas.fxclient.ui.CusomNotification("Database"));
                        break;
                        case "config" : notifyPreloader(new de.mfraas.fxclient.ui.CusomNotification("Config"));
                        break;
                        case "transactionManager" : notifyPreloader(new de.mfraas.fxclient.ui.CusomNotification("Transaction Management"));
                        break;
                        case "org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration" : notifyPreloader(new de.mfraas.fxclient.ui.CusomNotification("Client"));
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Server not ready! Retrying...");
            }
        } else {
            init();
        }

    }

    private void startLoadingProcess() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/MainWindow.fxml"));
        Region root = loader.load();
        final UndecoratorScene undecoratorScene = new UndecoratorScene(primaryStage, null, root, "/gui/view/StageDecorationNoResize.fxml");
        primaryStage.setScene(undecoratorScene);


        notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

        router.setPrimaryStage(primaryStage);
        router.setMainWindowLoader(loader);
        router.checkServerStatus();
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
        try{
            CommunicationsService.shutdown();
        } catch(ResourceAccessException e){
            System.out.println("Ignoring ResourceAccessException");
        }
    }

    @Override
    public void run() {
        LauncherImpl.launchApplication(FXClientApplication.class, FXClientApplicationPreloader.class, null);
    }

}
