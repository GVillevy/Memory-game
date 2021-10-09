package model;

import javafx.animation.PauseTransition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Carte;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Jeu {
    private ArrayList<Carte> mesCartes;
    int compteur = -1;
    private String difficulte;
    private MediaPlayer musique;

    public Jeu(String difficulte) {
        this.mesCartes = new ArrayList<>();
        this.difficulte = difficulte;
        String musicFile = "src\\vue\\ressources\\son\\ting-sound-effect.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        this.musique = new MediaPlayer(sound);
    }

    public void comparateurDeCarte(Carte carte) {
        /////////////////////////////////////////////////
        //Detail du mode de jeu en facile
        //-> On voit les 2 cartes choisie à durée infinie
        /////////////////////////////////////////////////
        if ("Facile".equals(difficulte)) {
            mesCartes.add(carte);
            compteur++;
            if (mesCartes.size() >= 3) {
                if (mesCartes.get(0).getClasse().equals(mesCartes.get(1).getClasse())) {
                    musique.play();
                } else {
                    mesCartes.get(0).getImage().setVisible(true);
                    mesCartes.get(1).getImage().setVisible(true);
                }
                Collections.swap(mesCartes, 0, 2);
                Carte carteTemporaire = mesCartes.get(0);
                mesCartes.clear();
                mesCartes.add(carteTemporaire);
            }
            System.out.println(compteur);
        }
        /////////////////////////////////////////////////
        //Detail du mode de jeu en difficile
        //-> On ne voit pas les 2 cartes retournées mais seulement une
        /////////////////////////////////////////////////
        if ("Difficile".equals(difficulte)) {
            mesCartes.add(carte);
            carte.getImage().setVisible(false);

            if (mesCartes.size() >= 2) {

                if (mesCartes.get(0).getClasse().equals(mesCartes.get(1).getClasse())) {
                    musique.play();
                } else {
                    mesCartes.get(0).getImage().setVisible(true);
                    mesCartes.get(1).getImage().setVisible(true);
                }
                compteur++;
                System.out.println(compteur);
                mesCartes.clear();
            }
        }
        /////////////////////////////////////////////////
        //Detail du mode de jeu en moyen
        //-> On voit les 2 cartes choisie pendant 0.5 seconde
        /////////////////////////////////////////////////
        if ("Moyen".equals(difficulte)) {
            mesCartes.add(carte);
            carte.getImage().setVisible(false);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(event -> {
                if (mesCartes.size() >= 2) {

                    if (mesCartes.get(0).getClasse().equals(mesCartes.get(1).getClasse())) {
                        musique.play();
                    } else {
                        mesCartes.get(0).getImage().setVisible(true);
                        mesCartes.get(1).getImage().setVisible(true);
                    }
                    compteur++;
                    System.out.println(compteur);
                    mesCartes.clear();
                }
            });
            pause.play();
        }
    }

}
