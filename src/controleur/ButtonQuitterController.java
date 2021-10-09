package controleur;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ButtonQuitterController implements EventHandler {

    /////////////////////////////////////////////////
    //Permet de quitter le processus
    /////////////////////////////////////////////////
    @Override
    public void handle(Event event) {
        Platform.exit();
    }
}
