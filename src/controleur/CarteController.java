package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.Carte;
import model.Jeu;

public class CarteController implements EventHandler {
    private Carte carte;
    private Jeu jeu;

    public CarteController(Carte carte, Jeu jeu) {
        this.carte = carte;
        this.jeu = jeu;
    }

    @Override
    public void handle(Event event) {
        carte.getImage().setVisible(false);
        /////////////////////////////////////////////////
        //Envoi nos cartes vers notre moteur de jeu principal
        /////////////////////////////////////////////////
        jeu.comparateurDeCarte(carte);
    }
}
