package model;

import controleur.ButtonQuitterController;
import controleur.CarteController;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Carte;
import model.Jeu;

import java.util.ArrayList;
import java.util.Collections;

import static javafx.stage.Modality.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        /////////////////////////////////////////////////
        //Création de nos layout et scene
        /////////////////////////////////////////////////
        BorderPane root = new BorderPane();
        GridPane gridCentre = new GridPane();
        Scene scene = new Scene(root,800,800);



        /////////////////////////////////////////////////
        //Création de nos composant ( btn, etc)
        /////////////////////////////////////////////////
        Button btnQuitter = new Button("Quitter");
        btnQuitter.addEventFilter(MouseEvent.MOUSE_PRESSED,new ButtonQuitterController());
        btnQuitter.setAlignment(Pos.CENTER);
        Button pauseButton = new Button("Pause");
        Button btnJour = new Button("Jour");
        btnJour.setOnAction(event -> {
            root.setStyle("-fx-base : rgba(255, 255, 255, 1);");
        });
        Button btnNuit = new Button("Nuit");
        btnNuit.setOnAction(event -> {
            root.setStyle("-fx-base : rgba(0, 0, 0, 1);");
        });
        HBox hbCouleur = new HBox();
        hbCouleur.getChildren().addAll(btnJour,btnNuit);
        hbCouleur.setAlignment(Pos.CENTER);
        root.setBottom(hbCouleur);
        root.setRight(pauseButton);
        root.setCenter(gridCentre);
        root.setStyle("-fx-base : rgba(255, 255, 255, 1);");
        root.setPadding(new Insets(25,25,25,25));
        gridCentre.setAlignment(Pos.CENTER);
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        rect.setFill(Color.CORAL);
        TranslateTransition animation = createAnimation(rect);

        /////////////////////////////////////////////////
        //Création de nos cartes
        /////////////////////////////////////////////////
        Carte cartebleu1 = new Carte("Bleu");
        Carte cartebleu2 = new Carte("Bleu");

        Carte carterouge1 = new Carte("Rouge");
        Carte carterouge2 = new Carte("Rouge");

        Carte cartevert1 = new Carte("Vert");
        Carte cartevert2 = new Carte("Vert");

        Carte cartejaune1 = new Carte("Jaune");
        Carte cartejaune2 = new Carte("Jaune");

        Carte carteviolet1 = new Carte("Violet");
        Carte carteviolet2 = new Carte("Violet");

        Carte cartenoir1 = new Carte("Noir");
        Carte cartenoir2 = new Carte("Noir");



        /////////////////////////////////////////////////
        //Melange des cartes grâce à une ArrayList et un shuffle
        /////////////////////////////////////////////////
        ArrayList<Carte> carteDuJeu = new ArrayList<>();
        carteDuJeu.add(cartejaune1);
        carteDuJeu.add(cartejaune2);
        carteDuJeu.add(carteviolet1);
        carteDuJeu.add(carteviolet2);
        carteDuJeu.add(carterouge1);
        carteDuJeu.add(carterouge2);
        carteDuJeu.add(cartebleu1);
        carteDuJeu.add(cartebleu2);
        carteDuJeu.add(cartevert1);
        carteDuJeu.add(cartevert2);
        carteDuJeu.add(cartenoir1);
        carteDuJeu.add(cartenoir2);
        Collections.shuffle(carteDuJeu);
        gridCentre.add(carteDuJeu.get(0).getVerso(), 0, 0);
        gridCentre.add(carteDuJeu.get(1).getVerso(), 1, 0);
        gridCentre.add(carteDuJeu.get(2).getVerso(), 2, 0);
        gridCentre.add(carteDuJeu.get(3).getVerso(), 3, 0);
        gridCentre.add(carteDuJeu.get(4).getVerso(), 0, 1);
        gridCentre.add(carteDuJeu.get(5).getVerso(), 1, 1);
        gridCentre.add(carteDuJeu.get(6).getVerso(), 2, 1);
        gridCentre.add(carteDuJeu.get(7).getVerso(), 3, 1);
        gridCentre.add(carteDuJeu.get(8).getVerso(), 0, 2);
        gridCentre.add(carteDuJeu.get(9).getVerso(), 1, 2);
        gridCentre.add(carteDuJeu.get(10).getVerso(), 2, 2);
        gridCentre.add(carteDuJeu.get(11).getVerso(), 3, 2);


        /////////////////////////////////////////////////
        //Evenement sur le btn pause -> Mise en place du menu en pause
        /////////////////////////////////////////////////
        pauseButton.setOnAction(e -> {
            animation.pause();
            root.setEffect(new GaussianBlur());

            VBox pauseRoot = new VBox(5);
            pauseRoot.getChildren().add(new Label("Pause"));
            pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
            pauseRoot.setAlignment(Pos.CENTER);
            pauseRoot.setPadding(new Insets(20));

            Button resume = new Button("Reprendre");
            pauseRoot.getChildren().add(resume);
            pauseRoot.getChildren().add(btnQuitter);

            Stage popupStage;
            popupStage = new Stage(StageStyle.TRANSPARENT);
            popupStage.initOwner(primaryStage);
            popupStage.initModality(APPLICATION_MODAL);
            popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));


            resume.setOnAction(event -> {
                root.setEffect(null);
                animation.play();
                popupStage.hide();
            });

            popupStage.show();
        });

        /////////////////////////////////////////////////
        //Code pour le menu de base ( page principale)
        /////////////////////////////////////////////////
        GridPane menu = new GridPane();
        menu.setId("menu");
        Scene SceneMenu = new Scene(menu,800,800);
        SceneMenu.getStylesheets().addAll(this.getClass().getResource("../vue/menu.css").toExternalForm());
        Button btnLancerJeu = new Button("Lancer une partie !");
        Label lblDfficulte = new Label("Choisissez une difficulté :");
        ComboBox cbbDifficulte = new ComboBox();
        cbbDifficulte.getItems().addAll("Facile","Moyen","Difficile");
        cbbDifficulte.setValue("Facile");
        Label lblTheme = new Label("Choisissez un thème :");
        ComboBox cbbTheme = new ComboBox();
        cbbTheme.getItems().addAll("HearthStone","Pokemon");
        cbbTheme.setValue("Pokemon");
        menu.add(lblDfficulte,0,0);
        menu.add(cbbDifficulte,1,0);
        menu.add(lblTheme,0,1);
        menu.add(cbbTheme,1,1);
        menu.add(btnLancerJeu,0,3,2,2);
        menu.add(btnQuitter,0,5,2,2);
        cbbDifficulte.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        cbbTheme.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnLancerJeu.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnQuitter.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        lblTheme.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        menu.setAlignment(Pos.CENTER);
        lblDfficulte.setPadding(new Insets(0,10,0,0));


        /////////////////////////////////////////////////
        //Evenement sur le btn LancerJeu
        /////////////////////////////////////////////////
        btnLancerJeu.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Création de notre thème pokémon
                if("Pokemon".equals(cbbTheme.getValue())){
                    cartebleu1.setImage("Pokemon");
                    cartebleu2.setImage("Pokemon");
                    carterouge1.setImage("Pokemon");
                    carterouge2.setImage("Pokemon");
                    cartejaune1.setImage("Pokemon");
                    cartejaune2.setImage("Pokemon");
                    cartevert1.setImage("Pokemon");
                    cartevert2.setImage("Pokemon");
                    cartenoir1.setImage("Pokemon");
                    cartenoir2.setImage("Pokemon");
                    carteviolet1.setImage("Pokemon");
                    carteviolet2.setImage("Pokemon");
                    gridCentre.add(carteDuJeu.get(0).getImage(), 0, 0);
                    gridCentre.add(carteDuJeu.get(1).getImage(), 1, 0);
                    gridCentre.add(carteDuJeu.get(2).getImage(), 2, 0);
                    gridCentre.add(carteDuJeu.get(3).getImage(), 3, 0);

                    gridCentre.add(carteDuJeu.get(4).getImage(), 0, 1);
                    gridCentre.add(carteDuJeu.get(5).getImage(), 1, 1);
                    gridCentre.add(carteDuJeu.get(6).getImage(), 2, 1);
                    gridCentre.add(carteDuJeu.get(7).getImage(), 3, 1);

                    gridCentre.add(carteDuJeu.get(8).getImage(), 0, 2);
                    gridCentre.add(carteDuJeu.get(9).getImage(), 1, 2);
                    gridCentre.add(carteDuJeu.get(10).getImage(), 2, 2);
                    gridCentre.add(carteDuJeu.get(11).getImage(), 3, 2);
                }
                //Création de notre thème HearthStone
                if("HearthStone".equals(cbbTheme.getValue())){
                    cartebleu1.setImage("HearthStone");
                    cartebleu2.setImage("HearthStone");
                    carterouge1.setImage("HearthStone");
                    carterouge2.setImage("HearthStone");
                    cartejaune1.setImage("HearthStone");
                    cartejaune2.setImage("HearthStone");
                    cartevert1.setImage("HearthStone");
                    cartevert2.setImage("HearthStone");
                    cartenoir1.setImage("HearthStone");
                    cartenoir2.setImage("HearthStone");
                    carteviolet1.setImage("HearthStone");
                    carteviolet2.setImage("HearthStone");
                    gridCentre.add(carteDuJeu.get(0).getImage(), 0, 0);
                    gridCentre.add(carteDuJeu.get(1).getImage(), 1, 0);
                    gridCentre.add(carteDuJeu.get(2).getImage(), 2, 0);
                    gridCentre.add(carteDuJeu.get(3).getImage(), 3, 0);

                    gridCentre.add(carteDuJeu.get(4).getImage(), 0, 1);
                    gridCentre.add(carteDuJeu.get(5).getImage(), 1, 1);
                    gridCentre.add(carteDuJeu.get(6).getImage(), 2, 1);
                    gridCentre.add(carteDuJeu.get(7).getImage(), 3, 1);

                    gridCentre.add(carteDuJeu.get(8).getImage(), 0, 2);
                    gridCentre.add(carteDuJeu.get(9).getImage(), 1, 2);
                    gridCentre.add(carteDuJeu.get(10).getImage(), 2, 2);
                    gridCentre.add(carteDuJeu.get(11).getImage(), 3, 2);
                }

                //Création de notre mode de jeu difficile
                if("Difficile".equals(cbbDifficulte.getValue())){
                    Jeu jeuDifficile= new Jeu("Difficile");
                    cartebleu1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartebleu1,jeuDifficile));
                    cartebleu2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartebleu2,jeuDifficile));

                    carterouge1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carterouge1,jeuDifficile));
                    carterouge2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carterouge2,jeuDifficile));

                    cartevert1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartevert1,jeuDifficile));
                    cartevert2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartevert2,jeuDifficile));

                    cartejaune1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartejaune1,jeuDifficile));
                    cartejaune2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartejaune2,jeuDifficile));

                    carteviolet1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carteviolet1,jeuDifficile));
                    carteviolet2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carteviolet2,jeuDifficile));

                    cartenoir1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartenoir1,jeuDifficile));
                    cartenoir2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartenoir2,jeuDifficile));

                    primaryStage.setScene(scene);
                }
                //Création de notre mode de jeu facile
                if("Facile".equals(cbbDifficulte.getValue())){
                    Jeu jeuFacile = new Jeu("Facile");

                    cartebleu1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartebleu1,jeuFacile));
                    cartebleu2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartebleu2,jeuFacile));

                    carterouge1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carterouge1,jeuFacile));
                    carterouge2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carterouge2,jeuFacile));

                    cartevert1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartevert1,jeuFacile));
                    cartevert2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartevert2,jeuFacile));

                    cartejaune1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartejaune1,jeuFacile));
                    cartejaune2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartejaune2,jeuFacile));

                    carteviolet1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carteviolet1,jeuFacile));
                    carteviolet2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carteviolet2,jeuFacile));

                    cartenoir1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartenoir1,jeuFacile));
                    cartenoir2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartenoir2,jeuFacile));

                    primaryStage.setScene(scene);
                }
                //Création de notre mode de jeu moyen
                if("Moyen".equals(cbbDifficulte.getValue())){
                    Jeu jeuMoyen = new Jeu("Moyen");

                    cartebleu1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartebleu1,jeuMoyen));
                    cartebleu2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartebleu2,jeuMoyen));

                    carterouge1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carterouge1,jeuMoyen));
                    carterouge2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carterouge2,jeuMoyen));

                    cartevert1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartevert1,jeuMoyen));
                    cartevert2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartevert2,jeuMoyen));

                    cartejaune1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartejaune1,jeuMoyen));
                    cartejaune2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartejaune2,jeuMoyen));

                    carteviolet1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carteviolet1,jeuMoyen));
                    carteviolet2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(carteviolet2,jeuMoyen));

                    cartenoir1.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartenoir1,jeuMoyen));
                    cartenoir2.getImage().addEventFilter(MouseEvent.MOUSE_PRESSED,new CarteController(cartenoir2,jeuMoyen));

                    primaryStage.setScene(scene);
                }
            }
        });



        //Pour que la dimension de la fenetre ne puisse pas être changer
        primaryStage.setResizable(false);

        //Affichage de la scène principale de jeu
        primaryStage.setScene(SceneMenu);
        primaryStage.setTitle("Jeu memory");
        primaryStage.show();
    }

    private TranslateTransition createAnimation (Rectangle rect) {
        TranslateTransition animation = new TranslateTransition(Duration.seconds(1), rect);
        animation.setByX(400);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
        return animation;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
